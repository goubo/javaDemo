package com.bobo.javaDemo.http_chunk_test;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 测试
 *
 * @author BO
 * @date 2021-02-06 14:48
 * @since 2021/2/6
 **/
public class Server {
  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    Router router = Router.router(vertx);
    vertx.createHttpServer().requestHandler(router).listen(10789);
    router.get("/test/chunk").handler(routingContext -> {
      System.out.println("收到请求");
      HttpServerResponse response = routingContext.response();
      response.setChunked(true);
      AtomicInteger i = new AtomicInteger();
      AtomicReference<Long> timerId = new AtomicReference<>();
      vertx.setPeriodic(1000, id -> {
        timerId.set(id);
        String s = LocalDateTime.now().toString();
        System.out.println("输出中:" + s);
        response.write(s);
        response.write("\n");
        if (i.getAndIncrement() == 10) {
          vertx.cancelTimer(timerId.get());
          System.out.println("输出完毕");
          response.end();//服务端发送结束符
        }
      });
      response.closeHandler(close -> System.out.println("response close"));
      response.endHandler(end -> System.out.println("response end"));
      response.exceptionHandler(event -> {
        System.out.println("response exception");
        vertx.cancelTimer(timerId.get());
        event.printStackTrace();

      });
    });

  }
}

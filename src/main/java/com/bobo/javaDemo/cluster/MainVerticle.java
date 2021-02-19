package com.bobo.javaDemo.cluster;

import com.hazelcast.config.Config;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.spi.cluster.hazelcast.ConfigUtil;
import io.vertx.spi.cluster.hazelcast.HazelcastClusterManager;

/**
 * v1
 *
 * @author BO
 * @date 2021-02-18 15:01
 * @since 2021/2/18
 **/
public class MainVerticle extends AbstractVerticle {
  public static void main(String[] args) {
    Config hazelcastConfig = ConfigUtil.loadConfig();
    hazelcastConfig.setClusterName("my-cluster-name");
    hazelcastConfig.setInstanceName("instanceName bb");
    HazelcastClusterManager mgr = new HazelcastClusterManager(hazelcastConfig);
    VertxOptions options = new VertxOptions().setClusterManager(mgr);
    Vertx.clusteredVertx(options, res -> {
      if (res.succeeded()) {
        Vertx vertx = res.result();
        vertx.deployVerticle(MainVerticle.class.getName());
      } else {
        System.out.println(res.cause().getMessage());
      }
    });
  }

  @Override
  public void start(Promise<Void> startPromise) throws Exception {

  }

  @Override
  public void stop(Promise<Void> stopPromise) throws Exception {

  }
}

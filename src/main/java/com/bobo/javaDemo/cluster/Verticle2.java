package com.bobo.javaDemo.cluster;

import com.hazelcast.config.Config;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.spi.cluster.hazelcast.ConfigUtil;
import io.vertx.spi.cluster.hazelcast.HazelcastClusterManager;

/**
 * Verticle
 *
 * @author BO
 * @date 2021-02-18 15:17
 * @since 2021/2/18
 **/
public class Verticle2 extends AbstractVerticle {
  public static void main(String[] args) {
    Config hazelcastConfig = ConfigUtil.loadConfig();
    hazelcastConfig.setClusterName("my-cluster-name");
    hazelcastConfig.setInstanceName("instanceName cc");
    HazelcastClusterManager mgr = new HazelcastClusterManager(hazelcastConfig);
    VertxOptions options = new VertxOptions().setClusterManager(mgr);
    Vertx.clusteredVertx(options, res -> {
      if (res.succeeded()) {
        Vertx vertx = res.result();
        vertx.deployVerticle(Verticle2.class.getName());
      } else {
        System.out.println(res.cause().getMessage());
      }
    });
  }

  @Override
  public void start(Promise<Void> startPromise) throws Exception {

  }
}

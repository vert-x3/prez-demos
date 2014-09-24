package io.vertx.example;

import io.vertx.core.AbstractVerticle;

import static io.vertx.core.http.HttpServerOptions.*;

public class HelloWorld extends AbstractVerticle {
  public void start() {
    vertx.createHttpServer(options().setPort(8080)).requestHandler(req -> {
      req.response().end("<body><h1>Hello World from Java!</h1></body>");
    }).listen();
  }
}

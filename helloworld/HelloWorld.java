import io.vertx.core.Handler;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServerOptions;

public class HelloWorld extends AbstractVerticle {
  public void start() {
    vertx.createHttpServer(new HttpServerOptions().setPort(8080)).requestHandler(req -> {
      req.response().end("<body><h1>Hello World from Java!</h1></body>");
    }).listen();
  }
}

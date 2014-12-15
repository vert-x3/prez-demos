import io.vertx.core.http.HttpServerOptions;
import io.vertx.lang.rxjava.AbstractVerticle;
import io.vertx.rxjava.core.http.HttpServer;

public class Server extends AbstractVerticle {

  public void start() {
    HttpServer server = vertx.createHttpServer(new HttpServerOptions().setPort(8080).setHost("localhost"));
    server.requestStream().toObservable().forEach( req -> {
      System.out.println("Server => Request: " + req.path());
      try {
        if (req.path().equals("/error")) {
          throw new RuntimeException("forced error");
        }
        req.response().setChunked(true).end("Path Requested =>: " + req.path() + '\n');
      } catch(Throwable e) {
        System.err.println("Server => Error [" + req.path() + "] => " + e);
        req.response().setStatusCode(500).setChunked(true).end("Error 500: Bad Request\n");
      }
    });
    server.listenObservable().forEach( s -> {
      System.out.println("server started");        
    });    
  }
}
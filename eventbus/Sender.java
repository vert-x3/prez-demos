import io.vertx.core.Handler;
import io.vertx.core.AbstractVerticle;

public class Sender extends AbstractVerticle {

  public void start() {
    vertx.setPeriodic(1000, timerID -> {
      System.out.println("sending");
      vertx.eventBus().publish("news-feed", "News from Java!");
    });
  }
}

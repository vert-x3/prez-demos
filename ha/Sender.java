import io.vertx.core.Handler;
import io.vertx.core.AbstractVerticle;

public class Sender extends AbstractVerticle {

  public void start() {
    vertx.setPeriodic(1000, timerID -> vertx.eventBus().publish("news-feed", "News from Java!"));
  }
}

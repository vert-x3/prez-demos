import io.vertx.core.AbstractVerticle;
import rx.Observable;
import io.vertx.rx.java.RxHelper;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Receiver extends AbstractVerticle {

  public void start() {
    Observable<Double> observable = RxHelper.toObservable(vertx.eventBus().<Double>consumer("heat-sensor").bodyStream());
    observable.
         buffer(1, TimeUnit.SECONDS).
         map(samples -> samples.stream().
         collect(Collectors.averagingDouble(d -> d))).
         subscribe(heat -> {
      System.out.println("Current heat is " + heat);
      // vertx.eventBus().send("news-feed", "Current heat is " + heat);
    });
  }
}

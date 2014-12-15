import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.HttpMethod;
import io.vertx.lang.rxjava.AbstractVerticle;
import io.vertx.rxjava.core.http.HttpClient;
import io.vertx.rxjava.core.http.HttpClientRequest;
import io.vertx.rxjava.core.http.HttpClientResponse;


public class Client extends AbstractVerticle {

  public void start() {
    HttpClient client = vertx.createHttpClient(new HttpClientOptions());

    HttpClientRequest req1 = client.request(HttpMethod.GET, 8080, "localhost", "/the_uri");
    req1.toObservable().
        flatMap(HttpClientResponse::toObservable).
        map(data -> "Client => " + data.toString("UTF-8")).
        forEach(System.out::println);
    req1.end();

    HttpClientRequest req2 = client.request(HttpMethod.GET, 8080, "localhost", "/error");
    req2.toObservable().
        flatMap(HttpClientResponse::toObservable).
        map(data -> "Client => " + data.toString("UTF-8")).
        forEach(System.out::println);
    req2.end();
    
    HttpClientRequest req3 = client.request(HttpMethod.GET, 8080, "localhost", "/data");
    req3.toObservable().
        flatMap(HttpClientResponse::toObservable).
        map(data -> "Client => " + data.toString("UTF-8")).
        forEach(System.out::println);
    req3.end();
  }
}
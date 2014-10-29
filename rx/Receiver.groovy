import java.util.concurrent.TimeUnit;

def observable = vertx.eventBus().consumer("heat-sensor").bodyStream().toObservable();
observable.
   buffer(1, TimeUnit.SECONDS).
   filter({ values -> !values.empty }).
   map({ values -> values.sum() / values.size() }).
   subscribe({ heat ->
   System.out.println("Current heat is " + heat);
});
	
	

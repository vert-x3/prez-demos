def eb = vertx.eventBus()

// The heat sensor sents a value every 100 millis
//   sin wave simulates some external sin wave temperature
//   random simulates some random noise

vertx.setPeriodic(100) {
  def heat = 15 + 10 * Math.sin(System.currentTimeMillis() / 10000.0) + Math.random() * 2;
  eb.publish("heat-sensor", heat)
}

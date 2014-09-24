def eb = vertx.eventBus()

// Send a message every second

vertx.setPeriodic(1000) {
  eb.publish("news-feed", "News from Groovy!")
}

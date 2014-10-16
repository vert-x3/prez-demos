vertx.eventBus().consumer("news-feed").handler(function(message) {
  console.log('Received news: ' + message.body());
});
console.log("listening");
vertx.eventBus().registerHandler("news-feed", function(message) {
  console.log('Received news: ' + message.body());
});

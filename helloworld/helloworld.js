vertx.createHttpServer({port:8080}).requestHandler(function(req) {
  req.response().end("<body><h1>Hello World from JavaScript!</h1></body>");
}).listen()

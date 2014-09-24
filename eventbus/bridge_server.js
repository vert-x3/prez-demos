var SockJS = require("ext-sockjs-js/sock_js_server.js");

var server = vertx.createHttpServer({host:"localhost", port:8080})

// Serve the static resources
server.requestHandler(function(req) {
  var file = req.path() === '/' ? 'index.html' : req.path()
  req.response().sendFile("./" + file)
})

// Create a SockJS bridge which lets everything through (be careful!)
var sockJSServer = SockJS.sockJSServer(vertx, server);
sockJSServer.bridge({prefix: "/eventbus"}, {inboundPermitteds: [{}], outboundPermitteds: [{}]})

server.listen()

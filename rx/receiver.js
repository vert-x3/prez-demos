Rx = require("rx.time");
Rx = require("rx.vertx");
var consumer = vertx.eventBus().consumer("heat-sensor").bodyStream();
var observable = Rx.Observable.fromReadStream(consumer);
observable.
  bufferWithTime(1000).
  filter(function (arr) { return arr.length > 0; }).
  map(function (arr) { return arr.reduce(function (acc, x) { return acc + x; }, 0) / arr.length; }).
  subscribe(function (heat) {
	  console.log('Current heat is: ' + heat);
});
console.log("listening");
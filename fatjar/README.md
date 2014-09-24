# Vert.x 3.0 Example Application

This is an example of a simple application using Vert.x 3.0 and Maven.

As you can see the project is a very standard Maven project with no special magic. In Vert.x 3.0 dependencies are
just simple jars so you can use the standard Maven dependency resolution to pull them in at build time.

The application is a simple, somewhat contrived, web application which uses the Vert.x event bus on the client side
to receive stock price updates, and allows the user to place orders for stock using a simple REST API.

As orders are received on the server side they are persisted in a MongoDB instance. We use the Vert.x MongoService to do that.

The application is comprised of three verticles, written in three different languages.

## WebServer

This verticle implements a simple web server which does a few different things:

1. It serves static web resources such as `index.html` and `vertxbus.js` to the browser. The web resources are in the
`src/main/webapp` directory, as that's the Maven convention.

2. It implements a very simple REST API (currently there is just one operation - to post an order).

3. It starts up a SockJS-eventbus bridge. This allows the event bus API to be used in client side JavaScript and bridges
the browser<-->server event bus traffic across SockJS connections.

This verticle is written in Java.

## StockTicker

This verticle simply updates random price updates for various stocks periodically.

It is written in Groovy.

## app.js

This verticle is just a simple JavaScript script which handles starting up the other verticles in the application.

It's a common Vert.x pattern to use a starter verticle like this, usually written in a scripting language, to co-ordinate the start-up.

You can, of course just run the other verticles directly if you prefer.

# Building and running the application

## Install MongoDB.

If you haven't already got it installed. It needs to be running on localhost:27017 (the defaults) for the demo to work.

## Create symbolic link for web resources

It's a Maven convention to put your web resources in the `src/main/webapp` directory. But, at run-time, the web server
looks (by default) for web resources in the `web` directory. So we can run the example from the source layout, we create
a symbolink link between the two:

    ln -s src/main/webapp web


## Import the project into your IDE.

It's a pretty standard Maven project so is well supported by all IDEs.

## Run the application in your IDE.

For easy debugging you can run the application in your IDE.

There's a simple main class called `MyMain` which just deploys the verticle `app.js`.

## Build the project

You build the project using the standard `mvn package`.

Artifacts are created in the `target` directory.

We also create a jar artifact with the name suffix `-fat.jar`. This is a fat executable jar which you can contains
all the dependencies of the application.

## Running at the command line

You can run the fat executable jar with:

    java -jar target/example-project-1.0-SNAPSHOT-fat.jar

This doesn't require Vert.x to be pre-installed on the machine you are running it on. You just need a Java8+ JDK.


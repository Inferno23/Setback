/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.application.client;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import setback.application.server.SetbackVertxServer;

/**
 * This class functions as the client that a player will run
 * when they want to play a game of Setback.
 * This implementation relies on Vertx.
 * @author Mike Burns
 */
public class SetbackVertxClient {

  public static void main(String[] args) {
    VertxOptions options = new VertxOptions()
        .setClusterHost(SetbackVertxServer.HOST)
        .setClusterPort(SetbackVertxServer.PORT);
    Vertx.clusteredVertx(options, handler -> {
      if (handler.succeeded()) {
        final Vertx vertx = handler.result();
        System.out.println("Client connected.");
      }
    });
  }
}

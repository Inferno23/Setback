/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.application.client;

import io.vertx.core.Vertx;
import setback.application.server.SetbackVertxServer;

/**
 * This class functions as the client that a player will run
 * when they want to play a game of Setback.
 * This implementation relies on Vertx.
 * @author Mike Burns
 */
public class SetbackVertxClient {

  public static void main(String[] args) {

    final Vertx vertx = Vertx.vertx();
    vertx.createNetClient().connect(SetbackVertxServer.PORT, SetbackVertxServer.HOST,
        handler -> {
          if (handler.succeeded()) {
            System.out.println("Client connected successfully!");
          } else {
            System.out.println("Client failed to connect.");
            System.out.println(handler.cause());
            System.exit(-1);
          }
        });
  }
}

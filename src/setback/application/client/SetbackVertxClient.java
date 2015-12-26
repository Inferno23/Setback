/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.application.client;

import io.vertx.core.Vertx;
import io.vertx.core.net.NetSocket;
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
        connectionHandler -> {
          if (connectionHandler.succeeded()) {
            System.out.println("Client connected successfully!");
            NetSocket netSocket = connectionHandler.result();
            netSocket.handler(socketHandler -> {
              // TODO: This is where all of the listening logic will go, so make it a helper method.
              System.out.println(socketHandler.toString());
            });
          } else {
            System.out.println("Client failed to connect.");
            System.out.println(connectionHandler.cause());
            System.exit(-1);
          }
        });
  }
}

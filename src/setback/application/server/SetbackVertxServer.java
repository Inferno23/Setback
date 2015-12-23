/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.application.server;

import io.netty.util.internal.ConcurrentSet;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;
import setback.application.socket.SocketIOPair;
import setback.game.SetbackGameController;
import setback.game.SetbackGameFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * This class functions as the server that the players' clients
 * will connect to when they want to play a game of Setback.
 * When a client connects to this server, a verticle is deployed.
 * @author Mike Burns
 */
public class SetbackVertxServer {

  // TODO: HOST and PORT
  public static final String HOST = "localhost";
  public static final int PORT = 8080;
  private static final int MAX_PLAYERS = 4;
  public static final String CONNECTION_CHANNEL = "CONNECTION_CHANNEL";

  /**
   * This is the executable function that creates the server.
   * By default, the server runs on port 2323, but this can
   * be changed if a different port is passed in as the only
   * argument.
   * This function also instantiates the SetbackGameController
   * which is passed to each verticle.
   * @param args Default variable for java.  If a number is
   * passed in as the only argument, it will be used as the
   * port number for the socket connection.
   */
  public static void main(String[] args) {
    final SetbackGameController game;
    final VertxOptions options;
    final ConcurrentSet<String> connections = new ConcurrentSet<>();

    // TODO: Debug vs Retail
    game = SetbackGameFactory.getInstance().makeDeltaSetbackGame();

    // TODO: Default options
    options = new VertxOptions().setClusterHost(HOST).setClusterPort(PORT);

    Vertx.clusteredVertx(options, handler -> {
      if (handler.succeeded()) {
        final Vertx vertx = handler.result();
        final EventBus eventBus = vertx.eventBus();

        eventBus.<String>consumer(CONNECTION_CHANNEL, connectionHandler -> {
          String message = connectionHandler.body();
          System.out.println("Connection from " + message);
          connections.add(message);
          // TODO: Send back a channel to listen on?

          if (connections.size() == MAX_PLAYERS) {
            // TODO: Send a message to start the game
          }
        });

      } else {
        System.out.println("Failed to deploy clustered vertx instance.");
        System.out.println(handler.cause());
        System.exit(-1);
      }
    });
  }
}

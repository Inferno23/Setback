/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.application.server;

import io.netty.util.internal.ConcurrentSet;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.net.NetServer;
import io.vertx.core.net.NetServerOptions;
import io.vertx.core.net.NetSocket;
import io.vertx.core.spi.cluster.ClusterManager;
import setback.game.SetbackGameController;
import setback.game.SetbackGameFactory;
import io.vertx.spi.cluster.hazelcast.HazelcastClusterManager;
import com.hazelcast.config.Config;
import sun.nio.ch.Net;

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
    final Vertx vertx;
    final EventBus eventBus;
    // TODO: Connections should be in the server.
    final ConcurrentSet<NetSocket> connections = new ConcurrentSet<>();

    // TODO: Debug vs Retail
    game = SetbackGameFactory.getInstance().makeDeltaSetbackGame();

    vertx = Vertx.vertx();
    final NetServerOptions serverOptions = new NetServerOptions()
        .setHost(HOST)
        .setPort(PORT);
    NetServer netServer = vertx.createNetServer(serverOptions)
        .connectHandler(connectHandler -> {
          System.out.println("Connect handler called.");
          connections.add(connectHandler);
          if (connections.size() == MAX_PLAYERS) {
            System.out.println("Time to start the game");
            int number = 1;
            for (NetSocket client : connections) {
              client.write("Hello there client #" + number++);
            }
          }
        });
    netServer.listen(handler -> {
      if (handler.succeeded()) {
        System.out.println("Server deployed successfully!");
      } else {
        System.out.println("Server failed to deploy.");
        System.out.println(handler.cause());
        System.exit(-1);
      }
    });

  }
}

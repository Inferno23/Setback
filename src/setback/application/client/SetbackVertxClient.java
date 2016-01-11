/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.application.client;

import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetSocket;
import setback.application.command.Command;
import setback.application.command.CommandMessageJson;
import setback.application.server.SetbackVertxServer;
import setback.application.views.PlayerSelectView;
import setback.common.PlayerNumber;

import static setback.common.PlayerNumber.*;

import javax.swing.*;

/**
 * This class functions as the client that a player will run
 * when they want to play a game of Setback.
 * This implementation relies on Vertx.
 * @author Mike Burns
 */
public class SetbackVertxClient {
  private static SetbackClientController controller;

  public static void main(String[] args) {

    final Vertx vertx = Vertx.vertx();
    vertx.createNetClient().connect(SetbackVertxServer.PORT, SetbackVertxServer.HOST,
        connectionHandler -> {
          if (connectionHandler.succeeded()) {
            System.out.println("Client connected successfully!");

            controller = new SetbackVertxClientControllerImpl(vertx);

            // Create the initial view
            new PlayerSelectView(controller);


            NetSocket netSocket = connectionHandler.result();
            netSocket.handler(socketHandler -> {
              // TODO: This is where all of the listening logic will go, so make it a helper method.
              CommandMessageJson command =
                  new CommandMessageJson(socketHandler.toString());
              handleServerInput(command);
              System.out.println(socketHandler.toString());
            });
          } else {
            System.out.println("Client failed to connect.");
            System.out.println(connectionHandler.cause());
            System.exit(-1);
          }
        });
  }

  /**
   * This method will handle input from the server
   * and call the appropriate method on the client controller.
   * @param commandMessageJson
   */
  private static void handleServerInput(CommandMessageJson commandMessageJson) {
    switch(commandMessageJson.getCommand()) {
      case REQUEST_PLAYER_ONE:
        controller.requestPlayer(PLAYER_ONE);
        break;
      case REQUEST_PLAYER_TWO:
        controller.requestPlayer(PLAYER_TWO);
        break;
      default:
        // TODO: Implement other commands.
    }
  }
}

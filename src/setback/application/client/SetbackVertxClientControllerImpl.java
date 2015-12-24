/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.application.client;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import setback.application.command.Command;
import setback.application.command.CommandMessageJson;
import setback.common.PlayerNumber;
import setback.common.SetbackRuntimeException;

/**
 * This class is the brains behind the SetbackClient using vertx.
 * The GUI views will call the methods on this class to send
 * messages back to the server.
 * @author Mike Burns
 */
public class SetbackVertxClientControllerImpl extends SetbackClientControllerSkeleton
    implements SetbackClientController {

  private Vertx vertx;
  private EventBus eventBus;

  /**
   * Constructor which takes in the shared vertx instance so we
   * can get the event bus and set up our consumers.
   * @param vertx The clustered vertx instance shared among the clients.
   */
  SetbackVertxClientControllerImpl(Vertx vertx) {
    // TODO: Remove the super class.
    super();
    this.vertx = vertx;
    this.eventBus = vertx.eventBus();
    // Set up our consumers
    configureConsumers();
  }

  /**
   * Helper method to configure the consumers of the event bus.
   */
  private void configureConsumers() {
    // TODO: Maybe remove this, maybe improve this
    eventBus.consumer("SOME_CHANNEL", handler -> {

    });
  }

  @Override
  public void setPlayerNumbersFromString(String input) {
    // TODO: Implement me.
  }

  @Override
  public String userInput(String input) {
    // TODO: Implement me.
    return null;
  }

  @Override
  public String requestPlayer(PlayerNumber playerNumber) {
    Command command;
    switch (playerNumber) {
      case PLAYER_ONE:
        command = Command.REQUEST_PLAYER_ONE;
        break;
      case PLAYER_TWO:
        command = Command.REQUEST_PLAYER_TWO;
        break;
      case PLAYER_THREE:
        command = Command.REQUEST_PLAYER_THREE;
        break;
      case PLAYER_FOUR:
        command = Command.REQUEST_PLAYER_FOUR;
        break;
      default:
        return "rejected";
    }

    CommandMessageJson json = CommandMessageJson.constructCommandMessage(command);

    // TODO: Need a better solution than this.
    final String[] result = new String[1];

    // TODO: Abstract this into a method.
    vertx.<String>executeBlocking(blockingHandler -> {
      blockingHandler.complete(sendCommandMessageJson(json));
    }, resultHandler -> {
      if (resultHandler.succeeded()) {
        result[0] = resultHandler.result();
      } else {
        result[0] = "rejected";
      }
    });

    return result[0];
  }

  /**
   * Helper method to handle sending a commandMessage from the client
   * to the server.
   * @param commandMessage The commandMessage to send to the server.
   * @return The reply from the server.
   */
  private String sendCommandMessageJson(CommandMessageJson commandMessage) {
    // TODO: Implement me.
    return "";
  }
}

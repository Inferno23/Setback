/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.application.client;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import setback.common.PlayerNumber;

/**
 * This class is the brains behind the SetbackClient using vertx.
 * The GUI views will call the methods on this class to send
 * messages back to the server.
 * @author Mike Burns
 */
public class SetbackVertxClientControllerImpl extends SetbackClientControllerSkeleton
    implements SetbackClientController {

  private EventBus eventBus;

  /**
   * Constructor which takes in the shared vertx instance so we
   * can get the event bus and set up our consumers.
   * @param vertx The clustered vertx instance shared among the clients.
   */
  SetbackVertxClientControllerImpl(Vertx vertx) {
    // TODO: Remove the super class.
    super();
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
    // TODO: Implement me.
    return null;
  }
}

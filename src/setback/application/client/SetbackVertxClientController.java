/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.application.client;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;

/**
 * This class is the brains behind the SetbackClient using vertx.
 * The GUI views will call the methods on this class to send
 * messages back to the server.
 * @author Mike Burns
 */
public class SetbackVertxClientController extends SetbackClientController {

  private EventBus eventBus;

  /**
   * Constructor which takes in the shared vertx instance so we
   * can get the event bus and set up our consumers.
   * @param vertx The clustered vertx instance shared among the clients.
   */
  SetbackVertxClientController(Vertx vertx) {
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

  // TODO: Need methods for each command.

  public void requestPlayerNumber(int playerNumber, Handler<AsyncResult<Boolean>> handler) {
    // TODO: Channel, request, handler
    eventBus.send("REQUEST_PLAYER_CHANNEL", "REQUEST_PLAYER_ONE", sendHandler -> {
      // TODO: Do some stuff
    });
  }
}

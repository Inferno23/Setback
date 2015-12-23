/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.application.client;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;

/**
 * This class functions as the client that a player will run
 * when they play a game of Setback.  This client uses vertx
 * instead of pure java.
 * @author Mike Burns
 */
// TODO: Somebody needs to start this verticle
public class SetbackClientVerticle extends AbstractVerticle {

  public static final int DEFAULT_PORT = 2323;
  public static final String DEFAULT_HOSTNAME = "216.49.151.138";

  // TODO: Might not need to remember this variable
  private SetbackClientController controller;

  @Override
  public void start() {
    controller = new SetbackVertxClientController(vertx);
  }
}

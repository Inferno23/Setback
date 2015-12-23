package setback.application.command;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

/**
 * This class is the message that is sent between the server
 * and the client. The server will parse it and use it to
 * run the game.  It is based on a JsonObject for ease of
 * serialization and deserialization.
 * @author Mike Burns
 */
public class CommandMessageJson extends JsonObject {

  private final String COMMAND = "COMMAND";
  private final String PARAMETERS = "PARAMETERS";

  // Getters/Setters

  /**
   * Getter for the value of the Command enum of this message.
   * @return The Command enum value.
   */
  public Command getCommand() {
    return Command.valueOf(getString(COMMAND));
  }

  CommandMessageJson setCommand(Command command) {
    return (CommandMessageJson) put(COMMAND, command.toString());
  }

  public JsonArray getParameters() {
    return getJsonArray(PARAMETERS);
  }

  CommandMessageJson setParameters(JsonArray parameters) {
    return (CommandMessageJson) put(PARAMETERS, parameters);
  }

  // Static constructors

  /**
   * Static constructor that takes in a Command enum and a
   * list of parameters for the Command.
   * @param command The command enum value.
   * @return The newly constructed CommandMessageJson object.
   */
  public static CommandMessageJson constructCommandMessage(Command command, String... parameters) {
    // TODO: parameters could probably be of type JsonArray instead of String...
    JsonArray jsonArray = new JsonArray();
    for (String parameter : parameters) {
      jsonArray.add(parameter);
    }
    return new CommandMessageJson().setCommand(command).setParameters(jsonArray);
  }
}

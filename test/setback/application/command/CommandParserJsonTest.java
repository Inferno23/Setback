package setback.application.command;

import io.vertx.core.json.JsonArray;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author Mike Burns
 */
public class CommandParserJsonTest {

  @Test
  public void commandTest() {
    final Command command = Command.NO_COMMAND;
    CommandMessageJson json = new CommandMessageJson();
    json.setCommand(command);
    Assert.assertEquals(command, json.getCommand());
  }

  @Test
  public void parametersTest() {
    final JsonArray parameters = new JsonArray().add(true).add(false);
    CommandMessageJson json = new CommandMessageJson();
    json.setParameters(parameters);
    Assert.assertEquals(parameters, json.getParameters());
  }

  @Test
  public void staticConstructorNoParametersTest() {
    final Command command = Command.NO_COMMAND;
    CommandMessageJson json = CommandMessageJson.constructCommandMessage(command);
    Assert.assertEquals(command, json.getCommand());
  }

  @Test
  public void staticConstructorWithParametersTest() {
    final Command command = Command.PLACE_BET;
    final String[] parameters = new String[]{"PASS"};
    CommandMessageJson json = CommandMessageJson.constructCommandMessage(command, parameters);
    Assert.assertEquals(command, json.getCommand());
    JsonArray parametersJson = json.getParameters();
    Assert.assertEquals(parametersJson.size(), parameters.length);
    Assert.assertTrue(parametersJson.getList().containsAll(
        Arrays.asList(parameters)));
  }
}

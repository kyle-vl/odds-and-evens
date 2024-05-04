package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Main.Choice;
import nz.ac.auckland.se281.Main.Difficulty;

/** This class represents the Game is the main entry point. */
public class Game {
  private int gameCount;
  private String name = null;

  public void newGame(Difficulty difficulty, Choice choice, String[] options) {
    // the first element of options[0]; is the name of the player
    gameCount = 0;
    name = options[0];
    MessageCli.WELCOME_PLAYER.printMessage(name);
  }

  public void play() {
    if (name == null) {
      MessageCli.GAME_NOT_STARTED.printMessage();
      return;
    }

    gameCount++;
    MessageCli.START_ROUND.printMessage(String.valueOf(gameCount));
    MessageCli.ASK_INPUT.printMessage();
    String input = Utils.scanner.nextLine();
    int fingers = Integer.parseInt(input);
    while ((fingers < 0) || (fingers > 5)) {
      MessageCli.INVALID_INPUT.printMessage();
      input = Utils.scanner.nextLine();
      fingers = Integer.parseInt(input);
    }

    int halFingers = Utils.getRandomNumberRange(0, 5);

    MessageCli.PRINT_INFO_HAND.printMessage(name, input);
    MessageCli.PRINT_INFO_HAND.printMessage("HAL-9000", String.valueOf(halFingers));
  }

  public void endGame() {}

  public void showStats() {}
}

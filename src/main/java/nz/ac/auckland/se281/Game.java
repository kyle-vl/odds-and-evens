package nz.ac.auckland.se281;

import java.util.List;
import java.util.ArrayList;
import main.java.nz.ac.auckland.se281.DifficultyLevel;
import main.java.nz.ac.auckland.se281.DifficultyLevelFactory;
import nz.ac.auckland.se281.Main.Choice;
import nz.ac.auckland.se281.Main.Difficulty;

/** This class represents the Game is the main entry point. */
public class Game {
  private int gameCount;
  private String name = null;
  private final String opponent = "HAL-9000";
  private List<Integer> fingersHistory = new ArrayList<>();
  private Choice userChoice;
  private Choice opponentChoice;
  private Difficulty currentDifficulty;

  public void newGame(Difficulty difficulty, Choice choice, String[] options) {
    // the first element of options[0]; is the name of the player
    gameCount = 0;
    name = options[0];
    userChoice = choice;

    if (userChoice == Choice.EVEN) {
      opponentChoice = Choice.ODD;
    } else {
      opponentChoice = Choice.EVEN;
    }

    currentDifficulty = difficulty;
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

    DifficultyLevel difficultyLevel = DifficultyLevelFactory.createDifficultyLevel(currentDifficulty, gameCount, fingersHistory, opponentChoice);
    int halFingers = difficultyLevel.getNumber();

    fingersHistory.add(fingers);
    MessageCli.PRINT_INFO_HAND.printMessage(name, input);
    MessageCli.PRINT_INFO_HAND.printMessage(opponent, String.valueOf(halFingers));

    int sum = fingers + halFingers;
    if ((userChoice == Choice.EVEN && Utils.isEven(sum)) || (userChoice == Choice.ODD && Utils.isOdd(sum))) {
      MessageCli.PRINT_OUTCOME_ROUND.printMessage(String.valueOf(sum), String.valueOf(userChoice), name);
      return;
    } else if (userChoice == Choice.EVEN) {
      MessageCli.PRINT_OUTCOME_ROUND.printMessage(String.valueOf(sum), "ODD", opponent);
      return;
    } else {
      MessageCli.PRINT_OUTCOME_ROUND.printMessage(String.valueOf(sum), "EVEN", opponent);
      return;
    }
  }

  public void endGame() {}

  public void showStats() {}
}

package nz.ac.auckland.se281;

import java.util.ArrayList;
import java.util.List;
import main.java.nz.ac.auckland.se281.DifficultyLevel;
import main.java.nz.ac.auckland.se281.DifficultyLevelFactory;
import nz.ac.auckland.se281.Main.Choice;
import nz.ac.auckland.se281.Main.Difficulty;

/** This class represents the Game is the main entry point. */
public class Game {
  private int gameCount;
  private int playerWins;
  private int opponentWins;
  private int playerLosses;
  private int opponentLosses;
  private String name = null;
  private final String opponent = "HAL-9000";
  private List<Integer> fingersHistory = new ArrayList<>();
  private Choice userChoice;
  private Choice opponentChoice;
  private Difficulty currentDifficulty;
  private boolean opponentWinsPrevious;

  public void newGame(Difficulty difficulty, Choice choice, String[] options) {
    // the first element of options[0]; is the name of the player
    gameCount = 0;
    playerWins = 0;
    opponentWins = 0;
    playerLosses = 0;
    opponentLosses = 0;

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

    DifficultyLevel difficultyLevel =
        DifficultyLevelFactory.createDifficultyLevel(
            currentDifficulty, gameCount, fingersHistory, opponentChoice, opponentWinsPrevious);
    int halFingers = difficultyLevel.getNumber();

    fingersHistory.add(fingers);
    MessageCli.PRINT_INFO_HAND.printMessage(name, input);
    MessageCli.PRINT_INFO_HAND.printMessage(opponent, String.valueOf(halFingers));

    int sum = fingers + halFingers;
    Choice sumType;
    if (Utils.isEven(sum)) {
      sumType = Choice.EVEN;
    } else {
      sumType = Choice.ODD;
    }

    if (userChoice == sumType) {
      MessageCli.PRINT_OUTCOME_ROUND.printMessage(
          String.valueOf(sum), String.valueOf(userChoice), name);
      playerWins++;
      opponentLosses++;
      opponentWinsPrevious = false;
    } else {
      MessageCli.PRINT_OUTCOME_ROUND.printMessage(
          String.valueOf(sum), String.valueOf(opponentChoice), opponent);
      opponentWins++;
      playerLosses++;
      opponentWinsPrevious = true;
    }
  }

  public void endGame() {
    if (name == null) {
      MessageCli.GAME_NOT_STARTED.printMessage();
      return;
    }

    showStats();

    if (opponentWins < playerWins) {
      MessageCli.PRINT_END_GAME.printMessage(name);
    } else if (playerWins < opponentWins) {
      MessageCli.PRINT_END_GAME.printMessage(opponent);
    } else {
      MessageCli.PRINT_END_GAME_TIE.printMessage();
    }

    name = null;
  }

  public void showStats() {
    if (name == null) {
      MessageCli.GAME_NOT_STARTED.printMessage();
      return;
    }

    MessageCli.PRINT_PLAYER_WINS.printMessage(
        name, String.valueOf(playerWins), String.valueOf(playerLosses));
    MessageCli.PRINT_PLAYER_WINS.printMessage(
        opponent, String.valueOf(opponentWins), String.valueOf(opponentLosses));
  }
}

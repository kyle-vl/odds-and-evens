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

  /**
   * Creates a new game. The user must run this method in order to use any of the other methods
   * under this class.
   *
   * @param difficulty difficulty level selected by the player
   * @param choice number type (even or odd) selected by the player
   * @param options the first element of options[0]; is the name of the player
   */
  public void newGame(Difficulty difficulty, Choice choice, String[] options) {
    // Set all game values to 0
    gameCount = 0;
    playerWins = 0;
    opponentWins = 0;
    playerLosses = 0;
    opponentLosses = 0;
    name = options[0];
    currentDifficulty = difficulty;

    userChoice = choice;
    if (userChoice == Choice.EVEN) {
      opponentChoice = Choice.ODD;
    } else {
      opponentChoice = Choice.EVEN;
    }

    MessageCli.WELCOME_PLAYER.printMessage(name);
  }

  /** Starts a new round of the current game. */
  public void play() {
    // Return if game hasn't started
    if (name == null) {
      MessageCli.GAME_NOT_STARTED.printMessage();
      return;
    }

    // Increment game count and display round number
    gameCount++;
    MessageCli.START_ROUND.printMessage(String.valueOf(gameCount));

    // Get user input, loop until user gives valid input between 0 and 5 (inclusive)
    MessageCli.ASK_INPUT.printMessage();
    String input = Utils.scanner.nextLine();
    int fingers = Integer.parseInt(input);

    while ((fingers < 0) || (fingers > 5)) {
      MessageCli.INVALID_INPUT.printMessage();
      input = Utils.scanner.nextLine();
      fingers = Integer.parseInt(input);
    }

    // Using the difficulty level to determine AI's fingers
    DifficultyLevel difficultyLevel =
        DifficultyLevelFactory.createDifficultyLevel(
            currentDifficulty, gameCount, fingersHistory, opponentChoice, opponentWinsPrevious);
    int halFingers = difficultyLevel.getNumber();

    // Print AI and user inputs, add user input to history
    fingersHistory.add(fingers);
    MessageCli.PRINT_INFO_HAND.printMessage(name, input);
    MessageCli.PRINT_INFO_HAND.printMessage(opponent, String.valueOf(halFingers));

    // Determine sum and print winner and add to wins and losses of each player
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

  /** Ends the current game, displays stats and declares the winner. */
  public void endGame() {
    // Return if game hasn't started
    if (name == null) {
      MessageCli.GAME_NOT_STARTED.printMessage();
      return;
    }

    showStats();

    // Print winner
    if (opponentWins < playerWins) {
      MessageCli.PRINT_END_GAME.printMessage(name);
    } else if (playerWins < opponentWins) {
      MessageCli.PRINT_END_GAME.printMessage(opponent);
    } else {
      MessageCli.PRINT_END_GAME_TIE.printMessage();
    }

    /* Set name to null so user cannot use play, showStats,
    and endGame methods without starting a new game. */
    name = null;
  }

  /** Displays the current wins and losses of each player. */
  public void showStats() {
    // Return if game hasn't started
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

package main.java.nz.ac.auckland.se281;

import java.util.List;
import nz.ac.auckland.se281.Main.Choice;
import nz.ac.auckland.se281.Main.Difficulty;

public class DifficultyLevelFactory {

  /**
   * Factory which creates easy, medium, and hard difficulty levels
   * Takes in parameters required to generate numbers for higher
   * difficulty levels.
   * 
   * @param difficulty difficulty level selected by the player
   * @param gameCount number of rounds played in the current game
   * @param fingersHistory all previous numbers selected by the player
   * @param opponentChoice the number type (even or odd) of the AI
   * @param opponentWins records whether the AI won or lost previous round
   * @return creates a new instance of the corresponding difficulty
   */
  public static DifficultyLevel createDifficultyLevel(
      Difficulty difficulty,
      int gameCount,
      List<Integer> fingersHistory,
      Choice opponentChoice,
      boolean opponentWins) {

    switch (difficulty) {
      case EASY:
        return new EasyDifficulty();
      case MEDIUM:
        return new MediumDifficulty(gameCount, fingersHistory, opponentChoice);
      case HARD:
        return new HardDifficulty(gameCount, fingersHistory, opponentChoice, opponentWins);
      default:
        throw new IllegalArgumentException();
    }
  }
}

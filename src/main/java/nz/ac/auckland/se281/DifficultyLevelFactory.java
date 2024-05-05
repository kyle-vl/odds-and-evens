package main.java.nz.ac.auckland.se281;

import java.util.List;
import nz.ac.auckland.se281.Main.Choice;
import nz.ac.auckland.se281.Main.Difficulty;

public class DifficultyLevelFactory {

  public static DifficultyLevel createDifficultyLevel(
      Difficulty difficulty,
      int gameCount,
      List<Integer> fingersHistory,
      Choice opponentChoice,
      boolean opponentWins) {

    // Factory creates a new instance of corresponding difficulty
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

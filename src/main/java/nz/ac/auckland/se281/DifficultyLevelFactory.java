package main.java.nz.ac.auckland.se281;

import java.util.List;
import nz.ac.auckland.se281.Main.Choice;
import nz.ac.auckland.se281.Main.Difficulty;

public class DifficultyLevelFactory {

  public static DifficultyLevel createDifficultyLevel(
      Difficulty difficulty, int gameCount, List<Integer> fingersHistory, Choice opponentChoice, boolean opponentWins) {

    switch (difficulty) {
      case EASY:
        return new Easy();
      case MEDIUM:
        return new Medium(gameCount, fingersHistory, opponentChoice);
      case HARD:
        return new Hard(gameCount, fingersHistory, opponentChoice, opponentWins);
      default:
        throw new IllegalArgumentException();
    }
  }
}

package main.java.nz.ac.auckland.se281;

import nz.ac.auckland.se281.Main.Difficulty;

public class DifficultyLevelFactory {
  public static DifficultyLevel createDifficultyLevel(Difficulty difficulty) {
    switch (difficulty) {
      case EASY:
        return new Easy();
      default:
        throw new IllegalArgumentException();
    }
  }
}

package main.java.nz.ac.auckland.se281;

import java.util.List;
import nz.ac.auckland.se281.Main.Choice;

/** Medium difficulty uses random strategy for first 3 rounds, then switches to top strategy. */
public class MediumDifficulty implements DifficultyLevel {
  private final Strategy strategy;

  /** Creates a strategy instance according to medium difficulty (explained under class JavaDoc) */
  public MediumDifficulty(int gameCount, List<Integer> fingersHistory, Choice opponentChoice) {
    if (gameCount <= 3) {
      this.strategy = new RandomStrategy();
    } else {
      this.strategy = new TopStrategy(fingersHistory, opponentChoice);
    }
  }

  @Override
  public int getNumber() {
    return strategy.generateNumber();
  }
}

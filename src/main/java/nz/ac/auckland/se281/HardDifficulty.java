package main.java.nz.ac.auckland.se281;

import java.util.List;
import nz.ac.auckland.se281.Main.Choice;

/**
 * Hard difficulty changes strategies if AI loses previous round, but always uses random strategy
 * for first three rounds.
 */
public class HardDifficulty implements DifficultyLevel {

  private Strategy strategy;

  /** Creates a strategy instance according to hard difficulty (explained under class JavaDoc). */
  public HardDifficulty(
      int gameCount, List<Integer> fingersHistory, Choice opponentChoice, boolean opponentWins) {
    if (gameCount <= 3
        || (opponentWins && strategy instanceof RandomStrategy)
        || (!opponentWins && strategy instanceof TopStrategy)) {
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

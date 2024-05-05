package main.java.nz.ac.auckland.se281;

import java.util.List;
import nz.ac.auckland.se281.Main.Choice;

/**
 * Hard difficulty changes strategies if AI loses previous round, but always uses random strategy
 * for first three rounds.
 */
public class HardDifficulty implements DifficultyLevel {

  /** Enum used to check for the strategy played by the AI in the previous round. */
  public enum StrategyType {
    RANDOM,
    TOP;
  }

  private final Strategy strategy;
  private StrategyType strategyType;

  /** Creates a strategy instance according to hard difficulty (explained under class JavaDoc) */
  public HardDifficulty(
      int gameCount, List<Integer> fingersHistory, Choice opponentChoice, boolean opponentWins) {
    if ((gameCount <= 3)
        || (opponentWins == false) && (strategyType == StrategyType.TOP)
        || (opponentWins == true) && (strategyType == StrategyType.RANDOM)) {
      this.strategy = new RandomStrategy();
      strategyType = StrategyType.RANDOM;
    } else {
      this.strategy = new TopStrategy(fingersHistory, opponentChoice);
      strategyType = StrategyType.TOP;
    }
  }

  @Override
  public int getNumber() {
    return strategy.generateNumber();
  }
}

package main.java.nz.ac.auckland.se281;

import java.util.List;
import nz.ac.auckland.se281.Main.Choice;

public class HardDifficulty implements DifficultyLevel {

  public enum StrategyType {
    RANDOM,
    TOP;
  }

  private final Strategy strategy;
  private StrategyType strategyType;

  /* Hard difficulty changes strategies if AI loses previous round, but uses random strategy for first three rounds */
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

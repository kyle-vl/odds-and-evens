package main.java.nz.ac.auckland.se281;

import java.util.List;
import nz.ac.auckland.se281.Main.Choice;

public class Hard implements DifficultyLevel {
  private final Strategy strategy;

  public enum StrategyType {
    RANDOM,
    TOP;
  }

  StrategyType strategyType;

  public Hard(
      int gameCount, List<Integer> fingersHistory, Choice opponentChoice, boolean opponentWins) {
    if ((gameCount <= 3)
        || (opponentWins == false) && (strategyType == StrategyType.TOP)
        || (opponentWins == true) && (strategyType == StrategyType.RANDOM)) {
      this.strategy = new RandomStrategy();
      strategyType = StrategyType.RANDOM;
    } else {
      this.strategy = new TopStrategy(fingersHistory, opponentChoice);
    }
  }

  @Override
  public int getNumber() {
    return strategy.generateNumber();
  }
}

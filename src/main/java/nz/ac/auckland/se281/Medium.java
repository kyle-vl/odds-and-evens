package main.java.nz.ac.auckland.se281;

import java.util.List;
import nz.ac.auckland.se281.Main.Choice;

public class Medium implements DifficultyLevel {
  private final Strategy strategy;

  public Medium(int gameCount, List<Integer> fingersHistory, Choice opponentChoice) {
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

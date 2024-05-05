package main.java.nz.ac.auckland.se281;

public class EasyDifficulty implements DifficultyLevel {
  private final Strategy strategy;

  public EasyDifficulty() {
    this.strategy = new RandomStrategy();
  }

  @Override
  public int getNumber() {
    return strategy.generateNumber();
  }
}

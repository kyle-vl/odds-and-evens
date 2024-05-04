package main.java.nz.ac.auckland.se281;

public class Easy implements DifficultyLevel {
  private final Strategy strategy;

  public Easy() {
    this.strategy = new RandomStrategy();
  }

  @Override
  public int getNumber() {
    return strategy.generateNumber();
  }
}

package main.java.nz.ac.auckland.se281;

/** Easy difficulty always uses random strategy. */
public class EasyDifficulty implements DifficultyLevel {
  private Strategy strategy;

  public EasyDifficulty() {
    setStrategy(new RandomStrategy());
  }

  @Override
  public void setStrategy(Strategy strategy) {
    this.strategy = strategy;
  }

  @Override
  public int getNumber() {
    return strategy.generateNumber();
  }
}

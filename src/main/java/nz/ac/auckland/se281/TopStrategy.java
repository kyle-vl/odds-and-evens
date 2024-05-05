package main.java.nz.ac.auckland.se281;

import java.util.List;
import nz.ac.auckland.se281.Main.Choice;
import nz.ac.auckland.se281.Utils;

public class TopStrategy implements Strategy {
  private List<Integer> fingersHistory;
  private Choice opponentChoice;

  public TopStrategy(List<Integer> fingersHistory, Choice opponentChoice) {
    this.fingersHistory = fingersHistory;
    this.opponentChoice = opponentChoice;
  }

  @Override
  public int generateNumber() {

    int evenCount = 0;
    int oddCount = 0;

    for (int fingers : fingersHistory) {
      if (Utils.isEven(fingers)) {
        evenCount++;
      } else {
        oddCount++;
      }
    }

    int number;
    Choice userPreferredType;

    if (oddCount < evenCount) {
      userPreferredType = Choice.EVEN;
    } else if (evenCount < oddCount) {
      userPreferredType = Choice.ODD;
    } else {
      number = Utils.getRandomNumberRange(0, 5);
      return number;
    }

    if (userPreferredType != opponentChoice) {
      number = Utils.getRandomOddNumber();
    } else {
      number = Utils.getRandomEvenNumber();
    }

    return number;
  }
}

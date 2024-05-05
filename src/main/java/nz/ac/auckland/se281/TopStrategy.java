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

    if (((evenCount < oddCount) && (opponentChoice == Choice.EVEN))
        || ((oddCount < evenCount) && (opponentChoice == Choice.ODD))) {
      int number = Utils.getRandomOddNumber();
      return number;
    } else if (((evenCount < oddCount) && (opponentChoice == Choice.ODD))
        || ((oddCount < evenCount) && (opponentChoice == Choice.EVEN))) {
      int number = Utils.getRandomEvenNumber();
      return number;
    } else {
      int number = Utils.getRandomNumberRange(0, 5);
      return number;
    }
  }
}

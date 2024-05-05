package main.java.nz.ac.auckland.se281;

import java.util.List;
import nz.ac.auckland.se281.Main.Choice;
import nz.ac.auckland.se281.Utils;

/**
 * Top strategy generates a number for the AI which is
 * most likely to win, depending on the previous numbers
 * played by the player.
 */
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

    // Count amount of times user plays even and odd
    for (int fingers : fingersHistory) {
      if (Utils.isEven(fingers)) {
        evenCount++;
      } else {
        oddCount++;
      }
    }

    int number;
    Choice userPreferredType;

    /* User preferred type is the type of number player has used the most.
    If player has played evens and odds an equal number of times, 
    AI will generate a random number. */
    if (oddCount < evenCount) {
      userPreferredType = Choice.EVEN;
    } else if (evenCount < oddCount) {
      userPreferredType = Choice.ODD;
    } else {
      number = Utils.getRandomNumberRange(0, 5);
      return number;
    }

    /* AI plays number type most likely to get the win, assuming 
    player will use their preferred number type. */
    if (userPreferredType != opponentChoice) {
      number = Utils.getRandomOddNumber();
    } else {
      number = Utils.getRandomEvenNumber();
    }

    return number;
  }
}

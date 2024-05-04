package main.java.nz.ac.auckland.se281;

import nz.ac.auckland.se281.Utils;

public class RandomStrategy implements Strategy {
    
    @Override
    public int generateNumber() {
        int number = Utils.getRandomNumberRange(0, 5);
        return number;
    }
}
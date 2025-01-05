package org.example.helpers;

import java.util.Random;

public class Randomizer {
    public static int randomNumber(int maxNumber) {
        if (maxNumber < 0) {
            throw new IllegalArgumentException("maxNumber - должен быть положительным числом.");
        }
        Random random = new Random();
        return random.nextInt(maxNumber + 1);
    }
}

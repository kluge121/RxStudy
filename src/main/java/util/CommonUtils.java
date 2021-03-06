package util;

import java.util.Random;

public class CommonUtils {

    static long startTime;
    static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static void exampleStart() {
        startTime = System.currentTimeMillis();
    }

    public static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void doSomething() {
        try {
            Thread.sleep(new Random().nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static String numberToAlphabet(long x) {
        return Character.toString(ALPHABET.charAt((int) x % ALPHABET.length()));


    }


}

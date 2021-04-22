package dev.fneira.util;

import java.util.Random;

public class StringUtils {

    private StringUtils() {

    }

    public static String randomCharacter() {
        return String.valueOf((char) (new Random().nextInt(26) + 'a'));
    }

}

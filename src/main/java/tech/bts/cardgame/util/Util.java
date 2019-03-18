package tech.bts.cardgame.util;

import java.util.Collection;

public class Util {

    /** Returns a string with the texts combined with the separator in between each of them */
    public static String join(Collection<String> texts, String separator) {

        String result = "";

        for (String text : texts) {

            if (!result.isEmpty()) {
                result += separator;
            }

            result += text;
        }

        return result;
    }
}

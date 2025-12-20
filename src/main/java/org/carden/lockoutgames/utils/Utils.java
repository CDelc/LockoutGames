package org.carden.lockoutgames.utils;

import org.carden.lockoutgames.LockoutGames;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Utils {

    public static String camelCaseEnumString(String s) {
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) != '_') {
                builder.append(Character.toLowerCase(s.charAt(i)));
            } else if(i + 1 == s.length()) {
                break;
            } else {
                i++;
                builder.append(Character.toUpperCase(s.charAt(i)));
            }
        }
        return builder.toString();
    }

    public static String readableEnumString(String s) {
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) != '_') {
                builder.append(Character.toLowerCase(s.charAt(i)));
            } else if(i + 1 == s.length()) {
                break;
            } else {
                builder.append(' ');
                i++;
                builder.append(Character.toUpperCase(s.charAt(i)));
            }
        }
        return builder.toString();
    }

    public static <T> List<T> selectNRandomValuesFromList(List<T> list, int n, Random rng) {
        if(n > list.size() || n < 0) throw new IllegalArgumentException("Logically invalid call to method");
        List<T> tmp = new ArrayList<>(list);
        Collections.shuffle(tmp, rng);
        return tmp.subList(0, n);
    }

    public static boolean weightedRandomBoolean(int truePercentChance) {
        return LockoutGames.getRng().nextInt() % 100 < truePercentChance;
    }

    public static String buildStringList(List<String> strings, String andor) {
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < strings.size(); i++) {
            if(i == strings.size() - 1) {
                builder.append(andor).append(" ");
            }
            builder.append(strings.get(i));
            builder.append(", ");
        }
        return builder.toString();
    }

}

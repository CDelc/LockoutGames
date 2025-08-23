package org.carden.lockoutgames.utils;

public class Utils {

    public static String camelCaseEnumString(String s) {
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < s.toCharArray().length; i++) {
            if(s.charAt(i) != '_') {
                builder.append(Character.toLowerCase(s.charAt(i)));
            } else if(i + 1 == s.toCharArray().length) {
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
        for(int i = 0; i < s.toCharArray().length; i++) {
            if(s.charAt(i) != '_') {
                builder.append(Character.toLowerCase(s.charAt(i)));
            } else if(i + 1 == s.toCharArray().length) {
                break;
            } else {
                builder.append(' ');
                i++;
                builder.append(Character.toUpperCase(s.charAt(i)));
            }
        }
        return builder.toString();
    }

}

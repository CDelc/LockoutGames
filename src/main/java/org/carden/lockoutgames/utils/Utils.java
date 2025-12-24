package org.carden.lockoutgames.utils;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.event.player.PlayerEvent;
import org.carden.lockoutgames.LockoutGames;
import org.jetbrains.annotations.Nullable;

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

    /**
     * Get the player on a PlayerEvent. Returns null if it is not a PlayerEvent.
     * @param e event
     * @return player or null
     */
    public static @Nullable Player playerEventPlayer(Event e) {
        if (e instanceof PlayerEvent) {
            return ((PlayerEvent) e).getPlayer();
        }
        else {
            return null;
        }
    }

    /**
     * Get the player on an EntityEvent. Returns null is it is not an EntityEvent or the entity is not a player.
     * @param e event
     * @return player or null
     */
    public static @Nullable Player entityEventPlayer(Event e) {
        if (e instanceof EntityEvent && ((EntityEvent) e).getEntityType() == EntityType.PLAYER) {
            return (Player)((EntityEvent) e).getEntity();
        }
        else {
            return null;
        }
    }
}

package org.carden.lockoutgames.game;

import org.bukkit.ChatColor;
import org.carden.lockoutgames.goal.Goal;
import org.carden.lockoutgames.goal.GoalType;

import java.util.List;
import java.util.Map;

public class Debug {

    private static List<Goal> goalInstances;
    private static boolean debugActive = false;

    private static final Map<String, String> commandDescription = Map.of(
            "start", "Start a debug session",
            "end", "Close the debug session"
    );

    public static void startDebug() {
        debugActive = true;
    }

    public static void endDebug() {
        debugActive = false;
    }

    public static List<String> getValidArgs() {
        return commandDescription.keySet().stream().toList();
    }

    public static String getHelpString() {
        StringBuilder helpStringBuilder = new StringBuilder();

        for(String arg : commandDescription.keySet()) {
            helpStringBuilder.append(ChatColor.RED)
                    .append(arg)
                    .append(ChatColor.WHITE)
                    .append(": ")
                    .append(commandDescription.get(arg))
                    .append("\n");
        }
        return helpStringBuilder.toString();
    }

    public static boolean isActive() {
        return debugActive;
    }

    public static void checkGoals() {

    }

    public static void testGoal(GoalType goalType) {

    }


}

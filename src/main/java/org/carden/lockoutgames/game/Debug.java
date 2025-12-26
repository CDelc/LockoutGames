package org.carden.lockoutgames.game;

import org.bukkit.ChatColor;
import org.carden.lockoutgames.LockoutGames;
import org.carden.lockoutgames.game.setting.Setting;
import org.carden.lockoutgames.goal.Goal;
import org.carden.lockoutgames.goal.factory.GoalFactory;
import org.carden.lockoutgames.info.SettingIDS;

import java.util.List;
import java.util.Map;

public class Debug {

    private static List<Goal> goalInstances;
    private static boolean debugActive = false;

    private static final Map<String, String> commandDescription = Map.of(
            "start", "Start a debug session",
            "end", "Close the debug session",
            "scanWorld", "Run the scan for biomes and structures and summarize the results, flagging any biomes/structures that are not being scanned for"
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

    public static void testGoal(GoalFactory goalFactory) {

    }

    public static void testAllGoals() {

    }

    public static void checkLogicScan() {
        LockoutGames.getGameWorld().updateLogic(Setting.getSettingValue(SettingIDS.LOGIC_RADIUS), true);
    }

    public static void validateItemList() {

    }

    public static void validateMobList() {

    }


}

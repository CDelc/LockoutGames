package org.carden.lockoutgames.game;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.carden.lockoutgames.LockoutGames;
import org.carden.lockoutgames.game.setting.Setting;
import org.carden.lockoutgames.goal.Goal;
import org.carden.lockoutgames.goal.factory.GoalFactory;
import org.carden.lockoutgames.info.SettingIDS;
import org.carden.lockoutgames.info.WorldRequirements;

import java.util.List;
import java.util.Map;

public class Debug {

    private static List<Goal> goalInstances;
    private static boolean debugActive = false;

    private static final Map<String, String> commandDescription = Map.of(
            "start", "Start a debug session",
            "end", "Close the debug session",
            "scanWorld", "Run the scan for biomes and structures and summarize the results, flagging any biomes/structures that are not being scanned for",
            "validateLogicList", "Check for incongruencies between the Material or Mob enums and WorldRequirements logic entries"
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

    public static void validateLogicList(CommandSender sender) {
        sender.sendMessage("-----MATERIAL-----");
        for(Material m : Material.values()) {
            try {
                Enum.valueOf(WorldRequirements.Element.class, m.name());
            } catch(IllegalArgumentException e) {
                sender.sendMessage(ChatColor.RED + m.name() + ChatColor.DARK_RED + " missing");
            }
        }
        sender.sendMessage("-----ENTITYTYPE-----");
        for(EntityType eType : EntityType.values()) {
            try {
                Enum.valueOf(WorldRequirements.Element.class, eType.name());
            } catch(IllegalArgumentException e) {
                sender.sendMessage(ChatColor.RED + eType.name() + ChatColor.DARK_RED + " missing");
            }
        }
        sender.sendMessage("-----INCORRECT LOGIC ENTRIES-----");
        for(WorldRequirements.Element element : WorldRequirements.Element.values()) {
            try {
                Enum.valueOf(Material.class, element.name());
            } catch(IllegalArgumentException e) {
                try {
                    Enum.valueOf(EntityType.class, element.name());
                } catch(IllegalArgumentException ex) {
                    sender.sendMessage(ChatColor.LIGHT_PURPLE + element.name() + ChatColor.DARK_PURPLE + " does not exist in minecraft version");
                }
            }
        }
    }

}

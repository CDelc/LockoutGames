package org.carden.lockoutgames.game;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.event.Event;
import org.carden.lockoutgames.LockoutGames;
import org.carden.lockoutgames.events.listener.GoalCheckListener;
import org.carden.lockoutgames.game.setting.Setting;
import org.carden.lockoutgames.game.setting.SettingsImage;
import org.carden.lockoutgames.goal.GoalDifficulty;
import org.carden.lockoutgames.goal.GoalFactories;
import org.carden.lockoutgames.goal.IGoal;
import org.carden.lockoutgames.goal.factory.GoalFactory;
import org.carden.lockoutgames.info.SettingIDS;
import org.carden.lockoutgames.info.WorldRequirements;
import org.carden.lockoutgames.utils.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Debug {

    private static List<IGoal> goalInstances = new ArrayList<>();
    private static boolean debugActive = false;

    private static final Map<String, String> commandDescription = Map.of(
            "start", "Start a debug session",
            "end", "Close the debug session",
            "scanWorld", "Run the scan for biomes and structures and summarize the results, flagging any biomes/structures that are not being scanned for",
            "validateLogicList", "Check for incongruencies between the Material or Mob enums and WorldRequirements logic entries",
            "testGoal", "Generate a specific goal and test it",
            "testAllGoals", "Generate one of every goal at once and test them freely"
    );

    public static boolean startDebug() {
        if(LockoutGames.getGame().isPresent()) return false;
        debugActive = true;
        return true;
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

    public static void checkGoals(Event e) {
        for(IGoal goal : goalInstances) {
            goal.checkEvent(e);
        }
    }

    public static void clearGoals() {
        goalInstances.clear();
        GoalCheckListener.getInstance().clearEventListeners();
    }

    public static boolean testGoal(GoalFactory goalFactory) {
        return false;
    }

    public static boolean testGoal(GoalFactory goalFactory, GoalDifficulty difficulty) {
        return false;
    }

    public static boolean testAllGoals() {
        if(!debugActive) return false;
        clearGoals();
        Bukkit.getServer().broadcastMessage(ChatColor.RED + "" + ChatColor.BOLD + "BEGIN GOAL TEST");
        SettingsImage settingsImage = Setting.saveSettings();
        for(GoalFactories goalFactoryEnum : GoalFactories.values()) {
            GoalFactory goalFactory = goalFactoryEnum.getFactory();
            IGoal goal;
            try {
                goal = goalFactory.makeGoal(settingsImage, Collections.emptyList());
                Bukkit.getServer().broadcastMessage(ChatColor.BLUE + goalFactoryEnum.name() + ChatColor.WHITE + " | " + goalDebugString(goal));
                goalInstances.add(goal);
                GoalCheckListener.getInstance().enableEventListener(goal.getCheckEvents());
            } catch(IllegalStateException e) {
                Bukkit.getServer().broadcastMessage(ChatColor.BLUE + Utils.camelCaseEnumString(goalFactoryEnum.name()) + ChatColor.WHITE + " | " + ChatColor.RED + "Failed to generate");
            }
        }
        return true;
    }

    private static String goalDebugString(IGoal goal) {
        return ChatColor.GREEN + Utils.readableEnumString(goal.getGoalDifficulty().name()) + ChatColor.WHITE + " | " + ChatColor.AQUA + goal.getDescription();
    }

    public static boolean testAllGoalsAllDifficulty(CommandSender sender) {
        return false;
    }

    public static void checkLogicScan() {
        GameWorld.getGameWorld().updateLogic(Setting.getSettingValue(SettingIDS.LOGIC_RADIUS), true);
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

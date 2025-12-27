package org.carden.lockoutgames.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.carden.lockoutgames.LockoutGames;
import org.carden.lockoutgames.game.Debug;
import org.carden.lockoutgames.game.setting.Setting;
import org.carden.lockoutgames.goal.GoalDifficulty;
import org.carden.lockoutgames.goal.GoalFactories;
import org.carden.lockoutgames.info.SettingIDS;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Gadmin implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (args.length > 0 && args[0].equalsIgnoreCase("start")) gadminStart(commandSender, args);
        else if (args.length > 0 && args[0].equalsIgnoreCase("settings")) gadminSettings(commandSender, args);
        else if (args.length > 0 && args[0].equalsIgnoreCase("debug")) gadminDebug(commandSender, args);
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if(args.length == 1) return Arrays.asList("start", "settings", "debug");
        else if(args.length >= 2 && args[0].equalsIgnoreCase("settings")) return settingsTabComplete(sender, args);
        else if(args.length >= 2 && args[0].equalsIgnoreCase("debug")) return debugTabComplete(sender, args);
        else return List.of();
    }

    private void gadminStart(@NotNull CommandSender commandSender, @NotNull String[] args) {
        LockoutGames.startGame();
    }

    private void gadminSettings(@NotNull CommandSender commandSender, @NotNull String[] args) {
        //gadmin settings {valid_setting} {some_arg}
        if (args.length > 2 && Setting.isValidSetting(args[1])) {
            String settingArg = args[1];
            if (!Setting.setSetting(settingArg, args[2])) {
                Setting<?> setting = Setting.getSettingObjectCopy(settingArg);
                commandSender.sendMessage(ChatColor.RED + "'" + args[2] + "' is not a valid value for this setting.");
                commandSender.sendMessage(ChatColor.BLUE + "Valid values for: " + ChatColor.GREEN + " " + args[1]);
                commandSender.sendMessage(ChatColor.YELLOW + setting.getSettingValuesString());
            } else {
                Setting<?> setting = Setting.getSettingObjectCopy(settingArg);
                LockoutGames.broadcastMessage(ChatColor.AQUA + setting.getName() + ChatColor.WHITE + " set to " + ChatColor.YELLOW + setting.getCurrentValue());
            }
        }
        //gadmin settings {valid_setting}
        else if (args.length == 2 && Setting.isValidSetting(args[1])) {
            String settingArg = args[1];
            Setting<?> setting = Setting.getSettingObjectCopy(settingArg);
            commandSender.sendMessage(ChatColor.BLUE + "Valid values for: " + ChatColor.GREEN + " " + args[1]);
            commandSender.sendMessage(ChatColor.YELLOW + setting.getSettingValuesString());
        }
        //gadmin settings help
        else if (args.length > 1 && args[1].equalsIgnoreCase("help")) {
            commandSender.sendMessage(Setting.settingsHelpString());
        }
        //gadmin settings list
        else if (args.length > 1 && args[1].equalsIgnoreCase("list")) {
            commandSender.sendMessage(Setting.settingsString());
        }
        //gadmin settings announce
        else if (args.length > 1 && args[1].equalsIgnoreCase("announce")) {
            Bukkit.broadcastMessage(Setting.settingsString());
        }
        //gadmin settings {invalid_argument}
        else if (args.length == 2) {
            commandSender.sendMessage(ChatColor.RED + "'" + args[1] + "' is not a valid setting.");
            commandSender.sendMessage(ChatColor.GREEN + String.join(", ", SettingIDS.COMMAND_MAPPINGS.keySet().stream().toList()));
        }
        //gadmin settings
        else if (args.length == 1) {
            commandSender.sendMessage(Setting.settingsString());
        } else {
            commandSender.sendMessage(Setting.settingsString());
        }
    }

    private List<String> settingsTabComplete(@NotNull CommandSender commandSender, @NotNull String[] args) {
        if(args.length == 2) return SettingIDS.COMMAND_MAPPINGS.keySet().stream().toList();
        else if(args.length == 3 && Setting.isValidSetting(args[1])) {
            return Setting.getSettingObjectCopy(args[1]).getValidArgs();
        }
        else return List.of();
    }

    private void gadminDebug(@NotNull CommandSender commandSender, @NotNull String[] args) {
        if(args.length <= 1) {
            commandSender.sendMessage(Debug.getHelpString());
        }
        else if(args[1].equalsIgnoreCase("scanworld")) Debug.checkLogicScan();
        else if(args[1].equalsIgnoreCase("validateLogicList")) Debug.validateLogicList(commandSender);
        else if(args[1].equalsIgnoreCase("start")) {
            if (Debug.startDebug()) {
                LockoutGames.broadcastMessage(ChatColor.RED + "" + ChatColor.BOLD + "DEBUG SESSION STARTED");
                commandSender.sendMessage(ChatColor.LIGHT_PURPLE + "Be sure to run /gadmin debug scanWorld to update world logic before testing goals");
            } else {
                commandSender.sendMessage(ChatColor.RED + "Failed to start debug session, make sure there is not a game running");
            }
        } else if(args[1].equalsIgnoreCase("end")) Debug.endDebug();
        else if(args[1].equalsIgnoreCase("testallgoals")) {
            if(args.length == 2 && !Debug.testAllGoals()) {
                commandSender.sendMessage(ChatColor.RED + "Debug session not active");
            } else if(args.length > 2) {
                try {
                    if(!Debug.testAllGoals(Enum.valueOf(GoalDifficulty.class, args[2].toUpperCase()))) {
                        commandSender.sendMessage(ChatColor.RED + "Debug session not active");
                    }
                } catch(IllegalArgumentException e) {
                    commandSender.sendMessage(ChatColor.RED + args[2] + " is not a valid difficulty");
                }
            }
        }
        else if(args[1].equalsIgnoreCase("testgoal")) {
            if(args.length == 2) {
                commandSender.sendMessage(ChatColor.RED + "Usage: /gadmin debug testgoal {goal_name} [difficulty]");
            } else if(args.length == 3) {
                try {
                    if(!Debug.testGoal(Enum.valueOf(GoalFactories.class, args[2].toUpperCase()))) commandSender.sendMessage(ChatColor.RED + "Debug session not active");;
                } catch(IllegalArgumentException e) {
                    commandSender.sendMessage(ChatColor.RED + args[2] + " is not a valid goal name");
                }
            } else if(args.length == 4) {
                GoalFactories goalFactory;
                GoalDifficulty difficulty;
                try{
                    goalFactory = Enum.valueOf(GoalFactories.class, args[2].toUpperCase());
                } catch(IllegalArgumentException e) {
                    commandSender.sendMessage(ChatColor.RED + args[2] + " is not a goal name");
                    return;
                }
                try{
                    difficulty = Enum.valueOf(GoalDifficulty.class, args[3].toUpperCase());
                } catch(IllegalArgumentException e) {
                    commandSender.sendMessage(ChatColor.RED + args[2] + " is not a valid difficulty");
                    return;
                }
                if(!Debug.testGoal(goalFactory, difficulty)) commandSender.sendMessage(ChatColor.RED + "Debug session not active");;
            }
        }
    }

    private List<String> debugTabComplete(@NotNull CommandSender commandSender, @NotNull String[] args) {
        if(args.length == 2) {
            return Debug.getValidArgs();
        } else if (args.length == 3) {
            if(args[1].equalsIgnoreCase("testallgoals")) return Arrays.stream(GoalDifficulty.values()).map(difficulty -> difficulty.name().toLowerCase()).toList();
            if(args[1].equalsIgnoreCase("testGoal")) return Arrays.stream(GoalFactories.values()).map(Enum::name).toList();
        }
        else if (args.length == 4) {
            if(args[1].equalsIgnoreCase("testGoal")) return Arrays.stream(GoalDifficulty.values()).map(difficulty -> difficulty.name().toLowerCase()).toList();
        }
        return new ArrayList<String>();
    }
}

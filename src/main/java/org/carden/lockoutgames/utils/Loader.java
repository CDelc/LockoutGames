package org.carden.lockoutgames.utils;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ScanResult;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;
import org.bukkit.event.Listener;
import org.carden.lockoutgames.LockoutGames;
import org.carden.lockoutgames.game.setting.Setting;
import org.carden.lockoutgames.info.OtherConstants;

import java.util.Objects;
import java.util.concurrent.locks.Lock;

import static org.bukkit.Bukkit.getServer;

public class Loader {

    private static boolean settingsLoaded = false;
    private static boolean goalsLoaded = false;

    public static void loadAll(LockoutGames plugin) {
        loadSettingsAPI();
        loadGoalsApi();
        loadEventListeners(plugin);
        loadCommands(plugin);
    }

    public static void loadSettingsAPI() {
        ClassGraph scanner = new ClassGraph();
        try (ScanResult result = scanner.enableClassInfo().acceptPackages(Setting.class.getPackageName()).scan()) {
            result.getAllClasses().forEach(classInfo -> {
                try {
                    Class.forName(classInfo.getName(), true, classInfo.getClass().getClassLoader());
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        settingsLoaded = true;
    }

    public static void loadGoalsApi() {
        goalsLoaded = true;
    }

    public static void loadEventListeners(LockoutGames plugin) {
        if(!(settingsLoaded && goalsLoaded)) throw new RuntimeException("Settings and Goals not loaded before event listeners");
        for(Listener listener : OtherConstants.EVENT_LISTENERS) {
            getServer().getPluginManager().registerEvents(listener, plugin);
        }
    }

    public static void loadCommands(LockoutGames plugin) {
        if(!(settingsLoaded && goalsLoaded)) throw new RuntimeException("Settings and Goals not loaded before commands");
        for(String command : OtherConstants.COMMAND_EXECUTOR_MAP.keySet()) {
            try {
                Objects.requireNonNull(plugin.getCommand(command)).setExecutor((CommandExecutor) OtherConstants.COMMAND_EXECUTOR_MAP.get(command));
                Objects.requireNonNull(plugin.getCommand(command)).setTabCompleter((TabCompleter) OtherConstants.COMMAND_EXECUTOR_MAP.get(command));
            } catch(NullPointerException e) {
                plugin.getLogger().severe("MISSING COMMAND " + command);
            }
        }
    }
}

package org.carden.lockoutgames.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.carden.lockoutgames.LockoutGames;
import org.jetbrains.annotations.NotNull;

public class Startgame implements CommandExecutor {

    LockoutGames plugin;

    public Startgame(LockoutGames plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        plugin.getGameSettings().start();
        return true;
    }
}

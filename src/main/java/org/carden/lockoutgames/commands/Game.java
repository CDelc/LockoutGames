package org.carden.lockoutgames.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.carden.lockoutgames.LockoutGames;
import org.carden.lockoutgames.game.player.GamePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

public class Game implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        GamePlayer player = LockoutGames.getPluginInstance().getPlayerManager().getGamePlayerObject(((Player) commandSender).getUniqueId());
        if(args.length > 0 && (args[0].equals("spectate") || args[0].equals("forfeit"))) {
            player.setSpectator(true);
            return true;
        }
        else if(args.length > 0 && (args[0].equals("join") || args[0].equals("play"))) {
            if(!player.setSpectator(false)) {
                commandSender.sendMessage(ChatColor.RED + "You may not join a game that is already running");
            }
            return true;
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if(args.length == 1) {
            return Arrays.asList("spectate", "join");
        }
        return List.of();
    }
}

package org.carden.lockoutgames.utils;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.carden.lockoutgames.LockoutGames;

import java.util.ArrayList;
import java.util.stream.Stream;

public class PlayerUtils {

    private static final Plugin plugin = LockoutGames.getPluginInstance();

    private static ArrayList<Player> getOnlinePlayers(){
        return new ArrayList<>(plugin.getServer().getOnlinePlayers());
    }

    private static Stream<? extends Player> getPlayerStream() {
        return plugin.getServer().getOnlinePlayers().stream();
    }

    public static void resetAllPlayers() {
        for(Player p : getOnlinePlayers()) {
            p.setHealth(20);
            p.setExp(0);
            p.setFoodLevel(20);
            p.getInventory().clear();
        }
    }

    public static void moveAllPlayers(World w) {
        getPlayerStream().forEach(p -> p.teleport(w.getSpawnLocation()));
    }

}

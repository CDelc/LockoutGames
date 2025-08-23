package org.carden.lockoutgames.events.listener;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.carden.lockoutgames.LockoutGames;
import org.carden.lockoutgames.events.GoalObtainedEvent;
import org.carden.lockoutgames.game.GameBuilder;
import org.carden.lockoutgames.goal.Goal;
import org.carden.lockoutgames.goal.GoalType;

import java.util.HashSet;

/**
 * General Event Listener, will be used to listen to both custom and spigot events
 */
public class EventListener implements Listener {

    private final LockoutGames plugin;

    private final GameBuilder gameBuilder;

    public EventListener(LockoutGames plugin) {
        this.plugin = plugin;
        gameBuilder = plugin.getGameBuilder();
    }

    @EventHandler
    public void onPlayerPickupItem(EntityPickupItemEvent e) {
        if(gameBuilder.getGame() == null || !(e.getEntity() instanceof Player)) return;
        Bukkit.getScheduler().runTask(plugin, () -> {
            HashSet<Goal> goals = plugin.getGameBuilder().getGame().getActiveGoals();
            goals.stream()
                    .filter(goal -> goal.getGoalType() == GoalType.COLLECT_GOAL)
                    .filter(goal -> goal.check((Player) e.getEntity()))
                    .forEach(goal -> {
                        LockoutGames.getPluginInstance().getServer().getPluginManager().callEvent(new GoalObtainedEvent((Player) e.getEntity(), goal));
                    });
        });
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if(gameBuilder.getGame() == null || !(e.getWhoClicked() instanceof Player)) return;
        Bukkit.getScheduler().runTask(plugin, () -> {
            HashSet<Goal> goals = plugin.getGameBuilder().getGame().getActiveGoals();
            goals.stream()
                    .filter(goal -> goal.getGoalType() == GoalType.COLLECT_GOAL)
                    .filter(goal -> goal.check((Player) e.getWhoClicked()))
                    .forEach(goal -> {
                        LockoutGames.getPluginInstance().getServer().getPluginManager().callEvent(new GoalObtainedEvent((Player) e.getWhoClicked(), goal));
                    });
        });
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e) {
        Location spawnPointOverride = LockoutGames.getPluginInstance().getPlayerManager().getGamePlayerObject(e.getPlayer().getUniqueId()).getDefaultSpawnPoint();
        if(e.getPlayer().getRespawnLocation() == null) e.setRespawnLocation(spawnPointOverride);
    }
}

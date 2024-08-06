package org.carden.lockoutgames.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.carden.lockoutgames.LockoutGames;
import org.carden.lockoutgames.game.GameSettings;
import org.carden.lockoutgames.goal.CollectGoal;
import org.carden.lockoutgames.goal.Goal;

import java.util.HashSet;

/**
 * General Event Listener, will be used to listen to both custom and spigot events
 */
public class EventListener implements Listener {

    private final LockoutGames plugin;

    private final GameSettings gameSettings;

    public EventListener(LockoutGames plugin) {
        this.plugin = plugin;
        gameSettings = plugin.getGameSettings();
    }

    @EventHandler
    public void onPlayerPickupItem(EntityPickupItemEvent e) {
        if(gameSettings.getGame() == null) return;
        if(e.getEntity() instanceof Player) {
            plugin.getServer().getPluginManager().callEvent(new CollectGoalCheckEvent(
                    (Player) e.getEntity(),
                    e.getItem().getItemStack(),
                    CollectGoalCheckEvent.ObtainMethod.ITEMPICKUP
            ));
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if(gameSettings.getGame() == null) return;
        if(e.getWhoClicked() instanceof Player) {
            plugin.getServer().getPluginManager().callEvent(new CollectGoalCheckEvent(
                    (Player) e.getWhoClicked(),
                    e.getCurrentItem(),
                    CollectGoalCheckEvent.ObtainMethod.INVENTORYCLICK
            ));
        }
    }

    @EventHandler
    public void onCollectGoal(CollectGoalCheckEvent e) {
        if(gameSettings.getGame() == null) return;
        HashSet<Goal> goals = plugin.getGameSettings().getGame().getActiveGoals();
        goals.stream().filter(goal -> goal instanceof CollectGoal).filter(goal -> goal.check(e.getPlayer())).forEach(goal -> {
            LockoutGames.getPluginInstance().getServer().getPluginManager().callEvent(new GoalObtainedEvent(e.getPlayer(), goal));
        });
    }

    @EventHandler
    public void onGoalComplete(GoalObtainedEvent e) {
        if(gameSettings.getGame() != null) gameSettings.getGame().awardGoal(e);
    }
}

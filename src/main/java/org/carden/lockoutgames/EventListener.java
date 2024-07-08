package org.carden.lockoutgames;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.carden.lockoutgames.events.CollectGoalCheckEvent;

/**
 * General Event Listener, will be used to listen to both custom and spigot events
 */
public class EventListener implements Listener {

    private final JavaPlugin plugin;

    public EventListener(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerPickupItem(EntityPickupItemEvent e) {
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
        if(e.getWhoClicked() instanceof Player) {
            plugin.getServer().getPluginManager().callEvent(new CollectGoalCheckEvent(
                    (Player) e.getWhoClicked(),
                    e.getCurrentItem(),
                    CollectGoalCheckEvent.ObtainMethod.INVENTORYCLICK
            ));
        }
    }
}

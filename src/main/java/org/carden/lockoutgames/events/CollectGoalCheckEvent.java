package org.carden.lockoutgames.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class CollectGoalCheckEvent extends Event {
    /**
     * Event intended to trigger whenever any CollectGoal should be checked for completion on a player.
     *
     */

    private static final HandlerList HANDLERS = new HandlerList();
    private final Player player;
    private final ItemStack item;
    private final ObtainMethod obtainMethod;

    public enum ObtainMethod {
        ITEMPICKUP,
        INVENTORYCLICK
    }

    public CollectGoalCheckEvent(Player player, ItemStack item, ObtainMethod obtainMethod) {
        this.player = player;
        this.item = item;
        this.obtainMethod = obtainMethod;
    }

    /**
     *
     * @return The player that should be checked
     */
    public Player getPlayer() {
        return player;
    }

    /**
     *
     * @return The item that triggered this event
     */
    public ItemStack getItemStack() {
        return item;
    }

    /**
     *
     * @return The ingame event that triggered this event
     */
    public ObtainMethod obtainMethod() {
        return obtainMethod;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }
}

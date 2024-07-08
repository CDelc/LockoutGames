package org.carden.lockoutgames.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;
import org.carden.lockoutgames.goal.Goal;

public class GoalObtainedEvent extends Event {

    /**
     * Event that triggers whenever a player completes a goal
     */

    private static final HandlerList HANDLERS = new HandlerList();
    private final Player player;
    private final Goal goal;

    public GoalObtainedEvent(Player player, Goal goal) {
        this.player = player;
        this.goal = goal;
    }

    /**
     *
     * @return The player that completed the goal
     */
    public Player getPlayer() {
        return player;
    }

    /**
     *
     * @return The goal that was completed
     */
    public Goal getGoal() {
        return goal;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }
}

package org.carden.lockoutgames.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.carden.lockoutgames.game.player.GamePlayer;
import org.carden.lockoutgames.goal.Goal;
import org.jetbrains.annotations.NotNull;

public class GoalCompleteEvent extends Event {

    /**
     * Event that triggers whenever a player completes a goal
     */

    private static final HandlerList HANDLERS = new HandlerList();
    private final GamePlayer player;
    private final Goal goal;

    public GoalCompleteEvent(GamePlayer player, Goal goal) {
        this.player = player;
        this.goal = goal;
    }

    /**
     *
     * @return The player that completed the goal
     */
    public GamePlayer getPlayer() {
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
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }
}

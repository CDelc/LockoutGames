package org.carden.lockoutgames.goal;

import org.bukkit.event.Event;
import org.carden.lockoutgames.LockoutGames;
import org.carden.lockoutgames.events.GoalCompleteEvent;
import org.carden.lockoutgames.game.player.GamePlayer;

import java.util.ArrayList;
import java.util.Set;

public abstract class RegularGoal extends Goal {
    /**
     * This class is for any goal that represents an objective. Once a player has completed this objective, it is never un-completed.
     * This tracks a list of all players that have completed the objective, including who was first.
     */

    private ArrayList<GamePlayer> completedPlayers;

    protected RegularGoal(Set<Class<? extends Event>> checkEvents) {
        super(checkEvents);
    }

    /**
     * @return The first player to have completed this goal
     */
    public GamePlayer getFirstCompletedPlayer() {
        return completedPlayers.getFirst();
    }

    /**
     * @return The list of players to have completed this goal, in order
     */
    public ArrayList<GamePlayer> getAllCompletedPlayers() {
        return completedPlayers;
    }

    /**
     * This override makes sure the player is added to the list of completed players immediately before the event is fired.
     */
    @Override
    protected void fireGoalCompleteEvent(GamePlayer p) {
        if(!p.isSpectator() && !this.completedPlayers.contains(p)) {
            this.completedPlayers.add(p);
            LockoutGames.getPluginInstance().getServer().getPluginManager().callEvent(new GoalCompleteEvent(p, this));
        }
    }

}

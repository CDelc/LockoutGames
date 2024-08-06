package org.carden.lockoutgames.game;

import org.carden.lockoutgames.events.GoalObtainedEvent;
import org.carden.lockoutgames.goal.Goal;

import java.util.HashSet;

public interface Game {

    /**
     * Process this GoalObtainedEvent in the context of the game
     * @param e The event associated with the collected goal
     */
    void awardGoal(GoalObtainedEvent e);

    /**
     * @return A set of Goals that can be awarded to players
     */
    HashSet<Goal> getActiveGoals();

}

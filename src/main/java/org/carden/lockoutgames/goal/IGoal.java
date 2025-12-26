package org.carden.lockoutgames.goal;

import org.bukkit.event.Event;

import java.util.Set;

public interface IGoal {
    /**
     * @return The difficulty of this goal in accordance with the constants in GoalConstants.GoalDifficulties
     */
    GoalDifficulty getGoalDifficulty();

    /**
     * @return The description for this goal that should be displayed in game
     */
    String getDescription();

    /**
     * @return Used by the goal selector to ensure this goal is unique.
     * Mainly for resolving conflicts this goal may have with itself if multiple versions may be generated.
     */
    Set<String> getUniquenessStrings();

    /**
     * @return Used by the goal generated to ensure this goal is not used if another goal with the same conflict code has already been used in the game.
     * The goal should not conflict with itself
     */
    Set<GoalType> getGoalTypes();

    /**
     * @return The set of event classes that this goal should be checked with
     */
    Set<Class<? extends Event>> getCheckEvents();

    public void checkEvent(Event e);
}

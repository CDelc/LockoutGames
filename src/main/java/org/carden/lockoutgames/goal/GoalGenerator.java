package org.carden.lockoutgames.goal;

import org.bukkit.entity.Player;

public interface GoalGenerator {

    /**
     * Generic interface for all types of goals
     */

    enum Option {
        AND,
        OR
    }

    /**
     *
     * @return A Goal object built from this constants parameters
     */
    Goal generate();

    int getDifficulty();

    String getID();

    /**
     *
     * @return Whether this goal can safely be included in a game in this world
     */
    default boolean canGenerate() {
        return true;
    }
}

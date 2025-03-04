package org.carden.lockoutgames.goal;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface Goal {

    /**
     * Generic interface for all types of goals
     */

    enum Option {
        AND,
        OR
    }

    /**
     * Checks whether a player has completed a goal
     * @param p The player to check completion for
     */
    boolean check(Player p);

    /**
     * This method can logically generate a description, but may also be used to hardcode specific descriptions.
     * @return The in-game description of the goal.
     */
    String getDescription();

    /**
     *
     * @return ItemStack that will appear in the goal gui
     */
    ItemStack displayItem();

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

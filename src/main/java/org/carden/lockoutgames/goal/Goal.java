package org.carden.lockoutgames.goal;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.carden.lockoutgames.game.GameWorld;

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
     * If so, triggers a GoalObtainedEvent
     * @param p The player to check completion for
     * @param plugin A reference to the plugin (for event handling)
     */
    void validate(Player p, Plugin plugin);

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


    /**
     *
     * @param world World the game is taking place in
     * @return Whether this goal can safely be included in a game in this world
     */
    default boolean canGenerate(GameWorld world) {
        return true;
    }
}

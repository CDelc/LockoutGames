package org.carden.lockoutgames.goal;

import org.bukkit.event.Event;
import org.carden.lockoutgames.game.player.GamePlayer;

import java.util.*;
import java.util.stream.Collectors;

public abstract class ChecklistGoal<T> extends RegularGoal{
    /**
     * This is a variety of RegularGoal that involves a player completing a checklist of objectives.
     * T - The type of object being tracked
     */

    /**
     * requiredItems - List of Ts required to complete the goal
     */
    protected List<T> requiredItems;

    /**
     * playerCollectionTracker - Checklist for each player. Maps each player to the set of items they have yet to complete.
     */
    protected Map<GamePlayer, Set<T>> playerCollectionTracker;

    /**
     * Removes the item from the player's checklist if needed. If the checklist is empty, invokes fireGoalCompleteEvent for the player.
     */
    protected void checkFromChecklist(GamePlayer gamePlayer, T item) {
        if(!playerCollectionTracker.containsKey(gamePlayer)) {
            playerCollectionTracker.put(gamePlayer, new HashSet<>(requiredItems));
        }
        playerCollectionTracker.get(gamePlayer).remove(item);
        if(playerCollectionTracker.get(gamePlayer) != null && playerCollectionTracker.get(gamePlayer).isEmpty()) {
            fireGoalCompleteEvent(gamePlayer);
        }
    }

    /**
     * @param player - The player to get the checklist for
     * @return A map of each item to a boolean value. The value is true if the player has already completed it, false otherwise.
     */
    public Map<T, Boolean> getPlayerProgress(GamePlayer player) {
        if(!playerCollectionTracker.containsKey(player)) {
            return requiredItems.stream().collect(Collectors.toMap(item -> item, item -> false));
        };
        Map<T, Boolean> checkList = new HashMap<>();
        Set<T> playerItemsRemaining = playerCollectionTracker.get(player);
        for(T item : requiredItems) {
            checkList.put(item, !playerItemsRemaining.contains(item));
        }
        return checkList;
    }

    protected ChecklistGoal(Set<Class<? extends Event>> checkEvents) {
        super(checkEvents);
        playerCollectionTracker = new HashMap<>();
        requiredItems = new ArrayList<>();
    }
}

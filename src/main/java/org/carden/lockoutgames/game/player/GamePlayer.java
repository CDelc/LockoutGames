package org.carden.lockoutgames.game.player;

import org.bukkit.entity.Player;

import java.util.*;

public class GamePlayer {
    /**
     * Wrapper class for Player objects that can track plugin-specific information on players
     */

    private boolean isSpectator;
    private java.util.UUID UUID;
    private Player playerObject;
    private Map<UUID, Checklist<?>> checklists;

    public GamePlayer(Player p) {
        playerObject = p;
        UUID = p.getUniqueId();
        isSpectator = false;
        checklists = new HashMap<>();
    }

    /**
     *
     * @return true if LockoutGames considers the player to be a spectator, false otherwise
     * The only other possible state is for the player to be a participant
     */
    public boolean isSpectator() {
        return isSpectator;
    }

    /**
     * @return This player's UUID
     */
    public UUID getUUID() {
        return UUID;
    }

    /**
     * @return The Spigot Player object contained in this GamePlayer
     */
    public Player getPlayer() {
        return playerObject;
    }

    /**
     * Add a new checklist for this player
     * @param identifier The ID used to track this checklist
     * @param contents A set of the items on this checklist
     * @param type The class of the contents of this checklist (This should be the template type of the set passed to contents)
     * @param <T> The generic type used for the contents and class.
     */
    public <T> void addChecklist(UUID identifier, HashSet<T> contents, Class<T> type) {
        if (checklists.containsKey(identifier)) {
            return;
        }
        checklists.put(identifier, new Checklist<T>(contents, type));
    }

    /**
     * Delete every checklist on this player
     */
    public void clearAllChecklists() {
        checklists.clear();
    }

    /**
     *
     * @param identifier The ID of the checklist to progress
     * @param item The checklist item to remove
     * @param type The class of the item to remove (This is here for type safety)
     * @return true of this call completes the checklist, false otherwise
     * @param <T>
     */
    public <T> boolean progressChecklist(UUID identifier, T item, Class<T> type) {
        Checklist<?> checklist = checklists.get(identifier);
        if (checklist == null) {
            return false;
        }

        if (!checklist.getType().equals(type)) {
            throw new IllegalArgumentException("Type mismatch: Expected " + checklist.getType().getSimpleName());
        }

        @SuppressWarnings("unchecked")
        Checklist<T> typedChecklist = (Checklist<T>) checklist;

        return typedChecklist.checkItem(item);
    }

    /**
     * Sets the player object, this is used to correct for Player objects being created when a player logs on/off
     * @param p The new Player object to connect to this
     */
    protected void setPlayerObject(Player p) {
        this.playerObject = p;
    }

    /**
     * Helper class for checklists
     * @param <T> Type of checklist
     */
    private static class Checklist<T> {
        private final HashSet<T> list;
        private final Class<T> type;

        /**
         *
         * @param list Initial contents of the checklist
         * @param type classtype of contents
         */
        public Checklist(Set<T> list, Class<T> type) {
            if (list.isEmpty()) {
                throw new IllegalArgumentException("Checklist cannot be empty!");
            }
            this.type = type;
            this.list = new HashSet<>(list);
        }

        /**
         *
         * @param e The item to be removed from the checklist
         * @return True of the checklist is empty after this removal, false otherwise
         */
        public boolean checkItem(T e) {
            list.remove(e);
            return list.isEmpty();
        }

        /**
         *
         * @return The classtype of checklist contents
         */
        public Class<T> getType() {
            return type;
        }
    }
}

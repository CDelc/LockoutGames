package org.carden.lockoutgames.game.player;

import org.bukkit.*;
import org.bukkit.advancement.Advancement;
import org.bukkit.advancement.AdvancementProgress;
import org.bukkit.entity.Player;
import org.carden.lockoutgames.LockoutGames;

import java.util.*;

public class GamePlayer {
    /**
     * Wrapper class for Player objects that can track plugin-specific information on players
     */

    private boolean isSpectator;
    private final java.util.UUID UUID;
    private Player playerObject;
    Location defaultSpawnPoint;

    public GamePlayer(Player p) {
        playerObject = p;
        UUID = p.getUniqueId();
        isSpectator = false;
    }

    /**
     *
     * @return true if LockoutGames considers the player to be a spectator, false otherwise
     * The only other possible state is for the player to be a participant
     */
    public boolean isSpectator() {
        return isSpectator;
    }

    public boolean setSpectator(boolean spectator) {
        if(LockoutGames.getPluginInstance().getGameBuilder().gameIsRunning() && !spectator) {
            return false;
        }
        isSpectator = spectator;
        updateGamemode();
        return true;
    }

    public void resetPlayerStats() {
        updateGamemode();
        playerObject.setExp(0);
        playerObject.setHealth(20);
        playerObject.setFoodLevel(20);
        playerObject.getInventory().clear();
        playerObject.setLevel(0);
        resetAdvancements();
    }

    private void resetAdvancements() {
        for (Iterator<Advancement> it = Bukkit.advancementIterator(); it.hasNext(); ) {
            Advancement advancement = it.next();
            AdvancementProgress progress = playerObject.getAdvancementProgress(advancement);

            for (String criterion : progress.getAwardedCriteria()) {
                progress.revokeCriteria(criterion);
            }
        }
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
    public Player getCBPlayer() {
        return playerObject;
    }

    public void updateGamemode() {
        if(!isSpectator) {
            playerObject.setGameMode(GameMode.SURVIVAL);
        } else {
            playerObject.setGameMode(GameMode.SPECTATOR);
        }
    }

    public Location getDefaultSpawnPoint() {
        if(defaultSpawnPoint != null) {
            return defaultSpawnPoint;
        } else {
            return LockoutGames.getPluginInstance().getGameWorld().getWorld(World.Environment.NORMAL).getSpawnLocation();
        }
    }

    public boolean isOnline() {
        return Bukkit.getServer().getPlayer(UUID) != null;
    }

    public void setDefaultSpawnPoint(Location defaultSpawnPoint) {
        this.defaultSpawnPoint = defaultSpawnPoint;
    }

    /**
     * Sets the player object, this is used to correct for Player objects being created when a player logs on/off
     * @param p The new Player object to connect to this
     */
    protected void setPlayerObject(Player p) {
        this.playerObject = p;
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof GamePlayer)) return false;
        else return this.getUUID().equals(((GamePlayer) o).getUUID());
    }


}

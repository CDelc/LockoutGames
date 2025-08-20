package org.carden.lockoutgames.game.gametypes;

import org.bukkit.Location;
import org.bukkit.World;
import org.carden.lockoutgames.LockoutGames;
import org.carden.lockoutgames.events.GoalObtainedEvent;
import org.carden.lockoutgames.game.GameWorld;
import org.carden.lockoutgames.game.SettingsImage;
import org.carden.lockoutgames.game.player.GamePlayer;
import org.carden.lockoutgames.game.player.PlayerManager;
import org.carden.lockoutgames.goal.Goal;

import java.util.HashSet;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

public abstract class Game {

    protected CompletableFuture<Boolean> logicFuture;

    protected GameWorld world;
    protected SettingsImage settingsImage;
    protected PlayerManager playerManager;

    private final int SPREAD_GRID_RESOLUTION_SIZE = 100;

    Random rng;

    public Game(SettingsImage settingsImage, Random rng) {
        LockoutGames.getPluginInstance().getPlayerManager().cleanOfflinePlayers();
        LockoutGames.getPluginInstance().getPlayerManager().updatePlayerGamemodes();
        world = LockoutGames.getPluginInstance().getGameWorld();
        this.settingsImage = settingsImage;
        this.world.setWorldSize(settingsImage.getWorldSize());
        this.logicFuture = world.checkLogic();
        this.playerManager = LockoutGames.getPluginInstance().getPlayerManager();
        this.world.setWorldSettings(settingsImage);
        setPlayersInitialPosition();
        playerManager.getParticipants().forEach(GamePlayer::resetPlayerStats);
    }

    /**
     * Process this GoalObtainedEvent in the context of the game
     * @param e The event associated with the collected goal
     */
    abstract public void handleGoal(GoalObtainedEvent e);

    /**
     * @return A set of Goals that can be awarded to players
     */
    abstract public HashSet<Goal> getActiveGoals();


    public void setPlayersInitialPosition() {
        Random placementRandomizer = new Random();
        playerManager.getParticipants().forEach(gamePlayer -> {
            World overworld = world.getWorld(World.Environment.NORMAL);
            int worldRadius = settingsImage.getWorldSize() / 2;
            int gridRadius = SPREAD_GRID_RESOLUTION_SIZE / 2;
            int grid_x = placementRandomizer.nextInt(-gridRadius, gridRadius);
            int grid_z = placementRandomizer.nextInt(-gridRadius, gridRadius);
            int x = (grid_x * worldRadius) / gridRadius;
            int z = (grid_z * worldRadius) / gridRadius;
            Location destination = new Location(overworld, x, overworld.getHighestBlockYAt(x, z) + 1, z);
            int timeout = 0;
            while(!destination.clone().subtract(0, 1, 0).getBlock().getType().isSolid()) {
                if(timeout > 10000) break;
                timeout++;
                grid_x = placementRandomizer.nextInt(-gridRadius, gridRadius);
                grid_z = placementRandomizer.nextInt(-gridRadius, gridRadius); 
                x = (grid_x * worldRadius) / gridRadius;
                z = (grid_z * worldRadius) / gridRadius;
                destination = new Location(overworld, x, overworld.getHighestBlockYAt(x, z) + 1, z);
                LockoutGames.broadcastMessage(destination.clone().subtract(0, 1, 0).getBlock().getType().isSolid() + " " + timeout);
            }
            gamePlayer.setDefaultSpawnPoint(destination);
            gamePlayer.getPlayer().teleport(destination);
        });
    }

}

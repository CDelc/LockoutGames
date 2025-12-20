package org.carden.lockoutgames.game.gametypes;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.Event;
import org.carden.lockoutgames.LockoutGames;
import org.carden.lockoutgames.events.GoalCompleteEvent;
import org.carden.lockoutgames.game.GameWorld;
import org.carden.lockoutgames.game.setting.Setting;
import org.carden.lockoutgames.game.setting.SettingsImage;
import org.carden.lockoutgames.game.player.GamePlayer;
import org.carden.lockoutgames.game.player.PlayerManager;
import org.carden.lockoutgames.info.SettingIDS;

import java.util.List;
import java.util.Random;

public abstract class Game {

    protected GameWorld world;
    protected SettingsImage settingsImage;
    protected PlayerManager playerManager;
    protected List<GamePlayer> playerList;

    private final int SPREAD_GRID_RESOLUTION_SIZE = 100;

    public Game(SettingsImage settingsImage) {
        this.world = GameWorld.getGameWorld();
        this.settingsImage = settingsImage;
        this.playerManager = PlayerManager.getPlayerManager();
        setupGame();
    }

    /**
     * Process this GoalObtainedEvent in the context of the game
     * @param e The event associated with the collected goal
     */
    abstract public void handleGoalEvent(GoalCompleteEvent e);

    abstract public void checkGoals(Event e);

    protected void setupGame() {

        long seed = (new Random()).nextLong();
        LockoutGames.getRng().setSeed(seed);

        for(GamePlayer gamePlayer : playerManager.getAllPlayers()) {
            if(gamePlayer.isOnline() && !gamePlayer.isSpectator()) {
                playerList.add(gamePlayer);
            } else {
                gamePlayer.setSpectator(true);
            }
            gamePlayer.updateGamemode();
            gamePlayer.resetPlayerStats();
        }

        setPlayersInitialPosition();
    }

    public void setPlayersInitialPosition() {
        Random placementRandomizer = new Random();
        playerList.forEach(gamePlayer -> {
            World overworld = world.getWorld(World.Environment.NORMAL);
            int worldRadius = Setting.getSettingValue(SettingIDS.WORLD_SIZE) / 2;
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
            }
            gamePlayer.setDefaultSpawnPoint(destination);
            gamePlayer.getCBPlayer().teleport(destination);
        });
    }

    public SettingsImage getSettings() {
        return settingsImage;
    }
}

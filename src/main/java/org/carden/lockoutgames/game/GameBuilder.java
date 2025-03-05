package org.carden.lockoutgames.game;

 import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.carden.lockoutgames.LockoutGames;
import org.carden.lockoutgames.game.gametypes.Lockout;
import org.carden.lockoutgames.utils.PlayerUtils;

import java.util.HashSet;

public class GameBuilder {
    /**
     * Class that stores all the game settings information. This class is used as a factory to generate the actual game instances.
     */

    public enum Gametype {

        LOCKOUT(Lockout.class);

        private final Class<? extends Game> gameType;

        Gametype(Class<? extends Game> gameType) {
            this.gameType = gameType;
        }

        public Class<? extends Game> getGameClass() {
            return this.gameType;
        }
    }

    LockoutGames plugin;
    GameWorld world;
    Lockout game;
    Gametype gametype;

    HashSet<Player> players;
    HashSet<Player> spectators;

    int worldSize;
    int numGoals;

    static final int WORLD_SIZE_DEFAULT = 2000;
    static final int NUM_GOALS_DEFAULT = 25;

    public GameBuilder() {
        this.plugin = LockoutGames.getPluginInstance();
        this.players = new HashSet<>();
        this.spectators = new HashSet<>();
        this.game = null;

        setDefaults();
    }

    public void setDefaults() {

        players.addAll(plugin.getServer().getOnlinePlayers());
        setNumGoals(NUM_GOALS_DEFAULT);
        setWorldSize(WORLD_SIZE_DEFAULT);
        gametype = Gametype.LOCKOUT;

    }

    /**
     *
     * @return World Size (Diameter from border to border)
     */
    public int getWorldSize() {
        return worldSize;
    }

    /**
     *
     * @param worldSize The new world size diameter setting
     */
    public void setWorldSize(int worldSize) {
        this.worldSize = worldSize;
    }

    /**
     *
     * @return Number of goals to be generated
     */
    public int getNumGoals() {
        return numGoals;
    }

    /**
     *
     * @param numGoals The number of goals to be generated for games
     */
    public void setNumGoals(int numGoals) {
        this.numGoals = numGoals;
    }

    /**
     *
     * @return The instance of the game, if one is running
     */
    public Game getGame() {
        return game;
    }

    /**
     * Begins a new game.
     * @return False if a game is already running, true if the game begins successfully.
     */
    public boolean start() {
        if (game != null) return false;
        GameBuilder instance = this;
        PlayerUtils.resetAllPlayers();
        GameWorld world = new GameWorld();
        world.generateWorld().thenRun(new BukkitRunnable() {
            @Override
            public void run() {
                LockoutGames.broadcastMessage("World Gen Complete");
                game = new Lockout(world, new SettingsImage(instance));
            }
        });
        return true;
    }
}

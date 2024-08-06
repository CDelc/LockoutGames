package org.carden.lockoutgames.game;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.carden.lockoutgames.LockoutGames;
import org.carden.lockoutgames.game.gamemodes.Lockout;
import org.carden.lockoutgames.utils.GoalSelector;
import org.carden.lockoutgames.utils.PlayerUtils;

import java.util.HashSet;

public class GameSettings {
    /**
     * Class that stores all the game settings information. This class is used as a factory to generate the actual game instances.
     */

    LockoutGames plugin;
    GameWorld world;
    Lockout game;

    GoalSelector goalSelector;

    HashSet<Player> players;
    HashSet<Player> spectators;

    int worldSize;
    int numGoals;

    static final int WORLD_SIZE_DEFAULT = 4000;
    static final int NUM_GOALS_DEFAULT = 25;

    /**
     *
     * @return The GoalSelector object this game factory is using
     */
    protected GoalSelector getGoalSelector() {
        return goalSelector;
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
        world.setWorldSize(worldSize);
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

    public HashSet<Player> getPlayers() {
        return players;
    }

    public HashSet<Player> getSpectators() {
        return spectators;
    }


    public GameSettings() {
        this.plugin = LockoutGames.getPluginInstance();
        this.world = new GameWorld(plugin);
        this.players = new HashSet<>();
        this.spectators = new HashSet<>();
        this.game = null;

        setDefaults();
    }

    private void setDefaults() {

        players.addAll(plugin.getServer().getOnlinePlayers());
        setNumGoals(NUM_GOALS_DEFAULT);
        setWorldSize(WORLD_SIZE_DEFAULT);
    }

    /**
     *
     * @return The GameWorld object that the game takes place in.
     */
    public GameWorld getWorld() {
        return world;
    }

    /**
     * Begins a new game.
     * @return False if a game is already running, true if the game begins successfully.
     */
    public boolean start() {
        if(game != null) return false;
        GameSettings instance = this;
        PlayerUtils.resetAllPlayers();
        world.generateWorld().thenRun(new BukkitRunnable() {
            @Override
            public void run() {
                game = new Lockout(instance);
            }
        });
        return true;
    }
}

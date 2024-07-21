package org.carden.lockoutgames.game;

import org.bukkit.entity.Player;
import org.carden.lockoutgames.LockoutGames;
import org.carden.lockoutgames.goal.GoalSelector;

import java.util.HashSet;

public class GameSettings {
    /**
     * Class that stores all the game settings information. This class is used as a factory to generate the actual game instances.
     */

    LockoutGames plugin;
    GameWorld world;
    LockoutGame lockout;

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
     * @return The instance of this plugin
     */
    protected LockoutGames getPlugin() {
        return plugin;
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
    public LockoutGame getGame() {
        return lockout;
    }


    public GameSettings(LockoutGames plugin) {
        this.plugin = plugin;
        this.world = new GameWorld(plugin);
        this.players = new HashSet<>();
        this.spectators = new HashSet<>();
        this.goalSelector = new GoalSelector();
        this.lockout = null;

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
        if(lockout != null) return false;
        world.generateWorld();
        //lockout = new LockoutGame(this);
        return true;
    }
}

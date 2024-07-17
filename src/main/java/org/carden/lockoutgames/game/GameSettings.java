package org.carden.lockoutgames.game;

import org.bukkit.entity.Player;
import org.carden.lockoutgames.LockoutGames;
import org.carden.lockoutgames.goal.GoalSelector;

import java.util.HashSet;

public class GameSettings {

    LockoutGames plugin;
    GameWorld world;
    LockoutGame lockout;

    GoalSelector goalSelector;

    HashSet<Player> players;
    HashSet<Player> spectators;

    int worldSize;
    int numGoals;

    protected GoalSelector getGoalSelector() {
        return goalSelector;
    }

    public int getWorldSize() {
        return worldSize;
    }

    public void setWorldSize(int worldSize) {
        this.worldSize = worldSize;
        world.setWorldSize(worldSize);
    }

    public int getNumGoals() {
        return numGoals;
    }

    public void setNumGoals(int numGoals) {
        this.numGoals = numGoals;
    }

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

        setNumGoals(25);
        setWorldSize(2000);
    }

    public GameWorld getWorld() {
        return world;
    }

    public boolean start() {
        if(lockout != null) return false;
        world.regenerate();
        lockout = new LockoutGame(this);
        return true;
    }
}

package org.carden.lockoutgames.game;

import org.bukkit.entity.Player;
import org.carden.lockoutgames.LockoutGames;
import org.carden.lockoutgames.goal.Goal;

import java.util.ArrayList;

public class LockoutGame {

    LockoutGames plugin;

    ArrayList<Goal> goals;
    GameSettings settings;

    ArrayList<Player> players;

    int numGoals;

    public LockoutGame(GameSettings settings) {
        this.settings = settings;
        this.numGoals = settings.numGoals;
        this.plugin = settings.getPlugin();
        plugin.getServer().broadcastMessage("Selecting " + numGoals + " Goals...");
        goals = settings.goalSelector.select(this.numGoals);
    }

    public ArrayList<Goal> getGoals() {
        return goals;
    }

}

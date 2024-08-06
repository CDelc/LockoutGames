package org.carden.lockoutgames.game.gamemodes;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.carden.lockoutgames.LockoutGames;
import org.carden.lockoutgames.events.GoalObtainedEvent;
import org.carden.lockoutgames.game.Game;
import org.carden.lockoutgames.game.GameSettings;
import org.carden.lockoutgames.goal.Goal;
import org.carden.lockoutgames.utils.GoalSelector;

import java.util.HashMap;
import java.util.HashSet;
import java.util.stream.Collectors;

public class Lockout implements Game {

    LockoutGames plugin;

    HashSet<Goal> goals;
    HashSet<String> goalIDs;
    GameSettings settings;

    HashSet<Player> players;
    HashSet<Player> spectators;

    HashMap<Player, HashSet<Goal>> goalLookup;
    HashSet<String> collectedGoals;

    int numGoals;

    public Lockout(GameSettings settings) {
        this.settings = settings;
        this.numGoals = settings.getNumGoals();
        this.plugin = LockoutGames.getPluginInstance();
        plugin.getServer().broadcastMessage("Selecting " + numGoals + " Goals...");
        goals = GoalSelector.select(numGoals);
        goalIDs = (HashSet<String>) goals.stream().map(Goal::getID).collect(Collectors.toSet());
        this.players = settings.getPlayers();
        goalLookup = new HashMap<>();
        collectedGoals = new HashSet<>();
        for(Goal g : goals) {
            plugin.getServer().broadcastMessage(g.getDescription());
        }
        for(Player p : players) {
            goalLookup.put(p, new HashSet<>());
        }
    }

    @Override
    public void awardGoal(GoalObtainedEvent e) {
        if(goalAvailable(e.getGoal())){
            LockoutGames.broadcastMessage(ChatColor.LIGHT_PURPLE + e.getPlayer().getDisplayName() + ChatColor.AQUA + " has completed " + ChatColor.GREEN + e.getGoal().getDescription());
            collectedGoals.add(e.getGoal().getID());
        }
    }

    @Override
    public HashSet<Goal> getActiveGoals() {
        return goals;
    }

    private boolean goalAvailable(Goal g){
        return goalIDs.contains(g.getID()) && !collectedGoals.contains(g.getID());
    }

}

package org.carden.lockoutgames.game;

import org.carden.lockoutgames.LockoutGames;
import org.carden.lockoutgames.events.GoalObtainedEvent;
import org.carden.lockoutgames.goal.Goal;

import java.util.HashSet;
import java.util.concurrent.CompletableFuture;

public abstract class Game {

    protected CompletableFuture<Boolean> logicFuture;

    protected GameWorld world;
    protected SettingsImage settingsImage;

    public Game(SettingsImage settingsImage) {
        world = LockoutGames.getPluginInstance().getGameWorld();
        this.settingsImage = settingsImage;
        this.world.setWorldSize(settingsImage.getWorldSize());
        this.logicFuture = world.checkLogic();
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

}

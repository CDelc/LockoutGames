package org.carden.lockoutgames.game;

import org.carden.lockoutgames.events.GoalObtainedEvent;
import org.carden.lockoutgames.goal.Goal;
import org.carden.lockoutgames.info.WorldRequirements;

import java.util.HashSet;

public abstract class Game {

    GameWorld world;
    SettingsImage settingsImage;

    public Game(GameWorld world, SettingsImage settingsImage) {
        this.world = world;
        this.settingsImage = settingsImage;
        this.world.setWorldSize(settingsImage.getWorldSize());
    }

    public GameWorld getWorld() {
        return world;
    }

    public void setWorld(GameWorld world) {
        this.world = world;
        WorldRequirements.Element.setWorld(world);
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

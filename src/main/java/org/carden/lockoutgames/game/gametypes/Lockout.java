package org.carden.lockoutgames.game.gametypes;

import org.carden.lockoutgames.LockoutGames;
import org.carden.lockoutgames.events.GoalObtainedEvent;
import org.carden.lockoutgames.game.SettingsImage;
import org.carden.lockoutgames.goal.Goal;
import org.carden.lockoutgames.utils.GoalSelector;

import java.util.HashSet;
import java.util.Random;

public class Lockout extends Game {

    HashSet<Goal> goals;

    public Lockout(SettingsImage settingsImage, Random rng) {
        super(settingsImage, rng);
        logicFuture.thenRun(() -> {
            this.goals = GoalSelector.select(settingsImage.getNumGoals());
        });
    }

    @Override
    public void handleGoal(GoalObtainedEvent e) {
        LockoutGames.broadcastMessage(e.getPlayer() + " has completed " + e.getGoal().getDescription());
    }

    @Override
    public HashSet<Goal> getActiveGoals() {
        return this.goals;
    }
}

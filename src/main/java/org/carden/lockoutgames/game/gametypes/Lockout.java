package org.carden.lockoutgames.game.gametypes;

import org.carden.lockoutgames.LockoutGames;
import org.carden.lockoutgames.events.GoalObtainedEvent;
import org.carden.lockoutgames.game.Game;
import org.carden.lockoutgames.game.GameWorld;
import org.carden.lockoutgames.game.SettingsImage;
import org.carden.lockoutgames.goal.Goal;
import org.carden.lockoutgames.utils.GoalSelector;

import java.util.HashSet;

public class Lockout extends Game {

    HashSet<Goal> goals;

    public Lockout(GameWorld world, SettingsImage settingsImage) {
        super(world, settingsImage);
        this.goals = GoalSelector.select(settingsImage.getNumGoals());
        LockoutGames.broadcastMessage("awiohdpoa;iewhjf");
        this.goals.stream().toList().forEach(goal -> LockoutGames.broadcastMessage(goal.getDescription()));
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

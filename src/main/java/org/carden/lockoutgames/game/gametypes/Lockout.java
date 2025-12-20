package org.carden.lockoutgames.game.gametypes;

import org.bukkit.event.Event;
import org.carden.lockoutgames.LockoutGames;
import org.carden.lockoutgames.events.GoalCompleteEvent;
import org.carden.lockoutgames.game.setting.SettingsImage;
import org.carden.lockoutgames.goal.Goal;

import java.util.HashSet;

public class Lockout extends Game {

    HashSet<Goal> goals;

    public Lockout(SettingsImage settingsImage) {
        super(settingsImage);
    }

    @Override
    public void handleGoalEvent(GoalCompleteEvent e) {
        LockoutGames.broadcastMessage(e.getPlayer() + " has completed " + e.getGoal().getDescription());
    }

    @Override
    public void checkGoals(Event e) {
        for(Goal g : goals) {
            g.check(e);
        }
    }

    public HashSet<Goal> getActiveGoals() {
        return this.goals;
    }
}

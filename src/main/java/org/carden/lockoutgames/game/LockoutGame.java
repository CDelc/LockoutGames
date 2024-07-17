package org.carden.lockoutgames.game;

import org.carden.lockoutgames.goal.Goal;
import org.carden.lockoutgames.goal.GoalSelector;

import java.util.ArrayList;

public class LockoutGame {

    ArrayList<Goal> goals;
    GameSettings settings;

    int numGoals;

    public LockoutGame(GameSettings settings) {
        this.settings = settings;
        this.numGoals = settings.numGoals;
        goals = settings.goalSelector.select(this.numGoals);
    }

    public ArrayList<Goal> getGoals() {
        return goals;
    }

}

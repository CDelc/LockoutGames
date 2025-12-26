package org.carden.lockoutgames.goal.factory;

import org.carden.lockoutgames.game.setting.SettingsImage;
import org.carden.lockoutgames.goal.Goal;
import org.carden.lockoutgames.goal.GoalDifficulty;
import org.carden.lockoutgames.goal.GoalType;
import org.carden.lockoutgames.goal.IGoal;

import java.util.Collection;
import java.util.List;

public interface GoalFactory {
    /**
     * Create a goal according to the set constraints.
     * @return the goal
     * @throws IllegalStateException if canGenerateGoal is false
     */
    IGoal makeGoal(SettingsImage settings, List<String> uniquenessStrings);

    /**
     * Whether this factory can generate a goal.
     * @return can generate
     */
    boolean canGenerateGoal(SettingsImage settings, List<String> uniquenessStrings);

    boolean canGenerateMultiple();

    GoalFactory setMinDifficulty(GoalDifficulty difficulty);

    GoalFactory setMaxDifficulty(GoalDifficulty difficulty);
}

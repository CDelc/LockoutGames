package org.carden.lockoutgames.goal;

import java.util.Set;

public interface IMutableGoal extends IGoal {
    void setGoalDifficulty(GoalDifficulty goalDifficulty);

    void addUniquenessStrings(Set<String> uniquenessStrings);

    void setDescription(String description);
}

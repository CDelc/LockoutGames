package org.carden.lockoutgames.goal.factory;


import org.carden.lockoutgames.goal.GoalConstants;
import org.carden.lockoutgames.goal.GoalDifficulty;
import org.carden.lockoutgames.goal.GoalType;

public class ObtainLushSingle extends ObtainSingleItemFromSetVariableStackSize {

    public ObtainLushSingle() {
        super(GoalConstants.LUSH_BLOCKS, GoalDifficulty.MEDIUM);

        this.addGoalTypes(GoalType.OBTAIN_LUSH);
        this.canGenerateMultiple = false;
    }
}

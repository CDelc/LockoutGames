package org.carden.lockoutgames.goal.factory.instance;


import org.carden.lockoutgames.goal.GoalConstants;
import org.carden.lockoutgames.goal.GoalDifficulty;
import org.carden.lockoutgames.goal.GoalType;
import org.carden.lockoutgames.goal.factory.base.ObtainSingleItemFromSetVariableStackSize;

public class ObtainLushSingle extends ObtainSingleItemFromSetVariableStackSize {

    public ObtainLushSingle() {
        super(GoalConstants.LUSH_BLOCKS, GoalDifficulty.EASY);

        this.addGoalTypes(GoalType.OBTAIN_LUSH);
        this.canGenerateMultiple = false;
    }
}

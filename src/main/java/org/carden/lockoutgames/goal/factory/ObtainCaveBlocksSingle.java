package org.carden.lockoutgames.goal.factory;

import org.carden.lockoutgames.goal.GoalConstants;
import org.carden.lockoutgames.goal.GoalType;

public class ObtainCaveBlocksSingle extends ObtainSingleItemFromSetVariableStackSize{

    public ObtainCaveBlocksSingle() {
        super(GoalConstants.CAVE_BLOCKS, GoalConstants.CAVE_BLOCK_DIFFICULTY_MAP);

        this.addGoalTypes(GoalType.CAVE_BLOCK);
        this.canGenerateMultiple = false;
    }

}

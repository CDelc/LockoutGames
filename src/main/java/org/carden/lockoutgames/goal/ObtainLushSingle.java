package org.carden.lockoutgames.goal;


public class ObtainLushSingle extends ObtainSingleItemFromSetVariableStackSize {

    protected ObtainLushSingle() {
        super(GoalConstants.LUSH_BLOCKS, GoalDifficulty.MEDIUM);

        this.goalTypes.add(GoalType.OBTAIN_LUSH);
        this.canGenerateMultiple = false;
    }
}

package org.carden.lockoutgames.goal;

public class ObtainCaveBlocksSingle extends ObtainSingleItemFromSetVariableStackSize{

    protected ObtainCaveBlocksSingle() {
        super(GoalConstants.CAVE_BLOCKS, GoalConstants.CAVE_BLOCK_DIFFICULTY_MAP);

        this.goalTypes.add(GoalType.CAVE_BLOCK);
        this.canGenerateMultiple = false;
    }

}

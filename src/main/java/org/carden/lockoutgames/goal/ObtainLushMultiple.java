package org.carden.lockoutgames.goal;

public class ObtainLushMultiple extends ObtainSubsetOrFullSetOfItems {

    private static final int SUBSET_PERCENT_CHANCE = 30;
    private static final int STACK_PERCENT_CHANCE = 40;
    private static final int SUBSET_MIN_SIZE = 2;
    private static final int SUBSET_MAX_SIZE = 4;
    private static final int MIN_STACK_SIZE = 2;
    private static final int MAX_STACK_SIZE = 8;

    protected ObtainLushMultiple() {
        super(GoalConstants.LUSH_BLOCKS.keySet().stream().toList(), SUBSET_PERCENT_CHANCE, STACK_PERCENT_CHANCE, SUBSET_MIN_SIZE, SUBSET_MAX_SIZE, MIN_STACK_SIZE, MAX_STACK_SIZE, "lush block", GoalDifficulty.MEDIUM);

        this.goalTypes.add(GoalType.OBTAIN_LUSH);
        this.canGenerateMultiple = false;
    }
}

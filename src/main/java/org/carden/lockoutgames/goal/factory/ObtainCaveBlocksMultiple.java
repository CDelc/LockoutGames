package org.carden.lockoutgames.goal.factory;

import org.carden.lockoutgames.goal.GoalConstants;
import org.carden.lockoutgames.goal.GoalDifficulty;
import org.carden.lockoutgames.goal.GoalType;

public class ObtainCaveBlocksMultiple extends ObtainSubsetOrFullSetOfItems {

    private static final int SUBSET_PERCENT_CHANCE = 30;
    private static final int STACK_PERCENT_CHANCE = 80;
    private static final int SUBSET_MIN_SIZE = 3;
    private static final int SUBSET_MAX_SIZE = 5;
    private static final int MIN_STACK_SIZE = 8;
    private static final int MAX_STACK_SIZE = 32;

//    private static final Set<Material> LUSH_BIOME_REQUIRED = Set.of(Material.MOSS_BLOCK, Material.MOSS_CARPET);
//    private static final Set<Material> DRIPSTONE_REQUIRED = Set.of(Material.DRIPSTONE_BLOCK, Material.POINTED_DRIPSTONE);
//    private static final Set<Material> DEEP_DARK_REQUIRED = Set.of(Material.SCULK);
    private static final GoalDifficulty GOAL_BOOST_DIFFICULTY = GoalDifficulty.MEDIUM;

    public ObtainCaveBlocksMultiple() {
        super(GoalConstants.CAVE_BLOCKS.keySet(), SUBSET_PERCENT_CHANCE, STACK_PERCENT_CHANCE, SUBSET_MIN_SIZE, SUBSET_MAX_SIZE, MIN_STACK_SIZE, MAX_STACK_SIZE, "cave block", GoalConstants.CAVE_BLOCK_DIFFICULTY_MAP, GOAL_BOOST_DIFFICULTY);
        this.addGoalTypes(GoalType.CAVE_BLOCK);
        this.canGenerateMultiple = false;
    }
}

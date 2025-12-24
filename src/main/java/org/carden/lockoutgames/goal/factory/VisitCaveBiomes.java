package org.carden.lockoutgames.goal.factory;

import org.bukkit.block.Biome;
import org.carden.lockoutgames.goal.Goal;
import org.carden.lockoutgames.goal.GoalDifficulty;
import org.carden.lockoutgames.goal.VisitBiomes;

import java.util.Set;

public class VisitCaveBiomes extends AllOrOneFactory<Biome> {
    private static final Set<Biome> CAVE_BIOMES = Set.of(Biome.DRIPSTONE_CAVES, Biome.LUSH_CAVES, Biome.DEEP_DARK);
    private static final GoalDifficulty DIFFICULTY_ONE_BIOME = GoalDifficulty.EASY;
    private static final GoalDifficulty DIFFICULTY_ALL_BIOMES = GoalDifficulty.MEDIUM;

    public VisitCaveBiomes() {
        super(CAVE_BIOMES, DIFFICULTY_ONE_BIOME, DIFFICULTY_ALL_BIOMES);
        this.setAllProbablility(0.4f);
    }

    @Override
    protected Goal makeOne(Biome value) {
        return new VisitBiomes(Set.of(value), DIFFICULTY_ONE_BIOME, String.format("Visit the %s biome", value));
    }

    @Override
    protected Goal makeAll(Set<Biome> values) {
        return new VisitBiomes(values, DIFFICULTY_ALL_BIOMES, "Visit every cave biome");
    }

    @Override
    protected boolean canGenerateGoalHook() {
        return false;
    }
}

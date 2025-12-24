package org.carden.lockoutgames.goal.factory;

import org.bukkit.block.Biome;
import org.carden.lockoutgames.goal.Goal;
import org.carden.lockoutgames.goal.GoalDifficulty;
import org.carden.lockoutgames.goal.VisitBiomes;

import java.util.Set;

public class VisitNetherBiomes extends AllOrOneFactory<Biome> {
    private static final Set<Biome> NETHER_BIOMES = Set.of(Biome.NETHER_WASTES, Biome.WARPED_FOREST, Biome.CRIMSON_FOREST, Biome.SOUL_SAND_VALLEY);
    private static final GoalDifficulty DIFFICULTY_ONE_BIOME = GoalDifficulty.EASY;
    private static final GoalDifficulty DIFFICULTY_ALL_BIOMES = GoalDifficulty.MEDIUM;

    public VisitNetherBiomes() {
        super(NETHER_BIOMES, DIFFICULTY_ONE_BIOME, DIFFICULTY_ALL_BIOMES);
        this.setAllProbablility(0.4f);
    }

    @Override
    protected Goal makeOne(Biome value) {
        return new VisitBiomes(Set.of(value), DIFFICULTY_ONE_BIOME, String.format("Visit the %s biome", value));
    }

    @Override
    protected Goal makeAll(Set<Biome> values) {
        return new VisitBiomes(values, DIFFICULTY_ALL_BIOMES, "Visit every nether biome");
    }

    @Override
    protected boolean canGenerateGoalHook() {
        return false;
    }
}

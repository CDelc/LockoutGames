package org.carden.lockoutgames.goal.factory;

import org.bukkit.block.Biome;
import org.carden.lockoutgames.LockoutGames;
import org.carden.lockoutgames.game.GameWorld;
import org.carden.lockoutgames.goal.*;

import java.util.Set;
import java.util.stream.Collectors;

public class VisitNetherBiomes extends AllOrOneFactory<Biome> {
    private static final Set<Biome> NETHER_BIOMES = Set.of(Biome.NETHER_WASTES, Biome.WARPED_FOREST, Biome.CRIMSON_FOREST, Biome.SOUL_SAND_VALLEY);
    private static final GoalDifficulty DIFFICULTY_ONE_BIOME = GoalDifficulty.EASY;
    private static final GoalDifficulty DIFFICULTY_ALL_BIOMES = GoalDifficulty.MEDIUM;

    public VisitNetherBiomes() {
        super(NETHER_BIOMES, DIFFICULTY_ONE_BIOME, DIFFICULTY_ALL_BIOMES);
        this.setAllProbablility(0.4f);
        this.addGoalTypes(GoalType.VISIT_BIOME);
    }

    @Override
    protected IMutableGoal makeOne(Biome value) {
        Goal g = new VisitBiomes(Set.of(value));
        g.setGoalDifficulty(DIFFICULTY_ONE_BIOME);
        g.setDescription(String.format("Visit the %s biome", value));
        return g;
    }

    @Override
    protected IMutableGoal makeAll(Set<Biome> values) {
        Goal g = new VisitBiomes(values);
        g.setGoalDifficulty(DIFFICULTY_ALL_BIOMES);
        g.setDescription("Visit every nether biome");
        return g;
    }

    @Override
    protected Set<Biome> filteredChoices() {
        GameWorld world = LockoutGames.getGameWorld();
        return super.filteredChoices().stream().filter(world::hasBiome).collect(Collectors.toSet());
    }
}

package org.carden.lockoutgames.goal.factory;

import org.bukkit.block.Biome;
import org.carden.lockoutgames.LockoutGames;
import org.carden.lockoutgames.game.GameWorld;
import org.carden.lockoutgames.goal.*;

import java.util.Set;
import java.util.stream.Collectors;

public class VisitCaveBiomes extends AllOrOneFactory<Biome> {
    private static final Set<Biome> CAVE_BIOMES = Set.of(Biome.DRIPSTONE_CAVES, Biome.LUSH_CAVES, Biome.DEEP_DARK);
    private static final GoalDifficulty DIFFICULTY_ONE_BIOME = GoalDifficulty.EASY;
    private static final GoalDifficulty DIFFICULTY_ALL_BIOMES = GoalDifficulty.MEDIUM;

    public VisitCaveBiomes() {
        super(CAVE_BIOMES, DIFFICULTY_ONE_BIOME, DIFFICULTY_ALL_BIOMES);
        this.addGoalTypes(GoalType.VISIT_BIOME);
        this.setAllProbablility(0.4f);
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
        g.setDescription("Visit every cave biome");
        return g;
    }

    @Override
    protected Set<Biome> filteredChoices() {
        GameWorld world = LockoutGames.getGameWorld();
        return super.filteredChoices().stream().filter(world::hasBiome).collect(Collectors.toSet());
    }
}

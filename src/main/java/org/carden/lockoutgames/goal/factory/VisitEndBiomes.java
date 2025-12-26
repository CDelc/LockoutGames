package org.carden.lockoutgames.goal.factory;

import org.bukkit.block.Biome;
import org.carden.lockoutgames.LockoutGames;
import org.carden.lockoutgames.game.GameWorld;
import org.carden.lockoutgames.game.setting.SettingsImage;
import org.carden.lockoutgames.goal.*;
import org.carden.lockoutgames.utils.Utils;

import java.util.Set;
import java.util.stream.Collectors;

public class VisitEndBiomes extends AllOrOneFactory<Biome> {
    private static final Set<Biome> END_BIOMES = Set.of(Biome.THE_END, Biome.END_BARRENS, Biome.END_MIDLANDS, Biome.END_HIGHLANDS);
    private static final GoalDifficulty DIFFICULTY_ONE_BIOME = GoalDifficulty.HARD;
    private static final GoalDifficulty DIFFICULTY_ALL_BIOMES = GoalDifficulty.HARD;

    public VisitEndBiomes() {
        super(END_BIOMES, DIFFICULTY_ONE_BIOME, DIFFICULTY_ALL_BIOMES);
        this.setAllProbablility(0.6f);
        this.addGoalTypes(GoalType.VISIT_BIOME);
    }

    @Override
    protected IMutableGoal makeOne(Biome value) {
        Goal g = new VisitBiomes(Set.of(value));
        g.setGoalDifficulty(DIFFICULTY_ONE_BIOME);
        g.setDescription(String.format("Visit the %s biome", Utils.readableEnumString(value.toString())));
        return g;
    }

    @Override
    protected IMutableGoal makeAll(Set<Biome> values) {
        Goal g = new VisitBiomes(values);
        g.setGoalDifficulty(DIFFICULTY_ALL_BIOMES);
        g.setDescription("Visit every end biome");
        return g;
    }

    @Override
    protected Set<Biome> filteredChoices() {
        GameWorld world = LockoutGames.getGameWorld();
        return super.filteredChoices().stream().filter(world::hasBiome).collect(Collectors.toSet());
    }
}

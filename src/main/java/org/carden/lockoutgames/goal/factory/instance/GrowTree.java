package org.carden.lockoutgames.goal.factory.instance;

import org.bukkit.Material;
import org.carden.lockoutgames.goal.FertilizeTree;
import org.carden.lockoutgames.goal.GoalDifficulty;
import org.carden.lockoutgames.goal.IMutableGoal;
import org.carden.lockoutgames.goal.factory.Filters;
import org.carden.lockoutgames.goal.factory.base.SelectFromChoices;
import org.carden.lockoutgames.utils.Utils;

import java.util.Map;
import java.util.Set;

public class GrowTree extends SelectFromChoices<Material> {
    public static final Map<Material, GoalDifficulty> SAPLING_DIFFICULTY_MAP = Map.ofEntries(
            Map.entry(Material.OAK_SAPLING, GoalDifficulty.EASY),
            Map.entry(Material.DARK_OAK_SAPLING, GoalDifficulty.EASY),
            Map.entry(Material.SPRUCE_SAPLING, GoalDifficulty.EASY),
            Map.entry(Material.BIRCH_SAPLING, GoalDifficulty.EASY),
            Map.entry(Material.JUNGLE_SAPLING, GoalDifficulty.EASY),
            Map.entry(Material.ACACIA_SAPLING, GoalDifficulty.EASY),
            Map.entry(Material.AZALEA, GoalDifficulty.MEDIUM),
            Map.entry(Material.MANGROVE_PROPAGULE, GoalDifficulty.MEDIUM),
            Map.entry(Material.CHERRY_SAPLING, GoalDifficulty.MEDIUM),
            Map.entry(Material.PALE_OAK_SAPLING, GoalDifficulty.MEDIUM),
            Map.entry(Material.WARPED_FUNGUS, GoalDifficulty.MEDIUM),
            Map.entry(Material.CRIMSON_FUNGUS, GoalDifficulty.MEDIUM),
            Map.entry(Material.BROWN_MUSHROOM, GoalDifficulty.EASY),
            Map.entry(Material.RED_MUSHROOM, GoalDifficulty.EASY)
    );

    public GrowTree() {
        super(SAPLING_DIFFICULTY_MAP, Filters::itemAvailable);
    }

    @Override
    protected IMutableGoal makeGoalFor(Material sapling, GoalDifficulty difficulty) {
        IMutableGoal goal = new FertilizeTree(getSaplings(sapling));
        goal.setGoalDifficulty(difficulty);
        goal.setDescription("Bonemeal and grow a " + Utils.readableEnumString(sapling.name()));
        return goal;
    }

    private static Set<Material> getSaplings(Material sapling) {
        if (sapling == Material.AZALEA) {
            return Set.of(Material.AZALEA, Material.FLOWERING_AZALEA);
        }
        else {
            return Set.of(sapling);
        }
    }
}

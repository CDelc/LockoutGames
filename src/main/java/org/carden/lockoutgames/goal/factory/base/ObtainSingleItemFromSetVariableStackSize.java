package org.carden.lockoutgames.goal.factory.base;

import org.bukkit.Material;
import org.carden.lockoutgames.LockoutGames;
import org.carden.lockoutgames.goal.CollectItemGoal;
import org.carden.lockoutgames.goal.GoalConstants;
import org.carden.lockoutgames.goal.GoalDifficulty;
import org.carden.lockoutgames.goal.IMutableGoal;
import org.carden.lockoutgames.goal.factory.Filters;
import org.carden.lockoutgames.goal.factory.UniquenessStrings;
import org.carden.lockoutgames.goal.factory.selector.MapSelector;
import org.carden.lockoutgames.utils.Utils;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class ObtainSingleItemFromSetVariableStackSize extends BaseGoalFactory {

    Map<Material, GoalConstants.MinMaxPair> stackSizeMap;
    private final MapSelector<Material, GoalDifficulty> itemSelector;

    protected ObtainSingleItemFromSetVariableStackSize(@NotNull Map<Material, GoalConstants.MinMaxPair> stackSizeMap, @NotNull GoalDifficulty difficulty) {
        this(
                stackSizeMap,
                stackSizeMap.keySet().stream().collect(Collectors.toMap(m -> m, m -> difficulty))
        );
    }

    protected ObtainSingleItemFromSetVariableStackSize(@NotNull Map<Material, GoalConstants.MinMaxPair> stackSizeMap, @NotNull Map<Material, GoalDifficulty> difficultyMap) {
        super();
        if (!stackSizeMap.keySet().containsAll(difficultyMap.keySet())) {
            throw new IllegalArgumentException("stack size map and difficulty map must have the same materials");
        }
        this.stackSizeMap = stackSizeMap;
        this.itemSelector = new MapSelector<>(difficultyMap, Filters.singleItemFilter(this::usedUniquenessStrings), this::isValidDifficulty);
        this.canGenerateMultiple = false;
    }

    @Override
    protected IMutableGoal makeGoalHook() {
        Map.Entry<Material, GoalDifficulty> entry = this.itemSelector.select();
        Material targetMaterial = entry.getKey();
        GoalDifficulty difficulty = entry.getValue();

        int min = stackSizeMap.get(targetMaterial).min;
        int max = stackSizeMap.get(targetMaterial).max;
        int stackSize = LockoutGames.getRng().nextInt(min, max+1);
        IMutableGoal goal = new CollectItemGoal(List.of(targetMaterial), stackSize);
        goal.setDescription("Collect " + stackSize + " " + Utils.readableEnumString(targetMaterial.name()));
        goal.setGoalDifficulty(difficulty);
        goal.addUniquenessStrings(Set.of(UniquenessStrings.collect(targetMaterial)));
        return goal;
    }

    @Override
    protected boolean canGenerateGoalHook() {
        return this.itemSelector.canSelect();
    }
}

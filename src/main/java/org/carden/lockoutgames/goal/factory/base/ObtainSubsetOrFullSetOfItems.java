package org.carden.lockoutgames.goal.factory.base;

import org.bukkit.Material;
import org.carden.lockoutgames.LockoutGames;
import org.carden.lockoutgames.goal.CollectItemGoal;
import org.carden.lockoutgames.goal.GoalDifficulty;
import org.carden.lockoutgames.goal.IMutableGoal;
import org.carden.lockoutgames.goal.factory.selector.SubsetOrFullSetSelector;
import org.carden.lockoutgames.utils.Utils;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class ObtainSubsetOrFullSetOfItems extends BaseGoalFactory {

    protected int stackPercentChance = 40;
    protected int minStackSize = 2;
    protected int maxStackSize = 8;
    protected final String setDescription;

    protected final Map<Material, GoalDifficulty> difficultyMap;

    private final SubsetOrFullSetSelector<Material> itemSelector;
    private final GoalDifficulty fullSetDifficulty;

    protected ObtainSubsetOrFullSetOfItems(
            Set<Material> setOfItems,
            int subsetPercentChance,
            int stackPercentChance,
            int subsetMinSize,
            int subsetMaxSize,
            int minStackSize,
            int maxStackSize,
            String setDescription,
            Map<Material, GoalDifficulty> difficultyMap,
            GoalDifficulty fullSetDifficulty
    ) {
        this.difficultyMap = difficultyMap;
        this.itemSelector = new SubsetOrFullSetSelector<>(
                setOfItems, subsetMinSize, subsetMaxSize, subsetPercentChance,
                (item) -> CollectItem.itemFilter(item) && this.isValidDifficulty(this.difficultyMap.get(item))
                );
        this.stackPercentChance = stackPercentChance;
        this.minStackSize = minStackSize;
        this.maxStackSize = maxStackSize;
        this.setDescription = setDescription;
        this.fullSetDifficulty = fullSetDifficulty;
        this.canGenerateMultiple = false;
    }

    protected ObtainSubsetOrFullSetOfItems(
            Set<Material> setOfItems,
            int subsetPercentChance,
            int stackPercentChance,
            int subsetMinSize,
            int subsetMaxSize,
            int minStackSize,
            int maxStackSize,
            String setDescription,
            GoalDifficulty difficulty
    ) {
        this(
                setOfItems, subsetPercentChance, stackPercentChance, subsetMinSize, subsetMaxSize, minStackSize, maxStackSize, setDescription,
                setOfItems.stream().collect(Collectors.toMap((item) -> item, (item) -> difficulty)),
                difficulty
        );
    }

    protected ObtainSubsetOrFullSetOfItems(
            Set<Material> setOfItems,
            int subsetPercentChance,
            int stackPercentChance,
            int subsetMinSize,
            int subsetMaxSize,
            int minStackSize,
            int maxStackSize,
            String setDescription,
            Map<Material, GoalDifficulty> difficultyMap
    ) {
        this(
                setOfItems, subsetPercentChance, stackPercentChance, subsetMinSize, subsetMaxSize, minStackSize, maxStackSize, setDescription,
                difficultyMap,
                getGoalDifficultyFromMap(setOfItems, difficultyMap)
        );
    }

    private GoalDifficulty getGoalDifficulty(Set<Material> items) {
        if (this.itemSelector.isFullSet(items)) {
            return this.fullSetDifficulty;
        }
        else {
            return getGoalDifficultyFromMap(items, this.difficultyMap);
        }
    }

    private static GoalDifficulty getGoalDifficultyFromMap(Set<Material> items, Map<Material, GoalDifficulty> difficultyMap) {
        return items.stream()
                .map(difficultyMap::get)
                .max(GoalDifficulty::compare).get();
    }

    @Override
    protected IMutableGoal makeGoalHook() {
        Set<Material> selectedItems = this.itemSelector.select();
        GoalDifficulty difficulty = this.getGoalDifficulty(selectedItems);
        int stackSize = this.getStackSize();
        IMutableGoal goal = new CollectItemGoal(List.copyOf(selectedItems), stackSize);
        goal.setGoalDifficulty(difficulty);
        goal.setDescription(this.getDescription(selectedItems, stackSize, this.itemSelector.isFullSet(selectedItems)));
        return goal;
    }

    private int getStackSize() {
        if (Utils.weightedRandomBoolean(this.stackPercentChance)) {
            return 1;
        }
        else {
            Random rng = LockoutGames.getRng();
            return rng.nextInt(this.minStackSize, this.maxStackSize+1);
        }
    }

    private String getDescription(Set<Material> items, int stackSize, boolean isFullSet) {
        if (isFullSet) {
            if(stackSize == 1) {
                return "Obtain one of each " + setDescription;

            } else {
                return "Obtain " + stackSize + " of each " + setDescription;
            }
        }
        else {
            if(stackSize == 1) {
                return "Obtain " +
                        Utils.buildStringList(items.stream().map(m -> Utils.readableEnumString(m.name())).toList(), "and");

            } else {
                return "Obtain " +
                        stackSize +
                        " " +
                        Utils.buildStringList(items.stream().map(m -> Utils.readableEnumString(m.name())).toList(), "and");
            }
        }
    }

    @Override
    protected boolean canGenerateGoalHook() {
        return this.itemSelector.canSelect();
    }
}

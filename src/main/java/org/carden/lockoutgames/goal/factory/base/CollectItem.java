package org.carden.lockoutgames.goal.factory.base;

import org.bukkit.Material;
import org.carden.lockoutgames.LockoutGames;
import org.carden.lockoutgames.goal.CollectItemGoal;
import org.carden.lockoutgames.goal.GoalDifficulty;
import org.carden.lockoutgames.goal.IMutableGoal;
import org.carden.lockoutgames.goal.factory.selector.SingleSelector;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.function.Predicate;

public class CollectItem extends BaseGoalFactory {
    private final SingleSelector<Material> itemSelector;
    private final int minStack;
    private final int maxStack;
    private final Map<Material, GoalDifficulty> difficultyMap;

    protected CollectItem(Map<Material, GoalDifficulty> items, int minStack, int maxStack) {
        final Predicate<Material> itemFilter = CollectItems.singleItemFilter(this::usedUniquenessStrings);
        this.minStack = minStack;
        this.maxStack = maxStack;
        this.difficultyMap = items;
        this.itemSelector = new SingleSelector<>(items.keySet(),
                (item) -> itemFilter.test(item) && this.isValidDifficulty(this.difficultyMap.get(item)));
    }

    protected CollectItem(Material item, GoalDifficulty difficulty, int minStack, int maxStack) {
        this(Map.of(item, difficulty), minStack, maxStack);
    }

    protected CollectItem(Material item, GoalDifficulty difficulty) {
        this(item, difficulty, 1, 1);
    }

    @Override
    protected final IMutableGoal makeGoalHook() {
        Material item = this.itemSelector.select();
        Random rng = LockoutGames.getRng();
        int stackSize = rng.nextInt(minStack, maxStack+1);
        IMutableGoal goal = this.makeCollectItemGoal(item, stackSize);
        goal.setGoalDifficulty(this.difficultyMap.get(item));
        goal.addUniquenessStrings(Set.of(CollectItems.uniquenessString(item)));
        return goal;
    }

    protected IMutableGoal makeCollectItemGoal(Material item, int stackSize) {
        return new CollectItemGoal(List.of(item), stackSize);
    }

    @Override
    protected boolean canGenerateGoalHook() {
        return this.itemSelector.canSelect();
    }
}

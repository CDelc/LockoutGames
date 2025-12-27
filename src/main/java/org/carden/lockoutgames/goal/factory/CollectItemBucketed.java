package org.carden.lockoutgames.goal.factory;

import org.bukkit.Material;
import org.carden.lockoutgames.goal.CollectItemGoal;
import org.carden.lockoutgames.goal.GoalDifficulty;
import org.carden.lockoutgames.goal.IMutableGoal;
import org.carden.lockoutgames.goal.factory.selector.SingleSelector;
import org.carden.lockoutgames.goal.factory.selector.SubsetSelector;

import java.util.*;

public class CollectItemBucketed extends QuantitativeDifficultyGoal {
    private final SingleSelector<Material> itemSelector;

    protected CollectItemBucketed(Set<Material> itemsToCollect, Map<Bucket, GoalDifficulty> bucketDifficulties) {
        super(bucketDifficulties);
        this.itemSelector = new SingleSelector<>(itemsToCollect,
                CollectItem.singleItemFilter(this::usedUniquenessStrings)
        );
    }

    @Override
    protected final IMutableGoal makeGoalForNumber(int value, GoalDifficulty difficulty) {
        return this.makeGoalForItem(this.itemSelector.select(), value, difficulty);
    }

    protected IMutableGoal makeGoalForItem(Material item, int stackSize, GoalDifficulty difficulty) {
        IMutableGoal g = new CollectItemGoal(List.of(item), stackSize);
        g.setGoalDifficulty(difficulty);
        g.addUniquenessStrings(Set.of(CollectItem.uniquenessString(item)));
        return g;
    }

    @Override
    protected boolean canGenerateGoalHook() {
        return super.canGenerateGoalHook() && this.itemSelector.canSelect();
    }
}

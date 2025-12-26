package org.carden.lockoutgames.goal.factory;

import org.bukkit.Material;
import org.carden.lockoutgames.game.setting.SettingsImage;
import org.carden.lockoutgames.goal.CollectItemGoal;
import org.carden.lockoutgames.goal.GoalDifficulty;
import org.carden.lockoutgames.goal.IMutableGoal;

import java.util.*;

public class CollectItemBucketed extends QuantitativeDifficultyGoal {
    private final SubsetSelector<Material> itemSelector;


    protected CollectItemBucketed(Set<Material> itemsToCollect, int minimumItemTypes, int maximumItemTypes, Map<Bucket, GoalDifficulty> bucketDifficulties) {
        super(bucketDifficulties);
        this.itemSelector = new SubsetSelector<>(itemsToCollect, minimumItemTypes, maximumItemTypes, CollectItem::itemFilter);
    }

    @Override
    protected final IMutableGoal makeGoalForNumber(int value, GoalDifficulty difficulty) {
        return this.makeGoalForItems(this.itemSelector.select(), value, difficulty);
    }

    protected IMutableGoal makeGoalForItems(Set<Material> items, int stackSize, GoalDifficulty difficulty) {
        List<Material> itemList = List.copyOf(items);
        IMutableGoal g = new CollectItemGoal(itemList, stackSize);
        g.setGoalDifficulty(difficulty);
        if (itemList.size() == 1) {
            g.addUniquenessStrings(Set.of(CollectItem.uniquenessString(itemList.getFirst())));
        }
        return g;
    }

    @Override
    protected boolean canGenerateGoalHook() {
        return super.canGenerateGoalHook() && this.itemSelector.canSelectSubset();
    }

    private static List<Integer> makeDifficultyBuckets(int minimumStack, int maximumStack, List<Integer> difficultyThresholds) {
        List<Integer> buckets = new ArrayList<>(difficultyThresholds);
        buckets.sort(Integer::compareTo);
        if (buckets.getFirst() <= minimumStack || buckets.getLast() >= maximumStack) {
            throw new IllegalArgumentException("Bucket thresholds are not between min and max stack sizes");
        }
        buckets.addFirst(minimumStack);
        buckets.addLast(maximumStack + 1);
        return buckets;
    }

    private static Map<GoalDifficulty, Integer> makeBucketMap(GoalDifficulty baseDifficulty, int numBuckets) {
        Map<GoalDifficulty, Integer> bucketMap = new HashMap<>();
        GoalDifficulty difficulty = baseDifficulty;
        for (int bucket = 0; bucket < numBuckets; bucket++) {
            if (difficulty == null) {
                throw new IllegalStateException("Too many difficulty buckets");
            }
            bucketMap.put(difficulty, bucket);
            difficulty = difficulty.getNextDifficulty();
        }
        return bucketMap;
    }
}

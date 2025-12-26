package org.carden.lockoutgames.goal.factory;

import org.bukkit.Material;
import org.carden.lockoutgames.LockoutGames;
import org.carden.lockoutgames.goal.CollectItemGoal;
import org.carden.lockoutgames.goal.IMutableGoal;
import org.carden.lockoutgames.goal.factory.selector.SubsetSelector;
import org.carden.lockoutgames.info.WorldRequirements;

import java.util.List;
import java.util.Random;
import java.util.Set;

public abstract class CollectItem extends BaseGoalFactory {

    //Setup fields
    private int minItemsPerStack;
    private int maxItemsPerStack;
    private int minItemsToSelect; //(Minimum) Number of different items from preFilteredItemList that may be selected for this goal
    private int maxItemsToSelect; //(Maximum inclusive)
    private final SubsetSelector<Material> itemSelector;

    /**
     * Constructs this goal with all the given parameters
     */
    protected CollectItem(List<Material> itemsToCollect, int minimumStack, int maximumStack, int minimumItems, int maximumItems) {
        this.minItemsPerStack = minimumStack;
        this.maxItemsPerStack = maximumStack;
        this.minItemsToSelect = minimumItems;
        this.maxItemsToSelect = maximumItems;
        this.itemSelector = new SubsetSelector<>(Set.copyOf(itemsToCollect), minimumItems, maximumItems, CollectItem::itemFilter);
    }

    /**
     * Constructs the goal, guarantees the entire list will be required. The amount of each item will be randomized.
     */
    protected CollectItem(List<Material> itemsToCollect, int minimumStack, int maximumStack) {
        this(itemsToCollect, minimumStack, maximumStack, itemsToCollect.size(), itemsToCollect.size());
    }

    /**
     * Constructs the goal, guarantees the entire list will be required with the provided stacksize per item
     */
    protected CollectItem(List<Material> itemsToCollect, int stackSize) {
        this(itemsToCollect, stackSize, stackSize, itemsToCollect.size(), itemsToCollect.size());
    }

    /**
     * Constructs the goal, guarantees the entire list will be required with only one item of each type required to complete
     */
    protected CollectItem(List<Material> itemsToCollect) {
        this(itemsToCollect, 1, 1, itemsToCollect.size(), itemsToCollect.size());
    }

    /**
     * Constructs the goal, only requires the given material of a randomized amount
     */
    protected CollectItem(Material material, int minimumStack, int maximumStack) {
        this(List.of(material), minimumStack, maximumStack, 1, 1);
    }

    /**
     * Constructs the goal, guarantees only a single instance of the given material is required
     */
    protected CollectItem(Material material) {
        this(List.of(material), 1, 1, 1, 1);
    }

    /**
     * Handles all the randomization for the goal. If fewer items in preFilteredItemList are possible to obtain in
     * GameWorld than minItemsToSelect requires, the goal is flagged as invalid
     * itemsRequiredPerStack is selected here and the final list of items after logic filtering and random selection is determined.
     */
    @Override
    protected IMutableGoal makeGoalHook() {
        return randomizeCollectItemGoal(minItemsPerStack, maxItemsPerStack, minItemsToSelect, maxItemsToSelect);
    }

    protected IMutableGoal randomizeCollectItemGoal(int minItemsPerStack, int maxItemsPerStack, int minItemsToSelect, int maxItemsToSelect) {
        //Filter items down by world logic
        Random rng = LockoutGames.getRng();

        //Randomize values
        int itemsRequiredPerStack = rng.nextInt(Math.max(1, minItemsPerStack), maxItemsPerStack + 1);

        //Select items
        List<Material> requiredItems = List.copyOf(this.itemSelector.select());

        IMutableGoal g = this.makeCollectItemGoal(requiredItems, itemsRequiredPerStack);

        //If this is only for one item, add to uniqueness strings to prevent duplicates
        if(requiredItems.size() == 1) {
            g.addUniquenessStrings(Set.of(uniquenessString(requiredItems.getFirst())));
        }

        return g;
    }

    public static String uniquenessString(Material item) {
        return "collect" + item.name();
    }

    /**
     * Post-randomization constructor. Overridable
     * @param requiredItems
     * @param itemsRequiredPerStack
     * @return
     */
    protected IMutableGoal makeCollectItemGoal(List<Material> requiredItems, int itemsRequiredPerStack) {
        return new CollectItemGoal(requiredItems, itemsRequiredPerStack);
    }

    public static boolean itemFilter(Material item) {
        return WorldRequirements.checkElement(item.name());
    }

    @Override
    protected boolean canGenerateGoalHook() {
        return this.itemSelector.canSelect();
    }
}

package org.carden.lockoutgames.goal;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.carden.lockoutgames.LockoutGames;
import org.carden.lockoutgames.game.player.GamePlayer;
import org.carden.lockoutgames.game.player.PlayerManager;
import org.carden.lockoutgames.info.WorldRequirements;
import org.jetbrains.annotations.NotNull;

import java.util.*;

import static org.carden.lockoutgames.utils.Utils.selectNRandomValuesFromList;

public class CollectItemGoal extends ChecklistGoal<Material> {
    /**
     * Generalized goal for obtaining a single item or a set of items.
     */

    private static final Set<Class<? extends Event>> eventsToCheck = Set.of(
            EntityPickupItemEvent.class,
            InventoryClickEvent.class
    );

    //Setup fields
    private List<Material> preFilteredItemList; //List of items that may be asked of the player. Will be filtered down.
    private int minItemsPerStack;
    private int maxItemsPerStack;
    private int minItemsToSelect; //(Minimum) Number of different items from preFilteredItemList that may be selected for this goal
    private int maxItemsToSelect; //(Maximum inclusive)

    //Total number of items required of each type (This is randomized between minItemsPerStack and maxItemsPerStack inclusively)
    protected int itemsRequiredPerStack;

    /**
     * Handles all the randomization for the goal. If fewer items in preFilteredItemList are possible to obtain in
     * GameWorld than minItemsToSelect requires, the goal is flagged as invalid
     * itemsRequiredPerStack is selected here and the final list of items after logic filtering and random selection is determined.
     */
    private void setupGoal() {
        this.failedToGenerate = false;
        //Filter items down by world logic
        List<Material> filteredItemList = new ArrayList<>(filterItems(preFilteredItemList));
        Random rng = LockoutGames.getRng();
        maxItemsToSelect = Math.min(maxItemsToSelect, filteredItemList.size());
        minItemsToSelect = Math.max(1, minItemsToSelect);
        if(filteredItemList.size() < minItemsToSelect) {
            this.failedToGenerate = true;
            return;
        }

        //Randomize values
        this.itemsRequiredPerStack = rng.nextInt(Math.max(1, minItemsPerStack), maxItemsPerStack + 1);
        int uniqueItemsRequired = rng.nextInt(minItemsToSelect, maxItemsToSelect + 1);

        //Select items
        requiredItems = selectNRandomValuesFromList(filteredItemList, uniqueItemsRequired, LockoutGames.getRng());

        //If this is only for one item, add to uniqueness strings to prevent duplicates
        if(requiredItems.size() == 1) {
            this.uniquenessStrings.add("collect" + requiredItems.getFirst().name());
        }
    }

    /**
     * Private utility function to remove all the items out of logic for this world from a list of items
     */
    private List<Material> filterItems(List<Material> itemList) {
        return itemList.stream().filter(item ->
            WorldRequirements.checkElement(item.name())
        ).toList();
    }

    /**
     * Private utility function that checks whether a player has a certain amount of a material in inventory
     * @param p - The player to check
     * @param m - The material to look for
     * @param amount - The amount to look for
     * @return true if the player has the specified amount of the material in inventory
     */
    private boolean checkInventoryForItem(@NotNull Player p, @NotNull Material m, int amount) {
        Inventory inv = p.getInventory();
        return inv.contains(m, amount);
    }

    /**
     * Invokes checkFromChecklist if the player has one of the checklist items in inventory.
     * @param e - The event triggering the check
     */
    @Override
    public void checkEvent(Event e) {
        Player p = getPlayerFromCollectEvent(e);
        if(p == null) return;
        GamePlayer gamePlayer = PlayerManager.getPlayerManager().getGamePlayerObject(p.getUniqueId());
        for(Material m : requiredItems) {
            if(checkInventoryForItem(p, m, itemsRequiredPerStack)) {
                checkFromChecklist(gamePlayer, m);
            }
        }
    }

    /**
     * failedToGenerate is true by default because a construct() method must be called before this goal is usable
     */
    public CollectItemGoal(List<Material> requiredItems, int itemsRequiredPerStack) {
        super(eventsToCheck);
        this.failedToGenerate = true;
    }

    /**
     * Constructs this goal with all the given parameters
     */
    protected void construct(List<Material> itemsToCollect, int minimumStack, int maximumStack, int minimumItems, int maximumItems) {
        this.preFilteredItemList = itemsToCollect;
        this.minItemsPerStack = minimumStack;
        this.maxItemsPerStack = maximumStack;
        this.minItemsToSelect = minimumItems;
        this.maxItemsToSelect = maximumItems;
        setupGoal();
    }

    /**
     * Constructs the goal, guarantees the entire list will be required. The amount of each item will be randomized.
     */
    protected void construct(List<Material> itemsToCollect, int minimumStack, int maximumStack) {
        this.preFilteredItemList = itemsToCollect;
        this.minItemsPerStack = minimumStack;
        this.maxItemsPerStack = maximumStack;
        this.minItemsToSelect = itemsToCollect.size();
        this.maxItemsToSelect = itemsToCollect.size();
        setupGoal();
    }

    /**
     * Constructs the goal, guarantees the entire list will be required with the provided stacksize per item
     */
    protected void construct(List<Material> itemsToCollect, int stackSize) {
        this.preFilteredItemList = itemsToCollect;
        this.minItemsPerStack = stackSize;
        this.maxItemsPerStack = stackSize;
        this.minItemsToSelect = itemsToCollect.size();
        this.maxItemsToSelect = itemsToCollect.size();
        setupGoal();
    }

    /**
     * Constructs the goal, guarantees the entire list will be required with only one item of each type required to complete
     */
    protected void construct(List<Material> itemsToCollect) {
        this.preFilteredItemList = itemsToCollect;
        this.minItemsPerStack = 1;
        this.maxItemsPerStack = 1;
        this.minItemsToSelect = itemsToCollect.size();
        this.maxItemsToSelect = itemsToCollect.size();
        setupGoal();
    }

    /**
     * Constructs the goal, only requires the given material of a randomized amount
     */
    protected void construct(Material material, int minimumStack, int maximumStack) {
        this.preFilteredItemList = List.of(material);
        this.minItemsPerStack = minimumStack;
        this.maxItemsPerStack = maximumStack;
        this.minItemsToSelect = 1;
        this.maxItemsToSelect = 1;
        setupGoal();
    }

    /**
     * Constructs the goal, guarantees only a single instance of the given material is required
     */
    protected void construct(Material material) {
        this.preFilteredItemList = List.of(material);
        this.minItemsPerStack = 1;
        this.maxItemsPerStack = 1;
        this.minItemsToSelect = 1;
        this.maxItemsToSelect = 1;
        setupGoal();
    }

    /**
     * Utility method to extract a Player object from either an InventoryClickEvent or an EntityPickupItemEvent
     */
    protected static Player getPlayerFromCollectEvent(Event e) {
        Player p;
        if(e instanceof InventoryClickEvent && ((InventoryClickEvent) e).getWhoClicked() instanceof Player) {
            p = (Player) ((InventoryClickEvent) e).getWhoClicked();
        }
        else if(e instanceof EntityPickupItemEvent && ((EntityPickupItemEvent) e).getEntity() instanceof Player) {
            p = (Player) ((EntityPickupItemEvent) e).getEntity();
        } else return null;
        return p;
    }
}

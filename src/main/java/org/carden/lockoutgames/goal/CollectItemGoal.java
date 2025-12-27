package org.carden.lockoutgames.goal;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.carden.lockoutgames.game.player.GamePlayer;
import org.carden.lockoutgames.game.player.PlayerManager;
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

    //Total number of items required of each type (This is randomized between minItemsPerStack and maxItemsPerStack inclusively)
    protected int itemsRequiredPerStack;

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
        this.requiredItems = requiredItems;
        this.itemsRequiredPerStack = itemsRequiredPerStack;
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

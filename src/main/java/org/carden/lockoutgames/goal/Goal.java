package org.carden.lockoutgames.goal;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;
import java.util.function.Function;

public class Goal {


    private final Function<Player, Boolean> checkFunction;
    private final ItemStack displayItem;
    private final String description;
    private final GoalType type;
    private final UUID uuid;

    protected Goal(Function<Player, Boolean> checkFunction, ItemStack displayItem, String description, GoalType type, UUID identifier) {
        this.checkFunction = checkFunction;
        this.displayItem = displayItem;
        this.description = description;
        this.type = type;
        this.uuid = identifier;
    }

    public boolean check(Player p) {
        return checkFunction.apply(p);
    }

    public ItemStack getDisplayItem() {
        return this.displayItem;
    }

    public String getDescription() {
        return this.getDescription();
    }

    public GoalType getGoalType() {
        return this.type;
    }

    public String getDescriptionShort() {
        return "";
    }

}

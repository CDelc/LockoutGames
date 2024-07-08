package org.carden.lockoutgames.goal;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.plugin.Plugin;
import org.carden.lockoutgames.events.GoalObtainedEvent;
import org.carden.lockoutgames.game.GameWorld;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

enum rngSettings {
    /**
     * This should make organizing randomization settings easier.
     * Structure is as follows:
     * [Minimum items required,
     * Exclusive maximum items required,
     * Minimum different items from set required,
     * Exclusive maximum different items from set required]
     */

    SINGLE(new int[]{1, 2, 1, 2});

    final int[] settings;

    rngSettings(int[] settings) {
        this.settings = settings;
    }
}

public enum CollectGoal implements Goal {

    /**
     * These are goals that involve obtaining an item or a set of items.
     */

    OBTAIN_CRAFTINGTABLE(new Material[]{Material.CRAFTING_TABLE}, Option.AND, rngSettings.SINGLE, 0),
    OBTAIN_PICKAXE(new Material[]{Material.WOODEN_PICKAXE, Material.STONE_PICKAXE, Material.IRON_PICKAXE, Material.GOLDEN_PICKAXE, Material.DIAMOND_PICKAXE, Material.NETHERITE_PICKAXE},
            Option.OR, rngSettings.SINGLE, 0),
    OBTAIN_SWORD(new Material[]{Material.WOODEN_SWORD, Material.STONE_SWORD, Material.IRON_SWORD, Material.GOLDEN_SWORD, Material.DIAMOND_SWORD, Material.NETHERITE_SWORD},
            Option.OR, rngSettings.SINGLE, 0),
    OBTAIN_HOE(new Material[]{Material.WOODEN_HOE, Material.STONE_HOE, Material.IRON_HOE, Material.GOLDEN_HOE, Material.DIAMOND_HOE, Material.NETHERITE_HOE},
            Option.OR, rngSettings.SINGLE, 0),
    OBTAIN_SHOVEL(new Material[]{Material.WOODEN_SHOVEL, Material.STONE_SHOVEL, Material.IRON_SHOVEL, Material.GOLDEN_SHOVEL, Material.DIAMOND_SHOVEL, Material.NETHERITE_SHOVEL},
            Option.OR, rngSettings.SINGLE, 0);


    public final ArrayList<Material> items;
    public final Integer amount;
    public final Option option;
    public final Integer difficulty;

    /**
     *
     * @param target The set of items that the goal will require (or will generate a subset from)
     * @param option AND: All items in the goal set must be collected. OR: Collecting any item completes the goal.
     * @param rngSettings Settings enum described above
     * @param difficulty Difficulty rating of this goal
     */
    CollectGoal(Material[] target, Option option, rngSettings rngSettings, Integer difficulty) {
        Random rng = new Random();
        int[] settings = rngSettings.settings;
        int numItems = rng.nextInt(settings[0], settings[1]);
        ArrayList<Material> tmpItems = new ArrayList<Material>();

        Set<Integer> chosenIndices = new HashSet<>();
        while(chosenIndices.size() < numItems) {
            chosenIndices.add(rng.nextInt(0, target.length));
        }
        for (int index : chosenIndices) {
            tmpItems.add(target[index]);
        }
        this.items = tmpItems;
        this.amount = rng.nextInt(settings[2], settings[3]);
        this.option = option;
        this.difficulty = difficulty;
    }


    @Override
    public void validate(Player p, Plugin plugin) {
        Inventory inv = p.getInventory();

        if(this.option == Option.AND) {
            for(Material m : items) {
                if (!inv.contains(m, amount)) {
                    return;
                }
            }
            plugin.getServer().getPluginManager().callEvent(new GoalObtainedEvent(p, this));
        }
        else if(this.option == Option.OR) {
            for(Material m : items) {
                if (inv.contains(m, amount)) {
                    plugin.getServer().getPluginManager().callEvent(new GoalObtainedEvent(p, this));
                }
            }
        }
    }

    @Override
    public String getDescription() {
        //Special Cases


        if(items.size() == 1) {
            return "Collect" + amount + " " + items.get(0).name().toLowerCase();
        }
        else {
            String rValue = "Collect " + amount;
            for(int i = 0; i < items.size(); i++) {
                if(i < items.size() - 1) {
                    rValue += " " + this.option.name().toLowerCase();
                }
                rValue += " " + items.get(1).name().toLowerCase();
                if(i < items.size() - 1) {
                    rValue += ",";
                }
            }
            return rValue;
        }
    }

    @Override
    public ItemStack displayItem() {
        return new ItemStack(items.get((new Random().nextInt(0, items.size()))), amount);
    }

    @Override
    public boolean canGenerate(GameWorld world) {
        return true;
    }
}



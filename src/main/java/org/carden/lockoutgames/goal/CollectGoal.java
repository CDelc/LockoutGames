package org.carden.lockoutgames.goal;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.plugin.Plugin;
import org.carden.lockoutgames.events.GoalObtainedEvent;
import org.carden.lockoutgames.game.GameWorld;

import java.util.*;

enum rngSettings {
    /**
     * This should make organizing randomization settings easier.
     * Structure is as follows:
     * [Minimum items required,
     * Exclusive maximum items required,
     * Minimum different items from set required,
     * Exclusive maximum different items from set required]
     */

    SINGLE(new int[]{1, 2, 1, 2}),
    DOUBLE(new int[]{1, 2, 2, 3}),
    COBBLESTONE(new int[]{32, 65, 1, 2}),
    FLINT(new int[]{1, 9, 1, 2}),
    GLOW_LICHEN(new int[]{1, 13, 1, 2}),
    DEEPSLATE(new int[]{16, 49, 1, 2}),
    ALLTOOLS(new int[]{1, 2, 1, 7}),
    TOOLSET(new int[]{1, 2, 1, 6}),
    OREBLOCK(new int[]{12, 25, 1, 2});

    final int[] settings;

    rngSettings(int[] settings) {
        this.settings = settings;
    }
}

public enum CollectGoal implements Goal {

    /**
     * These are goals that involve obtaining an item or a set of items.
     */

    CRAFTINGTABLE(new Material[]{Material.CRAFTING_TABLE}, Option.OR, rngSettings.SINGLE, 0),
    PICKAXE(new Material[]{Material.WOODEN_PICKAXE, Material.STONE_PICKAXE, Material.IRON_PICKAXE, Material.GOLDEN_PICKAXE, Material.DIAMOND_PICKAXE, Material.NETHERITE_PICKAXE},
            Option.OR, rngSettings.ALLTOOLS, 0),
    SWORD(new Material[]{Material.WOODEN_SWORD, Material.STONE_SWORD, Material.IRON_SWORD, Material.GOLDEN_SWORD, Material.DIAMOND_SWORD, Material.NETHERITE_SWORD},
            Option.OR, rngSettings.ALLTOOLS, 0),
    HOE(new Material[]{Material.WOODEN_HOE, Material.STONE_HOE, Material.IRON_HOE, Material.GOLDEN_HOE, Material.DIAMOND_HOE, Material.NETHERITE_HOE},
            Option.OR, rngSettings.ALLTOOLS, 0),
    SHOVEL(new Material[]{Material.WOODEN_SHOVEL, Material.STONE_SHOVEL, Material.IRON_SHOVEL, Material.GOLDEN_SHOVEL, Material.DIAMOND_SHOVEL, Material.NETHERITE_SHOVEL},
            Option.OR, rngSettings.ALLTOOLS, 0),
    COBBLESTONE(new Material[]{Material.COBBLESTONE}, Option.OR, rngSettings.COBBLESTONE, 0),
    SPOREBLOSSOM(new Material[]{Material.SPORE_BLOSSOM}, Option.OR, rngSettings.SINGLE, 1),
    AZALEA(new Material[]{Material.FLOWERING_AZALEA, Material.AZALEA}, Option.OR, rngSettings.DOUBLE, 1),
    FLINT(new Material[]{Material.FLINT}, Option.OR, rngSettings.FLINT, 1),
    GLOWLICHEN(new Material[]{Material.GLOW_LICHEN}, Option.OR, rngSettings.GLOW_LICHEN, 1),
    DEEPSLATE(new Material[]{Material.DEEPSLATE}, Option.OR, rngSettings.DEEPSLATE, 1),
    STONETOOLS(new Material[]{Material.STONE_PICKAXE, Material.STONE_AXE, Material.STONE_HOE, Material.STONE_SHOVEL, Material.STONE_SWORD}, Option.AND, rngSettings.TOOLSET, 0),
    IRONTOOLS(new Material[]{Material.IRON_PICKAXE, Material.IRON_AXE, Material.IRON_HOE, Material.IRON_SHOVEL, Material.IRON_SWORD}, Option.AND, rngSettings.TOOLSET, 1),
    DIAMONDTOOLS(new Material[]{Material.DIAMOND_PICKAXE, Material.DIAMOND_AXE, Material.DIAMOND_HOE, Material.DIAMOND_SHOVEL, Material.DIAMOND_SWORD}, Option.AND, rngSettings.TOOLSET, 3),
    COAL(new Material[]{Material.COAL}, Option.OR, rngSettings.DEEPSLATE, 1),
    COALBLOCK(new Material[]{Material.COAL_BLOCK}, Option.OR, rngSettings.OREBLOCK, 2),
    FURNACE(new Material[]{Material.FURNACE}, Option.OR, rngSettings.SINGLE, 0),
    CHARCOAL(new Material[]{Material.CHARCOAL}, Option.OR, rngSettings.DEEPSLATE, 1);


    public final ArrayList<Material> items;
    public final Integer amount;
    public final Option option;
    public final Integer difficulty;
    public final PlayerChecklist<Material> checklist;

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
        this.checklist = new PlayerChecklist<Material>(this.items);
    }


    @Override
    public void validate(Player p, Plugin plugin) {
        Inventory inv = p.getInventory();

        boolean isComplete;
        for(Material m : items) {
            if (inv.contains(m, amount)) {
                if (option == Option.OR || checklist.checkItem(p, m)) {
                    plugin.getServer().getPluginManager().callEvent(new GoalObtainedEvent(p, this));
                    return;
                }
            }
        }
    }

    @Override
    public String getDescription() {
        //Special Cases
        switch(this) {
            case HOE:
                return "Collect any hoe";
            case PICKAXE:
                return "Collect any pickaxe";
            case SHOVEL:
                return "Collect any shovel";
            case SWORD:
                return "Collect any sword";
            case STONETOOLS:
                return "Collect a full stone toolset";
            case IRONTOOLS:
                return "Collect a full iron toolset";
            case DIAMONDTOOLS:
                return "Collect a full diamond toolset";
        }

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



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
     * OPTIONAL: Minimum different items from set required (Default all items),
     * OPTIONAL: Exclusive maximum different items from set required] (Default all items)
     */

    SINGLE(new int[]{1, 2}),
    COBBLESTONE(new int[]{64, 129}),
    DIAMOND(new int[]{3, 9}),
    GLOW_LICHEN(new int[]{1, 13}),
    INGOT(new int[]{32, 65}),
    ORE_BLOCK(new int[]{8, 25}),
    MULTI_STACK(new int[]{64*2, 64*4+1}),
    DIAMOND_BLOCK(new int[]{2, 5}),
    SMALL_SUBSET(new int[]{1, 2, 2, 7}),
    LARGE_SUBSET(new int[]{1, 2, 6, 13}),
    PICK_ONE(new int[]{1, 2, 1, 2});
    final int[] settings;

    rngSettings(int[] settings) {
        this.settings = settings;
    }
}

public enum CollectGoal implements Goal {

    /**
     * These are goals that involve obtaining an item or a set of items.
     */

    CRAFTING_TABLE(new Material[]{Material.CRAFTING_TABLE}, Option.OR, rngSettings.SINGLE, 0),
    PICKAXE(new Material[]{Material.WOODEN_PICKAXE, Material.STONE_PICKAXE, Material.IRON_PICKAXE, Material.GOLDEN_PICKAXE, Material.DIAMOND_PICKAXE, Material.NETHERITE_PICKAXE},
            Option.OR, rngSettings.SINGLE, 0),
    SWORD(new Material[]{Material.WOODEN_SWORD, Material.STONE_SWORD, Material.IRON_SWORD, Material.GOLDEN_SWORD, Material.DIAMOND_SWORD, Material.NETHERITE_SWORD},
            Option.OR, rngSettings.SINGLE, 0),
    HOE(new Material[]{Material.WOODEN_HOE, Material.STONE_HOE, Material.IRON_HOE, Material.GOLDEN_HOE, Material.DIAMOND_HOE, Material.NETHERITE_HOE},
            Option.OR, rngSettings.SINGLE, 0),
    SHOVEL(new Material[]{Material.WOODEN_SHOVEL, Material.STONE_SHOVEL, Material.IRON_SHOVEL, Material.GOLDEN_SHOVEL, Material.DIAMOND_SHOVEL, Material.NETHERITE_SHOVEL},
            Option.OR, rngSettings.SINGLE, 0),
    COBBLESTONE(new Material[]{Material.COBBLESTONE}, Option.OR, rngSettings.COBBLESTONE, 0),
    SPORE_BLOSSOM(new Material[]{Material.SPORE_BLOSSOM}, Option.OR, rngSettings.SINGLE, 1),
    AZALEA(new Material[]{Material.FLOWERING_AZALEA, Material.AZALEA}, Option.OR, rngSettings.SINGLE, 1),
    FLINT(new Material[]{Material.FLINT}, Option.OR, rngSettings.DIAMOND, 1),
    GLOW_LICHEN(new Material[]{Material.GLOW_LICHEN}, Option.OR, rngSettings.GLOW_LICHEN, 1),
    DEEPSLATE(new Material[]{Material.DEEPSLATE}, Option.OR, rngSettings.INGOT, 1),
    STONE_TOOLS(new Material[]{Material.STONE_PICKAXE, Material.STONE_AXE, Material.STONE_HOE, Material.STONE_SHOVEL, Material.STONE_SWORD}, Option.AND, rngSettings.SINGLE, 0),
    IRON_TOOLS(new Material[]{Material.IRON_PICKAXE, Material.IRON_AXE, Material.IRON_HOE, Material.IRON_SHOVEL, Material.IRON_SWORD}, Option.AND, rngSettings.SINGLE, 1),
    DIAMOND_TOOLS(new Material[]{Material.DIAMOND_PICKAXE, Material.DIAMOND_AXE, Material.DIAMOND_HOE, Material.DIAMOND_SHOVEL, Material.DIAMOND_SWORD}, Option.AND, rngSettings.SINGLE, 3),
    COAL(new Material[]{Material.COAL}, Option.OR, rngSettings.COBBLESTONE, 1),
    COAL_BLOCK(new Material[]{Material.COAL_BLOCK}, Option.OR, rngSettings.ORE_BLOCK, 2),
    FURNACE(new Material[]{Material.FURNACE}, Option.OR, rngSettings.SINGLE, 0),
    CHARCOAL(new Material[]{Material.CHARCOAL}, Option.OR, rngSettings.INGOT, 1),
    SMOKER(new Material[]{Material.SMOKER}, Option.OR, rngSettings.SINGLE, 0),
    COPPER(new Material[]{Material.COPPER_INGOT}, Option.OR, rngSettings.INGOT, 1),
    COPPER_BLOCK(new Material[]{Material.COPPER_BLOCK}, Option.OR, rngSettings.ORE_BLOCK, 2),
    LIGHTNING_ROD(new Material[]{Material.LIGHTNING_ROD}, Option.OR, rngSettings.SINGLE, 1),
    OXIDIZED_COPPER(new Material[]{Material.OXIDIZED_COPPER}, Option.OR, rngSettings.SINGLE, 2),
    IRON_INGOT(new Material[]{Material.IRON_INGOT}, Option.OR, rngSettings.INGOT, 1),
    IRON_NUGGET(new Material[]{Material.IRON_NUGGET}, Option.OR, rngSettings.MULTI_STACK, 1),
    IRON_BLOCK(new Material[]{Material.IRON_BLOCK}, Option.OR, rngSettings.ORE_BLOCK, 2),
    GOLD_INGOT(new Material[]{Material.GOLD_INGOT}, Option.OR, rngSettings.INGOT, 1),
    GOLD_BLOCK(new Material[]{Material.GOLD_BLOCK}, Option.OR, rngSettings.ORE_BLOCK, 2),
    LAPIS(new Material[]{Material.LAPIS_LAZULI}, Option.OR, rngSettings.INGOT, 1),
    LAPIS_BLOCK(new Material[]{Material.LAPIS_BLOCK}, Option.OR, rngSettings.ORE_BLOCK, 2),
    REDSTONE(new Material[]{Material.REDSTONE}, Option.OR, rngSettings.INGOT, 1),
    REDSTONE_BLOCK(new Material[]{Material.REDSTONE_BLOCK}, Option.OR, rngSettings.ORE_BLOCK, 2),
    COMPASS(new Material[]{Material.COMPASS}, Option.OR, rngSettings.SINGLE, 1),
    CLOCK(new Material[]{Material.CLOCK}, Option.OR, rngSettings.SINGLE, 1),
    AMETHYST_SHARD(new Material[]{Material.AMETHYST_SHARD}, Option.OR, rngSettings.SINGLE, 1),
    CALCITE(new Material[]{Material.CALCITE}, Option.OR, rngSettings.DIAMOND, 1),
    SPYGLASS(new Material[]{Material.SPYGLASS}, Option.OR, rngSettings.SINGLE, 1),
    TINTED_GLASS(new Material[]{Material.TINTED_GLASS}, Option.OR, rngSettings.SINGLE, 1),
    AMETHYST_BLOCK(new Material[]{Material.AMETHYST_BLOCK}, Option.OR, rngSettings.ORE_BLOCK, 1),
    DIAMOND(new Material[]{Material.DIAMOND}, Option.OR, rngSettings.DIAMOND, 2),
    DIAMOND_PICKAXE(new Material[]{Material.DIAMOND_PICKAXE}, Option.OR, rngSettings.SINGLE, 2),
    ALL_ORE_BLOCKS(new Material[]{Material.COAL_BLOCK, Material.COPPER_BLOCK, Material.IRON_BLOCK, Material.GOLD_BLOCK, Material.REDSTONE_BLOCK, Material.LAPIS_BLOCK, Material.DIAMOND_BLOCK, Material.EMERALD_BLOCK},
            Option.AND, rngSettings.SINGLE, 3),
    DIAMOND_BLOCK(new Material[]{Material.DIAMOND_BLOCK}, Option.OR, rngSettings.DIAMOND_BLOCK, 3),
    EMERALD(new Material[]{Material.EMERALD}, Option.OR, rngSettings.COBBLESTONE, 2),
    LAVA_BUCKET(new Material[]{Material.LAVA_BUCKET}, Option.OR, rngSettings.SINGLE, 1),
    OBSIDIAN(new Material[]{Material.OBSIDIAN}, Option.OR, rngSettings.ORE_BLOCK, 2),
    ENCHANTING_TABLE(new Material[]{Material.ENCHANTING_TABLE}, Option.OR, rngSettings.SINGLE, 2),
    DOOR(new Material[]{
            Material.OAK_DOOR,
            Material.SPRUCE_DOOR,
            Material.BIRCH_DOOR,
            Material.JUNGLE_DOOR,
            Material.ACACIA_DOOR,
            Material.DARK_OAK_DOOR,
            Material.MANGROVE_DOOR,
            Material.CHERRY_DOOR,
            Material.BAMBOO_DOOR,
            Material.CRIMSON_DOOR,
            Material.WARPED_DOOR,
            Material.IRON_DOOR,
            Material.COPPER_DOOR},
            Option.AND, rngSettings.SMALL_SUBSET, 2),
    SLAB(new Material[]{
            Material.OAK_SLAB,
            Material.SPRUCE_SLAB,
            Material.BIRCH_SLAB,
            Material.JUNGLE_SLAB,
            Material.ACACIA_SLAB,
            Material.DARK_OAK_SLAB,
            Material.MANGROVE_SLAB,
            Material.CHERRY_SLAB,
            Material.BAMBOO_SLAB,
            Material.CRIMSON_SLAB,
            Material.WARPED_SLAB,
            Material.BAMBOO_MOSAIC_SLAB,
            Material.STONE_SLAB,
            Material.COBBLESTONE_SLAB,
            Material.MOSSY_COBBLESTONE_SLAB,
            Material.SMOOTH_STONE_SLAB,
            Material.STONE_BRICK_SLAB,
            Material.MOSSY_STONE_BRICK_SLAB,
            Material.GRANITE_SLAB,
            Material.POLISHED_GRANITE_SLAB,
            Material.DIORITE_SLAB,
            Material.POLISHED_DIORITE_SLAB,
            Material.ANDESITE_SLAB,
            Material.POLISHED_ANDESITE_SLAB,
            Material.COBBLED_DEEPSLATE_SLAB,
            Material.POLISHED_DEEPSLATE_SLAB,
            Material.DEEPSLATE_BRICK_SLAB,
            Material.DEEPSLATE_TILE_SLAB,
            Material.TUFF_SLAB,
            Material.POLISHED_TUFF_SLAB,
            Material.TUFF_BRICK_SLAB,
            Material.BRICK_SLAB,
            Material.MUD_BRICK_SLAB,
            Material.SANDSTONE_SLAB,
            Material.SMOOTH_SANDSTONE_SLAB,
            Material.CUT_SANDSTONE_SLAB,
            Material.RED_SANDSTONE_SLAB,
            Material.SMOOTH_RED_SANDSTONE_SLAB,
            Material.CUT_RED_SANDSTONE_SLAB,
            Material.PRISMARINE_SLAB,
            Material.PRISMARINE_BRICK_SLAB,
            Material.DARK_PRISMARINE_SLAB,
            Material.NETHER_BRICK_SLAB,
            Material.RED_NETHER_BRICK_SLAB,
            Material.BLACKSTONE_SLAB,
            Material.POLISHED_BLACKSTONE_SLAB,
            Material.POLISHED_BLACKSTONE_BRICK_SLAB,
            Material.QUARTZ_SLAB,
            Material.SMOOTH_QUARTZ_SLAB,
            Material.CUT_COPPER_SLAB},
            Option.AND, rngSettings.LARGE_SUBSET, 2),
    STAIRS(new Material[]{
            Material.OAK_STAIRS,
            Material.SPRUCE_STAIRS,
            Material.BIRCH_STAIRS,
            Material.JUNGLE_STAIRS,
            Material.ACACIA_STAIRS,
            Material.DARK_OAK_STAIRS,
            Material.MANGROVE_STAIRS,
            Material.CHERRY_STAIRS,
            Material.BAMBOO_STAIRS,
            Material.CRIMSON_STAIRS,
            Material.WARPED_STAIRS,
            Material.BAMBOO_MOSAIC_STAIRS,
            Material.STONE_STAIRS,
            Material.COBBLESTONE_STAIRS,
            Material.MOSSY_COBBLESTONE_STAIRS,
            Material.STONE_BRICK_STAIRS,
            Material.MOSSY_STONE_BRICK_STAIRS,
            Material.GRANITE_STAIRS,
            Material.POLISHED_GRANITE_STAIRS,
            Material.DIORITE_STAIRS,
            Material.POLISHED_DIORITE_STAIRS,
            Material.ANDESITE_STAIRS,
            Material.POLISHED_ANDESITE_STAIRS,
            Material.COBBLED_DEEPSLATE_STAIRS,
            Material.POLISHED_DEEPSLATE_STAIRS,
            Material.DEEPSLATE_BRICK_STAIRS,
            Material.DEEPSLATE_TILE_STAIRS,
            Material.TUFF_STAIRS,
            Material.POLISHED_TUFF_STAIRS,
            Material.TUFF_BRICK_STAIRS,
            Material.BRICK_STAIRS,
            Material.MUD_BRICK_STAIRS,
            Material.SANDSTONE_STAIRS,
            Material.SMOOTH_SANDSTONE_STAIRS,
            Material.RED_SANDSTONE_STAIRS,
            Material.SMOOTH_RED_SANDSTONE_STAIRS,
            Material.PRISMARINE_STAIRS,
            Material.PRISMARINE_BRICK_STAIRS,
            Material.DARK_PRISMARINE_STAIRS,
            Material.NETHER_BRICK_STAIRS,
            Material.RED_NETHER_BRICK_STAIRS,
            Material.BLACKSTONE_STAIRS,
            Material.POLISHED_BLACKSTONE_STAIRS,
            Material.POLISHED_BLACKSTONE_BRICK_STAIRS,
            Material.QUARTZ_STAIRS,
            Material.SMOOTH_QUARTZ_STAIRS,
            Material.CUT_COPPER_STAIRS},
            Option.AND, rngSettings.LARGE_SUBSET, 2),
    BED(new Material[]{
            Material.BLACK_BED,
            Material.BLUE_BED,
            Material.BROWN_BED,
            Material.GREEN_BED,
            Material.CYAN_BED,
            Material.GRAY_BED,
            Material.LIGHT_BLUE_BED,
            Material.LIGHT_GRAY_BED,
            Material.LIME_BED,
            Material.MAGENTA_BED,
            Material.ORANGE_BED,
            Material.PINK_BED,
            Material.PURPLE_BED,
            Material.RED_BED,
            Material.WHITE_BED,
            Material.YELLOW_BED},
            Option.AND, rngSettings.SMALL_SUBSET, 1),
    WALL(new Material[]{
            Material.COBBLESTONE_WALL,
            Material.MOSSY_COBBLESTONE_WALL,
            Material.STONE_BRICK_WALL,
            Material.MOSSY_STONE_BRICK_WALL,
            Material.GRANITE_WALL,
            Material.DIORITE_WALL,
            Material.ANDESITE_WALL,
            Material.COBBLED_DEEPSLATE_WALL,
            Material.POLISHED_DEEPSLATE_WALL,
            Material.DEEPSLATE_BRICK_WALL,
            Material.DEEPSLATE_TILE_WALL,
            Material.TUFF_WALL,
            Material.POLISHED_TUFF_WALL,
            Material.TUFF_BRICK_WALL,
            Material.BRICK_WALL,
            Material.MUD_BRICK_WALL,
            Material.SANDSTONE_WALL,
            Material.RED_SANDSTONE_WALL,
            Material.PRISMARINE_WALL,
            Material.NETHER_BRICK_WALL,
            Material.RED_NETHER_BRICK_WALL,
            Material.BLACKSTONE_WALL,
            Material.POLISHED_BLACKSTONE_WALL,
            Material.POLISHED_BLACKSTONE_BRICK_WALL},
            Option.AND, rngSettings.LARGE_SUBSET, 2),
    FENCE(new Material[]{
            Material.OAK_FENCE,
            Material.SPRUCE_FENCE,
            Material.BIRCH_FENCE,
            Material.JUNGLE_FENCE,
            Material.ACACIA_FENCE,
            Material.DARK_OAK_FENCE,
            Material.MANGROVE_FENCE,
            Material.CHERRY_FENCE,
            Material.BAMBOO_FENCE,
            Material.CRIMSON_FENCE,
            Material.WARPED_FENCE,
            Material.NETHER_BRICK_FENCE},
            Option.AND, rngSettings.SMALL_SUBSET, 2),
    TRAPDOOR(new Material[]{
            Material.OAK_TRAPDOOR,
            Material.SPRUCE_TRAPDOOR,
            Material.BIRCH_TRAPDOOR,
            Material.JUNGLE_TRAPDOOR,
            Material.ACACIA_TRAPDOOR,
            Material.DARK_OAK_TRAPDOOR,
            Material.MANGROVE_TRAPDOOR,
            Material.CHERRY_TRAPDOOR,
            Material.BAMBOO_TRAPDOOR,
            Material.CRIMSON_TRAPDOOR,
            Material.WARPED_TRAPDOOR,
            Material.IRON_TRAPDOOR,
            Material.COPPER_TRAPDOOR,},
            Option.AND, rngSettings.SMALL_SUBSET, 1),
    CHEST(new Material[]{Material.CHEST}, Option.OR, rngSettings.SINGLE, 0),
    BARREL(new Material[]{Material.BARREL}, Option.OR, rngSettings.SINGLE, 0),
    LECTERN(new Material[]{Material.LECTERN}, Option.OR, rngSettings.SINGLE, 1),
    SIGN(new Material[]{
            Material.OAK_SIGN,
            Material.SPRUCE_SIGN,
            Material.BIRCH_SIGN,
            Material.JUNGLE_SIGN,
            Material.ACACIA_SIGN,
            Material.DARK_OAK_SIGN,
            Material.MANGROVE_SIGN,
            Material.CHERRY_SIGN,
            Material.BAMBOO_SIGN,
            Material.CRIMSON_SIGN,
            Material.WARPED_SIGN},
            Option.AND, rngSettings.SMALL_SUBSET, 1),
    HANGING_SIGN(new Material[]{
            Material.OAK_HANGING_SIGN,
            Material.SPRUCE_HANGING_SIGN,
            Material.BIRCH_HANGING_SIGN,
            Material.JUNGLE_HANGING_SIGN,
            Material.ACACIA_HANGING_SIGN,
            Material.DARK_OAK_HANGING_SIGN,
            Material.MANGROVE_HANGING_SIGN,
            Material.CHERRY_HANGING_SIGN,
            Material.BAMBOO_HANGING_SIGN,
            Material.CRIMSON_HANGING_SIGN,
            Material.WARPED_HANGING_SIGN},
            Option.AND, rngSettings.SMALL_SUBSET, 1),
    LOOM(new Material[]{Material.LOOM}, Option.OR, rngSettings.SINGLE, 1),
    TORCH(new Material[]{Material.TORCH}, Option.OR, rngSettings.COBBLESTONE, 1),
    JACK_O_LANTERN(new Material[]{Material.JACK_O_LANTERN}, Option.OR, rngSettings.SINGLE, 1),
    GLOWSTONE_BLOCK(new Material[]{Material.GLOWSTONE}, Option.OR, rngSettings.COBBLESTONE, 2),
    SHROOMLIGHT(new Material[]{Material.SHROOMLIGHT}, Option.OR, rngSettings.ORE_BLOCK, 2),
    SOUL_FIRE_TORCH(new Material[]{Material.SOUL_TORCH}, Option.OR, rngSettings.COBBLESTONE, 2),
    FROGLIGHT(new Material[]{Material.OCHRE_FROGLIGHT, Material.VERDANT_FROGLIGHT, Material.PEARLESCENT_FROGLIGHT}, Option.OR, rngSettings.PICK_ONE, 3);


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
        if(settings.length < 2) throw new IllegalArgumentException("Missing rng Settings");
        if(settings.length > 2) {
            int minItems = settings[2];
            int maxItems = (settings.length > 3) ? settings[3] : target.length;
            int numItems = rng.nextInt(minItems, maxItems);
            ArrayList<Material> tmpItems = new ArrayList<Material>();

            Set<Integer> chosenIndices = new HashSet<>();
            while(chosenIndices.size() < numItems) {
                chosenIndices.add(rng.nextInt(0, target.length));
            }
            for (int index : chosenIndices) {
                tmpItems.add(target[index]);
            }
            this.items = tmpItems;
        }
        else this.items = new ArrayList<Material>(Arrays.asList(target));
        this.amount = rng.nextInt(settings[0], settings[1]);
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
            case STONE_TOOLS:
                return "Collect a full stone toolset";
            case IRON_TOOLS:
                return "Collect a full iron toolset";
            case DIAMOND_TOOLS:
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



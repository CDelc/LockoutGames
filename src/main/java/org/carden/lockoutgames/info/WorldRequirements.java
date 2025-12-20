package org.carden.lockoutgames.info;

import org.bukkit.block.Biome;
import org.bukkit.generator.structure.Structure;
import org.carden.lockoutgames.LockoutGames;
import org.carden.lockoutgames.game.GameWorld;

import java.util.function.Predicate;

public class WorldRequirements {

    /**
     * This tracks everything in the game that requires certain conditions about the gameplay area are met.
     * NOTE: A function here returning false does not necessarily mean the element is unobtainable, these
     * simply check the minimum requirements where the plugin can consistently guarantee that the element is obtainable.
     */

    public static boolean checkElement(String enumName) {
        try {
            return Enum.valueOf(WorldRequirements.Element.class, enumName).verify();
        } catch (Exception e) {
            return true;
        }
    }

    public enum Element {
        /**
         * Enums here should match the correspond enum's name in the Spigot API (i.e. Material.DRIPSTONE_BLOCK should match ELEMENT.DRIPSTONE_BLOCK
         * A boolean function can be passed that checks whether a GameWorld has the required attributes for an element to be accessible
         */

        // ********************** MATERIAL **********************

        AIR(WorldRequirements::guaranteed),
        STONE(WorldRequirements::guaranteed),
        GRANITE(WorldRequirements::guaranteed),
        POLISHED_GRANITE(WorldRequirements::guaranteed),
        DIORITE(WorldRequirements::guaranteed),
        POLISHED_DIORITE(WorldRequirements::guaranteed),
        ANDESITE(WorldRequirements::guaranteed),
        POLISHED_ANDESITE(WorldRequirements::guaranteed),
        DEEPSLATE(WorldRequirements::guaranteed),
        COBBLED_DEEPSLATE(WorldRequirements::guaranteed),
        POLISHED_DEEPSLATE(WorldRequirements::guaranteed),
        CALCITE(WorldRequirements::guaranteed),
        TUFF(WorldRequirements::guaranteed),
        TUFF_SLAB(WorldRequirements::guaranteed),
        TUFF_STAIRS(WorldRequirements::guaranteed),
        TUFF_WALL(WorldRequirements::guaranteed),
        CHISELED_TUFF(WorldRequirements::guaranteed),
        POLISHED_TUFF(WorldRequirements::guaranteed),
        POLISHED_TUFF_SLAB(WorldRequirements::guaranteed),
        POLISHED_TUFF_STAIRS(WorldRequirements::guaranteed),
        POLISHED_TUFF_WALL(WorldRequirements::guaranteed),
        TUFF_BRICKS(WorldRequirements::guaranteed),
        TUFF_BRICK_SLAB(WorldRequirements::guaranteed),
        TUFF_BRICK_STAIRS(WorldRequirements::guaranteed),
        TUFF_BRICK_WALL(WorldRequirements::guaranteed),
        CHISELED_TUFF_BRICKS(WorldRequirements::guaranteed),
        DRIPSTONE_BLOCK(WorldRequirements::hasDripstone),
        GRASS_BLOCK(WorldRequirements::guaranteed),
        DIRT(WorldRequirements::guaranteed),
        COARSE_DIRT(WorldRequirements::guaranteed),
        PODZOL(WorldRequirements::hasPodzol),
        ROOTED_DIRT(WorldRequirements::hasLushCave),
        MUD(WorldRequirements::guaranteed),
        CRIMSON_NYLIUM(WorldRequirements::hasCrimson),
        WARPED_NYLIUM(WorldRequirements::hasWarped),
        COBBLESTONE(WorldRequirements::guaranteed),
        OAK_PLANKS(WorldRequirements::hasOak),
        SPRUCE_PLANKS(WorldRequirements::hasSpruce),
        BIRCH_PLANKS(WorldRequirements::hasBirch),
        JUNGLE_PLANKS(WorldRequirements::hasJungle),
        ACACIA_PLANKS(WorldRequirements::hasAcacia),
        CHERRY_PLANKS(WorldRequirements::hasCherry),
        DARK_OAK_PLANKS(WorldRequirements::hasDarkOak),
        PALE_OAK_PLANKS(WorldRequirements::hasPaleOak),
        MANGROVE_PLANKS(WorldRequirements::hasMangrove),
        BAMBOO_PLANKS(WorldRequirements::hasBamboo),
        CRIMSON_PLANKS(WorldRequirements::hasCrimson),
        WARPED_PLANKS(WorldRequirements::hasWarped),
        BAMBOO_MOSAIC(WorldRequirements::hasBamboo),
        OAK_SAPLING(WorldRequirements::hasOak),
        SPRUCE_SAPLING(WorldRequirements::hasSpruce),
        BIRCH_SAPLING(WorldRequirements::hasBirch),
        JUNGLE_SAPLING(WorldRequirements::hasJungle),
        ACACIA_SAPLING(WorldRequirements::hasAcacia),
        CHERRY_SAPLING(WorldRequirements::hasCherry),
        DARK_OAK_SAPLING(WorldRequirements::hasDarkOak),
        PALE_OAK_SAPLING(WorldRequirements::hasPaleOak),
        MANGROVE_PROPAGULE(WorldRequirements::hasMangrove),
        BEDROCK(WorldRequirements::guaranteed),
        SAND(WorldRequirements::guaranteed),
        SUSPICIOUS_SAND(WorldRequirements::hasSuspiciousSand),
        SUSPICIOUS_GRAVEL(WorldRequirements::hasSuspiciousGravel),
        RED_SAND(WorldRequirements::hasRedSand),
        GRAVEL(WorldRequirements::guaranteed),
        COAL_ORE(WorldRequirements::guaranteed),
        DEEPSLATE_COAL_ORE(WorldRequirements::guaranteed),
        IRON_ORE(WorldRequirements::guaranteed),
        DEEPSLATE_IRON_ORE(WorldRequirements::guaranteed),
        COPPER_ORE(WorldRequirements::guaranteed),
        DEEPSLATE_COPPER_ORE(WorldRequirements::guaranteed),
        GOLD_ORE(WorldRequirements::guaranteed),
        DEEPSLATE_GOLD_ORE(WorldRequirements::guaranteed),
        REDSTONE_ORE(WorldRequirements::guaranteed),
        DEEPSLATE_REDSTONE_ORE(WorldRequirements::guaranteed),
        EMERALD_ORE(WorldRequirements::hasNaturalEmerald),
        DEEPSLATE_EMERALD_ORE(WorldRequirements::hasNaturalEmerald),
        LAPIS_ORE(WorldRequirements::guaranteed),
        DEEPSLATE_LAPIS_ORE(WorldRequirements::guaranteed),
        DIAMOND_ORE(WorldRequirements::guaranteed),
        DEEPSLATE_DIAMOND_ORE(WorldRequirements::guaranteed),
        NETHER_GOLD_ORE(WorldRequirements::guaranteed),
        NETHER_QUARTZ_ORE(WorldRequirements::guaranteed),
        ANCIENT_DEBRIS(WorldRequirements::guaranteed),
        COAL_BLOCK(WorldRequirements::guaranteed),
        RAW_IRON_BLOCK(WorldRequirements::guaranteed),
        RAW_COPPER_BLOCK(WorldRequirements::guaranteed),
        RAW_GOLD_BLOCK(WorldRequirements::guaranteed),
        HEAVY_CORE(WorldRequirements::hasTrialChamber),
        AMETHYST_BLOCK(WorldRequirements::guaranteed),
        BUDDING_AMETHYST(WorldRequirements::guaranteed),
        IRON_BLOCK(WorldRequirements::guaranteed),
        COPPER_BLOCK(WorldRequirements::guaranteed),
        GOLD_BLOCK(WorldRequirements::guaranteed),
        DIAMOND_BLOCK(WorldRequirements::guaranteed),
        NETHERITE_BLOCK(WorldRequirements::guaranteed),
        EXPOSED_COPPER(WorldRequirements::guaranteed),
        WEATHERED_COPPER(WorldRequirements::guaranteed),
        OXIDIZED_COPPER(WorldRequirements::guaranteed),
        CHISELED_COPPER(WorldRequirements::guaranteed),
        EXPOSED_CHISELED_COPPER(WorldRequirements::guaranteed),
        WEATHERED_CHISELED_COPPER(WorldRequirements::guaranteed),
        OXIDIZED_CHISELED_COPPER(WorldRequirements::guaranteed),
        CUT_COPPER(WorldRequirements::guaranteed),
        EXPOSED_CUT_COPPER(WorldRequirements::guaranteed),
        WEATHERED_CUT_COPPER(WorldRequirements::guaranteed),
        OXIDIZED_CUT_COPPER(WorldRequirements::guaranteed),
        CUT_COPPER_STAIRS(WorldRequirements::guaranteed),
        EXPOSED_CUT_COPPER_STAIRS(WorldRequirements::guaranteed),
        WEATHERED_CUT_COPPER_STAIRS(WorldRequirements::guaranteed),
        OXIDIZED_CUT_COPPER_STAIRS(WorldRequirements::guaranteed),
        CUT_COPPER_SLAB(WorldRequirements::guaranteed),
        EXPOSED_CUT_COPPER_SLAB(WorldRequirements::guaranteed),
        WEATHERED_CUT_COPPER_SLAB(WorldRequirements::guaranteed),
        OXIDIZED_CUT_COPPER_SLAB(WorldRequirements::guaranteed),
        WAXED_COPPER_BLOCK(WorldRequirements::hasBees),
        WAXED_EXPOSED_COPPER(WorldRequirements::hasBees),
        WAXED_WEATHERED_COPPER(WorldRequirements::hasBees),
        WAXED_OXIDIZED_COPPER(WorldRequirements::hasBees),
        WAXED_CHISELED_COPPER(WorldRequirements::hasBees),
        WAXED_EXPOSED_CHISELED_COPPER(WorldRequirements::hasBees),
        WAXED_WEATHERED_CHISELED_COPPER(WorldRequirements::hasBees),
        WAXED_OXIDIZED_CHISELED_COPPER(WorldRequirements::hasBees),
        WAXED_CUT_COPPER(WorldRequirements::hasBees),
        WAXED_EXPOSED_CUT_COPPER(WorldRequirements::hasBees),
        WAXED_WEATHERED_CUT_COPPER(WorldRequirements::hasBees),
        WAXED_OXIDIZED_CUT_COPPER(WorldRequirements::hasBees),
        WAXED_CUT_COPPER_STAIRS(WorldRequirements::hasBees),
        WAXED_EXPOSED_CUT_COPPER_STAIRS(WorldRequirements::hasBees),
        WAXED_WEATHERED_CUT_COPPER_STAIRS(WorldRequirements::hasBees),
        WAXED_OXIDIZED_CUT_COPPER_STAIRS(WorldRequirements::hasBees),
        WAXED_CUT_COPPER_SLAB(WorldRequirements::hasBees),
        WAXED_EXPOSED_CUT_COPPER_SLAB(WorldRequirements::hasBees),
        WAXED_WEATHERED_CUT_COPPER_SLAB(WorldRequirements::hasBees),
        WAXED_OXIDIZED_CUT_COPPER_SLAB(WorldRequirements::hasBees),
        OAK_LOG(WorldRequirements::hasOak),
        SPRUCE_LOG(WorldRequirements::hasSpruce),
        BIRCH_LOG(WorldRequirements::hasBirch),
        JUNGLE_LOG(WorldRequirements::hasJungle),
        ACACIA_LOG(WorldRequirements::hasAcacia),
        CHERRY_LOG(WorldRequirements::hasCherry),
        PALE_OAK_LOG(WorldRequirements::hasPaleOak),
        DARK_OAK_LOG(WorldRequirements::hasDarkOak),
        MANGROVE_LOG(WorldRequirements::hasMangrove),
        MANGROVE_ROOTS(WorldRequirements::hasMangrove),
        MUDDY_MANGROVE_ROOTS(WorldRequirements::hasMangrove),
        CRIMSON_STEM(WorldRequirements::hasCrimson),
        WARPED_STEM(WorldRequirements::hasWarped),
        BAMBOO_BLOCK(WorldRequirements::hasBamboo),
        STRIPPED_OAK_LOG(WorldRequirements::hasOak),
        STRIPPED_SPRUCE_LOG(WorldRequirements::hasSpruce),
        STRIPPED_BIRCH_LOG(WorldRequirements::hasBirch),
        STRIPPED_JUNGLE_LOG(WorldRequirements::hasJungle),
        STRIPPED_ACACIA_LOG(WorldRequirements::hasAcacia),
        STRIPPED_CHERRY_LOG(WorldRequirements::hasCherry),
        STRIPPED_DARK_OAK_LOG(WorldRequirements::hasDarkOak),
        STRIPPED_PALE_OAK_LOG(WorldRequirements::hasPaleOak),
        STRIPPED_MANGROVE_LOG(WorldRequirements::hasMangrove),
        STRIPPED_CRIMSON_STEM(WorldRequirements::hasCrimson),
        STRIPPED_WARPED_STEM(WorldRequirements::hasWarped),
        STRIPPED_OAK_WOOD(WorldRequirements::hasOak),
        STRIPPED_SPRUCE_WOOD(WorldRequirements::hasSpruce),
        STRIPPED_BIRCH_WOOD(WorldRequirements::hasBirch),
        STRIPPED_JUNGLE_WOOD(WorldRequirements::hasJungle),
        STRIPPED_ACACIA_WOOD(WorldRequirements::hasAcacia),
        STRIPPED_CHERRY_WOOD(WorldRequirements::hasCherry),
        STRIPPED_DARK_OAK_WOOD(WorldRequirements::hasDarkOak),
        STRIPPED_PALE_OAK_WOOD(WorldRequirements::hasPaleOak),
        STRIPPED_MANGROVE_WOOD(WorldRequirements::hasMangrove),
        STRIPPED_CRIMSON_HYPHAE(WorldRequirements::hasCrimson),
        STRIPPED_WARPED_HYPHAE(WorldRequirements::hasWarped),
        STRIPPED_BAMBOO_BLOCK(WorldRequirements::hasBamboo),
        OAK_WOOD(WorldRequirements::hasOak),
        SPRUCE_WOOD(WorldRequirements::hasSpruce),
        BIRCH_WOOD(WorldRequirements::hasBirch),
        JUNGLE_WOOD(WorldRequirements::hasJungle),
        ACACIA_WOOD(WorldRequirements::hasAcacia),
        CHERRY_WOOD(WorldRequirements::hasCherry),
        PALE_OAK_WOOD(WorldRequirements::hasPaleOak),
        DARK_OAK_WOOD(WorldRequirements::hasDarkOak),
        MANGROVE_WOOD(WorldRequirements::hasMangrove),
        CRIMSON_HYPHAE(WorldRequirements::hasCrimson),
        WARPED_HYPHAE(WorldRequirements::hasWarped),
        OAK_LEAVES(WorldRequirements::hasOak),
        SPRUCE_LEAVES(WorldRequirements::hasSpruce),
        BIRCH_LEAVES(WorldRequirements::hasBirch),
        JUNGLE_LEAVES(WorldRequirements::hasJungle),
        ACACIA_LEAVES(WorldRequirements::hasAcacia),
        CHERRY_LEAVES(WorldRequirements::hasCherry),
        DARK_OAK_LEAVES(WorldRequirements::hasDarkOak),
        PALE_OAK_LEAVES(WorldRequirements::hasPaleOak),
        MANGROVE_LEAVES(WorldRequirements::hasMangrove),
        AZALEA_LEAVES(WorldRequirements::hasLushCave),
        FLOWERING_AZALEA_LEAVES(WorldRequirements::hasLushCave),
        SPONGE(WorldRequirements::hasOceanMonument),
        WET_SPONGE(WorldRequirements::hasOceanMonument),
        GLASS(WorldRequirements::guaranteed),
        TINTED_GLASS(WorldRequirements::guaranteed),
        LAPIS_BLOCK(WorldRequirements::guaranteed),
        SANDSTONE(WorldRequirements::guaranteed),
        CHISELED_SANDSTONE(WorldRequirements::guaranteed),
        CUT_SANDSTONE(WorldRequirements::guaranteed),
        COBWEB(WorldRequirements::hasCobweb),
        SHORT_GRASS(WorldRequirements::guaranteed),
        FERN(WorldRequirements::hasFern),
        AZALEA(WorldRequirements::hasLushCave),
        FLOWERING_AZALEA(WorldRequirements::hasLushCave),
        DEAD_BUSH(WorldRequirements::hasDeadBush),
        SEAGRASS(WorldRequirements::hasSeagrass),
        SEA_PICKLE(WorldRequirements::hasCoral),
        WHITE_WOOL(WorldRequirements::guaranteed),
        ORANGE_WOOL(WorldRequirements::guaranteed),
        MAGENTA_WOOL(WorldRequirements::guaranteed),
        LIGHT_BLUE_WOOL(WorldRequirements::guaranteed),
        YELLOW_WOOL(WorldRequirements::guaranteed),
        LIME_WOOL(WorldRequirements::hasLime),
        PINK_WOOL(WorldRequirements::guaranteed),
        GRAY_WOOL(WorldRequirements::guaranteed),
        LIGHT_GRAY_WOOL(WorldRequirements::guaranteed),
        CYAN_WOOL(WorldRequirements::hasCyan),
        PURPLE_WOOL(WorldRequirements::guaranteed),
        BLUE_WOOL(WorldRequirements::guaranteed),
        BROWN_WOOL(WorldRequirements::hasBrown),
        GREEN_WOOL(WorldRequirements::hasGreen),
        RED_WOOL(WorldRequirements::guaranteed),
        BLACK_WOOL(WorldRequirements::guaranteed),
        DANDELION(WorldRequirements::guaranteed),
        OPEN_EYEBLOSSOM(WorldRequirements::hasPaleGarden),
        CLOSED_EYEBLOSSOM(WorldRequirements::hasPaleGarden),
        POPPY(WorldRequirements::guaranteed),
        BLUE_ORCHID(WorldRequirements::hasBlueOrchid),
        ALLIUM(WorldRequirements::hasAllium),
        AZURE_BLUET(WorldRequirements::hasAzureBluet),
        RED_TULIP(WorldRequirements::hasTulip),
        ORANGE_TULIP(WorldRequirements::hasTulip),
        WHITE_TULIP(WorldRequirements::hasTulip),
        PINK_TULIP(WorldRequirements::hasTulip),
        OXEYE_DAISY(WorldRequirements::hasOxeyeDaisy),
        CORNFLOWER(WorldRequirements::hasCornflower),
        LILY_OF_THE_VALLEY(WorldRequirements::hasLilyOfTheValley),
        WITHER_ROSE(WorldRequirements::hasFortress),
        TORCHFLOWER(WorldRequirements::hasSniffer),
        PITCHER_PLANT(WorldRequirements::hasSniffer),
        SPORE_BLOSSOM(WorldRequirements::hasLushCave),
        BROWN_MUSHROOM(WorldRequirements::guaranteed),
        RED_MUSHROOM(WorldRequirements::guaranteed),
        CRIMSON_FUNGUS(WorldRequirements::hasCrimson),
        WARPED_FUNGUS(WorldRequirements::hasWarped),
        CRIMSON_ROOTS(WorldRequirements::hasCrimson),
        WARPED_ROOTS(WorldRequirements::hasWarped),
        NETHER_SPROUTS(WorldRequirements::hasWarped),
        WEEPING_VINES(WorldRequirements::hasCrimson),
        TWISTING_VINES(WorldRequirements::hasWarped),
        SUGAR_CANE(WorldRequirements::guaranteed),
        KELP(WorldRequirements::hasKelp),
        PINK_PETALS(WorldRequirements::hasCherry),
        MOSS_CARPET(WorldRequirements::hasMossCarpet),
        MOSS_BLOCK(WorldRequirements::hasLushCave),
        PALE_MOSS_CARPET(WorldRequirements::hasPaleGarden),
        PALE_HANGING_MOSS(WorldRequirements::hasPaleGarden),
        PALE_MOSS_BLOCK(WorldRequirements::hasPaleGarden),
        HANGING_ROOTS(WorldRequirements::hasLushCave),
        BIG_DRIPLEAF(WorldRequirements::hasLushCave),
        SMALL_DRIPLEAF(WorldRequirements::hasLushCave),
        BAMBOO(WorldRequirements::hasBamboo),
        OAK_SLAB(WorldRequirements::hasOak),
        SPRUCE_SLAB(WorldRequirements::hasSpruce),
        BIRCH_SLAB(WorldRequirements::hasBirch),
        JUNGLE_SLAB(WorldRequirements::hasJungle),
        ACACIA_SLAB(WorldRequirements::hasAcacia),
        CHERRY_SLAB(WorldRequirements::hasCherry),
        DARK_OAK_SLAB(WorldRequirements::hasDarkOak),
        PALE_OAK_SLAB(WorldRequirements::hasPaleOak),
        MANGROVE_SLAB(WorldRequirements::hasMangrove),
        BAMBOO_SLAB(WorldRequirements::hasBamboo),
        BAMBOO_MOSAIC_SLAB(WorldRequirements::hasBamboo),
        CRIMSON_SLAB(WorldRequirements::hasCrimson),
        WARPED_SLAB(WorldRequirements::hasWarped),
        STONE_SLAB(WorldRequirements::guaranteed),
        SMOOTH_STONE_SLAB(WorldRequirements::guaranteed),
        SANDSTONE_SLAB(WorldRequirements::guaranteed),
        CUT_SANDSTONE_SLAB(WorldRequirements::guaranteed),
        PETRIFIED_OAK_SLAB(WorldRequirements::unobtainable),
        COBBLESTONE_SLAB(WorldRequirements::guaranteed),
        BRICK_SLAB(WorldRequirements::guaranteed),
        STONE_BRICK_SLAB(WorldRequirements::guaranteed),
        MUD_BRICK_SLAB(WorldRequirements::guaranteed),
        NETHER_BRICK_SLAB(WorldRequirements::guaranteed),
        QUARTZ_SLAB(WorldRequirements::guaranteed),
        RED_SANDSTONE_SLAB(WorldRequirements::hasRedSand),
        CUT_RED_SANDSTONE_SLAB(WorldRequirements::hasRedSand),
        PURPUR_SLAB(WorldRequirements::hasEndAccess),
        PRISMARINE_SLAB(WorldRequirements::hasOceanMonument),
        PRISMARINE_BRICK_SLAB(WorldRequirements::hasOceanMonument),
        DARK_PRISMARINE_SLAB(WorldRequirements::hasOceanMonument),
        SMOOTH_QUARTZ(WorldRequirements::guaranteed),
        SMOOTH_RED_SANDSTONE(WorldRequirements::hasRedSand),
        SMOOTH_SANDSTONE(WorldRequirements::guaranteed),
        SMOOTH_STONE(WorldRequirements::guaranteed),
        BRICKS(WorldRequirements::guaranteed),
        BOOKSHELF(WorldRequirements::guaranteed),
        CHISELED_BOOKSHELF(WorldRequirements::guaranteed),
        DECORATED_POT(WorldRequirements::hasTrialChamber),
        MOSSY_COBBLESTONE(WorldRequirements::hasMossyCobble),
        OBSIDIAN(WorldRequirements::guaranteed),
        TORCH(WorldRequirements::guaranteed),
        END_ROD(WorldRequirements::hasEndAccess),
        CHORUS_PLANT(WorldRequirements::hasEndAccess),
        CHORUS_FLOWER(WorldRequirements::hasEndAccess),
        PURPUR_BLOCK(WorldRequirements::hasEndAccess),
        PURPUR_PILLAR(WorldRequirements::hasEndAccess),
        PURPUR_STAIRS(WorldRequirements::hasEndAccess),
        SPAWNER(WorldRequirements::hasSpawner),
        CREAKING_HEART(WorldRequirements::hasPaleGarden),
        CHEST(WorldRequirements::guaranteed),
        CRAFTING_TABLE(WorldRequirements::guaranteed),
        FARMLAND(WorldRequirements::guaranteed),
        FURNACE(WorldRequirements::guaranteed),
        LADDER(WorldRequirements::guaranteed),
        COBBLESTONE_STAIRS(WorldRequirements::guaranteed),
        SNOW(WorldRequirements::hasSnow),
        ICE(WorldRequirements::hasIce),
        SNOW_BLOCK(WorldRequirements::hasSnow),
        CACTUS(WorldRequirements::guaranteed),
        CLAY(WorldRequirements::guaranteed),
        JUKEBOX(WorldRequirements::guaranteed),
        OAK_FENCE(WorldRequirements::hasOak),
        SPRUCE_FENCE(WorldRequirements::hasSpruce),
        BIRCH_FENCE(WorldRequirements::hasBirch),
        JUNGLE_FENCE(WorldRequirements::hasJungle),
        ACACIA_FENCE(WorldRequirements::hasAcacia),
        CHERRY_FENCE(WorldRequirements::hasCherry),
        DARK_OAK_FENCE(WorldRequirements::hasDarkOak),
        PALE_OAK_FENCE(WorldRequirements::hasPaleOak),
        MANGROVE_FENCE(WorldRequirements::hasMangrove),
        BAMBOO_FENCE(WorldRequirements::hasBamboo),
        CRIMSON_FENCE(WorldRequirements::hasCrimson),
        WARPED_FENCE(WorldRequirements::hasWarped),
        PUMPKIN(WorldRequirements::guaranteed),
        CARVED_PUMPKIN(WorldRequirements::guaranteed),
        JACK_O_LANTERN(WorldRequirements::guaranteed),
        NETHERRACK(WorldRequirements::guaranteed),
        SOUL_SAND(WorldRequirements::guaranteed),
        SOUL_SOIL(WorldRequirements::hasSoulValley),
        BASALT(WorldRequirements::hasBasalt),
        POLISHED_BASALT(WorldRequirements::hasBasalt),
        SMOOTH_BASALT(WorldRequirements::hasBasalt),
        SOUL_TORCH(WorldRequirements::guaranteed),
        GLOWSTONE(WorldRequirements::guaranteed),
        INFESTED_STONE(WorldRequirements::hasBlockInfestation),
        INFESTED_COBBLESTONE(WorldRequirements::unobtainable),
        INFESTED_STONE_BRICKS(WorldRequirements::hasStronghold),
        INFESTED_MOSSY_STONE_BRICKS(WorldRequirements::hasStronghold),
        INFESTED_CRACKED_STONE_BRICKS(WorldRequirements::hasStronghold),
        INFESTED_CHISELED_STONE_BRICKS(WorldRequirements::hasStronghold),
        INFESTED_DEEPSLATE(WorldRequirements::hasBlockInfestation),
        STONE_BRICKS(WorldRequirements::guaranteed),
        MOSSY_STONE_BRICKS(WorldRequirements::hasMossyBrick),
        CRACKED_STONE_BRICKS(WorldRequirements::guaranteed),
        CHISELED_STONE_BRICKS(WorldRequirements::guaranteed),
        PACKED_MUD(WorldRequirements::guaranteed),
        MUD_BRICKS(WorldRequirements::guaranteed),
        DEEPSLATE_BRICKS(WorldRequirements::guaranteed),
        CRACKED_DEEPSLATE_BRICKS(WorldRequirements::guaranteed),
        DEEPSLATE_TILES(WorldRequirements::guaranteed),
        CRACKED_DEEPSLATE_TILES(WorldRequirements::guaranteed),
        CHISELED_DEEPSLATE(WorldRequirements::guaranteed),
        REINFORCED_DEEPSLATE(WorldRequirements::hasAncientCity),
        BROWN_MUSHROOM_BLOCK(WorldRequirements::guaranteed),
        RED_MUSHROOM_BLOCK(WorldRequirements::guaranteed),
        MUSHROOM_STEM(WorldRequirements::guaranteed),
        IRON_BARS(WorldRequirements::guaranteed),
        CHAIN(WorldRequirements::guaranteed),
        GLASS_PANE(WorldRequirements::guaranteed),
        MELON(WorldRequirements::hasJungle),
        VINE(WorldRequirements::hasVines),
        GLOW_LICHEN(WorldRequirements::guaranteed),
        RESIN_CLUMP(WorldRequirements::hasPaleGarden),
        RESIN_BLOCK(WorldRequirements::hasPaleGarden),
        RESIN_BRICKS(WorldRequirements::hasPaleGarden),
        RESIN_BRICK_STAIRS(WorldRequirements::hasPaleGarden),
        RESIN_BRICK_SLAB(WorldRequirements::hasPaleGarden),
        RESIN_BRICK_WALL(WorldRequirements::hasPaleGarden),
        CHISELED_RESIN_BRICKS(WorldRequirements::hasPaleGarden),
        BRICK_STAIRS(WorldRequirements::guaranteed),
        STONE_BRICK_STAIRS(WorldRequirements::guaranteed),
        MUD_BRICK_STAIRS(WorldRequirements::guaranteed),
        MYCELIUM(WorldRequirements::hasMushroomBiome),
        LILY_PAD(WorldRequirements::hasSwamp),
        NETHER_BRICKS(WorldRequirements::guaranteed),
        CRACKED_NETHER_BRICKS(WorldRequirements::guaranteed),
        CHISELED_NETHER_BRICKS(WorldRequirements::guaranteed),
        NETHER_BRICK_FENCE(WorldRequirements::guaranteed),
        NETHER_BRICK_STAIRS(WorldRequirements::guaranteed),
        SCULK(WorldRequirements::hasDeepDark),
        SCULK_VEIN(WorldRequirements::hasDeepDark),
        SCULK_CATALYST(WorldRequirements::hasDeepDark),
        SCULK_SHRIEKER(WorldRequirements::hasDeepDark),
        ENCHANTING_TABLE(WorldRequirements::guaranteed),
        END_PORTAL_FRAME(WorldRequirements::hasStronghold),
        END_STONE(WorldRequirements::hasEndAccess),
        END_STONE_BRICKS(WorldRequirements::hasEndAccess),
        DRAGON_EGG(WorldRequirements::hasEndAccess),
        SANDSTONE_STAIRS(WorldRequirements::guaranteed),
        ENDER_CHEST(WorldRequirements::hasFortress),
        EMERALD_BLOCK(WorldRequirements::hasEmerald),
        OAK_STAIRS(WorldRequirements::hasOak),
        SPRUCE_STAIRS(WorldRequirements::hasSpruce),
        BIRCH_STAIRS(WorldRequirements::hasBirch),
        JUNGLE_STAIRS(WorldRequirements::hasJungle),
        ACACIA_STAIRS(WorldRequirements::hasAcacia),
        CHERRY_STAIRS(WorldRequirements::hasCherry),
        DARK_OAK_STAIRS(WorldRequirements::hasDarkOak),
        PALE_OAK_STAIRS(WorldRequirements::hasPaleOak),
        MANGROVE_STAIRS(WorldRequirements::hasMangrove),
        BAMBOO_STAIRS(WorldRequirements::hasBamboo),
        BAMBOO_MOSAIC_STAIRS(WorldRequirements::hasBamboo),
        CRIMSON_STAIRS(WorldRequirements::hasCrimson),
        WARPED_STAIRS(WorldRequirements::hasWarped),
        COMMAND_BLOCK(WorldRequirements::unobtainable),
        BEACON(WorldRequirements::hasFortress),
        COBBLESTONE_WALL(WorldRequirements::guaranteed),
        MOSSY_COBBLESTONE_WALL(WorldRequirements::hasMossyCobble),
        BRICK_WALL(WorldRequirements::guaranteed),
        PRISMARINE_WALL(WorldRequirements::hasOceanMonument),
        RED_SANDSTONE_WALL(WorldRequirements::hasRedSand),
        MOSSY_STONE_BRICK_WALL(WorldRequirements::hasMossyBrick),
        GRANITE_WALL(WorldRequirements::guaranteed),
        STONE_BRICK_WALL(WorldRequirements::guaranteed),
        MUD_BRICK_WALL(WorldRequirements::guaranteed),
        NETHER_BRICK_WALL(WorldRequirements::guaranteed),
        ANDESITE_WALL(WorldRequirements::guaranteed),
        RED_NETHER_BRICK_WALL(WorldRequirements::hasFortress),
        SANDSTONE_WALL(WorldRequirements::guaranteed),
        END_STONE_BRICK_WALL(WorldRequirements::hasEndAccess),
        DIORITE_WALL(WorldRequirements::guaranteed),
        BLACKSTONE_WALL(WorldRequirements::guaranteed),
        POLISHED_BLACKSTONE_WALL(WorldRequirements::guaranteed),
        POLISHED_BLACKSTONE_BRICK_WALL(WorldRequirements::guaranteed),
        COBBLED_DEEPSLATE_WALL(WorldRequirements::guaranteed),
        POLISHED_DEEPSLATE_WALL(WorldRequirements::guaranteed),
        DEEPSLATE_BRICK_WALL(WorldRequirements::guaranteed),
        DEEPSLATE_TILE_WALL(WorldRequirements::guaranteed),
        ANVIL(WorldRequirements::guaranteed),
        CHIPPED_ANVIL(WorldRequirements::guaranteed),
        DAMAGED_ANVIL(WorldRequirements::guaranteed),
        CHISELED_QUARTZ_BLOCK(WorldRequirements::guaranteed),
        QUARTZ_BLOCK(WorldRequirements::guaranteed),
        QUARTZ_BRICKS(WorldRequirements::guaranteed),
        QUARTZ_PILLAR(WorldRequirements::guaranteed),
        QUARTZ_STAIRS(WorldRequirements::guaranteed),
        WHITE_TERRACOTTA(WorldRequirements::guaranteed),
        ORANGE_TERRACOTTA(WorldRequirements::guaranteed),
        MAGENTA_TERRACOTTA(WorldRequirements::guaranteed),
        LIGHT_BLUE_TERRACOTTA(WorldRequirements::guaranteed),
        YELLOW_TERRACOTTA(WorldRequirements::guaranteed),
        LIME_TERRACOTTA(WorldRequirements::hasLime),
        PINK_TERRACOTTA(WorldRequirements::guaranteed),
        GRAY_TERRACOTTA(WorldRequirements::guaranteed),
        LIGHT_GRAY_TERRACOTTA(WorldRequirements::guaranteed),
        CYAN_TERRACOTTA(WorldRequirements::hasCyan),
        PURPLE_TERRACOTTA(WorldRequirements::guaranteed),
        BLUE_TERRACOTTA(WorldRequirements::guaranteed),
        BROWN_TERRACOTTA(WorldRequirements::hasBrown),
        GREEN_TERRACOTTA(WorldRequirements::hasGreen),
        RED_TERRACOTTA(WorldRequirements::guaranteed),
        BLACK_TERRACOTTA(WorldRequirements::guaranteed),
        BARRIER(WorldRequirements::unobtainable),
        LIGHT(WorldRequirements::unobtainable),
        HAY_BLOCK(WorldRequirements::guaranteed),
        WHITE_CARPET(WorldRequirements::guaranteed),
        ORANGE_CARPET(WorldRequirements::guaranteed),
        MAGENTA_CARPET(WorldRequirements::guaranteed),
        LIGHT_BLUE_CARPET(WorldRequirements::guaranteed),
        YELLOW_CARPET(WorldRequirements::guaranteed),
        LIME_CARPET(WorldRequirements::hasLime),
        PINK_CARPET(WorldRequirements::guaranteed),
        GRAY_CARPET(WorldRequirements::guaranteed),
        LIGHT_GRAY_CARPET(WorldRequirements::guaranteed),
        CYAN_CARPET(WorldRequirements::hasCyan),
        PURPLE_CARPET(WorldRequirements::guaranteed),
        BLUE_CARPET(WorldRequirements::guaranteed),
        BROWN_CARPET(WorldRequirements::hasBrown),
        GREEN_CARPET(WorldRequirements::hasGreen),
        RED_CARPET(WorldRequirements::guaranteed),
        BLACK_CARPET(WorldRequirements::guaranteed),
        TERRACOTTA(WorldRequirements::guaranteed),
        PACKED_ICE(WorldRequirements::hasIce),
        DIRT_PATH(WorldRequirements::guaranteed),
        SUNFLOWER(WorldRequirements::hasSunflower),
        LILAC(WorldRequirements::hasLilac),
        ROSE_BUSH(WorldRequirements::hasLilac),
        PEONY(WorldRequirements::hasLilac),
        TALL_GRASS(WorldRequirements::hasTallGrass),
        LARGE_FERN(WorldRequirements::hasLargeFern),
        WHITE_STAINED_GLASS(WorldRequirements::guaranteed),
        ORANGE_STAINED_GLASS(WorldRequirements::guaranteed),
        MAGENTA_STAINED_GLASS(WorldRequirements::guaranteed),
        LIGHT_BLUE_STAINED_GLASS(WorldRequirements::guaranteed),
        YELLOW_STAINED_GLASS(WorldRequirements::guaranteed),
        LIME_STAINED_GLASS(WorldRequirements::hasLime),
        PINK_STAINED_GLASS(WorldRequirements::guaranteed),
        GRAY_STAINED_GLASS(WorldRequirements::guaranteed),
        LIGHT_GRAY_STAINED_GLASS(WorldRequirements::guaranteed),
        CYAN_STAINED_GLASS(WorldRequirements::hasCyan),
        PURPLE_STAINED_GLASS(WorldRequirements::guaranteed),
        BLUE_STAINED_GLASS(WorldRequirements::guaranteed),
        BROWN_STAINED_GLASS(WorldRequirements::hasBrown),
        GREEN_STAINED_GLASS(WorldRequirements::hasGreen),
        RED_STAINED_GLASS(WorldRequirements::guaranteed),
        BLACK_STAINED_GLASS(WorldRequirements::guaranteed),
        WHITE_STAINED_GLASS_PANE(WorldRequirements::guaranteed),
        ORANGE_STAINED_GLASS_PANE(WorldRequirements::guaranteed),
        MAGENTA_STAINED_GLASS_PANE(WorldRequirements::guaranteed),
        LIGHT_BLUE_STAINED_GLASS_PANE(WorldRequirements::guaranteed),
        YELLOW_STAINED_GLASS_PANE(WorldRequirements::guaranteed),
        LIME_STAINED_GLASS_PANE(WorldRequirements::hasLime),
        PINK_STAINED_GLASS_PANE(WorldRequirements::guaranteed),
        GRAY_STAINED_GLASS_PANE(WorldRequirements::guaranteed),
        LIGHT_GRAY_STAINED_GLASS_PANE(WorldRequirements::guaranteed),
        CYAN_STAINED_GLASS_PANE(WorldRequirements::hasCyan),
        PURPLE_STAINED_GLASS_PANE(WorldRequirements::guaranteed),
        BLUE_STAINED_GLASS_PANE(WorldRequirements::guaranteed),
        BROWN_STAINED_GLASS_PANE(WorldRequirements::hasBrown),
        GREEN_STAINED_GLASS_PANE(WorldRequirements::hasGreen),
        RED_STAINED_GLASS_PANE(WorldRequirements::guaranteed),
        BLACK_STAINED_GLASS_PANE(WorldRequirements::guaranteed),
        PRISMARINE(WorldRequirements::hasOceanMonument),
        PRISMARINE_BRICKS(WorldRequirements::hasOceanMonument),
        DARK_PRISMARINE(WorldRequirements::hasOceanMonument),
        PRISMARINE_STAIRS(WorldRequirements::hasOceanMonument),
        PRISMARINE_BRICK_STAIRS(WorldRequirements::hasOceanMonument),
        DARK_PRISMARINE_STAIRS(WorldRequirements::hasOceanMonument),
        SEA_LANTERN(WorldRequirements::hasOceanMonument),
        RED_SANDSTONE(WorldRequirements::hasRedSand),
        CHISELED_RED_SANDSTONE(WorldRequirements::hasRedSand),
        CUT_RED_SANDSTONE(WorldRequirements::hasRedSand),
        RED_SANDSTONE_STAIRS(WorldRequirements::hasRedSand),
        REPEATING_COMMAND_BLOCK(WorldRequirements::unobtainable),
        CHAIN_COMMAND_BLOCK(WorldRequirements::unobtainable),
        MAGMA_BLOCK(WorldRequirements::guaranteed),
        NETHER_WART_BLOCK(WorldRequirements::hasCrimson),
        WARPED_WART_BLOCK(WorldRequirements::hasWarped),
        RED_NETHER_BRICKS(WorldRequirements::hasFortress),
        BONE_BLOCK(WorldRequirements::guaranteed),
        STRUCTURE_VOID(WorldRequirements::unobtainable),
        SHULKER_BOX(WorldRequirements::hasEndAccess),
        WHITE_SHULKER_BOX(WorldRequirements::hasEndAccess),
        ORANGE_SHULKER_BOX(WorldRequirements::hasEndAccess),
        MAGENTA_SHULKER_BOX(WorldRequirements::hasEndAccess),
        LIGHT_BLUE_SHULKER_BOX(WorldRequirements::hasEndAccess),
        YELLOW_SHULKER_BOX(WorldRequirements::hasEndAccess),
        LIME_SHULKER_BOX(WorldRequirements::hasLimeShulker),
        PINK_SHULKER_BOX(WorldRequirements::hasEndAccess),
        GRAY_SHULKER_BOX(WorldRequirements::hasEndAccess),
        LIGHT_GRAY_SHULKER_BOX(WorldRequirements::hasEndAccess),
        CYAN_SHULKER_BOX(WorldRequirements::hasCyanShulker),
        PURPLE_SHULKER_BOX(WorldRequirements::hasEndAccess),
        BLUE_SHULKER_BOX(WorldRequirements::hasEndAccess),
        BROWN_SHULKER_BOX(WorldRequirements::hasBrownShulker),
        GREEN_SHULKER_BOX(WorldRequirements::hasGreenShulker),
        RED_SHULKER_BOX(WorldRequirements::hasEndAccess),
        BLACK_SHULKER_BOX(WorldRequirements::hasEndAccess),
        WHITE_GLAZED_TERRACOTTA(WorldRequirements::guaranteed),
        ORANGE_GLAZED_TERRACOTTA(WorldRequirements::guaranteed),
        MAGENTA_GLAZED_TERRACOTTA(WorldRequirements::guaranteed),
        LIGHT_BLUE_GLAZED_TERRACOTTA(WorldRequirements::guaranteed),
        YELLOW_GLAZED_TERRACOTTA(WorldRequirements::guaranteed),
        LIME_GLAZED_TERRACOTTA(WorldRequirements::hasLime),
        PINK_GLAZED_TERRACOTTA(WorldRequirements::guaranteed),
        GRAY_GLAZED_TERRACOTTA(WorldRequirements::guaranteed),
        LIGHT_GRAY_GLAZED_TERRACOTTA(WorldRequirements::guaranteed),
        CYAN_GLAZED_TERRACOTTA(WorldRequirements::hasCyan),
        PURPLE_GLAZED_TERRACOTTA(WorldRequirements::guaranteed),
        BLUE_GLAZED_TERRACOTTA(WorldRequirements::guaranteed),
        BROWN_GLAZED_TERRACOTTA(WorldRequirements::hasBrown),
        GREEN_GLAZED_TERRACOTTA(WorldRequirements::hasGreen),
        RED_GLAZED_TERRACOTTA(WorldRequirements::guaranteed),
        BLACK_GLAZED_TERRACOTTA(WorldRequirements::guaranteed),
        WHITE_CONCRETE(WorldRequirements::guaranteed),
        ORANGE_CONCRETE(WorldRequirements::guaranteed),
        MAGENTA_CONCRETE(WorldRequirements::guaranteed),
        LIGHT_BLUE_CONCRETE(WorldRequirements::guaranteed),
        YELLOW_CONCRETE(WorldRequirements::guaranteed),
        LIME_CONCRETE(WorldRequirements::hasLime),
        PINK_CONCRETE(WorldRequirements::guaranteed),
        GRAY_CONCRETE(WorldRequirements::guaranteed),
        LIGHT_GRAY_CONCRETE(WorldRequirements::guaranteed),
        CYAN_CONCRETE(WorldRequirements::hasCyan),
        PURPLE_CONCRETE(WorldRequirements::guaranteed),
        BLUE_CONCRETE(WorldRequirements::guaranteed),
        BROWN_CONCRETE(WorldRequirements::hasBrown),
        GREEN_CONCRETE(WorldRequirements::hasGreen),
        RED_CONCRETE(WorldRequirements::guaranteed),
        BLACK_CONCRETE(WorldRequirements::guaranteed),
        WHITE_CONCRETE_POWDER(WorldRequirements::guaranteed),
        ORANGE_CONCRETE_POWDER(WorldRequirements::guaranteed),
        MAGENTA_CONCRETE_POWDER(WorldRequirements::guaranteed),
        LIGHT_BLUE_CONCRETE_POWDER(WorldRequirements::guaranteed),
        YELLOW_CONCRETE_POWDER(WorldRequirements::hasLime),
        LIME_CONCRETE_POWDER(WorldRequirements::guaranteed),
        PINK_CONCRETE_POWDER(WorldRequirements::guaranteed),
        GRAY_CONCRETE_POWDER(WorldRequirements::guaranteed),
        LIGHT_GRAY_CONCRETE_POWDER(WorldRequirements::guaranteed),
        CYAN_CONCRETE_POWDER(WorldRequirements::hasCyan),
        PURPLE_CONCRETE_POWDER(WorldRequirements::guaranteed),
        BLUE_CONCRETE_POWDER(WorldRequirements::guaranteed),
        BROWN_CONCRETE_POWDER(WorldRequirements::hasBrown),
        GREEN_CONCRETE_POWDER(WorldRequirements::hasGreen),
        RED_CONCRETE_POWDER(WorldRequirements::guaranteed),
        BLACK_CONCRETE_POWDER(WorldRequirements::guaranteed),
        TURTLE_EGG(WorldRequirements::hasTurtleBreeding),
        SNIFFER_EGG(WorldRequirements::hasSniffer),
        DEAD_TUBE_CORAL_BLOCK(WorldRequirements::hasCoral),
        DEAD_BRAIN_CORAL_BLOCK(WorldRequirements::hasCoral),
        DEAD_BUBBLE_CORAL_BLOCK(WorldRequirements::hasCoral),
        DEAD_FIRE_CORAL_BLOCK(WorldRequirements::hasCoral),
        DEAD_HORN_CORAL_BLOCK(WorldRequirements::hasCoral),
        TUBE_CORAL_BLOCK(WorldRequirements::hasCoral),
        BRAIN_CORAL_BLOCK(WorldRequirements::hasCoral),
        BUBBLE_CORAL_BLOCK(WorldRequirements::hasCoral),
        FIRE_CORAL_BLOCK(WorldRequirements::hasCoral),
        HORN_CORAL_BLOCK(WorldRequirements::hasCoral),
        TUBE_CORAL(WorldRequirements::hasCoral),
        BRAIN_CORAL(WorldRequirements::hasCoral),
        BUBBLE_CORAL(WorldRequirements::hasCoral),
        FIRE_CORAL(WorldRequirements::hasCoral),
        HORN_CORAL(WorldRequirements::hasCoral),
        DEAD_BRAIN_CORAL(WorldRequirements::hasCoral),
        DEAD_BUBBLE_CORAL(WorldRequirements::hasCoral),
        DEAD_FIRE_CORAL(WorldRequirements::hasCoral),
        DEAD_HORN_CORAL(WorldRequirements::hasCoral),
        DEAD_TUBE_CORAL(WorldRequirements::hasCoral),
        TUBE_CORAL_FAN(WorldRequirements::hasCoral),
        BRAIN_CORAL_FAN(WorldRequirements::hasCoral),
        BUBBLE_CORAL_FAN(WorldRequirements::hasCoral),
        FIRE_CORAL_FAN(WorldRequirements::hasCoral),
        HORN_CORAL_FAN(WorldRequirements::hasCoral),
        DEAD_TUBE_CORAL_FAN(WorldRequirements::hasCoral),
        DEAD_BRAIN_CORAL_FAN(WorldRequirements::hasCoral),
        DEAD_BUBBLE_CORAL_FAN(WorldRequirements::hasCoral),
        DEAD_FIRE_CORAL_FAN(WorldRequirements::hasCoral),
        DEAD_HORN_CORAL_FAN(WorldRequirements::hasCoral),
        BLUE_ICE(WorldRequirements::hasIce),
        CONDUIT(WorldRequirements::hasHeartOfTheSea),
        POLISHED_GRANITE_STAIRS(WorldRequirements::guaranteed),
        SMOOTH_RED_SANDSTONE_STAIRS(WorldRequirements::hasRedSand),
        MOSSY_STONE_BRICK_STAIRS(WorldRequirements::hasMossyBrick),
        POLISHED_DIORITE_STAIRS(WorldRequirements::guaranteed),
        MOSSY_COBBLESTONE_STAIRS(WorldRequirements::hasMossyCobble),
        END_STONE_BRICK_STAIRS(WorldRequirements::hasEndAccess),
        STONE_STAIRS(WorldRequirements::guaranteed),
        SMOOTH_SANDSTONE_STAIRS(WorldRequirements::guaranteed),
        SMOOTH_QUARTZ_STAIRS(WorldRequirements::guaranteed),
        GRANITE_STAIRS(WorldRequirements::guaranteed),
        ANDESITE_STAIRS(WorldRequirements::guaranteed),
        RED_NETHER_BRICK_STAIRS(WorldRequirements::hasFortress),
        POLISHED_ANDESITE_STAIRS(WorldRequirements::guaranteed),
        DIORITE_STAIRS(WorldRequirements::guaranteed),
        COBBLED_DEEPSLATE_STAIRS(WorldRequirements::guaranteed),
        POLISHED_DEEPSLATE_STAIRS(WorldRequirements::guaranteed),
        DEEPSLATE_BRICK_STAIRS(WorldRequirements::guaranteed),
        DEEPSLATE_TILE_STAIRS(WorldRequirements::guaranteed),
        POLISHED_GRANITE_SLAB(WorldRequirements::guaranteed),
        SMOOTH_RED_SANDSTONE_SLAB(WorldRequirements::hasRedSand),
        MOSSY_STONE_BRICK_SLAB(WorldRequirements::hasMossyBrick),
        POLISHED_DIORITE_SLAB(WorldRequirements::guaranteed),
        MOSSY_COBBLESTONE_SLAB(WorldRequirements::hasMossyCobble),
        END_STONE_BRICK_SLAB(WorldRequirements::hasEndAccess),
        SMOOTH_SANDSTONE_SLAB(WorldRequirements::guaranteed),
        SMOOTH_QUARTZ_SLAB(WorldRequirements::guaranteed),
        GRANITE_SLAB(WorldRequirements::guaranteed),
        ANDESITE_SLAB(WorldRequirements::guaranteed),
        RED_NETHER_BRICK_SLAB(WorldRequirements::hasFortress),
        POLISHED_ANDESITE_SLAB(WorldRequirements::guaranteed),
        DIORITE_SLAB(WorldRequirements::guaranteed),
        COBBLED_DEEPSLATE_SLAB(WorldRequirements::guaranteed),
        POLISHED_DEEPSLATE_SLAB(WorldRequirements::guaranteed),
        DEEPSLATE_BRICK_SLAB(WorldRequirements::guaranteed),
        DEEPSLATE_TILE_SLAB(WorldRequirements::guaranteed),
        SCAFFOLDING(WorldRequirements::hasBamboo),
        REDSTONE(WorldRequirements::guaranteed),
        REDSTONE_TORCH(WorldRequirements::guaranteed),
        REDSTONE_BLOCK(WorldRequirements::guaranteed),
        REPEATER(WorldRequirements::guaranteed),
        COMPARATOR(WorldRequirements::guaranteed),
        PISTON(WorldRequirements::guaranteed),
        STICKY_PISTON(WorldRequirements::guaranteed),
        SLIME_BLOCK(WorldRequirements::guaranteed),
        HONEY_BLOCK(WorldRequirements::hasBees),
        OBSERVER(WorldRequirements::guaranteed),
        HOPPER(WorldRequirements::guaranteed),
        DISPENSER(WorldRequirements::guaranteed),
        DROPPER(WorldRequirements::guaranteed),
        LECTERN(WorldRequirements::guaranteed),
        TARGET(WorldRequirements::guaranteed),
        LEVER(WorldRequirements::guaranteed),
        LIGHTNING_ROD(WorldRequirements::guaranteed),
        DAYLIGHT_DETECTOR(WorldRequirements::guaranteed),
        SCULK_SENSOR(WorldRequirements::hasDeepDark),
        CALIBRATED_SCULK_SENSOR(WorldRequirements::hasDeepDark),
        TRIPWIRE_HOOK(WorldRequirements::guaranteed),
        TRAPPED_CHEST(WorldRequirements::guaranteed),
        TNT(WorldRequirements::guaranteed),
        REDSTONE_LAMP(WorldRequirements::guaranteed),
        NOTE_BLOCK(WorldRequirements::guaranteed),
        STONE_BUTTON(WorldRequirements::guaranteed),
        POLISHED_BLACKSTONE_BUTTON(WorldRequirements::guaranteed),
        OAK_BUTTON(WorldRequirements::hasOak),
        SPRUCE_BUTTON(WorldRequirements::hasSpruce),
        BIRCH_BUTTON(WorldRequirements::hasBirch),
        JUNGLE_BUTTON(WorldRequirements::hasJungle),
        ACACIA_BUTTON(WorldRequirements::hasAcacia),
        CHERRY_BUTTON(WorldRequirements::hasCherry),
        DARK_OAK_BUTTON(WorldRequirements::hasDarkOak),
        PALE_OAK_BUTTON(WorldRequirements::hasPaleOak),
        MANGROVE_BUTTON(WorldRequirements::hasMangrove),
        BAMBOO_BUTTON(WorldRequirements::hasBamboo),
        CRIMSON_BUTTON(WorldRequirements::hasCrimson),
        WARPED_BUTTON(WorldRequirements::hasWarped),
        STONE_PRESSURE_PLATE(WorldRequirements::guaranteed),
        POLISHED_BLACKSTONE_PRESSURE_PLATE(WorldRequirements::guaranteed),
        LIGHT_WEIGHTED_PRESSURE_PLATE(WorldRequirements::guaranteed),
        HEAVY_WEIGHTED_PRESSURE_PLATE(WorldRequirements::guaranteed),
        OAK_PRESSURE_PLATE(WorldRequirements::hasOak),
        SPRUCE_PRESSURE_PLATE(WorldRequirements::hasSpruce),
        BIRCH_PRESSURE_PLATE(WorldRequirements::hasBirch),
        JUNGLE_PRESSURE_PLATE(WorldRequirements::hasJungle),
        ACACIA_PRESSURE_PLATE(WorldRequirements::hasAcacia),
        CHERRY_PRESSURE_PLATE(WorldRequirements::hasCherry),
        DARK_OAK_PRESSURE_PLATE(WorldRequirements::hasDarkOak),
        PALE_OAK_PRESSURE_PLATE(WorldRequirements::hasPaleOak),
        MANGROVE_PRESSURE_PLATE(WorldRequirements::hasMangrove),
        BAMBOO_PRESSURE_PLATE(WorldRequirements::hasBamboo),
        CRIMSON_PRESSURE_PLATE(WorldRequirements::hasCrimson),
        WARPED_PRESSURE_PLATE(WorldRequirements::hasWarped),
        IRON_DOOR(WorldRequirements::guaranteed),
        OAK_DOOR(WorldRequirements::hasOak),
        SPRUCE_DOOR(WorldRequirements::hasSpruce),
        BIRCH_DOOR(WorldRequirements::hasBirch),
        JUNGLE_DOOR(WorldRequirements::hasJungle),
        ACACIA_DOOR(WorldRequirements::hasAcacia),
        CHERRY_DOOR(WorldRequirements::hasCherry),
        DARK_OAK_DOOR(WorldRequirements::hasDarkOak),
        PALE_OAK_DOOR(WorldRequirements::hasPaleOak),
        MANGROVE_DOOR(WorldRequirements::hasMangrove),
        BAMBOO_DOOR(WorldRequirements::hasBamboo),
        CRIMSON_DOOR(WorldRequirements::hasCrimson),
        WARPED_DOOR(WorldRequirements::hasWarped),
        COPPER_DOOR(WorldRequirements::guaranteed),
        EXPOSED_COPPER_DOOR(WorldRequirements::guaranteed),
        WEATHERED_COPPER_DOOR(WorldRequirements::guaranteed),
        OXIDIZED_COPPER_DOOR(WorldRequirements::guaranteed),
        WAXED_COPPER_DOOR(WorldRequirements::guaranteed),
        WAXED_EXPOSED_COPPER_DOOR(WorldRequirements::guaranteed),
        WAXED_WEATHERED_COPPER_DOOR(WorldRequirements::guaranteed),
        WAXED_OXIDIZED_COPPER_DOOR(WorldRequirements::guaranteed),
        IRON_TRAPDOOR(WorldRequirements::guaranteed),
        OAK_TRAPDOOR(WorldRequirements::hasOak),
        SPRUCE_TRAPDOOR(WorldRequirements::hasSpruce),
        BIRCH_TRAPDOOR(WorldRequirements::hasBirch),
        JUNGLE_TRAPDOOR(WorldRequirements::hasJungle),
        ACACIA_TRAPDOOR(WorldRequirements::hasAcacia),
        CHERRY_TRAPDOOR(WorldRequirements::hasCherry),
        DARK_OAK_TRAPDOOR(WorldRequirements::hasDarkOak),
        PALE_OAK_TRAPDOOR(WorldRequirements::hasPaleOak),
        MANGROVE_TRAPDOOR(WorldRequirements::hasMangrove),
        BAMBOO_TRAPDOOR(WorldRequirements::hasBamboo),
        CRIMSON_TRAPDOOR(WorldRequirements::hasCrimson),
        WARPED_TRAPDOOR(WorldRequirements::hasWarped),
        COPPER_TRAPDOOR(WorldRequirements::guaranteed),
        EXPOSED_COPPER_TRAPDOOR(WorldRequirements::guaranteed),
        WEATHERED_COPPER_TRAPDOOR(WorldRequirements::guaranteed),
        OXIDIZED_COPPER_TRAPDOOR(WorldRequirements::guaranteed),
        WAXED_COPPER_TRAPDOOR(WorldRequirements::guaranteed),
        WAXED_EXPOSED_COPPER_TRAPDOOR(WorldRequirements::guaranteed),
        WAXED_WEATHERED_COPPER_TRAPDOOR(WorldRequirements::guaranteed),
        WAXED_OXIDIZED_COPPER_TRAPDOOR(WorldRequirements::guaranteed),
        OAK_FENCE_GATE(WorldRequirements::hasOak),
        SPRUCE_FENCE_GATE(WorldRequirements::hasSpruce),
        BIRCH_FENCE_GATE(WorldRequirements::hasBirch),
        JUNGLE_FENCE_GATE(WorldRequirements::hasJungle),
        ACACIA_FENCE_GATE(WorldRequirements::hasAcacia),
        CHERRY_FENCE_GATE(WorldRequirements::hasCherry),
        DARK_OAK_FENCE_GATE(WorldRequirements::hasDarkOak),
        PALE_OAK_FENCE_GATE(WorldRequirements::hasPaleOak),
        MANGROVE_FENCE_GATE(WorldRequirements::hasMangrove),
        BAMBOO_FENCE_GATE(WorldRequirements::hasBamboo),
        CRIMSON_FENCE_GATE(WorldRequirements::hasCrimson),
        WARPED_FENCE_GATE(WorldRequirements::hasWarped),
        POWERED_RAIL(WorldRequirements::guaranteed),
        DETECTOR_RAIL(WorldRequirements::guaranteed),
        RAIL(WorldRequirements::guaranteed),
        ACTIVATOR_RAIL(WorldRequirements::guaranteed),
        SADDLE(WorldRequirements::guaranteed),
        MINECART(WorldRequirements::guaranteed),
        CHEST_MINECART(WorldRequirements::guaranteed),
        FURNACE_MINECART(WorldRequirements::guaranteed),
        TNT_MINECART(WorldRequirements::guaranteed),
        HOPPER_MINECART(WorldRequirements::guaranteed),
        CARROT_ON_A_STICK(WorldRequirements::guaranteed),
        WARPED_FUNGUS_ON_A_STICK(WorldRequirements::hasWarped),
        PHANTOM_MEMBRANE(WorldRequirements::guaranteed),
        ELYTRA(WorldRequirements::hasEndAccess),
        OAK_BOAT(WorldRequirements::hasOak),
        OAK_CHEST_BOAT(WorldRequirements::hasOak),
        SPRUCE_BOAT(WorldRequirements::hasSpruce),
        SPRUCE_CHEST_BOAT(WorldRequirements::hasSpruce),
        BIRCH_BOAT(WorldRequirements::hasBirch),
        BIRCH_CHEST_BOAT(WorldRequirements::hasBirch),
        JUNGLE_BOAT(WorldRequirements::hasJungle),
        JUNGLE_CHEST_BOAT(WorldRequirements::hasJungle),
        ACACIA_BOAT(WorldRequirements::hasAcacia),
        ACACIA_CHEST_BOAT(WorldRequirements::hasAcacia),
        CHERRY_BOAT(WorldRequirements::hasCherry),
        CHERRY_CHEST_BOAT(WorldRequirements::hasCherry),
        DARK_OAK_BOAT(WorldRequirements::hasDarkOak),
        DARK_OAK_CHEST_BOAT(WorldRequirements::hasDarkOak),
        PALE_OAK_BOAT(WorldRequirements::hasPaleOak),
        PALE_OAK_CHEST_BOAT(WorldRequirements::hasPaleOak),
        MANGROVE_BOAT(WorldRequirements::hasMangrove),
        MANGROVE_CHEST_BOAT(WorldRequirements::hasMangrove),
        BAMBOO_RAFT(WorldRequirements::hasBamboo),
        BAMBOO_CHEST_RAFT(WorldRequirements::hasBamboo),
        STRUCTURE_BLOCK(WorldRequirements::unobtainable),
        JIGSAW(WorldRequirements::unobtainable),
        TURTLE_HELMET(WorldRequirements::hasTurtleBreeding),
        TURTLE_SCUTE(WorldRequirements::hasTurtleBreeding),
        ARMADILLO_SCUTE(WorldRequirements::hasArmadillo),
        WOLF_ARMOR(WorldRequirements::hasArmadillo),
        FLINT_AND_STEEL(WorldRequirements::guaranteed),
        BOWL(WorldRequirements::guaranteed),
        APPLE(WorldRequirements::hasApple),
        BOW(WorldRequirements::guaranteed),
        ARROW(WorldRequirements::guaranteed),
        COAL(WorldRequirements::guaranteed),
        CHARCOAL(WorldRequirements::guaranteed),
        DIAMOND(WorldRequirements::guaranteed),
        EMERALD(WorldRequirements::hasEmerald),
        LAPIS_LAZULI(WorldRequirements::guaranteed),
        QUARTZ(WorldRequirements::guaranteed),
        AMETHYST_SHARD(WorldRequirements::guaranteed),
        RAW_IRON(WorldRequirements::guaranteed),
        IRON_INGOT(WorldRequirements::guaranteed),
        RAW_COPPER(WorldRequirements::guaranteed),
        COPPER_INGOT(WorldRequirements::guaranteed),
        RAW_GOLD(WorldRequirements::guaranteed),
        GOLD_INGOT(WorldRequirements::guaranteed),
        NETHERITE_INGOT(WorldRequirements::guaranteed),
        NETHERITE_SCRAP(WorldRequirements::guaranteed),
        WOODEN_SWORD(WorldRequirements::guaranteed),
        WOODEN_SHOVEL(WorldRequirements::guaranteed),
        WOODEN_PICKAXE(WorldRequirements::guaranteed),
        WOODEN_AXE(WorldRequirements::guaranteed),
        WOODEN_HOE(WorldRequirements::guaranteed),
        STONE_SWORD(WorldRequirements::guaranteed),
        STONE_SHOVEL(WorldRequirements::guaranteed),
        STONE_PICKAXE(WorldRequirements::guaranteed),
        STONE_AXE(WorldRequirements::guaranteed),
        STONE_HOE(WorldRequirements::guaranteed),
        GOLDEN_SWORD(WorldRequirements::guaranteed),
        GOLDEN_SHOVEL(WorldRequirements::guaranteed),
        GOLDEN_PICKAXE(WorldRequirements::guaranteed),
        GOLDEN_AXE(WorldRequirements::guaranteed),
        GOLDEN_HOE(WorldRequirements::guaranteed),
        IRON_SWORD(WorldRequirements::guaranteed),
        IRON_SHOVEL(WorldRequirements::guaranteed),
        IRON_PICKAXE(WorldRequirements::guaranteed),
        IRON_AXE(WorldRequirements::guaranteed),
        IRON_HOE(WorldRequirements::guaranteed),
        DIAMOND_SWORD(WorldRequirements::guaranteed),
        DIAMOND_SHOVEL(WorldRequirements::guaranteed),
        DIAMOND_PICKAXE(WorldRequirements::guaranteed),
        DIAMOND_AXE(WorldRequirements::guaranteed),
        DIAMOND_HOE(WorldRequirements::guaranteed),
        NETHERITE_SWORD(WorldRequirements::hasBastion),
        NETHERITE_SHOVEL(WorldRequirements::hasBastion),
        NETHERITE_PICKAXE(WorldRequirements::hasBastion),
        NETHERITE_AXE(WorldRequirements::hasBastion),
        NETHERITE_HOE(WorldRequirements::hasBastion),
        STICK(WorldRequirements::guaranteed),
        MUSHROOM_STEW(WorldRequirements::guaranteed),
        STRING(WorldRequirements::guaranteed),
        FEATHER(WorldRequirements::guaranteed),
        GUNPOWDER(WorldRequirements::guaranteed),
        WHEAT_SEEDS(WorldRequirements::guaranteed),
        WHEAT(WorldRequirements::guaranteed),
        BREAD(WorldRequirements::guaranteed),
        LEATHER_HELMET(WorldRequirements::guaranteed),
        LEATHER_CHESTPLATE(WorldRequirements::guaranteed),
        LEATHER_LEGGINGS(WorldRequirements::guaranteed),
        LEATHER_BOOTS(WorldRequirements::guaranteed),
        CHAINMAIL_HELMET(WorldRequirements::hasVillage),
        CHAINMAIL_CHESTPLATE(WorldRequirements::hasVillage),
        CHAINMAIL_LEGGINGS(WorldRequirements::hasVillage),
        CHAINMAIL_BOOTS(WorldRequirements::hasVillage),
        IRON_HELMET(WorldRequirements::guaranteed),
        IRON_CHESTPLATE(WorldRequirements::guaranteed),
        IRON_LEGGINGS(WorldRequirements::guaranteed),
        IRON_BOOTS(WorldRequirements::guaranteed),
        DIAMOND_HELMET(WorldRequirements::guaranteed),
        DIAMOND_CHESTPLATE(WorldRequirements::guaranteed),
        DIAMOND_LEGGINGS(WorldRequirements::guaranteed),
        DIAMOND_BOOTS(WorldRequirements::guaranteed),
        GOLDEN_HELMET(WorldRequirements::guaranteed),
        GOLDEN_CHESTPLATE(WorldRequirements::guaranteed),
        GOLDEN_LEGGINGS(WorldRequirements::guaranteed),
        GOLDEN_BOOTS(WorldRequirements::guaranteed),
        NETHERITE_HELMET(WorldRequirements::hasBastion),
        NETHERITE_CHESTPLATE(WorldRequirements::hasBastion),
        NETHERITE_LEGGINGS(WorldRequirements::hasBastion),
        NETHERITE_BOOTS(WorldRequirements::hasBastion),
        FLINT(WorldRequirements::guaranteed),
        PORKCHOP(WorldRequirements::guaranteed),
        COOKED_PORKCHOP(WorldRequirements::guaranteed),
        PAINTING(WorldRequirements::guaranteed),
        GOLDEN_APPLE(WorldRequirements::hasApple),
        ENCHANTED_GOLDEN_APPLE(WorldRequirements::inaccessible),
        OAK_SIGN(WorldRequirements::hasOak),
        SPRUCE_SIGN(WorldRequirements::hasSpruce),
        BIRCH_SIGN(WorldRequirements::hasBirch),
        JUNGLE_SIGN(WorldRequirements::hasJungle),
        ACACIA_SIGN(WorldRequirements::hasAcacia),
        CHERRY_SIGN(WorldRequirements::hasCherry),
        DARK_OAK_SIGN(WorldRequirements::hasDarkOak),
        PALE_OAK_SIGN(WorldRequirements::hasPaleOak),
        MANGROVE_SIGN(WorldRequirements::hasMangrove),
        BAMBOO_SIGN(WorldRequirements::hasBamboo),
        CRIMSON_SIGN(WorldRequirements::hasCrimson),
        WARPED_SIGN(WorldRequirements::hasWarped),
        OAK_HANGING_SIGN(WorldRequirements::hasOak),
        SPRUCE_HANGING_SIGN(WorldRequirements::hasSpruce),
        BIRCH_HANGING_SIGN(WorldRequirements::hasBirch),
        JUNGLE_HANGING_SIGN(WorldRequirements::hasJungle),
        ACACIA_HANGING_SIGN(WorldRequirements::hasAcacia),
        CHERRY_HANGING_SIGN(WorldRequirements::hasCherry),
        DARK_OAK_HANGING_SIGN(WorldRequirements::hasDarkOak),
        PALE_OAK_HANGING_SIGN(WorldRequirements::hasPaleOak),
        MANGROVE_HANGING_SIGN(WorldRequirements::hasMangrove),
        BAMBOO_HANGING_SIGN(WorldRequirements::hasBamboo),
        CRIMSON_HANGING_SIGN(WorldRequirements::hasCrimson),
        WARPED_HANGING_SIGN(WorldRequirements::hasWarped),
        BUCKET(WorldRequirements::guaranteed),
        WATER_BUCKET(WorldRequirements::guaranteed),
        LAVA_BUCKET(WorldRequirements::guaranteed),
        POWDER_SNOW_BUCKET(WorldRequirements::hasPowderedSnow),
        SNOWBALL(WorldRequirements::hasSnow),
        LEATHER(WorldRequirements::guaranteed),
        MILK_BUCKET(WorldRequirements::guaranteed),
        PUFFERFISH_BUCKET(WorldRequirements::hasTropicalFish),
        SALMON_BUCKET(WorldRequirements::hasSalmon),
        COD_BUCKET(WorldRequirements::hasCod),
        TROPICAL_FISH_BUCKET(WorldRequirements::hasTropicalFish),
        AXOLOTL_BUCKET(WorldRequirements::hasLushCave),
        TADPOLE_BUCKET(WorldRequirements::hasFrog),
        BRICK(WorldRequirements::guaranteed),
        CLAY_BALL(WorldRequirements::guaranteed),
        DRIED_KELP_BLOCK(WorldRequirements::hasKelp),
        PAPER(WorldRequirements::guaranteed),
        BOOK(WorldRequirements::guaranteed),
        SLIME_BALL(WorldRequirements::guaranteed),
        EGG(WorldRequirements::guaranteed),
        COMPASS(WorldRequirements::guaranteed),
        RECOVERY_COMPASS(WorldRequirements::hasAncientCity),
        BUNDLE(WorldRequirements::hasRabbit),
        WHITE_BUNDLE(WorldRequirements::hasRabbit),
        ORANGE_BUNDLE(WorldRequirements::hasRabbit),
        MAGENTA_BUNDLE(WorldRequirements::hasRabbit),
        LIGHT_BLUE_BUNDLE(WorldRequirements::hasRabbit),
        YELLOW_BUNDLE(WorldRequirements::hasRabbit),
        LIME_BUNDLE(WorldRequirements::hasLimeBundle),
        PINK_BUNDLE(WorldRequirements::hasRabbit),
        GRAY_BUNDLE(WorldRequirements::hasRabbit),
        LIGHT_GRAY_BUNDLE(WorldRequirements::hasRabbit),
        CYAN_BUNDLE(WorldRequirements::hasCyanBundle),
        PURPLE_BUNDLE(WorldRequirements::hasRabbit),
        BLUE_BUNDLE(WorldRequirements::hasRabbit),
        BROWN_BUNDLE(WorldRequirements::hasBrownBundle),
        GREEN_BUNDLE(WorldRequirements::hasGreenBundle),
        RED_BUNDLE(WorldRequirements::hasRabbit),
        BLACK_BUNDLE(WorldRequirements::hasRabbit),
        FISHING_ROD(WorldRequirements::guaranteed),
        CLOCK(WorldRequirements::guaranteed),
        SPYGLASS(WorldRequirements::guaranteed),
        GLOWSTONE_DUST(WorldRequirements::guaranteed),
        COD(WorldRequirements::hasCod),
        SALMON(WorldRequirements::hasSalmon),
        TROPICAL_FISH(WorldRequirements::hasTropicalFish),
        PUFFERFISH(WorldRequirements::hasTropicalFish),
        COOKED_COD(WorldRequirements::hasCod),
        COOKED_SALMON(WorldRequirements::hasSalmon),
        INK_SAC(WorldRequirements::guaranteed),
        GLOW_INK_SAC(WorldRequirements::guaranteed),
        COCOA_BEANS(WorldRequirements::hasBrown),
        WHITE_DYE(WorldRequirements::guaranteed),
        ORANGE_DYE(WorldRequirements::guaranteed),
        MAGENTA_DYE(WorldRequirements::guaranteed),
        LIGHT_BLUE_DYE(WorldRequirements::guaranteed),
        YELLOW_DYE(WorldRequirements::guaranteed),
        LIME_DYE(WorldRequirements::hasLime),
        PINK_DYE(WorldRequirements::guaranteed),
        GRAY_DYE(WorldRequirements::guaranteed),
        LIGHT_GRAY_DYE(WorldRequirements::guaranteed),
        CYAN_DYE(WorldRequirements::hasCyan),
        PURPLE_DYE(WorldRequirements::guaranteed),
        BLUE_DYE(WorldRequirements::guaranteed),
        BROWN_DYE(WorldRequirements::hasBrown),
        GREEN_DYE(WorldRequirements::hasGreen),
        RED_DYE(WorldRequirements::guaranteed),
        BLACK_DYE(WorldRequirements::guaranteed),
        BONE_MEAL(WorldRequirements::guaranteed),
        BONE(WorldRequirements::guaranteed),
        SUGAR(WorldRequirements::guaranteed),
        CAKE(WorldRequirements::guaranteed),
        WHITE_BED(WorldRequirements::guaranteed),
        ORANGE_BED(WorldRequirements::guaranteed),
        MAGENTA_BED(WorldRequirements::guaranteed),
        LIGHT_BLUE_BED(WorldRequirements::guaranteed),
        YELLOW_BED(WorldRequirements::guaranteed),
        LIME_BED(WorldRequirements::hasLime),
        PINK_BED(WorldRequirements::guaranteed),
        GRAY_BED(WorldRequirements::guaranteed),
        LIGHT_GRAY_BED(WorldRequirements::guaranteed),
        CYAN_BED(WorldRequirements::hasCyan),
        PURPLE_BED(WorldRequirements::guaranteed),
        BLUE_BED(WorldRequirements::guaranteed),
        BROWN_BED(WorldRequirements::hasBrown),
        GREEN_BED(WorldRequirements::hasGreen),
        RED_BED(WorldRequirements::guaranteed),
        BLACK_BED(WorldRequirements::guaranteed),
        COOKIE(WorldRequirements::hasBrown),
        CRAFTER(WorldRequirements::guaranteed),
        FILLED_MAP(WorldRequirements::guaranteed),
        SHEARS(WorldRequirements::guaranteed),
        MELON_SLICE(WorldRequirements::hasJungle),
        DRIED_KELP(WorldRequirements::hasKelp),
        PUMPKIN_SEEDS(WorldRequirements::guaranteed),
        MELON_SEEDS(WorldRequirements::hasJungle),
        BEEF(WorldRequirements::guaranteed),
        COOKED_BEEF(WorldRequirements::guaranteed),
        CHICKEN(WorldRequirements::guaranteed),
        COOKED_CHICKEN(WorldRequirements::guaranteed),
        ROTTEN_FLESH(WorldRequirements::guaranteed),
        ENDER_PEARL(WorldRequirements::guaranteed),
        BLAZE_ROD(WorldRequirements::hasFortress),
        GHAST_TEAR(WorldRequirements::guaranteed),
        GOLD_NUGGET(WorldRequirements::guaranteed),
        NETHER_WART(WorldRequirements::hasFortress),
        GLASS_BOTTLE(WorldRequirements::guaranteed),
        POTION(WorldRequirements::guaranteed),
        SPIDER_EYE(WorldRequirements::guaranteed),
        FERMENTED_SPIDER_EYE(WorldRequirements::guaranteed),
        BLAZE_POWDER(WorldRequirements::hasFortress),
        MAGMA_CREAM(WorldRequirements::guaranteed),
        BREWING_STAND(WorldRequirements::hasFortress),
        CAULDRON(WorldRequirements::guaranteed),
        ENDER_EYE(WorldRequirements::hasFortress),
        GLISTERING_MELON_SLICE(WorldRequirements::hasJungle),
        ARMADILLO_SPAWN_EGG(WorldRequirements::unobtainable),
        ALLAY_SPAWN_EGG(WorldRequirements::unobtainable),
        AXOLOTL_SPAWN_EGG(WorldRequirements::unobtainable),
        BAT_SPAWN_EGG(WorldRequirements::unobtainable),
        BEE_SPAWN_EGG(WorldRequirements::unobtainable),
        BLAZE_SPAWN_EGG(WorldRequirements::unobtainable),
        BOGGED_SPAWN_EGG(WorldRequirements::unobtainable),
        BREEZE_SPAWN_EGG(WorldRequirements::unobtainable),
        CAT_SPAWN_EGG(WorldRequirements::unobtainable),
        CAMEL_SPAWN_EGG(WorldRequirements::unobtainable),
        CAVE_SPIDER_SPAWN_EGG(WorldRequirements::unobtainable),
        CHICKEN_SPAWN_EGG(WorldRequirements::unobtainable),
        COD_SPAWN_EGG(WorldRequirements::unobtainable),
        COW_SPAWN_EGG(WorldRequirements::unobtainable),
        CREEPER_SPAWN_EGG(WorldRequirements::unobtainable),
        DOLPHIN_SPAWN_EGG(WorldRequirements::unobtainable),
        DONKEY_SPAWN_EGG(WorldRequirements::unobtainable),
        DROWNED_SPAWN_EGG(WorldRequirements::unobtainable),
        ELDER_GUARDIAN_SPAWN_EGG(WorldRequirements::unobtainable),
        ENDER_DRAGON_SPAWN_EGG(WorldRequirements::unobtainable),
        ENDERMAN_SPAWN_EGG(WorldRequirements::unobtainable),
        ENDERMITE_SPAWN_EGG(WorldRequirements::unobtainable),
        EVOKER_SPAWN_EGG(WorldRequirements::unobtainable),
        FOX_SPAWN_EGG(WorldRequirements::unobtainable),
        FROG_SPAWN_EGG(WorldRequirements::unobtainable),
        GHAST_SPAWN_EGG(WorldRequirements::unobtainable),
        GLOW_SQUID_SPAWN_EGG(WorldRequirements::unobtainable),
        GOAT_SPAWN_EGG(WorldRequirements::unobtainable),
        GUARDIAN_SPAWN_EGG(WorldRequirements::unobtainable),
        HOGLIN_SPAWN_EGG(WorldRequirements::unobtainable),
        HORSE_SPAWN_EGG(WorldRequirements::unobtainable),
        HUSK_SPAWN_EGG(WorldRequirements::unobtainable),
        IRON_GOLEM_SPAWN_EGG(WorldRequirements::unobtainable),
        LLAMA_SPAWN_EGG(WorldRequirements::unobtainable),
        MAGMA_CUBE_SPAWN_EGG(WorldRequirements::unobtainable),
        MOOSHROOM_SPAWN_EGG(WorldRequirements::unobtainable),
        MULE_SPAWN_EGG(WorldRequirements::unobtainable),
        OCELOT_SPAWN_EGG(WorldRequirements::unobtainable),
        PANDA_SPAWN_EGG(WorldRequirements::unobtainable),
        PARROT_SPAWN_EGG(WorldRequirements::unobtainable),
        PHANTOM_SPAWN_EGG(WorldRequirements::unobtainable),
        PIG_SPAWN_EGG(WorldRequirements::unobtainable),
        PIGLIN_SPAWN_EGG(WorldRequirements::unobtainable),
        PIGLIN_BRUTE_SPAWN_EGG(WorldRequirements::unobtainable),
        PILLAGER_SPAWN_EGG(WorldRequirements::unobtainable),
        POLAR_BEAR_SPAWN_EGG(WorldRequirements::unobtainable),
        PUFFERFISH_SPAWN_EGG(WorldRequirements::unobtainable),
        RABBIT_SPAWN_EGG(WorldRequirements::unobtainable),
        RAVAGER_SPAWN_EGG(WorldRequirements::unobtainable),
        SALMON_SPAWN_EGG(WorldRequirements::unobtainable),
        SHEEP_SPAWN_EGG(WorldRequirements::unobtainable),
        SHULKER_SPAWN_EGG(WorldRequirements::unobtainable),
        SILVERFISH_SPAWN_EGG(WorldRequirements::unobtainable),
        SKELETON_SPAWN_EGG(WorldRequirements::unobtainable),
        SKELETON_HORSE_SPAWN_EGG(WorldRequirements::unobtainable),
        SLIME_SPAWN_EGG(WorldRequirements::unobtainable),
        SNIFFER_SPAWN_EGG(WorldRequirements::unobtainable),
        SNOW_GOLEM_SPAWN_EGG(WorldRequirements::unobtainable),
        SPIDER_SPAWN_EGG(WorldRequirements::unobtainable),
        SQUID_SPAWN_EGG(WorldRequirements::unobtainable),
        STRAY_SPAWN_EGG(WorldRequirements::unobtainable),
        STRIDER_SPAWN_EGG(WorldRequirements::unobtainable),
        TADPOLE_SPAWN_EGG(WorldRequirements::unobtainable),
        TRADER_LLAMA_SPAWN_EGG(WorldRequirements::unobtainable),
        TROPICAL_FISH_SPAWN_EGG(WorldRequirements::unobtainable),
        TURTLE_SPAWN_EGG(WorldRequirements::unobtainable),
        VEX_SPAWN_EGG(WorldRequirements::unobtainable),
        VILLAGER_SPAWN_EGG(WorldRequirements::unobtainable),
        VINDICATOR_SPAWN_EGG(WorldRequirements::unobtainable),
        WANDERING_TRADER_SPAWN_EGG(WorldRequirements::unobtainable),
        WARDEN_SPAWN_EGG(WorldRequirements::unobtainable),
        WITCH_SPAWN_EGG(WorldRequirements::unobtainable),
        WITHER_SPAWN_EGG(WorldRequirements::unobtainable),
        WITHER_SKELETON_SPAWN_EGG(WorldRequirements::unobtainable),
        WOLF_SPAWN_EGG(WorldRequirements::unobtainable),
        ZOGLIN_SPAWN_EGG(WorldRequirements::unobtainable),
        CREAKING_SPAWN_EGG(WorldRequirements::unobtainable),
        ZOMBIE_SPAWN_EGG(WorldRequirements::unobtainable),
        ZOMBIE_HORSE_SPAWN_EGG(WorldRequirements::unobtainable),
        ZOMBIE_VILLAGER_SPAWN_EGG(WorldRequirements::unobtainable),
        ZOMBIFIED_PIGLIN_SPAWN_EGG(WorldRequirements::unobtainable),
        EXPERIENCE_BOTTLE(WorldRequirements::hasVillage),
        FIRE_CHARGE(WorldRequirements::hasFortress),
        WIND_CHARGE(WorldRequirements::hasTrialChamber),
        WRITABLE_BOOK(WorldRequirements::guaranteed),
        WRITTEN_BOOK(WorldRequirements::guaranteed),
        BREEZE_ROD(WorldRequirements::hasTrialChamber),
        MACE(WorldRequirements::inaccessible),
        ITEM_FRAME(WorldRequirements::guaranteed),
        GLOW_ITEM_FRAME(WorldRequirements::guaranteed),
        FLOWER_POT(WorldRequirements::guaranteed),
        CARROT(WorldRequirements::guaranteed),
        POTATO(WorldRequirements::guaranteed),
        BAKED_POTATO(WorldRequirements::guaranteed),
        POISONOUS_POTATO(WorldRequirements::hasPoisonPotato),
        MAP(WorldRequirements::guaranteed),
        GOLDEN_CARROT(WorldRequirements::guaranteed),
        SKELETON_SKULL(WorldRequirements::guaranteed),
        WITHER_SKELETON_SKULL(WorldRequirements::hasFortress),
        PLAYER_HEAD(WorldRequirements::unobtainable),
        ZOMBIE_HEAD(WorldRequirements::guaranteed),
        CREEPER_HEAD(WorldRequirements::guaranteed),
        DRAGON_HEAD(WorldRequirements::hasEndAccess),
        PIGLIN_HEAD(WorldRequirements::guaranteed),
        NETHER_STAR(WorldRequirements::hasFortress),
        PUMPKIN_PIE(WorldRequirements::guaranteed),
        FIREWORK_ROCKET(WorldRequirements::guaranteed),
        FIREWORK_STAR(WorldRequirements::guaranteed),
        ENCHANTED_BOOK(WorldRequirements::guaranteed),
        NETHER_BRICK(WorldRequirements::guaranteed),
        RESIN_BRICK(WorldRequirements::guaranteed),
        PRISMARINE_SHARD(WorldRequirements::hasOceanMonument),
        PRISMARINE_CRYSTALS(WorldRequirements::hasOceanMonument),
        RABBIT(WorldRequirements::hasRabbit),
        COOKED_RABBIT(WorldRequirements::hasRabbit),
        RABBIT_STEW(WorldRequirements::hasRabbit),
        RABBIT_FOOT(WorldRequirements::hasRabbit),
        RABBIT_HIDE(WorldRequirements::hasRabbit),
        ARMOR_STAND(WorldRequirements::guaranteed),
        IRON_HORSE_ARMOR(WorldRequirements::inaccessible),
        GOLDEN_HORSE_ARMOR(WorldRequirements::inaccessible),
        DIAMOND_HORSE_ARMOR(WorldRequirements::inaccessible),
        LEATHER_HORSE_ARMOR(WorldRequirements::guaranteed),
        LEAD(WorldRequirements::guaranteed),
        NAME_TAG(WorldRequirements::guaranteed),
        COMMAND_BLOCK_MINECART(WorldRequirements::unobtainable),
        MUTTON(WorldRequirements::guaranteed),
        COOKED_MUTTON(WorldRequirements::guaranteed),
        WHITE_BANNER(WorldRequirements::guaranteed),
        ORANGE_BANNER(WorldRequirements::guaranteed),
        MAGENTA_BANNER(WorldRequirements::guaranteed),
        LIGHT_BLUE_BANNER(WorldRequirements::guaranteed),
        YELLOW_BANNER(WorldRequirements::guaranteed),
        LIME_BANNER(WorldRequirements::hasLime),
        PINK_BANNER(WorldRequirements::guaranteed),
        GRAY_BANNER(WorldRequirements::guaranteed),
        LIGHT_GRAY_BANNER(WorldRequirements::guaranteed),
        CYAN_BANNER(WorldRequirements::hasCyan),
        PURPLE_BANNER(WorldRequirements::guaranteed),
        BLUE_BANNER(WorldRequirements::guaranteed),
        BROWN_BANNER(WorldRequirements::hasBrown),
        GREEN_BANNER(WorldRequirements::hasGreen),
        RED_BANNER(WorldRequirements::guaranteed),
        BLACK_BANNER(WorldRequirements::guaranteed),
        END_CRYSTAL(WorldRequirements::guaranteed),
        CHORUS_FRUIT(WorldRequirements::hasEndAccess),
        POPPED_CHORUS_FRUIT(WorldRequirements::hasEndAccess),
        TORCHFLOWER_SEEDS(WorldRequirements::hasSniffer),
        PITCHER_POD(WorldRequirements::hasSniffer),
        BEETROOT(WorldRequirements::hasVillage),
        BEETROOT_SEEDS(WorldRequirements::hasVillage),
        BEETROOT_SOUP(WorldRequirements::hasVillage),
        DRAGON_BREATH(WorldRequirements::hasEndAccess),
        SPLASH_POTION(WorldRequirements::guaranteed),
        SPECTRAL_ARROW(WorldRequirements::guaranteed),
        TIPPED_ARROW(WorldRequirements::hasEndAccess),
        LINGERING_POTION(WorldRequirements::hasEndAccess),
        SHIELD(WorldRequirements::guaranteed),
        TOTEM_OF_UNDYING(WorldRequirements::hasEvoker),
        SHULKER_SHELL(WorldRequirements::hasEndAccess),
        IRON_NUGGET(WorldRequirements::guaranteed),
        KNOWLEDGE_BOOK(WorldRequirements::unobtainable),
        DEBUG_STICK(WorldRequirements::unobtainable),
        MUSIC_DISC_13(WorldRequirements::guaranteed),
        MUSIC_DISC_CAT(WorldRequirements::guaranteed),
        MUSIC_DISC_BLOCKS(WorldRequirements::guaranteed),
        MUSIC_DISC_CHIRP(WorldRequirements::guaranteed),
        MUSIC_DISC_CREATOR(WorldRequirements::inaccessible),
        MUSIC_DISC_CREATOR_MUSIC_BOX(WorldRequirements::inaccessible),
        MUSIC_DISC_FAR(WorldRequirements::guaranteed),
        MUSIC_DISC_MALL(WorldRequirements::guaranteed),
        MUSIC_DISC_MELLOHI(WorldRequirements::guaranteed),
        MUSIC_DISC_STAL(WorldRequirements::guaranteed),
        MUSIC_DISC_STRAD(WorldRequirements::guaranteed),
        MUSIC_DISC_WARD(WorldRequirements::guaranteed),
        MUSIC_DISC_11(WorldRequirements::guaranteed),
        MUSIC_DISC_WAIT(WorldRequirements::guaranteed),
        MUSIC_DISC_OTHERSIDE(WorldRequirements::inaccessible),
        MUSIC_DISC_RELIC(WorldRequirements::inaccessible),
        MUSIC_DISC_5(WorldRequirements::inaccessible),
        MUSIC_DISC_PIGSTEP(WorldRequirements::inaccessible),
        MUSIC_DISC_PRECIPICE(WorldRequirements::inaccessible),
        DISC_FRAGMENT_5(WorldRequirements::hasAncientCity),
        TRIDENT(WorldRequirements::guaranteed),
        NAUTILUS_SHELL(WorldRequirements::guaranteed),
        HEART_OF_THE_SEA(WorldRequirements::hasHeartOfTheSea),
        CROSSBOW(WorldRequirements::guaranteed),
        SUSPICIOUS_STEW(WorldRequirements::guaranteed),
        LOOM(WorldRequirements::guaranteed),
        FLOWER_BANNER_PATTERN(WorldRequirements::hasOxeyeDaisy),
        CREEPER_BANNER_PATTERN(WorldRequirements::guaranteed),
        SKULL_BANNER_PATTERN(WorldRequirements::hasFortress),
        MOJANG_BANNER_PATTERN(WorldRequirements::guaranteed),
        GLOBE_BANNER_PATTERN(WorldRequirements::hasVillage),
        PIGLIN_BANNER_PATTERN(WorldRequirements::hasBastion),
        FLOW_BANNER_PATTERN(WorldRequirements::guaranteed),
        GUSTER_BANNER_PATTERN(WorldRequirements::guaranteed),
        FIELD_MASONED_BANNER_PATTERN(WorldRequirements::guaranteed),
        BORDURE_INDENTED_BANNER_PATTERN(WorldRequirements::hasVines),
        GOAT_HORN(WorldRequirements::hasGoat),
        COMPOSTER(WorldRequirements::guaranteed),
        BARREL(WorldRequirements::guaranteed),
        SMOKER(WorldRequirements::guaranteed),
        BLAST_FURNACE(WorldRequirements::guaranteed),
        CARTOGRAPHY_TABLE(WorldRequirements::guaranteed),
        FLETCHING_TABLE(WorldRequirements::guaranteed),
        GRINDSTONE(WorldRequirements::guaranteed),
        SMITHING_TABLE(WorldRequirements::guaranteed),
        STONECUTTER(WorldRequirements::guaranteed),
        BELL(WorldRequirements::hasVillage),
        LANTERN(WorldRequirements::guaranteed),
        SOUL_LANTERN(WorldRequirements::guaranteed),
        SWEET_BERRIES(WorldRequirements::hasSweetBerries),
        GLOW_BERRIES(WorldRequirements::hasLushCave),
        CAMPFIRE(WorldRequirements::guaranteed),
        SOUL_CAMPFIRE(WorldRequirements::guaranteed),
        SHROOMLIGHT(WorldRequirements::hasShroomlight),
        HONEYCOMB(WorldRequirements::hasBees),
        BEE_NEST(WorldRequirements::hasBees),
        BEEHIVE(WorldRequirements::hasBees),
        HONEY_BOTTLE(WorldRequirements::hasBees),
        HONEYCOMB_BLOCK(WorldRequirements::hasBees),
        LODESTONE(WorldRequirements::guaranteed),
        CRYING_OBSIDIAN(WorldRequirements::hasCryingObsidian),
        BLACKSTONE(WorldRequirements::guaranteed),
        BLACKSTONE_SLAB(WorldRequirements::guaranteed),
        BLACKSTONE_STAIRS(WorldRequirements::guaranteed),
        GILDED_BLACKSTONE(WorldRequirements::hasBastion),
        POLISHED_BLACKSTONE(WorldRequirements::guaranteed),
        POLISHED_BLACKSTONE_SLAB(WorldRequirements::guaranteed),
        POLISHED_BLACKSTONE_STAIRS(WorldRequirements::guaranteed),
        CHISELED_POLISHED_BLACKSTONE(WorldRequirements::guaranteed),
        POLISHED_BLACKSTONE_BRICKS(WorldRequirements::guaranteed),
        POLISHED_BLACKSTONE_BRICK_SLAB(WorldRequirements::guaranteed),
        POLISHED_BLACKSTONE_BRICK_STAIRS(WorldRequirements::guaranteed),
        CRACKED_POLISHED_BLACKSTONE_BRICKS(WorldRequirements::guaranteed),
        RESPAWN_ANCHOR(WorldRequirements::hasCryingObsidian),
        CANDLE(WorldRequirements::hasBees),
        WHITE_CANDLE(WorldRequirements::hasBees),
        ORANGE_CANDLE(WorldRequirements::hasBees),
        MAGENTA_CANDLE(WorldRequirements::hasBees),
        LIGHT_BLUE_CANDLE(WorldRequirements::hasBees),
        YELLOW_CANDLE(WorldRequirements::hasBees),
        LIME_CANDLE(WorldRequirements::hasLimeCandle),
        PINK_CANDLE(WorldRequirements::hasBees),
        GRAY_CANDLE(WorldRequirements::hasBees),
        LIGHT_GRAY_CANDLE(WorldRequirements::hasBees),
        CYAN_CANDLE(WorldRequirements::hasCyanCandle),
        PURPLE_CANDLE(WorldRequirements::hasBees),
        BLUE_CANDLE(WorldRequirements::hasBees),
        BROWN_CANDLE(WorldRequirements::hasBrownCandle),
        GREEN_CANDLE(WorldRequirements::hasGreenCandle),
        RED_CANDLE(WorldRequirements::hasBees),
        BLACK_CANDLE(WorldRequirements::hasBees),
        SMALL_AMETHYST_BUD(WorldRequirements::guaranteed),
        MEDIUM_AMETHYST_BUD(WorldRequirements::guaranteed),
        LARGE_AMETHYST_BUD(WorldRequirements::guaranteed),
        AMETHYST_CLUSTER(WorldRequirements::guaranteed),
        POINTED_DRIPSTONE(WorldRequirements::hasDripstone),
        OCHRE_FROGLIGHT(WorldRequirements::hasOchreFroglight),
        VERDANT_FROGLIGHT(WorldRequirements::hasVerdantFroglight),
        PEARLESCENT_FROGLIGHT(WorldRequirements::hasPearlescentFroglight),
        FROGSPAWN(WorldRequirements::hasFrog),
        ECHO_SHARD(WorldRequirements::hasAncientCity),
        BRUSH(WorldRequirements::guaranteed),
        NETHERITE_UPGRADE_SMITHING_TEMPLATE(WorldRequirements::hasBastion),
        SENTRY_ARMOR_TRIM_SMITHING_TEMPLATE(WorldRequirements::inaccessible),
        DUNE_ARMOR_TRIM_SMITHING_TEMPLATE(WorldRequirements::inaccessible),
        COAST_ARMOR_TRIM_SMITHING_TEMPLATE(WorldRequirements::inaccessible),
        WILD_ARMOR_TRIM_SMITHING_TEMPLATE(WorldRequirements::inaccessible),
        WARD_ARMOR_TRIM_SMITHING_TEMPLATE(WorldRequirements::inaccessible),
        EYE_ARMOR_TRIM_SMITHING_TEMPLATE(WorldRequirements::inaccessible),
        VEX_ARMOR_TRIM_SMITHING_TEMPLATE(WorldRequirements::inaccessible),
        TIDE_ARMOR_TRIM_SMITHING_TEMPLATE(WorldRequirements::inaccessible),
        SNOUT_ARMOR_TRIM_SMITHING_TEMPLATE(WorldRequirements::inaccessible),
        RIB_ARMOR_TRIM_SMITHING_TEMPLATE(WorldRequirements::inaccessible),
        SPIRE_ARMOR_TRIM_SMITHING_TEMPLATE(WorldRequirements::inaccessible),
        WAYFINDER_ARMOR_TRIM_SMITHING_TEMPLATE(WorldRequirements::inaccessible),
        SHAPER_ARMOR_TRIM_SMITHING_TEMPLATE(WorldRequirements::inaccessible),
        SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE(WorldRequirements::inaccessible),
        RAISER_ARMOR_TRIM_SMITHING_TEMPLATE(WorldRequirements::inaccessible),
        HOST_ARMOR_TRIM_SMITHING_TEMPLATE(WorldRequirements::inaccessible),
        FLOW_ARMOR_TRIM_SMITHING_TEMPLATE(WorldRequirements::inaccessible),
        BOLT_ARMOR_TRIM_SMITHING_TEMPLATE(WorldRequirements::inaccessible),
        ANGLER_POTTERY_SHERD(WorldRequirements::inaccessible),
        ARCHER_POTTERY_SHERD(WorldRequirements::inaccessible),
        ARMS_UP_POTTERY_SHERD(WorldRequirements::inaccessible),
        BLADE_POTTERY_SHERD(WorldRequirements::inaccessible),
        BREWER_POTTERY_SHERD(WorldRequirements::inaccessible),
        BURN_POTTERY_SHERD(WorldRequirements::inaccessible),
        DANGER_POTTERY_SHERD(WorldRequirements::inaccessible),
        EXPLORER_POTTERY_SHERD(WorldRequirements::inaccessible),
        FLOW_POTTERY_SHERD(WorldRequirements::inaccessible),
        FRIEND_POTTERY_SHERD(WorldRequirements::inaccessible),
        GUSTER_POTTERY_SHERD(WorldRequirements::inaccessible),
        HEART_POTTERY_SHERD(WorldRequirements::inaccessible),
        HEARTBREAK_POTTERY_SHERD(WorldRequirements::inaccessible),
        HOWL_POTTERY_SHERD(WorldRequirements::inaccessible),
        MINER_POTTERY_SHERD(WorldRequirements::inaccessible),
        MOURNER_POTTERY_SHERD(WorldRequirements::inaccessible),
        PLENTY_POTTERY_SHERD(WorldRequirements::inaccessible),
        PRIZE_POTTERY_SHERD(WorldRequirements::inaccessible),
        SCRAPE_POTTERY_SHERD(WorldRequirements::inaccessible),
        SHEAF_POTTERY_SHERD(WorldRequirements::inaccessible),
        SHELTER_POTTERY_SHERD(WorldRequirements::inaccessible),
        SKULL_POTTERY_SHERD(WorldRequirements::inaccessible),
        SNORT_POTTERY_SHERD(WorldRequirements::inaccessible),
        COPPER_GRATE(WorldRequirements::guaranteed),
        EXPOSED_COPPER_GRATE(WorldRequirements::guaranteed),
        WEATHERED_COPPER_GRATE(WorldRequirements::guaranteed),
        OXIDIZED_COPPER_GRATE(WorldRequirements::guaranteed),
        WAXED_COPPER_GRATE(WorldRequirements::hasBees),
        WAXED_EXPOSED_COPPER_GRATE(WorldRequirements::hasBees),
        WAXED_WEATHERED_COPPER_GRATE(WorldRequirements::hasBees),
        WAXED_OXIDIZED_COPPER_GRATE(WorldRequirements::hasBees),
        COPPER_BULB(WorldRequirements::hasCopperBulb),
        EXPOSED_COPPER_BULB(WorldRequirements::hasCopperBulb),
        WEATHERED_COPPER_BULB(WorldRequirements::hasCopperBulb),
        OXIDIZED_COPPER_BULB(WorldRequirements::hasCopperBulb),
        WAXED_COPPER_BULB(WorldRequirements::hasWaxCopperBulb),
        WAXED_EXPOSED_COPPER_BULB(WorldRequirements::hasWaxCopperBulb),
        WAXED_WEATHERED_COPPER_BULB(WorldRequirements::hasWaxCopperBulb),
        WAXED_OXIDIZED_COPPER_BULB(WorldRequirements::hasWaxCopperBulb),
        TRIAL_SPAWNER(WorldRequirements::hasTrialChamber),
        TRIAL_KEY(WorldRequirements::hasTrialChamber),
        OMINOUS_TRIAL_KEY(WorldRequirements::hasTrialChamber),
        VAULT(WorldRequirements::hasTrialChamber),
        OMINOUS_BOTTLE(WorldRequirements::hasPillagerOutpost),
        WATER(WorldRequirements::guaranteed),
        LAVA(WorldRequirements::guaranteed),
        TALL_SEAGRASS(WorldRequirements::hasSeagrass),
        PISTON_HEAD(WorldRequirements::guaranteed),
        MOVING_PISTON(WorldRequirements::guaranteed),
        WALL_TORCH(WorldRequirements::guaranteed),
        FIRE(WorldRequirements::guaranteed),
        SOUL_FIRE(WorldRequirements::guaranteed),
        REDSTONE_WIRE(WorldRequirements::guaranteed),
        OAK_WALL_SIGN(WorldRequirements::hasOak),
        SPRUCE_WALL_SIGN(WorldRequirements::hasSpruce),
        BIRCH_WALL_SIGN(WorldRequirements::hasBirch),
        ACACIA_WALL_SIGN(WorldRequirements::hasAcacia),
        CHERRY_WALL_SIGN(WorldRequirements::hasCherry),
        JUNGLE_WALL_SIGN(WorldRequirements::hasJungle),
        DARK_OAK_WALL_SIGN(WorldRequirements::hasDarkOak),
        PALE_OAK_WALL_SIGN(WorldRequirements::hasPaleOak),
        MANGROVE_WALL_SIGN(WorldRequirements::hasMangrove),
        BAMBOO_WALL_SIGN(WorldRequirements::hasBamboo),
        OAK_WALL_HANGING_SIGN(WorldRequirements::hasOak),
        SPRUCE_WALL_HANGING_SIGN(WorldRequirements::hasSpruce),
        BIRCH_WALL_HANGING_SIGN(WorldRequirements::hasBirch),
        ACACIA_WALL_HANGING_SIGN(WorldRequirements::hasAcacia),
        CHERRY_WALL_HANGING_SIGN(WorldRequirements::hasCherry),
        JUNGLE_WALL_HANGING_SIGN(WorldRequirements::hasJungle),
        DARK_OAK_WALL_HANGING_SIGN(WorldRequirements::hasDarkOak),
        PALE_OAK_WALL_HANGING_SIGN(WorldRequirements::hasPaleOak),
        MANGROVE_WALL_HANGING_SIGN(WorldRequirements::hasMangrove),
        CRIMSON_WALL_HANGING_SIGN(WorldRequirements::hasCrimson),
        WARPED_WALL_HANGING_SIGN(WorldRequirements::hasWarped),
        BAMBOO_WALL_HANGING_SIGN(WorldRequirements::hasBamboo),
        REDSTONE_WALL_TORCH(WorldRequirements::guaranteed),
        SOUL_WALL_TORCH(WorldRequirements::guaranteed),
        NETHER_PORTAL(WorldRequirements::guaranteed),
        ATTACHED_PUMPKIN_STEM(WorldRequirements::guaranteed),
        ATTACHED_MELON_STEM(WorldRequirements::guaranteed),
        PUMPKIN_STEM(WorldRequirements::guaranteed),
        MELON_STEM(WorldRequirements::guaranteed),
        WATER_CAULDRON(WorldRequirements::guaranteed),
        LAVA_CAULDRON(WorldRequirements::guaranteed),
        POWDER_SNOW_CAULDRON(WorldRequirements::hasPowderedSnow),
        END_PORTAL(WorldRequirements::hasEndAccess),
        COCOA(WorldRequirements::hasBrown),
        TRIPWIRE(WorldRequirements::guaranteed),
        POTTED_TORCHFLOWER(WorldRequirements::hasSniffer),
        POTTED_OAK_SAPLING(WorldRequirements::hasOak),
        POTTED_SPRUCE_SAPLING(WorldRequirements::hasSpruce),
        POTTED_BIRCH_SAPLING(WorldRequirements::hasBirch),
        POTTED_JUNGLE_SAPLING(WorldRequirements::hasJungle),
        POTTED_ACACIA_SAPLING(WorldRequirements::hasAcacia),
        POTTED_CHERRY_SAPLING(WorldRequirements::hasCherry),
        POTTED_DARK_OAK_SAPLING(WorldRequirements::hasDarkOak),
        POTTED_PALE_OAK_SAPLING(WorldRequirements::hasPaleOak),
        POTTED_MANGROVE_PROPAGULE(WorldRequirements::hasMangrove),
        POTTED_FERN(WorldRequirements::hasFern),
        POTTED_DANDELION(WorldRequirements::guaranteed),
        POTTED_POPPY(WorldRequirements::guaranteed),
        POTTED_BLUE_ORCHID(WorldRequirements::hasBlueOrchid),
        POTTED_ALLIUM(WorldRequirements::hasAllium),
        POTTED_AZURE_BLUET(WorldRequirements::hasAzureBluet),
        POTTED_RED_TULIP(WorldRequirements::hasTulip),
        POTTED_ORANGE_TULIP(WorldRequirements::hasTulip),
        POTTED_WHITE_TULIP(WorldRequirements::hasTulip),
        POTTED_PINK_TULIP(WorldRequirements::hasTulip),
        POTTED_OXEYE_DAISY(WorldRequirements::hasOxeyeDaisy),
        POTTED_CORNFLOWER(WorldRequirements::hasCornflower),
        POTTED_LILY_OF_THE_VALLEY(WorldRequirements::hasLilyOfTheValley),
        POTTED_WITHER_ROSE(WorldRequirements::hasFortress),
        POTTED_RED_MUSHROOM(WorldRequirements::guaranteed),
        POTTED_BROWN_MUSHROOM(WorldRequirements::guaranteed),
        POTTED_DEAD_BUSH(WorldRequirements::hasDeadBush),
        POTTED_CACTUS(WorldRequirements::guaranteed),
        CARROTS(WorldRequirements::guaranteed),
        POTATOES(WorldRequirements::guaranteed),
        SKELETON_WALL_SKULL(WorldRequirements::guaranteed),
        WITHER_SKELETON_WALL_SKULL(WorldRequirements::hasFortress),
        ZOMBIE_WALL_HEAD(WorldRequirements::guaranteed),
        PLAYER_WALL_HEAD(WorldRequirements::unobtainable),
        CREEPER_WALL_HEAD(WorldRequirements::guaranteed),
        DRAGON_WALL_HEAD(WorldRequirements::hasEndAccess),
        PIGLIN_WALL_HEAD(WorldRequirements::guaranteed),
        WHITE_WALL_BANNER(WorldRequirements::guaranteed),
        ORANGE_WALL_BANNER(WorldRequirements::guaranteed),
        MAGENTA_WALL_BANNER(WorldRequirements::guaranteed),
        LIGHT_BLUE_WALL_BANNER(WorldRequirements::guaranteed),
        YELLOW_WALL_BANNER(WorldRequirements::guaranteed),
        LIME_WALL_BANNER(WorldRequirements::hasLime),
        PINK_WALL_BANNER(WorldRequirements::guaranteed),
        GRAY_WALL_BANNER(WorldRequirements::guaranteed),
        LIGHT_GRAY_WALL_BANNER(WorldRequirements::guaranteed),
        CYAN_WALL_BANNER(WorldRequirements::hasCyan),
        PURPLE_WALL_BANNER(WorldRequirements::guaranteed),
        BLUE_WALL_BANNER(WorldRequirements::guaranteed),
        BROWN_WALL_BANNER(WorldRequirements::guaranteed),
        GREEN_WALL_BANNER(WorldRequirements::hasGreen),
        RED_WALL_BANNER(WorldRequirements::guaranteed),
        BLACK_WALL_BANNER(WorldRequirements::guaranteed),
        TORCHFLOWER_CROP(WorldRequirements::hasSniffer),
        PITCHER_CROP(WorldRequirements::hasSniffer),
        BEETROOTS(WorldRequirements::hasVillage),
        END_GATEWAY(WorldRequirements::hasEndAccess),
        FROSTED_ICE(WorldRequirements::guaranteed),
        KELP_PLANT(WorldRequirements::hasKelp),
        DEAD_TUBE_CORAL_WALL_FAN(WorldRequirements::hasCoral),
        DEAD_BRAIN_CORAL_WALL_FAN(WorldRequirements::hasCoral),
        DEAD_BUBBLE_CORAL_WALL_FAN(WorldRequirements::hasCoral),
        DEAD_FIRE_CORAL_WALL_FAN(WorldRequirements::hasCoral),
        DEAD_HORN_CORAL_WALL_FAN(WorldRequirements::hasCoral),
        TUBE_CORAL_WALL_FAN(WorldRequirements::hasCoral),
        BRAIN_CORAL_WALL_FAN(WorldRequirements::hasCoral),
        BUBBLE_CORAL_WALL_FAN(WorldRequirements::hasCoral),
        FIRE_CORAL_WALL_FAN(WorldRequirements::hasCoral),
        HORN_CORAL_WALL_FAN(WorldRequirements::hasCoral),
        BAMBOO_SAPLING(WorldRequirements::hasBamboo),
        POTTED_BAMBOO(WorldRequirements::hasBamboo),
        VOID_AIR(WorldRequirements::guaranteed),
        CAVE_AIR(WorldRequirements::guaranteed),
        BUBBLE_COLUMN(WorldRequirements::guaranteed),
        SWEET_BERRY_BUSH(WorldRequirements::hasSweetBerries),
        WEEPING_VINES_PLANT(WorldRequirements::hasCrimson),
        TWISTING_VINES_PLANT(WorldRequirements::hasWarped),
        CRIMSON_WALL_SIGN(WorldRequirements::hasCrimson),
        WARPED_WALL_SIGN(WorldRequirements::hasWarped),
        POTTED_CRIMSON_FUNGUS(WorldRequirements::hasCrimson),
        POTTED_WARPED_FUNGUS(WorldRequirements::hasWarped),
        POTTED_CRIMSON_ROOTS(WorldRequirements::hasCrimson),
        POTTED_WARPED_ROOTS(WorldRequirements::hasWarped),
        CANDLE_CAKE(WorldRequirements::hasBees),
        WHITE_CANDLE_CAKE(WorldRequirements::hasBees),
        ORANGE_CANDLE_CAKE(WorldRequirements::hasBees),
        MAGENTA_CANDLE_CAKE(WorldRequirements::hasBees),
        LIGHT_BLUE_CANDLE_CAKE(WorldRequirements::hasBees),
        YELLOW_CANDLE_CAKE(WorldRequirements::hasBees),
        LIME_CANDLE_CAKE(WorldRequirements::hasLimeCandle),
        PINK_CANDLE_CAKE(WorldRequirements::hasBees),
        GRAY_CANDLE_CAKE(WorldRequirements::hasBees),
        LIGHT_GRAY_CANDLE_CAKE(WorldRequirements::hasBees),
        CYAN_CANDLE_CAKE(WorldRequirements::hasCyanCandle),
        PURPLE_CANDLE_CAKE(WorldRequirements::hasBees),
        BLUE_CANDLE_CAKE(WorldRequirements::hasBees),
        BROWN_CANDLE_CAKE(WorldRequirements::hasBrownCandle),
        GREEN_CANDLE_CAKE(WorldRequirements::hasGreenCandle),
        RED_CANDLE_CAKE(WorldRequirements::hasBees),
        BLACK_CANDLE_CAKE(WorldRequirements::hasBees),
        POWDER_SNOW(WorldRequirements::hasPowderedSnow),
        CAVE_VINES(WorldRequirements::hasLushCave),
        CAVE_VINES_PLANT(WorldRequirements::hasLushCave),
        BIG_DRIPLEAF_STEM(WorldRequirements::hasLushCave),
        POTTED_AZALEA_BUSH(WorldRequirements::hasLushCave),
        POTTED_FLOWERING_AZALEA_BUSH(WorldRequirements::hasLushCave),
        POTTED_OPEN_EYEBLOSSOM(WorldRequirements::hasPaleGarden),
        POTTED_CLOSED_EYEBLOSSOM(WorldRequirements::hasPaleGarden),

// ********************** ENTITYTYPE **********************

        ITEM(WorldRequirements::guaranteed),
        EXPERIENCE_ORB(WorldRequirements::guaranteed),
        AREA_EFFECT_CLOUD(WorldRequirements::guaranteed),
        ELDER_GUARDIAN(WorldRequirements::hasOceanMonument),
        WITHER_SKELETON(WorldRequirements::hasFortress),
        STRAY(WorldRequirements::hasStray),
        LEASH_KNOT(WorldRequirements::guaranteed),
        FIREBALL(WorldRequirements::guaranteed),
        SMALL_FIREBALL(WorldRequirements::guaranteed),
        EYE_OF_ENDER(WorldRequirements::hasFortress),
        WITHER_SKULL(WorldRequirements::hasFortress),
        FALLING_BLOCK(WorldRequirements::guaranteed),
        HUSK(WorldRequirements::hasDesert),
        SHULKER_BULLET(WorldRequirements::hasEndAccess),
        DRAGON_FIREBALL(WorldRequirements::hasEndAccess),
        ZOMBIE_VILLAGER(WorldRequirements::guaranteed),
        SKELETON_HORSE(WorldRequirements::guaranteed),
        ZOMBIE_HORSE(WorldRequirements::unobtainable),
        DONKEY(WorldRequirements::guaranteed),
        MULE(WorldRequirements::guaranteed),
        EVOKER_FANGS(WorldRequirements::hasEvoker),
        EVOKER(WorldRequirements::hasEvoker),
        VEX(WorldRequirements::hasEvoker),
        VINDICATOR(WorldRequirements::hasEvoker),
        ILLUSIONER(WorldRequirements::guaranteed),
        SPAWNER_MINECART(WorldRequirements::unobtainable),
        CREEPER(WorldRequirements::guaranteed),
        SKELETON(WorldRequirements::guaranteed),
        SPIDER(WorldRequirements::guaranteed),
        GIANT(WorldRequirements::unobtainable),
        ZOMBIE(WorldRequirements::guaranteed),
        SLIME(WorldRequirements::guaranteed),
        GHAST(WorldRequirements::hasGhast),
        ZOMBIFIED_PIGLIN(WorldRequirements::guaranteed),
        ENDERMAN(WorldRequirements::guaranteed),
        CAVE_SPIDER(WorldRequirements::hasMineshaft),
        SILVERFISH(WorldRequirements::hasEndAccess),
        BLAZE(WorldRequirements::hasFortress),
        MAGMA_CUBE(WorldRequirements::hasMagmaCube),
        ENDER_DRAGON(WorldRequirements::hasEndAccess),
        WITHER(WorldRequirements::hasFortress),
        BAT(WorldRequirements::guaranteed),
        WITCH(WorldRequirements::guaranteed),
        ENDERMITE(WorldRequirements::hasEndAccess),
        GUARDIAN(WorldRequirements::hasOceanMonument),
        SHULKER(WorldRequirements::hasEndAccess),
        PIG(WorldRequirements::guaranteed),
        SHEEP(WorldRequirements::guaranteed),
        COW(WorldRequirements::guaranteed),
        SQUID(WorldRequirements::guaranteed),
        WOLF(WorldRequirements::guaranteed),
        MOOSHROOM(WorldRequirements::hasMushroomBiome),
        SNOW_GOLEM(WorldRequirements::hasSnow),
        OCELOT(WorldRequirements::hasJungle),
        IRON_GOLEM(WorldRequirements::guaranteed),
        HORSE(WorldRequirements::guaranteed),
        POLAR_BEAR(WorldRequirements::hasPolarBear),
        LLAMA(WorldRequirements::hasNaturalLlama),
        LLAMA_SPIT(WorldRequirements::hasNaturalLlama),
        PARROT(WorldRequirements::hasJungle),
        VILLAGER(WorldRequirements::hasVillage),
        TURTLE(WorldRequirements::hasTurtle),
        PHANTOM(WorldRequirements::guaranteed),
        DROWNED(WorldRequirements::guaranteed),
        DOLPHIN(WorldRequirements::hasDolphin),
        CAT(WorldRequirements::hasVillage),
        PANDA(WorldRequirements::hasBamboo),
        PILLAGER(WorldRequirements::hasPillagerOutpost),
        RAVAGER(WorldRequirements::hasRaid),
        TRADER_LLAMA(WorldRequirements::guaranteed),
        WANDERING_TRADER(WorldRequirements::guaranteed),
        FOX(WorldRequirements::hasFox),
        BEE(WorldRequirements::hasBees),
        HOGLIN(WorldRequirements::hasCrimson),
        PIGLIN(WorldRequirements::hasPiglin),
        STRIDER(WorldRequirements::guaranteed),
        ZOGLIN(WorldRequirements::hasCrimson),
        PIGLIN_BRUTE(WorldRequirements::hasBastion),
        AXOLOTL(WorldRequirements::hasLushCave),
        GLOW_SQUID(WorldRequirements::guaranteed),
        GOAT(WorldRequirements::hasGoat),
        MARKER(WorldRequirements::unobtainable),
        ALLAY(WorldRequirements::inaccessible),
        FROG(WorldRequirements::hasFrog),
        TADPOLE(WorldRequirements::hasFrog),
        WARDEN(WorldRequirements::hasAncientCity),
        CAMEL(WorldRequirements::hasDesert),
        BLOCK_DISPLAY(WorldRequirements::unobtainable),
        INTERACTION(WorldRequirements::unobtainable),
        ITEM_DISPLAY(WorldRequirements::unobtainable),
        SNIFFER(WorldRequirements::hasSniffer),
        TEXT_DISPLAY(WorldRequirements::unobtainable),
        BREEZE(WorldRequirements::hasTrialChamber),
        BREEZE_WIND_CHARGE(WorldRequirements::hasTrialChamber),
        ARMADILLO(WorldRequirements::hasArmadillo),
        BOGGED(WorldRequirements::hasSwamp),
        OMINOUS_ITEM_SPAWNER(WorldRequirements::unobtainable),
        CREAKING(WorldRequirements::hasPaleGarden),
        FISHING_BOBBER(WorldRequirements::guaranteed),
        LIGHTNING_BOLT(WorldRequirements::guaranteed),
        PLAYER(WorldRequirements::guaranteed),
        UNKNOWN(WorldRequirements::unobtainable);


        private final GameWorld world;
        private final Predicate<GameWorld> requirement;

        Element(Predicate<GameWorld> requirement) {
            this.requirement = requirement;
            this.world = LockoutGames.getPluginInstance().getGameWorld();
        }

        /**
         *
         * @return True if this element can be accessed in this game world, false otherwise
         */
        public boolean verify() {
            return requirement.test(world);
        }
    }

    //Returns true if the world can generate ferns
    private static boolean hasFern(GameWorld world) {
        return world.hasBiome(Biome.JUNGLE) ||
                world.hasBiome(Biome.TAIGA) ||
                world.hasBiome(Biome.SNOWY_TAIGA) ||
                world.hasBiome(Biome.OLD_GROWTH_SPRUCE_TAIGA) ||
                world.hasBiome(Biome.OLD_GROWTH_PINE_TAIGA);
    }

    private static boolean hasCopperBulb(GameWorld world) {
        return world.hasStructure(Structure.TRIAL_CHAMBERS) || world.hasStructure(Structure.FORTRESS);
    }

    private static boolean hasWaxCopperBulb(GameWorld world) {
        return hasCopperBulb(world) && hasFortress(world);
    }

    //Returns true if the world can generate crying obsidian consistently
    private static boolean hasCryingObsidian(GameWorld world) {
        return world.hasStructure(Structure.RUINED_PORTAL) ||
                world.hasStructure(Structure.RUINED_PORTAL_DESERT) ||
                world.hasStructure(Structure.RUINED_PORTAL_JUNGLE) ||
                world.hasStructure(Structure.RUINED_PORTAL_MOUNTAIN) ||
                world.hasStructure(Structure.RUINED_PORTAL_NETHER) ||
                world.hasStructure(Structure.RUINED_PORTAL_OCEAN) ||
                world.hasStructure(Structure.RUINED_PORTAL_SWAMP) ||
                hasPiglin(world);
    }

    private static boolean hasFox(GameWorld world) {
        return world.hasBiome(Biome.TAIGA)
                || world.hasBiome(Biome.OLD_GROWTH_PINE_TAIGA)
                || world.hasBiome(Biome.OLD_GROWTH_SPRUCE_TAIGA);
    }

    //Returns true if a pillager outpost has generated in the world
    private static boolean hasPillagerOutpost(GameWorld world) {
        return world.hasStructure(Structure.PILLAGER_OUTPOST);
    }

    //Returns true if Evokers can be spawned in the world
    private static boolean hasEvoker(GameWorld world) {
        return (world.hasStructure(Structure.PILLAGER_OUTPOST) && hasVillage(world)) ||
                world.hasStructure(Structure.MANSION);
    }

    //Returns true if shroomlights generate in the world
    private static boolean hasShroomlight(GameWorld world) {
        return world.hasBiome(Biome.CRIMSON_FOREST) ||
                world.hasBiome(Biome.WARPED_FOREST);
    }

    //Returns true if sweet berries generate in the world
    private static boolean hasSweetBerries(GameWorld world) {
        return world.hasBiome(Biome.TAIGA) ||
                world.hasBiome(Biome.SNOWY_TAIGA) ||
                world.hasBiome(Biome.OLD_GROWTH_PINE_TAIGA) ||
                world.hasBiome(Biome.OLD_GROWTH_SPRUCE_TAIGA);
    }

    //Returns true if goats spawn in the world
    private static boolean hasGoat(GameWorld world) {
        return world.hasBiome(Biome.FROZEN_PEAKS) ||
                world.hasBiome(Biome.JAGGED_PEAKS) ||
                world.hasBiome(Biome.SNOWY_SLOPES);
    }

    //Returns true of ghasts spawn in the world
    private static boolean hasGhast(GameWorld world) {
        return world.hasBiome(Biome.NETHER_WASTES) ||
                world.hasBiome(Biome.SOUL_SAND_VALLEY) ||
                world.hasBiome(Biome.BASALT_DELTAS);
    }

    //Returns true if end crystals can be crafted from materials in this world
    private static boolean hasEndCrystal(GameWorld world) {
        return hasGhast(world) && hasFortress(world);
    }

    //Returns true if magma cubes spawn in this world
    private static boolean hasMagmaCube(GameWorld world) {
        return world.hasBiome(Biome.NETHER_WASTES) ||
                world.hasBiome(Biome.BASALT_DELTAS) ||
                world.hasStructure(Structure.FORTRESS);
    }

    //Returns true if rabbits spawn in this world
    private static boolean hasRabbit(GameWorld world) {
        return world.hasBiome(Biome.DESERT) ||
                world.hasBiome(Biome.SNOWY_PLAINS) ||
                world.hasBiome(Biome.SNOWY_TAIGA) ||
                world.hasBiome(Biome.GROVE) ||
                world.hasBiome(Biome.SNOWY_SLOPES) ||
                world.hasBiome(Biome.SNOWY_BEACH) ||
                world.hasBiome(Biome.FLOWER_FOREST) ||
                world.hasBiome(Biome.TAIGA) ||
                world.hasBiome(Biome.MEADOW) ||
                world.hasBiome(Biome.OLD_GROWTH_PINE_TAIGA) ||
                world.hasBiome(Biome.OLD_GROWTH_SPRUCE_TAIGA) ||
                world.hasBiome(Biome.CHERRY_GROVE);
    }

    //Returns true if frogs spawn in this world
    private static boolean hasFrog(GameWorld world) {
        return world.hasBiome(Biome.SWAMP) ||
                world.hasBiome(Biome.MANGROVE_SWAMP);
    }

    //Returns true if temperate frogs can be raised in this world
    private static boolean hasTemperateFrog(GameWorld world) {
        return hasFrog(world) && (world.hasBiome(Biome.SWAMP) ||
                world.hasBiome(Biome.BEACH) ||
                world.hasBiome(Biome.BIRCH_FOREST) ||
                world.hasBiome(Biome.CHERRY_GROVE) ||
                world.hasBiome(Biome.DARK_FOREST) ||
                world.hasBiome(Biome.DRIPSTONE_CAVES) ||
                world.hasBiome(Biome.FLOWER_FOREST) ||
                world.hasBiome(Biome.FOREST) ||
                world.hasBiome(Biome.LUSH_CAVES) ||
                world.hasBiome(Biome.MEADOW) ||
                world.hasBiome(Biome.MUSHROOM_FIELDS) ||
                world.hasBiome(Biome.OCEAN) ||
                world.hasBiome(Biome.OLD_GROWTH_BIRCH_FOREST) ||
                world.hasBiome(Biome.OLD_GROWTH_PINE_TAIGA) ||
                world.hasBiome(Biome.OLD_GROWTH_SPRUCE_TAIGA) ||
                world.hasBiome(Biome.PLAINS) ||
                world.hasBiome(Biome.RIVER) ||
                world.hasBiome(Biome.STONY_SHORE) ||
                world.hasBiome(Biome.SUNFLOWER_PLAINS) ||
                world.hasBiome(Biome.TAIGA) ||
                world.hasBiome(Biome.WINDSWEPT_FOREST) ||
                world.hasBiome(Biome.WINDSWEPT_GRAVELLY_HILLS) ||
                world.hasBiome(Biome.WINDSWEPT_HILLS) ||
                world.hasBiome(Biome.COLD_OCEAN) ||
                world.hasBiome(Biome.DEEP_COLD_OCEAN) ||
                world.hasBiome(Biome.DEEP_LUKEWARM_OCEAN) ||
                world.hasBiome(Biome.LUKEWARM_OCEAN) ||
                world.hasBiome(Biome.STONY_PEAKS));
    }

    //Returns true if warm frogs can be raised in this world
    private static boolean hasWarmFrog(GameWorld world) {
        return hasFrog(world) && (world.hasBiome(Biome.MANGROVE_SWAMP) ||
                world.hasBiome(Biome.BADLANDS) ||
                world.hasBiome(Biome.BAMBOO_JUNGLE) ||
                world.hasBiome(Biome.DESERT) ||
                world.hasBiome(Biome.ERODED_BADLANDS) ||
                world.hasBiome(Biome.JUNGLE) ||
                world.hasBiome(Biome.SAVANNA_PLATEAU) ||
                world.hasBiome(Biome.SAVANNA) ||
                world.hasBiome(Biome.SPARSE_JUNGLE) ||
                world.hasBiome(Biome.WARM_OCEAN) ||
                world.hasBiome(Biome.WINDSWEPT_SAVANNA) ||
                world.hasBiome(Biome.WOODED_BADLANDS));
    }

    //Returns true if cold frogs can be raised in this world
    private static boolean hasColdFrog(GameWorld world) {
        return hasFrog(world) && (world.hasBiome(Biome.DEEP_FROZEN_OCEAN) ||
                world.hasBiome(Biome.FROZEN_OCEAN) ||
                world.hasBiome(Biome.FROZEN_PEAKS) ||
                world.hasBiome(Biome.FROZEN_RIVER) ||
                world.hasBiome(Biome.GROVE) ||
                world.hasBiome(Biome.ICE_SPIKES) ||
                world.hasBiome(Biome.JAGGED_PEAKS) ||
                world.hasBiome(Biome.SNOWY_BEACH) ||
                world.hasBiome(Biome.SNOWY_PLAINS) ||
                world.hasBiome(Biome.SNOWY_SLOPES) ||
                world.hasBiome(Biome.SNOWY_TAIGA) ||
                world.hasBiome(Biome.DEEP_DARK) ||
                hasEndAccess(world));
    }

    //Returns true if pearlescent froglights are accessible
    private static boolean hasPearlescentFroglight(GameWorld world) {
        return hasMagmaCube(world) && hasWarmFrog(world);
    }

    //Returns true if ochre froglights are accessible
    private static boolean hasOchreFroglight(GameWorld world) {
        return hasMagmaCube(world) && hasTemperateFrog(world);
    }

    //Returns true if verdant froglights are accessible
    private static boolean hasVerdantFroglight(GameWorld world) {
        return hasMagmaCube(world) && hasColdFrog(world);
    }

    //Returns true if piglins spawn in this world
    private static boolean hasPiglin(GameWorld world) {
        return world.hasBiome(Biome.NETHER_WASTES) ||
                world.hasBiome(Biome.CRIMSON_FOREST) ||
                world.hasStructure(Structure.BASTION_REMNANT);
    }

    //Returns true if a bastion has generated in this world
    private static boolean hasBastion(GameWorld world) {
        return world.hasStructure(Structure.BASTION_REMNANT);
    }

    //Returns true if cod spawn in this world
    private static boolean hasCod(GameWorld world) {
        return world.hasBiome(Biome.OCEAN) ||
                world.hasBiome(Biome.COLD_OCEAN) ||
                world.hasBiome(Biome.DEEP_COLD_OCEAN) ||
                world.hasBiome(Biome.DEEP_OCEAN) ||
                world.hasBiome(Biome.DEEP_LUKEWARM_OCEAN) ||
                world.hasBiome(Biome.LUKEWARM_OCEAN);
    }

    //Returns true if salmon spawn in this world
    private static boolean hasSalmon(GameWorld world) {
        return world.hasBiome(Biome.COLD_OCEAN) ||
                world.hasBiome(Biome.DEEP_COLD_OCEAN) ||
                world.hasBiome(Biome.FROZEN_OCEAN) ||
                world.hasBiome(Biome.DEEP_FROZEN_OCEAN) ||
                world.hasBiome(Biome.RIVER) ||
                world.hasBiome(Biome.FROZEN_RIVER);
    }

    //Returns true if tropical fish spawn in this world
    private static boolean hasTropicalFish(GameWorld world) {
        return world.hasBiome(Biome.WARM_OCEAN) ||
                world.hasBiome(Biome.LUKEWARM_OCEAN) ||
                world.hasBiome(Biome.DEEP_LUKEWARM_OCEAN);
    }

    //Returns true if powdered snow generates in this world
    private static boolean hasPowderedSnow(GameWorld world) {
        return world.hasBiome(Biome.GROVE) ||
                world.hasBiome(Biome.SNOWY_SLOPES);
    }

    //Returns true if there is a village in this world
    private static boolean hasVillage(GameWorld world) {
        return world.hasStructure(Structure.VILLAGE_PLAINS) ||
                world.hasStructure(Structure.VILLAGE_DESERT) ||
                world.hasStructure(Structure.VILLAGE_TAIGA) ||
                world.hasStructure(Structure.VILLAGE_SNOWY) ||
                world.hasStructure(Structure.VILLAGE_SAVANNA);
    }

    private static boolean hasRaid(GameWorld world) {
        return hasVillage(world) && hasPillagerOutpost(world);
    }

    //Returns true if apples are naturally accessible in this world
    private static boolean hasApple(GameWorld world) {
        return hasOak(world) || hasDarkOak(world);
    }

    //Returns true if cobwebs are generating in this world
    private static boolean hasCobweb(GameWorld world) {
        return world.hasStructure(Structure.MINESHAFT) ||
                world.hasStructure(Structure.STRONGHOLD) ||
                world.hasStructure(Structure.MANSION);
    }

    //Returns true if there is a dripstone cave in this world
    private static boolean hasDripstone(GameWorld world) {
        return world.hasBiome(Biome.DRIPSTONE_CAVES);
    }

    //Returns true if there is a lush cave in this world
    private static boolean hasLushCave(GameWorld world) {
        return world.hasBiome(Biome.LUSH_CAVES);
    }

    //Returns true of podzol is naturally generated in this world
    private static boolean hasPodzol(GameWorld world) {
        return world.hasBiome(Biome.OLD_GROWTH_PINE_TAIGA) ||
                world.hasBiome(Biome.OLD_GROWTH_PINE_TAIGA) ||
                world.hasBiome(Biome.BAMBOO_JUNGLE);
    }

    //Returns true if there is a crimson forest in this world
    private static boolean hasCrimson(GameWorld world) {
        return world.hasBiome(Biome.CRIMSON_FOREST);
    }

    //Returns true if there is a warped forest in this world
    private static boolean hasWarped(GameWorld world) {
        return world.hasBiome(Biome.WARPED_FOREST);
    }

    //Returns true if oak trees generate in this world
    private static boolean hasOak(GameWorld world) {
        return world.hasBiome(Biome.BAMBOO_JUNGLE) ||
                world.hasBiome(Biome.DARK_FOREST) ||
                world.hasBiome(Biome.FOREST) ||
                world.hasBiome(Biome.JUNGLE) ||
                world.hasBiome(Biome.SPARSE_JUNGLE) ||
                world.hasBiome(Biome.PLAINS) ||
                world.hasBiome(Biome.SAVANNA) ||
                world.hasBiome(Biome.SWAMP) ||
                world.hasBiome(Biome.WOODED_BADLANDS) ||
                world.hasBiome(Biome.WINDSWEPT_FOREST) ||
                world.hasBiome(Biome.MEADOW);
    }

    //Returns true if spruce trees generate in this world
    private static boolean hasSpruce(GameWorld world) {
        return world.hasBiome(Biome.OLD_GROWTH_SPRUCE_TAIGA) ||
                world.hasBiome(Biome.OLD_GROWTH_PINE_TAIGA) ||
                world.hasBiome(Biome.SNOWY_TAIGA) ||
                world.hasBiome(Biome.SNOWY_PLAINS) ||
                world.hasBiome(Biome.TAIGA) ||
                world.hasBiome(Biome.WINDSWEPT_FOREST) ||
                world.hasBiome(Biome.GROVE);
    }

    //Returns true if birch trees generate in this world
    private static boolean hasBirch(GameWorld world) {
        return world.hasBiome(Biome.DARK_FOREST) ||
                world.hasBiome(Biome.FOREST) ||
                world.hasBiome(Biome.BIRCH_FOREST) ||
                world.hasBiome(Biome.OLD_GROWTH_BIRCH_FOREST) ||
                world.hasBiome(Biome.MEADOW);
    }

    //Returns true if jungle trees generate in this world
    private static boolean hasJungle(GameWorld world) {
        return world.hasBiome(Biome.BAMBOO_JUNGLE) ||
                world.hasBiome(Biome.JUNGLE) ||
                world.hasBiome(Biome.SPARSE_JUNGLE);
    }

    //Returns true if acacia trees generate in this world
    private static boolean hasAcacia(GameWorld world) {
        return world.hasBiome(Biome.SAVANNA) ||
                world.hasBiome(Biome.SAVANNA_PLATEAU);
    }

    //Returns true if cherry trees generate in this world
    private static boolean hasCherry(GameWorld world) {
        return world.hasBiome(Biome.CHERRY_GROVE);
    }

    //Returns true if dark oak trees generate in this world
    private static boolean hasDarkOak(GameWorld world) {
        return world.hasBiome(Biome.DARK_FOREST);
    }

    //Returns true if mangrove trees generate in this world
    private static boolean hasMangrove(GameWorld world) {
        return world.hasBiome(Biome.MANGROVE_SWAMP);
    }

    //Returns true if bamboo generates in this world
    private static boolean hasBamboo(GameWorld world) {
        return world.hasBiome(Biome.BAMBOO_JUNGLE);
    }

    //Returns true if suspicious sand can be generated in this world
    private static boolean hasSuspiciousSand(GameWorld world) {
        return world.hasStructure(Structure.DESERT_PYRAMID) ||
                world.hasStructure(Structure.OCEAN_RUIN_WARM);
    }

    //Returns true if suspicious gravel can be generated in this world
    private static boolean hasSuspiciousGravel(GameWorld world) {
        return world.hasStructure(Structure.OCEAN_RUIN_COLD);
    }

    //Returns true if red sand generates in this world
    private static boolean hasRedSand(GameWorld world) {
        return world.hasBiome(Biome.BADLANDS) ||
                world.hasBiome(Biome.WOODED_BADLANDS) ||
                world.hasBiome(Biome.ERODED_BADLANDS);
    }

    //Returns true if emeralds can naturally generate in this world
    private static boolean hasNaturalEmerald(GameWorld world) {
        return world.hasBiome(Biome.MEADOW) ||
                world.hasBiome(Biome.CHERRY_GROVE) ||
                world.hasBiome(Biome.GROVE) ||
                world.hasBiome(Biome.SNOWY_SLOPES) ||
                world.hasBiome(Biome.JAGGED_PEAKS) ||
                world.hasBiome(Biome.FROZEN_PEAKS) ||
                world.hasBiome(Biome.STONY_PEAKS) ||
                world.hasBiome(Biome.WINDSWEPT_HILLS) ||
                world.hasBiome(Biome.WINDSWEPT_GRAVELLY_HILLS) ||
                world.hasBiome(Biome.WINDSWEPT_FOREST);
    }

    //Returns true if a trial chamber has generated in this world
    private static boolean hasTrialChamber(GameWorld world) {
        return world.hasStructure(Structure.TRIAL_CHAMBERS);
    }

    //Returns true if bees can be reliably found in this world
    private static boolean hasBees(GameWorld world) {
        return world.hasBiome(Biome.MEADOW) ||
                world.hasBiome(Biome.PLAINS) ||
                world.hasBiome(Biome.SUNFLOWER_PLAINS) ||
                world.hasBiome(Biome.MANGROVE_SWAMP) ||
                world.hasBiome(Biome.FLOWER_FOREST) ||
                world.hasBiome(Biome.CHERRY_GROVE);
    }

    //Returns true if an ocean monument generated in this world
    private static boolean hasOceanMonument(GameWorld world) {
        return world.hasStructure(Structure.MONUMENT);
    }

    //Returns true if dead bushes generated in this world
    private static boolean hasDeadBush(GameWorld world) {
        return world.hasBiome(Biome.DESERT) ||
                world.hasBiome(Biome.BADLANDS) ||
                world.hasBiome(Biome.WOODED_BADLANDS) ||
                world.hasBiome(Biome.ERODED_BADLANDS) ||
                world.hasBiome(Biome.SWAMP) ||
                world.hasBiome(Biome.MANGROVE_SWAMP) ||
                world.hasBiome(Biome.OLD_GROWTH_PINE_TAIGA) ||
                world.hasBiome(Biome.OLD_GROWTH_SPRUCE_TAIGA);
    }

    //Returns true if seagrass can generate in this world
    private static boolean hasSeagrass(GameWorld world) {
        return world.hasBiome(Biome.OCEAN) ||
                world.hasBiome(Biome.COLD_OCEAN) ||
                world.hasBiome(Biome.DEEP_COLD_OCEAN) ||
                world.hasBiome(Biome.DEEP_OCEAN) ||
                world.hasBiome(Biome.DEEP_LUKEWARM_OCEAN) ||
                world.hasBiome(Biome.LUKEWARM_OCEAN) ||
                world.hasBiome(Biome.WARM_OCEAN);
    }

    //Returns true if kelp can generate in this world
    private static boolean hasKelp(GameWorld world) {
        return world.hasBiome(Biome.OCEAN) ||
                world.hasBiome(Biome.COLD_OCEAN) ||
                world.hasBiome(Biome.DEEP_COLD_OCEAN) ||
                world.hasBiome(Biome.DEEP_OCEAN) ||
                world.hasBiome(Biome.DEEP_LUKEWARM_OCEAN) ||
                world.hasBiome(Biome.LUKEWARM_OCEAN);
    }

    //Returns true if coral can generate in this world
    private static boolean hasCoral(GameWorld world) {
        return world.hasBiome(Biome.WARM_OCEAN);
    }

    //Returns true if blue orchids can generate in this world
    private static boolean hasBlueOrchid(GameWorld world) {
        return world.hasBiome(Biome.SWAMP);
    }

    //Returns true if allium can generate in this world
    private static boolean hasAllium(GameWorld world) {
        return world.hasBiome(Biome.FLOWER_FOREST) ||
                world.hasBiome(Biome.MEADOW);
    }

    //Returns true if azure bluet can generate in this world
    private static boolean hasAzureBluet(GameWorld world) {
        return world.hasBiome(Biome.FLOWER_FOREST) ||
                world.hasBiome(Biome.MEADOW) ||
                world.hasBiome(Biome.PLAINS) ||
                world.hasBiome(Biome.SUNFLOWER_PLAINS);
    }

    //Returns true of tulips can generate in this world
    private static boolean hasTulip(GameWorld world) {
        return world.hasBiome(Biome.FLOWER_FOREST) ||
                world.hasBiome(Biome.PLAINS) ||
                world.hasBiome(Biome.SUNFLOWER_PLAINS);
    }

    private static boolean hasOxeyeDaisy(GameWorld world) {
        return world.hasBiome(Biome.FLOWER_FOREST) ||
                world.hasBiome(Biome.MEADOW) ||
                world.hasBiome(Biome.PLAINS) ||
                world.hasBiome(Biome.SUNFLOWER_PLAINS);
    }

    private static boolean hasCornflower(GameWorld world) {
        return world.hasBiome(Biome.FLOWER_FOREST) ||
                world.hasBiome(Biome.MEADOW) ||
                world.hasBiome(Biome.PLAINS) ||
                world.hasBiome(Biome.SUNFLOWER_PLAINS);
    }

    private static boolean hasLilyOfTheValley(GameWorld world) {
        return world.hasBiome(Biome.FOREST) ||
                world.hasBiome(Biome.BIRCH_FOREST) ||
                world.hasBiome(Biome.OLD_GROWTH_BIRCH_FOREST) ||
                world.hasBiome(Biome.DARK_FOREST) ||
                world.hasBiome(Biome.FLOWER_FOREST);
    }

    private static boolean hasBrown(GameWorld world) {
        return world.hasBiome(Biome.JUNGLE) ||
                world.hasBiome(Biome.BAMBOO_JUNGLE) ||
                world.hasBiome(Biome.SPARSE_JUNGLE);
    }

    private static boolean hasGreen(GameWorld world) {
        return world.hasBiome(Biome.DESERT) ||
                world.hasBiome(Biome.BADLANDS);
    }

    private static boolean hasCyan(GameWorld world) {
        return world.hasStructure(Structure.OCEAN_RUIN_WARM) ||
                hasGreen(world);
    }

    private static boolean hasLime(GameWorld world) {
        return hasCoral(world) || hasGreen(world);
    }

    private static boolean hasBrownShulker(GameWorld world) {
        return hasBrown(world) && hasEndAccess(world);
    }

    private static boolean hasGreenShulker(GameWorld world) {
        return hasGreen(world) && hasEndAccess(world);
    }

    private static boolean hasCyanShulker(GameWorld world) {
        return hasCyan(world) && hasEndAccess(world);
    }

    private static boolean hasLimeShulker(GameWorld world) {
        return hasLime(world) && hasEndAccess(world);
    }

    private static boolean hasBrownBundle(GameWorld world) {
        return hasBrown(world) && hasRabbit(world);
    }

    private static boolean hasGreenBundle(GameWorld world) {
        return hasGreen(world) && hasRabbit(world);
    }

    private static boolean hasCyanBundle(GameWorld world) {
        return hasCyan(world) && hasRabbit(world);
    }

    private static boolean hasLimeBundle(GameWorld world) {
        return hasLime(world) && hasRabbit(world);
    }

    private static boolean hasBrownCandle(GameWorld world) {
        return hasBrown(world) && hasBees(world);
    }

    private static boolean hasGreenCandle(GameWorld world) {
        return hasGreen(world) && hasBees(world);
    }

    private static boolean hasCyanCandle(GameWorld world) {
        return hasCyan(world) && hasBees(world);
    }

    private static boolean hasLimeCandle(GameWorld world) {
        return hasLime(world) && hasBees(world);
    }

    private static boolean hasFortress(GameWorld world) {
        return world.hasStructure(Structure.FORTRESS);
    }

    private static boolean hasSniffer(GameWorld world) {
        return world.hasStructure(Structure.OCEAN_RUIN_WARM);
    }

    private static boolean hasMossCarpet(GameWorld world) {
        return world.hasBiome(Biome.LUSH_CAVES) ||
                world.hasBiome(Biome.MANGROVE_SWAMP);
    }

    private static boolean hasBogged(GameWorld world) {
        return hasMangrove(world) || hasSwamp(world);
    }

    private static boolean hasEndAccess(GameWorld world) {
        return world.hasStructure(Structure.FORTRESS) &&
                world.hasStructure(Structure.STRONGHOLD);
    }

    private static boolean hasSpawner(GameWorld world) {
        return world.hasStructure(Structure.MINESHAFT) ||
                world.hasStructure(Structure.STRONGHOLD) ||
                world.hasStructure(Structure.FORTRESS);
    }

    private static boolean hasMineshaft(GameWorld world) {
        return world.hasStructure(Structure.MINESHAFT);
    }

    private static boolean hasSnow(GameWorld world) {
        return world.hasBiome(Biome.SNOWY_TAIGA) ||
                world.hasBiome(Biome.SNOWY_SLOPES) ||
                world.hasBiome(Biome.SNOWY_PLAINS) ||
                world.hasBiome(Biome.SNOWY_BEACH) ||
                world.hasBiome(Biome.JAGGED_PEAKS) ||
                world.hasBiome(Biome.FROZEN_PEAKS) ||
                world.hasBiome(Biome.GROVE) ||
                world.hasBiome(Biome.FROZEN_RIVER) ||
                world.hasBiome(Biome.ICE_SPIKES);
    }

    private static boolean hasIce(GameWorld world) {
        return world.hasBiome(Biome.SNOWY_TAIGA) ||
                world.hasBiome(Biome.SNOWY_SLOPES) ||
                world.hasBiome(Biome.SNOWY_PLAINS) ||
                world.hasBiome(Biome.SNOWY_BEACH) ||
                world.hasBiome(Biome.FROZEN_RIVER) ||
                world.hasBiome(Biome.FROZEN_OCEAN) ||
                world.hasBiome(Biome.DEEP_FROZEN_OCEAN) ||
                world.hasBiome(Biome.ICE_SPIKES) ||
                world.hasBiome(Biome.FROZEN_PEAKS);
    }

    private static boolean hasSoulValley(GameWorld world) {
        return world.hasBiome(Biome.SOUL_SAND_VALLEY);
    }

    private static boolean hasBasalt(GameWorld world) {
        return world.hasBiome(Biome.BASALT_DELTAS);
    }

    private static boolean hasBlockInfestation(GameWorld world) {
        return world.hasBiome(Biome.WINDSWEPT_GRAVELLY_HILLS) ||
                world.hasBiome(Biome.WINDSWEPT_HILLS) ||
                world.hasBiome(Biome.WINDSWEPT_FOREST) ||
                world.hasBiome(Biome.MEADOW) ||
                world.hasBiome(Biome.CHERRY_GROVE) ||
                world.hasBiome(Biome.GROVE) ||
                world.hasBiome(Biome.SNOWY_SLOPES) ||
                world.hasBiome(Biome.JAGGED_PEAKS) ||
                world.hasBiome(Biome.FROZEN_PEAKS) ||
                world.hasBiome(Biome.STONY_PEAKS);
    }

    private static boolean hasStronghold(GameWorld world) {
        return world.hasStructure(Structure.STRONGHOLD);
    }

    private static boolean hasVines(GameWorld world) {
        return world.hasBiome(Biome.JUNGLE) ||
                world.hasBiome(Biome.SPARSE_JUNGLE) ||
                world.hasBiome(Biome.BAMBOO_JUNGLE) ||
                world.hasBiome(Biome.LUSH_CAVES) ||
                world.hasBiome(Biome.SWAMP) ||
                world.hasBiome(Biome.LUSH_CAVES);
    }

    private static boolean hasMossyBrick(GameWorld world) {
        return hasStronghold(world) || hasVines(world) || world.hasBiome(Biome.LUSH_CAVES);
    }

    private static boolean hasAncientCity(GameWorld world) {
        return world.hasStructure(Structure.ANCIENT_CITY);
    }

    private static boolean hasMushroomBiome(GameWorld world) {
        return world.hasBiome(Biome.MUSHROOM_FIELDS);
    }

    private static boolean hasSwamp(GameWorld world) {
        return world.hasBiome(Biome.SWAMP);
    }

    private static boolean hasDeepDark(GameWorld world) {
        return world.hasBiome(Biome.DEEP_DARK);
    }

    private static boolean hasEmerald(GameWorld world) {
        return hasNaturalEmerald(world) ||
                world.hasStructure(Structure.VILLAGE_DESERT) ||
                world.hasStructure(Structure.VILLAGE_PLAINS) ||
                world.hasStructure(Structure.VILLAGE_SAVANNA) ||
                world.hasStructure(Structure.VILLAGE_SNOWY) ||
                world.hasStructure(Structure.VILLAGE_TAIGA);
    }

    private static boolean hasMossyCobble(GameWorld world) {
        return hasVines(world) ||
                hasLushCave(world) ||
                world.hasStructure(Structure.JUNGLE_PYRAMID) ||
                world.hasStructure(Structure.PILLAGER_OUTPOST) ||
                world.hasStructure(Structure.OCEAN_RUIN_COLD) ||
                world.hasStructure(Structure.VILLAGE_PLAINS) ||
                world.hasStructure(Structure.VILLAGE_TAIGA) ||
                world.hasBiome(Biome.OLD_GROWTH_PINE_TAIGA) ||
                world.hasBiome(Biome.OLD_GROWTH_SPRUCE_TAIGA);

    }

    private static boolean hasSunflower(GameWorld world) {
        return world.hasBiome(Biome.SUNFLOWER_PLAINS);
    }

    private static boolean hasLilac(GameWorld world) {
        return world.hasBiome(Biome.FOREST) ||
                world.hasBiome(Biome.FLOWER_FOREST) ||
                world.hasBiome(Biome.BIRCH_FOREST) ||
                world.hasBiome(Biome.OLD_GROWTH_BIRCH_FOREST) ||
                world.hasBiome(Biome.DARK_FOREST);
    }

    private static boolean hasTallGrass(GameWorld world) {
        return world.hasBiome(Biome.PLAINS) ||
                world.hasBiome(Biome.SAVANNA) ||
                world.hasBiome(Biome.CHERRY_GROVE) ||
                world.hasBiome(Biome.MEADOW) ||
                world.hasBiome(Biome.LUSH_CAVES);
    }

    private static boolean hasLargeFern(GameWorld world) {
        return world.hasBiome(Biome.TAIGA) ||
                world.hasBiome(Biome.SNOWY_TAIGA) ||
                world.hasBiome(Biome.OLD_GROWTH_SPRUCE_TAIGA) ||
                world.hasBiome(Biome.OLD_GROWTH_PINE_TAIGA);
    }

    private static boolean hasTurtle(GameWorld world) {
        return world.hasBiome(Biome.BEACH);
    }

    private static boolean hasTurtleBreeding(GameWorld world) {
        return hasTurtle(world) && hasSeagrass(world);
    }

    private static boolean hasHeartOfTheSea(GameWorld world) {
        return world.hasStructure(Structure.BURIED_TREASURE);
    }

    private static boolean hasArmadillo(GameWorld world) {
        return world.hasBiome(Biome.BADLANDS) ||
                world.hasBiome(Biome.ERODED_BADLANDS) ||
                world.hasBiome(Biome.WOODED_BADLANDS) ||
                world.hasBiome(Biome.SAVANNA) ||
                world.hasBiome(Biome.SAVANNA_PLATEAU);
    }

    private static boolean hasPaleOak(GameWorld world) {
        return world.hasBiome(Biome.PALE_GARDEN);
    }

    private static boolean hasPoisonPotato(GameWorld world) {
        return world.hasStructure(Structure.SHIPWRECK);
    }

    private static boolean hasPaleGarden(GameWorld world) {
        return world.hasBiome(Biome.PALE_GARDEN);
    }

    private static boolean hasStray(GameWorld world) {
        return world.hasBiome(Biome.SNOWY_PLAINS)
                || world.hasBiome(Biome.ICE_SPIKES)
                || world.hasBiome(Biome.FROZEN_RIVER)
                || world.hasBiome(Biome.GROVE)
                || world.hasBiome(Biome.SNOWY_SLOPES);
    }

    private static boolean hasPolarBear(GameWorld world) {
        return world.hasBiome(Biome.FROZEN_OCEAN)
                || world.hasBiome(Biome.DEEP_FROZEN_OCEAN)
                || world.hasBiome(Biome.ICE_SPIKES)
                || world.hasBiome(Biome.SNOWY_PLAINS);
    }

    private static boolean hasNaturalLlama(GameWorld world) {
        return world.hasBiome(Biome.SAVANNA_PLATEAU)
                || world.hasBiome(Biome.WINDSWEPT_HILLS)
                || world.hasBiome(Biome.WINDSWEPT_GRAVELLY_HILLS)
                || world.hasBiome(Biome.WINDSWEPT_FOREST);
    }

    private static boolean hasDolphin(GameWorld world) {
        return world.hasBiome(Biome.DEEP_OCEAN)
                || world.hasBiome(Biome.OCEAN)
                || world.hasBiome(Biome.DEEP_LUKEWARM_OCEAN)
                || world.hasBiome(Biome.WARM_OCEAN)
                || world.hasBiome(Biome.LUKEWARM_OCEAN);
    }

    private static boolean hasDesert(GameWorld world) {
        return world.hasBiome(Biome.DESERT);
    }

    private static boolean inaccessible(GameWorld world) {
        return world.isLargeWorld();
    }

    private static boolean unobtainable(GameWorld world) {
        return false;
    }

    private static boolean guaranteed(GameWorld world) {
        return true;
    }

}

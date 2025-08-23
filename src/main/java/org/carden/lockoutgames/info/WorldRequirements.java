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
    public enum Element {
        /**
         * Enums here should match the correspond enum's name in the Spigot API (i.e. Material.DRIPSTONE_BLOCK should match ELEMENT.DRIPSTONE_BLOCK
         * A boolean function can be passed that checks whether a GameWorld has the required attributes for an element to be accessible
         */

        DRIPSTONE_BLOCK(WorldRequirements::hasDripstone),
        PODZOL(WorldRequirements::hasPodzol),
        ROOTED_DIRT(WorldRequirements::hasLushCave),
        CRIMSON_NYLIUM(WorldRequirements::hasCrimson),
        WARPED_NYLIUM(WorldRequirements::hasWarped),
        PETRIFIED_OAK_SLAB(WorldRequirements::inaccessible),
        OAK_PLANKS(WorldRequirements::hasOak),
        SPRUCE_PLANKS(WorldRequirements::hasSpruce),
        BIRCH_PLANKS(WorldRequirements::hasBirch),
        JUNGLE_PLANKS(WorldRequirements::hasJungle),
        ACACIA_PLANKS(WorldRequirements::hasAcacia),
        CHERRY_PLANKS(WorldRequirements::hasCherry),
        DARK_OAK_PLANKS(WorldRequirements::hasDarkOak),
        MANGROVE_PLANKS(WorldRequirements::hasMangrove),
        BAMBOO_PLANKS(WorldRequirements::hasBamboo),
        CRIMSON_PLANKS(WorldRequirements::hasCrimson),
        WARPED_PLANKS(WorldRequirements::hasWarped),
        BAMBOO_MOSAIC(WorldRequirements::hasBamboo),
        OAK_SAPLING(WorldRequirements::hasOak),
        OAK_LOG(WorldRequirements::hasOak),
        STRIPPED_OAK_LOG(WorldRequirements::hasOak),
        STRIPPED_OAK_WOOD(WorldRequirements::hasOak),
        OAK_WOOD(WorldRequirements::hasOak),
        OAK_LEAVES(WorldRequirements::hasOak),
        OAK_SLAB(WorldRequirements::hasOak),
        OAK_FENCE(WorldRequirements::hasOak),
        OAK_STAIRS(WorldRequirements::hasOak),
        OAK_BUTTON(WorldRequirements::hasOak),
        OAK_PRESSURE_PLATE(WorldRequirements::hasOak),
        OAK_DOOR(WorldRequirements::hasOak),
        OAK_TRAPDOOR(WorldRequirements::hasOak),
        OAK_FENCE_GATE(WorldRequirements::hasOak),
        OAK_BOAT(WorldRequirements::hasOak),
        OAK_CHEST_BOAT(WorldRequirements::hasOak),
        OAK_SIGN(WorldRequirements::hasOak),
        OAK_HANGING_SIGN(WorldRequirements::hasOak),
        OAK_WALL_SIGN(WorldRequirements::hasOak),
        OAK_WALL_HANGING_SIGN(WorldRequirements::hasOak),
        POTTED_OAK_SAPLING(WorldRequirements::hasOak),
        SPRUCE_SAPLING(WorldRequirements::hasSpruce),
        SPRUCE_LOG(WorldRequirements::hasSpruce),
        STRIPPED_SPRUCE_LOG(WorldRequirements::hasSpruce),
        STRIPPED_SPRUCE_WOOD(WorldRequirements::hasSpruce),
        SPRUCE_WOOD(WorldRequirements::hasSpruce),
        SPRUCE_LEAVES(WorldRequirements::hasSpruce),
        SPRUCE_SLAB(WorldRequirements::hasSpruce),
        SPRUCE_FENCE(WorldRequirements::hasSpruce),
        SPRUCE_STAIRS(WorldRequirements::hasSpruce),
        SPRUCE_BUTTON(WorldRequirements::hasSpruce),
        SPRUCE_PRESSURE_PLATE(WorldRequirements::hasSpruce),
        SPRUCE_DOOR(WorldRequirements::hasSpruce),
        SPRUCE_TRAPDOOR(WorldRequirements::hasSpruce),
        SPRUCE_FENCE_GATE(WorldRequirements::hasSpruce),
        SPRUCE_BOAT(WorldRequirements::hasSpruce),
        SPRUCE_CHEST_BOAT(WorldRequirements::hasSpruce),
        SPRUCE_SIGN(WorldRequirements::hasSpruce),
        SPRUCE_HANGING_SIGN(WorldRequirements::hasSpruce),
        SPRUCE_WALL_SIGN(WorldRequirements::hasSpruce),
        SPRUCE_WALL_HANGING_SIGN(WorldRequirements::hasSpruce),
        POTTED_SPRUCE_SAPLING(WorldRequirements::hasSpruce),
        BIRCH_SAPLING(WorldRequirements::hasBirch),
        BIRCH_LOG(WorldRequirements::hasBirch),
        STRIPPED_BIRCH_LOG(WorldRequirements::hasBirch),
        STRIPPED_BIRCH_WOOD(WorldRequirements::hasBirch),
        BIRCH_WOOD(WorldRequirements::hasBirch),
        BIRCH_LEAVES(WorldRequirements::hasBirch),
        BIRCH_SLAB(WorldRequirements::hasBirch),
        BIRCH_FENCE(WorldRequirements::hasBirch),
        BIRCH_STAIRS(WorldRequirements::hasBirch),
        BIRCH_BUTTON(WorldRequirements::hasBirch),
        BIRCH_PRESSURE_PLATE(WorldRequirements::hasBirch),
        BIRCH_DOOR(WorldRequirements::hasBirch),
        BIRCH_TRAPDOOR(WorldRequirements::hasBirch),
        BIRCH_FENCE_GATE(WorldRequirements::hasBirch),
        BIRCH_BOAT(WorldRequirements::hasBirch),
        BIRCH_CHEST_BOAT(WorldRequirements::hasBirch),
        BIRCH_SIGN(WorldRequirements::hasBirch),
        BIRCH_HANGING_SIGN(WorldRequirements::hasBirch),
        BIRCH_WALL_SIGN(WorldRequirements::hasBirch),
        BIRCH_WALL_HANGING_SIGN(WorldRequirements::hasBirch),
        POTTED_BIRCH_SAPLING(WorldRequirements::hasBirch),
        JUNGLE_SAPLING(WorldRequirements::hasJungle),
        JUNGLE_LOG(WorldRequirements::hasJungle),
        STRIPPED_JUNGLE_LOG(WorldRequirements::hasJungle),
        STRIPPED_JUNGLE_WOOD(WorldRequirements::hasJungle),
        JUNGLE_WOOD(WorldRequirements::hasJungle),
        JUNGLE_LEAVES(WorldRequirements::hasJungle),
        JUNGLE_SLAB(WorldRequirements::hasJungle),
        JUNGLE_FENCE(WorldRequirements::hasJungle),
        JUNGLE_STAIRS(WorldRequirements::hasJungle),
        JUNGLE_BUTTON(WorldRequirements::hasJungle),
        JUNGLE_PRESSURE_PLATE(WorldRequirements::hasJungle),
        JUNGLE_DOOR(WorldRequirements::hasJungle),
        JUNGLE_TRAPDOOR(WorldRequirements::hasJungle),
        JUNGLE_FENCE_GATE(WorldRequirements::hasJungle),
        JUNGLE_BOAT(WorldRequirements::hasJungle),
        JUNGLE_CHEST_BOAT(WorldRequirements::hasJungle),
        JUNGLE_SIGN(WorldRequirements::hasJungle),
        JUNGLE_HANGING_SIGN(WorldRequirements::hasJungle),
        JUNGLE_WALL_SIGN(WorldRequirements::hasJungle),
        JUNGLE_WALL_HANGING_SIGN(WorldRequirements::hasJungle),
        POTTED_JUNGLE_SAPLING(WorldRequirements::hasJungle),
        ACACIA_SAPLING(WorldRequirements::hasAcacia),
        ACACIA_LOG(WorldRequirements::hasAcacia),
        STRIPPED_ACACIA_LOG(WorldRequirements::hasAcacia),
        STRIPPED_ACACIA_WOOD(WorldRequirements::hasAcacia),
        ACACIA_WOOD(WorldRequirements::hasAcacia),
        ACACIA_LEAVES(WorldRequirements::hasAcacia),
        ACACIA_SLAB(WorldRequirements::hasAcacia),
        ACACIA_FENCE(WorldRequirements::hasAcacia),
        ACACIA_STAIRS(WorldRequirements::hasAcacia),
        ACACIA_BUTTON(WorldRequirements::hasAcacia),
        ACACIA_PRESSURE_PLATE(WorldRequirements::hasAcacia),
        ACACIA_DOOR(WorldRequirements::hasAcacia),
        ACACIA_TRAPDOOR(WorldRequirements::hasAcacia),
        ACACIA_FENCE_GATE(WorldRequirements::hasAcacia),
        ACACIA_BOAT(WorldRequirements::hasAcacia),
        ACACIA_CHEST_BOAT(WorldRequirements::hasAcacia),
        ACACIA_SIGN(WorldRequirements::hasAcacia),
        ACACIA_HANGING_SIGN(WorldRequirements::hasAcacia),
        ACACIA_WALL_SIGN(WorldRequirements::hasAcacia),
        ACACIA_WALL_HANGING_SIGN(WorldRequirements::hasAcacia),
        POTTED_ACACIA_SAPLING(WorldRequirements::hasAcacia),
        CHERRY_SAPLING(WorldRequirements::hasCherry),
        CHERRY_LOG(WorldRequirements::hasCherry),
        STRIPPED_CHERRY_LOG(WorldRequirements::hasCherry),
        STRIPPED_CHERRY_WOOD(WorldRequirements::hasCherry),
        CHERRY_WOOD(WorldRequirements::hasCherry),
        CHERRY_LEAVES(WorldRequirements::hasCherry),
        CHERRY_SLAB(WorldRequirements::hasCherry),
        CHERRY_FENCE(WorldRequirements::hasCherry),
        CHERRY_STAIRS(WorldRequirements::hasCherry),
        CHERRY_BUTTON(WorldRequirements::hasCherry),
        CHERRY_PRESSURE_PLATE(WorldRequirements::hasCherry),
        CHERRY_DOOR(WorldRequirements::hasCherry),
        CHERRY_TRAPDOOR(WorldRequirements::hasCherry),
        CHERRY_FENCE_GATE(WorldRequirements::hasCherry),
        CHERRY_BOAT(WorldRequirements::hasCherry),
        CHERRY_CHEST_BOAT(WorldRequirements::hasCherry),
        CHERRY_SIGN(WorldRequirements::hasCherry),
        CHERRY_HANGING_SIGN(WorldRequirements::hasCherry),
        CHERRY_WALL_SIGN(WorldRequirements::hasCherry),
        CHERRY_WALL_HANGING_SIGN(WorldRequirements::hasCherry),
        POTTED_CHERRY_SAPLING(WorldRequirements::hasCherry),
        DARK_OAK_SAPLING(WorldRequirements::hasDarkOak),
        DARK_OAK_LOG(WorldRequirements::hasDarkOak),
        STRIPPED_DARK_OAK_LOG(WorldRequirements::hasDarkOak),
        STRIPPED_DARK_OAK_WOOD(WorldRequirements::hasDarkOak),
        DARK_OAK_WOOD(WorldRequirements::hasDarkOak),
        DARK_OAK_LEAVES(WorldRequirements::hasDarkOak),
        DARK_OAK_SLAB(WorldRequirements::hasDarkOak),
        DARK_OAK_FENCE(WorldRequirements::hasDarkOak),
        DARK_OAK_STAIRS(WorldRequirements::hasDarkOak),
        DARK_OAK_BUTTON(WorldRequirements::hasDarkOak),
        DARK_OAK_PRESSURE_PLATE(WorldRequirements::hasDarkOak),
        DARK_OAK_DOOR(WorldRequirements::hasDarkOak),
        DARK_OAK_TRAPDOOR(WorldRequirements::hasDarkOak),
        DARK_OAK_FENCE_GATE(WorldRequirements::hasDarkOak),
        DARK_OAK_BOAT(WorldRequirements::hasDarkOak),
        DARK_OAK_CHEST_BOAT(WorldRequirements::hasDarkOak),
        DARK_OAK_SIGN(WorldRequirements::hasDarkOak),
        DARK_OAK_HANGING_SIGN(WorldRequirements::hasDarkOak),
        DARK_OAK_WALL_SIGN(WorldRequirements::hasDarkOak),
        DARK_OAK_WALL_HANGING_SIGN(WorldRequirements::hasDarkOak),
        POTTED_DARK_OAK_SAPLING(WorldRequirements::hasDarkOak),
        MANGROVE_PROPAGULE(WorldRequirements::hasMangrove),
        MANGROVE_LOG(WorldRequirements::hasMangrove),
        STRIPPED_MANGROVE_LOG(WorldRequirements::hasMangrove),
        STRIPPED_MANGROVE_WOOD(WorldRequirements::hasMangrove),
        MANGROVE_WOOD(WorldRequirements::hasMangrove),
        MANGROVE_LEAVES(WorldRequirements::hasMangrove),
        MANGROVE_SLAB(WorldRequirements::hasMangrove),
        MANGROVE_FENCE(WorldRequirements::hasMangrove),
        MANGROVE_STAIRS(WorldRequirements::hasMangrove),
        MANGROVE_BUTTON(WorldRequirements::hasMangrove),
        MANGROVE_PRESSURE_PLATE(WorldRequirements::hasMangrove),
        MANGROVE_DOOR(WorldRequirements::hasMangrove),
        MANGROVE_TRAPDOOR(WorldRequirements::hasMangrove),
        MANGROVE_FENCE_GATE(WorldRequirements::hasMangrove),
        MANGROVE_BOAT(WorldRequirements::hasMangrove),
        MANGROVE_CHEST_BOAT(WorldRequirements::hasMangrove),
        MANGROVE_SIGN(WorldRequirements::hasMangrove),
        MANGROVE_HANGING_SIGN(WorldRequirements::hasMangrove),
        MANGROVE_WALL_SIGN(WorldRequirements::hasMangrove),
        MANGROVE_WALL_HANGING_SIGN(WorldRequirements::hasMangrove),
        POTTED_MANGROVE_SAPLING(WorldRequirements::hasMangrove),
        BAMBOO_BLOCK(WorldRequirements::hasBamboo),
        STRIPPED_BAMBOO_BLOCK(WorldRequirements::hasBamboo),
        BAMBOO(WorldRequirements::hasBamboo),
        BAMBOO_SLAB(WorldRequirements::hasBamboo),
        BAMBOO_MOSAIC_SLAB(WorldRequirements::hasBamboo),
        BAMBOO_FENCE(WorldRequirements::hasBamboo),
        BAMBOO_STAIRS(WorldRequirements::hasBamboo),
        BAMBOO_MOSAIC_STAIRS(WorldRequirements::hasBamboo),
        BAMBOO_BUTTON(WorldRequirements::hasBamboo),
        BAMBOO_PRESSURE_PLATE(WorldRequirements::hasBamboo),
        BAMBOO_DOOR(WorldRequirements::hasBamboo),
        BAMBOO_TRAPDOOR(WorldRequirements::hasBamboo),
        BAMBOO_FENCE_GATE(WorldRequirements::hasBamboo),
        BAMBOO_RAFT(WorldRequirements::hasBamboo),
        BAMBOO_CHEST_RAFT(WorldRequirements::hasBamboo),
        BAMBOO_SIGN(WorldRequirements::hasBamboo),
        BAMBOO_HANGING_SIGN(WorldRequirements::hasBamboo),
        BAMBOO_WALL_SIGN(WorldRequirements::hasBamboo),
        BAMBOO_WALL_HANGING_SIGN(WorldRequirements::hasBamboo),
        BAMBOO_SAPLING(WorldRequirements::hasBamboo),
        POTTED_BAMBOO(WorldRequirements::hasBamboo),
        CRIMSON_STEM(WorldRequirements::hasCrimson),
        STRIPPED_CRIMSON_STEM(WorldRequirements::hasCrimson),
        STRIPPED_CRIMSON_HYPHAE(WorldRequirements::hasCrimson),
        CRIMSON_HYPHAE(WorldRequirements::hasCrimson),
        CRIMSON_FUNGUS(WorldRequirements::hasCrimson),
        CRIMSON_ROOTS(WorldRequirements::hasCrimson),
        CRIMSON_SLAB(WorldRequirements::hasCrimson),
        CRIMSON_FENCE(WorldRequirements::hasCrimson),
        CRIMSON_STAIRS(WorldRequirements::hasCrimson),
        CRIMSON_BUTTON(WorldRequirements::hasCrimson),
        CRIMSON_PRESSURE_PLATE(WorldRequirements::hasCrimson),
        CRIMSON_DOOR(WorldRequirements::hasCrimson),
        CRIMSON_TRAPDOOR(WorldRequirements::hasCrimson),
        CRIMSON_FENCE_GATE(WorldRequirements::hasCrimson),
        CRIMSON_SIGN(WorldRequirements::hasCrimson),
        CRIMSON_HANGING_SIGN(WorldRequirements::hasCrimson),
        CRIMSON_WALL_HANGING_SIGN(WorldRequirements::hasCrimson),
        CRIMSON_WALL_SIGN(WorldRequirements::hasCrimson),
        POTTED_CRIMSON_FUNGUS(WorldRequirements::hasCrimson),
        POTTED_CRIMSON_ROOTS(WorldRequirements::hasCrimson),
        WARPED_STEM(WorldRequirements::hasWarped),
        STRIPPED_WARPED_STEM(WorldRequirements::hasWarped),
        STRIPPED_WARPED_HYPHAE(WorldRequirements::hasWarped),
        WARPED_HYPHAE(WorldRequirements::hasWarped),
        WARPED_FUNGUS(WorldRequirements::hasWarped),
        WARPED_ROOTS(WorldRequirements::hasWarped),
        WARPED_SLAB(WorldRequirements::hasWarped),
        WARPED_FENCE(WorldRequirements::hasWarped),
        WARPED_STAIRS(WorldRequirements::hasWarped),
        WARPED_BUTTON(WorldRequirements::hasWarped),
        WARPED_PRESSURE_PLATE(WorldRequirements::hasWarped),
        WARPED_DOOR(WorldRequirements::hasWarped),
        WARPED_TRAPDOOR(WorldRequirements::hasWarped),
        WARPED_FENCE_GATE(WorldRequirements::hasWarped),
        WARPED_SIGN(WorldRequirements::hasWarped),
        WARPED_HANGING_SIGN(WorldRequirements::hasWarped),
        WARPED_WALL_HANGING_SIGN(WorldRequirements::hasWarped),
        WARPED_WALL_SIGN(WorldRequirements::hasWarped),
        POTTED_WARPED_FUNGUS(WorldRequirements::hasWarped),
        POTTED_WARPED_ROOTS(WorldRequirements::hasWarped),
        SUSPICIOUS_SAND(WorldRequirements::hasSuspiciousSand),
        SUSPICIOUS_GRAVEL(WorldRequirements::hasSuspiciousGravel),
        RED_SAND(WorldRequirements::hasRedSand),
        EMERALD_ORE(WorldRequirements::hasNaturalEmerald),
        DEEPSLATE_EMERALD_ORE(WorldRequirements::hasNaturalEmerald),
        HEAVY_CORE(WorldRequirements::hasTrialChamber),
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
        MANGROVE_ROOTS(WorldRequirements::hasMangrove),
        MUDDY_MANGROVE_ROOTS(WorldRequirements::hasMangrove),
        AZALEA_LEAVES(WorldRequirements::hasLushCave),
        FLOWERING_AZALEA_LEAVES(WorldRequirements::hasLushCave),
        SPONGE(WorldRequirements::hasOceanMonument),
        WET_SPONGE(WorldRequirements::hasOceanMonument),
        COBWEB(WorldRequirements::hasCobweb),
        FERN(WorldRequirements::hasFern),
        AZALEA(WorldRequirements::hasLushCave),
        FLOWERING_AZALEA(WorldRequirements::hasLushCave),
        DEAD_BUSH(WorldRequirements::hasDeadBush),
        SEAGRASS(WorldRequirements::hasSeagrass),
        SEA_PICKLE(WorldRequirements::hasCoral),
        LIME_WOOL(WorldRequirements::hasLime),
        CYAN_WOOL(WorldRequirements::hasCyan),
        BROWN_WOOL(WorldRequirements::hasBrown),
        GREEN_WOOL(WorldRequirements::hasGreen),
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
        NETHER_SPROUTS(WorldRequirements::hasWarped),
        WEEPING_VINES(WorldRequirements::hasCrimson),
        TWISTING_VINES(WorldRequirements::hasWarped),
        KELP(WorldRequirements::hasKelp),
        MOSS_CARPET(WorldRequirements::hasMossCarpet),
        PINK_PETALS(WorldRequirements::hasCherry),
        MOSS_BLOCK(WorldRequirements::hasLushCave),
        HANGING_ROOTS(WorldRequirements::hasLushCave),
        BIG_DRIPLEAF(WorldRequirements::hasLushCave),
        SMALL_DRIPLEAF(WorldRequirements::hasLushCave),
        RED_SANDSTONE_SLAB(WorldRequirements::hasRedSand),
        CUT_RED_SANDSTONE_SLAB(WorldRequirements::hasRedSand),
        PURPUR_SLAB(WorldRequirements::hasEndAccess),
        PRISMARINE_SLAB(WorldRequirements::hasOceanMonument),
        PRISMARINE_BRICK_SLAB(WorldRequirements::hasOceanMonument),
        DARK_PRISMARINE_SLAB(WorldRequirements::hasOceanMonument),
        SMOOTH_RED_SANDSTONE(WorldRequirements::hasRedSand),
        DECORATED_POT(WorldRequirements::hasTrialChamber),
        END_ROD(WorldRequirements::hasEndAccess),
        CHORUS_PLANT(WorldRequirements::hasEndAccess),
        CHORUS_FLOWER(WorldRequirements::hasEndAccess),
        PURPUR_BLOCK(WorldRequirements::hasEndAccess),
        PURPUR_PILLAR(WorldRequirements::hasEndAccess),
        PURPUR_STAIRS(WorldRequirements::hasEndAccess),
        SPAWNER(WorldRequirements::hasSpawner),
        SNOW(WorldRequirements::hasSnow),
        ICE(WorldRequirements::hasIce),
        SNOW_BLOCK(WorldRequirements::hasSnow),
        CACTUS(WorldRequirements::hasGreen),
        SOUL_SOIL(WorldRequirements::hasSoulValley),
        BASALT(WorldRequirements::hasBasalt),
        POLISHED_BASALT(WorldRequirements::hasBasalt),
        SMOOTH_BASALT(WorldRequirements::hasBasalt),
        INFESTED_STONE(WorldRequirements::hasBlockInfestation),
        INFESTED_DEEPSLATE(WorldRequirements::hasBlockInfestation),
        INFESTED_COBBLESTONE(WorldRequirements::unobtainable),
        INFESTED_STONE_BRICKS(WorldRequirements::hasStronghold),
        INFESTED_MOSSY_STONE_BRICKS(WorldRequirements::hasStronghold),
        INFESTED_CRACKED_STONE_BRICKS(WorldRequirements::hasStronghold),
        INFESTED_CHISELED_STONE_BRICKS(WorldRequirements::hasStronghold),
        MOSSY_STONE_BRICKS(WorldRequirements::hasMossyBrick),
        REINFORCED_DEEPSLATE(WorldRequirements::hasAncientCity),
        MELON(WorldRequirements::hasJungle),
        VINE(WorldRequirements::hasVines),
        MYCELIUM(WorldRequirements::hasMushroomBiome),
        LILY_PAD(WorldRequirements::hasSwamp),
        SCULK(WorldRequirements::hasDeepDark),
        SCULK_VEIN(WorldRequirements::hasDeepDark),
        SCULK_CATALYST(WorldRequirements::hasDeepDark),
        SCULK_SHRIEKER(WorldRequirements::hasDeepDark),
        END_PORTAL_FRAME(WorldRequirements::hasStronghold),
        END_STONE(WorldRequirements::hasEndAccess),
        END_STONE_BRICKS(WorldRequirements::hasEndAccess),
        DRAGON_EGG(WorldRequirements::hasEndAccess),
        ENDER_CHEST(WorldRequirements::hasFortress),
        EMERALD_BLOCK(WorldRequirements::hasEmerald),
        COMMAND_BLOCK(WorldRequirements::unobtainable),
        BEACON(WorldRequirements::hasFortress),
        MOSSY_COBBLESTONE(WorldRequirements::hasMossyCobble),
        MOSSY_COBBLESTONE_WALL(WorldRequirements::hasMossyCobble),
        PRISMARINE_WALL(WorldRequirements::hasOceanMonument),
        RED_SANDSTONE_WALL(WorldRequirements::hasRedSand),
        MOSSY_STONE_BRICK_WALL(WorldRequirements::hasMossyBrick),
        RED_NETHER_BRICK_WALL(WorldRequirements::hasFortress),
        END_STONE_BRICK_WALL(WorldRequirements::hasEndAccess),
        LIME_TERRACOTTA(WorldRequirements::hasLime),
        CYAN_TERRACOTTA(WorldRequirements::hasCyan),
        BROWN_TERRACOTTA(WorldRequirements::hasBrown),
        GREEN_TERRACOTTA(WorldRequirements::hasGreen),
        BARRIER(WorldRequirements::unobtainable),
        LIGHT(WorldRequirements::unobtainable),
        LIME_CARPET(WorldRequirements::hasLime),
        CYAN_CARPET(WorldRequirements::hasCyan),
        BROWN_CARPET(WorldRequirements::hasBrown),
        GREEN_CARPET(WorldRequirements::hasGreen),
        PACKED_ICE(WorldRequirements::hasIce),
        SUNFLOWER(WorldRequirements::hasSunflower),
        LILAC(WorldRequirements::hasLilac),
        ROSE_BUSH(WorldRequirements::hasLilac),
        PEONY(WorldRequirements::hasLilac),
        TALL_GRASS(WorldRequirements::hasTallGrass),
        LARGE_FERN(WorldRequirements::hasLargeFern),
        LIME_STAINED_GLASS(WorldRequirements::hasLime),
        CYAN_STAINED_GLASS(WorldRequirements::hasCyan),
        BROWN_STAINED_GLASS(WorldRequirements::hasBrown),
        GREEN_STAINED_GLASS(WorldRequirements::hasGreen),
        LIME_STAINED_GLASS_PANE(WorldRequirements::hasLime),
        CYAN_STAINED_GLASS_PANE(WorldRequirements::hasCyan),
        BROWN_STAINED_GLASS_PANE(WorldRequirements::hasBrown),
        GREEN_STAINED_GLASS_PANE(WorldRequirements::hasGreen),
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
        NETHER_WART_BLOCK(WorldRequirements::hasCrimson),
        WARPED_WART_BLOCK(WorldRequirements::hasWarped),
        RED_NETHER_BRICK(WorldRequirements::hasFortress),
        STRUCTURE_VOID(WorldRequirements::unobtainable),
        SHULKER_BOX(WorldRequirements::hasEndAccess),
        LIME_SHULKER_BOX(WorldRequirements::hasLimeShulker),
        CYAN_SHULKER_BOX(WorldRequirements::hasCyanShulker),
        BROWN_SHULKER_BOX(WorldRequirements::hasBrownShulker),
        GREEN_SHULKER_BOX(WorldRequirements::hasGreenShulker),
        LIME_GLAZED_TERRACOTTA(WorldRequirements::hasLime),
        CYAN_GLAZED_TERRACOTTA(WorldRequirements::hasCyan),
        BROWN_GLAZED_TERRACOTTA(WorldRequirements::hasBrown),
        GREEN_GLAZED_TERRACOTTA(WorldRequirements::hasGreen),
        LIME_CONCRETE(WorldRequirements::hasLime),
        CYAN_CONCRETE(WorldRequirements::hasCyan),
        BROWN_CONCRETE(WorldRequirements::hasBrown),
        GREEN_CONCRETE(WorldRequirements::hasGreen),
        LIME_CONCRETE_POWDER(WorldRequirements::hasLime),
        CYAN_CONCRETE_POWDER(WorldRequirements::hasCyan),
        BROWN_CONCRETE_POWDER(WorldRequirements::hasBrown),
        GREEN_CONCRETE_POWDER(WorldRequirements::hasGreen),
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
        SMOOTH_RED_SANDSTONE_STAIRS(WorldRequirements::hasRedSand),
        MOSSY_STONE_BRICK_STAIRS(WorldRequirements::hasMossyBrick),
        MOSSY_COBBLESTONE_STAIRS(WorldRequirements::hasMossyCobble),
        END_STONE_BRICK_STAIRS(WorldRequirements::hasEndAccess),
        RED_NETHER_BRICK_STAIRS(WorldRequirements::hasFortress),
        SMOOTH_RED_SANDSTONE_SLAB(WorldRequirements::hasRedSand),
        MOSSY_STONE_BRICK_SLAB(WorldRequirements::hasMossyBrick),
        MOSSY_COBBLESTONE_SLAB(WorldRequirements::hasMossyCobble),
        END_STONE_BRICK_SLAB(WorldRequirements::hasEndAccess),
        RED_NETHER_BRICK_SLAB(WorldRequirements::hasFortress),
        SCAFFOLDING(WorldRequirements::hasBamboo),
        HONEY_BLOCK(WorldRequirements::hasBees),
        SCULK_SENSOR(WorldRequirements::hasDeepDark),
        CALIBRATED_SCULK_SENSOR(WorldRequirements::hasDeepDark),
        WAXED_COPPER_DOOR(WorldRequirements::hasBees),
        WAXED_EXPOSED_COPPER_DOOR(WorldRequirements::hasBees),
        WAXED_WEATHERED_COPPER_DOOR(WorldRequirements::hasBees),
        WAXED_OXIDIZED_COPPER_DOOR(WorldRequirements::hasBees),
        WAXED_COPPER_TRAPDOOR(WorldRequirements::hasBees),
        WAXED_EXPOSED_COPPER_TRAPDOOR(WorldRequirements::hasBees),
        WAXED_WEATHERED_COPPER_TRAPDOOR(WorldRequirements::hasBees),
        WAXED_OXIDIZED_COPPER_TRAPDOOR(WorldRequirements::hasBees),
        WARPED_FUNGUS_ON_A_STICK(WorldRequirements::hasWarped),
        ELYTRA(WorldRequirements::hasEndAccess),
        STRUCTURE_BLOCK(WorldRequirements::unobtainable),
        JIGSAW(WorldRequirements::unobtainable),
        TURTLE_HELMET(WorldRequirements::hasTurtleBreeding),
        TURTLE_SCUTE(WorldRequirements::hasTurtleBreeding),
        ARMADILLO_SCUTE(WorldRequirements::hasArmadillo),
        WOLF_ARMOR(WorldRequirements::hasArmadillo),
        APPLE(WorldRequirements::hasApple),
        EMERALD(WorldRequirements::hasEmerald),
        CHAINMAIL_HELMET(WorldRequirements::hasVillage),
        CHAINMAIL_CHESTPLATE(WorldRequirements::hasVillage),
        CHAINMAIL_LEGGINGS(WorldRequirements::hasVillage),
        CHAINMAIL_BOOTS(WorldRequirements::hasVillage),
        GOLDEN_APPLE(WorldRequirements::hasApple),
        ENCHANTED_GOLDEN_APPLE(WorldRequirements::inaccessible),
        POWDER_SNOW_BUCKET(WorldRequirements::hasPowderedSnow),
        SNOWBALL(WorldRequirements::hasSnow),
        PUFFERFISH_BUCKET(WorldRequirements::hasTropicalFish),
        SALMON_BUCKET(WorldRequirements::hasSalmon),
        COD_BUCKET(WorldRequirements::hasCod),
        TROPICAL_FISH_BUCKET(WorldRequirements::hasTropicalFish),
        AXOLOTL_BUCKET(WorldRequirements::hasLushCave),
        TADPOLE_BUCKET(WorldRequirements::hasFrog),
        DRIED_KELP_BLOCK(WorldRequirements::hasKelp),
        RECOVERY_COMPASS(WorldRequirements::hasAncientCity),
        BUNDLE(WorldRequirements::hasRabbit),
        COD(WorldRequirements::hasCod),
        SALMON(WorldRequirements::hasSalmon),
        TROPICAL_FISH(WorldRequirements::hasTropicalFish),
        PUFFERFISH(WorldRequirements::hasTropicalFish),
        COOKED_COD(WorldRequirements::hasCod),
        COOKED_SALMON(WorldRequirements::hasSalmon),
        COCOA_BEANS(WorldRequirements::hasBrown),
        LIME_DYE(WorldRequirements::hasLime),
        CYAN_DYE(WorldRequirements::hasCyan),
        BROWN_DYE(WorldRequirements::hasBrown),
        GREEN_DYE(WorldRequirements::hasGreen),
        LIME_BED(WorldRequirements::hasLime),
        CYAN_BED(WorldRequirements::hasCyan),
        BROWN_BED(WorldRequirements::hasBrown),
        GREEN_BED(WorldRequirements::hasGreen),
        COOKIE(WorldRequirements::hasBrown),
        MELON_SLICE(WorldRequirements::hasJungle),
        DRIED_KELP(WorldRequirements::hasKelp),
        MELON_SEEDS(WorldRequirements::hasJungle),
        BLAZE_ROD(WorldRequirements::hasFortress),
        NETHER_WART(WorldRequirements::hasFortress),
        BLAZE_POWDER(WorldRequirements::hasFortress),
        MAGMA_CREAM(WorldRequirements::hasMagmaCube),
        BREWING_STAND(WorldRequirements::hasFortress),
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
        ZOMBIE_SPAWN_EGG(WorldRequirements::unobtainable),
        ZOMBIE_HORSE_SPAWN_EGG(WorldRequirements::unobtainable),
        ZOMBIE_VILLAGER_SPAWN_EGG(WorldRequirements::unobtainable),
        ZOMBIFIED_PIGLIN_SPAWN_EGG(WorldRequirements::unobtainable),
        EXPERIENCE_BOTTLE(WorldRequirements::hasVillage),
        FIRE_CHARGE(WorldRequirements::hasFortress),
        WIND_CHARGE(WorldRequirements::hasTrialChamber),
        MACE(WorldRequirements::inaccessible),
        POISONOUS_POTATO(WorldRequirements::hasPoisonPotato),
        WITHER_SKELETON_SKULL(WorldRequirements::hasFortress),
        PLAYER_HEAD(WorldRequirements::unobtainable),
        DRAGON_HEAD(WorldRequirements::hasEndAccess),
        NETHER_STAR(WorldRequirements::hasFortress),
        PRISMARINE_SHARD(WorldRequirements::hasOceanMonument),
        PRISMARINE_CRYSTALS(WorldRequirements::hasOceanMonument),
        RABBIT(WorldRequirements::hasRabbit),
        COOKED_RABBIT(WorldRequirements::hasRabbit),
        RABBIT_STEW(WorldRequirements::hasRabbit),
        RABBIT_FOOT(WorldRequirements::hasRabbit),
        RABBIT_HIDE(WorldRequirements::hasRabbit),
        IRON_HORSE_ARMOR(WorldRequirements::inaccessible),
        GOLDEN_HORSE_ARMOR(WorldRequirements::inaccessible),
        DIAMOND_HORSE_ARMOR(WorldRequirements::inaccessible),
        COMMAND_BLOCK_MINECART(WorldRequirements::unobtainable),
        LIME_BANNER(WorldRequirements::hasLime),
        CYAN_BANNER(WorldRequirements::hasCyan),
        BROWN_BANNER(WorldRequirements::hasBrown),
        GREEN_BANNER(WorldRequirements::hasGreen),
        END_CRYSTAL(WorldRequirements::hasEndCrystal),
        GHAST_TEAR(WorldRequirements::hasGhast),
        CHORUS_FRUIT(WorldRequirements::hasEndAccess),
        POPPED_CHORUS_FRUIT(WorldRequirements::hasEndAccess),
        TORCHFLOWER_SEEDS(WorldRequirements::hasSniffer),
        PITCHER_POD(WorldRequirements::hasSniffer),
        BEETROOT(WorldRequirements::hasVillage),
        BEETROOT_SEEDS(WorldRequirements::hasVillage),
        BEETROOT_SOUP(WorldRequirements::hasVillage),
        DRAGON_BREATH(WorldRequirements::hasEndAccess),
        TIPPED_ARROW(WorldRequirements::hasEndAccess),
        LINGERING_POTION(WorldRequirements::hasEndAccess),
        TOTEM_OF_UNDYING(WorldRequirements::hasEvoker),
        SHULKER_SHELL(WorldRequirements::hasEndAccess),
        KNOWLEDGE_BOOK(WorldRequirements::unobtainable),
        DEBUG_STICK(WorldRequirements::unobtainable),
        MUSIC_DISC_CREATOR(WorldRequirements::inaccessible),
        MUSIC_DISC_CREATOR_MUSIC_BOX(WorldRequirements::inaccessible),
        MUSIC_DISC_OTHERSIDE(WorldRequirements::inaccessible),
        MUSIC_DISC_RELIC(WorldRequirements::inaccessible),
        MUSIC_DISC_5(WorldRequirements::inaccessible),
        MUSIC_DISC_PIGSTEP(WorldRequirements::inaccessible),
        MUSIC_DISC_PRECIPICE(WorldRequirements::inaccessible),
        DISC_FRAGMENT_5(WorldRequirements::hasAncientCity),
        HEART_OF_THE_SEA(WorldRequirements::hasHeartOfTheSea),
        FLOWER_BANNER_PATTERN(WorldRequirements::hasOxeyeDaisy),
        SKULL_BANNER_PATTERN(WorldRequirements::hasFortress),
        MOJANG_BANNER_PATTERN(WorldRequirements::unobtainable),
        GLOBE_BANNER_PATTERN(WorldRequirements::hasVillage),
        PIGLIN_BANNER_PATTERN(WorldRequirements::unobtainable),
        FLOW_BANNER_PATTERN(WorldRequirements::unobtainable),
        GUSTER_BANNER_PATTERN(WorldRequirements::unobtainable),
        GOAT_HORN(WorldRequirements::hasGoat),
        BELL(WorldRequirements::hasVillage),
        SWEET_BERRIES(WorldRequirements::hasSweetBerries),
        GLOW_BERRIES(WorldRequirements::hasLushCave),
        SHROOMLIGHT(WorldRequirements::hasShroomlight),
        HONEYCOMB(WorldRequirements::hasBees),
        BEE_NEST(WorldRequirements::hasBees),
        BEEHIVE(WorldRequirements::hasBees),
        HONEY_BOTTLE(WorldRequirements::hasBees),
        HONEYCOMB_BLOCK(WorldRequirements::hasBees),
        CRYING_OBSIDIAN(WorldRequirements::hasCryingObsidian),
        GILDED_BLACKSTONE(WorldRequirements::hasBastion),
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
        POINTED_DRIPSTONE(WorldRequirements::hasDripstone),
        OCHRE_FROGLIGHT(WorldRequirements::hasOchreFroglight),
        VERDANT_FROGLIGHT(WorldRequirements::hasVerdantFroglight),
        PEARLESCENT_FROGLIGHT(WorldRequirements::hasPearlescentFroglight),
        FROGSPAWN(WorldRequirements::hasFrog),
        ECHO_SHARD(WorldRequirements::hasAncientCity),
        NETHERITE_UPGRADE_SMITHING_TEMPLATE(WorldRequirements::hasBastion),
        NETHERITE_SWORD(WorldRequirements::hasBastion),
        NETHERITE_SHOVEL(WorldRequirements::hasBastion),
        NETHERITE_PICKAXE(WorldRequirements::hasBastion),
        NETHERITE_AXE(WorldRequirements::hasBastion),
        NETHERITE_HOE(WorldRequirements::hasBastion),
        NETHERITE_HELMET(WorldRequirements::hasBastion),
        NETHERITE_CHESTPLATE(WorldRequirements::hasBastion),
        NETHERITE_LEGGINGS(WorldRequirements::hasBastion),
        NETHERITE_BOOTS(WorldRequirements::hasBastion),
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
        WAXED_COPPER_GRATE(WorldRequirements::hasBees),
        WAXED_EXPOSED_COPPER_GRATE(WorldRequirements::hasBees),
        WAXED_WEATHERED_COPPER_GRATE(WorldRequirements::hasBees),
        WAXED_OXIDIZED_COPPER_GRATE(WorldRequirements::hasBees),
        TRIAL_SPAWNER(WorldRequirements::hasTrialChamber),
        TRIAL_KEY(WorldRequirements::hasTrialChamber),
        OMINOUS_TRIAL_KEY(WorldRequirements::hasTrialChamber),
        VAULT(WorldRequirements::hasTrialChamber),
        OMINOUS_BOTTLE(WorldRequirements::hasPillagerOutpost),
        BREEZE_ROD(WorldRequirements::hasTrialChamber),
        TALL_SEAGRASS(WorldRequirements::hasSeagrass),
        ATTACHED_MELON_STEM(WorldRequirements::hasJungle),
        POWDER_SNOW_CAULDRON(WorldRequirements::hasPowderedSnow),
        END_PORTAL(WorldRequirements::hasEndAccess),
        COCOA(WorldRequirements::hasBrown),
        POTTED_TORCHFLOWER(WorldRequirements::hasSniffer),
        POTTED_FERN(WorldRequirements::hasFern),
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
        POTTED_DEAD_BUSH(WorldRequirements::hasDeadBush),
        POTTED_CACTUS(WorldRequirements::hasGreen),
        PLAYER_WALL_HEAD(WorldRequirements::inaccessible),
        WITHER_SKELETON_WALL_SKULL(WorldRequirements::hasFortress),
        DRAGON_WALL_HEAD(WorldRequirements::hasEndAccess),
        PIGLIN_WALL_HEAD(WorldRequirements::hasPiglin),
        LIME_WALL_BANNER(WorldRequirements::hasLime),
        CYAN_WALL_BANNER(WorldRequirements::hasCyan),
        BROWN_WALL_BANNER(WorldRequirements::hasBrown),
        GREEN_WALL_BANNER(WorldRequirements::hasGreen),
        TORCHFLOWER_CROP(WorldRequirements::hasSniffer),
        PITCHER_CROP(WorldRequirements::hasSniffer),
        BEETROOTS(WorldRequirements::hasVillage),
        END_GATEWAY(WorldRequirements::hasEndAccess),
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
        VOID_AIR(WorldRequirements::hasEndAccess),
        SWEET_BERRY_BUSH(WorldRequirements::hasSweetBerries),
        WEEPING_VINES_PLANT(WorldRequirements::hasCrimson),
        TWISTING_VINES_PLANT(WorldRequirements::hasWarped),
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
        COPPER_BULB(WorldRequirements::hasCopperBulb),
        WEATHERED_COPPER_BULB(WorldRequirements::hasCopperBulb),
        EXPOSED_COPPER_BULB(WorldRequirements::hasCopperBulb),
        OXIDIZED_COPPER_BULB(WorldRequirements::hasCopperBulb),
        WAXED_COPPER_BULB(WorldRequirements::hasWaxCopperBulb),
        WAXED_WEATHERED_COPPER_BULB(WorldRequirements::hasWaxCopperBulb),
        WAXED_EXPOSED_COPPER_BULB(WorldRequirements::hasWaxCopperBulb),
        WAXED_OXIDIZED_COPPER_BULB(WorldRequirements::hasWaxCopperBulb);


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
    public static boolean hasFern(GameWorld world) {
        return world.hasBiome(Biome.JUNGLE) ||
                world.hasBiome(Biome.TAIGA) ||
                world.hasBiome(Biome.SNOWY_TAIGA) ||
                world.hasBiome(Biome.OLD_GROWTH_SPRUCE_TAIGA) ||
                world.hasBiome(Biome.OLD_GROWTH_PINE_TAIGA);
    }

    public static boolean hasCopperBulb(GameWorld world) {
        return world.hasStructure(Structure.TRIAL_CHAMBERS) || world.hasStructure(Structure.FORTRESS);
    }

    public static boolean hasWaxCopperBulb(GameWorld world) {
        return hasCopperBulb(world) && hasFortress(world);
    }

    //Returns true if the world can generate crying obsidian consistently
    public static boolean hasCryingObsidian(GameWorld world) {
        return world.hasStructure(Structure.RUINED_PORTAL) ||
                world.hasStructure(Structure.RUINED_PORTAL_DESERT) ||
                world.hasStructure(Structure.RUINED_PORTAL_JUNGLE) ||
                world.hasStructure(Structure.RUINED_PORTAL_MOUNTAIN) ||
                world.hasStructure(Structure.RUINED_PORTAL_NETHER) ||
                world.hasStructure(Structure.RUINED_PORTAL_OCEAN) ||
                world.hasStructure(Structure.RUINED_PORTAL_SWAMP) ||
                hasPiglin(world);
    }

    //Returns true if a pillager outpost has generated in the world
    public static boolean hasPillagerOutpost(GameWorld world) {
        return world.hasStructure(Structure.PILLAGER_OUTPOST);
    }

    //Returns true if Evokers can be spawned in the world
    public static boolean hasEvoker(GameWorld world) {
        return (world.hasStructure(Structure.PILLAGER_OUTPOST) && hasVillage(world)) ||
                world.hasStructure(Structure.MANSION);
    }

    //Returns true if shroomlights generate in the world
    public static boolean hasShroomlight(GameWorld world) {
        return world.hasBiome(Biome.CRIMSON_FOREST) ||
                world.hasBiome(Biome.WARPED_FOREST);
    }

    //Returns true if sweet berries generate in the world
    public static boolean hasSweetBerries(GameWorld world) {
        return world.hasBiome(Biome.TAIGA) ||
                world.hasBiome(Biome.SNOWY_TAIGA) ||
                world.hasBiome(Biome.OLD_GROWTH_PINE_TAIGA) ||
                world.hasBiome(Biome.OLD_GROWTH_SPRUCE_TAIGA);
    }

    //Returns true if goats spawn in the world
    public static boolean hasGoat(GameWorld world) {
        return world.hasBiome(Biome.FROZEN_PEAKS) ||
                world.hasBiome(Biome.JAGGED_PEAKS) ||
                world.hasBiome(Biome.SNOWY_SLOPES);
    }

    //Returns true of ghasts spawn in the world
    public static boolean hasGhast(GameWorld world) {
        return world.hasBiome(Biome.NETHER_WASTES) ||
                world.hasBiome(Biome.SOUL_SAND_VALLEY) ||
                world.hasBiome(Biome.BASALT_DELTAS);
    }

    //Returns true if end crystals can be crafted from materials in this world
    public static boolean hasEndCrystal(GameWorld world) {
        return hasGhast(world) && hasFortress(world);
    }

    //Returns true if magma cubes spawn in this world
    public static boolean hasMagmaCube(GameWorld world) {
        return world.hasBiome(Biome.NETHER_WASTES) ||
                world.hasBiome(Biome.BASALT_DELTAS) ||
                world.hasStructure(Structure.FORTRESS);
    }

    //Returns true if rabbits spawn in this world
    public static boolean hasRabbit(GameWorld world) {
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
    public static boolean hasFrog(GameWorld world) {
        return world.hasBiome(Biome.SWAMP) ||
                world.hasBiome(Biome.MANGROVE_SWAMP);
    }

    //Returns true if temperate frogs can be raised in this world
    public static boolean hasTemperateFrog(GameWorld world) {
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
    public static boolean hasWarmFrog(GameWorld world) {
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
    public static boolean hasColdFrog(GameWorld world) {
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
    public static boolean hasPearlescentFroglight(GameWorld world) {
        return hasMagmaCube(world) && hasWarmFrog(world);
    }

    //Returns true if ochre froglights are accessible
    public static boolean hasOchreFroglight(GameWorld world) {
        return hasMagmaCube(world) && hasTemperateFrog(world);
    }

    //Returns true if verdant froglights are accessible
    public static boolean hasVerdantFroglight(GameWorld world) {
        return hasMagmaCube(world) && hasColdFrog(world);
    }

    //Returns true if piglins spawn in this world
    public static boolean hasPiglin(GameWorld world) {
        return world.hasBiome(Biome.NETHER_WASTES) ||
                world.hasBiome(Biome.CRIMSON_FOREST) ||
                world.hasStructure(Structure.BASTION_REMNANT);
    }

    //Returns true if a bastion has generated in this world
    public static boolean hasBastion(GameWorld world) {
        return world.hasStructure(Structure.BASTION_REMNANT);
    }

    //Returns true if cod spawn in this world
    public static boolean hasCod(GameWorld world) {
        return world.hasBiome(Biome.OCEAN) ||
                world.hasBiome(Biome.COLD_OCEAN) ||
                world.hasBiome(Biome.DEEP_COLD_OCEAN) ||
                world.hasBiome(Biome.DEEP_OCEAN) ||
                world.hasBiome(Biome.DEEP_LUKEWARM_OCEAN) ||
                world.hasBiome(Biome.LUKEWARM_OCEAN);
    }

    //Returns true if salmon spawn in this world
    public static boolean hasSalmon(GameWorld world) {
        return world.hasBiome(Biome.COLD_OCEAN) ||
                world.hasBiome(Biome.DEEP_COLD_OCEAN) ||
                world.hasBiome(Biome.FROZEN_OCEAN) ||
                world.hasBiome(Biome.DEEP_FROZEN_OCEAN) ||
                world.hasBiome(Biome.RIVER) ||
                world.hasBiome(Biome.FROZEN_RIVER);
    }

    //Returns true if tropical fish spawn in this world
    public static boolean hasTropicalFish(GameWorld world) {
        return world.hasBiome(Biome.WARM_OCEAN) ||
                world.hasBiome(Biome.LUKEWARM_OCEAN) ||
                world.hasBiome(Biome.DEEP_LUKEWARM_OCEAN);
    }

    //Returns true if powdered snow generates in this world
    public static boolean hasPowderedSnow(GameWorld world) {
        return world.hasBiome(Biome.GROVE) ||
                world.hasBiome(Biome.SNOWY_SLOPES);
    }

    //Returns true if there is a village in this world
    public static boolean hasVillage(GameWorld world) {
        return world.hasStructure(Structure.VILLAGE_PLAINS) ||
                world.hasStructure(Structure.VILLAGE_DESERT) ||
                world.hasStructure(Structure.VILLAGE_TAIGA) ||
                world.hasStructure(Structure.VILLAGE_SNOWY) ||
                world.hasStructure(Structure.VILLAGE_SAVANNA);
    }

    //Returns true if apples are naturally accessible in this world
    public static boolean hasApple(GameWorld world) {
        return hasOak(world) || hasDarkOak(world);
    }

    //Returns true if cobwebs are generating in this world
    public static boolean hasCobweb(GameWorld world) {
        return world.hasStructure(Structure.MINESHAFT) ||
                world.hasStructure(Structure.STRONGHOLD) ||
                world.hasStructure(Structure.MANSION);
    }

    //Returns true if there is a dripstone cave in this world
    public static boolean hasDripstone(GameWorld world) {
        return world.hasBiome(Biome.DRIPSTONE_CAVES);
    }

    //Returns true if there is a lush cave in this world
    public static boolean hasLushCave(GameWorld world) {
        return world.hasBiome(Biome.LUSH_CAVES);
    }

    //Returns true of podzol is naturally generated in this world
    public static boolean hasPodzol(GameWorld world) {
        return world.hasBiome(Biome.OLD_GROWTH_PINE_TAIGA) ||
                world.hasBiome(Biome.OLD_GROWTH_PINE_TAIGA) ||
                world.hasBiome(Biome.BAMBOO_JUNGLE);
    }

    //Returns true if there is a crimson forest in this world
    public static boolean hasCrimson(GameWorld world) {
        return world.hasBiome(Biome.CRIMSON_FOREST);
    }

    //Returns true if there is a warped forest in this world
    public static boolean hasWarped(GameWorld world) {
        return world.hasBiome(Biome.WARPED_FOREST);
    }

    //Returns true if oak trees generate in this world
    public static boolean hasOak(GameWorld world) {
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
    public static boolean hasSpruce(GameWorld world) {
        return world.hasBiome(Biome.OLD_GROWTH_SPRUCE_TAIGA) ||
                world.hasBiome(Biome.OLD_GROWTH_PINE_TAIGA) ||
                world.hasBiome(Biome.SNOWY_TAIGA) ||
                world.hasBiome(Biome.SNOWY_PLAINS) ||
                world.hasBiome(Biome.TAIGA) ||
                world.hasBiome(Biome.WINDSWEPT_FOREST) ||
                world.hasBiome(Biome.GROVE);
    }

    //Returns true if birch trees generate in this world
    public static boolean hasBirch(GameWorld world) {
        return world.hasBiome(Biome.DARK_FOREST) ||
                world.hasBiome(Biome.FOREST) ||
                world.hasBiome(Biome.BIRCH_FOREST) ||
                world.hasBiome(Biome.OLD_GROWTH_BIRCH_FOREST) ||
                world.hasBiome(Biome.MEADOW);
    }

    //Returns true if jungle trees generate in this world
    public static boolean hasJungle(GameWorld world) {
        return world.hasBiome(Biome.BAMBOO_JUNGLE) ||
                world.hasBiome(Biome.JUNGLE) ||
                world.hasBiome(Biome.SPARSE_JUNGLE);
    }

    //Returns true if acacia trees generate in this world
    public static boolean hasAcacia(GameWorld world) {
        return world.hasBiome(Biome.SAVANNA) ||
                world.hasBiome(Biome.SAVANNA_PLATEAU);
    }

    //Returns true if cherry trees generate in this world
    public static boolean hasCherry(GameWorld world) {
        return world.hasBiome(Biome.CHERRY_GROVE);
    }

    //Returns true if dark oak trees generate in this world
    public static boolean hasDarkOak(GameWorld world) {
        return world.hasBiome(Biome.DARK_FOREST);
    }

    //Returns true if mangrove trees generate in this world
    public static boolean hasMangrove(GameWorld world) {
        return world.hasBiome(Biome.MANGROVE_SWAMP);
    }

    //Returns true if bamboo generates in this world
    public static boolean hasBamboo(GameWorld world) {
        return world.hasBiome(Biome.BAMBOO_JUNGLE);
    }

    //Returns true if suspicious sand can be generated in this world
    public static boolean hasSuspiciousSand(GameWorld world) {
        return world.hasStructure(Structure.DESERT_PYRAMID) ||
                world.hasStructure(Structure.OCEAN_RUIN_WARM);
    }

    //Returns true if suspicious gravel can be generated in this world
    public static boolean hasSuspiciousGravel(GameWorld world) {
        return world.hasStructure(Structure.OCEAN_RUIN_COLD);
    }

    //Returns true if red sand generates in this world
    public static boolean hasRedSand(GameWorld world) {
        return world.hasBiome(Biome.BADLANDS) ||
                world.hasBiome(Biome.WOODED_BADLANDS) ||
                world.hasBiome(Biome.ERODED_BADLANDS);
    }

    //Returns true if emeralds can naturally generate in this world
    public static boolean hasNaturalEmerald(GameWorld world) {
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
    public static boolean hasTrialChamber(GameWorld world) {
        return world.hasStructure(Structure.TRIAL_CHAMBERS);
    }

    //Returns true if bees can be reliably found in this world
    public static boolean hasBees(GameWorld world) {
        return world.hasBiome(Biome.MEADOW) ||
                world.hasBiome(Biome.PLAINS) ||
                world.hasBiome(Biome.SUNFLOWER_PLAINS) ||
                world.hasBiome(Biome.MANGROVE_SWAMP) ||
                world.hasBiome(Biome.FLOWER_FOREST) ||
                world.hasBiome(Biome.CHERRY_GROVE);
    }

    //Returns true if an ocean monument generated in this world
    public static boolean hasOceanMonument(GameWorld world) {
        return world.hasStructure(Structure.MONUMENT);
    }

    //Returns true if dead bushes generated in this world
    public static boolean hasDeadBush(GameWorld world) {
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
    public static boolean hasSeagrass(GameWorld world) {
        return world.hasBiome(Biome.OCEAN) ||
                world.hasBiome(Biome.COLD_OCEAN) ||
                world.hasBiome(Biome.DEEP_COLD_OCEAN) ||
                world.hasBiome(Biome.DEEP_OCEAN) ||
                world.hasBiome(Biome.DEEP_LUKEWARM_OCEAN) ||
                world.hasBiome(Biome.LUKEWARM_OCEAN) ||
                world.hasBiome(Biome.WARM_OCEAN);
    }

    //Returns true if kelp can generate in this world
    public static boolean hasKelp(GameWorld world) {
        return world.hasBiome(Biome.OCEAN) ||
                world.hasBiome(Biome.COLD_OCEAN) ||
                world.hasBiome(Biome.DEEP_COLD_OCEAN) ||
                world.hasBiome(Biome.DEEP_OCEAN) ||
                world.hasBiome(Biome.DEEP_LUKEWARM_OCEAN) ||
                world.hasBiome(Biome.LUKEWARM_OCEAN);
    }

    //Returns true if coral can generate in this world
    public static boolean hasCoral(GameWorld world) {
        return world.hasBiome(Biome.WARM_OCEAN);
    }

    //Returns true if blue orchids can generate in this world
    public static boolean hasBlueOrchid(GameWorld world) {
        return world.hasBiome(Biome.SWAMP);
    }

    //Returns true if allium can generate in this world
    public static boolean hasAllium(GameWorld world) {
        return world.hasBiome(Biome.FLOWER_FOREST) ||
                world.hasBiome(Biome.MEADOW);
    }

    //Returns true if azure bluet can generate in this world
    public static boolean hasAzureBluet(GameWorld world) {
        return world.hasBiome(Biome.FLOWER_FOREST) ||
                world.hasBiome(Biome.MEADOW) ||
                world.hasBiome(Biome.PLAINS) ||
                world.hasBiome(Biome.SUNFLOWER_PLAINS);
    }

    //Returns true of tulips can generate in this world
    public static boolean hasTulip(GameWorld world) {
        return world.hasBiome(Biome.FLOWER_FOREST) ||
                world.hasBiome(Biome.PLAINS) ||
                world.hasBiome(Biome.SUNFLOWER_PLAINS);
    }

    public static boolean hasOxeyeDaisy(GameWorld world) {
        return world.hasBiome(Biome.FLOWER_FOREST) ||
                world.hasBiome(Biome.MEADOW) ||
                world.hasBiome(Biome.PLAINS) ||
                world.hasBiome(Biome.SUNFLOWER_PLAINS);
    }

    public static boolean hasCornflower(GameWorld world) {
        return world.hasBiome(Biome.FLOWER_FOREST) ||
                world.hasBiome(Biome.MEADOW) ||
                world.hasBiome(Biome.PLAINS) ||
                world.hasBiome(Biome.SUNFLOWER_PLAINS);
    }

    public static boolean hasLilyOfTheValley(GameWorld world) {
        return world.hasBiome(Biome.FOREST) ||
                world.hasBiome(Biome.BIRCH_FOREST) ||
                world.hasBiome(Biome.OLD_GROWTH_BIRCH_FOREST) ||
                world.hasBiome(Biome.DARK_FOREST) ||
                world.hasBiome(Biome.FLOWER_FOREST);
    }

    public static boolean hasBrown(GameWorld world) {
        return world.hasBiome(Biome.JUNGLE) ||
                world.hasBiome(Biome.BAMBOO_JUNGLE) ||
                world.hasBiome(Biome.SPARSE_JUNGLE);
    }

    public static boolean hasGreen(GameWorld world) {
        return world.hasBiome(Biome.DESERT) ||
                world.hasBiome(Biome.BADLANDS);
    }

    public static boolean hasCyan(GameWorld world) {
        return world.hasStructure(Structure.OCEAN_RUIN_WARM) ||
                hasGreen(world);
    }

    public static boolean hasLime(GameWorld world) {
        return hasCoral(world) || hasGreen(world);
    }

    public static boolean hasBrownShulker(GameWorld world) {
        return hasBrown(world) && hasEndAccess(world);
    }

    public static boolean hasGreenShulker(GameWorld world) {
        return hasGreen(world) && hasEndAccess(world);
    }

    public static boolean hasCyanShulker(GameWorld world) {
        return hasCyan(world) && hasEndAccess(world);
    }

    public static boolean hasLimeShulker(GameWorld world) {
        return hasLime(world) && hasEndAccess(world);
    }

    public static boolean hasBrownCandle(GameWorld world) {
        return hasBrown(world) && hasBees(world);
    }

    public static boolean hasGreenCandle(GameWorld world) {
        return hasGreen(world) && hasBees(world);
    }

    public static boolean hasCyanCandle(GameWorld world) {
        return hasCyan(world) && hasBees(world);
    }

    public static boolean hasLimeCandle(GameWorld world) {
        return hasLime(world) && hasBees(world);
    }

    public static boolean hasFortress(GameWorld world) {
        return world.hasStructure(Structure.FORTRESS);
    }

    public static boolean hasSniffer(GameWorld world) {
        return world.hasStructure(Structure.OCEAN_RUIN_WARM);
    }

    public static boolean hasMossCarpet(GameWorld world) {
        return world.hasBiome(Biome.LUSH_CAVES) ||
                world.hasBiome(Biome.MANGROVE_SWAMP);
    }

    public static boolean hasEndAccess(GameWorld world) {
        return world.hasStructure(Structure.FORTRESS) &&
                world.hasStructure(Structure.STRONGHOLD);
    }

    public static boolean hasSpawner(GameWorld world) {
        return world.hasStructure(Structure.MINESHAFT) ||
                world.hasStructure(Structure.STRONGHOLD) ||
                world.hasStructure(Structure.FORTRESS);
    }

    public static boolean hasSnow(GameWorld world) {
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

    public static boolean hasIce(GameWorld world) {
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

    public static boolean hasSoulValley(GameWorld world) {
        return world.hasBiome(Biome.SOUL_SAND_VALLEY);
    }

    public static boolean hasBasalt(GameWorld world) {
        return world.hasBiome(Biome.BASALT_DELTAS);
    }

    public static boolean hasBlockInfestation(GameWorld world) {
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

    public static boolean hasStronghold(GameWorld world) {
        return world.hasStructure(Structure.STRONGHOLD);
    }

    public static boolean hasVines(GameWorld world) {
        return world.hasBiome(Biome.JUNGLE) ||
                world.hasBiome(Biome.SPARSE_JUNGLE) ||
                world.hasBiome(Biome.BAMBOO_JUNGLE) ||
                world.hasBiome(Biome.LUSH_CAVES) ||
                world.hasBiome(Biome.SWAMP) ||
                world.hasBiome(Biome.LUSH_CAVES);
    }

    public static boolean hasMossyBrick(GameWorld world) {
        return hasStronghold(world) || hasVines(world) || world.hasBiome(Biome.LUSH_CAVES);
    }

    public static boolean hasAncientCity(GameWorld world) {
        return world.hasStructure(Structure.ANCIENT_CITY);
    }

    public static boolean hasMushroomBiome(GameWorld world) {
        return world.hasBiome(Biome.MUSHROOM_FIELDS);
    }

    public static boolean hasSwamp(GameWorld world) {
        return world.hasBiome(Biome.SWAMP);
    }

    public static boolean hasDeepDark(GameWorld world) {
        return world.hasBiome(Biome.DEEP_DARK);
    }

    public static boolean hasEmerald(GameWorld world) {
        return hasNaturalEmerald(world) ||
                world.hasStructure(Structure.VILLAGE_DESERT) ||
                world.hasStructure(Structure.VILLAGE_PLAINS) ||
                world.hasStructure(Structure.VILLAGE_SAVANNA) ||
                world.hasStructure(Structure.VILLAGE_SNOWY) ||
                world.hasStructure(Structure.VILLAGE_TAIGA);
    }

    public static boolean hasMossyCobble(GameWorld world) {
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

    public static boolean hasSunflower(GameWorld world) {
        return world.hasBiome(Biome.SUNFLOWER_PLAINS);
    }

    public static boolean hasLilac(GameWorld world) {
        return world.hasBiome(Biome.FOREST) ||
                world.hasBiome(Biome.FLOWER_FOREST) ||
                world.hasBiome(Biome.BIRCH_FOREST) ||
                world.hasBiome(Biome.OLD_GROWTH_BIRCH_FOREST) ||
                world.hasBiome(Biome.DARK_FOREST);
    }

    public static boolean hasTallGrass(GameWorld world) {
        return world.hasBiome(Biome.PLAINS) ||
                world.hasBiome(Biome.SAVANNA) ||
                world.hasBiome(Biome.CHERRY_GROVE) ||
                world.hasBiome(Biome.MEADOW) ||
                world.hasBiome(Biome.LUSH_CAVES);
    }

    public static boolean hasLargeFern(GameWorld world) {
        return world.hasBiome(Biome.TAIGA) ||
                world.hasBiome(Biome.SNOWY_TAIGA) ||
                world.hasBiome(Biome.OLD_GROWTH_SPRUCE_TAIGA) ||
                world.hasBiome(Biome.OLD_GROWTH_PINE_TAIGA);
    }

    public static boolean hasTurtle(GameWorld world) {
        return world.hasBiome(Biome.BEACH);
    }

    public static boolean hasTurtleBreeding(GameWorld world) {
        return hasTurtle(world) && hasSeagrass(world);
    }

    public static boolean hasHeartOfTheSea(GameWorld world) {
        return world.hasStructure(Structure.BURIED_TREASURE);
    }

    public static boolean hasArmadillo(GameWorld world) {
        return world.hasBiome(Biome.BADLANDS) ||
                world.hasBiome(Biome.ERODED_BADLANDS) ||
                world.hasBiome(Biome.WOODED_BADLANDS) ||
                world.hasBiome(Biome.SAVANNA) ||
                world.hasBiome(Biome.SAVANNA_PLATEAU);
    }

    public static boolean hasPoisonPotato(GameWorld world) {
        return world.hasStructure(Structure.SHIPWRECK);
    }

    public static boolean inaccessible(GameWorld world) {
        return false;
    }

    public static boolean unobtainable(GameWorld world) {
        return false;
    }

}

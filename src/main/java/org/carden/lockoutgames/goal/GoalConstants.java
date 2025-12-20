package org.carden.lockoutgames.goal;

import org.bukkit.Material;

import java.util.Map;

public class GoalConstants {


    public static int SMALL_DRIPLEAF_MIN = 1;
    public static int SMALL_DRIPLEAF_MAX = 8;
    public static int BIG_DRIPLEAF_MIN = 1;
    public static int BIG_DRIPLEAF_MAX = 8;
    public static int MOSS_CARPET_MIN = 1;
    public static int MOSS_CARPET_MAX = 64;
    public static int MOSS_BLOCK_MIN = 1;
    public static int MOSS_BLOCK_MAX = 64;
    public static int AZALEA_MIN = 1;
    public static int AZALEA_MAX = 32;
    public static int FLOWERING_AZALEA_MIN = 1;
    public static int FLOWERING_AZALEA_MAX = 32;
    public static int AZALEA_LEAVES_MIN = 1;
    public static int AZALEA_LEAVES_MAX = 32;
    public static int FLOWERING_AZALEA_LEAVES_MIN = 1;
    public static int FLOWERING_AZALEA_LEAVES_MAX = 32;
    public static int ROOTED_DIRT_MIN = 1;
    public static int ROOTED_DIRT_MAX = 32;
    public static int HANGING_ROOTS_MIN = 1;
    public static int HANGING_ROOTS_MAX = 16;
    public static int SPORE_BLOSSOM_MIN = 1;
    public static int SPORE_BLOSSOM_MAX = 12;
    public static final Map<Material, MinMaxPair> LUSH_BLOCKS = Map.ofEntries(
            Map.entry(Material.SMALL_DRIPLEAF, new MinMaxPair(SMALL_DRIPLEAF_MIN, SMALL_DRIPLEAF_MAX)),
            Map.entry(Material.BIG_DRIPLEAF, new MinMaxPair(BIG_DRIPLEAF_MIN, BIG_DRIPLEAF_MAX)),
            Map.entry(Material.MOSS_CARPET, new MinMaxPair(MOSS_CARPET_MIN, MOSS_CARPET_MAX)),
            Map.entry(Material.MOSS_BLOCK, new MinMaxPair(MOSS_BLOCK_MIN, MOSS_BLOCK_MAX)),
            Map.entry(Material.AZALEA, new MinMaxPair(AZALEA_MIN, AZALEA_MAX)),
            Map.entry(Material.FLOWERING_AZALEA, new MinMaxPair(FLOWERING_AZALEA_MIN, FLOWERING_AZALEA_MAX)),
            Map.entry(Material.AZALEA_LEAVES, new MinMaxPair(AZALEA_LEAVES_MIN, AZALEA_LEAVES_MAX)),
            Map.entry(Material.FLOWERING_AZALEA_LEAVES, new MinMaxPair(FLOWERING_AZALEA_LEAVES_MIN, FLOWERING_AZALEA_LEAVES_MAX)),
            Map.entry(Material.ROOTED_DIRT, new MinMaxPair(ROOTED_DIRT_MIN, ROOTED_DIRT_MAX)),
            Map.entry(Material.HANGING_ROOTS, new MinMaxPair(HANGING_ROOTS_MIN, HANGING_ROOTS_MAX)),
            Map.entry(Material.SPORE_BLOSSOM, new MinMaxPair(SPORE_BLOSSOM_MIN, SPORE_BLOSSOM_MAX))
    );


    public static int STONE_MIN = 16;
    public static int STONE_MAX = 128;
    public static int GRANITE_MIN = 32;
    public static int GRANITE_MAX = 64;
    public static int ANDESITE_MIN = 32;
    public static int ANDESITE_MAX = 64;
    public static int DIORITE_MIN = 32;
    public static int DIORITE_MAX = 64;
    public static int GRAVEL_MIN = 16;
    public static int GRAVEL_MAX = 64;
    public static int DIRT_MIN = 64;
    public static int DIRT_MAX = 192;
    public static int DEEPSLATE_MIN = 24;
    public static int DEEPSLATE_MAX = 128;
    public static int TUFF_MIN = 24;
    public static int TUFF_MAX = 96;
    public static int CLAY_MIN = 8;
    public static int CLAY_MAX = 32;
    public static int DRIPSTONE_BLOCK_MIN = 1;
    public static int DRIPSTONE_BLOCK_MAX = 32;
    public static int POINTED_DRIPSTONE_MIN = 1;
    public static int POINTED_DRIPSTONE_MAX = 32;
    public static int SCULK_MIN = 1;
    public static int SCULK_MAX = 32;
    public static final Map<Material, MinMaxPair> CAVE_BLOCKS = Map.ofEntries(
            Map.entry(Material.STONE, new MinMaxPair(STONE_MIN, STONE_MAX)),
            Map.entry(Material.GRANITE, new MinMaxPair(GRANITE_MIN, GRANITE_MAX)),
            Map.entry(Material.ANDESITE, new MinMaxPair(ANDESITE_MIN, ANDESITE_MAX)),
            Map.entry(Material.DIORITE, new MinMaxPair(DIORITE_MIN, DIORITE_MAX)),
            Map.entry(Material.GRAVEL, new MinMaxPair(GRAVEL_MIN, GRAVEL_MAX)),
            Map.entry(Material.MOSS_BLOCK, new MinMaxPair(MOSS_BLOCK_MIN, MOSS_BLOCK_MAX)),
            Map.entry(Material.MOSS_CARPET, new MinMaxPair(MOSS_CARPET_MIN, MOSS_CARPET_MAX)),
            Map.entry(Material.DIRT, new MinMaxPair(DIRT_MIN, DIRT_MAX)),
            Map.entry(Material.DEEPSLATE, new MinMaxPair(DEEPSLATE_MIN, DEEPSLATE_MAX)),
            Map.entry(Material.TUFF, new MinMaxPair(TUFF_MIN, TUFF_MAX)),
            Map.entry(Material.CLAY, new MinMaxPair(CLAY_MIN, CLAY_MAX)),
            Map.entry(Material.DRIPSTONE_BLOCK, new MinMaxPair(DRIPSTONE_BLOCK_MIN, DRIPSTONE_BLOCK_MAX)),
            Map.entry(Material.POINTED_DRIPSTONE, new MinMaxPair(POINTED_DRIPSTONE_MIN, POINTED_DRIPSTONE_MAX)),
            Map.entry(Material.SCULK, new MinMaxPair(SCULK_MIN, SCULK_MAX))
    );

    public static final Map<Material, GoalDifficulty> CAVE_BLOCK_DIFFICULTY_MAP = Map.ofEntries(
            Map.entry(Material.STONE, GoalDifficulty.EASY),
            Map.entry(Material.GRANITE, GoalDifficulty.EASY),
            Map.entry(Material.ANDESITE, GoalDifficulty.EASY),
            Map.entry(Material.DIORITE, GoalDifficulty.EASY),
            Map.entry(Material.GRAVEL, GoalDifficulty.EASY),
            Map.entry(Material.MOSS_BLOCK, GoalDifficulty.MEDIUM),
            Map.entry(Material.MOSS_CARPET, GoalDifficulty.MEDIUM),
            Map.entry(Material.DIRT, GoalDifficulty.VERY_EASY),
            Map.entry(Material.DEEPSLATE, GoalDifficulty.EASY),
            Map.entry(Material.TUFF, GoalDifficulty.EASY),
            Map.entry(Material.CLAY, GoalDifficulty.VERY_EASY),
            Map.entry(Material.DRIPSTONE_BLOCK, GoalDifficulty.MEDIUM),
            Map.entry(Material.POINTED_DRIPSTONE, GoalDifficulty.MEDIUM),
            Map.entry(Material.SCULK, GoalDifficulty.MEDIUM)
    );

    protected static final GoalDifficulty DEFAULT_DIFFICULTY = GoalDifficulty.MEDIUM;

    public static class MinMaxPair {

        int min;
        int max;

        private MinMaxPair(){}

        private MinMaxPair(int min, int max) {
            this.min = Math.max(min, 0);
            this.max = Math.max(this.min, max);
        }

        public int min() {
            return this.min;
        }

        public int max() {
            return this.max;
        }

    }

}

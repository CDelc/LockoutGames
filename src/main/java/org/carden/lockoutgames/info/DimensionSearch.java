package org.carden.lockoutgames.info;

import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.generator.structure.Structure;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public enum DimensionSearch {

    NORMAL(World.Environment.NORMAL, new Structure[]{
            Structure.BURIED_TREASURE,
            Structure.DESERT_PYRAMID,
            Structure.IGLOO,
            Structure.ANCIENT_CITY,
            Structure.MINESHAFT,
            Structure.MINESHAFT_MESA,
            Structure.JUNGLE_PYRAMID,
            Structure.MONUMENT,
            Structure.RUINED_PORTAL,
            Structure.SHIPWRECK,
            Structure.STRONGHOLD,
            Structure.SWAMP_HUT,
            Structure.OCEAN_RUIN_COLD,
            Structure.OCEAN_RUIN_WARM,
            Structure.PILLAGER_OUTPOST,
            Structure.RUINED_PORTAL,
            Structure.RUINED_PORTAL_DESERT,
            Structure.RUINED_PORTAL_JUNGLE,
            Structure.RUINED_PORTAL_MOUNTAIN,
            Structure.RUINED_PORTAL_OCEAN,
            Structure.RUINED_PORTAL_SWAMP,
            Structure.SHIPWRECK_BEACHED,
            Structure.SWAMP_HUT,
            Structure.TRAIL_RUINS,
            Structure.TRIAL_CHAMBERS,
            Structure.VILLAGE_DESERT,
            Structure.VILLAGE_PLAINS,
            Structure.VILLAGE_SAVANNA,
            Structure.VILLAGE_SNOWY,
            Structure.VILLAGE_TAIGA,
            Structure.MANSION
    }, new Biome[]{
            Biome.OCEAN,
            Biome.PLAINS,
            Biome.DESERT,
            Biome.WINDSWEPT_HILLS,
            Biome.FOREST,
            Biome.TAIGA,
            Biome.SWAMP,
            Biome.MANGROVE_SWAMP,
            Biome.RIVER,
            Biome.FROZEN_OCEAN,
            Biome.FROZEN_RIVER,
            Biome.SNOWY_PLAINS,
            Biome.MUSHROOM_FIELDS,
            Biome.BEACH,
            Biome.JUNGLE,
            Biome.SPARSE_JUNGLE,
            Biome.DEEP_OCEAN,
            Biome.STONY_SHORE,
            Biome.SNOWY_BEACH,
            Biome.BIRCH_FOREST,
            Biome.DARK_FOREST,
            Biome.SNOWY_TAIGA,
            Biome.OLD_GROWTH_PINE_TAIGA,
            Biome.WINDSWEPT_FOREST,
            Biome.SAVANNA,
            Biome.SAVANNA_PLATEAU,
            Biome.BADLANDS,
            Biome.WOODED_BADLANDS,
            Biome.SMALL_END_ISLANDS,
            Biome.WARM_OCEAN,
            Biome.LUKEWARM_OCEAN,
            Biome.COLD_OCEAN,
            Biome.DEEP_LUKEWARM_OCEAN,
            Biome.DEEP_COLD_OCEAN,
            Biome.DEEP_FROZEN_OCEAN,
            Biome.SUNFLOWER_PLAINS,
            Biome.WINDSWEPT_GRAVELLY_HILLS,
            Biome.FLOWER_FOREST,
            Biome.ICE_SPIKES,
            Biome.OLD_GROWTH_BIRCH_FOREST,
            Biome.OLD_GROWTH_SPRUCE_TAIGA,
            Biome.WINDSWEPT_SAVANNA,
            Biome.ERODED_BADLANDS,
            Biome.BAMBOO_JUNGLE,
            Biome.DRIPSTONE_CAVES,
            Biome.LUSH_CAVES,
            Biome.DEEP_DARK,
            Biome.MEADOW,
            Biome.GROVE,
            Biome.SNOWY_SLOPES,
            Biome.FROZEN_PEAKS,
            Biome.JAGGED_PEAKS,
            Biome.STONY_PEAKS,
            Biome.CHERRY_GROVE
    }), NETHER(World.Environment.NETHER, new Structure[]
            {Structure.FORTRESS,
            Structure.NETHER_FOSSIL,
            Structure.RUINED_PORTAL,
            Structure.RUINED_PORTAL_NETHER,
            Structure.BASTION_REMNANT
    }, new Biome[]{
            Biome.NETHER_WASTES,
            Biome.BASALT_DELTAS,
            Biome.SOUL_SAND_VALLEY,
            Biome.CRIMSON_FOREST,
            Biome.WARPED_FOREST
    }), THE_END(World.Environment.THE_END, new Structure[]{Structure.END_CITY}, new Biome[]{
            Biome.END_BARRENS,
            Biome.END_HIGHLANDS,
            Biome.END_MIDLANDS,
            Biome.THE_END,
            Biome.SMALL_END_ISLANDS});

    private final World.Environment environment;
    private final HashSet<Structure> structures;
    private final HashSet<Biome> biomes;

    DimensionSearch(World.Environment environment, Structure[] structures, Biome[] biomes) {
        this.environment = environment;
        this.structures = new HashSet<>(Arrays.asList(structures));
        this.biomes = new HashSet<>(Arrays.asList(biomes));
    }

    public World.Environment getEnvironment() {
        return environment;
    }

    public HashSet<Structure> getStructures() {
        return structures;
    }

    public HashSet<Biome> getBiomes() {
        return biomes;
    }
}

package org.carden.lockoutgames.info;

import org.bukkit.World;
import org.bukkit.generator.structure.Structure;
import org.bukkit.generator.structure.StructureType;

import java.util.ArrayList;

import java.util.Arrays;

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
            Structure.MANSION}),
    NETHER(World.Environment.NETHER, new Structure[]{
            Structure.FORTRESS,
            Structure.NETHER_FOSSIL,
            Structure.RUINED_PORTAL,
            Structure.RUINED_PORTAL_NETHER,
            Structure.BASTION_REMNANT
    }),
    THE_END(World.Environment.THE_END,new Structure[]{Structure.END_CITY});

    private final World.Environment environment;
    private final ArrayList<Structure> structures;

    DimensionSearch(World.Environment environment, Structure[] structures) {
        this.environment = environment;
        this.structures = new ArrayList<Structure>(Arrays.asList(structures));
    }

    public World.Environment getEnvironment() {
        return environment;
    }

    public ArrayList<Structure> getStructures() {
        return structures;
    }
}

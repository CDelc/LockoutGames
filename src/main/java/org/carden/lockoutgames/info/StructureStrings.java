package org.carden.lockoutgames.info;

import org.bukkit.generator.structure.Structure;

import java.util.HashMap;
import java.util.Map;

public class StructureStrings {

    private static final Map<Structure, String> STRUCTURE_TYPE_NAMES = new HashMap<>();

    static {
        STRUCTURE_TYPE_NAMES.put(Structure.PILLAGER_OUTPOST, "pillager_outpost");
        STRUCTURE_TYPE_NAMES.put(Structure.MINESHAFT, "mineshaft");
        STRUCTURE_TYPE_NAMES.put(Structure.MINESHAFT_MESA, "mineshaft_mesa");
        STRUCTURE_TYPE_NAMES.put(Structure.MANSION, "mansion");
        STRUCTURE_TYPE_NAMES.put(Structure.JUNGLE_PYRAMID, "jungle_pyramid");
        STRUCTURE_TYPE_NAMES.put(Structure.DESERT_PYRAMID, "desert_pyramid");
        STRUCTURE_TYPE_NAMES.put(Structure.IGLOO, "igloo");
        STRUCTURE_TYPE_NAMES.put(Structure.SHIPWRECK, "shipwreck");
        STRUCTURE_TYPE_NAMES.put(Structure.SHIPWRECK_BEACHED, "shipwreck_beached");
        STRUCTURE_TYPE_NAMES.put(Structure.SWAMP_HUT, "swamp_hut");
        STRUCTURE_TYPE_NAMES.put(Structure.STRONGHOLD, "stronghold");
        STRUCTURE_TYPE_NAMES.put(Structure.MONUMENT, "monument");
        STRUCTURE_TYPE_NAMES.put(Structure.OCEAN_RUIN_COLD, "ocean_ruin_cold");
        STRUCTURE_TYPE_NAMES.put(Structure.OCEAN_RUIN_WARM, "ocean_ruin_warm");
        STRUCTURE_TYPE_NAMES.put(Structure.FORTRESS, "fortress");
        STRUCTURE_TYPE_NAMES.put(Structure.NETHER_FOSSIL, "nether_fossil");
        STRUCTURE_TYPE_NAMES.put(Structure.END_CITY, "end_city");
        STRUCTURE_TYPE_NAMES.put(Structure.BURIED_TREASURE, "buried_treasure");
        STRUCTURE_TYPE_NAMES.put(Structure.BASTION_REMNANT, "bastion_remnant");
        STRUCTURE_TYPE_NAMES.put(Structure.VILLAGE_PLAINS, "village_plains");
        STRUCTURE_TYPE_NAMES.put(Structure.VILLAGE_DESERT, "village_desert");
        STRUCTURE_TYPE_NAMES.put(Structure.VILLAGE_SAVANNA, "village_savanna");
        STRUCTURE_TYPE_NAMES.put(Structure.VILLAGE_SNOWY, "village_snowy");
        STRUCTURE_TYPE_NAMES.put(Structure.VILLAGE_TAIGA, "village_taiga");
        STRUCTURE_TYPE_NAMES.put(Structure.RUINED_PORTAL, "ruined_portal");
        STRUCTURE_TYPE_NAMES.put(Structure.RUINED_PORTAL_DESERT, "ruined_portal_desert");
        STRUCTURE_TYPE_NAMES.put(Structure.RUINED_PORTAL_JUNGLE, "ruined_portal_jungle");
        STRUCTURE_TYPE_NAMES.put(Structure.RUINED_PORTAL_SWAMP, "ruined_portal_swamp");
        STRUCTURE_TYPE_NAMES.put(Structure.RUINED_PORTAL_MOUNTAIN, "ruined_portal_mountain");
        STRUCTURE_TYPE_NAMES.put(Structure.RUINED_PORTAL_OCEAN, "ruined_portal_ocean");
        STRUCTURE_TYPE_NAMES.put(Structure.RUINED_PORTAL_NETHER, "ruined_portal_nether");
        STRUCTURE_TYPE_NAMES.put(Structure.ANCIENT_CITY, "ancient_city");
        STRUCTURE_TYPE_NAMES.put(Structure.TRAIL_RUINS, "trail_ruins");
        STRUCTURE_TYPE_NAMES.put(Structure.TRIAL_CHAMBERS, "trial_chambers");
    }

    public static String getStructureName(Structure structure) {
        return STRUCTURE_TYPE_NAMES.getOrDefault(structure, "Unknown Structure");
    }

}

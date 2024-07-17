package org.carden.lockoutgames.game;

import com.onarandombox.MultiverseCore.api.MVWorldManager;
import com.onarandombox.MultiverseCore.api.MultiverseWorld;
import org.bukkit.Location;
import org.bukkit.PortalType;
import org.bukkit.World;
import org.bukkit.WorldType;
import org.bukkit.block.Biome;
import org.bukkit.generator.structure.Structure;
import org.carden.lockoutgames.LockoutGames;
import org.carden.lockoutgames.info.DimensionSearch;
import org.carden.lockoutgames.info.WorldRequirements;

import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;


public class GameWorld {
    /**
     * This will calculate and store information about the contents of the world the game is taking place in
     */

    LockoutGames plugin;
    MVWorldManager worldManager;
    MultiverseWorld overworld;
    MultiverseWorld nether;
    MultiverseWorld end;

    boolean consistentDimensionSeed;
    boolean amplified;
    int worldSize;

    Set<Biome> availableBiomes = new HashSet<>();
    Set<Structure> availableStructures = new HashSet<>();

    Map<World.Environment, World> cbWorlds;

    public GameWorld(LockoutGames plugin) {

        this.plugin = plugin;
        worldManager = plugin.getMultiverseCore().getMVWorldManager();
        consistentDimensionSeed = true;
        amplified = false;
        worldSize = 10000;

        WorldRequirements.Element.setWorld(this);

        regenerate();

    }

    public void setConsistentDimensionSeed(boolean value) {
        consistentDimensionSeed = value;
        regenerate();
    }

    protected void setWorldSize(int value) {
        worldSize = value;
        overworld.getCBWorld().getWorldBorder().setSize(worldSize);
        nether.getCBWorld().getWorldBorder().setSize(worldSize);
    }

    public boolean hasStructure(Structure t) {
        return availableStructures.contains(t);
    }

    public boolean hasBiome(Biome b) {
        return availableBiomes.contains(b);
    }

    public World getWorld(World.Environment type) {
        return cbWorlds.getOrDefault(type, overworld.getCBWorld());
    }

    private void generateWorld() {
        long seed = (new Random()).nextLong();

        worldManager.addWorld(
                "lockoutOverworld",
                World.Environment.NORMAL,
                consistentDimensionSeed ? String.valueOf(seed) : null,
                amplified ? WorldType.AMPLIFIED : WorldType.NORMAL,
                true, null);
        worldManager.addWorld(
                "lockoutNether",
                World.Environment.NETHER,
                consistentDimensionSeed ? String.valueOf(seed) : null,
                WorldType.NORMAL,
                true, null);
        worldManager.addWorld(
                "lockoutEnd",
                World.Environment.THE_END,
                consistentDimensionSeed ? String.valueOf(seed) : null,
                WorldType.NORMAL,
                true, null);

        overworld = worldManager.getMVWorld("lockoutOverworld");
        nether = worldManager.getMVWorld("lockoutNether");
        end = worldManager.getMVWorld("lockoutEnd");

        plugin.getMvnetherPortals().addWorldLink(overworld.getAlias(), nether.getAlias(), PortalType.NETHER);
        plugin.getMvnetherPortals().addWorldLink(nether.getAlias(), overworld.getAlias(), PortalType.NETHER);

        plugin.getMvnetherPortals().addWorldLink(overworld.getAlias(), end.getAlias(), PortalType.ENDER);
        plugin.getMvnetherPortals().addWorldLink(end.getAlias(), overworld.getAlias(), PortalType.ENDER);

        this.cbWorlds.clear();
        cbWorlds.put(World.Environment.NORMAL, overworld.getCBWorld());
        cbWorlds.put(World.Environment.NETHER, nether.getCBWorld());
        cbWorlds.put(World.Environment.THE_END, end.getCBWorld());

        overworld.getCBWorld().getWorldBorder().setSize(worldSize);
        nether.getCBWorld().getWorldBorder().setSize(worldSize);

        scanBiomes();
        scanStructures();

    }

    public void destroy() {
        worldManager.deleteWorld(overworld.getAlias());
        worldManager.deleteWorld(nether.getAlias());
        worldManager.deleteWorld(end.getAlias());
    }

    public void regenerate() {
        destroy();
        generateWorld();
    }

    private void scanBiomes() {
        World world = getWorld(World.Environment.NORMAL);
        Location origin = new Location(world, 0, 0 , 0);
        availableBiomes.clear();

        int stepsize = 32;

        for(int x = -worldSize + 100; x < worldSize - 100; x+=stepsize) {
            for(int z = -worldSize + 100; z < worldSize - 100; z+=stepsize) {
                availableBiomes.add(world.getBiome(x, world.getHighestBlockYAt(x, z),z));
            }
        }

        //cave biomes separate cause 3d biomes
        if(world.locateNearestBiome(origin, worldSize, Biome.LUSH_CAVES) != null) availableBiomes.add(Biome.LUSH_CAVES);
        if(world.locateNearestBiome(origin, worldSize, Biome.DRIPSTONE_CAVES) != null) availableBiomes.add(Biome.DRIPSTONE_CAVES);
        if(world.locateNearestBiome(origin, worldSize, Biome.DEEP_DARK) != null) availableBiomes.add(Biome.DEEP_DARK);

        world = getWorld(World.Environment.NETHER);
        for(int x = -worldSize + 100; x < worldSize - 100; x+=stepsize) {
            for(int z = -worldSize + 100; z < worldSize - 100; z+=stepsize) {
                availableBiomes.add(world.getBiome(x, world.getHighestBlockYAt(x, z),z));
            }
        }

        availableBiomes.add(Biome.SMALL_END_ISLANDS);
        availableBiomes.add(Biome.END_BARRENS);
        availableBiomes.add(Biome.END_HIGHLANDS);
        availableBiomes.add(Biome.END_MIDLANDS);
        availableBiomes.add(Biome.THE_END);
    }

    private void scanStructures() {
        availableStructures.clear();
        for (DimensionSearch dimension : DimensionSearch.values()) {
            World world = getWorld(dimension.getEnvironment());
            Location origin = new Location(world, 0, 0, 0);
            for (Structure structure : dimension.getStructures()) {
                if (world.locateNearestStructure(origin, structure, worldSize, false) != null)
                    availableStructures.add(structure);
            }
        }
        availableStructures.add(Structure.END_CITY);
    }
}

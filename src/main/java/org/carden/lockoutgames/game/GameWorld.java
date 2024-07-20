package org.carden.lockoutgames.game;

import com.onarandombox.MultiverseCore.api.MVWorldManager;
import com.onarandombox.MultiverseCore.api.MultiverseWorld;
import com.onarandombox.MultiverseNetherPortals.MultiverseNetherPortals;
import org.bukkit.*;
import org.bukkit.block.Biome;
import org.bukkit.generator.structure.Structure;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.StructureSearchResult;
import org.carden.lockoutgames.LockoutGames;
import org.carden.lockoutgames.info.DimensionSearch;
import org.carden.lockoutgames.info.StructureStrings;
import org.carden.lockoutgames.info.WorldRequirements;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class GameWorld {
    /**
     * This will calculate and store information about the contents of the world the game is taking place in
     */


    LockoutGames plugin;
    MVWorldManager worldManager;

    boolean consistentDimensionSeed;
    boolean amplified;
    int worldSize;

    Set<Biome> availableBiomes = new HashSet<>();
    Set<Structure> availableStructures = new HashSet<>();

    MultiverseWorld waitingRoom;

    public GameWorld(LockoutGames plugin) {

        this.plugin = plugin;
        worldManager = plugin.getMultiverseCore().getMVWorldManager();
        consistentDimensionSeed = true;
        amplified = false;
        worldSize = 10000;

        WorldRequirements.Element.setWorld(this);

    }

    protected void setWorldSize(int value) {
        worldSize = value;
        World overworld = getWorld(World.Environment.NORMAL);
        World nether = getWorld(World.Environment.NETHER);
        if(overworld != null) overworld.getWorldBorder().setSize(worldSize);
        if(nether != null) nether.getWorldBorder().setSize(worldSize);
    }

    public boolean hasStructure(Structure t) {
        return availableStructures.contains(t);
    }

    public boolean hasBiome(Biome b) {
        return availableBiomes.contains(b);
    }

    public World getWorld(World.Environment type) {
        try{
            switch(type) {
                case NORMAL:
                    return worldManager.getMVWorld(WorldNames.OVERWORLD.name).getCBWorld();
                case NETHER:
                    return worldManager.getMVWorld(WorldNames.NETHER.name).getCBWorld();
                case THE_END:
                    return worldManager.getMVWorld(WorldNames.END.name).getCBWorld();
            }
        }catch(NullPointerException e){
            return null;
        }
        return worldManager.getMVWorld(WorldNames.OVERWORLD.name).getCBWorld();
    }

    private void linkSMPWorlds(String overworld, String netherworld, String endworld) {
        MultiverseNetherPortals netherPortals = plugin.getMvnetherPortals();
        netherPortals.addWorldLink(overworld, netherworld, PortalType.NETHER);
        netherPortals.addWorldLink(netherworld, overworld, PortalType.NETHER);

        // Linking end portals both ways
        netherPortals.addWorldLink(overworld, endworld, PortalType.ENDER);
        netherPortals.addWorldLink(endworld, overworld, PortalType.ENDER);
    }

    public void generateWorld() {
        long seed = (new Random()).nextLong();

        this.waitingRoom = worldManager.getMVWorld(WorldNames.WORLD.name);

        try{
            plugin.getServer().broadcastMessage("Moving players to waiting room...");
            worldManager.getMVWorld(WorldNames.OVERWORLD.name).getCBWorld().getPlayers().forEach(p -> p.teleport(waitingRoom.getCBWorld().getSpawnLocation()));
            worldManager.getMVWorld(WorldNames.NETHER.name).getCBWorld().getPlayers().forEach(p -> p.teleport(waitingRoom.getCBWorld().getSpawnLocation()));
            worldManager.getMVWorld(WorldNames.END.name).getCBWorld().getPlayers().forEach(p -> p.teleport(waitingRoom.getCBWorld().getSpawnLocation()));
        }catch(NullPointerException e){
            worldManager.addWorld(WorldNames.OVERWORLD.name, World.Environment.NORMAL, String.valueOf(seed), amplified ? WorldType.AMPLIFIED : WorldType.NORMAL, true, null);
            worldManager.addWorld(WorldNames.NETHER.name, World.Environment.NETHER, String.valueOf(seed), WorldType.NORMAL, true, null);
            worldManager.addWorld(WorldNames.END.name, World.Environment.THE_END, String.valueOf(seed), WorldType.NORMAL, true, null);
        }

        new BukkitRunnable() {
            @Override
            public void run() {
                plugin.getServer().broadcastMessage("Generating Overworld (May cause lag)...");
                worldManager.regenWorld(WorldNames.OVERWORLD.name, true, true, null);
                plugin.getServer().broadcastMessage("Generating Nether (May cause lag)...");
                worldManager.regenWorld(WorldNames.NETHER.name, true, true, null);
                plugin.getServer().broadcastMessage("Generating End (May cause lag)...");
                worldManager.regenWorld(WorldNames.END.name, true, true, null);

                getWorld(World.Environment.NORMAL).getWorldBorder().setSize(worldSize);
                getWorld(World.Environment.NETHER).getWorldBorder().setSize(worldSize);
                linkSMPWorlds(WorldNames.OVERWORLD.name, WorldNames.NETHER.name, WorldNames.END.name);

                scanBiomes();
                scanStructures();
            }
        }.runTaskLater(plugin, 40);
    }

    private void scanBiomes() {
        new BukkitRunnable() {
            private final ArrayList<Biome> allBiomes = new ArrayList<>(Arrays.asList(Biome.values()));

            @Override
            public void run() {
                for (Biome b : allBiomes) {
                    World world = getWorld(World.Environment.NORMAL);
                    if(DimensionSearch.NETHER.getBiomes().contains(b)) {
                        world = getWorld(World.Environment.NETHER);
                    }
                    Location origin = new Location(world, 0, 50, 0);
                    if (world.locateNearestBiome(origin, worldSize / 2 - 50, b) != null) {
                        availableBiomes.add(b);
                        //plugin.getServer().broadcastMessage(ChatColor.GREEN + b.name() + " found");
                    }
                    else {
                        //plugin.getServer().broadcastMessage(ChatColor.RED + b.name() + " not found");
                    }
                }
            }
        }.runTaskAsynchronously(plugin);

        availableBiomes.add(Biome.SMALL_END_ISLANDS);
        availableBiomes.add(Biome.END_BARRENS);
        availableBiomes.add(Biome.END_HIGHLANDS);
        availableBiomes.add(Biome.END_MIDLANDS);
        availableBiomes.add(Biome.THE_END);
    }

    private void scanStructures() {

        ArrayList<Structure> allStructures = new ArrayList<>(DimensionSearch.NORMAL.getStructures());
        allStructures.addAll(DimensionSearch.NETHER.getStructures());
        Lock lock = new ReentrantLock();

        new BukkitRunnable() {

            private int index = 0;
            private int structureCount = 0;
            @Override
            public void run() {
                lock.lock();
                Structure s = allStructures.get(index);
                index++;
                lock.unlock();
                World world = getWorld(DimensionSearch.NORMAL.getEnvironment());
                if(DimensionSearch.NETHER.getStructures().contains(s)) {
                    world = getWorld(DimensionSearch.NETHER.getEnvironment());
                }
                Location origin = new Location(world, 0, 50, 0);
                StructureSearchResult result = structureSearchWithTimeout(world, origin, s);;
                if(result != null && Math.abs(result.getLocation().getX()) < (double) worldSize / 2 && Math.abs(result.getLocation().getZ()) < (double) worldSize / 2){
                    plugin.getServer().broadcastMessage(ChatColor.GREEN + StructureStrings.getStructureName(s) + " found");
                    availableStructures.add(s);
                }
                else {
                    plugin.getServer().broadcastMessage(ChatColor.RED + StructureStrings.getStructureName(s) + " not found");
                }
                structureCount++;
                if(index >= allStructures.size()) {
                    if(structureCount >= allStructures.size()) plugin.getServer().broadcastMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "Structure Scan complete");
                    cancel();
                }
            }
        }.runTaskTimerAsynchronously(plugin, 0, 5);

        availableStructures.add(Structure.END_CITY);
    }

    private StructureSearchResult structureSearchWithTimeout(World world, Location origin, Structure s) {
        CompletableFuture<StructureSearchResult> future = new CompletableFuture<>();
        BukkitTask search = new BukkitRunnable() {
            @Override
            public void run() {
                StructureSearchResult result = world.locateNearestStructure(origin, s, worldSize / 2, false);
                future.complete(result);
            }
        }.runTaskAsynchronously(plugin);

        new BukkitRunnable() {
            @Override
            public void run() {
                if(!future.isDone()) {
                    future.complete(null);
                    search.cancel();
                }
            }
        }.runTaskLater(plugin, 400);
        return future.join();
    }

    public enum WorldNames {
        OVERWORLD("game"),
        NETHER("game_nether"),
        END("game_the_end"),
        WORLD("world");

        String name;

        WorldNames(String name) {
            this.name = name;
        }

    }
}

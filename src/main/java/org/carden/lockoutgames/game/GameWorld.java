package org.carden.lockoutgames.game;

import com.onarandombox.MultiverseCore.api.MVWorldManager;
import com.onarandombox.MultiverseCore.api.MultiverseWorld;
import com.onarandombox.MultiverseCore.enums.AllowedPortalType;
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

import javax.annotation.Nullable;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class GameWorld {
    /**
     * This will calculate and store information about the contents of the world the game is taking place in
     */

    static final int STRUCTURE_SEARCH_TIMEOUT_SECONDS = 20;
    static final int BIOME_SEARCH_BORDER_MARGIN_BLOCKS = 50;
    static final int LARGE_WORLD_THRESHOLD = 50000;

    LockoutGames plugin;
    MVWorldManager worldManager;

    private int worldSize;
    private final String world_name;

    private final Set<Biome> availableBiomes;
    private final Set<Structure> availableStructures;

    public GameWorld() {

        this.plugin = LockoutGames.getPluginInstance();
        this.availableBiomes = new HashSet<>();
        this.availableStructures = new HashSet<>();
        this.world_name = "world";
        worldManager = plugin.getMultiverseCore().getMVWorldManager();
        setProps(null);
    }

    /**
     *
     * @param value Sets the world border size, to be used by GameSettings
     */
    public void setWorldSize(int value) {
        worldSize = value;
        World overworld = getWorld(World.Environment.NORMAL);
        World nether = getWorld(World.Environment.NETHER);
        if(overworld != null) overworld.getWorldBorder().setSize(worldSize);
        if(nether != null) nether.getWorldBorder().setSize(worldSize);
    }

    /**
     *
     * @param t The structure to check
     * @return true if the structure exists within the world boundaries (Structure scan is required)
     */
    public boolean hasStructure(Structure t) {
        return availableStructures.contains(t);
    }

    /**
     *
     * @param b The biome to check
     * @return true if the biome exists within the world boundaries (Biome scan is required)
     */
    public boolean hasBiome(Biome b) {
        return availableBiomes.contains(b);
    }

    /**
     *
     * @param type The world dimension to check
     * @return The Bukkit instance of the game world that correspond to the given environment
     * (Default overworld) returns null if worlds have not been generated
     */
    public World getWorld(World.Environment type) {
        try{
            return switch (type) {
                case NORMAL -> worldManager.getMVWorld(world_name).getCBWorld();
                case NETHER -> worldManager.getMVWorld(world_name + "_nether").getCBWorld();
                case THE_END -> worldManager.getMVWorld(world_name + "_the_end").getCBWorld();
                default -> worldManager.getMVWorld("world").getCBWorld();
            };
        }catch(NullPointerException e){
            return null;
        }
    }

    /**
     * Creates the nether and end portal connections between dimensions
     * @param overworld Overworld world name
     * @param netherworld Nether world name
     * @param endworld End world name
     */
    private void linkSMPWorlds(String overworld, String netherworld, String endworld) {
        MultiverseNetherPortals netherPortals = plugin.getMvnetherPortals();
        netherPortals.addWorldLink(overworld, netherworld, PortalType.NETHER);
        netherPortals.addWorldLink(netherworld, overworld, PortalType.NETHER);

        // Linking end portals both ways
        netherPortals.addWorldLink(overworld, endworld, PortalType.ENDER);
        netherPortals.addWorldLink(endworld, overworld, PortalType.ENDER);
    }

    public CompletableFuture<Boolean> checkLogic() {
        CompletableFuture<Boolean> completeCheck = new CompletableFuture<>();
        CompletableFuture<Boolean> biomesComplete = scanBiomes();
        CompletableFuture<Boolean> structuresComplete = scanStructures();
        CompletableFuture.allOf(biomesComplete, structuresComplete).thenRun(() -> completeCheck.complete(true));
        return completeCheck;
    }

    public CompletableFuture<Boolean> prepareNewWorld(SettingsImage settings) {
        CompletableFuture<Boolean> isComplete = new CompletableFuture<>();
        setProps(settings);
        return isComplete;
    }

    public void setWorldSettings(SettingsImage settings) {
        setProps(settings);
        getWorld(World.Environment.NORMAL).setTime(1000);
    }

    private void setProps(@Nullable SettingsImage settings) {
        MultiverseWorld[] worlds = {
                worldManager.getMVWorld(world_name),
                worldManager.getMVWorld(world_name + "_nether"),
                worldManager.getMVWorld(world_name + "_the_end")
        };

        for(MultiverseWorld world : worlds) {
            world.setAdjustSpawn(true);
            world.setPVPMode(settings != null && settings.isPvP());
            world.setAllowAnimalSpawn(true);
            world.setAllowMonsterSpawn(true);
            world.setDifficulty(settings != null ? settings.getDifficulty() : Difficulty.EASY);
            world.allowPortalMaking(AllowedPortalType.ALL);
            world.setBedRespawn(true);
            world.setHunger(settings == null || settings.isHunger());
            world.setEnableWeather(true);
            world.setSpawnLocation(world.getCBWorld().getSpawnLocation());
        }
    }

    /**
     * Updated the availableBiomes set to contain the biomes that exist within the world boundary.
     * This scan is done asynchronously in order to not block the main server thread.
     */
    private CompletableFuture<Boolean> scanBiomes() {
        availableBiomes.clear();
        ArrayList<Biome> allBiomes = new ArrayList<>(DimensionSearch.NORMAL.getBiomes());
        allBiomes.addAll(DimensionSearch.NETHER.getBiomes());
        Lock lock = new ReentrantLock();
        CompletableFuture<Boolean> isComplete = new CompletableFuture<>();

        if(this.worldSize >= LARGE_WORLD_THRESHOLD) {
            availableBiomes.addAll(allBiomes);
            new BukkitRunnable() {
                @Override
                public void run() {
                    isComplete.complete(true);
                }
            }.runTaskLater(plugin, 10);
            return isComplete;
        }

        new BukkitRunnable() {
            private int index = 0;
            private int biomeCount = 0;
            @Override
            public void run() {
                lock.lock();
                Biome b = allBiomes.get(index);
                index++;
                if(index >= allBiomes.size()) cancel();;
                lock.unlock();
                World world = getWorld(DimensionSearch.NORMAL.getEnvironment());
                if(DimensionSearch.NETHER.getBiomes().contains(b)) {
                    world = getWorld(World.Environment.NETHER);
                }
                Location origin = new Location(world, 0, 0, 0);

                if (world.locateNearestBiome(origin, worldSize / 2 - BIOME_SEARCH_BORDER_MARGIN_BLOCKS, b) != null) {
                    availableBiomes.add(b);
                    //plugin.getServer().broadcastMessage(ChatColor.GREEN + b.name() + " found");
                }
                else {
                    plugin.getServer().broadcastMessage(ChatColor.RED + b.name() + " not found");
                }
                biomeCount++;
                if(index >= allBiomes.size()) {
                    if(biomeCount >= allBiomes.size()){
                        plugin.getServer().broadcastMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "Biome Scan complete");
                        isComplete.complete(true);
                    }
                    cancel();
                }
            }
        }.runTaskTimerAsynchronously(plugin, 0, 1);

        //End dimension has no world border, so all biomes are included.
        availableBiomes.add(Biome.SMALL_END_ISLANDS);
        availableBiomes.add(Biome.END_BARRENS);
        availableBiomes.add(Biome.END_HIGHLANDS);
        availableBiomes.add(Biome.END_MIDLANDS);
        availableBiomes.add(Biome.THE_END);
        return isComplete;
    }

    /**
     * Updated the availableStructures set to contain the structures that exist within the world boundary.
     * This scan is done asynchronously in order to not block the main server thread,
     * and all structures are searched for in parallel
     */
    private CompletableFuture<Boolean> scanStructures() {
        availableStructures.clear();
        ArrayList<Structure> allStructures = new ArrayList<>(DimensionSearch.NORMAL.getStructures());
        allStructures.addAll(DimensionSearch.NETHER.getStructures());
        Lock lock = new ReentrantLock();
        CompletableFuture<Boolean> isComplete = new CompletableFuture<>();

        if(this.worldSize >= LARGE_WORLD_THRESHOLD) {
            availableStructures.addAll(allStructures);
            new BukkitRunnable() {
                @Override
                public void run() {
                    isComplete.complete(true);
                }
            }.runTaskLater(plugin, 10);
            return isComplete;
        }

        new BukkitRunnable() {

            private int index = 0;
            private int structureCount = 0;
            @Override
            public void run() {
                lock.lock();
                Structure s = allStructures.get(index);
                index++;
                if(index >= allStructures.size()) cancel();;
                lock.unlock();
                World world = getWorld(DimensionSearch.NORMAL.getEnvironment());
                if(DimensionSearch.NETHER.getStructures().contains(s)) {
                    world = getWorld(World.Environment.NETHER);
                }
                Location origin = new Location(world, 0, 0, 0);
                StructureSearchResult result = structureSearchWithTimeout(world, origin, s);;
                if(result != null && Math.abs(result.getLocation().getX()) < (double) worldSize / 2 && Math.abs(result.getLocation().getZ()) < (double) worldSize / 2){
                    //plugin.getServer().broadcastMessage(ChatColor.GREEN + StructureStrings.getStructureName(s) + " found");
                    availableStructures.add(s);
                }
                else {
                    plugin.getServer().broadcastMessage(ChatColor.RED + StructureStrings.getStructureName(s) + " not found");
                }
                structureCount++;
                if(index >= allStructures.size()) {
                    if(structureCount >= allStructures.size()){
                        plugin.getServer().broadcastMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "Structure Scan complete");
                        isComplete.complete(true);
                    }
                    cancel();
                }
            }
        }.runTaskTimerAsynchronously(plugin, 0, 1);

        //End has no border, end city always included.
        availableStructures.add(Structure.END_CITY);
        return isComplete;
    }

    /**
     * Calls world.locateNearestStructure inside the world's radius but with a timeout
     * Structure searches do not stop until the structure is found regardless of the radius so this is necessary for speed.
     * @param world The world to search
     * @param origin The center of the search
     * @param s The structure to search for
     * @return The corresponding StructureSearchResult if the structure is found, null if it is not found or times out.
     */
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
        }.runTaskLater(plugin, 20L * STRUCTURE_SEARCH_TIMEOUT_SECONDS);
        return future.join();
    }
}

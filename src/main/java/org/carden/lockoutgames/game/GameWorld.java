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
import org.carden.lockoutgames.game.setting.SettingsImage;
import org.carden.lockoutgames.info.DimensionSearch;
import org.carden.lockoutgames.info.SettingIDS;
import org.carden.lockoutgames.info.SettingsConstants;
import org.carden.lockoutgames.info.StructureStrings;
import org.carden.lockoutgames.utils.Utils;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class GameWorld {
    /**
     * This will calculate and store information about the contents of the world the game is taking place in
     */

    static final int STRUCTURE_SEARCH_TIMEOUT_SECONDS = 10;
    static final int BIOME_SEARCH_BORDER_MARGIN_BLOCKS = 30;

    LockoutGames plugin;
    MVWorldManager worldManager;

    private int worldSize;
    private boolean isLargeWorld = false;
    private final String world_name;

    private final Set<Biome> availableBiomes;
    private final Set<Structure> availableStructures;

    private static final List<Biome> allBiomes = Registry.BIOME.stream().toList();
    private static final List<Structure> allStructures = Registry.STRUCTURE.stream().toList();

    private static final GameWorld gameWorld = new GameWorld();

    private GameWorld() {

        this.plugin = LockoutGames.getPluginInstance();
        this.availableBiomes = new HashSet<>();
        this.availableStructures = new HashSet<>();
        this.world_name = "world";
        worldManager = plugin.getMultiverseCore().getMVWorldManager();
        setProps(null);
    }

    public static GameWorld getGameWorld() {
        return gameWorld;
    }

    /**
     *
     * @param value Sets the world border size
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

    public CompletableFuture<Boolean> updateLogic(int searchRadius, boolean debug) {
        CompletableFuture<Boolean> completeCheck = new CompletableFuture<>();
        CompletableFuture<Set<Biome>> biomesComplete = scanBiomes(searchRadius);
        CompletableFuture<Set<Structure>> structuresComplete = scanStructures(searchRadius);
        this.isLargeWorld = searchRadius >= SettingsConstants.LOGIC_SPREAD_SCAN_THRESHOLD;
        biomesComplete.thenAcceptBoth(structuresComplete, (biomeResult, structureResult) -> {
            if(debug) {
                Registry.BIOME.stream()
                        .filter(biomeResult::contains)
                        .forEach(biome -> LockoutGames.broadcastMessage(ChatColor.GREEN + Utils.readableEnumString(biome.toString()) + ChatColor.DARK_GREEN + " found"));
                Registry.STRUCTURE.stream()
                        .filter(structureResult::contains)
                        .forEach(struct -> LockoutGames.broadcastMessage(ChatColor.BLUE + Utils.readableEnumString(StructureStrings.getStructureName(struct)) + ChatColor.DARK_GREEN + " found"));
                Registry.BIOME.stream()
                        .filter(biome -> !biomeResult.contains(biome) && DimensionSearch.getAllBiomes().contains(biome))
                        .forEach(biome -> LockoutGames.broadcastMessage(ChatColor.GREEN + Utils.readableEnumString(biome.toString()) + ChatColor.RED + " not found"));
                Registry.STRUCTURE.stream()
                        .filter(struct -> !structureResult.contains(struct) && DimensionSearch.getAllStructures().contains(struct))
                        .forEach(struct -> LockoutGames.broadcastMessage(ChatColor.BLUE + Utils.readableEnumString(StructureStrings.getStructureName(struct)) + ChatColor.RED + " not found"));
                Registry.BIOME.stream()
                        .filter(biome -> !DimensionSearch.getAllBiomes().contains(biome))
                        .forEach(biome -> LockoutGames.broadcastMessage(ChatColor.GREEN + Utils.readableEnumString(biome.toString()) + ChatColor.DARK_RED + ChatColor.BOLD + " MISSING"));
                Registry.STRUCTURE.stream()
                        .filter(struct -> !DimensionSearch.getAllStructures().contains(struct))
                        .forEach(struct -> LockoutGames.broadcastMessage(ChatColor.BLUE + Utils.readableEnumString(StructureStrings.getStructureName(struct)) + ChatColor.DARK_RED + ChatColor.BOLD + " MISSING"));
            }
            else {
                availableBiomes.clear();
                availableBiomes.addAll(biomeResult);
                availableStructures.clear();
                availableStructures.addAll(structureResult);
            }
            completeCheck.complete(true);
        });
        return completeCheck;
    }

    public CompletableFuture<Boolean> setupWorld(@NotNull SettingsImage settings) {
        this.setWorldSize(settings.getSetting(SettingIDS.WORLD_SIZE));
        CompletableFuture<Boolean> isComplete = updateLogic(settings.getSetting(SettingIDS.LOGIC_RADIUS), false);
        applySettings(settings);
        return isComplete;
    }

    public void applySettings(@NotNull SettingsImage settings) {
        setProps(settings);
        this.setWorldSize(settings.getSetting(SettingIDS.WORLD_SIZE));
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
            world.setPVPMode(settings != null && settings.getSetting(SettingIDS.PVP));
            world.setAllowAnimalSpawn(true);
            world.setAllowMonsterSpawn(true);
            world.setDifficulty(settings != null ? settings.getSetting(SettingIDS.DIFFICULTY) : SettingsConstants.DIFFICULTY_DEFAULT);
            world.allowPortalMaking(AllowedPortalType.ALL);
            world.setBedRespawn(true);
            world.setHunger(settings == null || settings.getSetting(SettingIDS.HUNGER));
            world.setEnableWeather(true);
            world.setSpawnLocation(world.getCBWorld().getSpawnLocation());
        }
    }

    /**
     * Updated the availableBiomes set to contain the biomes that exist within the world boundary.
     * This scan is done asynchronously in order to not block the main server thread.
     */
    private CompletableFuture<Set<Biome>> scanBiomes(int searchRadius) {
        Set<Biome> scanResult = new HashSet<>();
        Lock lock = new ReentrantLock();
        CompletableFuture<Set<Biome>> isComplete = new CompletableFuture<>();

        if(searchRadius >= SettingsConstants.LOGIC_SPREAD_SCAN_THRESHOLD) {
            scanResult.addAll(allBiomes);
            new BukkitRunnable() {
                @Override
                public void run() {
                    isComplete.complete(scanResult);
                }
            }.runTaskLater(plugin, 10);
            return isComplete;
        } else {
            new BukkitRunnable() {
                private int index = 0;
                private int biomeCount = 0;
                @Override
                public void run() {
                    lock.lock();
                    Biome b = allBiomes.get(index);
                    index++;
                    if(index >= allBiomes.size()) cancel();
                    lock.unlock();
                    World world = getWorld(DimensionSearch.NORMAL.getEnvironment());
                    if(DimensionSearch.NETHER.getBiomes().contains(b)) {
                        world = getWorld(World.Environment.NETHER);
                    }
                    else if(DimensionSearch.THE_END.getBiomes().contains(b)) {
                        scanResult.add(b);
                        biomeCount++;
                        return;
                    }
                    Location origin = new Location(world, 0, 64, 0);
                    if (world.locateNearestBiome(origin, searchRadius - BIOME_SEARCH_BORDER_MARGIN_BLOCKS, b) != null) {
                        scanResult.add(b);
                    }
                    biomeCount++;
                    if(biomeCount >= allBiomes.size()){
                        plugin.getServer().broadcastMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "Biome Scan complete");
                        isComplete.complete(scanResult);
                    }
                }
            }.runTaskTimerAsynchronously(plugin, 0, 1);
        }
        return isComplete;
    }

    /**
     * Updated the availableStructures set to contain the structures that exist within the world boundary.
     * This scan is done asynchronously in order to not block the main server thread,
     * and all structures are searched for in parallel
     */
    private CompletableFuture<Set<Structure>> scanStructures(int searchRadius) {
        Set<Structure> scanResult = new HashSet<>();
        Lock lock = new ReentrantLock();
        CompletableFuture<Set<Structure>> isComplete = new CompletableFuture<>();

        if(searchRadius >= SettingsConstants.LOGIC_SPREAD_SCAN_THRESHOLD) {
            scanResult.addAll(allStructures);
            new BukkitRunnable() {
                @Override
                public void run() {
                    isComplete.complete(scanResult);
                }
            }.runTaskLater(plugin, 10);
        }
        else {
            new BukkitRunnable() {

                private int index = 0;
                private int structureCount = 0;
                @Override
                public void run() {
                    lock.lock();
                    Structure s = allStructures.get(index);
                    index++;
                    if(index >= allStructures.size()) cancel();
                    lock.unlock();
                    World world = getWorld(DimensionSearch.NORMAL.getEnvironment());
                    if(DimensionSearch.NETHER.getStructures().contains(s)) {
                        world = getWorld(World.Environment.NETHER);
                    } else if(DimensionSearch.THE_END.getStructures().contains(s)) {
                        scanResult.add(s);
                        structureCount++;
                        return;
                    }
                    Location origin = new Location(world, 0, 0, 0);
                    StructureSearchResult result = structureSearchWithTimeout(world, origin, s, searchRadius);
                    if(result != null && Math.abs(result.getLocation().getX()) < (double) searchRadius && Math.abs(result.getLocation().getZ()) < (double) searchRadius){
                        scanResult.add(s);
                    }
                    structureCount++;
                    if(index >= allStructures.size()) {
                        if(structureCount >= allStructures.size()){
                            plugin.getServer().broadcastMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "Structure Scan complete");
                            isComplete.complete(scanResult);
                        }
                        cancel();
                    }
                }
            }.runTaskTimerAsynchronously(plugin, 0, 1);
        }
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
    private StructureSearchResult structureSearchWithTimeout(World world, Location origin, Structure s, int searchRadius) {
        CompletableFuture<StructureSearchResult> future = new CompletableFuture<>();
        BukkitTask search = new BukkitRunnable() {
            @Override
            public void run() {
                StructureSearchResult result = world.locateNearestStructure(origin, s, searchRadius, false);
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

    public boolean isLargeWorld() {
        return this.isLargeWorld;
    }
}

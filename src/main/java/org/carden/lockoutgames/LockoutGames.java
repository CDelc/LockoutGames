package org.carden.lockoutgames;

import com.onarandombox.MultiverseCore.MultiverseCore;
import com.onarandombox.MultiverseNetherPortals.MultiverseNetherPortals;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.carden.lockoutgames.game.GameBuilder;
import org.carden.lockoutgames.game.GameWorld;
import org.carden.lockoutgames.game.player.PlayerManager;
import org.carden.lockoutgames.utils.Loader;

import java.util.Random;

/**
 * Base plugin class
 */
public final class LockoutGames extends JavaPlugin {

    private MultiverseCore multiverseCore;
    private MultiverseNetherPortals mvnetherPortals;

    private GameBuilder gameBuilder;
    private GameWorld gameWorld;
    private PlayerManager playerManager;

    private static Random rng;
    private static LockoutGames instance;

    private static final String BROADCAST_PREFIX = ChatColor.WHITE + "[" + ChatColor.AQUA + "Lockout" + ChatColor.YELLOW + "Games" + ChatColor.WHITE + "]";

    @Override
    public void onEnable() {
        instance = this;
        setupMultiverseDependencies();
        gameBuilder = GameBuilder.getGameBuilder();
        playerManager = PlayerManager.getPlayerManager();
        gameWorld = GameWorld.getGameWorld();

        Loader.loadAll(this);
    }

    @Override
    public void onDisable() {
    }


    private void setupMultiverseDependencies() {
        if(!setupMultiverseCore()) {
            getLogger().severe("Multiverse-Core not found! Please install the Multiverse-core plugin to your server.\n" +
                    "Plugin disabled.");
            getServer().getPluginManager().disablePlugin(this);
        }
        if(!setupNetherPortals()) {
            getLogger().severe("Multiverse-NetherPortals not found! Please install the Multiverse-core plugin to your server.\n" +
                    "Plugin disabled.");
            getServer().getPluginManager().disablePlugin(this);
        }
    }

    private boolean setupMultiverseCore() {
        Plugin plugin = getServer().getPluginManager().getPlugin("Multiverse-Core");
        if (!(plugin instanceof MultiverseCore)) {
            return false;
        }
        multiverseCore = (MultiverseCore) plugin;
        return true;
    }

    private boolean setupNetherPortals() {
        Plugin plugin = getServer().getPluginManager().getPlugin("Multiverse-NetherPortals");
        if (!(plugin instanceof MultiverseNetherPortals)) {
            return false;
        }
        mvnetherPortals = (MultiverseNetherPortals) plugin;
        return true;
    }

    public MultiverseCore getMultiverseCore() {
        return multiverseCore;
    }

    public MultiverseNetherPortals getMvnetherPortals() {
        return mvnetherPortals;
    }

    public GameBuilder getGameBuilder() {
        return gameBuilder;
    }

    public GameWorld getGameWorld() {
        return gameWorld;
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    public static LockoutGames getPluginInstance() {
        return instance;
    }

    public static Random getRng() {
        return rng;
    }

    public static void broadcastMessage(String s) {
        instance.getServer().broadcastMessage(BROADCAST_PREFIX + " " + s);
    }
}

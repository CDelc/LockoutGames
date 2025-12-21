package org.carden.lockoutgames;

import com.onarandombox.MultiverseCore.MultiverseCore;
import com.onarandombox.MultiverseNetherPortals.MultiverseNetherPortals;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.carden.lockoutgames.game.GameWorld;
import org.carden.lockoutgames.game.gametypes.Lockout;
import org.carden.lockoutgames.game.player.PlayerManager;
import org.carden.lockoutgames.game.setting.Setting;
import org.carden.lockoutgames.game.setting.SettingsImage;
import org.carden.lockoutgames.utils.Loader;

import java.util.Optional;
import java.util.Random;

/**
 * Base plugin class
 */
public final class LockoutGames extends JavaPlugin {

    private MultiverseCore multiverseCore;
    private MultiverseNetherPortals mvnetherPortals;

    private static GameWorld gameWorld;
    private static org.carden.lockoutgames.game.gametypes.Game game;
    private PlayerManager playerManager;

    private static Random rng;
    private static LockoutGames instance;

    private static final String BROADCAST_PREFIX = ChatColor.WHITE + "[" + ChatColor.AQUA + "Lockout" + ChatColor.YELLOW + "Games" + ChatColor.WHITE + "]";

    @Override
    public void onEnable() {
        instance = this;
        setupMultiverseDependencies();
        playerManager = PlayerManager.getPlayerManager();
        gameWorld = GameWorld.getGameWorld();
        game = null;

        Loader.loadAll(this);
    }

    @Override
    public void onDisable() {
    }

    /**
     * Begins a new game.
     * @return False if a game is already running, true if the game begins successfully.
     */
    public static boolean startGame() {
        if (getGame().isPresent()) return false;
        SettingsImage settingsImage = Setting.saveSettings();
        gameWorld.setupWorld(settingsImage).thenRun(new BukkitRunnable() {
            @Override
            public void run() {
                game = new Lockout(settingsImage);
            }
        });
        return true;
    }

    public static void endGame() {
        game = null;
    }

    public static LockoutGames getPluginInstance() {
        return instance;
    }

    public static Random getRng() {
        return rng;
    }

    public static Optional<org.carden.lockoutgames.game.gametypes.Game> getGame() {
        return Optional.ofNullable(game);
    }

    public static GameWorld getGameWorld() {
        return gameWorld;
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    public static void broadcastMessage(String s) {
        instance.getServer().broadcastMessage(BROADCAST_PREFIX + " " + s);
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
}

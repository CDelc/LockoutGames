package org.carden.lockoutgames;

import com.onarandombox.MultiverseCore.MultiverseCore;
import com.onarandombox.MultiverseNetherPortals.MultiverseNetherPortals;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.carden.lockoutgames.commands.Gadmin;
import org.carden.lockoutgames.commands.Game;
import org.carden.lockoutgames.events.EventListener;
import org.carden.lockoutgames.game.GameBuilder;
import org.carden.lockoutgames.game.GameWorld;
import org.carden.lockoutgames.game.player.PlayerManager;

import java.util.Objects;

/**
 * Base plugin class
 */
public final class LockoutGames extends JavaPlugin {

    private MultiverseCore multiverseCore;
    private MultiverseNetherPortals mvnetherPortals;

    private GameBuilder gameBuilder;
    private GameWorld gameWorld;
    private PlayerManager playerManager;

    private static LockoutGames instance;

    private static final String BROADCAST_PREFIX = ChatColor.WHITE + "[" + ChatColor.AQUA + "Lockout" + ChatColor.YELLOW + "Games" + ChatColor.WHITE + "]";

    @Override
    public void onEnable() {
        instance = this;
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

        gameBuilder = new GameBuilder();
        playerManager = new PlayerManager();
        gameWorld = new GameWorld();

        EventListener listener = new EventListener(this);
        EventListener playerTracker = new EventListener(this);
        getServer().getPluginManager().registerEvents(listener, this);
        getServer().getPluginManager().registerEvents(playerTracker, this);

        try {
            Objects.requireNonNull(this.getCommand("gadmin")).setExecutor(new Gadmin());
            Objects.requireNonNull(this.getCommand("gadmin")).setTabCompleter(new Gadmin());
            Objects.requireNonNull(this.getCommand("game")).setExecutor(new Game());
            Objects.requireNonNull(this.getCommand("game")).setTabCompleter(new Game());
        }catch(NullPointerException e) {
            this.getLogger().severe("MISSING COMMAND " + e.getMessage());
        }
    }

    @Override
    public void onDisable() {
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

    public static void broadcastMessage(String s) {
        instance.getServer().broadcastMessage(BROADCAST_PREFIX + " " + s);
    }
}

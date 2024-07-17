package org.carden.lockoutgames;

import com.onarandombox.MultiverseCore.MultiverseCore;
import com.onarandombox.MultiverseNetherPortals.MultiverseNetherPortals;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.carden.lockoutgames.events.EventListener;
import org.carden.lockoutgames.game.GameSettings;

/**
 * Base plugin class
 */
public final class LockoutGames extends JavaPlugin {

    private MultiverseCore multiverseCore;
    private MultiverseNetherPortals mvnetherPortals;

    private GameSettings game;


    @Override
    public void onEnable() {
        if(!setupMultiverseCore()) {
            getLogger().severe("Multiverse-Core not found! Please install the Multiverse-core plugin to your server.\n" +
                    "Plugin disabled.");
            getServer().getPluginManager().disablePlugin(this);
        }
        if(!setupNetherPortals()) {
            getLogger().severe("Multiverse-NetherPortals not found! Please install the Multiverse-NetherPortals plugin to your server.\n" +
                    "Plugin disabled.");
            getServer().getPluginManager().disablePlugin(this);
        }

        EventListener listener = new EventListener(this);
        getServer().getPluginManager().registerEvents(listener, this);

        game = new GameSettings(this);

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

    public GameSettings getGameSettings() {
        return game;
    }
}

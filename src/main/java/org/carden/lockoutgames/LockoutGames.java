package org.carden.lockoutgames;

import com.onarandombox.MultiverseCore.MultiverseCore;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Base plugin class
 */
public final class LockoutGames extends JavaPlugin {

    MultiverseCore multiverseCore;

    @Override
    public void onEnable() {
        if(!setupMultiverseCore()) {
            getLogger().severe("Multiverse-Core not found! Please install the Multiverse-core plugin to your server.\n" +
                    "Plugin disabled.");
            getServer().getPluginManager().disablePlugin(this);
        }

        EventListener listener = new EventListener(this);
        getServer().getPluginManager().registerEvents(listener, this);

    }

    @Override
    public void onDisable() {
    }

    private boolean setupMultiverseCore() {
        Plugin plugin = getServer().getPluginManager().getPlugin("Multiverse-Core");
        if (plugin == null || !(plugin instanceof MultiverseCore)) {
            return false;
        }
        multiverseCore = (MultiverseCore) plugin;
        return true;
    }
}

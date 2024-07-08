package org.carden.lockoutgames;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * Base plugin class
 */
public final class LockoutGames extends JavaPlugin {

    @Override
    public void onEnable() {

        EventListener listener = new EventListener(this);
        getServer().getPluginManager().registerEvents(listener, this);

    }

    @Override
    public void onDisable() {
    }
}

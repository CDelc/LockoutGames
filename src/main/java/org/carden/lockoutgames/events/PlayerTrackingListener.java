package org.carden.lockoutgames.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.carden.lockoutgames.LockoutGames;


public class PlayerTrackingListener implements Listener {

    static LockoutGames plugin = LockoutGames.getPluginInstance();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        plugin.getPlayerManager().handlePlayerJoin(e);
    }

}

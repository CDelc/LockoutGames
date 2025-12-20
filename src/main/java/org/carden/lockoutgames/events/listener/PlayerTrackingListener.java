package org.carden.lockoutgames.events.listener;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.carden.lockoutgames.LockoutGames;

import java.util.HashSet;


public class PlayerTrackingListener implements Listener {

    private static final LockoutGames plugin = LockoutGames.getPluginInstance();

    private static final PlayerTrackingListener playerTrackingListener = new PlayerTrackingListener();

    private PlayerTrackingListener() {}

    public static PlayerTrackingListener getInstance() {
        return playerTrackingListener;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        plugin.getPlayerManager().handlePlayerJoin(e);
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e) {
        Location spawnPointOverride = LockoutGames.getPluginInstance().getPlayerManager().getGamePlayerObject(e.getPlayer().getUniqueId()).getDefaultSpawnPoint();
        if(e.getPlayer().getRespawnLocation() == null) e.setRespawnLocation(spawnPointOverride);
    }

}

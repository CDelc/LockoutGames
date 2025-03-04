package org.carden.lockoutgames.game;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.HashSet;
import java.util.UUID;

public class PlayerManager {

    HashSet<UUID> players;
    HashSet<UUID> spectators;

    public PlayerManager() {
        spectators = new HashSet<UUID>();
        players = new HashSet<UUID>(Bukkit.getOnlinePlayers().stream().map(Player::getUniqueId).toList());
    }

    public void handlePlayerJoin(PlayerJoinEvent e) {
        UUID uuid = e.getPlayer().getUniqueId();
        if(!players.contains(uuid) && !spectators.contains(uuid)) {
            players.add(uuid);
        }
    }

    public boolean playerIsSpectator(Player p) {
        return spectators.contains(p.getUniqueId());
    }
}

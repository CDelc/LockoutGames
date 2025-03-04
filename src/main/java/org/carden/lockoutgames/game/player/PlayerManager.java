package org.carden.lockoutgames.game.player;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.HashMap;
import java.util.UUID;

public class PlayerManager {

    HashMap<UUID, GamePlayer> players;

    public PlayerManager() {
        players = new HashMap<UUID, GamePlayer>();
        Bukkit.getOnlinePlayers().forEach(this::introducePlayer);
    }

    private void introducePlayer(Player p) {
        if(!players.containsKey(p.getUniqueId())) {
            addNewPlayer(p);
        }
    }

    private void addNewPlayer(Player p) {
        GamePlayer new_player = new GamePlayer(p);
        players.put(p.getUniqueId(), new_player);
    }

    public void handlePlayerJoin(PlayerJoinEvent e) {
        Player target = e.getPlayer();
        UUID uuid = target.getUniqueId();
        if(players.containsKey(uuid)) {
            players.get(uuid).setPlayerObject(target);
        }
        else {
            introducePlayer(target);
        }
    }

    public GamePlayer getGamePlayerObject(UUID playerUUID) {
        return players.get(playerUUID);
    }
}

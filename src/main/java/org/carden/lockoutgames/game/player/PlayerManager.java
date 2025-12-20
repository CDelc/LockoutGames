package org.carden.lockoutgames.game.player;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.carden.lockoutgames.LockoutGames;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class PlayerManager {

    private final HashMap<UUID, GamePlayer> players;
    private static final PlayerManager playerManager = new PlayerManager();

    private PlayerManager() {
        players = new HashMap<>();
        Bukkit.getOnlinePlayers().forEach(this::addNewPlayer);
    }

    public static PlayerManager getPlayerManager() {
        return playerManager;
    }

    private void addNewPlayer(Player p) {
        GamePlayer new_player = new GamePlayer(p);
        if(players.containsKey(p.getUniqueId())) return;
        players.put(p.getUniqueId(), new_player);
        new_player.setSpectator(LockoutGames.getPluginInstance().getGameBuilder().gameIsRunning());
    }

    public void handlePlayerJoin(PlayerJoinEvent e) {
        Player target = e.getPlayer();
        UUID uuid = target.getUniqueId();
        if(players.containsKey(uuid)) {
            players.get(uuid).setPlayerObject(target);
        }
        else if(!players.containsKey(target.getUniqueId())) {
            addNewPlayer(target);
        }
        players.get(uuid).updateGamemode();
    }

    public Collection<GamePlayer> getAllPlayers() {
        return players.values();
    }

    public GamePlayer getGamePlayerObject(UUID playerUUID) {
        if(!players.containsKey(playerUUID)) {
            addNewPlayer(Bukkit.getServer().getPlayer(playerUUID));
        }
        return players.get(playerUUID);
    }
}

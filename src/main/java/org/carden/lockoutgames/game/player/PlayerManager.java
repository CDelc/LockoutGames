package org.carden.lockoutgames.game.player;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.carden.lockoutgames.LockoutGames;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class PlayerManager {

    HashMap<UUID, GamePlayer> players;

    public PlayerManager() {
        players = new HashMap<>();
        Bukkit.getOnlinePlayers().forEach(this::addNewPlayer);
    }

    private void addNewPlayer(Player p) {
        GamePlayer new_player = new GamePlayer(p);
        players.put(p.getUniqueId(), new_player);
        if(LockoutGames.getPluginInstance().getGameBuilder().gameIsRunning()) {
            new_player.setSpectator(true);
            p.setGameMode(GameMode.SPECTATOR);
        } else {
            new_player.setSpectator(false);
            p.setGameMode(GameMode.SURVIVAL);
        }
    }

    public List<GamePlayer> getParticipants() {
        return players.values().stream().filter(player -> !player.isSpectator()).toList();
    }

    public void cleanOfflinePlayers() {
        players.forEach((uuid, player) -> {
            if(!Bukkit.getOnlinePlayers().contains(player.getPlayer())) {
                players.remove(uuid);
            }
        });
    }

    public static void movePlayerstoSafeWorld() {
        Bukkit.getOnlinePlayers().forEach(player -> player.teleport(Objects.requireNonNull(Bukkit.getServer().getWorld("world")).getSpawnLocation()));
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
    }

    public void updatePlayerGamemodes() {
        players.forEach((uuid, player) -> {
            Player p = player.getPlayer();
            if(!player.isSpectator()) p.setGameMode(GameMode.SURVIVAL);
            else p.setGameMode(GameMode.SPECTATOR);
        });
    }

    public GamePlayer getGamePlayerObject(UUID playerUUID) {
        return players.get(playerUUID);
    }
}

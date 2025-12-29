package org.carden.lockoutgames.goal;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.generator.structure.GeneratedStructure;
import org.bukkit.generator.structure.Structure;
import org.carden.lockoutgames.game.player.GamePlayer;
import org.carden.lockoutgames.game.player.PlayerManager;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import static org.carden.lockoutgames.utils.Utils.playerEventPlayer;

public final class VisitStructures extends ChecklistGoal<Structure> {

    public VisitStructures(Collection<Structure> requiredStructures) {
        super(Set.of(PlayerMoveEvent.class)); // TODO: Replace w/ recurring event
        this.requiredItems = List.copyOf(requiredStructures);
    }

    @Override
    public void checkEvent(Event e) {
        Player p = playerEventPlayer(e);
        if (p == null) return;
        GamePlayer gamePlayer = PlayerManager.getPlayerManager().getGamePlayerObject(p.getUniqueId());
        p.getLocation().getChunk().getStructures().stream()
                .filter((structure) -> this.requiredItems.contains(structure.getStructure()))
                .forEach((structure) -> {
                    if (playerWithinStructure(p, structure)) {
                        this.checkFromChecklist(gamePlayer, structure.getStructure());
                    }
                });
    }

    private static boolean playerWithinStructure(Player player, GeneratedStructure structure) {
        return structure.getBoundingBox().contains(player.getLocation().toVector());
    }
}

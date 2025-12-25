package org.carden.lockoutgames.goal;

import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerMoveEvent;
import org.carden.lockoutgames.game.player.GamePlayer;
import org.carden.lockoutgames.game.player.PlayerManager;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.carden.lockoutgames.utils.Utils.playerEventPlayer;

public class VisitBiomes extends ChecklistGoal<Biome> {
    public VisitBiomes(Collection<Biome> requiredBiomes) {
        super(Set.of(PlayerMoveEvent.class));
        this.requiredItems = List.copyOf(requiredBiomes);
        this.setupGoal(requiredBiomes);
    }

    protected void setupGoal(Collection<Biome> biomes) {

        if (biomes.isEmpty()) {
            return;
        }
        else if (biomes.size() == 1) {
            Biome b = biomes.iterator().next();
            this.description = String.format("Visit a %s biome", b);
        }
        else {
            this.description = String.format("Visit biomes: %s", String.join(", ", biomes.stream().map(Object::toString).collect(Collectors.toUnmodifiableSet())));
        }
        this.failedToGenerate = false;
    }

    @Override
    protected void checkEvent(Event e) {
        Player p = playerEventPlayer(e);
        if (p == null) return;
        Biome biome = p.getLocation().getBlock().getBiome();
        if (this.requiredItems.contains(biome)) {
            GamePlayer gamePlayer = PlayerManager.getPlayerManager().getGamePlayerObject(p.getUniqueId());
            this.checkFromChecklist(gamePlayer, biome);
        }
    }
}

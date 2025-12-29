package org.carden.lockoutgames.goal;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.carden.lockoutgames.game.player.GamePlayer;
import org.carden.lockoutgames.game.player.PlayerManager;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static org.carden.lockoutgames.utils.Utils.entityEventPlayer;

public final class GetPotionEffect extends RegularGoal {

    private final Map<PotionEffectType, Integer> requiredEffectLevels;

    /**
     * Set a goal of having a collection of potion effects simultaneously.
     *
     * @param effectLevels
     */
    public GetPotionEffect(Map<PotionEffectType, Integer> effectLevels) {
        super(Set.of(EntityPotionEffectEvent.class));
        this.requiredEffectLevels = effectLevels.entrySet().stream()
                .collect(Collectors.toUnmodifiableMap(Map.Entry::getKey, (entry) -> Integer.max(0, Objects.requireNonNullElse(entry.getValue(), 0))));
    }

    @Override
    public void checkEvent(Event e) {
        EntityPotionEffectEvent effectEvent = assertEntityPotionEffectEvent(e);
        if (effectEvent == null) return;
        Player p = entityEventPlayer(effectEvent);
        if (p == null) return;

        GamePlayer gamePlayer = PlayerManager.getPlayerManager().getGamePlayerObject(p.getUniqueId());
        if (effectEvent.getAction() == EntityPotionEffectEvent.Action.ADDED && playerHasPotionEffectLevels(p, this.requiredEffectLevels)) {
            this.fireGoalCompleteEvent(gamePlayer);
        }
    }

    /**
     * Does a player have a superset of some effects?
     *
     * @param player
     * @param effectLevels
     * @return whether the player has the potion effects
     */
    private static boolean playerHasPotionEffectLevels(Player player, Map<PotionEffectType, Integer> effectLevels) {
        return effectLevels.entrySet().stream().allMatch((entry) -> {
            PotionEffect effectInstance = player.getPotionEffect(entry.getKey());
            if (effectInstance == null) {
                return false;
            } else {
                return effectInstance.getAmplifier() >= entry.getValue();
            }
        });
    }

    private static EntityPotionEffectEvent assertEntityPotionEffectEvent(Event e) {
        if (e instanceof EntityPotionEffectEvent) {
            return (EntityPotionEffectEvent) e;
        } else {
            return null;
        }
    }
}

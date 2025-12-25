package org.carden.lockoutgames.goal;

import org.bukkit.Statistic;
import org.bukkit.entity.EntityType;
import org.bukkit.event.player.PlayerStatisticIncrementEvent;

public final class EntityStatisticGoal extends BaseStatisticGoal {
    private final EntityType entity;

    /**
     * Create a goal for an entity statistic.
     * @param stat
     * @param requiredValue
     * @throws IllegalArgumentException when the statistic is not an entity statistic
     */
    public EntityStatisticGoal(Statistic stat, EntityType entity, int requiredValue) {
        super(stat, requiredValue);
        this.entity = entity;
        if (stat.getType() != Statistic.Type.ENTITY) {
            throw new IllegalArgumentException(String.format("Statistic %s is not an entity statistic", stat));
        }
    }

    @Override
    protected boolean isStatisticRelevant(PlayerStatisticIncrementEvent event) {
        return event.getStatistic() == this.statistic && event.getEntityType() == this.entity;
    }
}

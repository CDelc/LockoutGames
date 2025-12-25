package org.carden.lockoutgames.goal;

import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.event.player.PlayerStatisticIncrementEvent;

public final class MaterialStatisticGoal extends BaseStatisticGoal {
    private final Material material;

    /**
     * Create a goal for a material statistic.
     * @param stat
     * @param requiredValue
     * @throws IllegalArgumentException when the statistic is not a block or item statistic
     */
    public MaterialStatisticGoal(Statistic stat, Material material, int requiredValue) {
        super(stat, requiredValue);
        this.material = material;
        if (stat.getType() != Statistic.Type.BLOCK && stat.getType() != Statistic.Type.ITEM) {
            throw new IllegalArgumentException(String.format("Statistic %s is not a material statistic", stat));
        }
    }

    @Override
    protected boolean isStatisticRelevant(PlayerStatisticIncrementEvent event) {
        return event.getStatistic() == this.statistic && event.getMaterial() == this.material;
    }
}

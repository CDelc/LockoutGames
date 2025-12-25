package org.carden.lockoutgames.goal;

import org.bukkit.Statistic;
import org.bukkit.event.player.PlayerStatisticIncrementEvent;

public final class GeneralStatisticGoal extends BaseStatisticGoal {

    /**
     * Create a goal for an untyped statistic.
     * @param stat
     * @param requiredValue
     * @throws IllegalArgumentException when the statistic is a substatistic
     */
    public GeneralStatisticGoal(Statistic stat, int requiredValue) {
        super(stat, requiredValue);
        if (stat.isSubstatistic()) {
            throw new IllegalArgumentException(String.format("Statistic %s is not an untyped statistic", stat));
        }
    }

    @Override
    protected boolean isStatisticRelevant(PlayerStatisticIncrementEvent event) {
        return true;
    }
}

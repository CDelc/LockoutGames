package org.carden.lockoutgames.goal.factory.instance;

import org.bukkit.Material;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.carden.lockoutgames.goal.GoalDifficulty;
import org.carden.lockoutgames.goal.IMutableGoal;
import org.carden.lockoutgames.goal.PlayerEntityEventPredicateGoal;
import org.carden.lockoutgames.goal.factory.base.BaseGoalFactory;
import org.carden.lockoutgames.info.WorldRequirements;

public final class TriggerDripleaf extends BaseGoalFactory {
    @Override
    protected IMutableGoal makeGoalHook() {
        return new PlayerEntityEventPredicateGoal<>(
                EntityChangeBlockEvent.class,
                (event) -> event.getBlock().getType() == Material.BIG_DRIPLEAF,
                GoalDifficulty.EASY,
                "Jump on a big dripleaf"
                );
    }

    @Override
    protected boolean canGenerateGoalHook() {
        return WorldRequirements.checkElement(Material.BIG_DRIPLEAF.name());
    }
}

package org.carden.lockoutgames.goal.factory.instance;

import org.bukkit.Material;
import org.carden.lockoutgames.goal.GoalDifficulty;
import org.carden.lockoutgames.goal.GoalType;
import org.carden.lockoutgames.goal.IMutableGoal;
import org.carden.lockoutgames.goal.factory.base.CollectItem;

public class ObtainRecoveryCompass extends CollectItem {

    public ObtainRecoveryCompass() {
        super(Material.RECOVERY_COMPASS, GoalDifficulty.HARD);
        this.canGenerateMultiple = false;
        this.addGoalTypes(GoalType.ECHO_SHARD);
    }

    @Override
    protected IMutableGoal makeCollectItemGoal(Material item, int itemsRequiredPerStack) {
        IMutableGoal goal = super.makeCollectItemGoal(item, itemsRequiredPerStack);
        goal.setDescription("Obtain a recovery compass");
        return goal;
    }
}

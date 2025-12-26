package org.carden.lockoutgames.goal.factory;

import org.bukkit.Material;
import org.carden.lockoutgames.game.setting.SettingsImage;
import org.carden.lockoutgames.goal.GoalDifficulty;
import org.carden.lockoutgames.goal.GoalType;
import org.carden.lockoutgames.goal.IMutableGoal;

import java.util.List;

public class ObtainRecoveryCompass extends CollectItem {

    public ObtainRecoveryCompass() {
        super(Material.RECOVERY_COMPASS);
        this.canGenerateMultiple = false;
        this.addGoalTypes(GoalType.ECHO_SHARD);
    }

    @Override
    protected IMutableGoal makeCollectItemGoal(List<Material> requiredItems, int itemsRequiredPerStack) {
        IMutableGoal goal = super.makeCollectItemGoal(requiredItems, itemsRequiredPerStack);
        goal.setDescription("Obtain a recovery compass");
        goal.setGoalDifficulty(GoalDifficulty.HARD);
        return goal;
    }
}

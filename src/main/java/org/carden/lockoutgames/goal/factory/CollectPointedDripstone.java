package org.carden.lockoutgames.goal.factory;

import org.bukkit.Material;
import org.carden.lockoutgames.game.setting.SettingsImage;
import org.carden.lockoutgames.goal.GoalDifficulty;
import org.carden.lockoutgames.goal.IMutableGoal;

import java.util.List;

public class CollectPointedDripstone extends CollectItem {

    private static final int COLLECT_POINTED_DRIPSTONE_MINIMUM = 1;
    private static final int COLLECT_POINTED_DRIPSTONE_MAXIMUM = 32;

    public CollectPointedDripstone(SettingsImage settings) {
        super(settings, Material.POINTED_DRIPSTONE, COLLECT_POINTED_DRIPSTONE_MINIMUM, COLLECT_POINTED_DRIPSTONE_MAXIMUM);

        this.canGenerateMultiple = false;
    }

    @Override
    protected IMutableGoal makeCollectItemGoal(List<Material> requiredItems, int itemsRequiredPerStack) {
        IMutableGoal goal = super.makeCollectItemGoal(requiredItems, itemsRequiredPerStack);
        goal.setDescription("Collect " + itemsRequiredPerStack + " pointed dripstone");
        goal.setGoalDifficulty(GoalDifficulty.MEDIUM);
        return goal;
    }
}

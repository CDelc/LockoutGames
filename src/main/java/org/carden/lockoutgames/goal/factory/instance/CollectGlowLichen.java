package org.carden.lockoutgames.goal.factory.instance;

import org.bukkit.Material;
import org.carden.lockoutgames.goal.GoalDifficulty;
import org.carden.lockoutgames.goal.IMutableGoal;
import org.carden.lockoutgames.goal.factory.base.CollectItem;

import java.util.List;

public class CollectGlowLichen extends CollectItem {

    private static final int COLLECT_GLOW_LICKEN_MINIMUM = 1;
    private static final int COLLECT_GLOW_LICKEN_MAXIMUM = 64;

    public CollectGlowLichen() {
        super(Material.GLOW_LICHEN, COLLECT_GLOW_LICKEN_MINIMUM, COLLECT_GLOW_LICKEN_MAXIMUM);

        this.canGenerateMultiple = false;
    }

    @Override
    protected IMutableGoal makeCollectItemGoal(List<Material> requiredItems, int itemsRequiredPerStack) {
        IMutableGoal goal = super.makeCollectItemGoal(requiredItems, itemsRequiredPerStack);
        goal.setDescription("Collect " + itemsRequiredPerStack + " glow lichen");
        goal.setGoalDifficulty(GoalDifficulty.EASY);
        return goal;
    }
}

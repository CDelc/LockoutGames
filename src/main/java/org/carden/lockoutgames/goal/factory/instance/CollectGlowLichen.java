package org.carden.lockoutgames.goal.factory.instance;

import org.bukkit.Material;
import org.carden.lockoutgames.goal.GoalDifficulty;
import org.carden.lockoutgames.goal.IMutableGoal;
import org.carden.lockoutgames.goal.factory.base.CollectItem;
import org.carden.lockoutgames.goal.factory.base.CollectItems;

import java.util.List;

public class CollectGlowLichen extends CollectItem {

    private static final int COLLECT_GLOW_LICKEN_MINIMUM = 1;
    private static final int COLLECT_GLOW_LICKEN_MAXIMUM = 64;

    public CollectGlowLichen() {
        super(Material.GLOW_LICHEN, GoalDifficulty.EASY, COLLECT_GLOW_LICKEN_MINIMUM, COLLECT_GLOW_LICKEN_MAXIMUM);

        this.canGenerateMultiple = false;
    }

    @Override
    protected IMutableGoal makeCollectItemGoal(Material item, int stackSize) {
        IMutableGoal goal = super.makeCollectItemGoal(item, stackSize);
        goal.setDescription("Collect " + stackSize + " glow lichen");
        return goal;
    }
}

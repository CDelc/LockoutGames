package org.carden.lockoutgames.goal.factory.instance;

import org.bukkit.Material;
import org.carden.lockoutgames.goal.GoalDifficulty;
import org.carden.lockoutgames.goal.IMutableGoal;
import org.carden.lockoutgames.goal.factory.base.CollectItem;
import org.carden.lockoutgames.goal.factory.base.CollectItems;

import java.util.List;

public class CollectPointedDripstone extends CollectItem {

    private static final int COLLECT_POINTED_DRIPSTONE_MINIMUM = 1;
    private static final int COLLECT_POINTED_DRIPSTONE_MAXIMUM = 32;

    public CollectPointedDripstone() {
        super(Material.POINTED_DRIPSTONE, GoalDifficulty.MEDIUM, COLLECT_POINTED_DRIPSTONE_MINIMUM, COLLECT_POINTED_DRIPSTONE_MAXIMUM);

        this.canGenerateMultiple = false;
    }

    @Override
    protected IMutableGoal makeCollectItemGoal(Material item, int itemsRequiredPerStack) {
        IMutableGoal goal = super.makeCollectItemGoal(item, itemsRequiredPerStack);
        goal.setDescription("Collect " + itemsRequiredPerStack + " pointed dripstone");
        return goal;
    }
}

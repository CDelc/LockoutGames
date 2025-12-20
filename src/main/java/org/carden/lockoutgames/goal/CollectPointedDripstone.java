package org.carden.lockoutgames.goal;

import org.bukkit.Material;

public class CollectPointedDripstone extends CollectItemGoal {

    private static final int COLLECT_POINTED_DRIPSTONE_MINIMUM = 1;
    private static final int COLLECT_POINTED_DRIPSTONE_MAXIMUM = 32;

    protected CollectPointedDripstone() {
        super();
        construct(Material.POINTED_DRIPSTONE, COLLECT_POINTED_DRIPSTONE_MINIMUM, COLLECT_POINTED_DRIPSTONE_MAXIMUM);

        this.canGenerateMultiple = false;
        this.description = "Collect " + this.itemsRequiredPerStack + " pointed dripstone";
        this.goalDifficulty = GoalDifficulty.MEDIUM;
    }

}

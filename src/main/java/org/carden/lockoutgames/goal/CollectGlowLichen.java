package org.carden.lockoutgames.goal;

import org.bukkit.Material;

public class CollectGlowLichen extends CollectItemGoal {

    private static final int COLLECT_GLOW_LICKEN_MINIMUM = 1;
    private static final int COLLECT_GLOW_LICKEN_MAXIMUM = 64;

    protected CollectGlowLichen() {
        super();
        construct(Material.GLOW_LICHEN, COLLECT_GLOW_LICKEN_MINIMUM, COLLECT_GLOW_LICKEN_MAXIMUM);

        this.canGenerateMultiple = false;
        this.description = "Collect " + this.itemsRequiredPerStack + " glow lichen";
        this.goalDifficulty = GoalDifficulty.EASY;
    }

}

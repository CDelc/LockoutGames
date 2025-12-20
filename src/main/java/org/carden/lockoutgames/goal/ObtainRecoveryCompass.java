package org.carden.lockoutgames.goal;

import org.bukkit.Material;

public class ObtainRecoveryCompass extends CollectItemGoal {

    protected ObtainRecoveryCompass() {
        super();
        construct(Material.RECOVERY_COMPASS);
        this.canGenerateMultiple = false;
        this.description = "Obtain a recovery compass";
        this.goalDifficulty = GoalDifficulty.HARD;
        this.goalTypes.add(GoalType.ECHO_SHARD);
    }

}

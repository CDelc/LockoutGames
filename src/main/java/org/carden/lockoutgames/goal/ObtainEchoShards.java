package org.carden.lockoutgames.goal;

import org.bukkit.Material;

public class ObtainEchoShards extends CollectItemGoal{

    private static final int COLLECT_SHARD_MIN = 1;
    private static final int COLLECT_SHARD_MAX = 8;

    protected ObtainEchoShards() {
        super();
        construct(Material.ECHO_SHARD);
        this.canGenerateMultiple = false;
        this.description = "Obtain " + itemsRequiredPerStack + " echo shards";
        this.goalDifficulty = GoalDifficulty.HARD;
        this.goalTypes.add(GoalType.ECHO_SHARD);
    }

}

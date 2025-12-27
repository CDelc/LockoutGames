package org.carden.lockoutgames.goal.factory.instance;

import org.bukkit.Material;
import org.carden.lockoutgames.goal.GoalDifficulty;
import org.carden.lockoutgames.goal.GoalType;
import org.carden.lockoutgames.goal.IMutableGoal;
import org.carden.lockoutgames.goal.factory.base.CollectItem;

public class ObtainEchoShards extends CollectItem {

    private static final int COLLECT_SHARD_MIN = 1;
    private static final int COLLECT_SHARD_MAX = 8;

    public ObtainEchoShards() {
        super(Material.ECHO_SHARD, GoalDifficulty.HARD, COLLECT_SHARD_MIN, COLLECT_SHARD_MAX);
        this.canGenerateMultiple = false;
        this.addGoalTypes(GoalType.ECHO_SHARD);
    }

    @Override
    protected IMutableGoal makeCollectItemGoal(Material item, int itemsRequiredPerStack) {
        IMutableGoal goal = super.makeCollectItemGoal(item, itemsRequiredPerStack);
        goal.setDescription("Obtain " + itemsRequiredPerStack + " echo shards");
        return goal;
    }
}

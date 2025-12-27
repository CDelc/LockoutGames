package org.carden.lockoutgames.goal.factory.instance;

import org.bukkit.Material;
import org.carden.lockoutgames.goal.GoalDifficulty;
import org.carden.lockoutgames.goal.IMutableGoal;
import org.carden.lockoutgames.goal.factory.base.CollectItem;

import java.util.List;

public class ObtainMusicDisc5 extends CollectItem {

    public ObtainMusicDisc5() {
        super(Material.MUSIC_DISC_5);
        this.canGenerateMultiple = false;
    }

    @Override
    protected IMutableGoal makeCollectItemGoal(List<Material> requiredItems, int itemsRequiredPerStack) {
        IMutableGoal goal = super.makeCollectItemGoal(requiredItems, itemsRequiredPerStack);
        goal.setDescription("Obtain music disc 5");
        goal.setGoalDifficulty(GoalDifficulty.HARD);
        return goal;
    }
}

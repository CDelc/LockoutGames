package org.carden.lockoutgames.goal.factory;

import org.bukkit.Material;
import org.carden.lockoutgames.game.setting.SettingsImage;
import org.carden.lockoutgames.goal.GoalDifficulty;
import org.carden.lockoutgames.goal.IMutableGoal;

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

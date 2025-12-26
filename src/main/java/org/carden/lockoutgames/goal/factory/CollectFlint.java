package org.carden.lockoutgames.goal.factory;

import org.bukkit.Material;
import org.carden.lockoutgames.LockoutGames;
import org.carden.lockoutgames.game.setting.SettingsImage;
import org.carden.lockoutgames.goal.CollectItemGoal;
import org.carden.lockoutgames.goal.GoalDifficulty;
import org.carden.lockoutgames.goal.IMutableGoal;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class CollectFlint extends CollectItemBucketed {

    private static final int COLLECT_FLINT_MINIMUM = 1;
    private static final int COLLECT_FLINT_MAXIMUM = 64;
    private static final int EASY_DIFFICULTY_THRESHOLD = 4;

    public CollectFlint() {
        super(Set.of(Material.FLINT), 1, 1, Map.of(
                new Bucket(COLLECT_FLINT_MINIMUM, EASY_DIFFICULTY_THRESHOLD-1), GoalDifficulty.VERY_EASY,
                new Bucket(EASY_DIFFICULTY_THRESHOLD, COLLECT_FLINT_MAXIMUM), GoalDifficulty.EASY
        ));

        this.canGenerateMultiple = false;
    }

    @Override
    protected IMutableGoal makeGoalForItems(Set<Material> items, int stackSize, GoalDifficulty difficulty) {
        IMutableGoal g = super.makeGoalForItems(items, stackSize, difficulty);
        g.setDescription("Collect " + stackSize + " flint");
        return g;
    }
}

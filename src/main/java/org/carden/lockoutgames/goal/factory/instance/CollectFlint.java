package org.carden.lockoutgames.goal.factory.instance;

import org.bukkit.Material;
import org.carden.lockoutgames.goal.GoalDifficulty;
import org.carden.lockoutgames.goal.IMutableGoal;

import java.util.Map;
import java.util.Set;

public class CollectFlint extends CollectItemBucketed {

    private static final int COLLECT_FLINT_MINIMUM = 1;
    private static final int COLLECT_FLINT_MAXIMUM = 64;
    private static final int EASY_DIFFICULTY_THRESHOLD = 4;

    public CollectFlint() {
        super(Set.of(Material.FLINT), Map.of(
                new Bucket(COLLECT_FLINT_MINIMUM, EASY_DIFFICULTY_THRESHOLD-1), GoalDifficulty.VERY_EASY,
                new Bucket(EASY_DIFFICULTY_THRESHOLD, COLLECT_FLINT_MAXIMUM), GoalDifficulty.EASY
        ));

        this.canGenerateMultiple = false;
    }

    @Override
    protected IMutableGoal makeGoalForItem(Material item, int stackSize, GoalDifficulty difficulty) {
        IMutableGoal g = super.makeGoalForItem(item, stackSize, difficulty);
        g.setDescription("Collect " + stackSize + " flint");
        return g;
    }
}

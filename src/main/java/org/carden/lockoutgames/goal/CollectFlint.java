package org.carden.lockoutgames.goal;

import org.bukkit.Material;
import org.carden.lockoutgames.LockoutGames;

public class CollectFlint extends CollectItemGoal {

    private static final int COLLECT_FLINT_MINIMUM = 1;
    private static final int COLLECT_FLINT_MAXIMUM = 64;
    private static final int EASY_DIFFICULTY_THRESHOLD = 4;


    protected CollectFlint() {
        super();
        construct(Material.FLINT, COLLECT_FLINT_MINIMUM, COLLECT_FLINT_MAXIMUM);

        this.canGenerateMultiple = false;
        this.description = "Collect " + this.itemsRequiredPerStack + " flint";
        this.goalDifficulty = itemsRequiredPerStack < EASY_DIFFICULTY_THRESHOLD ? GoalDifficulty.VERY_EASY : GoalDifficulty.EASY;
    }

    @Override
    protected boolean forceDifficulty(GoalDifficulty goalDifficulty) {
        if(goalDifficulty.isEqualTo(this.goalDifficulty)) return true;
        else if(goalDifficulty.isEqualTo(GoalDifficulty.VERY_EASY)) {
            this.itemsRequiredPerStack = LockoutGames.getRng().nextInt(EASY_DIFFICULTY_THRESHOLD);
            return true;
        }
        else if(goalDifficulty.isEqualTo(GoalDifficulty.EASY)) {
            this.itemsRequiredPerStack = LockoutGames.getRng().nextInt(EASY_DIFFICULTY_THRESHOLD, COLLECT_FLINT_MAXIMUM);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public GoalDifficulty getGoalDifficulty() {
        this.goalDifficulty = itemsRequiredPerStack < EASY_DIFFICULTY_THRESHOLD ? GoalDifficulty.VERY_EASY : GoalDifficulty.EASY;
        return this.goalDifficulty;
    }

}

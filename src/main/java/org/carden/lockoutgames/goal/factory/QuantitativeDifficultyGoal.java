package org.carden.lockoutgames.goal.factory;

import org.carden.lockoutgames.LockoutGames;
import org.carden.lockoutgames.goal.GoalDifficulty;
import org.carden.lockoutgames.goal.IMutableGoal;

import java.util.Map;
import java.util.Random;
import java.util.Set;

public abstract class QuantitativeDifficultyGoal extends SelectFromChoices<QuantitativeDifficultyGoal.Bucket> {

    protected QuantitativeDifficultyGoal(Set<Bucket> choices, GoalDifficulty difficulty) {
        super(choices, difficulty);
    }

    protected QuantitativeDifficultyGoal(Map<Bucket, GoalDifficulty> choices) {
        super(choices);
    }

    @Override
    protected final IMutableGoal makeGoalFor(Bucket bucket, GoalDifficulty difficulty) {
        Random rng = LockoutGames.getRng();
        return this.makeGoalForNumber(bucket.getRandom(rng), difficulty);
    }

    protected abstract IMutableGoal makeGoalForNumber(int n, GoalDifficulty difficulty);

    public record Bucket(int min, int max) {
        public boolean contains(int n) {
            return n >= min && n <= max;
        }

        public int getRandom(Random rng) {
            return rng.nextInt(min, max+1);
        }
    }
}

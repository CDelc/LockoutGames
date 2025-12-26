package org.carden.lockoutgames.goal.factory;

import org.carden.lockoutgames.LockoutGames;
import org.carden.lockoutgames.game.setting.SettingsImage;
import org.carden.lockoutgames.goal.Goal;
import org.carden.lockoutgames.goal.GoalDifficulty;
import org.carden.lockoutgames.goal.IGoal;
import org.carden.lockoutgames.goal.IMutableGoal;

import java.util.List;
import java.util.Random;
import java.util.Set;

public abstract class AllOrOneFactory<E> extends BaseGoalFactory {
    private float allProbablility = 0.5f;
    private final GoalDifficulty difficultyOne;
    private final GoalDifficulty difficultyAll;
    private final Set<E> possibleValues;

    protected AllOrOneFactory(Set<E> possibleValues, GoalDifficulty difficultyOne, GoalDifficulty difficultyAll) {
        this.difficultyOne = difficultyOne;
        this.difficultyAll = difficultyAll;
        this.possibleValues = possibleValues;
    }

    @Override
    protected final IMutableGoal makeGoalHook() {
        if (this.generateAll()) {
            return this.makeAll(this.possibleValues);
        }
        else {
            Random rng = LockoutGames.getRng();
            E value = List.copyOf(possibleValues).get(rng.nextInt(possibleValues.size()));
            return this.makeOne(value);
        }
    }

    protected abstract IMutableGoal makeOne(E value);

    protected abstract IMutableGoal makeAll(Set<E> values);

    protected void setAllProbablility(float probability) {
        this.allProbablility = Math.clamp(probability, 0, 1);
    }

    private boolean generateAll() {
        Random rng = LockoutGames.getRng();
        Set<E> filteredValues = filteredChoices();
        if (this.isValidDifficulty(this.difficultyOne) && this.isValidDifficulty(this.difficultyAll)) {
            return !filteredValues.equals(this.possibleValues) && rng.nextDouble() < this.allProbablility;
        }
        else if (this.isValidDifficulty(difficultyOne)) {
            return false;
        }
        else if (this.isValidDifficulty(difficultyAll)) {
            return !filteredValues.equals(this.possibleValues);
        }
        else {
            throw new IllegalStateException("No valid difficulties to generate");
        }
    }

    @Override
    protected boolean canGenerateGoalHook() {
        return !this.filteredChoices().isEmpty() && (this.isValidDifficulty(this.difficultyOne) || this.isValidDifficulty(this.difficultyAll));
    }

    /**
     * Override to filter choices to what is allowed for the game. If the filtered results do not allow
     * all values, then the "all" goal will not be generated.
     * @return available choices
     */
    protected Set<E> filteredChoices() {
        return possibleValues;
    }
}

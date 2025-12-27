package org.carden.lockoutgames.goal.factory.base;

import org.carden.lockoutgames.LockoutGames;
import org.carden.lockoutgames.goal.GoalDifficulty;
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
        if (this.isValidDifficulty(this.difficultyAll) && this.isAllValues(this.filteredChoices())) {
            double rand = rng.nextDouble();
            return rand < this.allProbablility;
        }
        else {
            return false;
        }
    }

    @Override
    protected boolean canGenerateGoalHook() {
        Set<E> filteredChoices = this.filteredChoices();
        return (!filteredChoices.isEmpty() && this.isValidDifficulty(this.difficultyOne)) ||
                (this.isValidDifficulty(this.difficultyAll) && this.isAllValues(filteredChoices));
    }

    /**
     * Override to filter choices to what is allowed for the game. If the filtered results do not allow
     * all values, then the "all" goal will not be generated.
     * @return available choices
     */
    protected Set<E> filteredChoices() {
        return possibleValues;
    }

    protected boolean isAllValues(Set<E> filteredValues) {
        return filteredValues.size() == this.possibleValues.size();
    }
}

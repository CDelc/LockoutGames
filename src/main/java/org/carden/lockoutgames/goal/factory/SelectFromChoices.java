package org.carden.lockoutgames.goal.factory;

import org.carden.lockoutgames.LockoutGames;
import org.carden.lockoutgames.goal.GoalDifficulty;
import org.carden.lockoutgames.goal.IMutableGoal;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class SelectFromChoices<E> extends BaseGoalFactory {
    private final Map<E, GoalDifficulty> choices;

    protected SelectFromChoices(Map<E, GoalDifficulty> choices) {
        this.choices = choices;
    }

    protected SelectFromChoices(Set<E> choices, GoalDifficulty difficulty) {
        this.choices = choices.stream().collect(Collectors.toMap((e) -> e, (e) -> difficulty));
    }

    @Override
    protected final IMutableGoal makeGoalHook() {
        E value = selectRandomFrom(this.filteredChoices());
        return this.makeGoalFor(value, this.choices.get(value));
    }

    protected abstract IMutableGoal makeGoalFor(E value, GoalDifficulty difficulty);

    @Override
    protected boolean canGenerateGoalHook() {
        return !this.filteredChoices().isEmpty();
    }

    /**
     * Subclasses can further filter choices by overriding.
     * @return choices that may be selected for the game
     */
    protected Set<E> filteredChoices() {
        return this.choices.keySet().stream().filter((value) -> this.isValidDifficulty(this.choices.get(value))).collect(Collectors.toSet());
    }

    private E selectRandomFrom(Set<E> choices) {
        Random rng = LockoutGames.getRng();
        return List.copyOf(choices).get(rng.nextInt(choices.size()));
    }
}

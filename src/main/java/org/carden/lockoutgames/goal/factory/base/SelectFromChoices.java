package org.carden.lockoutgames.goal.factory.base;

import org.carden.lockoutgames.LockoutGames;
import org.carden.lockoutgames.goal.GoalDifficulty;
import org.carden.lockoutgames.goal.IMutableGoal;
import org.carden.lockoutgames.goal.factory.selector.MapSelector;
import org.carden.lockoutgames.goal.factory.selector.SingleSelector;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class SelectFromChoices<E> extends BaseGoalFactory {
    private final Map<E, GoalDifficulty> difficultyMap;
    private final MapSelector<E, GoalDifficulty> selector;

    protected SelectFromChoices(Map<E, GoalDifficulty> choices, Predicate<E> choiceFilter) {
        this.difficultyMap = choices;
        this.selector = new MapSelector<>(choices, choiceFilter, this::isValidDifficulty);
    }

    protected SelectFromChoices(Set<E> choices, GoalDifficulty difficulty, Predicate<E> choiceFilter) {
        this(
                choices.stream().collect(Collectors.toMap((e) -> e, (e) -> difficulty)),
                choiceFilter
        );
    }

    protected SelectFromChoices(Map<E, GoalDifficulty> choices) {
        this(choices, (x) -> true);
    }

    protected SelectFromChoices(Set<E> choices, GoalDifficulty difficulty) {
        this(choices.stream().collect(Collectors.toMap((e) -> e, (e) -> difficulty)));
    }

    @Override
    protected final IMutableGoal makeGoalHook() {
        Map.Entry<E, GoalDifficulty> target = this.selector.select();
        return this.makeGoalFor(target.getKey(), target.getValue());
    }

    protected abstract IMutableGoal makeGoalFor(E value, GoalDifficulty difficulty);

    @Override
    protected boolean canGenerateGoalHook() {
        return this.selector.canSelect();
    }
}

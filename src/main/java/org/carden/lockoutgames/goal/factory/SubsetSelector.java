package org.carden.lockoutgames.goal.factory;

import org.carden.lockoutgames.LockoutGames;
import org.carden.lockoutgames.goal.GoalDifficulty;
import org.carden.lockoutgames.goal.IMutableGoal;
import org.carden.lockoutgames.utils.Utils;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class SubsetSelector<E> {
    private final Set<E> choices;
    private final int minSize;
    private final int maxSize;
    private final Predicate<E> filter;

    public SubsetSelector(Set<E> choices, int minSize, int maxSize, Predicate<E> filter) {
        this.choices = choices;
        this.minSize = minSize;
        this.maxSize = maxSize;
        this.filter = filter;
    }

    public Set<E> select() {
        Random rng = LockoutGames.getRng();
        int maxSize = Math.min(this.maxSize + 1, this.choices.size());
        int subsetSize = rng.nextInt(this.minSize, maxSize);
        return Set.copyOf(Utils.selectNRandomValuesFromList(List.copyOf(choices), subsetSize, rng));
    }

    protected boolean canSelectSubset() {
        return !this.filteredChoices().isEmpty();
    }

    /**
     * Subclasses can further filter choices by overriding.
     * @return choices that may be selected for the game
     */
    protected Set<E> filteredChoices() {
        return this.choices.stream().filter(this.filter).collect(Collectors.toSet());
    }


}

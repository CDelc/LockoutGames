package org.carden.lockoutgames.goal.factory.selector;

import org.carden.lockoutgames.LockoutGames;
import org.carden.lockoutgames.utils.Utils;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class SubsetSelector<E> implements Selector<Set<E>> {
    private final Set<E> choices;
    private final int minSize;
    private final int maxSize;
    private final Predicate<E> filter;

    public SubsetSelector(Set<E> choices, int minSize, int maxSize, Predicate<E> filter) {
        this.choices = choices;
        this.minSize = Math.max(2, minSize);
        this.maxSize = Math.min(choices.size(), maxSize);
        this.filter = filter;
    }

    public Set<E> select() {
        Random rng = LockoutGames.getRng();
        int subsetSize = rng.nextInt(this.minSize, this.maxSize+1);
        return Set.copyOf(Utils.selectNRandomValuesFromList(List.copyOf(choices), subsetSize, rng));
    }

    public boolean canSelect() {
        return !this.filteredChoices().isEmpty();
    }

    /**
     * Subclasses can further filter choices by overriding.
     * @return choices that may be selected for the game
     */
    public Set<E> filteredChoices() {
        return this.choices.stream().filter(this.filter).collect(Collectors.toSet());
    }


}

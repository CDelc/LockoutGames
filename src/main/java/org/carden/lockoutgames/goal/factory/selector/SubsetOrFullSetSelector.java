package org.carden.lockoutgames.goal.factory.selector;

import org.carden.lockoutgames.LockoutGames;

import java.util.Random;
import java.util.Set;
import java.util.function.Predicate;

public class SubsetOrFullSetSelector<E> implements Selector<Set<E>> {
    private final Set<E> fullSet;
    private final SubsetSelector<E> subsetSelector;
    private final double fullSetChance;
    private final Predicate<E> filter;

    public SubsetOrFullSetSelector(Set<E> fullSet, int minSubsetSize, int maxSubsetSize, int subsetPercentChance, Predicate<E> filter) {
        this.fullSet = fullSet;
        this.subsetSelector = new SubsetSelector<>(fullSet, minSubsetSize, maxSubsetSize, filter);
        this.fullSetChance = 1 - Math.clamp(subsetPercentChance/100.0, 0, 1);
        this.filter = filter;
    }

    @Override
    public Set<E> select() {
        Random rng = LockoutGames.getRng();
        if (this.canSelectFullSet() && rng.nextDouble() < this.fullSetChance) {
            return fullSet;
        }
        else {
            return this.subsetSelector.select();
        }
    }

    @Override
    public boolean canSelect() {
        return this.subsetSelector.canSelect();
    }

    private boolean canSelectFullSet() {
        return this.fullSet.stream().allMatch(filter);
    }

    public boolean isFullSet(Set<E> set) {
        return set.equals(this.fullSet);
    }
}

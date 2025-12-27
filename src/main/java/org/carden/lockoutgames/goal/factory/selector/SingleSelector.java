package org.carden.lockoutgames.goal.factory.selector;

import org.carden.lockoutgames.LockoutGames;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public record SingleSelector<E>(Set<E> choices, Predicate<E> filter) implements Selector<E> {
    @Override
    public E select() {
        Set<E> filteredValues = this.filteredValues();
        Random rng = LockoutGames.getRng();
        return List.copyOf(filteredValues).get(rng.nextInt(filteredValues.size()));
    }

    @Override
    public boolean canSelect() {
        return !this.filteredValues().isEmpty();
    }

    private Set<E> filteredValues() {
        return this.choices.stream().filter(filter).collect(Collectors.toSet());
    }
}

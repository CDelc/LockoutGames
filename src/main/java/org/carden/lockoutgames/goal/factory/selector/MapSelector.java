package org.carden.lockoutgames.goal.factory.selector;

import org.carden.lockoutgames.LockoutGames;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Predicate;

public class MapSelector<K, V> implements Selector<Map.Entry<K, V>> {
    private final Map<K, V> choices;
    private final Predicate<K> keyFilter;
    private final Predicate<V> valueFilter;

    public MapSelector(Map<K, V> choices, Predicate<K> keyFilter, Predicate<V> valueFilter) {
        this.choices = choices;
        this.keyFilter = keyFilter;
        this.valueFilter = valueFilter;
    }

    public Map.Entry<K, V> select() {
        List<Map.Entry<K, V>> filteredEntries = this.choices.entrySet().stream().filter(this::entryFilter).toList();
        return selectItemFromList(filteredEntries);
    }

    public boolean canSelect() {
        return this.choices.entrySet().stream().anyMatch(this::entryFilter);
    }

    private boolean entryFilter(Map.Entry<K, V> entry) {
        return keyFilter.test(entry.getKey()) && valueFilter.test(entry.getValue());
    }

    private static <E> E selectItemFromList(List<E> list) {
        Random rng = LockoutGames.getRng();
        return list.get(rng.nextInt(list.size()));
    }
}

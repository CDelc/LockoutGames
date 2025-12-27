package org.carden.lockoutgames.goal.factory;

import org.bukkit.Material;
import org.carden.lockoutgames.info.WorldRequirements;

import java.util.Set;
import java.util.function.Predicate;
import java.util.function.Supplier;

public final class Filters {
    public static boolean itemAvailable(Material item) {
        return WorldRequirements.checkElement(item.name());
    }

    public static Predicate<Material> singleItemFilter(Supplier<Set<String>> uniquenessStrings) {
        return (item) -> Filters.itemAvailable(item) && uniquenessStrings.get().contains(UniquenessStrings.collect(item));
    }
}

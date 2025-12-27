package org.carden.lockoutgames.goal.factory;

import org.bukkit.Material;

public final class UniquenessStrings {

    public static String collect(Material item) {
        return "collect" + item.name();
    }
}

package org.carden.lockoutgames.goal.factory.selector;

public interface Selector<T> {
    T select();

    boolean canSelect();
}

package org.carden.lockoutgames.goal;

import org.bukkit.event.Event;

import java.util.Set;

public abstract class SingleEventGoal<E extends Event> extends RegularGoal {
    private final Class<E> eventClass;

    protected SingleEventGoal(Class<E> eventClass) {
        super(Set.of(eventClass));
        this.eventClass = eventClass;
    }

    @Override
    protected final void checkEvent(Event e) {
        if (this.eventClass.isInstance(e)) {
            this.checkSpecificEvent(this.eventClass.cast(e));
        }
    }

    protected abstract void checkSpecificEvent(E event);
}

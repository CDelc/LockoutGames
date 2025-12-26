package org.carden.lockoutgames.goal;

import org.bukkit.event.Event;

import java.util.Set;

public abstract class SingleEventGoal<E extends Event> extends RegularGoal {
    private final Class<E> eventClass;

    protected SingleEventGoal(Class<E> eventClass, GoalDifficulty difficulty, String description, Set<GoalType> goalTypes, Set<String> uniquenessStrings) {
        super(Set.of(eventClass), difficulty, description, goalTypes, uniquenessStrings);
        this.eventClass = eventClass;
    }

    protected SingleEventGoal(Class<E> eventClass, GoalDifficulty difficulty, String description) {
        super(Set.of(eventClass), difficulty, description);
        this.eventClass = eventClass;
    }

    protected SingleEventGoal(Class<E> eventClass) {
        super(Set.of(eventClass));
        this.eventClass = eventClass;
    }

    @Override
    public final void checkEvent(Event e) {
        if (this.eventClass.isInstance(e)) {
            this.checkSpecificEvent(this.eventClass.cast(e));
        }
    }

    protected abstract void checkSpecificEvent(E event);
}

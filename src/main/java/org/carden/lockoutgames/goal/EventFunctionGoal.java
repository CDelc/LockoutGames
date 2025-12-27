package org.carden.lockoutgames.goal;

import org.bukkit.event.Event;
import org.carden.lockoutgames.game.player.GamePlayer;

import java.util.Set;
import java.util.function.Function;

public class EventFunctionGoal<E extends Event> extends SingleEventGoal<E> {
    private final Function<E, Set<GamePlayer>> completingPlayersFunction;

    public EventFunctionGoal(Class<E> eventClass, Function<E, Set<GamePlayer>> completingPlayersFunction, GoalDifficulty difficulty, String description) {
        super(eventClass);
        this.completingPlayersFunction = completingPlayersFunction;
        this.goalDifficulty = difficulty;
        this.description = description;
    }

    @Override
    protected void checkSpecificEvent(E event) {
        for (GamePlayer player : this.completingPlayersFunction.apply(event)) {
            this.fireGoalCompleteEvent(player);
        }
    }
}

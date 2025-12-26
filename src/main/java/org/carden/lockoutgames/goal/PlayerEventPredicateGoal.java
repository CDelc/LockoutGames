package org.carden.lockoutgames.goal;

import org.bukkit.event.player.PlayerEvent;
import org.carden.lockoutgames.game.player.GamePlayer;
import org.carden.lockoutgames.game.player.PlayerManager;

import java.util.function.Predicate;

public class PlayerEventPredicateGoal<E extends PlayerEvent> extends SingleEventGoal<E> {
    private final Predicate<E> predicate;

    public PlayerEventPredicateGoal(Class<E> eventClass, Predicate<E> predicate, GoalDifficulty difficulty, String description) {
        super(eventClass);
        this.predicate = predicate;
        this.goalDifficulty = difficulty;
        this.description = description;
    }

    @Override
    protected void checkSpecificEvent(E event) {
        if (this.predicate.test(event)) {
            GamePlayer gamePlayer = PlayerManager.getPlayerManager().getGamePlayerObject(event.getPlayer().getUniqueId());
            fireGoalCompleteEvent(gamePlayer);
        }
    }
}

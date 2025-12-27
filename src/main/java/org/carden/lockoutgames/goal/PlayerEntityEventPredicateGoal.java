package org.carden.lockoutgames.goal;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityEvent;
import org.carden.lockoutgames.game.player.GamePlayer;
import org.carden.lockoutgames.game.player.PlayerManager;
import org.carden.lockoutgames.utils.Utils;

import java.util.function.Predicate;

public class PlayerEntityEventPredicateGoal<E extends EntityEvent> extends SingleEventGoal<E> {

    private final Predicate<E> predicate;

    public PlayerEntityEventPredicateGoal(Class<E> eventClass, Predicate<E> predicate, GoalDifficulty difficulty, String description) {
        super(eventClass);
        this.predicate = predicate;
        this.goalDifficulty = difficulty;
        this.description = description;
    }

    @Override
    protected void checkSpecificEvent(E event) {
        Player p = Utils.entityEventPlayer(event);
        if (p == null) return;
        if (this.predicate.test(event)) {
            GamePlayer gamePlayer = PlayerManager.getPlayerManager().getGamePlayerObject(p.getUniqueId());
            fireGoalCompleteEvent(gamePlayer);
        }
    }
}

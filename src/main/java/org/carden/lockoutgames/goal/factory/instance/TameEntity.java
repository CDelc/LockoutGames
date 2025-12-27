package org.carden.lockoutgames.goal.factory.instance;

import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.EntityTameEvent;
import org.carden.lockoutgames.LockoutGames;
import org.carden.lockoutgames.game.player.GamePlayer;
import org.carden.lockoutgames.game.player.PlayerManager;
import org.carden.lockoutgames.goal.*;
import org.carden.lockoutgames.goal.factory.base.SelectFromChoices;

import java.util.*;

public final class TameEntity extends SelectFromChoices<EntityType> {
    private static final Map<EntityType, GoalDifficulty> tamingMap = Map.ofEntries(
            new AbstractMap.SimpleEntry<>(EntityType.HORSE, GoalDifficulty.EASY),
            new AbstractMap.SimpleEntry<>(EntityType.DONKEY, GoalDifficulty.EASY),
            new AbstractMap.SimpleEntry<>(EntityType.MULE, GoalDifficulty.HARD),
            new AbstractMap.SimpleEntry<>(EntityType.WOLF, GoalDifficulty.EASY),
            new AbstractMap.SimpleEntry<>(EntityType.CAT, GoalDifficulty.EASY),
            new AbstractMap.SimpleEntry<>(EntityType.PARROT, GoalDifficulty.EASY),
            new AbstractMap.SimpleEntry<>(EntityType.LLAMA, GoalDifficulty.EASY)
    );

    public TameEntity() {
        super(tamingMap);
        this.addGoalTypes(GoalType.TAME_MOB);
        this.canGenerateMultiple = false;
    }

    @Override
    protected IMutableGoal makeGoalFor(final EntityType entity, GoalDifficulty difficulty) {
        return new EventFunctionGoal<>(
                EntityTameEvent.class,
                (event) -> {
                    if (event.getEntity().getType() == entity) {
                        GamePlayer gamePlayer = PlayerManager.getPlayerManager().getGamePlayerObject(event.getOwner().getUniqueId());
                        return Set.of(gamePlayer);
                    } else return Set.of();
                },
                difficulty, String.format("Tame a %s", entity));
    }
}

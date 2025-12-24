package org.carden.lockoutgames.goal;

import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.EntityTameEvent;
import org.carden.lockoutgames.LockoutGames;
import org.carden.lockoutgames.game.player.GamePlayer;
import org.carden.lockoutgames.game.player.PlayerManager;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public final class TameEntity extends SingleEventGoal<EntityTameEvent> {
    private EntityType entity;
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
        super(EntityTameEvent.class);
        this.goalTypes.add(GoalType.TAME_MOB);
        this.canGenerateMultiple = false;
        setupGoal();
    }

    private void setupGoal() {
        Random rng = LockoutGames.getRng();
        this.entity = List.copyOf(tamingMap.keySet()).get(rng.nextInt(tamingMap.size()));
        this.goalDifficulty = tamingMap.get(this.entity);
        this.description = String.format("Tame a %s", this.entity);
        this.failedToGenerate = false;
    }

    @Override
    protected void checkSpecificEvent(EntityTameEvent event) {
        if (event.getEntity().getType() == this.entity) {
            GamePlayer gamePlayer = PlayerManager.getPlayerManager().getGamePlayerObject(event.getOwner().getUniqueId());
            if (gamePlayer != null) {
                this.fireGoalCompleteEvent(gamePlayer);
            }
        }
    }
}

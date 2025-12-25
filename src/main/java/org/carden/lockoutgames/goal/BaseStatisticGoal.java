package org.carden.lockoutgames.goal;

import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerStatisticIncrementEvent;
import org.carden.lockoutgames.game.player.GamePlayer;
import org.carden.lockoutgames.game.player.PlayerManager;

public sealed abstract class BaseStatisticGoal extends SingleEventGoal<PlayerStatisticIncrementEvent> permits EntityStatisticGoal, GeneralStatisticGoal, MaterialStatisticGoal {
    protected final Statistic statistic;
    protected final int requiredValue;

    public BaseStatisticGoal(Statistic stat, int requiredValue) {
        super(PlayerStatisticIncrementEvent.class);
        this.statistic = stat;
        this.requiredValue = requiredValue;
    }

    @Override
    protected final void checkSpecificEvent(PlayerStatisticIncrementEvent event) {
        Player p = event.getPlayer();
        GamePlayer gamePlayer = PlayerManager.getPlayerManager().getGamePlayerObject(p.getUniqueId());
        if (this.isStatisticRelevant(event) && event.getNewValue() >= this.requiredValue) {
            this.fireGoalCompleteEvent(gamePlayer);
        }
    }

    /**
     * Whether the event statistic is relevant to this goal.
     *
     * @param event
     * @return event relevance
     */
    protected abstract boolean isStatisticRelevant(PlayerStatisticIncrementEvent event);
}

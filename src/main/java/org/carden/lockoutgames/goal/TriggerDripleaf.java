package org.carden.lockoutgames.goal;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.carden.lockoutgames.game.player.GamePlayer;
import org.carden.lockoutgames.game.player.PlayerManager;

import static org.carden.lockoutgames.utils.Utils.playerEventPlayer;

public final class TriggerDripleaf extends SingleEventGoal<EntityChangeBlockEvent> {
    public TriggerDripleaf() {
        super(EntityChangeBlockEvent.class);
        this.goalDifficulty = GoalDifficulty.EASY;
        this.description = "Jump on a big dripleaf";
    }

    @Override
    protected void checkSpecificEvent(EntityChangeBlockEvent event) {
        if (event.getBlock().getType() != Material.BIG_DRIPLEAF) { return; }
        Player p = playerEventPlayer(event);
        if (p == null) { return; }
        GamePlayer gamePlayer = PlayerManager.getPlayerManager().getGamePlayerObject(p.getUniqueId());
        this.fireGoalCompleteEvent(gamePlayer);
    }
}

package org.carden.lockoutgames.goal;

import org.bukkit.Material;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.carden.lockoutgames.game.player.GamePlayer;
import org.carden.lockoutgames.game.player.PlayerManager;

public final class ConsumeItem extends SingleEventGoal<PlayerItemConsumeEvent> {
    private final Material item;

    public ConsumeItem(Material item) {
        super(PlayerItemConsumeEvent.class);
        this.item = item;
    }

    @Override
    protected void checkSpecificEvent(PlayerItemConsumeEvent event) {
        if (event.getItem().getType() == this.item) {
            GamePlayer gamePlayer = PlayerManager.getPlayerManager().getGamePlayerObject(event.getPlayer().getUniqueId());
            this.fireGoalCompleteEvent(gamePlayer);
        }
    }
}

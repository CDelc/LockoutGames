package org.carden.lockoutgames.goal;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockFertilizeEvent;
import org.carden.lockoutgames.game.player.GamePlayer;
import org.carden.lockoutgames.game.player.PlayerManager;

import java.util.Set;

public class FertilizeBlock extends SingleEventGoal<BlockFertilizeEvent> {
    private final Set<Material> blockTypes;

    /**
     * Create a goal that is completed if any of the blockTypes are fertilized.
     * @param blockTypes
     */
    public FertilizeBlock(Set<Material> blockTypes) {
        super(BlockFertilizeEvent.class);
        this.blockTypes = blockTypes;
    }

    public FertilizeBlock(Material blockType) {
        this(Set.of(blockType));
    }

    @Override
    public void checkSpecificEvent(BlockFertilizeEvent event) {
        Player p = event.getPlayer();
        Material fertilizedType = event.getBlock().getType();
        if (p == null || !blockTypes.contains(fertilizedType)) return;
        GamePlayer gamePlayer = PlayerManager.getPlayerManager().getGamePlayerObject(p.getUniqueId());
        this.fireGoalCompleteEvent(gamePlayer);
    }
}

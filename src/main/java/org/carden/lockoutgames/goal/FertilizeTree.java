package org.carden.lockoutgames.goal;

import org.bukkit.Material;
import org.bukkit.event.block.BlockFertilizeEvent;

import java.util.Set;

public class FertilizeTree extends FertilizeBlock {
    public FertilizeTree(Set<Material> blockTypes) {
        super(blockTypes);
    }

    public FertilizeTree(Material blockType) {
        super(blockType);
    }

    @Override
    public void checkSpecificEvent(BlockFertilizeEvent event) {
        // Avoids goal completion from advancing growth stage
        if (event.getBlocks().size() > 1) {
            super.checkSpecificEvent(event);
        }
    }
}

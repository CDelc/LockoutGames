package org.carden.lockoutgames.goal;

import org.bukkit.Material;

public class ObtainMusicDisc5 extends CollectItemGoal {

    protected ObtainMusicDisc5() {
        super();
        construct(Material.MUSIC_DISC_5);
        this.canGenerateMultiple = false;
        this.description = "Obtain music disc 5";
        this.goalDifficulty = GoalDifficulty.HARD;
    }

}

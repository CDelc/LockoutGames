package org.carden.lockoutgames.goal.factory;

import org.bukkit.event.player.PlayerMoveEvent;
import org.carden.lockoutgames.game.setting.SettingsImage;
import org.carden.lockoutgames.goal.GoalDifficulty;
import org.carden.lockoutgames.goal.IMutableGoal;
import org.carden.lockoutgames.goal.PlayerEventPredicateGoal;

public class LightLevelZero extends BaseGoalFactory {
    private static final String description = "Reach light level 0";
    private static final GoalDifficulty difficulty = GoalDifficulty.VERY_EASY;

    protected LightLevelZero(SettingsImage settings) {
        super(settings);
    }

    @Override
    protected IMutableGoal makeGoalHook() {
        return new PlayerEventPredicateGoal<>(PlayerMoveEvent.class, (event) ->
                event.getPlayer().getLocation().getBlock().getLightLevel() == 0
        , difficulty, description);
    }

    @Override
    protected boolean canGenerateGoalHook() {
        return this.isValidDifficulty(difficulty);
    }
}

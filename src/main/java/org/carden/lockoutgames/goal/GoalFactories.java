package org.carden.lockoutgames.goal;

import org.carden.lockoutgames.goal.factory.*;

public enum GoalFactories {

    LIGHT_LEVEL_ZERO(new LightLevelZero()),
    VISIT_CAVE_BIOMES(new VisitCaveBiomes()),
    VISIT_NETHER_BIOMES(new VisitNetherBiomes()),
    VISIT_END_BIOMES(new VisitEndBiomes());

    private final GoalFactory goalFactory;
    GoalFactories(GoalFactory goalFactory) {
        this.goalFactory = goalFactory;
    }

    public GoalFactory getFactory() {
        return this.goalFactory;
    }

}

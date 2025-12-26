package org.carden.lockoutgames.goal;

import org.carden.lockoutgames.goal.factory.*;

public enum GoalFactories {

    LIGHT_LEVEL_ZERO(LightLevelZero.class),
    VISIT_CAVE_BIOMES(VisitCaveBiomes.class),
    VISIT_NETHER_BIOMES(VisitNetherBiomes.class),
    VISIT_END_BIOMES(VisitEndBiomes.class);

    public final Class<? extends GoalFactory> classObject;
    GoalFactories(Class<? extends GoalFactory> goalClass) {
        this.classObject = goalClass;
    }

}

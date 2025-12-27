package org.carden.lockoutgames.goal;

import org.carden.lockoutgames.goal.factory.GoalFactory;
import org.carden.lockoutgames.goal.factory.instance.*;

public enum GoalFactories {

    LIGHT_LEVEL_ZERO(new LightLevelZero()),
    VISIT_CAVE_BIOMES(new VisitCaveBiomes()),
    VISIT_NETHER_BIOMES(new VisitNetherBiomes()),
    VISIT_END_BIOMES(new VisitEndBiomes()),
    COLLECT_FLINT(new CollectFlint()),
    COLLECT_GLOW_LICHEN(new CollectGlowLichen()),
    COLLECT_POINTED_DRIPSTONE(new CollectPointedDripstone()),
    OBTAIN_CAVE_BLOCKS_MULTIPLE(new ObtainCaveBlocksMultiple()),
    OBTAIN_CAVE_BLOCKS_SINGLE(new ObtainCaveBlocksSingle()),
    OBTAIN_LUSH_MULTIPLE(new ObtainLushMultiple()),
    OBTAIN_LUSH_SINGLE(new ObtainLushSingle()),
    OBTAIN_MUSIC_DISK_5(new ObtainMusicDisc5()),
    OBTAIN_RECOVERY_COMPASS(new ObtainRecoveryCompass())
    ;


    private final GoalFactory goalFactory;
    GoalFactories(GoalFactory goalFactory) {
        this.goalFactory = goalFactory;
    }

    public GoalFactory getFactory() {
        return this.goalFactory;
    }

}

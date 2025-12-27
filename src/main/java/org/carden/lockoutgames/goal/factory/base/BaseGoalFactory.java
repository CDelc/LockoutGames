package org.carden.lockoutgames.goal.factory.base;

import org.carden.lockoutgames.game.setting.SettingsImage;
import org.carden.lockoutgames.goal.*;
import org.carden.lockoutgames.goal.factory.GoalFactory;

import java.util.*;
import java.util.stream.Collectors;

public abstract class BaseGoalFactory implements GoalFactory {
    private SettingsImage settings;
    private Set<String> usedUniquenessStrings = Set.of();
    private final Set<String> baseUniquenessStrings = new HashSet<>();

    protected boolean canGenerateMultiple = false;
    protected GoalDifficulty minDifficulty = GoalDifficulty.VERY_EASY;
    protected GoalDifficulty maxDifficulty = GoalDifficulty.VERY_HARD;

    @Override
    public final IGoal makeGoal(SettingsImage settings, List<String> uniquenessStrings) throws IllegalStateException{
        if (this.canGenerateGoal(settings, uniquenessStrings)) {
            IMutableGoal goal = this.makeGoalHook();
            goal.addUniquenessStrings(this.baseUniquenessStrings);
            return goal;
        }
        else {
            throw new IllegalStateException("canGenerateGoal is false");
        }
    }

    private void setupMakeGoalContext(SettingsImage settings, List<String> uniquenessStrings) {
        this.settings = settings;
        this.usedUniquenessStrings = Set.copyOf(uniquenessStrings);
    }

    protected abstract IMutableGoal makeGoalHook();

    @Override
    public final boolean canGenerateGoal(SettingsImage settings, List<String> uniquenessStrings) {
        this.setupMakeGoalContext(settings, uniquenessStrings);
        if (!invalidGoalType()) {
            return this.canGenerateGoalHook();
        }
        return false;
    }

    public boolean canGenerateMultiple() {
        return this.canGenerateMultiple;
    }

    private boolean invalidGoalType() {
        return this.usedUniquenessStrings.stream().anyMatch((gt) -> this.baseUniquenessStrings.contains(gt));
    }

    protected final boolean isValidDifficulty(GoalDifficulty difficulty) {
        return difficulty.isInRange(this.minDifficulty, this.maxDifficulty);
    }

    public final GoalFactory setMinDifficulty(GoalDifficulty difficulty) {
        this.minDifficulty = difficulty;
        return this;
    }

    public final GoalFactory setMaxDifficulty(GoalDifficulty difficulty) {
        this.maxDifficulty = difficulty;
        return this;
    }

    public final GoalFactory setFixedDifficulty(GoalDifficulty difficulty) {
        this.minDifficulty = difficulty;
        this.maxDifficulty = difficulty;
        return this;
    }

    public final GoalFactory setNoDifficulty() {
        this.minDifficulty = GoalDifficulty.VERY_EASY;
        this.maxDifficulty = GoalDifficulty.VERY_HARD;
        return this;
    }

    protected abstract boolean canGenerateGoalHook();

    protected void addGoalTypes(GoalType... goalTypes) {
        this.baseUniquenessStrings.addAll(Arrays.stream(goalTypes).map(Objects::toString).collect(Collectors.toSet()));
    }

    protected Set<String> usedUniquenessStrings() {
        return this.usedUniquenessStrings;
    }

    protected SettingsImage settings() {
        return this.settings;
    }
}

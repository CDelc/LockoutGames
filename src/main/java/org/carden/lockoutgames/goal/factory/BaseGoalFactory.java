package org.carden.lockoutgames.goal.factory;

import org.carden.lockoutgames.game.setting.SettingsImage;
import org.carden.lockoutgames.goal.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class BaseGoalFactory implements GoalFactory {
    protected final SettingsImage settings;

    protected GoalDifficulty minDifficulty = GoalDifficulty.VERY_EASY;
    protected GoalDifficulty maxDifficulty = GoalDifficulty.VERY_HARD;
    protected Collection<GoalType> usedGoalTypes = Set.of();
    protected Collection<String> usedUniquenessStrings = Set.of();
    protected boolean canGenerateMultiple = false;
    private boolean generatedGoal = false;
    protected final Set<GoalType> myGoalTypes = new HashSet<>();

    protected BaseGoalFactory(SettingsImage settings) {
        this.settings = settings;
    }

    @Override
    public final IGoal makeGoal() {
        if (this.canGenerateGoal()) {
            IMutableGoal goal = this.makeGoalHook();
            goal.addGoalTypes(this.myGoalTypes);
            this.generatedGoal = true;
            return goal;
        }
        else {
            throw new IllegalStateException("canGenerateGoal is false");
        }
    }

    protected abstract IMutableGoal makeGoalHook();

    @Override
    public GoalFactory setMinDifficulty(GoalDifficulty minDifficulty) {
        this.minDifficulty = minDifficulty;
        return this;
    }

    @Override
    public GoalFactory setMaxDifficulty(GoalDifficulty maxDifficulty) {
        this.maxDifficulty = maxDifficulty;
        return this;
    }

    @Override
    public GoalFactory setUsedGoalTypes(Collection<GoalType> goalTypes) {
        this.usedGoalTypes = goalTypes;
        return this;
    }

    @Override
    public GoalFactory setUsedUniquenessStrings(Collection<String> uniquenessStrings) {
        this.usedUniquenessStrings = uniquenessStrings;
        return this;
    }

    @Override
    public boolean canGenerateGoal() {
        if (this.generatedGoal && this.canGenerateMultiple) {
            return this.canGenerateGoalHook();
        }
        if (!this.generatedGoal && !invalidGoalType()) {
            return this.canGenerateGoalHook();
        }
        return false;
    }

    private boolean invalidGoalType() {
        return this.usedGoalTypes.stream().anyMatch((gt) -> this.myGoalTypes.contains(gt));
    }

    protected void addGoalTypes(GoalType... goalTypes) {
        this.myGoalTypes.addAll(List.of(goalTypes));
    }

    protected final boolean isValidDifficulty(GoalDifficulty difficulty) {
        return difficulty.isInRange(this.minDifficulty, this.maxDifficulty);
    }

    protected abstract boolean canGenerateGoalHook();
}

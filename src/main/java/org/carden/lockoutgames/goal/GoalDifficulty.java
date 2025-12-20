package org.carden.lockoutgames.goal;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum GoalDifficulty {

    VERY_EASY(0),
    EASY(1),
    MEDIUM(2),
    HARD(3),
    VERY_HARD(4);

    final int difficulty;
    GoalDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public boolean isGreaterThanOrEqualTo(GoalDifficulty goalDifficulty) {
        return this.difficulty >= goalDifficulty.difficulty;
    }

    public boolean isLessThanOrEqualTo(GoalDifficulty goalDifficulty) {
        return this.difficulty >= goalDifficulty.difficulty;
    }

    public boolean isGreaterThan(GoalDifficulty goalDifficulty) {
        return this.difficulty > goalDifficulty.difficulty;
    }

    public boolean isLessThan(GoalDifficulty goalDifficulty) {
        return this.difficulty > goalDifficulty.difficulty;
    }

    public boolean isInRange(GoalDifficulty minDifficulty, GoalDifficulty maxDifficulty) {
        return this.difficulty >= minDifficulty.difficulty && this.difficulty <= maxDifficulty.difficulty;
    }

    public GoalDifficulty getNextDifficulty() {
        try {
            return Arrays.stream(GoalDifficulty.values()).filter(diff -> diff.difficulty == this.difficulty + 1).toList().getFirst();
        } catch(NoSuchElementException e) {
            return null;
        }
    }

    public GoalDifficulty getPrevDifficulty() {
        try {
            return Arrays.stream(GoalDifficulty.values()).filter(diff -> diff.difficulty == this.difficulty - 1).toList().getFirst();
        } catch(NoSuchElementException e) {
            return null;
        }
    }

    public boolean isEqualTo(GoalDifficulty goalDifficulty) {
        return this.difficulty == goalDifficulty.difficulty;
    }
}

package org.carden.lockoutgames.goal.factory;

import org.carden.lockoutgames.goal.Goal;
import org.carden.lockoutgames.goal.GoalDifficulty;
import org.carden.lockoutgames.goal.GoalType;

import java.util.Collection;

public interface GoalFactory {
    /**
     * Create a goal according to the set constraints.
     * @return the goal
     * @throws IllegalStateException if canGenerateGoal is false
     */
    Goal makeGoal();

    /**
     * Create a goal with a minimum difficulty (inclusive).
     * @param minDifficulty
     * @return this
     */
    GoalFactory setMinDifficulty(GoalDifficulty minDifficulty);

    /**
     * Create a goal with a maximum difficulty (inclusive).
     * @param maxDifficulty
     * @return this
     */
    GoalFactory setMaxDifficulty(GoalDifficulty maxDifficulty);

    /**
     * Set the goal types that are not permitted to generate.
     *
     * NOTE: This factory should maintain the given reference for external mutability.
     * @param goalTypes
     * @return this
     */
    GoalFactory setUsedGoalTypes(Collection<GoalType> goalTypes);

    /**
     * Set the uniqueness strings that are not permitted to generate.
     *
     * NOTE: This factory should maintain the given reference for external mutability.
     * @param uniquenessStrings
     * @return this
     */
    GoalFactory setUsedUniquenessStrings(Collection<String> uniquenessStrings);

    /**
     * Whether this factory can generate a goal.
     * @return can generate
     */
    boolean canGenerateGoal();
}

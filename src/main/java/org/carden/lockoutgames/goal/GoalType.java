package org.carden.lockoutgames.goal;

public enum GoalType {

    COLLECT_GOAL(CollectGoalGenerator.class);

    private final Class<? extends GoalGenerator> goalType;

    GoalType(Class<? extends GoalGenerator> goalType) {
        this.goalType = goalType;
    }

    public Class<? extends GoalGenerator> getGoalClass() {
        return goalType;
    }

}

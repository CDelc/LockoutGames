package org.carden.lockoutgames.goal;

public enum GoalType {

    COLLECT_GOAL(CollectGoal.class);

    private final Class<? extends Goal> goalType;

    GoalType(Class<? extends Goal> goalType) {
        this.goalType = goalType;
    }

    public Class<? extends Goal> getGoalClass() {
        return goalType;
    }

}

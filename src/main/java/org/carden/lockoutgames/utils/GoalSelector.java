package org.carden.lockoutgames.utils;

import org.carden.lockoutgames.goal.Goal;
import org.carden.lockoutgames.goal.GoalGenerator;
import org.carden.lockoutgames.goal.GoalType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

public class GoalSelector {

    static HashSet<GoalGenerator> allGoals;

    static {
        allGoals = GoalSelector.getAllGoals();
    }

    public static HashSet<Goal> select(int numGoals) {
        Random random = new Random();
        ArrayList<GoalGenerator> allValidGoals = new ArrayList<>();
        ArrayList<Goal> selectedGoals = new ArrayList<>();
        allGoals.stream().filter(GoalGenerator::canGenerate).forEach(allValidGoals::add);

        if(numGoals > allValidGoals.size()) {
            throw new IllegalArgumentException("Too many goals requested for this world");
        }

        for(int i = 0; i < numGoals; i++) {
            int index = random.nextInt(0, allValidGoals.size());
            selectedGoals.add(allValidGoals.get(index).generate());
            allValidGoals.remove(index);
        }

        return new HashSet<>(selectedGoals);
    }

    public static HashSet<GoalGenerator> getAllGoals() {
        HashSet<GoalGenerator> goals = new HashSet<>();

        for(GoalType goalType : GoalType.values()) {
            if(goalType.getGoalClass().isEnum()) {
                goals.addAll(Arrays.asList(goalType.getGoalClass().getEnumConstants()));
            }
        }

        return goals;
    }

}

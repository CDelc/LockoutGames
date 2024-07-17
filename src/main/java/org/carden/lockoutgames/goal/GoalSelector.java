package org.carden.lockoutgames.goal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

public class GoalSelector {


    HashSet<Goal> allGoals;

    public GoalSelector() {
        this.allGoals = getAllGoals();
    }

    public ArrayList<Goal> select(int numGoals) {
        Random random = new Random();
        ArrayList<Goal> allValidGoals = new ArrayList<>();
        ArrayList<Goal> selectedGoals = new ArrayList<>();
        allGoals.stream().filter(Goal::canGenerate).forEach(allValidGoals::add);

        if(numGoals > allValidGoals.size()) {
            throw new IllegalArgumentException("Too many goals requested for this world");
        }
        for(int i = 0; i < numGoals; i++) {
            int index = random.nextInt(0, allValidGoals.size());
            selectedGoals.add(allValidGoals.get(index).generate());
            allValidGoals.remove(index);
        }

        return selectedGoals;
    }

    public HashSet<Goal> getAllGoals() {
        HashSet<Goal> goals = new HashSet<>();

        for(GoalType goalType : GoalType.values()) {
            if(goalType.getGoalClass().isEnum()) {
                goals.addAll(Arrays.asList(goalType.getGoalClass().getEnumConstants()));
            }
        }

        return goals;
    }

}

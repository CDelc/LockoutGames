package org.carden.lockoutgames.goal;

import org.bukkit.Material;
import org.carden.lockoutgames.info.WorldRequirements;
import org.carden.lockoutgames.utils.Utils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ObtainSubsetOrFullSetOfItems extends CollectItemGoal {

    protected int subsetPercentChance = 30;
    protected int stackPercentChance = 40;
    protected int subsetMinSize = 2;
    protected int subsetMaxSize = 4;
    protected int minStackSize = 2;
    protected int maxStackSize = 8;
    protected final String setDescription;

    protected final List<Material> setOfItems;
    protected Map<Material, GoalDifficulty> difficultyMap;

    protected ObtainSubsetOrFullSetOfItems(
            List<Material> setOfItems,
            int subsetPercentChance,
            int stackPercentChance,
            int subsetMinSize,
            int subsetMaxSize,
            int minStackSize,
            int maxStackSize,
            String setDescription,
            Map<Material, GoalDifficulty> difficultyMap
    ) {
        super();

        this.setOfItems = setOfItems;
        this.subsetPercentChance = subsetPercentChance;
        this.stackPercentChance = stackPercentChance;
        this.subsetMinSize = subsetMinSize;
        this.subsetMaxSize = subsetMaxSize;
        this.minStackSize = minStackSize;
        this.maxStackSize = maxStackSize;
        this.setDescription = setDescription;
        this.difficultyMap = difficultyMap;

        construct();
    }

    protected ObtainSubsetOrFullSetOfItems(
            List<Material> setOfItems,
            int subsetPercentChance,
            int stackPercentChance,
            int subsetMinSize,
            int subsetMaxSize,
            int minStackSize,
            int maxStackSize,
            String setDescription,
            GoalDifficulty difficulty
    ) {
        super();

        this.setOfItems = setOfItems;
        this.subsetPercentChance = subsetPercentChance;
        this.stackPercentChance = stackPercentChance;
        this.subsetMinSize = subsetMinSize;
        this.subsetMaxSize = subsetMaxSize;
        this.minStackSize = minStackSize;
        this.maxStackSize = maxStackSize;
        this.setDescription = setDescription;
        this.difficultyMap = setOfItems.stream().collect(Collectors.toMap(m -> m, m -> difficulty));;

        construct();
    }

    private void construct() {
        List<Material> possibleList = this.setOfItems
                .stream()
                .filter(m -> WorldRequirements.checkElement(m.name()))
                .toList();
        if(!Utils.weightedRandomBoolean(subsetPercentChance) && possibleList.size() == setOfItems.size()) {
            constructCompleteSetGoal();
        } else {
            constructSubsetGoal(this.setOfItems);
        }
        this.canGenerateMultiple = false;
        this.goalDifficulty = difficultyMap.get(
                requiredItems
                        .stream()
                        .max((m1, m2) -> Integer.compare(difficultyMap.get(m1).difficulty, difficultyMap.get(m2).difficulty))
        );
    }

    protected void constructSubsetGoal(List<Material> setOfItems) {
        if(Utils.weightedRandomBoolean(stackPercentChance)) {
            construct(setOfItems, minStackSize, maxStackSize, subsetMinSize, subsetMaxSize);
            this.description = "Obtain " +
                    itemsRequiredPerStack +
                    " " +
                    Utils.buildStringList(requiredItems.stream().map(m -> Utils.readableEnumString(m.name())).toList(), "and");
        } else {
            construct(setOfItems, 1, 1, subsetMinSize, subsetMaxSize);
            this.description = "Obtain " +
                    Utils.buildStringList(requiredItems.stream().map(m -> Utils.readableEnumString(m.name())).toList(), "and");
        }
    }

    protected void constructCompleteSetGoal() {
        if(Utils.weightedRandomBoolean(stackPercentChance)) {
            construct(setOfItems, minStackSize, maxStackSize);
            this.description = "Obtain " + itemsRequiredPerStack + " of each " + setDescription;
        } else {
            construct(setOfItems);
            this.description = "Obtain one of each " + setDescription;
        }
    }

    @Override
    protected boolean forceDifficulty(GoalDifficulty goalDifficulty) {
        if(this.goalDifficulty.isEqualTo(goalDifficulty)) return true;
        List<Material> difficultyFilteredList = setOfItems
                .stream()
                .filter(m -> WorldRequirements.checkElement(m.name()) && difficultyMap.get(m).isEqualTo(goalDifficulty))
                .toList();
        if(difficultyFilteredList.size() < subsetMinSize) {
            return false;
        }
        if(!Utils.weightedRandomBoolean(subsetPercentChance) && difficultyFilteredList.size() == setOfItems.size()) {
            constructCompleteSetGoal();
        } else {
            constructSubsetGoal(this.setOfItems);
        }
        this.canGenerateMultiple = false;
        this.goalDifficulty = difficultyMap.get(
                requiredItems
                        .stream()
                        .max((m1, m2) -> Integer.compare(difficultyMap.get(m1).difficulty, difficultyMap.get(m2).difficulty))
        );
        return true;
    }

    @Override
    public GoalDifficulty getGoalDifficulty() {
        this.goalDifficulty = difficultyMap.get(
                requiredItems
                        .stream()
                        .max((m1, m2) -> Integer.compare(difficultyMap.get(m1).difficulty, difficultyMap.get(m2).difficulty)));
        return this.goalDifficulty;
    }

}

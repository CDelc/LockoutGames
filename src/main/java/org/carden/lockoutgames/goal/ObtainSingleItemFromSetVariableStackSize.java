package org.carden.lockoutgames.goal;

import org.bukkit.Material;
import org.carden.lockoutgames.LockoutGames;
import org.carden.lockoutgames.info.WorldRequirements;
import org.carden.lockoutgames.utils.Utils;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class ObtainSingleItemFromSetVariableStackSize extends CollectItemGoal {

    Map<Material, GoalConstants.MinMaxPair> stackSizeMap;
    Map<Material, GoalDifficulty> difficultyMap;

    protected ObtainSingleItemFromSetVariableStackSize(@NotNull Map<Material, GoalConstants.MinMaxPair> stackSizeMap, @NotNull GoalDifficulty difficulty) {
        super();
        this.stackSizeMap = stackSizeMap;
        this.difficultyMap = stackSizeMap.keySet().stream().collect(Collectors.toMap(m -> m, m -> difficulty));
        this.construct();
    }

    protected ObtainSingleItemFromSetVariableStackSize(@NotNull Map<Material, GoalConstants.MinMaxPair> stackSizeMap, @NotNull Map<Material, GoalDifficulty> difficultyMap) {
        super();
        this.stackSizeMap = stackSizeMap;
        this.difficultyMap = difficultyMap;
        this.construct();
    }

    private void construct() {
        List<Material> possibleList = stackSizeMap
                .keySet()
                .stream()
                .filter(m -> WorldRequirements.checkElement(m.name()))
                .toList();
        Material targetMaterial = possibleList.get(LockoutGames.getRng().nextInt(possibleList.size()));
        int min = stackSizeMap.get(targetMaterial).min;
        int max = stackSizeMap.get(targetMaterial).max;
        construct(targetMaterial, min, max);

        this.canGenerateMultiple = false;
        this.description = "Collect " + this.itemsRequiredPerStack + " " + Utils.readableEnumString(targetMaterial.name());
        this.goalDifficulty = Optional.ofNullable(difficultyMap.get(targetMaterial)).orElse(GoalConstants.DEFAULT_DIFFICULTY);
    }

    @Override
    protected boolean forceDifficulty(GoalDifficulty goalDifficulty) {
        if(this.goalDifficulty.isEqualTo(goalDifficulty)) return true;
        List<Material> possibleList = stackSizeMap
                .keySet()
                .stream()
                .filter(m -> WorldRequirements.checkElement(m.name()) && difficultyMap.get(m).isEqualTo(goalDifficulty))
                .toList();
        if(possibleList.isEmpty()) {
            return false;
        }
        Material targetMaterial = possibleList.get(LockoutGames.getRng().nextInt(possibleList.size()));
        int min = stackSizeMap.get(targetMaterial).min;
        int max = stackSizeMap.get(targetMaterial).max;
        this.uniquenessStrings.clear();
        construct(targetMaterial, min, max);
        return true;
    }

    @Override
    public GoalDifficulty getGoalDifficulty() {
        this.goalDifficulty = Optional.ofNullable(difficultyMap.get(requiredItems.getFirst())).orElse(GoalConstants.DEFAULT_DIFFICULTY);
        return this.goalDifficulty;
    }



}

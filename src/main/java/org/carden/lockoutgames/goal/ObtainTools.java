package org.carden.lockoutgames.goal;

import org.bukkit.Material;
import org.carden.lockoutgames.LockoutGames;
import org.carden.lockoutgames.utils.Utils;

import java.util.*;
import java.util.stream.Collectors;

public class ObtainTools extends CollectItemGoal{


    private static final Set<Material> SHOVELS = Set.of(Material.WOODEN_SHOVEL, Material.DIAMOND_SHOVEL, Material.STONE_SHOVEL, Material.GOLDEN_SHOVEL, Material.IRON_SHOVEL, Material.NETHERITE_SHOVEL);
    private static final Set<Material> HOES = Set.of(Material.WOODEN_HOE, Material.DIAMOND_HOE, Material.STONE_HOE, Material.GOLDEN_HOE, Material.IRON_HOE, Material.NETHERITE_HOE);
    private static final Set<Material> SWORDS = Set.of(Material.WOODEN_SWORD, Material.DIAMOND_SWORD, Material.STONE_SWORD, Material.GOLDEN_SWORD, Material.IRON_SWORD, Material.NETHERITE_SWORD);
    private static final Set<Material> PICKAXES = Set.of(Material.WOODEN_PICKAXE, Material.DIAMOND_PICKAXE, Material.STONE_PICKAXE, Material.GOLDEN_PICKAXE, Material.IRON_PICKAXE, Material.NETHERITE_PICKAXE);
    private static final Set<Material> AXES = Set.of(Material.WOODEN_AXE, Material.DIAMOND_AXE, Material.STONE_AXE, Material.GOLDEN_AXE, Material.IRON_AXE, Material.NETHERITE_AXE);
    private static final List<Set<Material>> TOOL_TYPE_LISTS = List.of(SHOVELS, HOES, SWORDS, PICKAXES, AXES);

    private static final Set<Material> WOODEN_TOOLS = Set.of(Material.WOODEN_SHOVEL, Material.WOODEN_AXE, Material.WOODEN_PICKAXE, Material.WOODEN_HOE, Material.WOODEN_SWORD);
    private static final Set<Material> STONE_TOOLS = Set.of(Material.STONE_SHOVEL, Material.STONE_AXE, Material.STONE_PICKAXE, Material.STONE_HOE, Material.STONE_SWORD);
    private static final Set<Material> IRON_TOOLS = Set.of(Material.IRON_SHOVEL, Material.IRON_AXE, Material.IRON_PICKAXE, Material.IRON_HOE, Material.IRON_SWORD);
    private static final Set<Material> GOLDEN_TOOLS = Set.of(Material.GOLDEN_SHOVEL, Material.GOLDEN_AXE, Material.GOLDEN_PICKAXE, Material.GOLDEN_HOE, Material.GOLDEN_SWORD);
    private static final Set<Material> DIAMOND_TOOLS = Set.of(Material.DIAMOND_SHOVEL, Material.DIAMOND_AXE, Material.DIAMOND_PICKAXE, Material.DIAMOND_HOE, Material.DIAMOND_SWORD);
    private static final Set<Material> NETHERITE_TOOLS = Set.of(Material.NETHERITE_SHOVEL, Material.NETHERITE_AXE, Material.NETHERITE_PICKAXE, Material.NETHERITE_HOE, Material.NETHERITE_SWORD);
    private static final List<Set<Material>> TOOL_MATERIAL_LISTS = List.of(WOODEN_TOOLS, STONE_TOOLS, IRON_TOOLS, GOLDEN_TOOLS, DIAMOND_TOOLS, NETHERITE_TOOLS);

    private static final Set<Material> ALL_TOOLS = TOOL_MATERIAL_LISTS.stream().flatMap(Set::stream).collect(Collectors.toSet());

    private enum PossibleGoalVersion {
        SINGLE_TOOL, TOOL_MATERIAL, TOOL_TYPE, TOOL_TYPE_NON_NETHERITE
    }

    private enum ToolMaterials {
        WOODEN(WOODEN_TOOLS, GoalDifficulty.VERY_EASY, GoalDifficulty.VERY_EASY, "Obtain all wooden tools"),
        STONE(STONE_TOOLS, GoalDifficulty.EASY, GoalDifficulty.VERY_EASY, "Obtain all stone tools"),
        IRON(IRON_TOOLS, GoalDifficulty.EASY, GoalDifficulty.EASY, "Obtain all iron tools"),
        GOLDEN(GOLDEN_TOOLS, GoalDifficulty.EASY, GoalDifficulty.EASY, "Obtain all golden tools"),
        DIAMOND(DIAMOND_TOOLS, GoalDifficulty.MEDIUM, GoalDifficulty.HARD, "Obtain all diamond tools"),
        NETHERITE(NETHERITE_TOOLS, GoalDifficulty.HARD, GoalDifficulty.VERY_HARD, "Obtain all netherite tools");

        final Set<Material> toolSet;
        final GoalDifficulty difficultyToObtainAll;
        final GoalDifficulty difficultyToObtainSingle;
        final String description;

        ToolMaterials(Set<Material> toolSet, GoalDifficulty difficultyToObtainAll, GoalDifficulty difficultyToObtainSingle, String description) {
            this.toolSet = toolSet;
            this.difficultyToObtainAll = difficultyToObtainAll;
            this.difficultyToObtainSingle = difficultyToObtainSingle;
            this.description = description;
        }
    }

    private enum ToolTypes {
        SHOVEL(SHOVELS, "Obtain all types of shovels"),
        SWORD(SWORDS,  "Obtain all types of swords"),
        PICKAXE(PICKAXES, "Obtain all types of pickaxes"),
        AXE(AXES, "Obtain all types of axes"),
        HOE(HOES, "Obtain all types of hoes");

        final Set<Material> toolSet;
        final String description;

        ToolTypes(Set<Material> toolSet, String description) {
            this.toolSet = toolSet;
            this.description = description;
        }
    }

    private static final List<PossibleGoalVersion> allGoalVersions = Arrays.stream(PossibleGoalVersion.values()).toList();

    protected ObtainTools() {
        super();
        constructToolGoal();
    }

    @Override
    protected boolean forceDifficulty(GoalDifficulty goalDifficulty) {
        this.uniquenessStrings.clear();
        List<PossibleGoalVersion> goalVersions = Utils.selectNRandomValuesFromList(Arrays.stream(PossibleGoalVersion.values()).toList(), PossibleGoalVersion.values().length, LockoutGames.getRng());
        for(PossibleGoalVersion goalVersion : goalVersions) {
            if(goalVersion == PossibleGoalVersion.SINGLE_TOOL) {
                List<Material> filteredTools = filterToolsByDifficulty(goalDifficulty);
                if(filteredTools.isEmpty()) continue;
                construct(filterToolsByDifficulty(goalDifficulty), 1, 1, 1, 1);
                if(failedToGenerate) continue;
                this.requiredItems.getFirst();
            } else if(goalVersion == PossibleGoalVersion.TOOL_TYPE && goalDifficulty == GoalDifficulty.HARD) {
                ToolTypes toolType = Utils.selectNRandomValuesFromList(Arrays.stream(ToolTypes.values()).toList(), 1, LockoutGames.getRng()).getFirst();
                construct(toolType.toolSet.stream().toList());
                this.description = toolType.description;
                this.goalDifficulty = GoalDifficulty.HARD;
            } else if(goalVersion == PossibleGoalVersion.TOOL_MATERIAL) {
                List<ToolMaterials> toolMaterialList = Utils.selectNRandomValuesFromList(
                        Arrays.stream(ToolMaterials.values())
                                .filter(tm -> tm.difficultyToObtainAll == goalDifficulty)
                                .toList(),
                        1, LockoutGames.getRng());
                if(toolMaterialList.isEmpty()) continue;
                construct(toolMaterial.toolSet.stream().toList());
                this.description = toolMaterial.description;
                this.goalDifficulty = toolMaterial.difficultyToObtainAll;
            } else if(goalVersion == PossibleGoalVersion.TOOL_TYPE_NON_NETHERITE && goalDifficulty == GoalDifficulty.MEDIUM) {
                ToolTypes toolType = Utils.selectNRandomValuesFromList(Arrays.stream(ToolTypes.values()).toList(), 1, LockoutGames.getRng()).getFirst();
                construct(toolType.toolSet.stream().filter(material -> !material.name().contains("NETHERITE")).toList());
                this.description = toolType.description + " except netherite";
                this.goalDifficulty = GoalDifficulty.MEDIUM;
            }
        }
    }

    private void constructToolGoal() {
        PossibleGoalVersion goalVersion = Utils.selectNRandomValuesFromList(allGoalVersions, 1, LockoutGames.getRng()).getFirst();
        if(goalVersion == PossibleGoalVersion.SINGLE_TOOL) {
            construct(ALL_TOOLS.stream().toList(), 1, 1, 1, 1);
            if(failedToGenerate) return;
            this.requiredItems.getFirst();
        } else if(goalVersion == PossibleGoalVersion.TOOL_TYPE) {
            ToolTypes toolType = Utils.selectNRandomValuesFromList(Arrays.stream(ToolTypes.values()).toList(), 1, LockoutGames.getRng()).getFirst();
            construct(toolType.toolSet.stream().toList());
            this.description = toolType.description;
            this.goalDifficulty = GoalDifficulty.HARD;
            this.uniquenessStrings.add("toolset");
        } else if(goalVersion == PossibleGoalVersion.TOOL_MATERIAL) {
            ToolMaterials toolMaterial = Utils.selectNRandomValuesFromList(Arrays.stream(ToolMaterials.values()).toList(), 1, LockoutGames.getRng()).getFirst();
            construct(toolMaterial.toolSet.stream().toList());
            this.description = toolMaterial.description;
            this.goalDifficulty = toolMaterial.difficultyToObtainAll;
            this.uniquenessStrings.add("toolset");
        } else if(goalVersion == PossibleGoalVersion.TOOL_TYPE_NON_NETHERITE) {
            ToolTypes toolType = Utils.selectNRandomValuesFromList(Arrays.stream(ToolTypes.values()).toList(), 1, LockoutGames.getRng()).getFirst();
            construct(toolType.toolSet.stream().filter(material -> !material.name().contains("NETHERITE")).toList());
            this.description = toolType.description + " except netherite";
            this.goalDifficulty = GoalDifficulty.MEDIUM;
            this.uniquenessStrings.add("toolset");
        }
        this.goalTypes.add(GoalType.OBTAIN_TOOLS);
    }

    private List<Material> filterToolsByDifficulty(GoalDifficulty difficulty) {
        return ObtainTools.ALL_TOOLS.stream()
                .filter(
                        material ->
                                Arrays.stream(ToolMaterials.values())
                                        .filter(toolMaterials ->
                                                toolMaterials.difficultyToObtainSingle == difficulty
                                        )
                                        .anyMatch(
                                                toolMaterials -> toolMaterials.toolSet.contains(material)
                                        )
                ).toList();
    }

    private List<ToolMaterials> filterToolMaterialByDifficulty(GoalDifficulty difficulty) {
        return Arrays.stream(ToolMaterials.values()).filter(toolMaterials -> toolMaterials.difficultyToObtainAll == difficulty).toList();
    }
}

//package org.carden.lockoutgames.goal;
//
//import org.bukkit.Material;
//import org.carden.lockoutgames.LockoutGames;
//import org.carden.lockoutgames.info.WorldRequirements;
//import org.carden.lockoutgames.utils.Utils;
//
//import java.util.Collection;
//import java.util.List;
//import java.util.Set;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//public class ObtainCaveBlocksMultiple extends ObtainSubsetOrFullSetOfItems{
//
//    private static final int SUBSET_PERCENT_CHANCE = 30;
//    private static final int STACK_PERCENT_CHANCE = 80;
//    private static final int SUBSET_MIN_SIZE = 3;
//    private static final int SUBSET_MAX_SIZE = 5;
//    private static final int MIN_STACK_SIZE = 8;
//    private static final int MAX_STACK_SIZE = 32;
//
//    private static final Set<Material> LUSH_BIOME_REQUIRED = Set.of(Material.MOSS_BLOCK, Material.MOSS_CARPET);
//    private static final Set<Material> DRIPSTONE_REQUIRED = Set.of(Material.DRIPSTONE_BLOCK, Material.POINTED_DRIPSTONE);
//    private static final Set<Material> DEEP_DARK_REQUIRED = Set.of(Material.SCULK);
//    private static final GoalDifficulty GOAL_BOOST_DIFFICULTY = GoalDifficulty.MEDIUM;
//
//    protected ObtainCaveBlocksMultiple() {
//        super(GoalConstants.CAVE_BLOCKS.keySet().stream().toList(), SUBSET_PERCENT_CHANCE, STACK_PERCENT_CHANCE, SUBSET_MIN_SIZE, SUBSET_MAX_SIZE, MIN_STACK_SIZE, MAX_STACK_SIZE, "cave block", GoalConstants.CAVE_BLOCK_DIFFICULTY_MAP);
//
//        this.goalTypes.add(GoalType.CAVE_BLOCK);
//        this.canGenerateMultiple = false;
//
//        if(difficultyBoost(requiredItems)) {
//            this.goalDifficulty = GOAL_BOOST_DIFFICULTY;
//        }
//    }
//
//    @Override
//    protected boolean forceDifficulty(GoalDifficulty goalDifficulty) {
//        if(this.goalDifficulty.isEqualTo(goalDifficulty)) return true;
//        else if(goalDifficulty.isEqualTo(GOAL_BOOST_DIFFICULTY)) {
//            return forceDifficultyBoost();
//        }
//        else {
//            return super.forceDifficulty(goalDifficulty);
//        }
//    }
//
//    @Override
//    public GoalDifficulty getGoalDifficulty() {
//        this.goalDifficulty = difficultyMap.get(
//                requiredItems
//                        .stream()
//                        .max((m1, m2) -> Integer.compare(difficultyMap.get(m1).difficulty, difficultyMap.get(m2).difficulty)));
//        if(difficultyBoost(requiredItems)) {
//            this.goalDifficulty = GOAL_BOOST_DIFFICULTY;
//        }
//        return this.goalDifficulty;
//    }
//
//    private boolean difficultyBoost(List<Material> list) {
//        return list.stream().anyMatch(material -> Stream.of(LUSH_BIOME_REQUIRED, DRIPSTONE_REQUIRED, DEEP_DARK_REQUIRED).filter(set -> set.contains(material)).count() > 1);
//    }
//
//    private boolean forceDifficultyBoost() {
//        if(this.goalDifficulty.isEqualTo(GOAL_BOOST_DIFFICULTY)) return true;
//        List<Material> possibleList = GoalConstants.CAVE_BLOCKS.keySet()
//                .stream()
//                .filter(m -> WorldRequirements.checkElement(m.name()))
//                .toList();
//
//        Set<Material> materialToAdd = Stream.of(LUSH_BIOME_REQUIRED, DRIPSTONE_REQUIRED, DEEP_DARK_REQUIRED)
//                .filter(set -> requiredItems.stream().anyMatch(item -> !set.contains(item)))
//                .flatMap(Set::stream)
//                .collect(Collectors.toSet());
//        if(materialToAdd.isEmpty()) return false;
//
//        if(!Utils.weightedRandomBoolean(subsetPercentChance) && possibleList.size() == setOfItems.size()) {
//            constructCompleteSetGoal();
//        } else {
//            constructDifficultSubsetGoal();
//        }
//        return true;
//    }
//
//    private void constructDifficultSubsetGoal() {
//        if(Utils.weightedRandomBoolean(stackPercentChance)) {
//            construct(setOfItems, minStackSize, maxStackSize, subsetMinSize, subsetMaxSize);
//            this.description = "Obtain " +
//                    itemsRequiredPerStack +
//                    " " +
//                    Utils.buildStringList(requiredItems.stream().map(m -> Utils.readableEnumString(m.name())).toList(), "and");
//        } else {
//            construct(setOfItems, 1, 1, subsetMinSize, subsetMaxSize);
//            this.description = "Obtain " +
//                    Utils.buildStringList(requiredItems.stream().map(m -> Utils.readableEnumString(m.name())).toList(), "and");
//        }
//        if(difficultyBoost(requiredItems)) {
//            this.goalDifficulty = GOAL_BOOST_DIFFICULTY;
//            return;
//        }
//        //If we're already at the max subset size, remove an item that doesn't require a specific biome
//        else if(requiredItems.size() == SUBSET_MAX_SIZE) {
//            Material removeMaterial = requiredItems
//                    .stream()
//                    .filter(
//                            material -> Stream.of(LUSH_BIOME_REQUIRED, DRIPSTONE_REQUIRED, DEEP_DARK_REQUIRED).noneMatch(set -> set.contains(material))
//                    ).findFirst()
//                    .orElse(Material.AIR);
//            requiredItems.remove(removeMaterial);
//        }
//        //Add some item that comes from a biome that isn't already included on requiredItems
//        Stream<Set<Material>> missingBiomeStream = Stream.of(LUSH_BIOME_REQUIRED, DRIPSTONE_REQUIRED, DEEP_DARK_REQUIRED).filter(set -> requiredItems.stream().noneMatch(set::contains));
//        List<Material> addItemList = missingBiomeStream.flatMap(Collection::stream).toList();
//        requiredItems.add(addItemList.get(LockoutGames.getRng().nextInt(addItemList.size())));
//    }
//}

package org.carden.lockoutgames.info;

import org.bukkit.Difficulty;
import org.carden.lockoutgames.game.setting.SettingID;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SettingIDS {
    public static final SettingID<Integer> WORLD_SIZE = new SettingID<>(0);
    public static final SettingID<Integer> NUM_GOALS = new SettingID<>(1);;
    public static final SettingID<Boolean> PVP = new SettingID<>(2);;
    public static final SettingID<Difficulty> DIFFICULTY = new SettingID<>(3);;
    public static final SettingID<Boolean> HUNGER = new SettingID<>(4);;
    public static final SettingID<Integer> PLAYER_SPREAD_RADIUS = new SettingID<>(5);;
    public static final SettingID<Integer> PLAYER_SPREAD = new SettingID<>(6);;

    private static final String WORLD_SIZE_ARG = "worldSize";
    private static final String NUM_GOALS_ARG = "numGoals";
    private static final String PVP_ARG = "pvp";
    private static final String DIFFICULTY_ARG = "difficulty";
    private static final String HUNGER_ARG = "hunger";
    private static final String PLAYER_SPREAD_RADIUS_ARG = "playerSpreadRadius";
    private static final String PLAYER_SPREAD_ARG = "playerSpread";

    public static Map<String, SettingID<?>> COMMAND_MAPPINGS;

    static {
        COMMAND_MAPPINGS = new HashMap<>();
        COMMAND_MAPPINGS.put(WORLD_SIZE_ARG.toLowerCase(), WORLD_SIZE);
        COMMAND_MAPPINGS.put(NUM_GOALS_ARG.toLowerCase(), NUM_GOALS);
        COMMAND_MAPPINGS.put(PVP_ARG.toLowerCase(), PVP);
        COMMAND_MAPPINGS.put(DIFFICULTY_ARG.toLowerCase(), DIFFICULTY);
        COMMAND_MAPPINGS.put(HUNGER_ARG.toLowerCase(), HUNGER);
        COMMAND_MAPPINGS.put(PLAYER_SPREAD_RADIUS_ARG.toLowerCase(), PLAYER_SPREAD_RADIUS);
        COMMAND_MAPPINGS.put(PLAYER_SPREAD_ARG.toLowerCase(), PLAYER_SPREAD);

        COMMAND_MAPPINGS = Collections.unmodifiableMap(COMMAND_MAPPINGS);
    }
}

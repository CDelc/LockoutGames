package org.carden.lockoutgames.info;

import org.bukkit.Difficulty;

public class SettingsConstants {

    public static String WORLD_SIZE_HEADER = "World Size";
    public static String WORLD_SIZE_HELP = "The diameter of the world border";
    public static Integer WORLD_SIZE_DEFAULT = 2500;
    public static Integer WORLD_SIZE_MINIMUM = 500;
    public static Integer WORLD_SIZE_MAXIMUM = 30000000;

    public static String NUM_GOALS_HEADER = "Number of Goals";
    public static String NUM_GOALS_HELP = "The number of total goals in the game";
    public static Integer NUM_GOALS_DEFAULT = 25;
    public static Integer NUM_GOALS_MINIMUM = 1;

    public static String PVP_HEADER = "Enable PvP";
    public static String PVP_HELP = "PvP is enabled or disabled";
    public static Boolean PVP_DEFAULT = true;

    public static String DIFFICULTY_HEADER = "Difficulty";
    public static String DIFFICULTY_HELP = "The in-game difficulty (Peaceful not currently supported)";
    public static Difficulty DIFFICULTY_DEFAULT = Difficulty.NORMAL;

    public static String HUNGER_HEADER = "Enable Hunger";
    public static String HUNGER_HELP = "Consumption of hunger can be enabled or disabled";
    public static boolean HUNGER_DEFAULT = true;

    public static String PLAYER_SPREAD_RADIUS_HEADER = "Player Spread Radius";
    public static String PLAYER_SPREAD_RADIUS_HELP = "The distance from 0,0 to the edge of the square in which players can start the game in";
    public static Integer PLAYER_SPREAD_RADIUS_DEFAULT = 30000000;
    public static Integer PLAYER_SPREAD_RADIUS_MINIMUM = 50;
    public static Integer PLAYER_SPREAD_RADIUS_MAXIMUM = 30000000;

    public static String PLAYER_SPREAD_HEADER = "Spread Players at Start of Game";
    public static String PLAYER_SPREAD_HELP = "Whether players are have initial spawn points distributed across the world";
    public static Boolean PLAYER_SPREAD_DEFAULT = true;
}

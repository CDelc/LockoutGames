package org.carden.lockoutgames.game;

import org.bukkit.Difficulty;
import org.bukkit.scheduler.BukkitRunnable;
import org.carden.lockoutgames.LockoutGames;
import org.carden.lockoutgames.game.gametypes.Game;
import org.carden.lockoutgames.game.gametypes.Lockout;

import java.util.Random;

public class GameBuilder {
    /**
     * Class that stores all the game settings information. This class is used as a factory to generate the actual game instances.
     */

    public enum Gametype {

        LOCKOUT(Lockout.class);

        private final Class<? extends Game> gameType;

        Gametype(Class<? extends Game> gameType) {
            this.gameType = gameType;
        }

        public Class<? extends Game> getGameClass() {
            return this.gameType;
        }
    }

    LockoutGames plugin;
    Lockout game;
    Gametype gametype;

    private int worldSize;
    private int numGoals;
    private boolean pvp;
    private Difficulty difficulty;
    private boolean hunger;
    private int playerSpreadRadius;

    static final int WORLD_SIZE_DEFAULT = 60000;
    static final int NUM_GOALS_DEFAULT = 25;
    static final boolean PVP_DEFAULT = false;
    static final Difficulty DIFFICULTY_DEFAULT = Difficulty.EASY;
    static final boolean HUNGER_DEFAULT = true;

    public GameBuilder() {
        this.plugin = LockoutGames.getPluginInstance();
        this.game = null;

        setDefaults();
    }

    public void setDefaults() {

        setNumGoals(NUM_GOALS_DEFAULT);
        setWorldSize(WORLD_SIZE_DEFAULT);
        gametype = Gametype.LOCKOUT;
        pvp = PVP_DEFAULT;
        this.difficulty = DIFFICULTY_DEFAULT;
        this.hunger = HUNGER_DEFAULT;
        this.playerSpreadRadius = worldSize;
    }

    /**
     *
     * @return World Size (Diameter from border to border)
     */
    public int getWorldSize() {
        return worldSize;
    }

    /**
     *
     * @param worldSize The new world size diameter setting
     */
    public void setWorldSize(int worldSize) {
        this.worldSize = worldSize;
    }

    /**
     *
     * @return Number of goals to be generated
     */
    public int getNumGoals() {
        return numGoals;
    }

    /**
     *
     * @param numGoals The number of goals to be generated for games
     */
    public void setNumGoals(int numGoals) {
        this.numGoals = numGoals;
    }

    public boolean isPvp() {
        return pvp;
    }

    public void setPvp(boolean pvp) {
        this.pvp = pvp;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) throws IllegalArgumentException{
        if(difficulty == Difficulty.PEACEFUL) {
            throw new IllegalArgumentException("Peaceful difficulty is not supported in logic");
        }
        this.difficulty = difficulty;
    }

    public boolean isHunger() {
        return hunger;
    }

    public void setHunger(boolean hunger) {
        this.hunger = hunger;
    }

    public int getPlayerSpreadRadius() {
        return playerSpreadRadius;
    }

    public void setPlayerSpreadRadius(int playerSpreadRadius) {
        this.playerSpreadRadius = playerSpreadRadius;
    }

    /**
     *
     * @return The instance of the game, if one is running
     */
    public Game getGame() {
        return game;
    }

    public boolean gameIsRunning() {
        return game != null;
    }

    /**
     * Begins a new game.
     * @return False if a game is already running, true if the game begins successfully.
     */
    public boolean start() {
        if (gameIsRunning()) return false;
        GameBuilder instance = this;
        SettingsImage image = new SettingsImage(instance);
        GameWorld world = LockoutGames.getPluginInstance().getGameWorld();
        world.prepareNewWorld(image).thenRun(new BukkitRunnable() {
            @Override
            public void run() {
                game = new Lockout(image, new Random());
            }
        });
        return true;
    }
}

package org.carden.lockoutgames.game;

import org.bukkit.Difficulty;

public class SettingsImage {

    private final int worldSize;
    private final int numGoals;
    private final boolean regenOnStart;
    private final boolean pvp;
    private final Difficulty difficulty;
    private final boolean hunger;
    private final int playerSpreadRadius;

    public SettingsImage(GameBuilder b) {
        this.worldSize = b.getWorldSize();
        this.numGoals = b.getNumGoals();
        this.regenOnStart = b.isRegenOnStart();
        this.pvp = b.isPvp();
        this.difficulty = b.getDifficulty();
        this.hunger = b.isHunger();
        this.playerSpreadRadius = b.getPlayerSpreadRadius();
    }

    public int getWorldSize() {
        return worldSize;
    }

    public int getNumGoals() {
        return numGoals;
    }

    public boolean isRegenOnStart() {
        return regenOnStart;
    }

    public boolean isPvP() {
        return pvp;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public boolean isHunger() {
        return hunger;
    }

    public int getPlayerSpreadRadius() {
        return playerSpreadRadius;
    }
}

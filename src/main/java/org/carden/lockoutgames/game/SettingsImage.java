package org.carden.lockoutgames.game;

public class SettingsImage {

    int worldSize;
    int numGoals;

    public SettingsImage(GameBuilder b) {
        this.worldSize = b.getWorldSize();
        this.numGoals = b.getNumGoals();
    }

    public int getWorldSize() {
        return worldSize;
    }

    public int getNumGoals() {
        return numGoals;
    }
}

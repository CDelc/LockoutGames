package org.carden.lockoutgames.game.gametypes;

public enum GameType {

    LOCKOUT(Lockout.class);

    private final Class<? extends Game> gameType;

    GameType(Class<? extends Game> gameType) {
        this.gameType = gameType;
    }

    public Class<? extends Game> getGameClass() {
        return this.gameType;
    }
}

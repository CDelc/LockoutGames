package org.carden.lockoutgames.game;

import com.onarandombox.MultiverseCore.MVWorld;
import org.carden.lockoutgames.LockoutGames;

import java.util.concurrent.locks.Lock;

public class GameWorld {
    /**
     * Implementation not yet started -
     * This will calculate and store information about the contents of the world the game is taking place in
     */

    MVWorld world;
    LockoutGames plugin;

    public GameWorld(LockoutGames plugin, MVWorld world) {
        this.plugin = plugin;
        this.world = world;
    }

}

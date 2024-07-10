package org.carden.lockoutgames.game;

import com.onarandombox.MultiverseCore.api.MVWorldManager;
import org.bukkit.World;
import org.carden.lockoutgames.LockoutGames;

public class Lockout {

    LockoutGames plugin;
    GameWorld world;
    MVWorldManager worldManager;

    public Lockout(LockoutGames plugin) {
        this.plugin = plugin;
    }

}

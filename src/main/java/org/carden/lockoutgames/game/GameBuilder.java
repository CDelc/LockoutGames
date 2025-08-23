package org.carden.lockoutgames.game;

import org.bukkit.scheduler.BukkitRunnable;
import org.carden.lockoutgames.LockoutGames;
import org.carden.lockoutgames.game.gametypes.Game;
import org.carden.lockoutgames.game.gametypes.Lockout;
import org.carden.lockoutgames.game.setting.Setting;
import org.carden.lockoutgames.game.setting.SettingsImage;

import java.util.Map;
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


    Map<Integer, Setting<?>> settings;

    public GameBuilder() {
        this.plugin = LockoutGames.getPluginInstance();
        this.game = null;
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
        SettingsImage image = Setting.saveSettings();
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

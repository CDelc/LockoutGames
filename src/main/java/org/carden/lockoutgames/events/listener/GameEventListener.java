package org.carden.lockoutgames.events.listener;

import org.bukkit.ChatColor;
import org.bukkit.event.*;
import org.carden.lockoutgames.LockoutGames;
import org.carden.lockoutgames.events.GoalCompleteEvent;
import org.carden.lockoutgames.events.SettingChangeEvent;
import org.carden.lockoutgames.game.GameBuilder;
import org.carden.lockoutgames.game.setting.Setting;

public class GameEventListener implements Listener {

    private static final GameEventListener gameEventListener = new GameEventListener();

    private GameEventListener() {}

    public static GameEventListener getInstance() {
        return gameEventListener;
    }

    private final static GameBuilder gameBuilder = LockoutGames.getPluginInstance().getGameBuilder();

    @EventHandler
    public void onGoalComplete(GoalCompleteEvent e) {
        if(gameBuilder.getGame().isPresent()) gameBuilder.getGame().get().handleGoalEvent(e);
    }

    @EventHandler
    public void onSettingChange(SettingChangeEvent<?> settingChangeEvent) {
        if(gameBuilder.getGame().isPresent()) return;
        LockoutGames.broadcastMessage(
                ChatColor.BLUE + Setting.getSettingObjectCopy(settingChangeEvent.getSettingID()).getName() +
                        ChatColor.WHITE + " changed " +
                        ChatColor.YELLOW + settingChangeEvent.getFromValue() +
                        ChatColor.WHITE + " → " +
                        ChatColor.YELLOW + settingChangeEvent.getToValue()
        );
    }
}

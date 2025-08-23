package org.carden.lockoutgames.events.listener;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.carden.lockoutgames.LockoutGames;
import org.carden.lockoutgames.events.GoalObtainedEvent;
import org.carden.lockoutgames.events.SettingChangeEvent;
import org.carden.lockoutgames.game.GameBuilder;
import org.carden.lockoutgames.game.setting.Setting;
import org.carden.lockoutgames.info.SettingIDS;

public class GameEventListener implements Listener {

    private final static GameBuilder gameBuilder = LockoutGames.getPluginInstance().getGameBuilder();

    @EventHandler
    public void onGoalComplete(GoalObtainedEvent e) {
        if(gameBuilder.getGame() != null) gameBuilder.getGame().handleGoal(e);
    }

    @EventHandler
    public void onSettingChange(SettingChangeEvent<?> settingChangeEvent) {
        if(gameBuilder.gameIsRunning()) return;
        if(settingChangeEvent.getSettingID().equals(SettingIDS.WORLD_SIZE)) {
            LockoutGames.getPluginInstance().getGameWorld().setWorldSize((Integer) settingChangeEvent.getToValue());
        }
        LockoutGames.broadcastMessage(
                ChatColor.BLUE + Setting.getSettingObjectCopy(settingChangeEvent.getSettingID()).getName() +
                        ChatColor.WHITE + " changed " +
                        ChatColor.YELLOW + settingChangeEvent.getFromValue() +
                        ChatColor.WHITE + " → " +
                        ChatColor.YELLOW + settingChangeEvent.getToValue()
        );
    }

}

package org.carden.lockoutgames.events.listener;

import org.bukkit.ChatColor;
import org.bukkit.event.*;
import org.carden.lockoutgames.LockoutGames;
import org.carden.lockoutgames.events.GoalCompleteEvent;
import org.carden.lockoutgames.events.SettingChangeEvent;
import org.carden.lockoutgames.game.Debug;
import org.carden.lockoutgames.game.setting.Setting;

public class GameEventListener implements Listener {

    private static final GameEventListener gameEventListener = new GameEventListener();

    private GameEventListener() {}

    public static GameEventListener getInstance() {
        return gameEventListener;
    }

    @EventHandler
    public void onGoalComplete(GoalCompleteEvent e) {
        if(Debug.isActive()) LockoutGames.broadcastMessage(ChatColor.LIGHT_PURPLE + "" + e.getPlayer() + " " + ChatColor.WHITE + " has completed " + ChatColor.AQUA + e.getGoal().getDescription());
        if(LockoutGames.getGame().isPresent()) LockoutGames.getGame().get().handleGoalEvent(e);
    }

    @EventHandler
    public void onSettingChange(SettingChangeEvent<?> settingChangeEvent) {
        if(LockoutGames.getGame().isPresent()) return;
        LockoutGames.broadcastMessage(
                ChatColor.BLUE + Setting.getSettingObjectCopy(settingChangeEvent.getSettingID()).getName() +
                        ChatColor.WHITE + " changed " +
                        ChatColor.YELLOW + settingChangeEvent.getFromValue() +
                        ChatColor.WHITE + " → " +
                        ChatColor.YELLOW + settingChangeEvent.getToValue()
        );
    }
}

package org.carden.lockoutgames.info;

import org.bukkit.event.Listener;
import org.carden.lockoutgames.LockoutGames;
import org.carden.lockoutgames.commands.Gadmin;
import org.carden.lockoutgames.commands.Game;
import org.carden.lockoutgames.events.listener.GameEventListener;
import org.carden.lockoutgames.events.listener.GoalCheckListener;
import org.carden.lockoutgames.events.listener.PlayerTrackingListener;
import org.carden.lockoutgames.events.listener.SettingsEnforcementListener;
import org.carden.lockoutgames.goal.Goal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OtherConstants {

    public static Map<String, Object> COMMAND_EXECUTOR_MAP = new HashMap<>();

    static {
        COMMAND_EXECUTOR_MAP.put("gadmin", new Gadmin());
        COMMAND_EXECUTOR_MAP.put("game", new Game());
    }

    public static List<Listener> EVENT_LISTENERS = List.of(
            GameEventListener.getInstance(),
            PlayerTrackingListener.getInstance(),
            GoalCheckListener.getInstance(),
            SettingsEnforcementListener.getInstance()
    );
}

package org.carden.lockoutgames.game;

import org.carden.lockoutgames.game.setting.Setting;

import java.util.Map;

public class SettingsImage {

    private static final Map<Integer, Setting<?>> settings_map;

    public SettingsImage() {

    }

    public static Object getSetting(Integer settingID) {
        return settings_map.get(settingID).getCurrentValue();
    }
}

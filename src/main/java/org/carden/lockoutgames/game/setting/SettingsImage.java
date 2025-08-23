package org.carden.lockoutgames.game.setting;

import java.util.Collections;
import java.util.Map;

public class SettingsImage {

    private final Map<Integer, Setting<?>> settings_map;

    protected SettingsImage(Map<Integer, Setting<?>> settings_map) {
        this.settings_map = Collections.unmodifiableMap(settings_map);
    }

    @SuppressWarnings("unchecked")
    public <T> T getSetting(SettingID<T> settingID) {
        return (T) settings_map.get(settingID.getID()).getCurrentValue();
    }
}

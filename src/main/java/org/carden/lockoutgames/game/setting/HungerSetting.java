package org.carden.lockoutgames.game.setting;

import org.carden.lockoutgames.info.SettingIDS;
import org.carden.lockoutgames.info.SettingsConstants;

public class HungerSetting extends BooleanSetting {

    static {
        settings_map.put(SettingIDS.HUNGER.getID(), new HungerSetting());
    }

    public HungerSetting() {
        super(SettingsConstants.HUNGER_HEADER, SettingsConstants.HUNGER_HELP, SettingsConstants.HUNGER_DEFAULT);
    }

}

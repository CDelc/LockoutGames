package org.carden.lockoutgames.game.setting;

import org.carden.lockoutgames.info.SettingIDS;
import org.carden.lockoutgames.info.SettingsConstants;

public class LogicRadiusSetting extends NumericalSetting {

    static {settings_map.put(SettingIDS.LOGIC_RADIUS.ID(), new LogicRadiusSetting());}

    protected LogicRadiusSetting() {
        super(
                SettingsConstants.LOGIC_SPREAD_HEADER,
                SettingsConstants.LOGIC_SPREAD_HELP,
                SettingsConstants.LOGIC_SPREAD_DEFAULT,
                SettingsConstants.LOGIC_SPREAD_MINIMUM,
                SettingsConstants.LOGIC_SPREAD_MAXIMUM
        );
    }
}

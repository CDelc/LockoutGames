package org.carden.lockoutgames.game.setting;

import org.carden.lockoutgames.info.SettingIDS;
import org.carden.lockoutgames.info.SettingsConstants;

public class PvPSetting extends BooleanSetting {

    static {settings_map.put(SettingIDS.PVP.ID(), new PvPSetting());}

    public PvPSetting() {
        super(SettingsConstants.PVP_HEADER, SettingsConstants.PVP_HELP, SettingsConstants.PVP_DEFAULT);
    }
}

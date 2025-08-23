package org.carden.lockoutgames.game.setting;

import org.carden.lockoutgames.info.SettingIDS;
import org.carden.lockoutgames.info.SettingsConstants;

public class PlayerSpreadSetting extends BooleanSetting {

    static {settings_map.put(SettingIDS.PLAYER_SPREAD.getID(), new PlayerSpreadSetting());}

    public PlayerSpreadSetting() {
        super(SettingsConstants.PLAYER_SPREAD_HEADER, SettingsConstants.PLAYER_SPREAD_HELP, SettingsConstants.PLAYER_SPREAD_DEFAULT);
    }

}

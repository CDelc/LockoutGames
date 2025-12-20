package org.carden.lockoutgames.game.setting;

import org.carden.lockoutgames.info.SettingIDS;
import org.carden.lockoutgames.info.SettingsConstants;

public class PlayerSpreadRadiusSetting extends NumericalSetting {

    static {settings_map.put(SettingIDS.PLAYER_SPREAD_RADIUS.ID(), new PlayerSpreadRadiusSetting());}

    protected PlayerSpreadRadiusSetting() {
        super(
                SettingsConstants.PLAYER_SPREAD_RADIUS_HEADER,
                SettingsConstants.PLAYER_SPREAD_RADIUS_HELP,
                SettingsConstants.PLAYER_SPREAD_RADIUS_DEFAULT,
                SettingsConstants.PLAYER_SPREAD_RADIUS_MINIMUM,
                SettingsConstants.PLAYER_SPREAD_RADIUS_MAXIMUM
        );
    }

}

package org.carden.lockoutgames.game.setting;

import org.carden.lockoutgames.info.SettingIDS;
import org.carden.lockoutgames.info.SettingsConstants;

public class WorldSizeSetting extends NumericalSetting {

    static {settings_map.put(SettingIDS.WORLD_SIZE.getID(), new WorldSizeSetting());}

    public WorldSizeSetting() {
        super(
                SettingsConstants.WORLD_SIZE_HEADER,
                SettingsConstants.WORLD_SIZE_HELP,
                SettingsConstants.WORLD_SIZE_DEFAULT,
                SettingsConstants.WORLD_SIZE_MINIMUM,
                SettingsConstants.WORLD_SIZE_MAXIMUM
        );
    }


}

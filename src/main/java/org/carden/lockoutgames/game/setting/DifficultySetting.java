package org.carden.lockoutgames.game.setting;

import org.bukkit.Difficulty;
import org.carden.lockoutgames.info.SettingIDS;
import org.carden.lockoutgames.info.SettingsConstants;

public class DifficultySetting extends MultiChoiceSetting<Difficulty> {

    static {settings_map.put(SettingIDS.DIFFICULTY.ID(), new DifficultySetting());}

    public DifficultySetting() {
        super(SettingsConstants.DIFFICULTY_HEADER, SettingsConstants.DIFFICULTY_HELP, SettingsConstants.DIFFICULTY_DEFAULT);
    }

    @Override
    public boolean setValue(Difficulty value) {
        if(value == Difficulty.PEACEFUL) {
            return false;
        }
        this.currentValue = value;
        return true;
    }

    @Override
    public Class<?> getExpectedType() {
        return Difficulty.class;
    }

}

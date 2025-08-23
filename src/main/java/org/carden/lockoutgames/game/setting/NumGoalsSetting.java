package org.carden.lockoutgames.game.setting;

import org.carden.lockoutgames.info.SettingIDS;
import org.carden.lockoutgames.utils.GoalSelector;
import org.carden.lockoutgames.info.SettingsConstants;

public class NumGoalsSetting extends NumericalSetting{

    static {settings_map.put(SettingIDS.NUM_GOALS.getID(), new NumGoalsSetting());}

    public NumGoalsSetting() {
        super(
                SettingsConstants.NUM_GOALS_HEADER,
                SettingsConstants.NUM_GOALS_HELP,
                SettingsConstants.NUM_GOALS_DEFAULT,
                SettingsConstants.NUM_GOALS_MINIMUM,
                GoalSelector.getAllGoals().size()
        );
    }
}

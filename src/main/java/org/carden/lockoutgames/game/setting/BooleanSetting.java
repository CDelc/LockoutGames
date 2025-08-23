package org.carden.lockoutgames.game.setting;

import java.util.List;

public class BooleanSetting extends Setting<Boolean> {

    protected BooleanSetting(String name, String helpString, Boolean defaultValue) {
        this.name = name;
        this.helpString = helpString;
        this.defaultValue = defaultValue;
        this.currentValue = defaultValue;
    }

    @Override
    public boolean setValue(Boolean value) {
        currentValue = value;
        return true;
    }

    @Override
    public Boolean getCurrentValue() {
        return this.currentValue;
    }

    @Override
    public Class<?> getExpectedType() {
        return Boolean.class;
    }

    @Override
    protected boolean setSettingFromString(String s) {
        Boolean value = Boolean.parseBoolean(s);
        return this.setValue(value);
    }

    @Override
    public List<String> getValidArgs() {
        return List.of("true", "false");
    }

    @Override
    public String getSettingValuesString() {
        return "true, false";
    }
}

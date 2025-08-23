package org.carden.lockoutgames.game.setting;

import java.util.List;

public class NumericalSetting extends Setting<Integer> {

    Integer minValue;
    Integer maxValue;

    protected NumericalSetting(String name, String helpString, Integer defaultValue, Integer minValue, Integer maxValue) {
        this.name = name;
        this.helpString = helpString;
        this.defaultValue = defaultValue;
        this.currentValue = defaultValue;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    @Override
    public boolean setValue(Integer value) {
        if(value < minValue || value > maxValue) return false;
        else currentValue = value;
        return true;
    }

    @Override
    public Integer getCurrentValue() {
        return this.currentValue;
    }

    @Override
    public Class<?> getExpectedType() {
        return Integer.class;
    }

    @Override
    protected boolean setSettingFromString(String s) {
        int value;
        try {
            value = Integer.parseInt(s);
        } catch(NumberFormatException e) {
            return false;
        }
        return this.setValue(value);
    }

    @Override
    public List<String> getValidArgs() {
        return List.of();
    }

    @Override
    public String getSettingValuesString() {
        return minValue + " - " + maxValue;
    }
}

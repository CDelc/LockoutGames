package org.carden.lockoutgames.game.setting;

import java.util.Arrays;
import java.util.List;

public class MultiChoiceSetting<T extends Enum<T>> extends Setting<T> {

    protected MultiChoiceSetting(String name, String helpString, T defaultValue) {
        this.name = name;
        this.helpString = helpString;
        this.defaultValue = defaultValue;
        this.currentValue = defaultValue;
    }

    @Override
    public boolean setValue(T value) {
        this.currentValue = value;
        return true;
    }

    @Override
    public T getCurrentValue() {
        return this.currentValue;
    }

    @Override
    public Class<?> getExpectedType() {
        return Enum.class;
    }

    @Override
    protected boolean setSettingFromString(String s) {
        T value;
        try {
            value = Enum.valueOf(defaultValue.getDeclaringClass(), s.toUpperCase());
        } catch (Exception e) {
            return false;
        }
        return setValue(value);
    }

    @Override
    public List<String> getValidArgs() {
        return Arrays.stream(defaultValue.getDeclaringClass().getEnumConstants()).map(Enum::toString).toList();
    }

    @Override
    public String getSettingValuesString() {
        List<String> validArgs = this.getValidArgs();
        return String.join(", ", validArgs);
    }
}

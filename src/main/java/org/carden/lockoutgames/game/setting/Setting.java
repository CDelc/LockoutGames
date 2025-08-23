package org.carden.lockoutgames.game.setting;

import org.bukkit.ChatColor;
import org.carden.lockoutgames.LockoutGames;
import org.carden.lockoutgames.events.SettingChangeEvent;
import org.carden.lockoutgames.info.SettingIDS;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Setting<T>{

    /**
     * SETTINGS API
     * How to add a new setting:
     * 1. Create a class for said setting that implements one of the setting types (ie NumericalSetting, BooleanSetting, MultiChoiceSetting)
     * 2. Add the settingID for your setting to SettingIDS (ensure you are using a new ID number)
     * 3. Add a static block to that class that adds it to the settings_map
     * 4. Ensure your class is using the default constructor
     * 5. Define the chat argument name for your setting in SettingIDS
     * 6. Add your setting to COMMAND_ARGS map in the static block in SettingIDS
     */

    protected String name;
    protected String helpString;
    protected T defaultValue;
    protected T currentValue;

    protected static Map<Integer, Setting<?>> settings_map = new HashMap<>();

    protected Setting() {}

    /**
     * @return The display name of this setting
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return The string displayed in /settings help for this setting
     */
    public String getHelpString() {
        return this.helpString;
    }

    /**
     * @return The default value of this setting
     */
    public T getDefaultValue() {
        return defaultValue;
    }

    /**
     * @param o The new value for this setting
     * @return True if the value could be set, false if it fails due to boundary constraints or any other reason
     */
    public abstract boolean setValue(T o);

    /**
     * @return The current value this setting is set to
     */
    public abstract T getCurrentValue();

    /**
     * @return The Class object for the type of value this setting uses
     */
    public abstract Class<?> getExpectedType();

    /**
     * Set a new value for this setting, but with a string that will be read as the correct value if possible.
     * @param s The string representing the new value for this setting
     * @return True if the value was set, false if it fails
     */
    protected abstract boolean setSettingFromString(String s);

    /**
     * @return A list of valid values for this setting. Returns an empty list in the case of numbers.
     * Used for tab completion
     */
    public abstract List<String> getValidArgs();

    /**
     * @return A short string describing the valid values for this setting.
     * Used for the valid values text that can be printed from some commands
     */
    public abstract String getSettingValuesString();

    /**
     *
     * @param settingID The settingID object for the desired setting (Get from SettingIDS)
     * @return The currently assigned value to this setting
     * @param <T> Setting value type
     */
    @SuppressWarnings("unchecked")
    public static <T> T getSettingValue(SettingID<T> settingID) {
        return (T) settings_map.get(settingID.getID()).getCurrentValue();
    }

    /**
     * @param settingArg The inline argument used for the desired setting
     * @return The currently assigned value to this setting
     * @param <T> Setting value type
     */
    @SuppressWarnings("unchecked")
    public static <T> T getSettingValue(String settingArg) {
        settingArg = settingArg.toLowerCase();
        if(!isValidSetting(settingArg)) return null;
        return (T) settings_map.get(SettingIDS.COMMAND_MAPPINGS.get(settingArg).getID()).getCurrentValue();
    }

    /**
     * @param settingID The settingID object for the desired setting (Get from SettingIDS)
     * @param value The new value for this setting
     * @return True if the value was set, false if it fails
     * @param <T> Setting value type
     */
    @SuppressWarnings("unchecked")
    public static <T> boolean setSetting(SettingID<T> settingID, Object value) {
        Setting<?> tmpSetting = settings_map.get(settingID.getID());
        if(!tmpSetting.getExpectedType().isInstance(value)) {
            if(value instanceof String) {
                return tmpSetting.setSettingFromString((String) value);
            }
            else throw new IllegalArgumentException("Type mismatch for setting: " + settingID);
        }
        Setting<T> setting = (Setting <T>) tmpSetting;
        LockoutGames.getPluginInstance().getServer().getPluginManager().callEvent(new SettingChangeEvent<>(settingID, setting.getCurrentValue(), (T) value));
        return ((Setting<Object>) setting).setValue(value);
    }


    /**
     * @param settingArg The inline argument used for the desired setting
     * @param value The new value for this setting
     * @return True if the value was set, false if it fails
     */
    public static boolean setSetting(String settingArg, Object value) {
        settingArg = settingArg.toLowerCase();
        return setSetting(SettingIDS.COMMAND_MAPPINGS.get(settingArg), value);
    }

    /**
     * @return A list of all the setting objects, the objects are copied and changes to the values will not
     * affect the in-game settings
     */
    public static List<Setting<?>> getSettingList() {
        return getSettingMapCopy().values().stream().toList();
    }

    /**
     * @param settingID The settingID object for the desired setting (Get from SettingIDS)
     * @return A copy of
     * @param <T> Setting value type
     */
    @SuppressWarnings("unchecked")
    public static <T> Setting<T> getSettingObjectCopy(SettingID<T> settingID) {
        Setting<T> rValue = (Setting<T>) getSettingMapCopy().get(settingID.getID());
        if(rValue == null) throw new IllegalArgumentException("Copy attempted with invalid setting");
        return rValue;
    }

    /**
     * @param settingArg The inline argument used for the desired setting
     * @return A copy of
     * @param <T> Setting value type
     */
    @SuppressWarnings("unchecked")
    public static <T> Setting<T> getSettingObjectCopy(String settingArg) {
        settingArg = settingArg.toLowerCase();
        Setting<T> rValue = (Setting<T>) getSettingMapCopy().get(SettingIDS.COMMAND_MAPPINGS.get(settingArg).getID());
        if(rValue == null) throw new IllegalArgumentException("Copy attempted with invalid setting");
        return rValue;
    }

    /**
     * @return A SettingsImage object to preserve the current state of all the settings
     */
    public static SettingsImage saveSettings() {
        return new SettingsImage(getSettingMapCopy());
    }

    /**
     * @return The string printed in chat that lists the settings, their values, and their defaults
     */
    public static String settingsString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(ChatColor.BLUE)
                .append(ChatColor.BOLD)
                .append(ChatColor.UNDERLINE)
                .append("GAME SETTINGS\n")
                .append(ChatColor.RESET);
        for(Setting<?> setting : Setting.getSettingList()) {
            stringBuilder.append(ChatColor.AQUA)
                    .append(setting.getName())
                    .append(ChatColor.WHITE)
                    .append(": ")
                    .append(ChatColor.YELLOW)
                    .append(setting.getCurrentValue())
                    .append(ChatColor.GREEN)
                    .append(" (Default: ")
                    .append(setting.getDefaultValue())
                    .append(")")
                    .append('\n');
        }
        return stringBuilder.toString();
    }

    /**
     * @return The string printed on /settings help
     */
    public static String settingsHelpString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(ChatColor.BLUE)
                .append(ChatColor.BOLD)
                .append(ChatColor.UNDERLINE)
                .append("GAME SETTINGS HELP\n")
                .append(ChatColor.RESET);
        for(String arg : SettingIDS.COMMAND_MAPPINGS.keySet()) {
            Setting<?> setting = Setting.getSettingObjectCopy(SettingIDS.COMMAND_MAPPINGS.get(arg));
            stringBuilder.append(ChatColor.AQUA)
                    .append(arg)
                    .append(ChatColor.WHITE)
                    .append(": ")
                    .append(ChatColor.YELLOW)
                    .append(setting.getHelpString())
                    .append(ChatColor.GREEN)
                    .append(" (Default: ")
                    .append(setting.getDefaultValue())
                    .append(")")
                    .append('\n');
        }
        return stringBuilder.toString();
    }

    /**
     * @param settingArg The inline argument to check
     * @return True if there is a setting that uses the provided string as its chat argument
     */
    public static boolean isValidSetting(String settingArg) {
        return SettingIDS.COMMAND_MAPPINGS.containsKey(settingArg.toLowerCase())
                && settings_map.containsKey(SettingIDS.COMMAND_MAPPINGS.get(settingArg.toLowerCase()).getID());
    }

    private static Map<Integer, Setting<?>> getSettingMapCopy() {
        Map<Integer, Setting<?>> settings_map_copy = new HashMap<>();
        for(Integer i : settings_map.keySet()) {
            Setting<?> copy = settings_map.get(i).copy();
            settings_map_copy.put(i, copy);
        }
        return settings_map_copy;
    }

    @SuppressWarnings("unchecked")
    private Setting<T> copy() {
        try {
            Setting<T> copy = this.getClass().getDeclaredConstructor().newInstance();
            copy.setValue(this.getCurrentValue());
            return copy;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

package org.carden.lockoutgames.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.carden.lockoutgames.game.setting.SettingID;
import org.jetbrains.annotations.NotNull;

public class SettingChangeEvent<T> extends Event {

    private static final HandlerList HANDLERS = new HandlerList();
    private final SettingID<T> settingID;
    private final T fromValue;
    private final T toValue;

    public SettingChangeEvent(SettingID<T> settingID, T fromValue, T toValue) {
        this.settingID = settingID;
        this.fromValue = fromValue;
        this.toValue = toValue;
    }

    public SettingID<T> getSettingID() {
        return settingID;
    }

    public T getFromValue() {
        return fromValue;
    }

    public T getToValue() {
        return toValue;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }
}

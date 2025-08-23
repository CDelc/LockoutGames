package org.carden.lockoutgames.game.setting;

import java.util.Objects;

public class SettingID<T> {

    private final Integer ID;

    public SettingID(Integer value) {
        this.ID = value;
    }

    public Integer getID() {
        return this.ID;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof SettingID)) return false;
        else return Objects.equals(((SettingID<?>) obj).ID, this.ID);
    }
}

package org.carden.lockoutgames.game.setting;

import java.util.Objects;

public record SettingID<T>(Integer ID) {

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof SettingID)) return false;
        else return Objects.equals(((SettingID<?>) obj).ID, this.ID);
    }
}

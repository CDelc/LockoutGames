package org.carden.lockoutgames.events.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.carden.lockoutgames.LockoutGames;
import org.carden.lockoutgames.info.SettingIDS;

public class SettingsEnforcementListener implements Listener {

    private static final SettingsEnforcementListener settingsEnforcementListener = new SettingsEnforcementListener();

    private SettingsEnforcementListener() {
    }

    public static SettingsEnforcementListener getInstance() {
        return settingsEnforcementListener;
    }

    @EventHandler
    public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent e) {
        if (LockoutGames.getGame().isPresent() && LockoutGames.getGame().get().getSettings().getSetting(SettingIDS.PVP) && e.getDamager() instanceof Player && e.getEntity() instanceof Player) {
            e.setCancelled(true);
        }
    }
}

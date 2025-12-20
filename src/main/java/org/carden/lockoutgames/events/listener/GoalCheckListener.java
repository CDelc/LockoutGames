package org.carden.lockoutgames.events.listener;

import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.carden.lockoutgames.game.GameBuilder;

import java.util.HashSet;
import java.util.Set;

public class GoalCheckListener implements Listener {

    Set<Class<? extends Event>> eventsToListenFor;

    private static final GoalCheckListener goalCheckListener = new GoalCheckListener();

    private GoalCheckListener() {
        this.eventsToListenFor = new HashSet<>();
    }

    public static GoalCheckListener getInstance() {
        return goalCheckListener;
    }

    public void enableEventListener(Class<? extends Event> eventClass) {
        eventsToListenFor.add(eventClass);
    }

    public void clearEventListeners() {
        eventsToListenFor.clear();
    }

    @EventHandler
    public void onEntityPickupItemEvent(EntityPickupItemEvent e) {
        sendGoalCheck(EntityPickupItemEvent.class, e);
    }

    @EventHandler
    public void onInventoryClickEvent(InventoryClickEvent e) {
        sendGoalCheck(InventoryClickEvent.class, e);
    }

    private void sendGoalCheck(Class<? extends Event> eventClass, Event e) {
        if(!eventsToListenFor.contains(eventClass)) return;
        GameBuilder gameBuilder = GameBuilder.getGameBuilder();
        if(gameBuilder.getGame().isPresent()) {
            gameBuilder.getGame().get().checkGoals(e);
        }
    }
}

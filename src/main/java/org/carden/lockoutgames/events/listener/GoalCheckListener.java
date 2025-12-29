package org.carden.lockoutgames.events.listener;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFertilizeEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.EntityTameEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.carden.lockoutgames.LockoutGames;
import org.carden.lockoutgames.game.Debug;
import org.carden.lockoutgames.goal.Goal;

import java.util.Arrays;
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
        if (Debug.isActive() && !GoalCheckListener.hasEventHandlerFor(eventClass)) {
            LockoutGames.broadcastDebug("Missing GoalCheckListener event handler for " + eventClass.getName());
        }
        eventsToListenFor.add(eventClass);
    }

    public void enableEventListener(Set<Class<? extends Event>> eventClasses) {
        eventClasses.forEach(this::enableEventListener);
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

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        sendGoalCheck(PlayerMoveEvent.class, e);
    }

    @EventHandler
    public void onBlockFertilize(BlockFertilizeEvent e) {
        sendGoalCheck(BlockFertilizeEvent.class, e);
    }

    @EventHandler
    public void onEntityChangeBlock(EntityChangeBlockEvent e) {
        sendGoalCheck(EntityChangeBlockEvent.class, e);
    }

    @EventHandler
    public void onEntityTame(EntityTameEvent e) {
        sendGoalCheck(EntityTameEvent.class, e);
    }

    private void sendGoalCheck(Class<? extends Event> eventClass, Event e) {
        if(!eventsToListenFor.contains(eventClass)) return;
        if(LockoutGames.getGame().isPresent()) {
            LockoutGames.getGame().get().checkGoals(e);
        }
        if(Debug.isActive()) {
            Debug.checkGoals(e);
        }
    }

    private static <T> boolean hasEventHandlerFor(Class<T> event) {
        return Arrays.stream(GoalCheckListener.getInstance().getClass().getMethods())
                .filter((method) -> method.getDeclaredAnnotation(EventHandler.class) != null)
                .anyMatch((method) -> {
                    Class<?>[] parameters = method.getParameterTypes();
                    return parameters.length == 1 && parameters[0].isAssignableFrom(event);
                });
    }
}

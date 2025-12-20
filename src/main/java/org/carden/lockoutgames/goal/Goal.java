package org.carden.lockoutgames.goal;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.carden.lockoutgames.LockoutGames;
import org.carden.lockoutgames.events.GoalCompleteEvent;
import org.carden.lockoutgames.events.listener.GoalCheckListener;
import org.carden.lockoutgames.game.player.GamePlayer;

import java.util.HashSet;
import java.util.Set;

public abstract class Goal {

    protected final Set<Class<? extends Event>> checkEvents;

    protected boolean failedToGenerate = false;
    protected Set<String> uniquenessStrings;
    protected Set<GoalType> goalTypes;
    protected String description = "";
    protected GoalDifficulty goalDifficulty = GoalConstants.DEFAULT_DIFFICULTY;
    protected boolean canGenerateMultiple = false;

    protected Goal(Set<Class<? extends Event>> checkEvents) {
        this.checkEvents = checkEvents;
        for(Class<? extends Event> eventClass : checkEvents) {
            GoalCheckListener.getInstance().enableEventListener(eventClass);
        }
        this.uniquenessStrings = new HashSet<>();
        this.goalTypes = new HashSet<>();
    }

    /**
     * Public-facing method used to check goals. Wrapper for the private checkEvent method.
     * This does the following before invoking the private method:
     * 1. Checks that the goal is in a valid usable state (failedToGenerate variable)
     * 2. Checks that the event being passed is on the list of events this goal is listening for
     * 3. Schedules the check to run on the next available tick on the main server thread.
     * @param e - The event triggering the check
     */
    public void check(Event e) {
        if(failedToGenerate || !checkEvents.contains(e.getClass())) return;
        Bukkit.getScheduler().runTask(LockoutGames.getPluginInstance(), () -> {
            this.checkEvent(e);
        });
    }

    /**
     * Assumes the conditions to check the goal are valid.
     * This should invoke a GoalCompleteEvent if the conditions to complete this goal are met.
     * @param e - The event triggering the check
     */
    protected abstract void checkEvent(Event e);

    /**
     * Will try to change this goal to match the desired difficulty. If this cannot be done, returns false.
     * Returns true if successful
     */
    protected boolean forceDifficulty(GoalDifficulty goalDifficulty) {
        return goalDifficulty.isEqualTo(this.goalDifficulty);
    }

    public final boolean regenerateWithDifficulty(GoalDifficulty goalDifficulty) {
        if(!this.forceDifficulty(goalDifficulty)) {
            this.failedToGenerate = true;
            return false;
        } else return true;
    }

    /**
     * Fires a GoalCompleteEvent for this goal with the given GamePlayer. Will not fire if the GamePlayer is a spectator
     * @param p
     */
    protected void fireGoalCompleteEvent(GamePlayer p) {
        if(!p.isSpectator()) {
            LockoutGames.getPluginInstance().getServer().getPluginManager().callEvent(new GoalCompleteEvent(p, this));
        }
    }

    /**
     * @return true if the goal should not be used in a game
     */
    public boolean failedToGenerate() {
        return failedToGenerate;
    }

    /**
     * @return The difficulty of this goal in accordance with the constants in GoalConstants.GoalDifficulties
     */
    public GoalDifficulty getGoalDifficulty() {
        return goalDifficulty;
    }

    /**
     * @return The description for this goal that should be displayed in game
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return Used by the goal selector to ensure this goal is unique.
     * Mainly for resolving conflicts this goal may have with itself if multiple versions may be generated.
     */
    public Set<String> getUniquenessStrings() {
        return uniquenessStrings;
    }

    /**
     * @return Used by the goal generated to ensure this goal is not used if another goal with the same conflict code has already been used in the game.
     * The goal should not conflict with itself
     */
    public Set<GoalType> getGoalTypes() {
        return goalTypes;
    }

    /**
     * @return Tells the goal selector whether it can pick this goal again (provided the uniqueness strings do not conflict)
     */
    public boolean canGenerateMultiple() {
        return this.canGenerateMultiple;
    }

    /**
     * @return The set of event classes that this goal should be checked with
     */
    public Set<Class<? extends Event>> getCheckEvents() {
        return checkEvents;
    }

    /**
     * List of every goal class that can be picked for a game
     */
    public enum GoalClass {
        OBTAIN_LUSH_SINGLE(ObtainLushSingle.class),
        OBTAIN_LUSH_MULTIPLE(ObtainLushMultiple.class);

        public final Class<? extends Goal> classObject;
        GoalClass(Class<? extends Goal> goalClass) {
            this.classObject = goalClass;
        }
    }

}

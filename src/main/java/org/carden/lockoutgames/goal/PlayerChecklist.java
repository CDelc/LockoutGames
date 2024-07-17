package org.carden.lockoutgames.goal;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.HashSet;

public class PlayerChecklist<T> {
    /**
     * Tool for goals that use AND logic that allows tracking of progress on a goal.
     */

    HashMap<Player, HashSet<T>> tracker;
    HashSet<T> list;

    PlayerChecklist(HashSet<T> list) {
        this.tracker = new HashMap<>();
        this.list = list;
    }

    public boolean checkItem(Player p, Material m) {
        if(!tracker.containsKey(p)) {
            tracker.put(p, list);
        }
        HashSet<T> tmp = tracker.get(p);
        tmp.remove(m);
        tracker.put(p, tmp);
        return tmp.isEmpty();
    }
}


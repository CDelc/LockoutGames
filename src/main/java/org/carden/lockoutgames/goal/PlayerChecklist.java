package org.carden.lockoutgames.goal;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class PlayerChecklist<T> {

    HashMap<Player, HashSet<T>> tracker;
    HashSet<T> list;

    PlayerChecklist(ArrayList<T> list) {
        this.tracker = new HashMap<Player, HashSet<T>>();
        this.list = new HashSet<T>(list);
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


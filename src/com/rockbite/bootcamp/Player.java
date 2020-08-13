package com.rockbite.bootcamp;

import java.util.HashMap;
import java.util.Map;

/**
 * Player or buyer who makes transaction in the shop
 */
public class Player {

    //unique identifier
    private int id;

    //items that player holds
    private Map<Integer, Item> items = new HashMap<>();

    public int getId() {
        return id;
    }

    public Player(final int id) {
        this.id = id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public Map<Integer, Item> getItems() {
        return items;
    }

    public void setItems(final Map<Integer, Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", items=" + items +
                '}';
    }
}

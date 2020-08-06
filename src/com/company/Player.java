package com.company;

import java.util.ArrayList;
import java.util.List;

/**
 * Player or buyer who makes transaction in the shop
 */
public class Player {

    //unique identifier
    private int id;

    //items that player holds
    private List<Item> items = new ArrayList<>();

    public int getId() {
        return id;
    }

    public Player(final int id) {
        this.id = id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(final List<Item> items) {
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

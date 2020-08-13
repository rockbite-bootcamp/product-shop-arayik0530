package com.rockbite.bootcamp.util;

import com.rockbite.bootcamp.Item;

import java.util.Map;

/**
 * Helper class that holds arguments for calling transfer method in ShopImpl class
 * to make method call less complicated
 */
public class ArgumentsHolder {

    private final Map<Integer, Item> mapTo;
    private final Map<Integer, Item> mapFrom;
    private final int requiredItemId;
    private final int count;

    public ArgumentsHolder(final Map<Integer, Item> mapTo, final Map<Integer, Item> mapFrom, final int requiredItemId, final int count) {
        this.mapTo = mapTo;
        this.mapFrom = mapFrom;
        this.requiredItemId = requiredItemId;
        this.count = count;
    }

    public Map<Integer, Item> getMapTo() {
        return mapTo;
    }

    public Map<Integer, Item> getMapFrom() {
        return mapFrom;
    }

    public int getRequiredItemId() {
        return requiredItemId;
    }

    public int getCount() {
        return count;
    }
}

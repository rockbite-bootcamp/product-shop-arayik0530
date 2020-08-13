package com.rockbite.bootcamp;

import java.util.Objects;

/**
 * Item class is to describe items in the shop
 */
public class Item {

    //unique identifier
    private final int id;

    //Type of the item
    private final ItemType itemType;

    //count of things of the same type in the item
    private int count;

    public Item(final int id, final ItemType itemType) {
        this.id = id;
        this.itemType = itemType;
        this.count = 0;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public int getCount() {
        return count;
    }

    public void setCount(final int count) {
        this.count = count;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", type=" + itemType +
                ", count=" + count +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        final Item item = (Item) o;
        return getId() == item.getId() &&
                getCount() == item.getCount() &&
                Objects.equals(getItemType(), item.getItemType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getItemType(), getCount());
    }
}



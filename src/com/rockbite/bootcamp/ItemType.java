package com.rockbite.bootcamp;

/**
 * Type of Items
 */
public class ItemType {

    //name of type
    private String name;

    public ItemType(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Type{" +
                "name='" + name + '\'' +
                '}';
    }
}

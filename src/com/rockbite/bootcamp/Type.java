package com.rockbite.bootcamp;

import java.io.Serializable;

/**
 * Type of Items
 * it also implements Serializable like Item class, because it is part of Item class
 */
public class Type implements Serializable {

    //name of type
    private String name;

    public Type(final String name) {
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

package com.rockbite.bootcamp;

/**
 * This class is used to describe product's category
 */
public class Category {

    //unique identifier
    private int id;

    //category's name
    private String name;

    public String getName() {
        return name;
    }

    public Category(final String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

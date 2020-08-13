package com.rockbite.bootcamp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Product which must be sold in the shop
 */
public class Product {

    //unique identifier
    private int id;

    //category of the product
    private Category category;

    //payload of the product
    private Item[] payload;

    //cost of the product
    private Map<Integer, Item> cost = new HashMap<>();

    //flag that describes whether the Product is sold or not
    private boolean isSold = false;

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(final Category category) {
        this.category = category;
    }

    public Item[] getPayload() {
        return payload;
    }

    public void setPayload(final Item[] payload) {
        this.payload = payload;
    }

    public Map<Integer, Item> getCost() {
        return cost;
    }

    public void setCost(final Map<Integer, Item> cost) {
        this.cost = cost;
    }


    public boolean isSold() {
        return isSold;
    }

    public void setSold(final boolean sold) {
        isSold = sold;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", category=" + category +
                ", payload=" + Arrays.toString(payload) +
                ", cost=" + cost +
                '}';
    }
}

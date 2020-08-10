package com.company;

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
    private Map<Integer, Item> payload = new HashMap<>();

    //cost of the product
    private Map<Integer, Item> cost = new HashMap<>();


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

    public Map<Integer, Item> getPayload() {
        return payload;
    }

    public void setPayload(final Map<Integer, Item> payload) {
        this.payload = payload;
    }

    public Map<Integer, Item> getCost() {
        return cost;
    }

    public void setCost(final Map<Integer, Item> cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", category=" + category +
                ", payload=" + payload +
                ", cost=" + cost +
                '}';
    }
}

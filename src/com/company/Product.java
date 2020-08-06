package com.company;

import java.util.ArrayList;
import java.util.List;

/**
 * Product which must be sold in the shop
 */
public class Product {

    //unique identifier
    private int id;

    //category of the product
    private Category category;

    //payload of the product
    private List<Item> payload = new ArrayList<>();

    //cost of the product
    private List<Item> cost = new ArrayList<>();


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

    public List<Item> getPayload() {
        return payload;
    }

    public void setPayload(final List<Item> payload) {
        this.payload = payload;
    }

    public List<Item> getCost() {
        return cost;
    }

    public void setCost(final List<Item> cost) {
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

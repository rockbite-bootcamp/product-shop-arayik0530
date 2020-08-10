package com.rockbite.bootcamp;

import java.io.IOException;
import java.util.List;

/**
 * Shop API
 */
public interface IShop {

    /**
     * @return list of products available in the shop
     */
    List<Product> showProducts();

    /**
     * Player can buy a product from the shop via this method
     *
     * @param buyer
     * @param product
     */
    void buy(Player buyer, Product product) throws IOException, ClassNotFoundException;

    /**
     * Adding products to the shop initially
     *
     * @param product
     */
    void add(Product product);

}

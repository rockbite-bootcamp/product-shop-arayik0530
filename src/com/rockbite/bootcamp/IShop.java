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
    List<Product> getAvailableProducts();

    /**
     * Player can buy a product from the shop via this method
     * @param buyer Player
     * @param productId unique id of product
     * @throws TransactionFailedException TransactionFailedException
     */
    void buy(Player buyer, int productId);

    /**
     * Adding products to the shop initially
     * @param productId unique id of product
     * @param product product
     */
    void addProduct(final int productId, final Product product);

    void refund(Player buyer, int productId)  throws Exception;
}

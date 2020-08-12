package com.rockbite.bootcamp.util.command;

import com.rockbite.bootcamp.Player;
import com.rockbite.bootcamp.ShopImpl;
import com.rockbite.bootcamp.util.pool.Poolable;

/**
 * Command for buying a product from the shop
 */
public class BuyCommand implements Command, Poolable {
    /**
     * undo buying method
     *
     * @param buyer     Player
     * @param productId unique id of the Product
     */
    @Override
    public void undo(Player buyer, int productId) {
        ShopImpl.getInstance().refund(buyer, productId);
    }

    /**
     * buying method
     *
     * @param buyer     Player
     * @param productId unique id of the Product
     */
    @Override
    public void execute(Player buyer, int productId) {
        ShopImpl.getInstance().buy(buyer, productId);
    }

    /**
     * useless method derived from Poolable interface
     */
    @Override
    public void reset() {
        //
    }
}

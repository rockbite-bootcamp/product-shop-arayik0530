package com.rockbite.bootcamp.util.command;

import com.rockbite.bootcamp.Player;
import com.rockbite.bootcamp.ShopImpl;
import com.rockbite.bootcamp.util.pool.Poolable;

/**
 * Command for undo buying a product from the shop
 */
public class RefundCommand implements Command, Poolable {

    /**
     * undo buying method
     * @param buyer Player
     * @param productId unique id of the Product
     */
    @Override
    public void execute(Player buyer, int productId) {
        ShopImpl.getInstance().refund(buyer, productId);
    }

    /**
     * undo undone buying method
     * @param buyer Player
     * @param productId unique id of the Product
     */
    @Override
    public void undo(Player buyer, int productId) {
        ShopImpl.getInstance().buy(buyer, productId);
    }

    /**
     * useLess method from Poolable interface
     */
    @Override
    public void reset() {
        //
    }
}

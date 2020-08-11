package com.rockbite.bootcamp.util.command;

import com.rockbite.bootcamp.Player;
import com.rockbite.bootcamp.Product;
import com.rockbite.bootcamp.ShopImpl;
import com.rockbite.bootcamp.util.pool.Poolable;

/**
 * Command for undo buying a product from the shop
 */
public class UndoBuyCommand implements Command, Poolable {

    /**
     * undo buying method
     * @param buyer Player
     * @param product Product
     * @throws Exception
     */
    @Override
    public void execute(Player buyer, Product product) throws Exception {
        ShopImpl.getInstance().undoBuy(buyer, product);
    }

    /**
     * undo undone buying method
     * @param buyer Player
     * @param product Product
     * @throws Exception
     */
    @Override
    public void undo(Player buyer, Product product) throws Exception {
        ShopImpl.getInstance().buy(buyer, product);
    }

    /**
     * useLess method from Poolable interface
     */
    @Override
    public void reset() {
        //
    }
}

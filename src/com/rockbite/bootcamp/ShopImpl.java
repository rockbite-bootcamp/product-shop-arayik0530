package com.rockbite.bootcamp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * implementation of the IShop interface, represents shop API
 * in current version it have to be a singleton to make easy to implement command pattern on it
 */
public class ShopImpl implements IShop {

    private static ShopImpl instance;

    //items taken from buyers
    private Map<Integer, Item> itemsInCashDesk;

    //products to be sold in the shop
    private List<Product> products;

    private Map<Integer, Product> soldProducts;

    private ShopImpl() {
        this.products = new ArrayList<>();
        this.soldProducts = new HashMap<>();
        this.itemsInCashDesk = new HashMap<>();
    }

    public static ShopImpl getInstance() {
        if (instance == null)
            instance = new ShopImpl();
        return instance;
    }

    @Override
    public List<Product> showProducts() {
        return this.products;
    }

    /**
     * with this method the PLayer/buyer buys a product from the shop
     * @param buyer PLayer
     * @param product Product which is being sold in the shop
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Override
    public void buy(final Player buyer, final Product product) throws IOException, ClassNotFoundException {

        //deep clone of Player's items
        Map<Integer, Item> copyOfPlayerItems = new HashMap<>();
        for (Map.Entry<Integer, Item> playerItem : buyer.getItems().entrySet()) {
            copyOfPlayerItems.put(playerItem.getKey(), playerItem.getValue().deepClone());
        }

        //deep clone of items in the cash desk
        Map<Integer, Item> copyOfCashDesk = new HashMap<>();
        for (Map.Entry<Integer, Item> cashDeskItem : this.itemsInCashDesk.entrySet()) {
            copyOfCashDesk.put(cashDeskItem.getKey(), cashDeskItem.getValue().deepClone());
        }

        //deep clone of product's payload
        Map<Integer, Item> copyOfPayloadItems = new HashMap<>();
        for (Map.Entry<Integer, Item> payloadItem : product.getPayload().entrySet()) {
            copyOfPayloadItems.put(payloadItem.getKey(), payloadItem.getValue().deepClone());
        }

        for (int costItemId : product.getCost().keySet()) {

            Item buyerItem = buyer.getItems().get(costItemId);
            Item costItem = product.getCost().get(costItemId);

            if (buyer.getItems().containsKey(costItemId)) {
                if (buyerItem.getCount() >= costItem.getCount()) {
                    this.transfer(this.itemsInCashDesk, buyer.getItems(), costItemId, costItem.getCount());
                } else {
                    //rollBack
                    buyer.setItems(copyOfPlayerItems);
                    this.itemsInCashDesk = copyOfCashDesk;

                    throw new TransactionFailedException("The player does not have enough count of required item " +
                            product.getCost().get(costItemId));
                }
            } else {
                //rollBack
                buyer.setItems(copyOfPlayerItems);
                this.itemsInCashDesk = copyOfCashDesk;

                throw new TransactionFailedException("The player does not have required item" +
                        costItem);
            }
        }

        //adding product's payload to player's items
        // iterating over copyOfPayload instead of it to avoid ConcurrentModificationException
        for (int payloadItemId : copyOfPayloadItems.keySet()) {
            this.transfer(buyer.getItems(), product.getPayload(),
                    payloadItemId, product.getPayload().get(payloadItemId).getCount());
        }

        // removing sold product from the product list and adding it in sold products
        product.setPayload(copyOfPayloadItems);
        this.soldProducts.put(product.getId(), product);
        this.products.remove(product);
    }

    /**
     * helper method: transfers the whole item or some count of it from one map to another
     *
     * @param mapTo          increasing map
     * @param mapFrom        decreasing map
     * @param requiredItemId key for entry
     */
    private void transfer(final Map<Integer, Item> mapTo, final Map<Integer, Item> mapFrom, final int requiredItemId,
                          final int count) {
        if (!mapTo.containsKey(requiredItemId)) {
            Item tempItem = new Item(requiredItemId, mapFrom.get(requiredItemId).getType());
            mapTo.put(requiredItemId, tempItem);
        }

        Item decreasedItem = mapFrom.get(requiredItemId);
        decreasedItem.setCount(decreasedItem.getCount() - count);

        Item increasedItem = mapTo.get(requiredItemId);
        increasedItem.setCount(increasedItem.getCount() + count);

        if (decreasedItem.getCount() == 0) {
            mapFrom.remove(requiredItemId);
        }
    }

    @Override
    public void add(final Product product) {
        this.products.add(product);
    }

    /**
     * right vice versa method for method buy
     * @param buyer PLayer
     * @param product Product which is being sold in the shop
     * @throws Exception
     */
    @Override
    public void undoBuy(final Player buyer, final Product product) throws Exception {


        for (int costItemId : product.getCost().keySet()) {
            this.transfer(buyer.getItems(), this.itemsInCashDesk, costItemId,
                    product.getCost().get(costItemId).getCount());
        }

        this.products.add(this.soldProducts.remove(product.getId()));

        for (int payloadItemId : product.getPayload().keySet()) {

            this.transfer(new HashMap<>(), buyer.getItems(), payloadItemId,
                    product.getPayload().get(payloadItemId).getCount());
        }
    }


    public void setProducts(final List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "itemsInCashDesk=" + itemsInCashDesk +
                ", products=" + products +
                ", soldProducts=" + soldProducts +
                '}';
    }
}
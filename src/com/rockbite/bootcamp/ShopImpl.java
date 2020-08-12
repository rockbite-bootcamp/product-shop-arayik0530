package com.rockbite.bootcamp;

import com.rockbite.bootcamp.util.ArgumentsHolder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * implementation of the IShop interface, represents shop API
 * in current version it have to be a singleton to make easy to implement command pattern on it
 */
public class ShopImpl implements IShop {

    //Single instance for the whole application
    private static ShopImpl instance;

    //items taken from buyers
    private final Map<Integer, Item> itemsInCashDesk;

    //products to be sold in the shop
    private final Map<Integer, Product> products;

    private ShopImpl() {
        this.products = new HashMap<>();
        this.itemsInCashDesk = new HashMap<>();
    }

    public static ShopImpl getInstance() {
        if (instance == null)
            instance = new ShopImpl();
        return instance;
    }

    @Override
    public List<Product> getAvailableProducts() {
        return this.products.values().stream().filter(p -> !p.isSold()).collect(Collectors.toList());
    }

    /**
     * with this method the PLayer/buyer buys a product from the shop
     * @param buyer PLayer
     * @param productId id of Product which is being sold in the shop
     */
    @Override
    public void buy(final Player buyer, final int productId) {
        //product with id productId
        final Product product = this.products.get(productId);

        // argumentsHolderList. transfer method will be called with every element of this list in the end
        List<ArgumentsHolder> argumentsHolderList = new ArrayList<>();

        for (int costItemId : product.getCost().keySet()) {

            Item buyerItem = buyer.getItems().get(costItemId);
            Item costItem = product.getCost().get(costItemId);

            if (buyer.getItems().containsKey(costItemId)) {
                if (buyerItem.getCount() >= costItem.getCount()) {
                    argumentsHolderList.add(new ArgumentsHolder(this.itemsInCashDesk, buyer.getItems(),
                            costItemId, costItem.getCount()));
                } else {

                    throw new TransactionFailedException("The player does not have enough count of required item " +
                            product.getCost().get(costItemId));
                }
            } else {

                throw new TransactionFailedException("The player does not have required item" +
                        costItem);
            }
        }

        //getting a map from payload
        Map<Integer, Item> payloadMap = new HashMap<>();
        for (int i = 0; i < product.getPayload().length; i++) {
            payloadMap.put(product.getPayload()[i].getId(), product.getPayload()[i]);
        }

        //adding product's payload to player's items
        for (int payloadItemId : payloadMap.keySet()) {
            this.transferPayload(new ArgumentsHolder(buyer.getItems(), payloadMap,
                    payloadItemId, payloadMap.get(payloadItemId).getCount()));
        }

        // removing sold product from the available productList and adding it in sold products
        product.setSold(true);

        //after checking above that current player can buy current product we can call transfer method
        for (ArgumentsHolder argumentsHolder: argumentsHolderList) {
            this.transfer(argumentsHolder);
        }
    }

    /**
     * helper method: transfers the whole item or some count of it from one map to another
     * @param argumentsHolder holds: mapTo - increasing map, mapFrom - decreasing map,
     *                        requiredItemId - key for entry, count - count of transferring item
     */
    private void transfer(final ArgumentsHolder argumentsHolder) {
        if (!argumentsHolder.getMapTo().containsKey(argumentsHolder.getRequiredItemId())) {
            Item tempItem = new Item(argumentsHolder.getRequiredItemId(),
                    argumentsHolder.getMapFrom().get(argumentsHolder.getRequiredItemId()).getItemType());
            argumentsHolder.getMapTo().put(argumentsHolder.getRequiredItemId(), tempItem);
        }

        Item decreasedItem = argumentsHolder.getMapFrom().get(argumentsHolder.getRequiredItemId());
        decreasedItem.setCount(decreasedItem.getCount() - argumentsHolder.getCount());

        Item increasedItem = argumentsHolder.getMapTo().get(argumentsHolder.getRequiredItemId());
        increasedItem.setCount(increasedItem.getCount() + argumentsHolder.getCount());

        if (decreasedItem.getCount() == 0) {
            argumentsHolder.getMapFrom().remove(argumentsHolder.getRequiredItemId());
        }
    }

    /**
     * helper method: transfers the whole item or some count of it from one map to another
     * but the decreasing map actually does'nt lose any Item or any count of Item
     * @param argumentsHolder holds: mapTo - increasing map, mapFrom - decreasing map,
     *                        requiredItemId - key for entry, count - count of transferring item
     */
    private void transferPayload(final ArgumentsHolder argumentsHolder){
        if (!argumentsHolder.getMapTo().containsKey(argumentsHolder.getRequiredItemId())) {
            Item tempItem = new Item(argumentsHolder.getRequiredItemId(),
                    argumentsHolder.getMapFrom().get(argumentsHolder.getRequiredItemId()).getItemType());
            argumentsHolder.getMapTo().put(argumentsHolder.getRequiredItemId(), tempItem);
        }

        Item increasedItem = argumentsHolder.getMapTo().get(argumentsHolder.getRequiredItemId());
        increasedItem.setCount(increasedItem.getCount() + argumentsHolder.getCount());
    }

    @Override
    public void addProduct(final int productId, final Product product) {
        this.products.put(product.getId(), product);
    }

    /**
     * right vice versa method for method buy
     * @param buyer PLayer
     * @param productId unique id of the Product which is being returned to the shop
     */
    @Override
    public void refund(final Player buyer, final int productId) {
        //product with id productId
        final Product product = this.products.get(productId);

        // argumentsHolderList. transfer method will be called with every element of this list in the end
        List<ArgumentsHolder> argumentsHolderList = new ArrayList<>();

        for (int costItemId : product.getCost().keySet()) {
            argumentsHolderList.add(new ArgumentsHolder(buyer.getItems(), this.itemsInCashDesk, costItemId,
                    product.getCost().get(costItemId).getCount()));
        }

        //getting a map from payload
        Map<Integer, Item> payloadMap = new HashMap<>();
        for (int i = 0; i < product.getPayload().length; i++) {
            payloadMap.put(product.getPayload()[i].getId(), product.getPayload()[i]);
        }

        //pseudo map
        Map<Integer, Item> tempMap = new HashMap<>();

        for (int payloadItemId : payloadMap.keySet()) {

            argumentsHolderList.add(new ArgumentsHolder(tempMap, buyer.getItems(), payloadItemId,
                    payloadMap.get(payloadItemId).getCount()));
        }

        //calling transfer method for appropriate items and maps to complete refund method
        for (ArgumentsHolder argumentsHolder: argumentsHolderList) {
            this.transfer(argumentsHolder);
        }

        // removing returned product from the sold products to available productList
        product.setSold(false);
    }


    @Override
    public String toString() {
        return "Shop{" +
                "itemsInCashDesk=" + itemsInCashDesk +
                ", products=" + products.values().stream().filter(p -> !p.isSold()).collect(Collectors.toList()) +
                ", soldProducts=" + products.values().stream().filter(Product::isSold).collect(Collectors.toList()) +
                '}';
    }
}
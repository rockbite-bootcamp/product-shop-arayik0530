package com.company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * implementation of the IShop interface, represents shop API
 */
public class ShopImpl implements IShop {

    //items taken from buyers
    private List<Item> itemsInCashDesk;

    //products to be sold in the shop
    private List<Product> products;

    public ShopImpl() {
        this.products = new ArrayList<>();
        this.itemsInCashDesk = new ArrayList<>();
    }

    @Override
    public List<Product> showProducts() {
        return this.products;
    }

    @Override
    public void buy(final Player buyer, final Product product) throws IOException, ClassNotFoundException {
        //deep clone of Product's payload
        List<Item> copyOfPayloadItems = new ArrayList<>();
        for (Item payloadItem : product.getPayload()) {
            copyOfPayloadItems.add(payloadItem.deepClone());
        }

        //deep clone of Player's items
        List<Item> copyOfPlayerItems = new ArrayList<>();
        for (Item playerItem : buyer.getItems()) {
            copyOfPlayerItems.add(playerItem.deepClone());
        }

        //deep clone of items in the cash desk
        List<Item> copyOfCashDesk = new ArrayList<>();
        for (Item item : this.itemsInCashDesk) {
            copyOfCashDesk.add(item.deepClone());
        }

        //temp list is added to avoid concurrentModificationException
        List<Item> tempItemsToRemove = new ArrayList<>();

        for (Item costItem : product.getCost()) {
            int count = 0;
            for (Item playerItem : buyer.getItems()) {
                if (costItem.getId() == playerItem.getId()) {
                    ++count;
                    if (playerItem.getCount() - costItem.getCount() >= 0) {
                        playerItem.setCount(playerItem.getCount() - costItem.getCount());
                        if (playerItem.getCount() == 0) {
                            tempItemsToRemove.add(playerItem);
                        }
                    } else {

                        //rollback
                        product.setPayload(copyOfPayloadItems);
                        buyer.setItems(copyOfPlayerItems);
                        this.itemsInCashDesk = copyOfCashDesk;
                        //rollback

                        throw new TransactionFailedException("The player does not have enough count of required item " +
                                costItem);
                    }
                }
            }
            if (count == 0) {
                //rollback
                product.setPayload(copyOfPayloadItems);
                buyer.setItems(copyOfPlayerItems);
                this.itemsInCashDesk = copyOfCashDesk;
                //rollback
                throw new TransactionFailedException("The player does not have required item" +
                        costItem);
            }
        }

        buyer.getItems().removeAll(tempItemsToRemove);

        //adding bought product items to player item list start--->

        //temp set is added to avoid concurrentModificationException
        Set<Item> tempItemsToAdd = new HashSet<>();

        for (Item payloadItem : product.getPayload()) {

            for (Item playerItem : buyer.getItems()) {
                if (payloadItem.getId() == playerItem.getId()) {
                    playerItem.setCount(playerItem.getCount() + payloadItem.getCount());
                } else {
                    tempItemsToAdd.add(payloadItem);
                }
            }
        }

        buyer.getItems().addAll(tempItemsToAdd);
        //adding bought product items to player item list <---end

        //removing sold product from the shop
        this.products.remove(product);

        //adding cost payload to the cash desk

        if (itemsInCashDesk.size() == 0) {
            itemsInCashDesk.addAll(product.getCost());
        } else {
            //temp set is added to avoid concurrentModificationException
            Set<Item> tempItemsToAddInDesk = new HashSet<>();

            for (Item costItem : product.getCost()) {

                for (Item deskItem : itemsInCashDesk) {

                    if (costItem.getId() == deskItem.getId()) {
                        deskItem.setCount(costItem.getCount() + deskItem.getCount());
                    } else {
                        tempItemsToAddInDesk.add(costItem);
                    }

                }
            }

            this.itemsInCashDesk.addAll(tempItemsToAddInDesk);
        }
    }

    @Override
    public void add(final Product product) {
        this.products.add(product);
    }


    public void setProducts(final List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "itemsInCashDesk=" + itemsInCashDesk +
                ", products=" + products +
                '}';
    }
}
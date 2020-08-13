package com.rockbite.bootcamp;

import com.rockbite.bootcamp.util.command.CommandManager;
import com.rockbite.bootcamp.util.command.PurchaseCommand;
import com.rockbite.bootcamp.util.command.RefundCommand;
import com.rockbite.bootcamp.util.observer.ObservationSubject;
import com.rockbite.bootcamp.util.observer.TransactionObserver;
import com.rockbite.bootcamp.util.pool.Pool;

/**
 * The main class to bootstrap SHOP API
 */
public class Main {

    public static void main(String[] args) {
        IShop shop = ShopImpl.getInstance();

        ObservationSubject transactionSubject = new ObservationSubject(shop.getAvailableProducts().size());
        TransactionObserver transactionObserver = new TransactionObserver(transactionSubject);

        ItemType typePhone = new ItemType("phone");
        ItemType typeWeapon = new ItemType("weapon");

        Item iPhone = new Item(1, typePhone);
        iPhone.setCount(1);

        Item pistol = new Item(2, typeWeapon);
        pistol.setCount(1);

        Product product1 = new Product();
        product1.setId(1);
        Item[] payload = new Item[2];
        payload[0] = iPhone;
        payload[1] = pistol;
        product1.setPayload(payload);

        Category sale = new Category("SALE");
        product1.setCategory(sale);

        ItemType typeMoney = new ItemType("money");
        ItemType typeCar = new ItemType("car");
        ItemType typeShoe = new ItemType("shoe");

        Item AMD = new Item(3, typeMoney);
        Item BMW_1 = new Item(4, typeCar);
        Item shoe_1 = new Item(5, typeShoe);

        AMD.setCount(100);
        BMW_1.setCount(2);
        shoe_1.setCount(4);

        Item BMW_2 = new Item(4, typeCar);
        Item shoe_2 = new Item(5, typeShoe);

        BMW_2.setCount(1);
        shoe_2.setCount(2);

        product1.getCost().put(BMW_2.getId(), BMW_2);
        product1.getCost().put(shoe_2.getId(), shoe_2);


        shop.addProduct(product1.getId(), product1);
        transactionSubject.setState(shop.getAvailableProducts().size());


        System.out.println("Shop before any transaction");//TODO must be removed
        System.out.println(shop); //TODO must be removed


        Player player1 = new Player(1);

        player1.getItems().put(AMD.getId(), AMD);
        player1.getItems().put(BMW_1.getId(), BMW_1);
        player1.getItems().put(shoe_1.getId(), shoe_1);

        System.out.println("Player before any transaction");//TODO must be removed
        System.out.println(player1); //TODO must be removed
        System.out.println("-------------------------------------------------------------------------------");//TODO must be removed

        CommandManager commandManager = new CommandManager();

        Pool<PurchaseCommand> buyCommandPool = commandManager.getBuyCommandPool();
        Pool<RefundCommand> refundCommandPool = commandManager.getUndoBuyCommandPool();

        try {
            PurchaseCommand purchaseCommand = buyCommandPool.obtain();
            commandManager.executeCommand(purchaseCommand, player1, product1.getId());
            buyCommandPool.free(purchaseCommand);
        } catch (Exception e) {
            e.printStackTrace();
        }

        transactionSubject.setState(shop.getAvailableProducts().size());

        System.out.println("Shop after the transaction");//TODO must be removed
        System.out.println(shop); //TODO must be removed

        System.out.println("Player after the transaction");//TODO must be removed
        System.out.println(player1); //TODO must be removed

        System.out.println("-------------------------------------------------------------------------------");//TODO must be removed

        try {
            commandManager.undo(player1, product1.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }

        transactionSubject.setState(shop.getAvailableProducts().size());

        System.out.println("Shop after the undo transaction");//TODO must be removed
        System.out.println(shop); //TODO must be removed

        System.out.println("Player after the undo transaction");//TODO must be removed
        System.out.println(player1); //TODO must be removed
    }
}

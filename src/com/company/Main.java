package com.company;

/**
 * The main class to bootstrap SHOP API
 */
public class Main {

    public static void main(String[] args) throws Exception{
        IShop shop = new ShopImpl();

        Type typePhone = new Type("phone");
        Type typeWeapon = new Type("weapon");

        Item iPhone = new Item(1, typePhone);
        iPhone.setCount(1);

        Item pistol = new Item(2, typeWeapon);
        pistol.setCount(1);

        Product product1 = new Product();
        product1.getPayload().add(iPhone);
        product1.getPayload().add(pistol);

        Category sale = new Category("SALE");
        product1.setCategory(sale);

        Type typeMoney = new Type("money");
        Type typeCar = new Type("car");
        Type typeShoe = new Type("shoe");

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

        product1.getCost().add(BMW_2);
        product1.getCost().add(shoe_2);


        shop.add(product1);

        System.out.println("Shop before any transaction");//TODO must be removed
        System.out.println(shop); //TODO must be removed


	    Player player1 = new Player(1);

	    player1.getItems().add(AMD);
	    player1.getItems().add(BMW_1);
	    player1.getItems().add(shoe_1);

        System.out.println("Player before any transaction");//TODO must be removed
        System.out.println(player1); //TODO must be removed

        shop.buy(player1, product1);

        System.out.println("Shop after the transaction");//TODO must be removed
        System.out.println(shop); //TODO must be removed

        System.out.println("Player after the transaction");//TODO must be removed
        System.out.println(player1); //TODO must be removed
    }
}

package tests;

import com.rockbite.bootcamp.*;
import org.junit.Test;

import static org.junit.Assert.assertThrows;

public class ShopImplUT {

    ShopImpl shop = ShopImpl.getInstance();

    Product product1 = new Product();

    Player player1 = new Player(1);

   {

        ItemType typeMoney = new ItemType("money");
        ItemType typeCar = new ItemType("car");
        ItemType typeShoe = new ItemType("shoe");


        Item AMD = new Item(3, typeMoney);
        Item BMW_1 = new Item(4, typeCar);
        Item shoe_1 = new Item(5, typeShoe);

        AMD.setCount(100);
        BMW_1.setCount(2);
        shoe_1.setCount(4);
        player1.getItems().put(AMD.getId(), AMD);
        player1.getItems().put(BMW_1.getId(), BMW_1);
        player1.getItems().put(shoe_1.getId(), shoe_1);

        ItemType typePhone = new ItemType("phone");
        ItemType typeWeapon = new ItemType("weapon");

        Item iPhone = new Item(1, typePhone);
        Item pistol = new Item(2, typeWeapon);

        Item[] payload = new Item[2];

        Category sale = new Category("SALE");

        Item BMW_2 = new Item(4, typeCar);
        Item shoe_2 = new Item(5, typeShoe);

        iPhone.setCount(1);
        pistol.setCount(1);
        product1.setId(1);
        payload[0] = iPhone;
        payload[1] = pistol;
        product1.setPayload(payload);
        product1.setCategory(sale);
        BMW_2.setCount(1);
        shoe_2.setCount(2);

        product1.getCost().put(BMW_2.getId(), BMW_2);
        product1.getCost().put(shoe_2.getId(), shoe_2);

        shop.addProduct(1, product1);
    }


    @Test
    public void callingShopsBuyMethodWithNoneExistingProductIdTest() {
        assertThrows(NullPointerException.class, () -> {
           shop.buy(player1, 99);
        });
    }
    @Test
    public void callingShopsBuyMethodWithNullPlayerTest() {
        assertThrows(NullPointerException.class, () -> {
            shop.buy(null, 1);
        });
    }

    @Test
    public void callingShopsBuyMethodWithNotEnoughPlayerItemsTest() {
       player1.getItems().remove(4);
        assertThrows(TransactionFailedException.class, () -> {
            shop.buy(player1, 1);
        });
    }

    @Test
    public void callingShopsBuyMethodWithNotEnoughCountPlayerItemsTest() {
        player1.getItems().get(5).setCount(1);
        assertThrows(TransactionFailedException.class, () -> {
            shop.buy(player1, 1);
        });
    }

    @Test
    public void callingShopsRefundAfterBuyMethodAndCheckShopsStateTest() {
        assert (shop.getAvailableProducts().size() == 1);
        shop.buy(player1, 1);
        assert (shop.getAvailableProducts().size() == 0);
        shop.refund(player1, 1);
        assert (shop.getAvailableProducts().size() == 1);
    }
}

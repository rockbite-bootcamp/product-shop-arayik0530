package tests;

import com.rockbite.bootcamp.util.command.PurchaseCommand;
import com.rockbite.bootcamp.util.pool.Pool;
import org.junit.Test;

import static org.junit.Assert.assertThrows;

/**
 * Pool class's unit testing class
 */
public class PoolUT {

    Pool<PurchaseCommand> pool = new Pool<PurchaseCommand>() {
        @Override
        public PurchaseCommand newObject() {
            return new PurchaseCommand();
        }
    };

    PurchaseCommand purchaseCommand = pool.obtain();


    /**
     * testing situation when we call pools free method twice with the same object
     */
    @Test(expected = Test.None.class)
    public void poolsFreeMethodTestWthFreeingSameCommandTwice() {
        pool.free(purchaseCommand);
        pool.free(purchaseCommand);
    }

    /**
     * testing situation when we call pools free method twice with null
     */
    @Test
    public void poolsFreeMethodTestWthNullArgument() {
        assertThrows(NullPointerException.class, () -> {
            pool.free(null);
        });
    }
}

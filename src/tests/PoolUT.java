package tests;

import com.rockbite.bootcamp.util.command.BuyCommand;
import com.rockbite.bootcamp.util.pool.Pool;
import org.junit.Test;

import static org.junit.Assert.assertThrows;

/**
 * Pool class's unit testing class
 */
public class PoolUT {

    Pool<BuyCommand> pool = new Pool<BuyCommand>() {
        @Override
        public BuyCommand newObject() {
            return new BuyCommand();
        }
    };

    BuyCommand buyCommand = pool.obtain();


    /**
     * testing situation when we call pools free method twice with the same object
     */
    @Test(expected = Test.None.class)
    public void poolsFreeMethodTestWthFreeingSameCommandTwice() {
        pool.free(buyCommand);
        pool.free(buyCommand);
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

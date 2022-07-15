package yellow.meta.jdp.singleton.hungry;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class YellowMetaSingletonHungryTest {
    @Test
    public void getInstance() {
        YellowMetaSingletonHungry instance0 = YellowMetaSingletonHungry.getInstance();
        int memoryAddress = System.identityHashCode(instance0);
        for (int i = 0; i < 50; i++) {
            YellowMetaSingletonHungry instance = YellowMetaSingletonHungry.getInstance();
            assertTrue(instance==instance0);
            assertEquals(System.identityHashCode(instance), memoryAddress);
        }
    }
}
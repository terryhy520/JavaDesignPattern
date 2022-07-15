package yellow.meta.jdp.singleton.lazy;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class YellowMetaSingletonLazyTest {

    @Test
    public void getInstance() {
        YellowMetaSingletonLazy instance0 = YellowMetaSingletonLazy.getInstance();
        int memoryAddress = System.identityHashCode(instance0);
        for (int i = 0; i < 50; i++) {
            YellowMetaSingletonLazy instance = YellowMetaSingletonLazy.getInstance();
            assertTrue(instance == instance0);
            assertEquals(System.identityHashCode(instance), memoryAddress);
        }
    }
}
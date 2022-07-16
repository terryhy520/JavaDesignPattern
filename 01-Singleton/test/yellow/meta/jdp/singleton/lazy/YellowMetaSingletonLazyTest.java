package yellow.meta.jdp.singleton.lazy;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class YellowMetaSingletonLazyTest {
    // 野鹿先生网站的总页面数为5个
    private static final int PAGE_NUM = 5;
    @Test
    public void getInstance() {
        YellowMetaSingletonLazy instance0 = YellowMetaSingletonLazy.getInstance();
        int memoryAddress = System.identityHashCode(instance0);
        for (int i = 0; i < PAGE_NUM; i++) {
            YellowMetaSingletonLazy instance = YellowMetaSingletonLazy.getInstance();
            assertTrue(instance == instance0);
            assertEquals(System.identityHashCode(instance), memoryAddress);
        }
    }


    @Test
    public void countOneTime() {
        int accessTimes = 10;
        // 模拟5个页面的访问
        for (int i = 0; i < PAGE_NUM; i++) {
            ((Runnable) () -> {
                YellowMetaSingletonLazy instance = YellowMetaSingletonLazy.getInstance();
                // 模拟独立访问每个页面10次，访问时间间隔0~1000之间的随机毫秒数
                for (int j = 0; j < accessTimes; j++) {
                    Random random = new Random();
                    try {
                        Thread.sleep(random.nextInt(1000));
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    instance.countOneTime();
                }
            }).run();
        }
        // 5个页面各自被访问了10次，预期网址当前访问总次数为50次
        assertEquals(YellowMetaSingletonLazy.getInstance().getCounts(), 50);
    }
}
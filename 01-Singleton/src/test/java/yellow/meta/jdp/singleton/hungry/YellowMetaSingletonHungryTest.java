package yellow.meta.jdp.singleton.hungry;

import main.java.yellow.meta.jdp.singleton.hungry.YellowMetaSingletonHungry;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class YellowMetaSingletonHungryTest {
    // 野鹿先生网站的总页面数为5个
    private static final int PAGE_NUM = 5;

    @Test
    public void getInstance() {
        YellowMetaSingletonHungry instance0 = YellowMetaSingletonHungry.getInstance();
        int memoryAddress = System.identityHashCode(instance0);
        // 模拟5个页面获取计数相关实例
        for (int i = 0; i < PAGE_NUM; i++) {
            YellowMetaSingletonHungry instance = YellowMetaSingletonHungry.getInstance();
            // 期待每个页面获取到的是同一个实例
            assertTrue(instance == instance0);
            // 此处比通过比较内存地址是否相等判断是否同一个实例
            assertEquals(System.identityHashCode(instance), memoryAddress);
        }
    }

    @Test
    public void countOneTime() {
        int accessTimes = 10;
        // 模拟5个页面的访问
        for (int i = 0; i < PAGE_NUM; i++) {
            ((Runnable) () -> {
                YellowMetaSingletonHungry instance = YellowMetaSingletonHungry.getInstance();
                // 模拟独立访问每个页面10次，访问时间间隔0~1000之间的随机毫秒数
                for (int j = 0; j < accessTimes; j++) {
                    Random random = new Random();
                    try {
                        Thread.sleep(random.nextInt(10));
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    instance.countOneTime();
                }
            }).run();
        }
        // 5个页面各自被访问了10次，预期网址当前访问总次数为50次
        assertEquals(YellowMetaSingletonHungry.getInstance().getCounts(), 50);
    }
}


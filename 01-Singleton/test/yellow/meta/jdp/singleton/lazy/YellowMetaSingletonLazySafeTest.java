package yellow.meta.jdp.singleton.lazy;

import org.junit.Test;
import yellow.meta.jdp.util.Cost;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class YellowMetaSingletonLazySafeTest {
    // 野鹿先生网站的总页面数为 PAGE_NUM 个
    private static final int PAGE_NUM = 50000;
    @Test
    public void getInstance() {
        YellowMetaSingletonLazySafe [] Global = new YellowMetaSingletonLazySafe[PAGE_NUM];
        try(Cost cost = new Cost()) {
        for (int i = 0; i < PAGE_NUM; i++) {
            int finalI = i;
            ((Runnable) () -> {
                YellowMetaSingletonLazySafe instance = YellowMetaSingletonLazySafe.getInstance();
                Global[finalI] = instance;
            }).run();
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
        for (int i = 0; i < PAGE_NUM - 1; i++) {
            // 期待每个页面获取到的是同一个实例
            assertTrue(Global[i] == Global[i+1]);
            // 此处比通过比较内存地址是否相等判断是否同一个实例
            assertEquals(System.identityHashCode(Global[i]),System.identityHashCode(Global[i+1]));
        }
    }


    @Test
    public void countOneTime() {
        int accessTimes = 10;
        // 模拟5个页面的访问
        for (int i = 0; i < PAGE_NUM; i++) {
            ((Runnable) () -> {
                YellowMetaSingletonLazySafe instance = YellowMetaSingletonLazySafe.getInstance();
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
        assertEquals(YellowMetaSingletonLazySafe.getInstance().getCounts(), 50);
    }
}
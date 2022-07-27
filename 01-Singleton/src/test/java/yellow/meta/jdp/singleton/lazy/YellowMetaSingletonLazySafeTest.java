package yellow.meta.jdp.singleton.lazy;

import main.java.yellow.meta.jdp.singleton.lazy.YellowMetaSingletonLazySafe;
import main.java.yellow.meta.jdp.util.Cost;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class YellowMetaSingletonLazySafeTest {
    // 野鹿先生网站的总页面数为 PAGE_NUM 个
    private static final int PAGE_NUM = 50000;

    @Test
    public void getInstance() {
        YellowMetaSingletonLazySafe[] Global = new YellowMetaSingletonLazySafe[PAGE_NUM];
        try (Cost cost = new Cost("Lazy Safe")) {
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
            assertTrue(Global[i] == Global[i + 1]);
            // 此处比通过比较内存地址是否相等判断是否同一个实例
            assertEquals(System.identityHashCode(Global[i]), System.identityHashCode(Global[i + 1]));
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
                    instance.countOneTime();
                }
            }).run();
        }
        // PAGE_NUM 个页面各自被访问了 accessTimes 次，预期网址当前访问总次数为 PAGE_NUM * accessTimes 次
        assertEquals(YellowMetaSingletonLazySafe.getInstance().getCounts(), PAGE_NUM * accessTimes);
    }
}
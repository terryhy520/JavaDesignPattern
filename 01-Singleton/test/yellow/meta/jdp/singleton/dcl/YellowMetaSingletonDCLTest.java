package yellow.meta.jdp.singleton.dcl;

import org.junit.Test;
import yellow.meta.jdp.singleton.lazy.YellowMetaSingletonLazySafe;
import yellow.meta.jdp.util.Cost;

import static org.junit.Assert.*;

public class YellowMetaSingletonDCLTest {
    // 野鹿先生网站的总页面数为 PAGE_NUM 个
    private static final int PAGE_NUM = 50000;

    @Test
    public void getInstance() {
        YellowMetaSingletonDCL[] Global = new YellowMetaSingletonDCL[PAGE_NUM];
        try(Cost cost = new Cost()) {
        for (int i = 0; i < PAGE_NUM; i++) {
            int finalI = i;
            ((Runnable) () -> {
                YellowMetaSingletonDCL instance = YellowMetaSingletonDCL.getInstance();
                Global[finalI] = instance;
            }).run();
        }  } catch (Exception e) {
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
    }
}
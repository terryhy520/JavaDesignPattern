package yellow.meta.jdp.singleton.lazy;

import yellow.meta.jdp.singleton.hungry.YellowMetaSingletonHungry;

public class YellowMetaSingletonLazy {
    private static YellowMetaSingletonLazy instance;
    // 总访问次数
    private int visitCounts;
    private YellowMetaSingletonLazy() {
        visitCounts = 0;
    }

    public static synchronized YellowMetaSingletonLazy getInstance() {
        if (instance == null) {
            instance = new YellowMetaSingletonLazy();
        }
        return instance;
    }

    /**
     * 出现一次页面访问，则增加一次访问计数
     */
    public void countOneTime() {
        synchronized(YellowMetaSingletonHungry.class) {
            visitCounts++;
        }
    }

    /**
     * 获取当前访问计数
     * @return 返回当前访问计数的值
     */
    public int getCounts() {
        return visitCounts;
    }
}

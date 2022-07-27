package main.java.yellow.meta.jdp.singleton.hungry;

public class YellowMetaSingletonHungry {
    private static final YellowMetaSingletonHungry INSTANCE = new YellowMetaSingletonHungry();

    // 总访问次数
    private int visitCounts;

    private YellowMetaSingletonHungry() {
        visitCounts = 0;
    }

    public static YellowMetaSingletonHungry getInstance() {
        return INSTANCE;
    }

    /**
     * 出现一次页面访问，则增加一次访问计数
     */
    public void countOneTime() {
        synchronized (YellowMetaSingletonHungry.class) {
            visitCounts++;
        }
    }

    /**
     * 获取当前访问计数
     *
     * @return 返回当前访问计数的值
     */
    public int getCounts() {
        return visitCounts;
    }
}

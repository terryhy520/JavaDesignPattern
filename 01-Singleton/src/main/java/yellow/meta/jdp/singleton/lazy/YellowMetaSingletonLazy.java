package main.java.yellow.meta.jdp.singleton.lazy;


public class YellowMetaSingletonLazy {

    // 懒汉模式的单例模式实例
    private static YellowMetaSingletonLazy instance;

    // 网址的总访问次数
    private int visitCounts;

    // 构造函数为 private，确保该类不会再其他地方被实例化
    private YellowMetaSingletonLazy() {
        visitCounts = 0;
    }

    /**
     * getInstance 提供静态全局访问点，方便使用者调用获取单例模式的唯一实例
     *
     * @return 返回单例模式的唯一实例
     */
    public static YellowMetaSingletonLazy getInstance() {
        if (instance == null) {
            instance = new YellowMetaSingletonLazy();
        }
        return instance;
    }

    /**
     * countOneTime 出现一次页面访问，则增加一次访问计数
     */
    public void countOneTime() {
        synchronized (YellowMetaSingletonLazy.class) {
            visitCounts++;
        }
    }

    /**
     * getCounts 获取当前访问计数
     *
     * @return 返回当前访问计数的值
     */
    public int getCounts() {
        return visitCounts;
    }
}

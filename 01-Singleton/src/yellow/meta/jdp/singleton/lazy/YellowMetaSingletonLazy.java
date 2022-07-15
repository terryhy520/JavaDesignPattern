package yellow.meta.jdp.singleton.lazy;

public class YellowMetaSingletonLazy {
    private static YellowMetaSingletonLazy instance;

    private YellowMetaSingletonLazy() {
    }

    public static synchronized YellowMetaSingletonLazy getInstance() {
        if (instance == null) {
            instance = new YellowMetaSingletonLazy();
        }
        return instance;
    }
}

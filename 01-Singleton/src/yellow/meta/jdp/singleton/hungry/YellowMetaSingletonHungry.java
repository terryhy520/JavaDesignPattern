package yellow.meta.jdp.singleton.hungry;

public class YellowMetaSingletonHungry {
    private static final YellowMetaSingletonHungry instance = new YellowMetaSingletonHungry();

    private YellowMetaSingletonHungry() {
    }

    public static YellowMetaSingletonHungry getInstance() {
        return instance;
    }
}

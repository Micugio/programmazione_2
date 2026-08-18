package lecture07.defaults;
public interface AltBoss {
    default void announceArrival() {
        System.out.println(">> [BOSS BAR]!");
    }
}
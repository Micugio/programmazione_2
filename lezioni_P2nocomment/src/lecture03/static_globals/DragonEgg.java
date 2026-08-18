package lecture03.static_globals;
public class DragonEgg {
    private static DragonEgg THE_INSTANCE = new DragonEgg();
    private DragonEgg() {}
    public static DragonEgg getInstance() {
        return THE_INSTANCE;
    }
    public void teleport() {
        System.out.println(">> Vwoop! The Dragon Egg teleported away.");
    }
}
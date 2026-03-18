package lecture03.static_globals;

//@ Istanza del Singleton pattern
public class DragonEgg {

    private static DragonEgg THE_INSTANCE = new DragonEgg();

    private DragonEgg() {}

    public static DragonEgg getInstance() {
        return THE_INSTANCE;
    }

    //@ Il metodo `teleport()` e` un metodo da chiamare sul singolo oggetto
    public void teleport() {
        System.out.println(">> Vwoop! The Dragon Egg teleported away.");
    }
}

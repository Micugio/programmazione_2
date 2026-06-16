package lecture03.static_globals;

public class GameConstants {

    //@ Un valore condiviso da tutto il server, tutti lo possono leggere
    //@ Questo e` `static` perche` si applica al gioco, non ad un oggetto
    public static int MAX_STACK_SIZE = 64;
    public static int SMALL_MAX_STACK_SIZE = 16;

    //@ Un metodo `static` (Utility).
    //@ Non ha bisogno di conoscere lo stato di alcun oggetto per svolgere il suo compito
    public static void printMOTD() {
        System.out.println(">> Welcome to the Minecraft Course!");
    }

    //@ Elementi non-static per le domande
    public int field;
    public void method(){}
}

package lecture03;
import lecture03.final_mod.FinalPlayer;
import lecture03.final_mod.ToolTier;
import lecture03.ackages.entities.Player;
import lecture03.ackages.blocks.TNT;
import lecture03.ackages.entities.Witch;
import lecture03.static_globals.DragonEgg;
import lecture03.static_globals.GameConstants;
public class Lecture3 {
    public static void main(String[] args) {
        System.out.println("---------------- Packages ----------------");
        packagesExample();
        packagePrivateExample();
        System.out.println("---------------- Modificatori di Accesso/Visibilita` ----------------");
        visibilityExample();
        System.out.println("---------------- Final ----------------");
        finalExample();
        System.out.println("---------------- Static ----------------");
        staticExample();
        singletonExample();
    }
    private static void packagesExample() {
        Player p = new Player();
        TNT b = new TNT();
        System.out.println("Loaded " + p.getClass().getName());
        System.out.println("Loaded " + b.getClass().getName());
    }
    public static void visibilityExample() {
        Player steve = new Player();
        steve.damage(5);
    }
    public static void packagePrivateExample(){
        Witch w = new Witch();
        Player p = new Player();
        w.fakeAttack(p);
        p.isAlive();
        w.attack(p);
        p.isAlive();
    }
    public static void finalExample() {
        FinalPlayer fp = new FinalPlayer();
        ToolTier currentTier = ToolTier.DIAMOND;
        System.out.println("Selected: " + currentTier);
        System.out.println("Speed: " + currentTier.getEfficiency());
        System.out.println("Durability: " + currentTier.getMaxUses());
    }
    private static void staticExample() {
        System.out.println("Max Stack Size: " + GameConstants.MAX_STACK_SIZE);
        GameConstants g = new GameConstants();
        int c = GameConstants.MAX_STACK_SIZE;
        int s = g.SMALL_MAX_STACK_SIZE;
        g.method();
        g.printMOTD();
        GameConstants.MAX_STACK_SIZE = 1;
        System.err.println("Print on the error stream");
        System.currentTimeMillis();
        System.gc();
    }
    private static void singletonExample(){
        DragonEgg egg = DragonEgg.getInstance();
        egg.teleport();
        DragonEgg egg2 = DragonEgg.getInstance();
        System.out.println("Is it the exact same egg? " + (egg == egg2));
    }
}
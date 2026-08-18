package lecture02;
import lecture02.v_enums.Direction;
import lecture02.v_enums.ToolTier;
import lecture02.v1.TNT;
public class Lecture2 {
    public static void main(String[] args) {
        System.out.println("---------------- Classi ----------------");
        classExample();
        System.out.println("---------------- Campi ----------------");
        fieldsExample();
        System.out.println("---------------- Costruttori ----------------");
        constructorsExample();
        System.out.println("---------------- Metodi ----------------");
        methodsExample();
        System.out.println("---------------- Enums ----------------");
        enumExample();
        richEnumExample();
    }
    private static void classExample() {
        TNT tnt1 = new TNT();
        System.out.println("My first block: " + tnt1);
        TNT tnt2 = new TNT();
    }
    public static void fieldsExample() {
        lecture02.v2.TNT blockA = new lecture02.v2.TNT();
        lecture02.v2.TNT blockB = new lecture02.v2.TNT();
        System.out.println("Igniting Block A...");
        blockA.isIgnited = true;
        System.out.println("Block A Ignited?" + blockA.isIgnited);
        System.out.println("Block B Ignited?" + blockB.isIgnited);
    }
    private static void constructorsExample(){
        lecture02.v2.TNT blockA = new lecture02.v2.TNT();
        lecture02.v2.TNT blockB = new lecture02.v2.TNT();
        System.out.println("blockA e blockB: " +
                "" +blockA.isIgnited+ " "+blockB.isIgnited+ "\n " +
                "" +blockA.fuseLength+ " "+blockB.fuseLength+ "\n " +
                "" +blockA.explosionPower+ " "+blockB.explosionPower );
        lecture02.v2.TNT blockC = new lecture02.v2.TNT(10);
        lecture02.v2.TNT blockD = new lecture02.v2.TNT(20);
        System.out.println("blockC e blockD: " +
                "" +blockC.isIgnited+ " "+blockD.isIgnited+ "\n " +
                "" +blockC.fuseLength+ " "+blockD.fuseLength+ "\n " +
                "" +blockC.explosionPower+ " "+blockD.explosionPower );
        TNT blockE;
    }
    private static void methodsExample() {
        lecture02.v3.TNT tnt = new lecture02.v3.TNT();
        System.out.println(">> Sending ignite signal...");
        tnt.ignite();
        for (int i = 0; i < 20; i++) {
            tnt.tick();
        }
    }
    public static void enumExample() {
        System.out.println("=== 1. THE BAD WAY (MAGIC NUMBERS) ===");
        movePlayer(0);
        movePlayer(99);
        System.out.println("\n=== 2. THE GOOD WAY (ENUMS) ===");
        movePlayerEnum(Direction.NORTH);
    }
    private static void movePlayer(int direction) {
        if (direction == 0) {
            System.out.println("Moving NORTH");
        } else if (direction == 1) {
            System.out.println("Moving EAST");
        } else {
            System.out.println("Unknown direction! (Bug)");
        }
    }
    private static void movePlayerEnum(Direction dir) {
        String dirName = switch (dir) {
            case NORTH -> "NORTH";
            case EAST -> "EAST";
            default -> "UP";
        };
        System.out.println("Moving" + dirName);
    }
    public static void richEnumExample() {
        ToolTier myTier = ToolTier.GOLD;
        ToolTier myTier2 = ToolTier.GOLD;
        System.out.println(""+ myTier.getEfficiency() + " " + myTier2.getEfficiency());
        myTier2.efficiency = 0;
        System.out.println(""+ myTier.getEfficiency() + " " + myTier2.getEfficiency());
    }
}
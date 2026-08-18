package lecture18;
import lecture18.observer.ObservingPlayer;
import lecture18.observer.v1.Enderman_v1;
import lecture18.observer.v2.mob.Cow;
import lecture18.observer.v2.mob.Enderman;
import lecture18.observer.v2.mob.MobInterface;
import lecture18.observer.v2.ObservingMovingPlayer;
import lecture18.visitor.*;
import lecture18.visitor.visitors.*;
public class Lecture18 {
    public static void main(String[] args) {
        System.out.println("---------------- Visitor fatto male ----------------");
        badVisitor();
        System.out.println("---------------- Visitor Pattern ----------------");
        goodVisitor();
        System.out.println("---------------- Observer Pattern ----------------");
        observerExample();
    }
    private static void badVisitor(){
        VisitableEntity entity = new Sheep();
        String holdingItem = "Shears";
        if (entity instanceof Villager) {
            Villager v = (Villager) entity;
            System.out.println("Opening Trading GUI for " + v.getProfession());
        } else if (entity instanceof Sheep) {
            Sheep s = (Sheep) entity;
            if (holdingItem.equals("Shears") && s.hasWool) {
                System.out.println("Sheared the sheep.");
                s.hasWool = false;
            }
        } else if (entity instanceof Creeper) {
            Creeper c = (Creeper) entity;
            if (holdingItem.equals("Flint and Steel")) {
                System.out.println("Ignited the Creeper!");
                c.isIgnited = true;
            }
        }
    }
    private static void goodVisitor(){
        VisitableEntity[] mobsInArea = { new Villager(), new Sheep(), new Creeper() };
        InteractionVisitor holdingFlint = new ItemInteraction("Flint and Steel");
        for (VisitableEntity mob : mobsInArea) {
            mob.accept(holdingFlint);
        }
    }
    public static void observerExample(){
        ObservingPlayer p = new ObservingPlayer();
        Enderman_v1 ev1 = new Enderman_v1();
                printWorldState(ev1,p);
        p.add_gaze_observer(ev1);
        p.look_around();            printWorldState(ev1,p);
        p.look_around();            printWorldState(ev1,p);
        p.wearPumpkinHead();
        p.look_around();            printWorldState(ev1,p);
        p.removePumpkinHead();
        p.look_around();            printWorldState(ev1,p);
        p.look_around();            printWorldState(ev1,p);
        p.look_around();            printWorldState(ev1,p);
        System.out.println();
        Enderman e1 = new Enderman();
        lecture18.observer.v2.mob.Creeper e2 = new lecture18.observer.v2.mob.Creeper();
        Cow e3 = new Cow();
        ObservingMovingPlayer p2 = new ObservingMovingPlayer();
                    printWorldState(e1,e2,e3,p2);
        p2.add_gaze_observer(e1);
        p2.look_around();               printWorldState(e1,e2,e3,p2);
        p2.wearPumpkinHead();
        p2.look_around();               printWorldState(e1,e2,e3,p2);
        p2.add_movement_observer(e2);
        p2.look_around();               printWorldState(e1,e2,e3,p2);
        p2.removePumpkinHead();
        p2.look_around();               printWorldState(e1,e2,e3,p2);
        p2.look_around();               printWorldState(e1,e2,e3,p2);
        p2.move();                      printWorldState(e1,e2,e3,p2);
        p2.move();                      printWorldState(e1,e2,e3,p2);
        p2.add_movement_observer(e3);
        p2.move();                      printWorldState(e1,e2,e3,p2);
        p2.move();                      printWorldState(e1,e2,e3,p2);
    }
    private static void printWorldState(MobInterface e1, MobInterface e2, MobInterface e3, ObservingPlayer p){
        String s = p.hasPumpkinHead() ? "[] : " : "() : ";
        System.out.println(
                "Enderman : "+ e1.getLocation()+" "+
                        "Creeper : " + e2.getLocation()+" "+
                        "Cow : " + e3.getLocation()+" "+
                        "Player "+s+ p.getLocation()
        );
    }
    private static void printWorldState(Enderman_v1 e1, ObservingPlayer p){
        String s = p.hasPumpkinHead() ? "[] : " : "() : ";
        System.out.println(
                "Enderman : "+ e1.getLocation()+" "+
                        "Player "+s+ p.getLocation()
        );
    }
}
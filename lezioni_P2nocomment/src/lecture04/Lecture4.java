package lecture04;
import lecture03.ackages.blocks.TNT;
import lecture03.ackages.entities.Player;
import lecture03.ackages.entities.Witch;
public class Lecture4 {
    public static void main(String[] args){
        System.out.println("---------------- Incapsulamento e Invarianti ----------------");
        invariants();
        System.out.println("---------------- Passaggio dei valori ----------------");
        valuePassingExample();
        System.out.println("---------------- Class Memory Layout ----------------");
        System.out.println("---------------- Object Memory Layout ----------------");
        layoutTest();
    }
    public static void invariants() {
        TNT standardTNT = new TNT();
        standardTNT.ignite();
        standardTNT.tick();
    }
    private static void valuePassingExample(){
        int x = 0;
        helper(x);
        TNT t = new TNT();
        reference(t);
        TNT[] arrT;
        arrT = new TNT[5];
        for (int i = 0 ; i < 5 ; i++){
            arrT[i] = new TNT();
        }
    }
    private static void helper(int p){}
    private static void reference(TNT t){}
    private static void layoutTest(){
        Witch w1 = new Witch();
        Witch w2 = new Witch();
        Player p1 = new Player();
        Player p2 = new Player();
        p2.username = "steve";
        p1.setPoisoned(true);
        p2.damage(10);
        TNT t1 = new TNT();
        TNT t2 = new TNT();
        t1.ignite();
        t2.tick();
    }
}
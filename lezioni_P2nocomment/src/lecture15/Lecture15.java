package lecture15;
import lecture07.interfaces.Skeleton;
import lecture14.examples.Spawner;
import lecture07.interfaces.Zombie;
import lecture15.factory_method.Tree;
import lecture15.factory_method.TreeFactory;
import lecture15.state.con.v1.Piglin_v1;
import lecture15.state.con.v2.Piglin_v2;
import lecture15.state.senza.v1.Piglin_Sv1;
import lecture15.state.senza.v2.Piglin_Sv2;
public class Lecture15 {
    public static void main(String[] args) {
        System.out.println("---------------- Type Erasure ----------------");
        typeErasureExample();
        System.out.println("---------------- Parametricity ----------------");
        System.out.println("---------------- Patterns ----------------");
        factoryMethodExample();
        stateExample();
    }
    private static void typeErasureExample() {
        Spawner<Zombie> zSpawner = new Spawner<>();
        Spawner<Skeleton> sSpawner = new Spawner<>();
        Class<?> c1 = zSpawner.getClass();
        Class<?> c2 = sSpawner.getClass();
        System.out.println("Are they the same class? " + (c1 == c2));
        System.out.println("Class Name: " + c1.getName());
    }
    private static void factoryMethodExample() {
        Tree t1 = TreeFactory.createDarkOak();
        Tree t2 = TreeFactory.createBirch();
        System.out.println("Factory created: " + t1.type);
        System.out.println("Factory created: " + t2.type);
    }
    public static void stateExample(){
        System.out.println("Testing v1 without");
        Piglin_Sv1 pig_s1 = new Piglin_Sv1();
        pig_s1.see_player_no_gold();
        pig_s1.unsee_player();
        pig_s1.see_player_wearing_gold();
        pig_s1.see_player_no_gold();
        System.out.println("Testing v2 without");
        Piglin_Sv2 pig_s2 = new Piglin_Sv2();
        pig_s2.player_opens_chest();
        pig_s2.see_player_wearing_gold();
        pig_s2.see_player_no_gold();
        pig_s2.player_attacks_piglin();
        System.out.println("Testing v1 with");
        Piglin_v1 pig1 = new Piglin_v1();
        pig1.see_player_no_gold();
        pig1.unsee_player();
        pig1.see_player_wearing_gold();
        pig1.see_player_no_gold();
        System.out.println("Testing v2 with");
        Piglin_v2 pig2 = new Piglin_v2();
        pig2.player_opens_chest();
        pig2.see_player_wearing_gold();
        pig2.see_player_no_gold();
        pig2.player_attacks_piglin();
    }
}
package lecture06;
import lecture05.inheritance.Zombie;
import lecture06.abstracts.Block;
import lecture06.abstracts.Entity;
import lecture06.abstracts.blocks.Cobblestone;
import lecture06.abstracts.blocks.Dirt;
import lecture06.abstracts.entities.Chicken;
import lecture06.abstracts.entities.Wolf;
import lecture06.overloading.IronBlock;
import lecture06.overloading.IronPick;
import lecture06.overloading.Pick;
import lecture06.overriding.Andesite;
import lecture06.overriding.Bedrock;
import lecture06.overriding.GoldBlock;
public class Lecture6 {
    public static void main(String[] args) {
        System.out.println("---------------- Classi astratte ----------------");
        abstractExample();
        System.out.println("---------------- Metodi astratti ----------------");
        abstractMethodsExample();
        runtimeExample();
        System.out.println("---------------- Overriding e super ----------------");
        overridingExample();
        System.out.println("---------------- Overloading ----------------");
        overloadingExample();
    }
    private static void abstractExample(){
        Chicken c = new Chicken();
        Wolf w = new Wolf();
        c.teleport(10, 20);
        w.teleport(20,30);
        c.makeSound();
    }
    private static void abstractMethodsExample(){
        Dirt d = new Dirt();
        System.out.println("Received: " + d.mineWithDrop());
    }
    private static void runtimeExample(){
        Dirt d = new Dirt();
        d.mine();
        d.getDrop();
        lecture05.inheritance.Entity e = new lecture05.inheritance.Entity();
        e.teleport(0,0);
        lecture05.inheritance.Zombie z = new Zombie();
        z.teleport(0,0);
        z.groan();
        e = z;
        e.teleport(0,0);
        Block b = d;
        b.mine();
        b.getDrop();
        Cobblestone c = new Cobblestone();
        c.getDrop();
        b=c;
        b.getDrop();
        Block[] arrb = new Block[2];
        arrb[0] = d;
        arrb[1] = c;
        for (Block bb: arrb){
            bb.getDrop();
        }
    }
    private static void overridingExample() {
        GoldBlock gb = new GoldBlock();
        gb.mine();
        Andesite a = new Andesite();
        a.mine();
        Bedrock br = new Bedrock();
        br.mine();
    }
    private static void overloadingExample(){
        Pick p = new Pick();
        p.use(new Dirt());
        p.use(new Bedrock());
        p.use(new Chicken());
    }
    private static void overloadingOoverridingExample(){
        IronPick ip = new IronPick();
        ip.use(new Dirt());
        ip.use(new IronBlock());
        ip.use(new Dirt(),new IronBlock());
    }
}
package lecture05;
import lecture05.inheritance.*;
import lecture06.overriding.Bedrock;
public class Lecture5 {
    public static void main(String[] args) {
        System.out.println("---------------- Ereditarieta` ----------------");
        inheritanceExample();
        inheritanceAndConstructorsExample();
        System.out.println("---------------- Polimorfismo di sottotipo ----------------");
        subtypingExample();
        System.out.println("---------------- La classe Object ----------------");
        classObjectExample();
        System.out.println("---------------- Il Diamond Problem ----------------");
    }
    private static void inheritanceExample() {
        Zombie z = new Zombie();
        Creeper c = new Creeper(0);
        z.teleport(10, 64);
        c.teleport(20, 64);
        z.groan();
        c.hiss();
        Entity e = new Entity(10,10);
        System.out.println();
    }
    private static void inheritanceAndConstructorsExample(){
        System.out.println("Attraversiamo i costruttori");
        Zombie z1 = new Zombie();
        z1.printLocation();
        Zombie z2 = new Zombie(3);
        z2.printLocation();
        Entity e1 = new Entity();
        e1.printLocation();
        Entity e2 = new Entity(4, 4);
        e2.printLocation();
        Creeper c1 = new Creeper(7);
        c1.printLocation();
        Creeper c2 = new Creeper(7,7);
        c2.printLocation();
    }
    private static void subtypingExample() {
        Entity gr = new Zombie();
        Entity[] allMobs = new Entity[3];
        Zombie z = new Zombie();
        allMobs[0] = z ;
        allMobs[1] = new Creeper(0);
        allMobs[2] = new Entity(10,10);
        System.out.println(">> Moving all mobs...");
        for (Entity e : allMobs) {
            gotoSpawn(e);
            z.groan();
        }
    }
    private static void gotoSpawn(Entity e){
        e.teleport(0,0);
    }
    private static void classObjectExample(){
        Object o = new Object();
        System.out.println( o.getClass() );
        Entity e = new Zombie();
        System.out.println( e.getClass() );
        System.out.println( o.toString() );
        System.out.println( o.equals(o) );
        System.out.println( o.hashCode() );
    }
}
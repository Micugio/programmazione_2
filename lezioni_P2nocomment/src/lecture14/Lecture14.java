package lecture14;
import lecture05.inheritance.*;
import lecture14.examples.*;
public class Lecture14 {
    public static void main(String[] args) {
        System.out.println("---------------- Generics ----------------");
        try {
            rawTypeExample();
        }catch (RuntimeException e){}
        genericsExample();
        System.out.println("---------------- Invariance, Variance, Co-Variance ----------------");
        invarianceExample();
        try{
            covarianceExample();
        }catch (ArrayStoreException e){}
        contravarianceExample();
        System.out.println();
        System.out.println("---------------- Wildcards ----------------");
        covarianceExtendsExample();
        contravarianceSuperExample();
        pecsExample();
    }
    public static void rawTypeExample() {
        RawSpawner mySpawner = new RawSpawner();
        mySpawner.setEntity(new Zombie());
        Object o = mySpawner.spawn();
        Creeper c = (Creeper) o;
        c.hiss();
    }
    private static void genericsExample() {
        Spawner<Zombie> zombieSpawner = new Spawner<>();
        zombieSpawner.setEntity(new Zombie());
        Zombie z = zombieSpawner.spawn();
        z.groan();
        Spawner<Creeper> creeperSpawner = new Spawner<>();
        creeperSpawner.setEntity(new Creeper(0));
        creeperSpawner.spawn().hiss();
    }
    private static void invarianceExample() {
        Entity e = new Zombie();
        Spawner<Zombie> zombieSpawner = new Spawner<>();
    }
    private static void covarianceExample() {
        Zombie[] zombieArray = new Zombie[1];
        Entity[] entityArray = zombieArray;
        entityArray[0] = new Creeper(5);
    }
    private static void contravarianceExample(){
        System.out.println();
        System.out.println("Supponiamo di avere covarianza");
        Spawner<Zombie> zombieSpawner = new Spawner<>();
        zombieSpawner.setEntity(new Zombie());
        System.out.println();
        System.out.println("Supponiamo di avere controvarianza");
        Spawner<Entity> genericSpawner = new Spawner<>();
        genericSpawner.setEntity(new Creeper(5));
    }
    private static void covarianceExtendsExample() {
        Spawner<Zombie> zombieSpawner = new Spawner<>();
        zombieSpawner.setEntity(new Zombie());
        Spawner<? extends Entity> source = zombieSpawner;
        Entity e = source.spawn();
        System.out.println("Spawned: " + e);
    }
    private static void contravarianceSuperExample() {
        Spawner<Entity> generalSpawner = new Spawner<>();
        Spawner<? super Zombie> destination = generalSpawner;
        destination.setEntity(new Zombie());
        Entity zz = (Entity) destination.spawn();
    }
    private static void pecsExample() {
        Spawner<Zombie> zSource = new Spawner<>();
        zSource.setEntity(new Zombie());
        Spawner<Entity> eDest = new Spawner<>();
        transferMob(zSource, eDest);
    }
    public static void transferMob(Spawner<? extends Entity> producer,
                                   Spawner<? super Entity> consumer) {
        Entity e = producer.spawn();
        consumer.setEntity(e);
        System.out.println("Transferred: " + e);
    }
    private static void combinations(){
        Spawner<? extends Entity> see = new Spawner<>();
        Spawner<? extends Zombie> sez = new Spawner<>();
        Spawner<? super Zombie> ssz = new Spawner<>();
        Spawner<? super Drowned> ssd = new Spawner<>();
        Spawner<Drowned> sd = new Spawner<>();
        Spawner<Entity> se = new Spawner<>();
        Spawner<Zombie> sz = new Spawner<>();
        see = se;
        sez = sz;
        ssz = sz;
        ssd = sd;
        see = sd;
        sez = sd;
        see = sez;
        ssd = se;
        ssd = sz;
    }
}
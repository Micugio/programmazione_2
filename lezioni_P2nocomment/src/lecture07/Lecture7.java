package lecture07;
import lecture06.abstracts.Entity;
import lecture07.interfaces.*;
import lecture07.defaults.*;
public class Lecture7 {
    public static void main(String[] args) {
        System.out.println("---------------- Interfacce (come contratti) ----------------");
        intefacesExample();
        System.out.println("---------------- Contratti multipli ----------------");
        interfacesAndDiamondExample();
        System.out.println("---------------- Polimorfismo tramite interfacce ----------------");
        interfacesPolymorphismExample();
        System.out.println("---------------- Defaults ----------------");
        defaultsExample();
        System.out.println("---------------- Chiamante e chiamato ----------------");
        caller_calleeExample();
        System.out.println("---------------- Recap ----------------");
    }
    private static void intefacesExample() {
        Zombie z = new Zombie();
        z.attack(z);
        z.playAggressiveSound();
    }
    private static void interfacesAndDiamondExample() {
        Skeleton skelly = new Skeleton();
        skelly.performRangedAttack(null, 10);
        skelly.attack(null);
    }
    private static void interfacesPolymorphismExample() {
        Skeleton skelly = new Skeleton();
        Entity n = skelly;
        n.teleport(0, 0);
        Enemy badGuy = skelly;
        badGuy.playAggressiveSound();
        RangedAttackMob sniper = skelly;
        sniper.performRangedAttack(null, 1.0f);
        skelly.performRangedAttack(null, 1.0f );
    }
    private static void defaultsExample(){
        Wither w = new Wither();
        w.attack(null);
        w.playAggressiveSound();
        Boss b = w;
        b.announceArrival();
        b.performSpecialAttack();
        Boss b2 = new Wither();
        b2.announceArrival();
    }
    private static void caller_calleeExample(){
        Zombie z = new Zombie();
        takeSound(z);
        Wither w = new Wither();
        takeSound(w);
        Skeleton sk = new Skeleton();
        takeSound(sk);
    }
    private static void takeSound(Enemy r){
        r.playAggressiveSound();
    }
}
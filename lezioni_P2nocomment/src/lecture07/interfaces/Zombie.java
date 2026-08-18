package lecture07.interfaces;
import lecture06.abstracts.Entity;
public class Zombie extends Entity implements Enemy {
    @Override
    public void playAggressiveSound() {
        System.out.println("Zombie: GROAAAN! (Targeting Player)");
    }
    @Override
    public void attack(Entity target) {
        System.out.println("Zombie attacks: *Melee Punch* (Dealing 3 damage)");
    }
}
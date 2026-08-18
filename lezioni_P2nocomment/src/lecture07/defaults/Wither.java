package lecture07.defaults;
import lecture06.abstracts.Entity;
public class Wither extends Entity implements Boss {
    @Override
    public void attack(Entity target) {
        System.out.println("Wither shoots a skull!");
    }
    @Override
    public void playAggressiveSound() {
        System.out.println("Wither: ... (Ghostly sound)");
    }
    @Override
    public void performSpecialAttack() {
        System.out.println(">> Wither Effect Applied!");
    }
}
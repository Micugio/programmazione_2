package lecture07.interfaces;
import lecture06.abstracts.Entity;
public class Skeleton extends Entity implements Enemy, RangedAttackMob {
    @Override
    public void playAggressiveSound() {
        System.out.println("Skeleton: *Bone Rattle*");
    }
    @Override
    public void attack(Entity target) {
        System.out.println("Skeleton attacks: *Shoots Arrow*");
    }
    @Override
    public void performRangedAttack(Entity target, float distanceFactor) {
        System.out.println(">> Skeleton draws bow...");
        System.out.println(">> Fired arrow at target! (Power: " + distanceFactor + ")");
    }
}
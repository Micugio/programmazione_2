package lecture07.interfaces;
import lecture06.abstracts.Entity;
public interface RangedAttackMob {
    void performRangedAttack(Entity target, float distanceFactor);
    void attack(Entity target);
}
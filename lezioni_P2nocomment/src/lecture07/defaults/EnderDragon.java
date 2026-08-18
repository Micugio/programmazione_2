package lecture07.defaults;
import lecture06.abstracts.Entity;
public class EnderDragon implements Boss, AltBoss{
    @Override
    public void performSpecialAttack() {}
    @Override
    public void announceArrival() {
        Boss.super.announceArrival();
        AltBoss.super.announceArrival();
    }
    @Override
    public void playAggressiveSound() {}
    @Override
    public void attack(Entity target) {}
}
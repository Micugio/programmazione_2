package lecture07.interfaces;
import lecture06.abstracts.Entity;
public interface Enemy {
    void playAggressiveSound();
    void attack(Entity target);
}
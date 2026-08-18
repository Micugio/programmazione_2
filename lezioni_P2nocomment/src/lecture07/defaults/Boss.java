package lecture07.defaults;
import lecture07.interfaces.Enemy;
public interface Boss extends Enemy {
    void performSpecialAttack();
    default void announceArrival() {
        System.out.println(">> [BOSS BAR]: A powerful enemy has appeared!");
        playDramaticSound();
        this.performSpecialAttack();
    }
    private void playDramaticSound() {
        System.out.println(">> (Orchestral Music Starts...)");
    }
}
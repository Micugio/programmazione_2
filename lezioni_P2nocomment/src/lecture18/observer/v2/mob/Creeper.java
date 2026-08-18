package lecture18.observer.v2.mob;
import lecture18.observer.v2.ObservingMovingPlayer;
import lecture18.observer.v2.PlayerMovementObserver;
import lecture18.observer.v2.behaviours.ChaseAndAttackBehaviour;
public class Creeper implements PlayerMovementObserver, MobInterface {
    private ChaseAndAttackBehaviour beh;
    private int location;
    public Creeper(){
        this.beh = new ChaseAndAttackBehaviour();
        this.location = -4;
    }
    @Override
    public int getLocation(){
        return this.location;
    }
    @Override
    public void addToLocation(int qty) {
        this.location+=qty;
    }
    @Override
    public void handlePlayerMovement(ObservingMovingPlayer player) {
        this.beh.action(player, this);
    }
    @Override
    public String getName() {
        return "Creeper";
    }
    @Override
    public int getSpeed() {
        return 2;
    }
}
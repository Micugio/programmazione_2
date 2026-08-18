package lecture18.observer.v2.mob;
import lecture18.observer.ObservingPlayer;
import lecture18.observer.PlayerGazeObserver;
import lecture18.observer.v2.behaviours.ChaseAndAttackBehaviour;
public class Enderman implements PlayerGazeObserver, MobInterface {
    private ChaseAndAttackBehaviour beh;
    private int location;
    public Enderman(){
        this.beh = new ChaseAndAttackBehaviour();
        this.location = 20;
    }
    public Enderman(int l){
        this.location = l;
    }
    public int getLocation(){
        return this.location;
    }
    @Override
    public void addToLocation(int qty) {
        this.location+=qty;
    }
    @Override
    public void handlePlayerGaze(ObservingPlayer player) {
        if (!player.hasPumpkinHead()) {
            this.beh.action(player, this);
        }
    }
    @Override
    public String getName() {
        return "Enderman";
    }
    @Override
    public int getSpeed() {
        return 4;
    }
}
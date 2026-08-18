package lecture18.observer.v2.mob;
import lecture18.observer.v2.ObservingMovingPlayer;
import lecture18.observer.v2.PlayerMovementObserver;
import lecture18.observer.v2.behaviours.ApproachBehaviour;
import lecture18.observer.v2.behaviours.Behaviour;
import lecture18.observer.v2.behaviours.WanderBehaviour;
public class Cow implements PlayerMovementObserver, MobInterface {
    private int location = 8;
    private Behaviour beh;
    @Override
    public void handlePlayerMovement(ObservingMovingPlayer player) {
        if ( player.hasGrano()){
            this.beh = new ApproachBehaviour();
        }else{
            this.beh = new WanderBehaviour();
        }
        this.beh.behave();
        this.addToLocation( - this.getSpeed() );
    }
    @Override
    public String getName() {
        return "Cow";
    }
    @Override
    public int getLocation() {
        return this.location;
    }
    @Override
    public void addToLocation(int qty) {
        this.location+=qty;
    }
    @Override
    public int getSpeed() {
        return 1;
    }
}
package lecture18.observer.v2;
import lecture18.observer.ObservingPlayer;
import lecture18.observer.v2.PlayerMovementObserver;
import java.util.HashSet;
import java.util.Set;
public class ObservingMovingPlayer extends ObservingPlayer {
    private Set<PlayerMovementObserver> m_observers;
    public ObservingMovingPlayer() {
        super();
        this.m_observers = new HashSet<>();
    }
    public void move(){
        for (PlayerMovementObserver o: m_observers) {
            o.handlePlayerMovement(this);
        }
    }
    public void add_movement_observer(PlayerMovementObserver e){
        this.m_observers.add(e);
    }
    public boolean hasGrano() {
        return true;
    }
}
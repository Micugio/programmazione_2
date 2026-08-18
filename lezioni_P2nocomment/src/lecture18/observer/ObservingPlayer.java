package lecture18.observer;
import java.util.HashSet;
import java.util.Set;
public class ObservingPlayer extends Player {
    private boolean hasPumpkinHead;
    private Set<PlayerGazeObserver> g_observers;
    public ObservingPlayer() {
        this.hasPumpkinHead = false;
        this.g_observers = new HashSet<>();
    }
    public void wearPumpkinHead(){
        this.hasPumpkinHead = true;
    }
    public void removePumpkinHead(){
        this.hasPumpkinHead = false;
    }
    public boolean hasPumpkinHead(){
        return this.hasPumpkinHead;
    }
    public void look_around(){
        for (PlayerGazeObserver o: g_observers) {
            o.handlePlayerGaze(this);
        }
    }
    public void add_gaze_observer(PlayerGazeObserver e){
        this.g_observers.add(e);
    }
}
package lecture18.observer;
@FunctionalInterface
public interface PlayerGazeObserver {
    void handlePlayerGaze(ObservingPlayer player);
}
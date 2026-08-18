package lecture18.observer.v2;
@FunctionalInterface
public interface PlayerMovementObserver {
    void handlePlayerMovement(ObservingMovingPlayer player);
}
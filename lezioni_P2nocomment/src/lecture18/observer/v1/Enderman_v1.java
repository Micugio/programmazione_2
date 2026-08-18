package lecture18.observer.v1;
import lecture18.observer.ObservingPlayer;
import lecture18.observer.PlayerGazeObserver;
public class Enderman_v1 implements PlayerGazeObserver {
    private int location;
    public Enderman_v1(){
        this.location = 20;
    }
    public int getLocation(){
        return this.location;
    }
    private void lookedBy(ObservingPlayer p){
        if (p.getLocation() < this.location){
            this.location -= 4;
        }
        if (p.getLocation() > this.location){
            this.location += 4;
        }
        if (p.getLocation() == this.location){
            System.out.println("Enderman attacks");
        }
    }
    @Override
    public void handlePlayerGaze(ObservingPlayer player) {
        if (!player.hasPumpkinHead()) {
            this.lookedBy(player);
        }
    }
}
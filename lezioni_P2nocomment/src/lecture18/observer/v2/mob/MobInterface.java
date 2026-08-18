package lecture18.observer.v2.mob;
public interface MobInterface {
    public String getName();
    public int getLocation();
    public void addToLocation(int qty);
    public int getSpeed();
}
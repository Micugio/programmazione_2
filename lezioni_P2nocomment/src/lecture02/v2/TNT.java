package lecture02.v2;
public class TNT {
    public int fuseLength = 5;
    public double explosionPower;
    public boolean isIgnited;
    public TNT() {
        this.isIgnited = false;
        this.explosionPower = 4;
    }
    public TNT(double e){
        this();
        this.explosionPower = e;
    }
}
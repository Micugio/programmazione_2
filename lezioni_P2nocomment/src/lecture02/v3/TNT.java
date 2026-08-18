package lecture02.v3;
public class TNT {
    public int fuseLength = 10;
    public double explosionPower;
    public boolean isIgnited;
    public void ignite() {
        if (this.isIgnited) {
            System.out.println(">> It is already burning!");
            return;
        }
        this.isIgnited = true;
        System.out.println(">> Fuse lit!");
    }
    public void tick() {
        if (this.isIgnited && this.fuseLength >0) {
            this.fuseLength = this.fuseLength - 1;
            System.out.println(">> Ticking... " + this.fuseLength);
            if (this.fuseLength <= 0) {
                this.explode();
            }
        }
    }
    public void explode() {
        System.out.println(">> BOOM! (Block destroyed)");
        this.isIgnited = false;
    }
}
package lecture03.ackages.blocks;
public class TNT {
    private int fuseLength;
    private boolean isIgnited;
    private static final double EXPLOSION_POWER = 100;
    private static int DEFAULT_FUSE_LENGTH = 80;
    public TNT() {
        this.fuseLength = DEFAULT_FUSE_LENGTH;
        this.isIgnited = false;
    }
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
                this.fuseLength = 0;
                this.explode();
            }
        }
    }
    private void explode() {
        System.out.println(">> BOOM! (Block destroyed), danno: " + EXPLOSION_POWER);
        this.isIgnited = false;
    }
}
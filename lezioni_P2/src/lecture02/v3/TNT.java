package lecture02.v3;

public class TNT {
    public int fuseLength = 10;
    public double explosionPower;
    public boolean isIgnited;

    //@ Questo metodo innesca la TNT.
    //@ Se e` gia` innescata, allora ritorna anticipatamente.
    public void ignite() {
        if (this.isIgnited) {
            System.out.println(">> It is already burning!");
            return;
        }
        this.isIgnited = true;
        System.out.println(">> Fuse lit!");
    }

    //@ Questo metodo simula un tick del gioco e riduce il fuso della TNT
    //@ Questo metodo cambia lo stato dell'oggetto corrente, accorciando il fuso
    //@ I metodi possono chiamare altri metodi, per esempio se il fuso e` 0, questo metodo chiama il metodo `explode()`
    public void tick() {
        if (this.isIgnited && this.fuseLength >0) {
            this.fuseLength = this.fuseLength - 1;

            System.out.println(">> Ticking... " + this.fuseLength);

            if (this.fuseLength <= 0) {
                this.explode();
            }
        }
    }

    //@ Il metodo per far scoppiare la TNT stampa a schermo
    public void explode() {
        System.out.println(">> BOOM! (Block destroyed)");
        this.isIgnited = false;
    }
}

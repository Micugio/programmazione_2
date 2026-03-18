package lecture03.ackages.blocks;

public class TNT {
    //? Q: Il campo `isIgnited` dovrebbe essere `static`?
    //@ ---
    //@ A: No, altrimenti innescare una `TNT` le innescherebbe tutte

    //? Q: Il campo `fuseLength` dovrebbe essere `final`?
    //@ ---
    //@ A: No, perche` deve variare (decrementarsi) per il countdown

    //? Q: Perche` rendere `explosionPower` `final`?
    //@ ---
    //@ A: Questo valore non cambia una volta che e` creato un oggetto, e `final` ci rende sicuri che questo valore non cambi per errore

    //? Q: Il campo `explosionPower` dovrebbe essere `static`?
    //@ ---
    //@ A: Si, perche` il potere dell'esplosione e` sempre quello per tutte le `TNT`

    private int fuseLength;
    private boolean isIgnited;
    private static final double EXPLOSION_POWER = 100;
    private static int DEFAULT_FUSE_LENGTH = 80;

    //@ Invariante: il fuso e` sempre un valore positivo
    public TNT() {
        this.fuseLength = DEFAULT_FUSE_LENGTH;
        this.isIgnited = false;
    }
    //@ Invariante: non si puo` innescare piu` volte
    public void ignite() {
        if (this.isIgnited) {
            System.out.println(">> It is already burning!");
            return;
        }
        this.isIgnited = true;
        System.out.println(">> Fuse lit!");
    }

    //@ Invariante: solo se e` innescata, e se ha ancora del fuso, allora il metodo fa qualcosa
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

    //@ Reset dell'invariante: dopo che e` esplosa, si disinnesca
    private void explode() {
        System.out.println(">> BOOM! (Block destroyed), danno: " + EXPLOSION_POWER);
        this.isIgnited = false;
    }
}


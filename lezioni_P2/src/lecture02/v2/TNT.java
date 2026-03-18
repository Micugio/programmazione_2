package lecture02.v2;

public class TNT {
    //@ Tre campi, di tipo `int`, `double` e `boolean`
    //@ Un campo puo` avere un valore iniziale, come `fuseLength` altrimenti questo valore e` inizializzato nel costruttore, oppure settato al valore di default, che dipende dal tipo del campo.
    public int fuseLength = 5;
    public double explosionPower;
    public boolean isIgnited;

    //@ Un costruttore semplice, senza parametri
    public TNT() {
        this.isIgnited = false;
        this.explosionPower = 4;
    }
    //@ Un costruttore con un parametro, che richiama l'altro.
    public TNT(double e){
        this();
        this.explosionPower = e;
    }
}

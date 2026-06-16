package lecture02.v2;

public class TNT {
    //@ Tre campi, di tipo `int`, `double` e `boolean`
    //@ Un campo puo` avere un valore iniziale, come `fuseLength` altrimenti questo valore e` inizializzato nel costruttore, oppure settato al valore di default, che dipende dal tipo del campo.
    public int fuseLength = 5; //@ "(lunghezza miccia tnt)"
    public double explosionPower;
    public boolean isIgnited; //@ "(tnt accessa o spenta)"

    //@ NOTA: Se non viene creato nessun costruttore all'interno della classe, Java crea un costruttore di default cioè un costruttore senza parametri e vuoto

    //@ I costruttori ci permettono di creare degli oggetti imponendo dei vincoli secondo il costruttore

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

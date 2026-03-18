package lecture06;

import lecture06.abstracts.blocks.Dirt;
import lecture06.abstracts.entities.Chicken;
import lecture06.abstracts.entities.Wolf;
import lecture06.overloading.Pick;
import lecture06.overriding.Andesite;
import lecture06.overriding.Bedrock;
import lecture06.overriding.GoldBlock;

//# Lezione 6
public class Lecture6 {

    public static void main(String[] args) {
        //@ BT: Understand, Apply
        System.out.println("---------------- Classi astratte ----------------");
        abstractExample();
        System.out.println("---------------- Metodi astratti ----------------");
        abstractMethodsExample();
        System.out.println("---------------- Overriding e super ----------------");
        overridingExample();
        System.out.println("---------------- Overloading ----------------");
        overloadingExample();
    }

    //@ ## Classi e metodi astratti
    //@ Quando definiamo una classe, possiamo metterci il modificatore `abstract`
    //@ Per esempio, questo succede in `Entity`, dentro `abstracts`.
    //@ Notate che l'ide vi segnala le classi `abstract` diversamente dalle altre

    //@ Una classe astratta serve per raggruppare funzionalita` e logica comune alle sue sottoclassi
    //@ Le regole dell'ereditarieta` si applicano normalmente alle classi astratte, quindi queste possono venire estese da altre classi, che ne ereditano campi e metodi (entrambi, non-`private`)
    //@ Infatti, le sottoclassi di `Entity` hanno accesso ai suoi metodi
    //@ Tipicamente, una classe e` abstract perche` ha un metodo `abstract`, come nel caso di `Block`
    //@ Questo non e` necessario pero`: una classe puo` essere `abstract` pur non avendo metodi `abstract`.

    //@ Le classi `abstract` non possono istanziarsi con la semplice chiamata a `new`
    //@ Come vedremo piu` avanti nel corso, per istanziare una classe `abstract` bisogna creare una classe anonima
    //@ Per ora quindi consideriamo le classi `abstract` come non instanziabili

    //@ Per questo, spesso, il costruttore di una classe abstract e` `protected`
    //@ Questo rende la logica del costruttore riutilizzabile dalle sottoclassi.
    //@ Guardiamo il costruttore di `Entity`, che e` il responsabile di inizializzare i campi `x` e `y` che sono definiti dentro ad `Entity` stessa
    //@ Ha senso permettere il riuso di queste funzionalita` dai costruttori delle sottoclassi
    //@ Per esempio il costruttore di `Chicken` in `abstracts/entities`, chiama `super()` proprio per inizializzare i campi ereditati
    //@ Dopodiche`, questo costruttore contiene la logica di inizializzazione dei campi propri di `Chicken`

    //@ Il metodo `abstractExample` istanzia le sottoclassi di `Entity` e vi chiama sopra dei metodi
    //@ Su `c`, possiamo anche chiamare `makeSound()`, che e` definito in `Chicken`, mentre sia su `c` che su `w` possiamo chiamare `teleport()` che e` definito in `Entity`
    private static void abstractExample(){
        //QUIZ: Posso decommentare questa riga?
        //@ ---
        // Entity e = new Entity();
        Chicken c = new Chicken();
        Wolf w = new Wolf();
        c.teleport(10, 20);
        w.teleport(20,30);
        c.makeSound();
    }

    //@ Consideriamo la classe `Block`
    //@ Questa classe deve essere `abstract` perche` contiene un metodo `abstract` (`getDrop()`)
    //@ Un metodo abstract rappresenta un obbligo per le sottoclassi: se una classe (non-`abstract`) estende `Block`, dovra` implementare `getDrop()`
    //@ Se una classe astratta invece estendesse `Block`, questo obbligo non sussiste
    //@ Questo perche` le classi astratte non si possono istanziare.
    //@ Se si potessero istanziare, avremmo un problema: che codice eseguiamo se chiamiamo `getDrop()` su un oggetto di tipo `Block`?
    //@ Visto che tali oggetti non si possono creare, il problema non sussiste

    //@ Il metodo `getDrop()` ci dice che ogni `Block` deve ritornare qualcosa
    //@ Pero`, non c'e` una implementazione di default, il programmatore che estende `Block` deve sapere che cosa ritornare come drop
    //@ Se proviamo a commentare il metodo `getDrop()` dentro `Dirt` otteniamo un errore dal compilatore
    //@ Questo ci garantisce che non possiamo creare un sottotipo di blocco e dimenticarci di definire cosa droppa

    //QUIZ: Quale codice viene eseguito all'interno di `abstractMethodsExample()`, nella sua chiamata `d.mineWithDrop()`?
    private static void abstractMethodsExample(){
        Dirt d = new Dirt();
        System.out.println("Received: " + d.mineWithDrop());
    }

    //@ ## Overriding
    // PRIMM-like

    private static void overridingExample() {
        //QUIZ: Cosa fa il metodo `mine()` di `GoldBlock`?
        //@ ---

        //@ La keyword `super` (senza le parentesi) si usa per richiamare le funzionalita` della superclasse
        //@ Nota: non si possono concatenare i `super` per risalire la gerarchia di ereditarieta`: non posso scrivere `super.super.super`, ma solo una volta `super`

        //@ I metodi di questa sottoclasse sono decorati con `@Override`
        //@ E` una indicazione opzionale per il compilatore che ci dice che vogliamo sostituire l'implementazione di un metodo di una superclasse, con un'altra implementazione locale
        //@ E` buona pratica usare questa annotazione, cosi` che in caso di typos, il compilatore ci avvisa

        //@ In una sottoclasse, scrivere un metodo con lo stesso nome e firma di un metodo di una superclasse si chiama `overriding`, cioe`, riscrittura.
        //@ Con l'overriding possiamo andare a cambiare, estendere, incapsulare o sostituire il comportamento che ereditiamo dalle superclassi
        //@ Per esempio, il `mine()` di `GoldBlock` si comporta come il `mine()` di `Block`, ma in piu` allerta anche i Piglin
        GoldBlock gb = new GoldBlock();
        gb.mine();
        //QUIZ: Se non facciamo overriding di `mine()` in `GoldBlock` cosa succede?
        //@ ---

        Andesite a = new Andesite();
        a.mine();
        //QUIZ: Posso cancellare il metodo `mine()` dentro `Andesite`?
        //@ ---

        //@ A volte potremmo volere sostituire completamente il comportamento che ereditiamo
        //@ Questo succede nel metodo `mine()` dentro a `Bedrock`
        Bedrock br = new Bedrock();
        br.mine();
    }

    //@ ## Overloading
    //@ Da non confondere con overriding!
    //@ Overloading significa scrivere metodi con nomi uguali e firme diverse

    //@ Consideriamo la classe `Pick`, ha diversi metodi `use`, ognuno di firma diversa
    //QUIZ: Possiamo aggiungere questo metodo a `Pick`?
    //@ ---
    // public Dirt use(Dirt d){}
    //
    //@ Le firme non possono avere stesso input e output diversi, per variare l'output deve variare anche l'input
    //@ Altrimenti non sapremmo cosa chiamare
    private static void overloadingExample(){
        Pick p = new Pick();
        p.use(new Dirt());
        p.use(new Bedrock());
        p.use(new Chicken());
    }

    //@ ## Link Utili
    //@ -
    //@ FIXME: se avete suggerimenti, fateli via pull request

    //@ ## Follow-up

}

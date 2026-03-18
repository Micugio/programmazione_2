package lecture05;

import lecture05.inheritance.*;
import lecture06.overriding.Bedrock;

//@ # Lezione 5
public class Lecture5 {

    public static void main(String[] args) {
        //@ BT: Understand, Analyse, Apply
        System.out.println("---------------- Ereditarieta` ----------------");
        inheritanceExample();
        inheritanceAndConstructorsExample();
        System.out.println("---------------- Polimorfismo di sottotipo ----------------");
        subtypingExample();
        System.out.println("---------------- La classe Object ----------------");
        classObjectExample();
        System.out.println("---------------- Il Diamond Problem ----------------");
    }

    //@ ## Ereditarieta`
    //@ L'ereditarieta` e` un concetto chiave dell'OOP, e ha come obiettivo principale il riuso di codice
    //@ Con l'ereditarieta`, andiamo a strutturare le nostre classe in un albero, con superclassi e sottoclassi
    //@ Intuitivamente, le sottoclassi ereditano campi e metodi (`public` e `protected`, ma non `private`) delle superclassi
    //@ Java permette ereditarieta` singola: per ogni classe c'e` al massimo una superclasse, mentre il numero di sottoclassi non e` limitato
    //@ Altri linguaggi permettono ereditarieta` multipla, togliendo il limite ad una sola superclasse, ma vedremo che questo puo` causare problemi: il diamond problem

    //@ La keyword per definire la gerarchia di ereditarieta` e` `extends`
    //@ Consideriamo le classi dentro il package `inheritance`
    //@ La superclasse `Entity` definisce due campi `protected` ed un metodo `public`
    //@ Poi ci sono due sottoclassi, `Creeper` e `Zombie`
    //@ Nella riga che definisce queste classi, la keyword `extends` dice che sono sottoclassi di `Entity`

    //@ Questo fa si che sia `Creeper` che `Zombie` abbiano:
    //@ - effettivamente anch'essi una copia dei due campi (la cui definizione pero` non viene ripetuta nelle sottoclassi)
    //@ - accesso al metodo `teleport()`, che puo` essere chiamato su oggetti di tipo `Creeper` e `Zombie`s
    //@ Cosi` facendo, il codice di `teleport()` viene scritto una volta sola, e se e` da debuggare, c'e` un solo luogo dove agire

    //@ Le sottoclassi pero`, possono aggiungere:
    //@ - campi (come `isHolding` dentro a `Zombie`)
    //@ - metodi (come `hiss()` dentro a `Creeper`)
    //@ Queste aggiunte sono invisibili alla superclasse `Entity`, ma potrebbero essere visibili ad eventuali sottoclassi

    //QUIZ: Considerate `Drowned` come sottoclasse di `Zombie`. Ha accesso a `isHolding`?
    //@ ---

    //@ Il metodo `inheritanceExample()` alloca uno `Zombie` nella variabile `z`.
    //@ Non possiamo fare `z.x` in quanto `x` e` un campo `protected` di `Entity`, e per quanto `Zombie` ce l'abbia, da questo file (`Lecture5`) non lo vediamo
    //@ Pero` `Zombie` puo` definire il metodo `getX()`, dove accede al campo `x` tramite `this`.
    //@ Per quanto `x` non sia definito dentro `Zombie`, ci abbiamo accesso tramite l'ereditarieta`
    //@ Analogamente, possiamo creare un `Creeper` `c` e possiamo chiamare `teleport(...)`, che e` definito in `Entity` sia su `c` che su `z`
    //@ Pero` possiamo chiamare `hiss` solo su `c` (e non su `z`)
    private static void inheritanceExample() {
        Zombie z = new Zombie();
        Creeper c = new Creeper(0);

        z.teleport(10, 64);
        c.teleport(20, 64);
        z.groan();
        c.hiss();

        Entity e = new Entity();
        //QUIZ: Posso decommentare questa riga?
        // e.hiss()
        //@ ---
        System.out.println();
    }

    //@ I costruttori non si ereditano.
    //@ La classe entity, per esempio, definisce un costruttore `Entity(int x, int y)`
    //@ Da questa classe non possiamo creare uno zombie facendo `Zombie(0,0)`, ne` facendo `Entity(0,0)`
    //@ Da una sottoclasse, pero`, possiamo richiamare i costruttori della superclasse dai costruttori della sottoclasse tramite la keyword `super()`
    //@ Nota: questa keyword ha le parentesi (e gli eventuali argomenti) e non e` da confondere con la keyword `super` senza parentesi, che vedremo piu` avanti
    //QUIZ: Consideriamo il costruttore dentro a `Creeper`, quale riga possiamo decommentare?
    //@ ---

    //@ Ci sono dei vincoli sull'uso del `super()`:
    //@ - deve essere la prima riga di codice del costruttore di una sottoclasse
    //@ - non puo` chiamare costruttori `private` della superclasse

    //@ Visto che la keyword `this()`, per richiamare altri costruttori della stessa classe deve anch'essa essere la prima riga di un costruttore, possiamo avere solo una delle due

    //@ Se non e` presente esplicitamente, il compilatore inserisce sempre una chiamata al costruttore di default della superclasse
    //@ Quindi inserisce una chiamata a `super()`
    //! Analizzate le stampe dentro `inheritanceAndConstructorsExample()`
    //@ ---
    //QUIZ: Cosa stampa se commentiamo la `super(x, y) dentro a `Creeper(x, y)?
    //@ ---
    private static void inheritanceAndConstructorsExample(){
        //QUIZ: Posso decommentare questa riga?
        // Zombie z = new Zombie(0,0);
        //@ ---
        System.out.println("Attraversiamo i costruttori");
        Zombie z1 = new Zombie();
        z1.printLocation();
        Zombie z2 = new Zombie(3);
        z2.printLocation();
        Entity e1 = new Entity();
        e1.printLocation();
        Entity e2 = new Entity(4, 4);
        e2.printLocation();
        Creeper c1 = new Creeper(7);
        c1.printLocation();
        Creeper c2 = new Creeper(7,7);
        c2.printLocation();
    }
    //@ Questo e` come 'funziona' l'ereditarieta`, e quello che vi serve per metterla in atto.
    //@ Adesso vediamo le proprieta` che ci da e gli elementi di design di un linguaggio (e del paradigma OO) che vengono maggiormente influenzati dall'ereditarieta`

    //@ ## Polimorfismo di sottotipo
    //@ Esistono 3 tipi di polimorfismo:
    //@ 1. ad-hoc: o overloading.
    //@ 2. di sottotipo (che vediamo ora)
    //@ 3. parametrico (che vediamo coi Generics)

    //@ L'idea del polimorfismo di sottotipo e` il seguente:
    //@ > una variabile di tipo `A` puo` contenere valori il cui tipo e` sottotipo di `A`
    //@ ---

    //@ Questo polimorfismo e` indotto dalla relazione: 'is-a'
    //@ Ogni oggetto e` del suo tipo, ma e` anche (is-a) di ogni suo supertipo
    //@ Quindi un `Creeper` is-a `Creeper`, ma anche is-a `Entity` e is-a `Object`

    private static void subtypingExample() {
        Entity gr = new Zombie();
        //QUIZ: Posso decommentare questa riga?
        // gr.groan();
        //@ ---
        //QUIZ: Posso decommentare questa riga?
        // gr.teleport(9, 9);
        //@ ---

        //@ Il polimorfismo di sottotipo, e la relazione 'is-a' inducono l' 'upcasting': una variabile di un certo sottotipo viene 'upcast'ata, cioe` messa in e considerata come di un suo supertipo
        //@ Questo ci permette di definire una variabile il cui tipo e` di superclasse, ma che viene istanziata con un oggetto di una sottoclasse
        //@ Per esempio nel metodo `subtypingExample()`, la variabile `gr` ha tipo `Entity`, ma contiene un oggetto di tipo `Zombie`
        //@ Sempre nello stesso metodo, creiamo un array `allMobs` di `Entity` e ci mettiamo dentro oggetti di tipo `Entity`, `Creeper` e `Zombie`
        //@ Il compilatore tratta `gr`, ed il contenuto di `allMobs`, staticamente come oggetti di tipo `Entity`, perche` questo e` il tipo con cui sono stati definiti: questo si chiama tipo 'statico'
        //@ Quindi possiamo chiamare `teleport()` su tutti loro
        //@ Dinamicamente pero`, gli oggetti sono del loro tipo di definizione, questo pero` lo vediamo quando parliamo di static e dynamic dispatch

        Entity[] allMobs = new Entity[3];
        allMobs[0] = new Zombie();
        allMobs[1] = new Creeper(0);
        allMobs[2] = new Entity();

        System.out.println(">> Moving all mobs...");
        for (Entity e : allMobs) {
            e.teleport(0, 0);
        }
    }

    //@ ## La classe Object
    //@ La classe Object e` automaticamente superclasse di tutte le classi
    //@ Se una classe non definisce `extends ...`, Java inserisce automaticamente un `extends Object` a quella classe
    //@ Questo e` utile perche` `Object` fornisce molti metodi utili a tutte le classi
    //@ - `getClass()` dice la classe di un oggetto. Da usare per debuggare, non per funzionalita`
    //@ - `toString()` permette di stampare un oggetto
    //@ - `equals()` dice se due oggetti sono allo stesso indirizzo di memoria
    //@ - `hashCode()` fornisce un codice univoco per un oggetto
    private static void classObjectExample(){
        Object o = new Object();
        System.out.println( o.getClass() );
        System.out.println( o.toString() );
        System.out.println( o.equals(o) );
        System.out.println( o.hashCode() );
    }

    //@ ## Il diamond problem
    //@ Il diamond problem e` un problema di ambiguita` nella chiamata dei metodi nei linguaggi che permettono ad una classe di estendere piu` di una classe
    //@ Consideriamo le classi `GameObject`, `Block` e `LightSource` definite dentro la classe `DiamondProblem`
    //@ Tutte definiscono un metodo `update()`
    //@ Se potessimo creare la `RedstoneLamp`, chiamare `this.update()`, cosa chiama?
    //@ `Block.update()`? oppure `LightSource.update()`?
    //@ Oppure entrambi, ma in che ordine?
    //@ In Java non possiamo, ma per esempio in C++ possiamo


    //@ ## Link Utili
    //@ -
    //@ FIXME: se avete suggerimenti, fateli via pull request

    //@ ## Follow-up
}

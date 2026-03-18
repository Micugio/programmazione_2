package lecture15;

import lecture07.interfaces.Skeleton;
import lecture14.examples.Spawner;
import lecture07.interfaces.Zombie;
import lecture15.factory_method.Tree;
import lecture15.factory_method.TreeFactory;
import lecture15.state.con.v1.Piglin_v1;
import lecture15.state.con.v2.Piglin_v2;
import lecture15.state.senza.v1.Piglin_Sv1;
import lecture15.state.senza.v2.Piglin_Sv2;

//@ # Lezione 15
public class Lecture15 {

    public static void main(String[] args) {
        //@ BT: Understand, Analyse
        System.out.println("---------------- Type Erasure ----------------");
        typeErasureExample();
        //@ BT: Understand, Evaluate
        System.out.println("---------------- Parametricity ----------------");
        // TODO: Paramtericity in therotical sense. Type behaves like itself in Logical Relation. Parametricity explanation is lacking here
        //@ BT: Understand, Evaluate
        System.out.println("---------------- Patterns ----------------");
        factoryMethodExample();
        stateExample();
    }

    //@ ## Type erasure
    //@ Il compilatore di Java cancella le informazioni di tipo relative ai parametri di tipo
    //@ 'Spawner<Zombie>' diventa just 'Spawner', mentre un parametro 'T' diventa 'Object' (oppure il suo bound nel caso di ?).
    //@ Per far funzionare questo, il compilatore aggiunge dei Cast, che non falliscono mai pero`
    //@ Perche`?
    //@ 1. retrocompatibilita`. I generici sono stati aggiunti in Java 5 e fare una cosa diversa avrebbe significato far rompere tantissimo codice
    //@ 2. condivisione. Non si generano istanze diverse di `Spawner`, ma solo una.
    private static void typeErasureExample() {
        Spawner<Zombie> zSpawner = new Spawner<>();
        Spawner<Skeleton> sSpawner = new Spawner<>();

        Class<?> c1 = zSpawner.getClass();
        Class<?> c2 = sSpawner.getClass();
        System.out.println("Are they the same class? " + (c1 == c2));
        System.out.println("Class Name: " + c1.getName());

        //QUIZ: Possiamo decommentare questa riga?
//         if (zSpawner instanceof Spawner<Skeleton>){}
        //@ ---
    }
    //@ Per questo non possiamo scrivere `new T()`, a runtime e` `Object`, non quello che vogliamo.

    //@ Inoltre, nel caso di overloading, potremmo avere questi casi:
    //@ `public void process(Spawner<Zombie> z) { ... }`
    //@ `public void process(Spawner<Skeleton> s) { ... }`
    //@ che diventano
    //@ `public void process(Spawner z) { ... }`
    //@ `public void process(Spawner s) { ... }`
    //@ Quindi non e` permesso.

    //@ # Parametricity e Representation independence
    //@ Il parametric polymorphism ci da questa proprieta`
    //@ Una classe parametrica tipo `Spawner` si comporta sempre in maniera uguale a se stessa, indipendentemente da quale tipo viene istanziato per `T`.
    //@ Se non avessimo instance of, almeno, avremmo questo risultato

    //@ ## Patterns

    //@ #### Factory method
    //@ Il factory method e` un design pattern che serve per raggruppare la logica di costruzione di certi oggetti
    //@ Pensate agli alberi di Minecraft: hanno un tronco di una certa lunghezza, e una chioma semi-sferica ... se sono Oak o Birch. Ma se sono Dark Oak o Spruce, sono alti e hanno il tronco spesso, se sono Mangrovie, sono molto ramificate, nella savana sono sbilenchi, nella giungla hanno i rampicanti
    //@ Mettere tutta questa logica in un costruttore lo rende molto complesso
    //@ Per semplificare il codice, si preferisce creare un Factory Method, cioe` una classe che contiene i metodi per creare gli oggetti complessi -- in questo caso gli Alberi

    //@ La classe il cui costruttore diverrebbe troppo complesso ottiene un costruttore PackagePrivate, ma il suo tipo rimane pubblico e conosciuto a tutti
    //@ Nello stesso package della classe si crea la classe factory
    //@ Nella classe factory si creano metodi `static` che contengono la logica complessa di costruzione degli oggetti
    //@ In questo modo, solo la classe factory (assumendo non ce ne siano altre nel package, come qui) puo` chiamare il costruttore della classe complessa

    //@ Vediamo la classe `Tree`, col costruttore package-private
    //@ Vediamo la classe `TreeFactory`, che contiene i costruttori, gli unici metodi che ritornano un `Tree`
    private static void factoryMethodExample() {
        Tree t1 = TreeFactory.createDarkOak();
        Tree t2 = TreeFactory.createBirch();

        System.out.println("Factory created: " + t1.type);
        System.out.println("Factory created: " + t2.type);
    }
    //QUIZ: Guardate la classe `Tree_Bad`. Perche` e` 'bad'?
    //@ ---
    // factory method nella stessa classe
    // String invece che enums
    // tipo non final
    // height pubblica e liberamente modificabile


    //@ #### State pattern
    //@ Situazione: vogliamo modellare una state machine
    //@ Cos'e` una state machine ? stati e transizioni
    //@ Prendiamo ad esempio: i comportamenti dei piglin, che possono essere disegnati come una state machine
    //! Disegnare alla lavagna
    //@ ---
    //@ Di base uno userebbe una enum per gli stati, tipo `PiglinState_Sv1`
    //@ La classe `Piglin_Sv1` salva lo stato del piglin in un campo
    //@ I suoi metodi catturano le transizioni facendo uno switch sullo stato, e aggiornandolo
    //@ Problemi: code duplication, logica scomposta
    //@ E se vogliamo estendere la state machine?
    //@ Nuova Enum, nuova classe `Piglin_Sv2`, potremmo estendere gli switch vecchi, ma e` poco componibile, e abbiamo la stessa code duplication e logica scomposta

    public static void stateExample(){
        System.out.println("Testing v1 without");
        Piglin_Sv1 pig_s1 = new Piglin_Sv1();
        pig_s1.see_player_no_gold();       // vedo un giocatore senza oro
        pig_s1.unsee_player();             // il giocatore scappa e si mette l'oro
        pig_s1.see_player_wearing_gold();  // vedo un giocatore con l'oro
        pig_s1.see_player_no_gold();       // il giocatore si toglie l'oro

        System.out.println("Testing v2 without");
        Piglin_Sv2 pig_s2 = new Piglin_Sv2();
        pig_s2.player_opens_chest();        // vedo un giocatore aprire una cassa
        pig_s2.see_player_wearing_gold();   // vedo un giocatore indossare l'oro
        pig_s2.see_player_no_gold();        // il giocatore si toglie l'oro
        pig_s2.player_attacks_piglin();     // il giocatore attacca

        //@ La soluzione e` lo state pattern
        //QUIZ: Cosa possiamo usare per gli stati?
        //@ ---
        //QUIZ: Cosa possiamo usare per le transizioni?
        //@ ---

        //@ Per le transizioni creiamo una interfaccia: `PiglinStateInterface_v1` che contiene tutti i metodi che possono fare una transizione
        //@ - player_not_visible, player_wears_gold, player_visible_no_gold,

        //@ Per gli stati creiamo una classe apposita, visto che stiamo considerando 3 stati, creiamo solo quelli per la v1:
        //@ - Passive, Friendly, Aggressive1,
        //@ Queste classi devono implementare l'interfaccia `PiglinStateInterface_v1`, cosi` da avere l'obbligo di dover gestire quelle transizioni.
        //@ Non abbiamo piu` il problema del rischio di perderci dei casi: dobbiamo gestirli tutti
        //@ Abbiamo il problema di codice duplicato, che possiamo fattorizzare
        //@ Abbiamo il problema che dobbiamo inserire codice che gestisce tutti i casi, anche quelli che non hanno senso

        //@ Inoltre devono aggiornare lo stato del piglin su cui hanno compiuto la transizione.
        //@ Per questo, fattorizziamo alcuni comportamenti degli stati, visto che hanno bisogno del piglin, ne creiamo un campo, e lo inizializziamo nel costruttore
        //@ Il tutto dentro alla classe astratta che tutti gli stati estendono: `AbstractPiglinState`

        //@ Vediamo la classe `Piglin_v1`, che contiene come prima il campo dello stato, solo che ora ha il tipo dell'interfaccia degli stati
        //@ In questo caso stiamo anche usando composition over inheritance, in quanto il piglin ha la stessa interfaccia `PiglinStateInterface_v1` degli stati, e delega le transizioni al suo campo che contiene lo stato (essendo di tipo `PiglinStateInterface_v1`
        System.out.println("Testing v1 with");
        Piglin_v1 pig1 = new Piglin_v1();
        pig1.see_player_no_gold();
        pig1.unsee_player();
        pig1.see_player_wearing_gold();
        pig1.see_player_no_gold();

        //@ Nella v2 vogliamo aggiungere comportamento
        //@ Per prima cosa creiamo `PiglinStateInterface` come estensione dell'interfaccia precedente
        //@ Poi creiamo la `Piglin_v2`, e modifichiamo questa e tutte le classi precedenti in modo che lavorino con la nuova interfaccia
        //@ Gli stati della v2 sono di piu`, quindi li aggiungiamo
        //@ - Aggro2, Aggro3
        //@ Le transizioni della v2 sono questi metodi dell'interfaccia:
        //@ - player_open_chest, player_attack_piglin,
        //@ Per fattorizzare il codice, lo mettiamo nella classe astratta, e lo chiamiamo con `super` dalle sottoclassi di stato

        //@ In questo caso vediamo la comodita` di usare dei pattern: estendere la v1 nella v2 con un pattern rende tutto piu` facile.
        //@ Ricordate: stiamo facendo programming in the large

        System.out.println("Testing v2 with");
        Piglin_v2 pig2 = new Piglin_v2();
        pig2.player_opens_chest();
        pig2.see_player_wearing_gold();
        pig2.see_player_no_gold();
        pig2.player_attacks_piglin();

        //! Cosa dovremmo fare se volessimo aggiungere comportamento:
        //@ " Distracted, Bartering, Trading "
        //@ con le azioni:
        //@ " player_mine_gold, player_gives_gold, pick_up_gold, wait, give_item(X) "
        //@ ---
        //@ - Azioni: aggiungere Interfaccia coi metodi delle azioni
        //@ - Stati: aggiungere gli stati nuovi (classi), aggiungere le azioni nuove agli stati vecchi
        //@ - Ottimizzare: fattorizzare comportamento
    }

    //@ ## Link Utili
    //@ - Slides con un buon recap e topics avanzati sulle Wildcards https://ethz.ch/content/dam/ethz/special-interest/infk/chair-program-method/pm/documents/Education/Courses/as2025/coop/lecture04-Types.pdf
    //@ - https://minecraft.fandom.com/wiki/Piglin
    //@ FIXME: se avete suggerimenti, fateli via pull request

    //@ ## Follow-up
    //@ -
}

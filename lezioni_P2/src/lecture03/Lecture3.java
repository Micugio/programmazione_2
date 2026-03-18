package lecture03;

import lecture03.final_mod.FinalPlayer;
import lecture03.final_mod.ToolTier;
import lecture03.ackages.entities.Player;
import lecture03.ackages.blocks.TNT;
import lecture03.ackages.entities.Witch;
import lecture03.static_globals.DragonEgg;
import lecture03.static_globals.GameConstants;

//@ # Lezione 3
public class Lecture3 {

    public static void main(String[] args) {
        //@ BT: Remember & Understand
        System.out.println("---------------- Packages ----------------");
        packagesExample();
        packagePrivateExample();
        System.out.println("---------------- Modificatori di Accesso/Visibilita` ----------------");
        visibilityExample();
        System.out.println("---------------- Final ----------------");
        finalExample();
        System.out.println("---------------- Static ----------------");
        staticExample();
        singletonExample();
    }

    //@ ## Packages
    //@ Un progetto piccolo potrebbe avere una decina di file di codice, Minecraft ne ha piu` di 5,000.
    //@ Non si possono mettere in una sola cartella.
    //@ E questo vale per ogni progetto, specialmente per quello che consegnate all'esame: non usare i packages vi costa punti
    //@ Aggiungiamo tre classi: `Player`, `Witch` e `TNT` (senza guardarle)
    //@ Per organizzarle, creiamo un package `entities`, con `Player` e `Witch`.
    //@ Poi creiamo un package `blocks` e ci mettiamo la classe `TNT`
    //@ Ci sono diverse organizzazioni per i packages, in questo progetto organizziamo i vari file in base alle loro features

    //@ Concretamente, un package e` tante cose.
    //@ E` un namespace, che previene conflitti di nome, come nel caso delle TNT
    //@ E` la struttura delle cartelle nel vostro hard disk, dentro alle quali sono organizzati i file di un progetto
    //@ Per usare una classe definita dentro a un package `A` da un altro package `B`, bisogna importarla, come
    //@ Le righe di `import` in cima al file rendono visibili le classi che sono in altri packages

    //@ Questo metodo stampa le informazioni di package di due oggetti.
    //@ Per capire bene i loro tipi, controllate gli `import`
    //@ Tipicamente, se non state debuggando (o facendo override della `equals` --- cosa che capirete piu` avanti), non dovrete MAI usare il metodo `getClass()`
    //@ Spieghero` perche` quando parliamo di polimorfismo, casting e instanceof
    private static void packagesExample() {
        Player p = new Player();
        TNT b = new TNT();
        System.out.println("Loaded " + p.getClass().getName());
        System.out.println("Loaded " + b.getClass().getName());
    }

    //@ ## Modificatori di visibilita`/di accesso (e incapsulazione)
    //@ I modificatori di visibilita` ci permettono di regolare l'accesso a campi e metodi, permettendoci di realizzare 'encapsulation'
    //@ Ci sono i seguenti modificatori:
    //@ - `public` : visibile a tutti (altri packages, altre classi)
    //@ - `private` : visibile solo al file / alla classe dichiarante
    //@ - `protected` : visibile ai figli nella gerarchia di ereditarieta` (ne parliamo in lezione 5)
    //@ - 'nulla', cioe` Package-Private: se non scrivete nulla, il modificatore e` Package-Private, il che significa visibile a classi nello stesso package

    //@ Consideriamo la classe `Player` dentro `ackages/entities`
    //@ Tutti possono vedere il campo `username`, quindi e` `public`
    //@ --Attenzione, cosi` pero` lo possono sia leggere che scrivere!
    //@ Il campo `name` evita questa cosa con `final`, che spieghiamo dopo
    //@ La vita, invece, e` privata, come il fatto che il personaggio sia avvelenato o meno.
    //@ Quindi i campi `health` e `isPoisoned` sono `private`
    //@ Questo ci impedisce di andare dentro un'altra classe, tipo `Witch` e modificare direttamente la vita, per esempio bypassando l'armatura
    //
    //@ Per modificare lo stato `private` in maniera consona, si usano i metodi.
    //@ I modificatori si applicano anche ai metodi.
    //@ Questo ci permette di creare dei metodi `public`, che tutti possono invocare
    //@ E dei metodi (`private` o package-private) che invece servono solo localmente

    //@ Alcuni metodi hanno un nome speciale: getters e setters sono metodi che ritornano il valore di un campo, o modificano il valore di un campo dato un certo input
    //@ Per esempio dentro a `Player` ci sono un getter e un setter
    //@ Nota: creare un campo `private` e poi getters e setters per quel campo, e` un errore grave

    //@ Il metodo `visibilityExample()` crea un nuovo `Player` e cerca di accederne i campi
    public static void visibilityExample() {
        Player steve = new Player();
        //QUIZ: posso decommentare questa riga?
        //@ ---
        // steve.username = "Steve";
        //QUIZ: posso decommentare questa riga?
        //@ ---
        // steve.health = 0;

        steve.damage(5);
    }

    //@ Per capire il funzionamento di Package-Private, andiamo dentro a `Witch`
    //@ Visto che il campo `fakeHealth` di `Player` e` Package-private, possiamo accedervi
    //@ Il campo `health` e` `private` e rimane accessibile solo dentro a `Player`
    public static void packagePrivateExample(){
        Witch w = new Witch();
        Player p = new Player();
        w.fakeAttack(p);

        p.isAlive();
        w.attack(p);
        p.isAlive();
        //QUIZ: ordinate i metodi dentro `attack` per uccidere `p`
    }

    //@ #### I modificatori in un linguaggio
    //@ I modificatori in Java sono rispettati a livello di linguaggio
    //@ Vediamo un esempio di C++ dove i modificatori sono bypassabili (cosa non possibile in Java)
    //@ File 'packages/Player.cpp'

    //@ ## Final
    //@ La keyword `final` si puo` aggiungere a campi e a metodi, ha due significati diversi.
    //@ Per ora vediamo solo l'applicazione ai campi.
    //@ Un campo `final` diventa una costante, si puo` inizializzare, leggere, ma non scrivere

    //@ Consideriamo la classe `FinalPlayer`, che modifica `Player` rendendone il nome `final` oltre che `private`
    //@ Il campo `final` deve essere inizializzato.
    //@ Si puo` aggiungere il valore quando dichiariamo il campo, oppure dentro ad un costruttore
    //QUIZ: Cosa succede se commentiamo il corpo del default constructor di `FinalPlayer`?
    //@ ---

    //@ Consideriamo la classe `ToolTier`, dove I campi `efficiency` e `maxUses` sono 'final'.
    //@ Una volta settati dal costruttore, non possono cambiare
    //@ Ogni istanza della classe ha questi valori.
    //@ La velocita` e` una costante, non un valore che puo` variare.
    //@ Rendere `efficiency` `final` garantisce che il Diamante sia piu` veloce del Legno, per come abbiamo impostato i valori
    //@ E nessun altro pezzo di codice puo` (anche accidentalmente!) violare questa cosa

    //@ ###### Nota: Immutabilita
    //@ Un concetto di Ingegneria del Software e` che la mutazione (cioe` il permettere che i dati cambino) e` una fonte di bug
    //@ Se una variabile cambia valore inaspettatamente, il comportamento del programma diventa impredicibile
    //@ L'Immutabilita` ci permette di fidarci che lo stato di un oggetto non cambiera`, sia per un attacco hacker, che per un problema

    //@ Il metodo `finalExample()` alloca oggetti con campi `final`. Il codice commentato non puo` essere decommentato, o dara` errore
    public static void finalExample() {
        FinalPlayer fp = new FinalPlayer();
//        fp.name = "Steeeve";

        ToolTier currentTier = ToolTier.DIAMOND;
        System.out.println("Selected: " + currentTier);
        System.out.println("Speed: " + currentTier.getEfficiency());
        System.out.println("Durability: " + currentTier.getMaxUses());
        // currentTier.efficiency = 500.0f;
    }

    //@ #### Static
    //@ La keyword `static` serve per identificare campi e metodi essenzialmente globali
    //@ Intuitivamente, un campo o un metodo `static` appartiene alla classe, e non all'oggetto
    //@ In entrambi i casi, li si accede cosi`: `Classe.campo_statico` oppure `Classe.metodo_statico()`.
    //@ Dato un oggetto di una Classe, invocare `oggetto.campo_statico` o `oggetto.metodo_statico()` e` equivalente a scrivere `Classe.campo_statico` e `Classe.metodo_statico()`.
    //@ Un campo `public` e `static` si puo` considerare una variabile globale.
    //@ Di questi campi, ne esiste una sola copia, non una per oggetto come per i campi non-`static`
    //@ Un metodo `static` e` propriamente una funzione (non un metodo) in quanto non necessita di un oggetto per essere eseguito
    //@ Per esempio, `Math.sqrt( ... )` non ha bisogno di un oggetto su cui essere invocato, mentre `player.damage()` funziona su una precisa istanza di un giocatore
    //@ Come gli altri metodi, anche dei metodi `static` ne esiste una sola copia
    //@ I metodi `static` quindi non possono usare la keyword `this` al loro interno
    //@ Inoltre, i metodi `static` possono fare riferimento solo a campi `static` e ad altri metodi `static`
    //@ Questo perche` tali metodi possono essere invocati anche se non ci sono oggetti di quella classe, quindi non ha senso poter far riferimento a campi non-`static`

    //@ Di seguito presentiamo i casi d'uso piu` frequente per gli `static`:
    //@ - Costanti globali: Informazioni da condividere, tipo 'MAX_STACK_SIZE'.
    //@ - Singleton: Serve per far in modo che ci sia un solo oggetto di una certa classe (es, Dragon Egg).
    //@ - Factory Methods: Metodi statici per creare oggetti complessi (es, Trees).
    //@ Gli ultimi due sono 'design patterns' cioe` principi di Ingegneria del Software che sono nati per facilitare la scrittura del codice e racchiudere in una singola metodologia delle soluzioni frequenti a problemi comuni

    //@ In OOP i dati appartengono a oggetti, tipicamente (es, la vita di un giocatore)
    //@ Altri dati, invece, rappresentano dati generali, comuni a tutti
    //@ Il valore di uno stack di blocchi e` tipicamente 64
    //@ Non vogliamo segnare questo valore in ogni oggetto, immaginate lo spreco di memoria!
    //@ Invece lo segnamo una volta sola, nella classe `GameConstants`
    //@ Vediamo la classe `GameConstants`
    //@ La classe `System` contiene tante istanze di `static`

    private static void staticExample() {
        System.out.println("Max Stack Size: " + GameConstants.MAX_STACK_SIZE);
        GameConstants g = new GameConstants();
        //QUIZ: Quali di queste righe si puo` decommentare?
        //@ ---
        int c = GameConstants.MAX_STACK_SIZE;
        int s = g.SMALL_MAX_STACK_SIZE;
//        GameConstants.method();
        g.method();
        g.printMOTD();

        //@ Le variabili statiche possono mutare
        //QUIZ: Come possiamo prevenire questa riga?
        //@ ---
        GameConstants.MAX_STACK_SIZE = 1;

        //QUIZ: Cosa e` `static` in `System.out.println()`?
        //@ ---

        //@ System contiene altre cose utili:
        System.err.println("Print on the error stream");
        //@ Fai il timing di certe funzionalita`
        System.currentTimeMillis();
        //@ Chiama il garbage collector
        System.gc();
    }

    //@ Vediamo ora due design pattern
    //@ Tutto il software serio e` sviluppato seguendo patterns.
    //@ In questo corso non voglio farveli vedere tutti, ma parlare di OO senza parlare di pattern e` strano
    //@ Anche perche` molte delle scelte dei linguaggi OO influenzano e sono influenzate dai pattern.
    //@ Nota: I design pattern sono elemento fondante di questo corso, e si trovano nei temi d'esame (non in quelli pre 2025/2026)

    //@ #### Il Singleton Pattern
    //@ Alcune cose del gioco sono uniche: i settings, oppure l'uovo di drago
    //@ Questo pattern usa la keyword `static` per fare in modo che ci sia una sola istanza di una certa classe: un solo oggetto

    //@ Vediamo la classe `DragonEgg`
    //@ Esiste una sola istanza di uovo di drago in tutto il gioco, e per farlo usiamo il pattern `Singleton`
    //@ Per realizzare questo pattern seguiamo questi passi:
    //@ - c'e` una singola istanza del `DragonEgg`, creata a loading time, salvata in un campo `private` e `static`
    //@ - il costruttore e` `private`: nessuno puo` chiamarlo se non all'interno della classe
    //@ - c'e` un getter `public`, ma e` anche `static`, e` l'unica funzionalita` che ritorna un `DragonEgg`
    //@ In questo modo, l'unico oggetto viene creato a loading time, tramite il costruttore privato, quando il gioco ne ha bisogno, accede a questo oggetto tramite il getter
    //@ Una volta ottenuto, l'oggetto e` normale, e si comporta come tutti gli altri oggetti

    //@ Il metodo `singletonExample()` utilizza il singleton su `DragonEgg`
    //@ Non possiamo fare 'new DragonEgg()', il costruttore e` `private`!
    private static void singletonExample(){
        DragonEgg egg = DragonEgg.getInstance();
        egg.teleport();

        DragonEgg egg2 = DragonEgg.getInstance();
        System.out.println("Is it the exact same egg? " + (egg == egg2));
        //QUIZ: cosa stampa?
        //@ ---
    }

    //@ ## Link Utili
    //@ -
    //@ FIXME: se avete suggerimenti, fateli via pull request

    //@ ## Follow-up
}

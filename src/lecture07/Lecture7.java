package lecture07;

import lecture06.abstracts.Entity;
import lecture07.interfaces.*;
import lecture07.defaults.*;

//# Lezione 7
public class Lecture7 {

    public static void main(String[] args) {
        //@ BT: Understand, Apply
        System.out.println("---------------- Interfacce (come contratti) ----------------");
        intefacesExample();
        System.out.println("---------------- Contratti multipli ----------------");
        interfacesAndDiamondExample();
        //@ BT: Analyse
        System.out.println("---------------- Polimorfismo tramite interfacce ----------------");
        //@ BT: Understand, Apply
        interfacesPolymorphismExample();
        System.out.println("---------------- Defaults ----------------");
        defaultsExample();
        //@ BT: Analyse
        System.out.println("---------------- Chiamante e chiamato ----------------");
        caller_calleeExample();
        System.out.println("---------------- Recap ----------------");
    }

    //@ ## Interfacce
    //@ Le interfacce sono altri file di un progetto Java, ma si differenziano (molto) dalle classi nel modo in cui si definiscono
    //@ Allo stesso tempo, le interfacce sono tipi, come le classi, quindi si comportano spesso in modo analogo
    //@ Fisicamente, le interfacce sono collezioni di metodi astratti, senza implementazione
    //@ Non possono avere ne` campi, ne` costruttori
    //@ Le classi possono implementare interfacce, cosi` come estendono altre classi, introducendo una gerarchia di erediarieta` tra classi ed interfacce
    //@ La relazione 'is-a' che si crea tra una sottoclasse ed una superclasse si crea anche tra una (sotto)classe ed una interfaccia implementata dalla (sotto)classe

    //@ Intuitivamente, le interfacce forniscono degli obblighi, possiamo pensarle come dei contratti
    //@ Definendo solo metodi astratti, senza implementazione, obbligano le loro sottoclassi a implementare tali metodi

    //@ Per fare un esempio concreto, le prese della corrente danno un contratto (alle spine) di forma e voltaggio
    //@ Un microonde puo` rispettare il contratto di una certa presa, e venirne alimentato
    //@ Alla presa non interessa che sia attaccato un microonde, una TV, etc, ma solo che abbiano la spina giusta
    //@
    //@ Nel caso di Minecraft, essere una `Entity` ci da informazioni sullo stato (le coordinate)
    //@ Pero`, un mob potrebbe essere un `Enemy`, informazione che ci dice come si comporta quel mob (es, attacca)
    //@ Un `Wolf` e` si una `Entity`, ma potrebbe essere un `Enemy`

    //@ #### L'interfaccia `Enemy`
    //@ Tutti i metodi di una interfaccia sono `public`, non hanno bisogno di questo modificatore
    //@ L'interfaccia `Enemy` stipula un contratto: le sottoclassi di `Enemy`, cioe` tutte le classi che 'is-a' `Enemy`, devono avere due comportamenti: `attack` e `playAggressiveSound`

    //@ #### L'interfaccia `RangedAttackMob`
    //@ Questa interfaccia stipula un altro contratto: `attack` e `performRangedAttack`

    //@ Le due interfacce sono scorrelate, infatti non tutti gli `Enemy` sono `RangedAttackMob` (es, gli zombie)
    //@ Allo stesso modo, non tutti i `RangedAttackMob` sono `Enemy` (per es. pensate agli Snow Golem)

    //@ #### La classe `Zombie`
    //@ La riga `import lecture06.abstracts.Entity;` ci dice a quale `Entity` fa riferimento la classe `Zombie`
    //@ Dopo la keyword `extends` troviamo una nuova keyword: `implements`, seguita dal nome dell'interfaccia implementata da `Zombie`

    //@ Il contratto che riceviamo implementando `Enemy` ci dice di fornire l'implementazione ai due metodi definiti nell'interfaccia
    //QUIZ: Cosa succede se commento `attack` dentro a `Zombie`?
    //@ ---

    //@ Il metodo `interfacesExample` illustra che possiamo chiamare i metodi definiti nell'interfaccia `Entity` su un oggetto `Zombie`
    private static void intefacesExample() {
        Zombie z = new Zombie();
        z.attack(z);
        z.playAggressiveSound();
    }

    //@ ## Interfacce e contratti multipli
    //@ Consideriamo la classe `Skeleton`
    //@ Il vincolo di estendere una sola classe non si applica alle interfacce, infatti possiamo implementarne piu` di una
    //@ Lo `Skeleton` e` si` un `Enemy`, ma e` anche un `RangedAttackMob`
    //@ Pertanto, dentro `Skeleton` abbiamo gli obblighi derivanti dai contratti di entrambe le interfacce
    //QUIZ: Cosa devo fare per `attack` in `Skeleton`?
    //@ ---


    //@ Le interfacce `Entity` e `RangedAttackMob` hanno una signature identica: `attack() : Entity-> Void`
    //@ In questo caso, non c'e` un diamond problem, come nel caso di ereditarieta` multipla, perche` non c'e` una implementazione da scegliere
    //@ Di nuovo, le interfacce forniscono contratti, o obblighi, e una singola implementazione di `attack` soddisfera` entrambi gli obblighi

    private static void interfacesAndDiamondExample() {
        Skeleton skelly = new Skeleton();
        skelly.performRangedAttack(null, 10);
        skelly.attack(null);
    }

    //@ Le interfacce permettono ereditarieta` multipla di tipo, ma non permettono ereditarieta` multipla di stato
    //@ Non permettono ereditarieta` multipla di comportamento, perche` le firme di metodi nelle interfacce non hanno implementazione
    //! Ci sono altri costrutti che permettono ereditarieta` multipla di stato (Mixins) ed ereditarieta` multipla di comportamento (Traits)

    //@ ## Interfacce e polimorfismo
    //@ Le classi astratte ci danno una visione verticale della gerarchia di ereditarieta`
    //@ Risalendo l'albero delle classi, capiamo tutti i tipi che un oggetto e` (is-a)
    //@ Le interfacce, invece, definiscono una gerarchia orizzontale di abilita`
    //@ Ci dicono tutte le cose che un oggetto puo` fare (anche se il tipo di relazione e` sempre la is-a)
    //@ Implementando piu` interfacce, una classe puo` quindi decidere le sue abilita` e quello che puo` fare a livello comportamentale

    //@ Per fare un esempio concreto, gli oggetti del mondo reale sono complessi, e difficilmente si puo` definire una singola gerarchia di ereditarieta` per caratterizzare tutte le loro dimensioni
    //@ Per esempio uno Smartphone e` un Telefono, una Telecamera, un Lettore di Musica e un Web Browser
    //@ Se queste fossero classi, come dovremmo organizzarle in una gerarchia di ereditarieta`?
    //@ Ma queste non devono essere classi, possono essere interfacce che dicono cosa ci si aspetta da un oggetto che le implementa

    //@ Il metodo `interfacesPolymorphismExample` introduce un esempio di 'aliasing'
    //@ L'aliasing e` una caratteristica fondante dell'OO, e` l'abilita` di avere piu` riferimenti allo stesso oggetto
    //@ Di seguito, la variabile `n` punta allo stesso oggetto della variabile `skelly`
    //@ L'aliasing e` utile, ma uccide le capacita` di ragionare sul codice: metodi per controllare l'aliasing sono quelli che hanno portato all'ownership, e a Rust
    private static void interfacesPolymorphismExample() {
        Skeleton skelly = new Skeleton();

        //QUIZ: posso usare una classe astratta come tipo?
        //@ ---
        Entity n = skelly;
        n.teleport(0, 0);

        //QUIZ: posso usare una intefaccia come tipo?
        //@ ---
        Enemy badGuy = skelly;
        badGuy.playAggressiveSound();
        //QUIZ: Posso chiamare teleport su badguy?
        //@ ---

        RangedAttackMob sniper = skelly;
        sniper.performRangedAttack(null, 1.0f);
        skelly.performRangedAttack(null, 1.0f );
    }

    //@ ## Default
    //@ Le interfacce possono essere organizzate in una gerarchia di ereditarieta` a loro volta, tramite la keyword `extends`
    //@ Possiamo avere sottointerfacce e soprainterfacce
    //@ Per esempio l'interfaccia `Boss` nel package `defaults` e` sottointerfaccia di `Enemy`
    //@ Questo perche` tutti i `Boss` sono `Enemy`, quindi un `Boss` deve potersi comportare come un `Enemy`, e anche fare qualcosa in piu`

    //@ L'interfaccia `Boss` introduce una keyword nuova: `default`
    //@ Questa ci permette di dare un corpo alle interfacce, una implementazione di default
    //@ Tecnicamente, questo rende le interfacce di Java molto piu` simili ai Traits, come concetto di Linguaggi di Programmazione
    //@ L'implementazione di `announceArrival` e` quindi disponibile per tutte le classi the implementano `Boss`
    //@ Una classe pero` puo` fornire la sua implementazione, che avra` la precedenza sul `default`
    //@ Questi metodi non sono accedibili tramite la keyword `super` dalle sottoclassi
    //@ Infine, c'e` un metodo `private`, che serve come helper per il metodo default

    //@ Consideriamo la classe Wither
    //@ Non abbiamo implementato `announceArrival()`, il `Wither` usa l'implementazione di default

    //QUIZ: Da quale interfaccia arriva questo obbligo: `attack`?
    //@ ---
    //QUIZ: Da quale interfaccia arriva questo obbligo: `playAggressiveSound`?
    //@ ---
    //QUIZ: Da quale interfaccia arriva questo obbligo: `performSpecialAttack`?
    //@ ---

    private static void defaultsExample(){
        Wither w = new Wither();
        w.attack(null);
        w.playAggressiveSound();

        Boss b = w;
        b.announceArrival();
        b.performSpecialAttack();
    }

    //@ ## Ragionare come chiamante e come chiamato
    //@ La gerarchia di ereditarieta` che introduciamo estendendo classi, classi astratte, ed implementando interfacce, definisce i tipi che possiamo usare nel nostro programma
    //@ Oltre a dare questi tipi alle variabili, questi vengono usati come parametri per chiamate di metodi
    //@ Ragioniamo sul tipo da mettere ad un metodo da due punti di vista:
    //@ - il punto di vista del chiamante: cioe`, che oggetto passo ad un metodo che si aspetta un certo tipo?
    //@ - il punto di vista del chiamato (o implementatore): cioe`, quale e` il tipo che mi serve per questo parametro?

    //@ Consideriamo il metodo `takeSound`, che prende un oggetto sul quale abbiamo bisogno di chiamare solo il metodo `playAggressiveSound`: ha senso definire quell'oggetto di tipo `Enemy`
    //@ Ha senso quindi definire il tipo piu` generale possibile, cosi` che possiamo passarci sia uno `Zombie`, che un `Wither` che con uno `Skeleton`
    //@ Se avessimo definito parametro di `takeSound` di tipo `Zombie`, l'implementazione del metodo non sarebbe cambiata, ma sarebbe cambiato il modo in cui il metodo viene usato: non avremmo potuto chiamarlo con un `Wither`
    //@ Questo funziona in base all'assunzione iniziale: dentro al metodo `takeSound`, abbiamo bisogno solo di chiamare il metodo `playAggressiveSound`

    private static void caller_calleeExample(){
        Zombie z = new Zombie();
        takeSound(z);
        Wither w = new Wither();
        takeSound(w);
        Skeleton sk = new Skeleton();
        takeSound(sk);
    }
    private static void takeSound(Enemy r){
        r.playAggressiveSound();
    }


    //@ ## Recap e limitazioni delle interfacce
    //@ #### Contratti
    //@ Il game engine non si preoccupa se un oggetto è uno `Zombie`, uno `Skeleton` o un `Creeper`.
    //@ Sapere se questo oggetto implementa `Enemy` ci garantisce che esiste un metodo `attack()`.
    //@
    //@ #### Capacità
    //@ Con le interfacce possiamo comporre oggetti complessi aggiungendo più "tag" o capacità.
    //@ Uno `Skeleton`:
    //@ - Eredita lo stato (e alcuni comportamenti) da `Entity` (is-a `Entity`)
    //@ - Adotta `playAggressiveSound` da `Enemy`
    //@ - Adotta `performRangedAttack` da `RangedAttackMob`

    //@ #### I Limiti delle Interfacce
    //@ Anche con le funzionalità moderne (Metodi Default), le interfacce hanno limiti rigidi.
    //@
    //@ ##### Limite 1: Nessuno Stato
    //@ Le interfacce non possono contenere variabili di istanza, quindi non puoi inserire `int health = 100;` dentro `Enemy`.
    //@
    //@ ##### Limite 2: Nessun Costruttore
    //@ Poiché le interfacce non hanno stato, non possono avere costruttori.
    //@
    //@ ##### Limite 3: API Fragili
    //@ Se crei l’interfaccia `Enemy` e 50 classi diverse la implementano, non puoi facilmente aggiungere un nuovo metodo in seguito.
    //@ Aggiungere `void sleep();` a `Enemy` rompe immediatamente tutte e 50 le classi perché non implementano `sleep`.
    //@ (Per questo Java 8 ha introdotto i metodi `default`).


    //@ ## Link Utili
    //@ - [Oracle Docs: Interfaces and Inheritance](https://docs.oracle.com/javase/tutorial/java/IandI/createinterface.html) - Official Java documentation on defining interfaces.
    //@ FIXME: se avete suggerimenti, fateli via pull request

    //@ ## Follow-up
    //@ #### Structural VS nominal subtyping
}

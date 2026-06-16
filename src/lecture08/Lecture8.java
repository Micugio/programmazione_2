package lecture08;

import lecture08.strategy.strategies.*;
import lecture08.strategy.Piglin;

//@ # Lezione 8
public class Lecture8 {
    public static void main(String[] args){
        //@ BT: Analyse
        System.out.println("---------------- Ereditarieta` VS Composizione ----------------");
        //@ BT: Understand, Apply, Evaluate
        System.out.println("---------------- Strategy pattern ----------------");
        strategyPatternExample();
    }

    //@ ## Ereditarieta` VS Composizione
    //@ L'ereditarieta` e` rigida, crea un albero statico con la relazione 'is-a'
    //@ Supponiamo di voler creare uno Zombie Piglin, in Java non possiamo scrivere:
    //@ `class ZombiePiglin extends Piglin, Zombie`
    //QUIZ: Perche`?
    //@ ---
    //
    //@ `ZombiePiglin` dovrebbe ereditare da due classi, ha sostanzialmente due 'dimensioni' (ripensate all'esempio dello smartphone, che ha molte piu` dimensioni)
    //@ Definire le dimensioni a livello di classi non e` fattibile, porta a duplicazione del codice e una esplosione combinatoria delle possibilita` di incrocio
    //
    //@ L'ereditarieta` pero` e` molto utile.
    //@ A livello intuitivo, come vedremo nei Lab, dobbiamo chiederci l'identita` di certi oggetti, e definire la gerarchia delle classi in base a quello
    //@ Il tutto modularizzando identita` (campi e costruttori in primis) tramite classi astratte
    //@ Il comportamento, invece, lo definiamo (spesso) a livello di interfacce

    //@ Minecraft usa `extends per `Block`, `Item` e `Entity`, perche` queste classi gestiscono logica del game engine
    //@ Ma come si fa a risolvere il diamond problem, specializzando una classe lungo diverse dimensioni?
    //@ Una soluzione e` lo Strategy Pattern, che fa uso di Composizione invece che di ereditarieta`

    //@ La composizione e` un principio secondo il quale un oggetto e` composto da tanti sotto-oggetti di tipo diverso (salvati nei campi dell'oggetto originale)
    //@ L'oggetto poi delega comportamento specifico ai vari sotto-oggetti
    //@ La composizione introduce una relazione diversa rispetto all'ereditarieta`, e si chiama 'has-a' (a differenza della 'is-a')

    //@ ## Lo Strategy Pattern
    //@ Consideriamo le classe e le interfacce della Lezione 7:
    //@ `Skeleton` e `Zombie` estendono `Entity`, entrambe implementano `Enemy` e uno implementa `RangedAttackMob`
    //@ Abbiamo usato l'ereditarita` per definire cosa un mob e`, e le interfacce per definire cosa faccia
    //@ Il problema che si pone ora e` quello della rigidita` (proprio a runtime):
    //@ - Se uno `Zombie` prende un Arco, diventa uno `Skeleton`?
    //@ - Se uno `Skeleton` finisce le frecce, diventa uno `Zombie`?
    //@ Facciamo un esempio` piu` concreto da Minecraft
    //@ Supponiamo di voler scrivere un `Piglin`:
    //@ - che attacco fa di base? Se e` la crossbow, diventa un `RangedPiglin`?
    //@ - se gli si rompe la `Crossbow`, che attacco fa? Diventa un `PunchPiglin`?
    //@ - se prende una spada da terra, che attacco fa? Diventa un `SwordPiglin`?
    //@ Provando a risolvere questo problema con l'ereditarieta` e` complesso:
    //! Quale sarebbe la relazione tra le classi `Piglin`, `RangedPiglin`, `PunchPiglin` e `SwordPiglin`?
    //@ ---
    //@ La soluzione e` usare la composizione e la 'has-a' relation che introduce
    //@ Invece che essere un `RangedPiglin`, definiamo solo `Piglin` come classe
    //@ Pero`, il `Piglin` ha un metodo generico di attacco che delega la sua logica implementativa a un sott-oggetto
    //@ Questo sotto-oggetto e` responsabile di dire se usa la crossbow, se da un pugno o una spadata

    //@ Il problema che affrontiamo ora e` il seguente:
    //@ La logica di usare la crossbow, di dare un pugno, e di dare una spadata risiedera` realisticamente in tre classi diverse
    //@ Questo perche` ci permette di estendere il codice modularmente: per esempio potremmo dover aggiungere un attacco da `Axe` nel caso dei `PiglinBrute`
    //@ Come facciamo a chiamare la logica di ogni attacco?
    //@ In questo caso ci viene in soccorso il concetto di Interfacce
    //@ Le interfacce permettono di definire una interfaccia comune , un insieme di comportamenti che ci si aspetta da tutti gli oggetti che sono suoi sottotipi --- che la implementano
    //@ Il primo step e` quello di definire questa interfaccia (che chiameremo `AttackStrategy`) e un metodo che applica l'attacco ( in questo caso `execute()`)
    //@ Pertanto, tutte le classi che definiscono la logica di un attacco (`CrossbowAttack` e `PunchAttack`), devono implementare `AttackStrategy` e quindi dare una implementazione al metodo `execute` che applica l'attacco
    //@ Ogni classe specializzera` questo metodo con la propria logica (calcolo della distanza per la crossbow, etc)
    //@ La classe `Piglin` poi, una volta che attacchera`, deleghera` al sottooggetto dell'interfaccia `AttackStrategy`, la logica dell'attacco

    //@ Questa e` sostanzialmente l'idea dietro allo Strategy Pattern
    //@ Un aspetto chiave del pattern e` la sua capacita` di far mutare il comportamento di un oggetto nel tempo
    //@ Infatti, il sotto-oggetto responsabile della logica di attacco del `Piglin` puo` essere cambiato durante la vita di un oggetto `Piglin`
    //@ Se ad un `Piglin` si rompe la `Crossbow`, basta sostituire il sottooggetto di tipo `CrossbowAttack` con uno di tipo `PunchAttack` ed il `Piglin` cambiera` comportamento di attacco
    //@ Notate che questo non richiede nessun cambiamento nella classe `Piglin`!

    //@ #### Strategy Pattern in azione
    //@ Realizziamo lo strategy pattern appena visto per i `Piglin`
    //@ ## 1. The Generic Context
    //@ We create a "Mob". It is not a Zombie or Skeleton class. It is just a container.
    //@ We give it a "Melee" brain to start.
    //@ The most important part is that we can change the behaviour not at runtime/dynamically

    //@ #### La classe `Piglin`
    //@ Questa classe usa lo strategy pattern
    //@ Il campo `currentStrategy` e` l'oggetto con cui il `Piglin` ha la 'has-a' relation
    //@ NON implementiamo i diversi `meleeAttack` o `crossbowAttack` come metodi qui` dentro!
    //@ Invece, delegheremo al contenuto di `currentStrategy`
    //! Il `Piglin` non sa come fare danno, delega questo comportamento al contenuto di `currentStrategy`
    //@ ---
    //@ Il costruttore, tipicamente, sceglie la strategia di base
    //@ Per poter far variare la strategia a runtime, creiamo anche un setter

    //@ #### L'interfaccia `AttackStrategy`
    //@ Questa interfaccia rappresenta l'obbligo di saper realizzare un certo comportamento
    //@ Definisce solo l'azione `execute`
    //@ Qualsiasi variabile che ha questo tipo deve poter fare questo comportamento, nulla piu`
    //@ Il contratto e`: tutte le strategie di attacco possono essere `execute`d

    //@ #### `PunchAttack` e `CrossbowAttack`
    //@ Queste classi semplicemente implementano la loro logica di attacco

    public static void strategyPatternExample() {
        Piglin monster = new Piglin();
        System.out.println("\n--- Round 1: Default Behavior ---");
        monster.fight();
        System.out.println("\n--- Round 2: Picking up a crossbow ---");
        monster.setStrategy(new CrossbowAttack());
        monster.fight();

        //QUIZ: Come possiamo modellare la logica della crossbow che si rompe?
        //@ ---
        //QUIZ: Come facciamo a fare la sword strategy?
        //@ ---
        //QUIZ: Come possiamo modellare un Brute?
        //@ ---
    }


    //@ ## Link Utili
    //@ - [Wikipedia: Composition over inheritance](https://en.wikipedia.org/wiki/Composition_over_inheritance) - A great overview of the principle.
    //@ - [StackOverflow: Why favor composition over inheritance?](https://stackoverflow.com/questions/49002/prefer-composition-over-inheritance) - A classic discussion with real-world examples of why inheritance fails. Very old but still relevant
    //@ - [GameProgrammingPatterns: Component Pattern](https://gameprogrammingpatterns.com/component.html) - Guy writes very nice books. Has one on compilers in Java as well
    //@ - [Wiki: The Fragile Base Class Problem](https://en.wikipedia.org/wiki/Fragile_base_class) - The specific architectural name for the "Rigidity" problem we discussed.
    //@ FIXME: se avete suggerimenti, fateli via pull request

    //@ ## Follow-up
}

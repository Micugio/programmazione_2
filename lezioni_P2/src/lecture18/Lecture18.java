package lecture18;

import lecture18.observer.ObservingPlayer;
import lecture18.observer.v1.Enderman_v1;
import lecture18.observer.v2.mob.Cow;
import lecture18.observer.v2.mob.Enderman;
import lecture18.observer.v2.mob.MobInterface;
import lecture18.observer.v2.ObservingMovingPlayer;
import lecture18.visitor.*;
import lecture18.visitor.visitors.*;

//@ # Lezione 18
public class Lecture18 {
    public static void main(String[] args) {
        //@ BT: Analyze, Evaluate
        System.out.println("---------------- Visitor fatto male ----------------");
        badVisitor();
        //@ BT: Understand, Analyze, Evaluate
        System.out.println("---------------- Visitor Pattern ----------------");
        goodVisitor();
        //@ BT: Understand, Analyze, Evaluate
        System.out.println("---------------- Observer Pattern ----------------");
        observerExample();
    }
    //@ ## Problema, e un visitor fatto male
    //@ Come gestiamo l'interazione con i Mob?
    //@ Clickare su un Mob ha un comportamento diverso: in base al mob, e in base a oggetti che abbiamo
    //@ Il metodo `badVisitor` risolve questa cosa in maniera errata, con una lista di instanceof per capire il tipo degli oggetti, e mantenendo la logica di interazione unita in un solo blocco di codice
    //@ Come facciamo quando aggiungiamo un mob?
    //@ E` lento, perche` fare runtime typechecking aggiunge al costo di runtime
    private static void badVisitor(){
        VisitableEntity entity = new Sheep();
        String holdingItem = "Shears";
        if (entity instanceof Villager) {
            Villager v = (Villager) entity;
            System.out.println("Opening Trading GUI for " + v.getProfession());
        } else if (entity instanceof Sheep) {
            Sheep s = (Sheep) entity;
            if (holdingItem.equals("Shears") && s.hasWool) {
                System.out.println("Sheared the sheep.");
                s.hasWool = false;
            }
        } else if (entity instanceof Creeper) {
            Creeper c = (Creeper) entity;
            if (holdingItem.equals("Flint and Steel")) {
                System.out.println("Ignited the Creeper!");
                c.isIgnited = true;
            }
        }
    }
    //@ ## Il double-dispatch
    //@ Il problema vero e` che Java usa single dispatch: per chiamare un metodo conta solo il tipo dinamico dell'oggetto su cui viene chiamato
    //@ Non conta il tipo dinamico degli argomenti (solo il loro tipo statico)
    //@ La soluzione e` creare il double dispatch:
    //@ - si conta il tipo dinamico dell'oggetto su cui si invoca il metodo
    //@ - si conta il tipo dinamico dei parametri del metodo
    //! Questo pero` non cambia la natura del linguaggio. Java rimane single dipatch. Noi possiamo fare encoding del double dispatch.
    //@ ---
    //! Spesso possiamo estendere un linguaggio con costrutti che ne evitano le limitazioni, ma quando e` un costrutto del programma, e non del linguaggio, ci sono grossi problemi di manutenzione, sicurezza, interazione, composizione.
    //@ ---

    //@ Il Visitor pattern ottiene Double Dispatch concatenando due chiamate di metodi in un ordine specifico
    //@ Il primo si chiama `accept`, il secondo `visit`
    //@ Il primo ragiona sul tipo dinamico dell'oggetto su cui e` chiamato il metodo `accept`
    //@ Il secondo ragiona sul tipo dinamico dell'argomento del metodo `accept`
    //@ Per fare questo, l'argomento del metodo `accept` diventa l'oggetto su cui viene chiamato `visit`
    //@ [Client Code]
    //@ mob.accept(visitor);
    //@   |
    //@   | DISPATCH 1: Java controlla il tipo di Mob, per esempio potrebbe essere una `Sheep`
    //@   V
    //@ [Nella classe `Sheep`]
    //@ public void accept(InteractionVisitor visitor) {
    //@   |
    //@   | DISPATCH 2: Dentro a `Sheep` sappiamo che `this` ha tipo `Sheep
    //@   | Il compilatore sa che va chiamato il metodo `visit(Sheep)`, e questo succede a runtime
    //@   V
    //@   visitor.visit(this);
    //@   |
    //@   V
    //@ [Dentro il visitor corretto, con l'argomento corretto]
    //@ public void visit(Sheep sheep) {
    //@   System.out.println("Shearing the sheep...");

    //@ Le terminologie per i metodi vengono dal libro originale dei Design Patterns
    //@ L'analogia e` quella di invitare un artigiano (Visitor) a fare un lavoro in casa (Element)
    //@ La casa deve `accept` l'artigiano sulla soglia
    //@ Una volta dentro, l'artigiano vede la pianta della casa e la `visit`

    //@ ## Il Visitor Pattern
    //@ Definiamo i passi per definire il pattern Visitor
    //@ In questo caso creeremo uno specifico Visitor per l'interazione tramite oggetto `ItemInteraction`
    //@ Potremmo creare altri Visitor per altre interazioni, come `EmptyHandInteraction`

    //@ #### L'interfaccia per i vari visitor
    //@ Il primo step e` creare l'interfaccia per i visitor `InteractionVisitor`
    //@ Questa interfaccia conosce tutte le classi specifiche su cui puo` venire chiamata
    //@ Infatti ha un metodo `visit` per ogni classe che puo` essere visitabile.

    //@ #### Le classi dei visitor
    //@ Le classi che sono sottotipo di `InteractionVisitor` definiscono i dettagli della logica delle varie azioni
    //@ `ItemInteraction` contiene i metodi per interagire coi `Villager`, con le `Sheep` etc

    //@ #### L'interfaccia visitabile
    //@ Tutto cio` che puo` essere visitato deve implementare una interfaccia 'visitabile', che in questo caso e` `VisitableEntity`
    //@ L'interfaccia definisce un solo contratto: dobbiamo poter fare `accept` di un qualsiasi visitor che realizzi l'interfaccia definita in precedenza

    //@ #### Le classi visitabili
    //@ Queste classi sono `Sheep`, `Creeper` e `Villager`
    //@ La loro implementazione di `accept` e`
    //@ - uguale per tutti
    //@ - standard: fa solo `v.visit(this);`
    //QUIZ: Quale e` il tipo di `this` dentro le varie classi?
    //@ ---
    //@ Questo permette a Java di fissare staticamente il metodo da chiamare, in quanto il tipo di `this` e staticamente noto all'interno di una classe
    //@ Come sempre, le classi risultano particolarmente piccole e pulite
    private static void goodVisitor(){
        VisitableEntity[] mobsInArea = { new Villager(), new Sheep(), new Creeper() };
        InteractionVisitor holdingFlint = new ItemInteraction("Flint and Steel");
        for (VisitableEntity mob : mobsInArea) {
            mob.accept(holdingFlint);
        }
    }

    //@ #### Utilizzi reali del Visitor
    //@ Sebbene le interazioni nei giochi siano un ottimo esempio visivo, questo pattern si trova spesso in sistemi altamente strutturati e con grande mole di dati.
    //@ 1. COMPILATORI & LINTER
    //@  - Questo è il caso d’uso principale per il Visitor Pattern. Quando un compilatore legge il codice, costruisce un albero di oggetti (IfStatement, VariableDeclaration, ForLoop).
    //@  - Invece di inserire la logica di compilazione all’interno di questi nodi, i compilatori usano Visitor: un `TypeCheckVisitor`, un `OptimizationVisitor` e un `MachineCodeVisitor`.
    //@ 2. SERIALIZZAZIONE & ESPORTAZIONE
    //@  - Consideriamo documenti con Testo, Immagini e Tabelle.
    //@  - Si possono creare un `JsonExportVisitor`, un `XmlExportVisitor` o un `PdfExportVisitor`.
    //@  - Si aggiungono nuovi formati di esportazione scrivendo un nuovo Visitor, senza mai modificare le classi principali del documento.
    //@ 3. MOTORI FISICI
    //@  - In un game engine ci sono Sfere, Cubi e Piani, il modo in cui interagiscono dipende da entrambe le forme.
    //@  - Una sfera che colpisce un piano calcola la fisica in modo diverso rispetto a una sfera che colpisce un cubo.

    //@ ## L'observer pattern
    //@ Alcune volte abbiamo un oggetto che esprime un comportamento ed altri oggetti che devono fare cose in risposta
    //@	Per esempio: se guardo un enderman, questo mi viene vicino e mi attacca
    //@ L'observer pattern serve proprio in questo caso
    //@ Vediamone la ricetta.

    //@ #### Le interfacce per i comportamenti
    //@ Per prima cosa creiamo una interfaccia per tutti i tipi di comportamenti iniziali che devono essere notificati ad altre classi: `PlayerGazeObserver`

    //@ #### La collection di oggetti da notificare
    //@ Nella classe `ObservingPlayer`, che esprime il comportamento, tengo una collection degli oggetti che devo notificare
    //@ Inoltre si aggiunge un metodo per registrare tutti gli oggetti che vanno osservati nella collection

    //@ #### Utilizzo: registrazione e notifica
    //@ Quando succede il comportamento, semplicemente notifico tutti quelli della collection
    //@ Questo succede nel metodo `look_around`, dove viene chiamato il medoto dell'interfaccia su tutti gli elementi raggruppati nella collection

    //@	I clients `Enderman` implementano l'interafaccia.
    //@ Il suo metodo fa dei controlli e poi chiama dei metodi interni
    //QUIZ: Chi deve fare il controllo sulla presenza della pumpkin?
    //@ ---

    //
    //@ La logica del comportamento della pumpkin si lascia al client, cioe` a chi scrive l'`Enderman` perche` loro sanno come si comporta un enderman, non il player
    //@ Qui, facciamo spostare l'`Enderman` verso chi lo ha guardato

    //@ Nel metodo `observerExample` si creano il giocatore che osserva e un enderman.
    //@ Dopodiche viene registrato l'enderman dentro al player
    //@ Infine il metodo chiama ripetutamente della logica sul giocatore.
    //@ Ogni stampa dello stato del mondo ci dice che il metodo che fa spostare l'`Enderman` viene effettivamente chiamato
    //@ Questo succede in risposta all'azione sul giocatore `look_around`, che chiama i vari observers
    public static void observerExample(){
        ObservingPlayer p = new ObservingPlayer();
        Enderman_v1 ev1 = new Enderman_v1();
        /*initial snapshot*/        printWorldState(ev1,p);
        p.add_gaze_observer(ev1);
        p.look_around();            printWorldState(ev1,p);
        p.look_around();            printWorldState(ev1,p);
        p.wearPumpkinHead();
        p.look_around();            printWorldState(ev1,p);
        p.removePumpkinHead();
        p.look_around();            printWorldState(ev1,p);
        p.look_around();            printWorldState(ev1,p);
        p.look_around();            printWorldState(ev1,p);

        //@ #### Estensioni
        //@ Come sempre la bonta` dei pattern si evince facendo evolvere il codice nel tempo.
        //@ Possiamo infatti creare nuovi tipi che osservano: `PlayerMovementObserver`
        //@ E delle nuove classi che realizzano questi mob: `Creeper` e `Cow`, senza toccare i vecchi
        //?	E se volessimo fare il creeper che scappa dal gatto?
        //@ ---

        //@ In questo esempio abbiamo anche fattorizzato i comportamenti dei mob in una interfaccia `MobInterface`
        //QUIZ: Che pattern e` quello dentro `Cow`?
        //@ ---

        //
        //@ Il package `behaviours` contiene i comportamenti per i vari mob
        //@ Come prima, creiamo un nuovo tipo di Player, e uno per ogni nuovo mob, e registriamo i mob
        //@ Quando stampiamo lo stato del mondo notiamo che i mob creati si comportano in reazione alle azioni del player, senza che venga chiamato il loro comportamento da qui
        System.out.println();
        Enderman e1 = new Enderman();
        lecture18.observer.v2.mob.Creeper e2 = new lecture18.observer.v2.mob.Creeper();
        Cow e3 = new Cow();
        ObservingMovingPlayer p2 = new ObservingMovingPlayer();
        /*initial snapshot*/            printWorldState(e1,e2,e3,p2);
        p2.add_gaze_observer(e1);
        p2.look_around();               printWorldState(e1,e2,e3,p2);
        p2.wearPumpkinHead();
        p2.look_around();               printWorldState(e1,e2,e3,p2);
        p2.add_movement_observer(e2);
        p2.look_around();               printWorldState(e1,e2,e3,p2);
        p2.removePumpkinHead();
        p2.look_around();               printWorldState(e1,e2,e3,p2);
        p2.look_around();               printWorldState(e1,e2,e3,p2);
        p2.move();                      printWorldState(e1,e2,e3,p2);
        p2.move();                      printWorldState(e1,e2,e3,p2);
        p2.add_movement_observer(e3);
        p2.move();                      printWorldState(e1,e2,e3,p2);
        p2.move();                      printWorldState(e1,e2,e3,p2);
    }

    private static void printWorldState(MobInterface e1, MobInterface e2, MobInterface e3, ObservingPlayer p){
        String s = p.hasPumpkinHead() ? "[] : " : "() : ";
        System.out.println(
                "Enderman : "+ e1.getLocation()+" "+
                        "Creeper : " + e2.getLocation()+" "+
                        "Cow : " + e3.getLocation()+" "+
                        "Player "+s+ p.getLocation()
        );
    }

    private static void printWorldState(Enderman_v1 e1, ObservingPlayer p){
        String s = p.hasPumpkinHead() ? "[] : " : "() : ";
        System.out.println(
                "Enderman : "+ e1.getLocation()+" "+
                        "Player "+s+ p.getLocation()
        );
    }

    //@ ## Link Utili
    //@ -
    //@ FIXME: se avete suggerimenti, fateli via pull request

    //@ ## Follow-up
    //@ -
}
package lecture02;

//@ # Lezione 2

//@ ## Introduzione all'OO

//@ ##### Motivazione
//@ Programming in the large.
//@ Differentemente dal programming in the small, vogliamo creare un progetto come l'unione delle sue sottoparti e seguire l'evoluzione del progetto
//@ Il codice evolve nel tempo (cambio di funzionalita`), e nei manutentori (persone che ci lavorano)
//@ Vogliamo massimizzare riuso del codice: questo permette di minimizzare errori

//@ Per studiare i principi OO usiamo Java, con cenni a come altri linguaggi implementano gli stessi principi.
//@ Java e` semplice ed espressivo.
//@ Java ha degli obiettivi di design: e` fortemente tipato (anche se sballa con gli array), e` modulare, non e` il massimo dell'efficienza (ma spesso questo si lascia a un buon compilatore)

//@ ##### Concetti chiave
//@ Quali sono i concetti fondanti del paradigma OO
//@ - oggetti : hanno uno stato / hanno un comportamento (messaggi / metodi), hanno un id, hanno una locazione in memoria,
//@ - incapsulamento : interfacce / information hiding / implementazioni non visibili
//@ - classificazione (ereditarieta`): gli oggetti appartengono a piu classi
//@ - specializzazione (ereditarieta`) : permette l'aggiunta di stato & comportamento
//@ - polimorfismo : permette ad uno stesso oggetto di essere usato in contesti (di tipo) diversi

//@ ##### Terminologia
//@ In oop, usiamo termini ben definiti
//@ - classe : tipo definito dall'utente. Schema per creare oggetti che condividono comportamento e stessa forma di stato -- memory footprint
//@ - oggetto : istanza di una classe. Ha una nozione di stato che varia da oggetto a oggetto
//@ - metodo : comportamento definito da una classe, invocabile su un oggetto (con parametri)
//@ - funzione : funzionalita` che non si chiama su un oggetto
//@ - campo/variabile d'istanza : parte dello stato di un oggetto
//@ - firma : tipo di input e di output di un metodo (o di una funzione)

//@ ####### Esempi
//@ Un tipico esempio di classe e` lo stampino dei biscotti: tutti i biscotti hanno la stessa forma
//@ Il correlato esempio di oggetto sono biscotti che sono decorati in maniera diversa l'uno dall'altro.
//@ Questi biscotti hanno la stessa forma (classe), ma possono avere caratteristiche diverse.
//
//@ L'esempio che seguiamo in questo corso e` quello di Minecraft.
//@ Tutti i blocchi sono simili in Minecraft, per esempio hanno lo stesso comportamento (puoi scavarli)
//@ Pero` ogni blocco e` diverso. Ogni blocco di terra e` scollegato dagli altri blocchi di terra, e scavarne uno non tocca gli altri.
//@ E ancora di piu`, i blocchi di tipo diverso hanno comportamento diverso.

//@ ##### Idea del corso
//@ In questo corso useremo Minecraft come progetto per capire i principi OO.
//@ Partiremo dalla definizione di alcuni blocchi di Minecraft per capire come strutturare questa nozione in classi, e per capire che i blocchi sono oggetti.
//@ Scriveremo metodi e campi per definire il comportamento di questi oggetti
//@ Applicheremo le nozioni di incapsulamento per prevenire comportamenti non voluti.
//@ Utilizzeremo l'ereditarieta` per modularizzare il progetto e minimizzare la scrittura del codice, e allo stesso tempo permettere comportamenti spefici per blocchi di tipo diverso.
//@ Ci baseremo sul polimorfismo (parametrico e di sottotipo) per favorire il riuso di codice.
//@ Andremo poi ad aggiungere le eccezioni per la gestione degli errori.
//@ Utilizzeremo due framework di Java: collections (per organizzare i blocchi in strutture dati), e javafx (per dotare il nostro progetto di una GUI).
//@ Infine, integreremo diversi design patterns nel progetto per semplificare la gestione del codice, minimizzarne la scrittura e massimizzarne il riuso.

//@ ## Struttura dei file e del main
//@ Siamo dentro al package `lecture02`, una struttura con un significato semantico che vedremo dopo
//@ Per ora considerate i package come un modo per fare ordine all'interno di un grande progetto
//@ Le righe di `import` di sotto le evitiamo, ne parliamo insieme ai package piu` avanti
import lecture02.v_enums.Direction;
import lecture02.v_enums.ToolTier;
import lecture02.v1.TNT;

//@ Tutto in Java e` una classe, non esistono metodi scollegati ad una classe
//@ Per questo dichiariamo una classe `Lecture2`

//@ Di sotto dichiariamo il metodo `main`.
//@ `main` e` un nome speciale, Java sa che puo` cominciare l'esecuzione da questo medoto e quando l'esecuzione parte, il Runtime di Java crea un processo che inizia da qui.
//@ Ci possono essere piu` metodi `main` in uno stesso progetto.
//@ Per ora evitiamo di parlare delle keyword `public` e `static`.
//@ Il metodo `main` ha una firma: `String[] -> void` cioe` prende in input un array di `String` e non restituisce nessun valore (`void`)

public class Lecture2 {
    public static void main(String[] args) {
        //@ BT: Remember & Understand
        System.out.println("---------------- Classi ----------------");
        classExample();
        System.out.println("---------------- Campi ----------------");
        fieldsExample();
        System.out.println("---------------- Costruttori ----------------");
        constructorsExample();
        System.out.println("---------------- Metodi ----------------");
        methodsExample();
        System.out.println("---------------- Enums ----------------");
        enumExample();
        richEnumExample();
    }
    //@ ## Oggetti e Classi --- La classe TNT (dentro v1)
    //@ Java conosce una serie di Tipi Primitivi, alcuni sono forniti dal linguaggio come `int` e `boolean` e `char` e `double` mentre altri sono forniti dalla libreria di base come `String` o `Date`.
    //@ In ogni progetto pero`, dobbiamo modellare un dominio specifico.
    //@ In questo progetto (cioe` in questo corso), il dominio e` quello di Minecraft.
    //@ Quindi dobbiamo poter definire dei tipi nostri, con un comportamento che decidiamo noi

    //@ Questo e` quanto facciamo con la classe `TNT`
    //@ Aggiungiamo il package `v1` e dentro a quella directory aggiungiamo il file `TNT.java`
    //@ Questo file contiene la definizione della classe `TNT`
    //@ Definendo la nostra classe stiamo aggiungendo un nuovo tipo al programma, il tipo `TNT`, che sara` diverso da `Zombie`, `Cobblestone`, `String`, `int`, ...

    //@ ####### Nota: statico VS dinamico
    //@ Il codice ha per sua caratteristica fondante due aspetti: quello statico e quello dinamico.
    //@ Gli aspetti statici del codice catturano quello che e` il codice prima di essere eseguito, quando e` scritto dal programmatore.
    //@ Gli aspetti dinamici del codice catturano quello che e` il codice in esecuzione, cioe` a runtime.
    //@ Questi due aspetti permeano tutte le nozioni di programmazione, ma ancora di piu` in OO.
    //@ Per esempio, classi e oggetti sono aspetti statici e dinamici del programma.
    //@ Il programmatore scrive classi, e a runtime queste vengono istanziate in oggetti.
    //@ Non vi sono oggetti prima del runtime, non si scrivono classi a runtime.

    //@ ##### Allocazione di oggetti
    //@ Questo metodo crea un nuovo oggetto di tipo TNT tramite la keyword `new` e lo salva in una variabile locale `tnt1`.
    //@ Il tipo `TNT` all'inizio della riga definisce il tipo della variabile.
    //@ Possono esistere piu` istanze della stessa classe, cioe` piu` oggetti di quel tipo, per esempio il secondo oggetto viene salvato nella variabile `tnt2`
    private static void classExample() {
        TNT tnt1 = new TNT();
        System.out.println("My first block: " + tnt1);

        TNT tnt2 = new TNT();
    }

    //@ ##### I Campi (nelle classi) e lo stato (degli oggetti)
    //@ Col termine stato identifichiamo i dati contenuti dentro un oggetto.
    //@ Per permettere agli oggetti di avere uno stato, la loro classe deve avere dei campi
    //@ Ogni oggetto ha la propria istanziazione dei campi dichiarati nella classe, e modificare il contenuto dei campi di un oggetto non modifica altri oggetti.
    //@ Questo perche` oggetti diversi occupano aree di memoria diverse.

    //@ ##### La classe TNT (dentro v2)
    //@ La classe `TNT` nel package `v2` estende la precedente con 3 campi
    //@ Ogni campo ha un modificatore di visibilita` (per ora `public`), in tipo ed un nome.

    //@ Il metodo `fieldsExample()` crea due blocchi, poi modifica lo stato di uno, e ne stampa i valori.
    public static void fieldsExample() {
        lecture02.v2.TNT blockA = new lecture02.v2.TNT();
        lecture02.v2.TNT blockB = new lecture02.v2.TNT();

        System.out.println("Igniting Block A...");
        blockA.isIgnited = true;
        System.out.println("Block A Ignited?" + blockA.isIgnited);
        System.out.println("Block B Ignited?" + blockB.isIgnited);
        //QUIZ: cosa viene stampato?
    }

    //@ ##### I costruttori
    //@ I costruttori sono metodi speciali che si usano per inizializzare i campi di un oggetto, si identificano dal nome, che e` uguale a quello della classe, e dall'assenza di un tipo di ritorno, in quanto ritornano una istanza della classe
    //@ La classe `TNT` dentro a `v2` contiene due costruttori.
    //@ Ogni classe puo` contenere quanti costruttori vuole, purche` di firma diversa.
    //@ Il primo inizializza i campi a dei valori, il secondo richiama il primo (via `this()`) e inizializza un campo ad un valore passato.

    //@ ##### Costruttori e valori di default
    //@ In assenza di costruttori, il compilatore crea un costruttore di default, che non prende parametri, e che inizializza ogni campo al suo valore di default.
    //@ Per questo abbiamo potuto creare un v1.TNT anche senza costruttore.
    //@ Ogni tipo ha un valore di default, per es., int ha 0, boolean ha false, e per gli oggetti (e gli array) il valore e` `null`.
    //@ `null` e` una parola chiave che indica l'assenza di un valore vero e proprio

    //@ Il metodo `constructorsExample()` crea due oggetti con il costruttore base, e due con il costruttore che inizializza il campo `explosionPower` a un valore dato in input.
    private static void constructorsExample(){
        lecture02.v2.TNT blockA = new lecture02.v2.TNT();
        lecture02.v2.TNT blockB = new lecture02.v2.TNT();
        System.out.println("blockA e blockB: " +
                "" +blockA.isIgnited+ " "+blockB.isIgnited+ "\n " +
                "" +blockA.fuseLength+ " "+blockB.fuseLength+ "\n " +
                "" +blockA.explosionPower+ " "+blockB.explosionPower );

        lecture02.v2.TNT blockC = new lecture02.v2.TNT(10);
        lecture02.v2.TNT blockD = new lecture02.v2.TNT(20);
        System.out.println("blockC e blockD: " +
                "" +blockC.isIgnited+ " "+blockD.isIgnited+ "\n " +
                "" +blockC.fuseLength+ " "+blockD.fuseLength+ "\n " +
                "" +blockC.explosionPower+ " "+blockD.explosionPower );

        TNT blockE;
        //QUIZ: che valore contiene la variabile `blockE`?
    }


    //@ ##### La parola chiave `this`
    //@ La keyword `this` si usa per disambiguare e far riferimento allo stato dell'oggetto sul quale si opera.
    //@ All'interno dei costruttori la si usa per identificare lo stato dell'oggetto che si sta creando.
    //@ Nei metodi lo si usa per identificare l'oggetto su cui e` chiamato un metodo.
    //@ Vediamo ora i Metodi

    //@ ##### Metodi e comportamento
    //@ I Metodi definiscono cosa possono fare gli oggetti, cioe` il loro comportamento
    //@ I metodi sono simili alle funzioni: hanno un nome, una lista di parametri (tipati) ed un tipo di ritorno.
    //@ In sostanza, hanno una firma.
    //@ Differentemente dalle funzioni, i metodi si chiamano su oggetti, pertanto al loro interno possono usare la keyword `this` per riferirsi allo stato dell'oggetto su cui sono invocati.
    //@ Un metodo appartiene alla classe che lo definisce, quindi lo si puo` chiamare solo su oggetti di quella classe.
    //@ Per invocare un metodo si usa la 'dot notation' cioe`:  `oggetto.metodo(parametri ...)`.
    //@ L'invocazione di un metodo e l'accesso a un campo si differenziano sintatticamente per la presenza di parentesi (e degli eventuali parametri nel caso di un metodo)

    //@ I metodi permettono di raggruppare un certo tipo di logica coerentemente.
    //@ Inoltre lasciano la definizione del comportamento all'implementatore, e non all'utilizzatore

    //@ ####### Nota: chiamante e chiamato
    //@ E` importante capire due visioni dell'esecuzione del codice: quella del chiamante e quella del chiamato.
    //@ Per esempio, il metodo `ignite` di `TNT` dentro a `v3` controlla se una `TNT` e` gia` innescata, e se non lo e` la innesca.
    //@ Questa logica e` giusto che rimanga dentro al metodo, e quindi dentro alla classe `TNT`, perche` e` chi definisce `TNT` che sa cosa bisogna fare quando la si innesca.
    //@ Questa logica e` nel codice 'chiamato'.
    //@ Non avrebbe senso mettere questa logica dentro al metodo `methodsExample`.
    //@ Innanzitutto perche` non e` detto che chi scrive `methodsExample` sappia cosa controllare quando si innesca una TNT.
    //@ Inoltre, se ci fossero 100 oggetti di tipo TNT, non vorremmo mettere 100 controlli dentro a `methodsExample`

    //@ ##### La classe `TNT` (v3)
    //@ La classe `TNT` dentro al package `v3` estende la precedente con 3 metodi.
    //@ Per convenienza abbiamo eliminato i costruttori.

    //@ Il metodo `methodsExample()` crea una `TNT`, la innesca, poi simula 20 tick di gioco per farla scoppiare
    private static void methodsExample() {
        lecture02.v3.TNT tnt = new lecture02.v3.TNT();

        System.out.println(">> Sending ignite signal...");

        tnt.ignite();

        for (int i = 0; i < 20; i++) {
            tnt.tick();
        }
        //? Esercizio: provate a chiamare `ignite()` piu` volte sullo stesso oggetto

        //@ ---

        //? Esercizio: provate a creare piu` oggetti con fusi di lunghezza diversa e a chiamare `tick` su tutti.
    }

    //@ ## Enums
    //@ Enum e` una keyword che sta per 'enumerazione'
    //@ Ogni enum ha delle varianti, che si scrivono in `UPPER_CASE` per convenzione
    //@ Le enums servono per evitare l'antipattern dei Magic Numbers
    //@ In linguaggi vecchi, spesso si usavano `int` per elencare opzioni:
    //@ 0=North, 1=East, 2=South, 3=West
    //@ Questo e` pericoloso, per esempio se uno scrive `5`, il compilatore non avvisa che si e` inserita una direzione inesistente, ma questo lo scoprirete solo a runtime, cioe`, avrete dei bug
    //@ Con le enums, si spostano questo tipo di errori e controlli a compile time, cioe` staticamente
    //@ Non si puo` inserire un valore che non faccia parte delle Varianti di una Enum

    //@ ##### La Enum `Direction`
    //@ E` una enum standard, elenca diverse varianti per le direzioni di un gioco

    //@ Il metodo `enumExample()` fa vedere l'utilizzo errato di magic numbers, e quello corretto di enums
    public static void enumExample() {
        System.out.println("=== 1. THE BAD WAY (MAGIC NUMBERS) ===");
        //@ problema 1: leggibilita`. Cosa significa 0?
        movePlayer(0);
        //@ problema 2: sicurezza. Cosa succede?
        movePlayer(99);

        System.out.println("\n=== 2. THE GOOD WAY (ENUMS) ===");
        movePlayerEnum(Direction.NORTH);
        //QUIZ: posso decommentare questa riga?
        //@ ---
//         movePlayerEnum(99);
    }

    //@ Il metodo `movePlayer()` e` 'cattivo': usa magic numbers
    private static void movePlayer(int direction) {
        if (direction == 0) {
            System.out.println("Moving NORTH");
        } else if (direction == 1) {
            System.out.println("Moving EAST");
        } else {
            System.out.println("Unknown direction! (Bug)");
        }
    }

    //@ Il metodo `movePlayerEnum()` e` 'buono': usa le enums
    //@ Le enums si analizzano tramite switch
    //@ Ci sono 2 modi, uno switch statement e una switch expression
    //QUIZ: Differenza tra statement ed expressions?
    //@ ---
    //@ Le switch expressions devono essere esaustive, volendo con un default case
    private static void movePlayerEnum(Direction dir) {
        String dirName = switch (dir) {
            case NORTH -> "NORTH";
            case EAST -> "EAST";
            default -> "UP";
        };
        System.out.println("Moving" + dirName);
    }

    //@ ##### La enum `ToolTier`
    //@ Le enums sono classi vere e proprie, possono avere campi, metodi e costruttori
    //@ Ogni variante (es, WOOD o DIAMOND) definisce un oggetto di cui esiste una sola istanza
    //@ Il costruttore delle enums e` privato, esternamente si crea una variante, e questo chiama il costruttore come definito dentro all'enum
    //@ Questo e` per evitare che si possano aggiungere varianti all'enum

    //@ Le enums possono contenere campi e metodi
    //@ Questo evita di scrivere metodi di lookup come questo
    //@ ```if (tool == "Wood") speed = 2.0; if (tool == "Gold") speed = 12.0;```
    //@ Questo codice e` terribile e separa i dati di Gold.
    //@ Le enums permettono di unire tutti i dati e la logica delle varianti in un solo luogo
    public static void richEnumExample() {
        ToolTier myTier = ToolTier.GOLD;
        ToolTier myTier2 = ToolTier.GOLD;
        System.out.println(""+ myTier.getEfficiency() + " " + myTier2.getEfficiency());

        myTier2.efficiency = 0;
        System.out.println(""+ myTier.getEfficiency() + " " + myTier2.getEfficiency());
        //QUIZ: cosa viene stampato?
    }


    //@ ## Link Utili
    //@ - null, il billion-dollar mistake: https://news.ycombinator.com/item?id=12427069
    //@ -
    //@ FIXME: se avete suggerimenti, fateli via pull request

    //@ ## Follow-up
    //@ ##### Classi in altri linguaggi
    // FIXME

    //@ ##### Nominal VS structural typing
    // FIXME

}

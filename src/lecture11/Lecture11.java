package lecture11;

import lecture05.inheritance.*;
import lecture10.arguments.*;
import lecture11.bad.*;
import lecture11.templateMethod.AbstractBlock;
import lecture11.templateMethod.DiamondOre;
import lecture11.templateMethod.GlassBlock;

//@ # Lezione 11
public class Lecture11 {
    public static void main(String[] args) {
        //@ BT: Analyse
        System.out.println("---------------- Object Memory Layout: la v-table ----------------");
        vtableTest();
        //@ BT: Understand, Analyse
        System.out.println("---------------- Instanceof e casting ----------------");
        castingAndInstanceof();
        runBadInstanceExample();
        //@ BT: Understand, Evaluate
        System.out.println("---------------- Il pattern Template Method ----------------");
        runTemplateExample();
    }

    //@ ## The v-table
    //@ Dalla lezione 4 abbiamo lasciato indietro il concetto di v-table mentre disegnavamo il layout degli oggetti e delle classi in memoria
    //@ Nel metodo `vtableTest` andiamo a riempire questo vuoto
    //@ Disegnamo il layout delle classi `Miner`, `Pick` e `DiamondPick`
    //@ ---
    //@ | 0x0000A0 | mine(Pick p) |
    //@ | :---: | :--- |
    //@ | 0x0000B0 | mine(DiamondPick p) |
    //@ | 0x0000C0 | mineWithSubcall(Pick p) |
    //@ | 0x0000D0 | mineWithSubcall(DiamondPick p) |
    //@ ---
    //@ | 0x000100 | pick() |
    //@ | :---: | :---: |
    //@ ---
    //@ | 0x000A00 | pick() |
    //@ | :---: | :---: |
    //@ ---
    //@ Questi layout sono le v-table di ogni classe.
    //@ Supponiamo che l'indirizzo a cui ogni tabella e`, sia rispettivamente
    //@ - `0x010000`
    //@ - `0x0A0000`
    //@ - `0x0B0000`

    //@ Queste classi non hanno campi!
    //@ Il layout degli oggetti `m`, `pp` e `pd`, quindi e` il seguente:

    //@ ---
    //@ | 0x010000 |
    //@ | :---: |
    //@ ---
    //@ | 0x0A0000 |
    //@ | :---: |
    //@ ---
    //@ | 0x0B0000 |
    //@ | :---: |
    //@ ---

    //@ Ora simuliamo l'esecuzione dei metodi di `Miner`
    //@ Le domande che ci dobbiamo chiedere sono:
    //@ - Quale e` il tipo statico dell'oggetto su cui chiamiamo un metodo?
    //@ Con questa capiamo che metodi possiamo considerare vengano eseguiti
    //@ - Quale e` il tipo statico degli argomenti del metodo?
    //@ Con questa capiamo come disambiguare i casi di overloading
    //@ - Quale e` il tipo dinamico dell'oggetto su cui chiamiamo un metodo?
    //@ Con questa capiamo quale metodo viene invocato (seguendo la v-table)
    private static void vtableTest() {
        Miner m = new Miner();
        Pick pp = new Pick();
        Pick pd = new DiamondPick();
        DiamondPick dd = new DiamondPick();

        m.mine(pp);
        m.mine(pd);
        m.mineWithSubcall(pp);
        m.mineWithSubcall(pd);
    }
    //? Disegnate il layout di `Block` e `DiamondBlock` dalla lezione 10, esercitatevi sul dispatch per oggetti di quelle classi come nel metodo `dynamicTypeLookupExample()`

    //@ ## Instanceof e casting

    //@ Ci sono casi in cui non sappiamo precisamente il tipo dell'oggetto che riceviamo, potremmo sapere che l'oggetto e` un `Entity` o un `Object`, come tipo statico.
    //@ Questa informazione pero` non basta per poter invocare i metodi corretti che ci aspettiamo su un tale oggetto, in base al suo tipo dinamico
    //@ Per farlo, dovremmo usare un cast, cioe` una coercizione statica di tipo, che convince il compilatore che il tipo (statico) di un oggetto e` quello definito nel cast
    //@ Il problema e` che il cast e` un costrutto fallibile: il compilatore si fida (e altro non puo` fare) che il cast sia corretto.
    //@ Se il cast non e` corretto, il programma scoppia a runtime.
    //@ Per prevenire questo errore, il linguaggio ci fornisce una primitiva: `instanceof` che serve per controllare il tipo dinamico di un oggetto a runtime
    //! Spoiler: non vogliamo praticamente mai usare ne casting ne instanceof
    //@ ---
    //@ Avere degli if-then-else annidati con `instanceof` e cast e` una indicazione di pessima programmazione e di mancato utilizzo dei principi OO
    //@ Se usate overriding e polimorfismo (tramite dynamic dispatch) non avete bisogno di `instanceof`

    //@ Per capire il problema in atto, vediamo `castingAndInstanceof`.
    //@ Qui, creiamo un `genericMob1` con tipo statico `Entity` ma tipo dinamico `Creeper`
    //@ Questo e` un esempio pedagogico, pensate se fosse un parametro preso in input, non avremmo controllo sui tipi qui!
    //@ Il codice prima chiama `instanceof`, e solo in caso ritorni `true`, fa il cast, che a questo punto e` sicuro di non fallire
    //@ Inoltre il cast ci cambia il tipo statico, quini possiamo chiamare `hiss()`.
    private static void castingAndInstanceof() {
        Entity genericMob1 = new Creeper(5);
        if (genericMob1 instanceof Creeper) {
            Creeper c = (Creeper) genericMob1;
            System.out.println("Watch out!");
            c.hiss();
        }
    }
    //@ Questo e` da evitare.
    //@ Questo e sue varianti, tipo usare il `getClass()`, oppure mettere un campo che contiene un encoding del tipo (es: un magic number o una enum)
    //@ Vedremo che l'unico caso in cui possiamo e dovremmo usare modi per ottenere informazioni di tipo dinamicamente e` dentro il metodo equals, ma ne parliamo con le collections

    //@ Vediamo ora un esempio di come potreste voler usare `instanceof`, in maniera completamente errata, e successivamente vediamo come fixare questo esempio usando il Template Method design pattern
    //@ Supponiamo di voler implementare la logica di distruzione di un blocco:
    //@ 1. animazione delle particelle (tutti i blocchi)
    //@ 2. suono (comune per molti blocchi)
    //@ 3. loot (specifico per ogni blocco)

    //@ Vediamo il metodo `runBadInstanceExample`, che crea due `BadBlock` e vi chiama un metodo sopra.
    //@ Il metodo `destroyBlock` dentro a `BadBlock` fa casting e instanceof, contiene tutta la logica, e per farlo deve conoscere tutte le sue sottoclassi
    //@ La soluzione di `destroyBlock` e` pessima e prevede la scrittura, all'interno del metodo chiamante, di un nested `instanceof` che controlla il tipo di blocco, e in base a cosa ritorna `true`, fa diverse cose
    //QUIZ: Perche` non va bene?
    //@ ---
    //@ Alcuni principi che vengono violati da questa soluzione:
    //@ - open/closed: se aggiungiamo un blocco (es, `Wood`) dobbiamo modificare questo metodo. Ma le due cose sono scorrelate, logicamente.
    //@ - fragile: se ci dimentichiamo un `else if`, non aggiungiamo il comportamento del nuovo blocco
    //@ - lento: fare dynamic typecheck e` piu` lento che fare dynamic dispatch
    private static void runBadInstanceExample() {
        BadBlock d = new BadDiamond();
        BadBlock g = new BadGlass();
        d.destroyBlock();
        g.destroyBlock();
    }

    //@ ## Il pattern Template Method
    //@ Questo pattern si usa per risolvere il problema appena visto
    //@ Piu` in generale, possiamo pensare al problema di avere molte classi che concettualmente fanno la stessa sequenza di passi, ma ognuna potrebbe fare questi passi in maniera diversa
    //@ Una analogia che si usa spesso per il Template Method e` quella della ricetta da cucina
    //@ Lo scheletro dell'algoritmo viene scritto nella classe parent, e viene segnato come `final`
    //QUIZ: Perche` viene segnato `final`?
    //@ ---
    //@ Vengono poi creati metodi astratti per i passi dell'algoritmo
    //@ Lo scheletro chiama i metodi astratti
    //@ Le sottoclassi devono implementare i metodi astratti, ma non possono fare overriding dello scheletro final
    //@ Quindi gli oggetti delle sottoclassi finiranno per eseguire i passi dello scheletro, ma contestualizzati con le loro implementazioni dei metodi abstract
    //@ Nel metodo `runTemplateExample` vediamo il problema del break+loot precedente risolto col template method
    //@ Consideriamo la classe `AbstractBlock`, che e` parent di `DiamondOre` e di `GlassBlock`
    //@ Questa classe implementa il template method dentro a `destroyBlock()`

    //@ Il metodo `spawnParticles()` rappresenta uno step comune a tutti i blocchi.
    //@ Potremmo lasciarlo abstract, ma il modo in cui saltano via i pezzi e` uguale per tutti i blocchi, quindi va bene qui
    //QUIZ: Quali modificatori ha senso dare ad un tale metodo?
    //@ ---
    //@ Il metodo `playBreakSound()` definisce una implementazione base, ma che puo` venire sovrascritta dalle sottoclassi
    //@ Per esempio, la classe `GlassBlock` lo fa
    //@ Il metodo `dropLoot` invece va lasciato abstract: ogni classe figlia deve istanziare questa logica

    //@ Nelle classi `DiamondOre` e `GlassBlock`, dobbiamo solo implementare i metodi abstract
    //@ Dal punto di vista dell'implementatore, sappiamo che non possono venire bypassati gli invarianti che noi settiamo
    //@ Inoltre, noi che implementiamo il block sappiamo come si comporta, il nostro user (o caller) non lo sa
    private static void runTemplateExample() {
        AbstractBlock diamondOre = new DiamondOre();
        AbstractBlock glass = new GlassBlock();

        diamondOre.destroyBlock();
        glass.destroyBlock();
    }

    //@ Proviamo ad aggiungere blocchi e a variare il nostro Template Method
    //@ Consideriamo il legno:
    //@ - ha un suono diverso dai blocchi normali
    //@ - ha un suono tutto uguale per tutti i legni
    //@ - ha una loot specifica per ogni tipo di legno

    //! Esercizio: Create le classi per aggiungere `OakBlock`, in modo che sia estendibile all'aggiunta di `CherryBlock`.
    //@ ---

    //? La classe `AbstractWoodBlock` puo` non essere `abstract`?
    //@ ---

    //! Aggiungete la classe `CherryBlock`

    //@ ## Link Utili
    //@ -
    //@ FIXME: se avete suggerimenti, fateli via pull request

    //@ ## Follow-up
    //@ -
}
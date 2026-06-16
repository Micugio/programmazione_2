package lecture10;

import lecture10.arguments.*;
import lecture10.bindings.*;

//@ # Lezione 10
//! Questa e` una delle lezioni piu` importanti del corso
//@ ---
public class Lecture10 {
    public static void main(String[] args) {
        //@ BT: Remember, Understand, Analyse
        System.out.println("---------------- Static VS Dynamic binding ----------------");
        predictBindingExample();
        dynamicTypeLookupExample();
        staticTypeLookupExample();
        suggestionsExample();
        varExample();
        bindingRecapExample();
        bindingExamples();
        //@ BT: Understand, Analyse
        System.out.println("---------------- Binding e tipo degli argomenti ----------------");
        argBindingExample();
        argBindingAndSubcallExample();
    }
    //@ ## Static vs Dynamic Binding
    //@ Consideriamo (una parte) la classe `Block`, e il metodo `onBreak()`.
    //@ La classe `Diamond` estende `Block` e fa overriding di quel metodo.

    //@ Nel metodo `predictBindingExample`, la prima stampa e` chiara: invoca il metodo `onBreak` di `Block`
    //@ La seconda e la terza, invece, potrebbero essere meno chiare.
    private static void predictBindingExample() {
        Block bb = new Block("dirt");
        bb.onBreak();

        Block bd = new DiamondBlock();
        bd.onBreak();
        //QUIZ: Cosa viene stampato di sopra?
        //@ ---

        DiamondBlock dd = new DiamondBlock();
        dd.onBreak();
        //QUIZ: Cosa viene stampato di sopra?
        //@ ---
    }

    //@ Intuitivamente, `obj.method()` invoca il metodo `method` sull'oggetto `obj`
    //@ Per capire quale codice viene eseguito a questa invocazione, Java ha due meccanismi
    //@
    //@ 1. 'dynamic binding` o `dynamic dispatch' o 'late binding'.
    //@  In assenza di altre keyword, Java usa questo meccanismo.
    //@  Sostanzialmente, per decidere quale metodo invocare, Java guarda il tipo effettivo (cioe` a runtime) di un oggetto, e non il tipo indicato staticamente
    //@ 2. 'static binding' o 'static dispatch' o 'early binding'.
    //@  Con le keyword `final`, `static` e `private`, Java non ha bisogno di guardare il tipo di un oggetto a runtime e sa gia` che metodo invocare

    //@ Per capire questi meccanismi abbiamo bisogno di introdurre le nozioni di 'tipo statico' e 'tipo dinamico' di una variabile.

    //@ #### Tipi statici e dinamici
    //@ Quando definiamo una variabile: `Block bd = new DiamondBlock();`, essa ha due tipi:
    //@ `Block` e` il tipo statico. E` il tipo che il compilatore considera per `b`.
    //@ `DiamondBlock` e` il tipo dinamico. E` il tipo che `b` ha in memoria.
    //! Se vogliamo capire che comportamento e` possibile invocare su `b`, dobbiamo guardare il comportamento offerto dal suo tipo statico
    //@ ---
    //! Se vogliamo capire quale comportamento viene eseguito su `b`, dobbiamo guardare il comportamento offerto dal suo tipo dinamico
    //@ ---
    //@ Notate la differenza tra 'e` possibile invocare' e 'viene eseguito'.

    //@ Nota: questa e` una differenza fondamentale nel codice: c'e` un aspetto statico e un aspetto dinamico.
    //@ L'aspetto statico ha a che fare con quello che possiamo definire prima di eseguire, cioe` a compile time. Tipicamente e` il compilatore che fa queste analisi.
    //@ L'aspetto dinamico invece modella quello che succede quando il programma viene eseguito.

    //@ Consideriamo il codice di `predictStaticBindingExample`:
    //QUIZ: Quale e` il tipo statico di `bd`?
    //@ ---
    //QUIZ: Quale e` il tipo dinamico di `bd`?
    //@ ---
    //QUIZ: Quale e` il tipo statico di `dd`?
    //@ ---
    //QUIZ: Quale e` il tipo dinamico di `dd`?
    //@ ---

    private static void suggestionsExample() {
        Block bd = new DiamondBlock();
        DiamondBlock dd = new DiamondBlock();
        //QUIZ: Posso decommentare queste righe?
        //@ ---
        //dd.getDiamond();
        //bd.getDiamond();
    }
    //@ Guardiamo i suggerimenti dell'ide sulle variabili `bd` e `dd`.
    //@ Per quanto `bd` sia di tipo `DiamondBlock`, non possiamo invocare il metodo `getDiamond` definito in `DiamondBlock`
    //@ Questo perche` il compilatore ragiona solo in termine di tipo statico: `bd` e` staticamente un `Block` e quindi offre i suoi comportamenti
    //@ A runtime, pero`, quando invochiamo `okBreak()`, il runtime ragiona in base al tipo dinamico di `bd`, che` e` un `DiamondBlock`.
    //@ Pertanto, viene eseguito il metodo `onBreak()` dentro `DiamondBlock`

    //@ #### La keyword `var`
    //@ In Java ora si puo` usare la keyword `var` per definire una variabile locale, come nel metodo `varExample()`
    private static void varExample() {
        var b = new Block("dirt");
        var d = new DiamondBlock();

        //QUIZ: Cosa stampano?
        //@ ---
        b.onBreak();
        d.onBreak();
    }
    //@ In questo caso il tipo statico viene inizializzato al tipo presente dopo l' `=`

    //@ #### Dynamic dispatch
    //@ Il dynamic dispatch e` il meccanismo standard con il quale vengono eseguiti i metodi in Java
    //@ Il compilatore genera delle istruzioni per ricercare il metodo da eseguire in base al tipo dinamico di un oggetto: vedremo i dettagli nella lezione 11
    //@ Il dynamic dispatch e` fondamentale in OO: il tipo statico ci dice come comportarci, ma solo guardando il tipo dinamico possiamo sapere cosa succedera`
    //@ Guardare il tipo dinamico, pero`, non sempre e` possibile.
    //@ Considerate `dynamicTypeLookupExample()`, che invoca `breakBlock()` su un `Block` e su un `DiamondBlock`.
    //@ Il metodo `breakBlock()` ha bisogno solo di un `Block` per funzionare, e questo definisce come tipo del suo parametro.
    //@ Chi scrive `breakBlock()` potrebbe non conoscere tutti i tipi che possono essere passati al metodo, ma questo va bene: questo metodo sa che ha bisogno solo del fatto che l'oggetto 'is-a' `Block` per funzionare
    //! Questo e` il principio di 'least privilege': viene richiesto il minimo delle funzionalita` per svolgere il proprio compito
    //@ ---
    //@ Questo e` simile a quanto detto in `caller_calleeExample` nella Lezione 7

    private static void dynamicTypeLookupExample() {
        breakBlock(new Block("dirt"));
        breakBlock(new DiamondBlock());
    }
    private static void breakBlock(Block b) {
        b.onBreak();
    }

    //@ #### Static binding
    //@ Considerate il metodo `getRegistryName()` in `Block`
    //@ Questo metodo viene chiamato dentro `staticTypeLookupExample()` su tre oggetti con tipo statico e dinamico diversi
    //QUIZ: Cosa vi aspettate venga eseguito con `bd.getRegistryName()`?
    //@ ---
    private static void staticTypeLookupExample() {
        Block bb = new Block("dirt");
        bb.getRegistryName();

        Block bd = new DiamondBlock();
        bd.getRegistryName();

        DiamondBlock dd = new DiamondBlock();
        dd.getRegistryName();
    }

    //@ Il metodo `getRegistryName()` ha una keyword che `onBreak()` non ha: `final`
    //@ Questa keyword fa si che il metodo non sia overrid-abile nelle sottoclassi di `Block`.
    //@ Pertanto, non dobbiamo chiederci quale sia il tipo dinamico di un oggetto per sapere quale `getRegistryName()` eseguire: e` e sara` sempre quello dentro a `Block`s

    //@ Notate, ci sono altre due keyword che forzano Java a fare static dispatch:
    //@ - un metodo `static` viene eseguito con static dispatch per definizione: non appartiene a un oggetto, ma ad una classe, e una classe ha un solo tipo
    //@ - un metodo `private` non puo` venire overrid-ato da una sottoclasse perche` quest'ultima non lo vede, quindi il dispatch viene fatto staticamente

    //QUIZ: Cosa e` piu` efficiente?
    //@ ---

    private static void bindingRecapExample() {
        DiamondBlock dd = new DiamondBlock();
        dd.onBreak();
        //QUIZ: Cosa viene chiamato?
        //@ ---
        dd.getRegistryName();
        //QUIZ: Cosa viene chiamato?
        //@ ---
    }

    //! Nota: lo strategy pattern si basa proprio su questi meccanismi, e` un esempio dove NON vogliamo static binding.
    //@ La decisione della strategia va lasciata a runtime, in base al tipo dinamico dell'oggetto

    //@ #### Ulteriori esercizi sul binding
    //! Ci sono tanti esercizi di dynamic dispatch nell'esame.
    //@ ---
    private static void bindingExamples() {
        class Shield {
            final void block() {
                System.out.println("Blocked");
            }
        }
        Shield s = new Shield();
        s.block();
        //QUIZ: Questo e` static o dynamic binding?
        //@ ---

        class Monster {
            public void growl() {
                System.out.println("Monster growls");
            }
        }
        class Zombie extends Monster {
            public void growl() {
            }
        }
        Monster m = new Zombie();
        m.growl();
        //QUIZ: Questo e` static o dynamic binding?
        //@ ---

        Math.abs(10);
        //QUIZ: Questo e` static o dynamic binding?
        //@ ---

        class Potion {
            private void brew() { }
            public Potion() {
                //QUIZ: Questo e` static o dynamic binding?
                //@ ---
                this.brew();
            }
        }
    }

    //@ ## Binding e gli argomenti
    //@ Il tipo degli argomenti puo` confonderci quando cerchiamo di capire su quale metodo verra` fatto dynamic dispatch
    //@ Consideriamo la classe `Miner`, che contiene due metodi in overloading:
    //@ - `mine(Pickaxe p)`
    //@ - `mine(DiamondPickaxe p)`
    //@ Nel metodo `argBindingExample`, cosa viene chiamato?

    private static void argBindingExample() {
        Miner steve = new Miner();

        DiamondPick realDiamond = new DiamondPick();
        steve.mine(realDiamond);
        //QUIZ: Cosa viene chiamato qui?
        //@ ---

        Pick hiddenDiamond = new DiamondPick();
        steve.mine(hiddenDiamond);
        //QUIZ: Cosa viene chiamato qui?
        //@ ---

    }
    //@ Il compilatore deve capire quale metodo va chiamato
    //@ A runtime, poi, verra` chiamato il metodo scelto dal compilatore.
    //@ Per fare questa scelta, il compilatore cerca (tra tutti i metodi offerti) il metodo con la firma piu` consona in base agli argomenti passati.
    //@ Visto che la decisione e` fatta dal compilatore, questa decisione e` statica, quindi si considera solo il tipo statico.
    //@ Per il compilatore, `hiddenDiamond` ha tipo statico `Pickaxe`, quindi decidera` che va chiamato il metodo che vuole un `Pickaxe`, anche se il tipo dinamico dell'oggetto e` `DiamondPickaxe`
    //QUIZ: Quale feature del linguaggio genera questo problema?

    //! Attenzione: il tipo dinamico dell'oggetto e` sempre `DiamondPickaxe`
    //@ ---
    //@ Se dentro `mine` viene chiamato un metodo sull'argomento, di questo metodo verra fatto dispatch seguendo il tipo dinamico
    //@ Vi invitiamo a ragionare su tipo statico e dinamico, e su oggetto e parametro

    //! Il tipo statico dell'oggetto su cui e` chiamato il metodo determina quali metodi si possono chiamare
    //! Il tipo statico dei parametri attuali con cui e` chiamato il metodo servono per determinare ulteriormente il metodo da chiamare in caso di overloading
    //! Il tipo dinamico dell'oggetto su cui e` chiamato il metodo determina quale metodo viene eseguito a runtime
    //@ ---

    //@ Consideriamo i metodi `mineWithSubcall` di `Miner` e i relativi metodi `pick` di `Pick` e `DiamondPick`
    private static void argBindingAndSubcallExample() {
        Miner steve = new Miner();
        Pick pp = new Pick();
        Pick pd = new DiamondPick();

        //QUIZ: Quale `mineWithSubcall` viene chiamato? Quale `pick`?
        //@ ---
        steve.mineWithSubcall(pp);
        steve.mineWithSubcall(pd);

        //QUIZ: Come faccio a chiamare `mineWithSubcall` di `DiamondPick`?
        //@ ---
    }


    //@ ## Link Utili
    //@ -
    //@ FIXME: se avete suggerimenti, fateli via pull request

    //@ ## Follow-up
    //@ - Come interagisce il tipo degli argomenti e lo static binding? Fatevi esempi.
    //@ -
}

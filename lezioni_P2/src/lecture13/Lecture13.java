package lecture13;

import lecture13.exceptions.*;
import lecture13.tables.CraftingTable;
import lecture13.tables.EnchantingTable;

import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

//@ # Lezione 13
public class Lecture13 {
    public static void main(String[] args) {
        //@ BT: Remember, Understand, Analyse
        System.out.println("---------------- Eccezioni ----------------");
        exceptionsExample();
        exceptionsAndConstructors();
        deepenStackExample();
        //@ BT: Understand, Analyse
        System.out.println("---------------- Runtime exceptions ----------------");
        runtimeExceptionsExample();
        //@ BT: Understand, Analyse
        System.out.println("---------------- Eccezioni e subtyping ----------------");
        exceptionHierarchyExample();
        System.out.println("---------------- Resources ----------------");
        tryWithResourcesExample();
    }
    //@ ## Eccezioni
    //@ In linguaggi piu` vecchi, si utilizzavano codici di errore, per esempio una funzione in C puo` ritornare -1, e questo sappiamo essere un caso di errore.
    //@ Questo approccio ha un sacco di problemi:
    //@ 1. Come possiamo differenziare errori diversi?
    //@ 2. E se -1 fosse un valore possibile della funzione? Come si mappano gli errori?
    //@ 3. Come facciamo a propagare l'errore tra le diverse chiamate?
    //@ 4. Come separiamo la logica di gestione degli errori dalla logica di business?

    //@ ---
    //@ Le eccezioni sono una soluzione alla gestione degli errori che non prevede l'utilizzo di codici di errore.
    //@ Invece, il fatto che un programma possa sollevare un errore viene portato ad un livello semantico.
    //@ Non e` piu` sintattico: -1 == errore, ma e` semantico: il linguaggio ha le eccezioni, e quelle rappresentano gli errori.
    //@ Altri linguaggi non usano eccezioni, ma lasciano la gestione degli errori a livello di tipo: questo e` ancora buono perche` il livello e` sempre semantico.

    //@ Gestire le eccezioni a livello semantico permette di rispondere al punto 1: errori diversi sono eccezioni diverse
    //@ Inoltre, permettono anche di rispondere al punto 2: le eccezioni non si mischiano coi valori di ritorno.
    //@ Le eccezioni hanno un costrutto sintattico che differenzia la business logic dalla error logic, permettendoci di rispondere al punto 4.
    //@ Infine, vedremo che le eccezioni hanno una semantica che attraversa tutto il call stack, permettendo di propagare le eccezioni tra diverse chiamate a funzione.

    //@ Le eccezioni vengono definite come Classi, ma sono classi speciali, esse devono estendere `Exception`.
    //@ Le firme dei metodi si ampliano: esse contemplano anche una lista di eccezioni.
    //@ Quando invochiamo un metodo dobbiamo fornire input, ci aspettiamo un output, e dobbiamo poter gestire tutti i casi di errore che il metodo elenca come sue eccezioni.
    //@ Nel codice che invoca un metodo che puo` sollevare eccezioni, abbiamo l'obbligo di gestirle.
    //@ Questo obbligo si traduce in due modi:
    //@ - nei nuovi costrutti sintattici del try-catch
    //@ - nell'obbligo di propagare la possibilita` di errore nella firma del metodo che contiene il codice invocante

    //@ #### Definire le Eccezioni
    //@ Supponiamo di dover scrivere la classe `CraftingTable`, con i metodi per creare i blocchi.
    //@ Oltre al metodo legacy, contiene anche un metodo `craftAdvanced` la cui signature definisce che puo` sollevare una eccezione di tipo `CraftingException`.
    //@ Mentre definiamo la logica di business del metodo `craftAdvanced`, vediamo che questo puo` sollevare diversi errori.
    //@ Per esempio, potremmo non avere la ricetta per creare un blocco, oppure potremmo crearlo, ma avere l'inventario pieno.
    //@ Questi errori vengono codificati in eccezioni.
    //@ Essendo le eccezioni delle classi, gli possiamo dare un parent.
    //@ Inoltre, perche` siano eccezioni, devono ereditare ad `Exception`.
    //@ In questo caso, generiamo prima una eccezione generale di tipo `CraftingException`, che estende `Exception`
    //@ Poi creiamo le due specifiche eccezioni che solleva la crafting table.
    //@ Il loro comportamento lo lasciamo vuoto, quello che conta e` avere definito i tipi di errore, e aver definito che `craftAdvanced` puo` generare degli errori

    //@ Non dovete per forza ricreare eccezioni per ogni errore, riutilizzate quelle di Java, come:
    //@ 1. IllegalArgumentException:
    //@ 2. IllegalStateException:
    //@ 3. UnsupportedOperationException:
    private static void exceptionsExample() {
        CraftingTable table1 = new CraftingTable();
        CraftingTable table2 = new CraftingTable();
        //@ Il metodo `exceptionsExample` mostra un primo esempio di gestione degli errori con error codes che e` obsoleto e da evitare.
        //@ Come i magic numbers, siamo noi a dover ricordare il significato semantico di ogni numero, con possibilita` di errori e confusione.
        int result = table1.craft_legacy("Sword");
        if (result == -1) { System.out.println("Inventory Full"); }
        else if (result == -2) { System.out.println("No Materials"); }
        else { System.out.println("Crafted!"); }

        //@ #### La keyword `throw` (senza la 's')
        //@ Il corpo del metodo `craftAdvanced` contiene la keyword `throw`.
        //@ Essa solleva una eccezione: vuole un oggetto di un tipo che estende exception e la sua semantica e`: ora c'e` un errore, ecco l'oggetto che ne cattura i dettagli

        //@ #### La keyword `throws` (con la 's' alla fine)
        //@ Il metodo `craftAdvanced` ha una firma arricchita, dopo la keyword `throws` dice quali tipi di errori puo` sollevare.

        //QUIZ: Posso decommentare questa linea?
        // table1.craftAdvanced("Diamond_Sword");
        //@ ---
        try {
            //QUIZ: Posso decommentare questa linea?
            // table2.craftAdvanced("Diamond_Sword");
            //@ ---

            //@ #### Il blocco try-catch
            //@ Una volta che invochiamo il metodo `craftAdvanced`, otteniamo l'obbligo di gestire l'eccezione.
            //@ Pertanto quell'invocazione va racchiusa in un blocco `try`, seguita da blocchi `catch` che definiscono cosa fare nei casi di errore
            //@ Si possono avere piu` blocchi `catch`.
            //@ Questi vanno dal piu` specifico al meno specifico, perche` vengono attraversati in ordine.
            //@ Viene scelto uno solo, non vengono attraversati tutti.

            //QUIZ: Posso lasciare solo questa linea?
            throw new CraftingException("generic exc");
            //@ ---
            //QUIZ: Posso decommentare questa linea?
            // throw new Exception();
            //@ ---
        }
        catch (RecipeMissingException e) {
            System.out.println("Unknown Recipe: " + e.getMessage());
            System.out.println(">> Opening Recipe Book...");
        }
        catch (InventoryFullException e) {
            System.out.println("Inventory Full: " + e.getMessage());
            System.out.println(">> Please clear a slot.");
        }
        catch (Exception e) {
            System.out.println("Critical Error: " + e.getMessage());
        }

        //@ Consideriamo il metodo `craftGeneral` dentro `CraftingTable`
        //@ Notate la gerarchia di ereditarieta`: mettendo la classe parent, possiamo sollevare qualsiasi errore figlio di `CraftingException`
        //@ Le eccezioni che vengono sollevate sono comunque figlie di `CraftingException`
        try {
            table1.craftGeneral("null");
            //QUIZ: Posso aggiungere una di queste righe dentro al corpo di `craftGeneral()`?  'throw new Exception()' e 'throw new EnchantingException("");'
            //@ ---
            //
            //@ La gestione della seconda e` forzata dall'indicazione attuale
            //@ La gestione della prima, no, richiede un cambio nella firma.
        } catch (CraftingException e) {}

    }

    //@ Il metodo `exceptionsAndConstructors` dimostra che le eccezioni sono particolarmente utili nei costruttori
    //@ Come faremmo a modellare errori nei costruttori, che non ritornano un intero?
    private static void exceptionsAndConstructors(){
        try {
            CraftingTable c = new CraftingTable(3);
        } catch (CraftingException e) {

        }
    }

    //@ Non tutte le eccezioni che ricevete vanno gestite, alcune vanno semplicemente propagate
    //@ Come nel caso di `deepenStackExample`, l'eccezione viene tirata in `last`, ma `mid` non sa gestirla
    //@ Allora deve aggiungere la `throws` clause nella sua firma
    //@ E la gestione viene propagata a `deepenStackExample`
    private static void deepenStackExample(){
        try {
            mid();
        }catch (Exception e){}
    }
    private static void mid() throws IOException{
        last();
    }
    private static void last() throws IOException{
        throw new IOException();
    }

    //@ ## Runtime Exceptions
    //@ Java fornisce gia` una gerarchia legata alle eccezioni
    //@ Throwable
    //@    ├── Error
    //@    └── Exception
    //@         └── RuntimeException
    //@ Gli `Error` sono errori problemi seri che non dovrebbero essere gestiti dall'applicazione (es. fine memoria, etc)
    //@ Le `RuntimeException` sono casi di errore che non dovrebbero succedere se il codice fosse scritto correttamente.
    //@ Queste includono `NullPointerException` e `IndexOutOfBoundsException`.
    //@ Per queste, non vanno gestite coi try-catch, va corretta la logica che causa il problema
    //@ Il codice di `runtimeExceptionsExample` contiene dei try-catch con `RuntimeException` ma solo per fini didattici.
    //@ Infatti, non dovremmo gestire la `IndexOutOfBoundsException` con un try-catch, ma con un semplice check che l'index sia in-bound
    //@ Oppure non dovremmo gestire la `NullPointerException` di `riskyMethod()`, ma controllare che
    private static void runtimeExceptionsExample() {
        try { getInventorySlot(99); } catch (IndexOutOfBoundsException e) {
            System.out.println("How did you figure out what exception to catch?");
        }
        try {
            int index = 0;
            if (index < 9) {
                getInventorySlot(index);
            }
        }catch (IndexOutOfBoundsException e){}

        try {
            //QUIZ: Posso mettere questa riga fuori dal try-catch?
            //@ ---
            riskyMethod();
        } catch (NullPointerException e) {
            System.out.println("Caught a NullPointer, but we really should have just checked if the object was null first!");
        }
    }
    private static void getInventorySlot(int i) {
        throw new IndexOutOfBoundsException();
    }

    private static void riskyMethod() {
        throw new NullPointerException();
    }

    //@ ## Eccezioni e subtyping
    //@ Nella definizione delle eccezioni fatta in precedenza, potremmo aver voluto metterle tutte sotto `Exception`
    //@ Questo design non e` buono, eccezioni correlate andrebbero rese figlie di una classe comune, come nel caso di `CraftingException`
    //@ La gerarchia (parziale) che abbiamo creato e` questa:
    //@   Exception
    //@    └── CraftingException
    //@         ├── RecipeMissingException
    //@         ├── InventoryFullException
    //@         └── EnchantingException
    //@              └── LevelNotEnoughException
    //@ Questo approccio ha diversi vantaggi sia dal punto di vista di chi la solleva, e di chi la deve gestire

    //@ La relazione di subtyping tra eccezioni ha una interazione interessante con l'overriding di metodi.
    //@ Consideriamo ora la classe `EnchantingTable` che estende `CraftingTable`.
    //@ Questa classe ha un metodo privato per fare enchantment che puo` sollevare una nuova eccezione: `LevelNotEnoughException`
    //@ Questa classe fa overriding dei due metodi di `CraftingTable`.
    //QUIZ: Possiamo aggiungere la chiamata a `enchant` nei metodi `craftAdvanced` e `craftGeneral`?
    //@ ---
    private static void exceptionHierarchyExample() {
        EnchantingTable enchantingTable = new EnchantingTable();
        try {
            //@ Nel caso di `craftGeneral`, visto che `LevelNotEnoughException` 'is-a' `CraftingException`, la firma del metodo cattura gia` gli errori che possono essere gnerati dalla chiamata a `enchant`
            enchantingTable.craftGeneral("Boots");
            //@ Nel caso di `craftAdvanced`, non possiamo.
            //@ E non possiamo neanche modificare la firma di `craftAdvanced` dentro a `EnchantingTable`.
            enchantingTable.craftAdvanced("Boots");
        }catch (Exception e){}

        //@ Questo perche` potremmo creare un `EnchantingTable` ma legarlo a una variabile il cui tipo statico e` un suo supertipo.
        //@ A compile-time, `craftAdvanced` viene controllato sul tipo statico, quindi su `CraftingTable`
        //@ Quindi se permettiamo tipi dinamici che sollevano piu` eccezioni, rischiamo di avere codice che solleva eccezioni che il compilatore non ha detto di gestire.
        CraftingTable ct = new EnchantingTable();
        try {
            ct.craftAdvanced("Boots");
        }catch (Exception e){}
        //@ Invece, nella sottoclasse possiamo dichiarare di sollevare meno eccezioni.
        //@ Come nel caso di `craftAdvanced` dentro a `EnchantingTable`.
        //@ I controlli statici saranno ok, perche` ci fanno catturare tutto, probabilmente generando codice morto
        //@ Pero` a runtime, non ci saranno eccezioni che non sono gestite
    }

    //@ ## Try-with-resources
    //@ Per oggetti che 'is-a' `AutoCloseable`, possiamo usare il try-with-resources
    //@ A livello di sintassi, notate il try con le () invece che con le {}.
    //@ Semanticamente, questi sono oggetti su cui si puo` chiamare il metodo `close`
    //@ Il compilatore inserisce una chiamata a `br.close()` in ogni caso.
    //@ Perche` usare il try-with-resources?
    //@ Se br.close tira una IOException (E puo`), `fr` non viene chiuso.
    private static void tryWithResourcesExample(){
        try (FileReader fr = new FileReader("asd");
             BufferedReader br = new BufferedReader(fr)) {
            br.readLine();
        } catch (IOException e) {}
        catch (Exception e) {}
    }

    //@ ## Link Utili
    //@ -  https://docs.oracle.com/javase/tutorial/essential/exceptions/tryResourceClose.html
    //@ FIXME: se avete suggerimenti, fateli via pull request

    //@ ## Follow-up
    //@ - errori in altri linguaggi: rust e go // FIXME
}

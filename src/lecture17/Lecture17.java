package lecture17;

import lecture17.eq.*;
import lecture17.sorting.*;
import java.util.*;

//@ # Lezione 17
public class Lecture17 {
    public static void main(String[] args) {
        //@ BT: Remember, Understand
        System.out.println("---------------- The Collections Framework ----------------");
        threeExamples();
        implementationsExample();
        programmingToInterfacesExample();
        //@ BT: Understand, Analyze
        System.out.println("---------------- Equality ----------------");
        idVSeqExample();
        hashExample();
        //@ BT: Understand
        System.out.println("---------------- Sorting ----------------");
        comparableExample();
        comparatorExample();
    }

    //@ ## Collections
    //@ E` un framework, una libreria di classi e di interfacce create e testate cosi` che non dobbiamo reinventare la ruota
    //@ Ci sono due radici nella gerarchia di ereditarieta` delle Collections
    //@ - Radice 1: `Collections<E>`, cioe` un gruppo di cose. Suoi sottotipi includono le interfacce `List<E>` per liste e `Set<E>` per insiemi
    //@ - Radice 2: `Map<K,V>`, un dizionario, un registro chiave(K)-valore(V)
    //@ Tutti questi tipi sono generici, si possono quindi instanziare con un qualsiasi contenuto che sia di tipo classe (o interfaccia)

    //@ Il metodo `threeExamples` mostra un esempio di ogni interfaccia chiave.
    //@ Focalizziamoci sul tipo statico degli oggetti che stiamo creando, parliamo delle classi dopo.
    private static void threeExamples() {
        //@ Possiamo usare `List` per implementare la Hotbar di Minecraft
        //@ Questo perche` le caratteristiche fondamentali delle liste sono:
        //@ - accesso tramite indice
        //@ - duplicati
        List<String> hotbar = new ArrayList<>();
        hotbar.add("Sword");
        hotbar.add("Dirt");
        hotbar.add("Dirt");
        System.out.println("List: " + hotbar);
        String second = hotbar.get(1);

        //@ Possiamo usare `Set` per implementare la lista dei Biomi scoperti
        //@ Le caratteristiche fondamentali dei `Set` sono
        //@ - accesso casuale
        //@ - non ci sono duplicati
        //@ Aggiungere "Forest" due volte non ha effetto
        Set<String> biomes = new HashSet<>();
        biomes.add("Forest");
        biomes.add("Desert");
        System.out.println("Set: " + biomes);
        biomes.add("Forest");
        System.out.println("Set: " + biomes);

        //@ Possiamo usare `Map` per implementare un registro che collega una chiave a un valore
        //@ In questo caso le chiavi sono di tipo `String` e i valori sono `Integer`
        //@ Le chiavi devono essere univoche
        Map<String, Integer> durabilityRegistry = new HashMap<>();
        durabilityRegistry.put("Wood_Pickaxe", 59);
        durabilityRegistry.put("Iron_Pickaxe", 250);
        System.out.println("Map Value: " + durabilityRegistry.get("Iron_Pickaxe"));
    }

    //@ #### Le classi di Collections
    //@ Le interfacce definiscono cosa viene fatto, le API per il programmatore
    //@ Le classi che implementano tali interfacce dicono come viene realizzata quella logica
    //@ Nel metodo `implementationsExample` le vediamo all'opera
    private static void implementationsExample() {
        //@ - `ArrayList` e` sottotipo di `List` e la realizza con un array `[]`. L'accesso e` veloce, il resize e` costoso
        //@ - `LinkedList` e` sottotipo di `List` e la realizza tramite catena di puntatori. L'accesso e` lento, l'inserimento o il resize e` veloce.
        List<String> fastRead  = new ArrayList<>();
        List<String> fastWrite = new LinkedList<>();

        //@ `Set` e `Map` hanno due tipi di implementazioni, tramite Hash e tramite Tree.
        //@ - le implementazioni basate su hash hanno accesso piu` veloce, se la funzione di `hash` e` ok
        //@ - le implementazioni basate su tree hanno un ordinamento naturale
        Set<String> hs = new HashSet<>();
        Set<String> ts = new TreeSet<>();
        Map<String, Integer> registry= new HashMap<>();
        Map<String, Integer> reg_sort= new TreeMap<>();

        //@ Vi e` un ultima interfaccia `Queue`, che e` sempre una lista ma cambia (logicamente) il tipo di accesso
        //@ Le code sono per processare, quindi non permettono accesso ad indice, ma permettono push e pop tramite i metodi `add` e `poll`
        //@ Per esempio, possiamo implementare la chat di Minecraft come una Queue, il primo messaggio scritto e` il primo che viene mostrato

        //QUIZ: Come mai la classe `LinkedList` puo` essere usata come tipo dinamico di una `Queue`?
        //@ ---
        Queue<String> slowQueue = new LinkedList<>();
        Queue<String> commandQueue = new ArrayDeque<>();
        commandQueue.add("/gamemode creative");
        commandQueue.add("/kill @e[type=bat]");
        commandQueue.add("/say Hello");
        while (!commandQueue.isEmpty()) {
            String cmd = commandQueue.poll();
            System.out.println(" Executing: " + cmd);
        }
    }

    //@ #### Generalizzare
    //@ Evitiamo di scrivere metodi del tipo `print(ArrayList<String> items)` perche` forzano il chiamante a usare una implementazione specifica delle collections
    //@ Opzioni migliori sono `List` o ancora meglio `Collection`, in base a cosa dobbiamo fare nel corpo
    //@ Astraete sempre verso il tipo piu` in alto nella gerarchia di erediarieta` (con tutte le funzionalita` che servono)
    private static void programmingToInterfacesExample() {
        ArrayList<String> myItems = new ArrayList<>();
        myItems.add("Apple");
        printInventory(myItems);
        countItems(myItems);
    }
    public static void printBad(ArrayList<String> items) {
        for (String s : items) System.out.println(s);
    }

    public static void printInventory(List<String> items) {
        System.out.println("Item 1: " + items.get(0));
    }

    public static void countItems(Collection<String> items) {
        System.out.println("Count: " + items.size());
    }

    //@ Di sotto si propone una visualizzazione ascii della gerarchia di ereditarieta` del framework
    //@ Iterable<T>
    //@ |
    //@ Collection<T> -------------------------.
    //@ |                  |               |
    //@ List<T>             Set<T>         Queue<T>
    //@ |                  |               |
    //@ [ArrayList]        [HashSet]      [ArrayDeque]
    //@ [LinkedList]       [TreeSet]      [PriorityQueue]
    //@
    //@ (Separate Branch)
    //@ Map<K,V>
    //@ |
    //@ [HashMap]
    //@ [TreeMap]
    //@

    //@ ## Equals
    //@ In Java ci sono due modi di controllare se due oggetti sono lo stesso
    //@ 1. controllare se puntano allo stesso oggetto in memoria, tramite `==`
    //@ 2. uguaglianza logica, tramite il metodo `equals`, nel caso gli oggetti catturino essenzialmente gli stessi dati
    //@ Il comportamento di default di `equals` e` chiamare la `==`
    private static void idVSeqExample() {
        //@ Abbiamo due oggetti che identificano le stesse coordinate nel mondo
        BlockPos posA = new BlockPos(10, 64, 10);
        BlockPos posB = new BlockPos(10, 64, 10);
        //QUIZ: Cosa viene stampato di sotto?
        //@ ---
        System.out.println("posA == posB: " + (posA == posB));
        System.out.println("posA.equals(posB): " + posA.equals(posB));

        //
        //@ Gli oggetti vivono ad indirizzi diversi, quindi non sono uguali
        //@ Questo puo` essere problematico:
        //@ `posA` potrebbe essere la posizione del giocatore, `posB` di un blocco di lava
        //@ Se il controllo non ci dice che le posizioni coincidono, non brucio quando sono sulla lava
        //@ Inoltre la classe `BlockPos` non fa overriding della equals
        //@ Quindi anche la `equals` dira` false.

        //@ Possiamo fare Overriding della `equals()`, come nella classe `PosWithEquals`
        //QUIZ: Posso fare overriding scrivendo `bool equals(PosWithEquals p)`?
        //@ ---

        //
        //@ In una equals posso fare diversi controlli che mi semplificano la logica
        //@ - se `==`, allora `equals`
        //@ - se uno e` `null`, oppure hanno classi diverse, non-equals
        BlockPosEq p1 = new BlockPosEq(10, 64, 10);
        BlockPosEq p2 = new BlockPosEq(10, 64, 10);
        System.out.println("p1.equals(p2): " + p1.equals(p2));
        //@ In questo caso gli oggetti sono determinati come uguali.
        //@ Notate, questo e` uno dei pochi casi in cui e` ok usare la `.getClass` o la `instanceof`.
        //@ Bisogna stare attenti pero`:
        //QUIZ: Cosa stampa?
        //@ ---
        System.out.println(p1 instanceof Object);

        //@ Usate uno tra `getClass` e `instanceof` in base alla logica che dovete implementare
    }

    //@ ## Hashcode
    //@ Questo pero` non e` sempre sufficiente, perche` il framework di Collections usa anche un altro metodo per controllare se due oggetti sono gli stessi `hashcode`
    //@ Vediamo un esempio dentro al metodo `hashsetFailureExample`.
    private static void hashExample() {
        HashSet<BlockPosEq> brokenSet = new HashSet<>();
        BlockPosEq p1 = new BlockPosEq(10, 64, 10);
        BlockPosEq p2 = new BlockPosEq(10, 64, 10);
        brokenSet.add(p1);
        brokenSet.add(p2);
        //QUIZ: Cosa stampa?
        //@ ---
        System.out.println("Set Size: " + brokenSet.size());

        //@ Il motivo e` il funzionamento interno di `HashSet`
        //@ ctrl-click: add->put->putval e questo chiama la `hash`, che dentro chiama la `hashcode`
        //@ Di base questo metodo ragiona a livello `Object`, quindi sul valore di memoria di un oggetto
        //@ Senza farne overriding, due oggetti, anche se con equals uguale, vengono definiti diversi

        //@ Cos'e` una `hash` function?
        //@ E` una funzione tipicamente usata in crittografia
        //FIXME

        //@ Java ci da` tutti gli strumenti per evitare problemi
        //@ `Object` definisce `equals` e `hashcode`, quindi possiamo sempre farne overriding
        //@ Fare overriding solo della `equals`, per una classe i cui oggetti sono usati in `Collections` e` errato
        //@ Infatti le classi `Hash` usano `hashcode` per calcolare la chiave
        //@ In base alla chiave trovano il 'bucket'
        //@ Per comparare gli oggetti di un bucket usano la `equals`
        //@ Chiaramente con `hashcode` diverse, si cerca in buckets diversi

        HashSet<BlockPosEqHc> safeSet = new HashSet<>();
        BlockPosEqHc sp1 = new BlockPosEqHc(10, 64, 10);
        BlockPosEqHc sp2 = new BlockPosEqHc(10, 64, 10);
        safeSet.add(sp1);
        safeSet.add(sp2);
        System.out.println("Set Size: " + safeSet.size());

        BlockPosEqHc p3 = new BlockPosEqHc(10, 64, 10);
        boolean found = safeSet.contains(p3);
        //QUIZ: Cosa stampa?
        //@ ---
        System.out.println("Contains p3? " + found);

        //
        //@ Perche`?
        //@ La `hashcode` identifica lo stesso bucket
        //@ Al suo interno la `equals` trova un oggetto per cui e` `true`
    }
    //@ Quindi: se fate overriding della `equals`, fate overriding anche della `hashcode`


    //@ ## Ordinamento
    //@ #### Comparable
    //@ Il framework fornisce anche una classe `Collections` (con la S) che ha molti metodi statici
    //@ Per esempio per ordinare le collections
    //@ Come facciamo a dire a Java quale blocco viene prima?
    //@ Implementiamo l'interfaccia `Comparable<T>`, che definisce un 'ordinamento naturale'
    //@ Nel metodo `comparableExample` vediamo la classe `LootItem` che la implementa
    private static void comparableExample() {
        List<LootItem> inventory = new ArrayList<>();
        inventory.add(new LootItem("Stick", 1));
        inventory.add(new LootItem("Diamond", 100));
        inventory.add(new LootItem("Iron Ingot", 50));

        //@ Il contratto di `compareTo` e` il seguente:
        //@ se negativo -> `this` viene prima dell' `other`
        //@ se positivo -> `this` viene dopo 'other'
        //@ se zero     ->  sono uguali
        //@ In questo caso associamo value alti agli oggetti che vogliamo prima
        //! Attenzione all'ordine del paragone, se mettete prima `this` o `other cambia da ascendente a discendente
        //@ Potete anche basarvi sul metodo `Integer.compare`
        Collections.sort(inventory);
        for (LootItem item : inventory) {
            System.out.println(item);
        }
    }

    //@ #### Comparator
    //@ E se volessimo ordinare una collection in piu` modi?
    //@ Questo non cambia l'ordine naturale di quegli oggetti.
    //@ Per questo creiamo un ordinatore esterno: un oggetto che implementa `Comparator<E>` e che sa comparare gli oggetti di tipo `E`
    //@ Questo oggetto viene poi passato ai metodi che ordinano, come vediamo in `comparatorExample`
    private static void comparatorExample() {
        List<LootItem> inventory = new ArrayList<>();
        inventory.add(new LootItem("Stick", 1));
        inventory.add(new LootItem("Diamond", 100));
        inventory.add(new LootItem("Coal", 1));
        //@ `LootItem` ha sempre lo stesso ordinamento naturale
        //@ Ma `NameSorter` puo` ordinarli alfabeticamente
        //@ In questo caso, si basa sul metodo `compareTo` della classe `String`
        Comparator<LootItem> nameSorter = new NameSorter();
        inventory.sort(nameSorter);
        for (LootItem item : inventory) {
            System.out.println(item);
        }

        //@ #### Ordinamento moderno
        //@ In Java moderno possiamo usare factory methods e concatenarli per creare comparatori anonimi
        //@ In sede d'esame voglio le classi esterne
        List<LootItem> inv = new ArrayList<>();
        inv.add(new LootItem("Stick", 1));
        inv.add(new LootItem("Coal", 1));
        inv.add(new LootItem("Diamond", 100));

        //@ vogliamo ordinare per valore e poi per nome
        //QUIZ: Che modificatori hanno?
        //@ ---
        inv.sort(
                Comparator.comparingInt(LootItem::getValue)
                        .reversed()
                        .thenComparing(LootItem::getName)
        );
        for (LootItem item : inv) {
            System.out.println(item);
        }
    }

    //@ ## Link Utili
    //@ -
    //@ -
    //@ FIXME: se avete suggerimenti, fateli via pull request

    //@ ## Follow-up
    //@ -
}

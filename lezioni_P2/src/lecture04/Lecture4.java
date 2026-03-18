package lecture04;

import lecture03.ackages.blocks.TNT;
import lecture03.ackages.entities.Player;
import lecture03.ackages.entities.Witch;

//@ # Lezione 4
public class Lecture4 {
    public static void main(String[] args){
        //@ BT: Understand, Analyse, Apply
        System.out.println("---------------- Incapsulamento e Invarianti ----------------");
        invariants();
        //@ BT: Remember, Understand
        System.out.println("---------------- Passaggio dei valori ----------------");
        valuePassingExample();
        //@ BT: Understand, Analyse, Apply
        System.out.println("---------------- Class Memory Layout ----------------");
        System.out.println("---------------- Object Memory Layout ----------------");
        layoutTest();
    }

    //@ ## Incapsulamento e Invarianti
    //@ Con i vari modificatori visti fin ora, il linguaggio ci da una proprieta` fondamentale:
    //@ Incapsulamento (o information hiding)
    //@ Questo ci permette di nascondere dei dettagli implementativi all'interno di una classe, senza rivelarli agli utilizzatori della classe stessa
    //@ Inoltre possiamo definire 'invarianti', cioe` dei fatti che sono sempre veri per ogni classe e ogni oggetto che viene creato

    //@ Tutto il codice e` soggetto a Invarianti, le funzionalita` viste fin ora ci danno un linguaggio che ci permette di definirli
    //@ C non e` un linguaggio che ci permette di avere Incapsulamento ed Invarianti (come abbiamo visto)
    //@ Altri linguaggi ci permettono di definire invarianti ancora piu` potenti (es, Rust)

    //@ Per esempio consideriamo `lecture03/packages/entities/Player`, ci sono diversi invarianti:
    //@ Invariante: `poisonDamage` non fa nulla se `this` non e` avvelenato
    //@ Invariante: `poisonDamage` non fa danno se ho meno di 2 di vita, e sono avvelenato

    //@ Esempi: Come fareste un effetto di rigenerazione? E una Golden Apple?
    //@ Controllate di non farli in modo che qualcuno possa barare e ottenere piu` del dovuto

    //@ Consideriamo la classe `lecture03/packages/blocks/TNT`
    //@ Per capirne gli invarianti ci facciamo diverse domande:
    //@ - c'e` del comportamento che non dovremmo permettere?
    //@ - ha senso poter far calare il fuso senza che sia innescata?
    //@ - ha senso farla esplodere senza che sia innescata?
    //@ - il fuso puo` essere un valore negativo?
    //
    //@ Per questo, iniziamo rendendo `fuseLength` un campo `private`
    //@ Vediamo la classe per vedere
    //@ - altre domande per ragionare sui modificatori
    //@ - come si propagano gli invarianti nei vari metodi

    //@ La `TNT` deve scoppiare solo quando il fuso arriva a 0
    //@ Non dovremmo permettere di farla scoppiare prima
    //@ Inoltre, il fuso si deve poter ridurre solo se la `TNT` e` innescata
    //@ Infine, il fuso si deve ridurre col passare del tempo, e non per valori arbitrari

    public static void invariants() {
        TNT standardTNT = new TNT();
        //@ standardTNT.fuseLength = -100; // COMPILER ERROR!

        //@ Utilizzo corretto
        standardTNT.ignite();
        standardTNT.tick();
    }

    //@ ## Passaggio dei valori
    //@ Ci sono valori di tipo Primitivo, valori di tipo Classe, e valori di tipo Array
    //QUIZ: Dove sono allocati?
    //@ ---
    //@ I valori di tipo Primitivo, allocati sullo stack, tipicamente occupano uno (o pochi) registri, e vengono passati per 'Copia'
    //@ I valori di tipo Array e Classe vengono allocati in memoria
    //@ Pertanto, non vengono passati per copia, perche` potrebbero occupare molti registri
    //@ Quindi, vengono passati per Riferimento, invece che un oggetto, viene passato un puntatore a tale oggetto
    //@ Quando una variabile contiene un oggetto, in realta` contiene un puntatore a quell'oggetto.
    //@ Un puntatore occupa un registro (tipicamente), ed e` piu` facile sia da passare, che da allocare in uno stack frame
    //@ Java per fortuna nasconde questa cosa, e non permette aritmetica dei puntatori, inserendo dei dereference automatici quando ha valori di tipo Classe e Array

    private static void valuePassingExample(){
        //@ `x` e` una variabile locale
        int x = 0;
        //@ `x` viene copiata nello stack frame di `helper`, se `helper` modifica la sua copia, `x` non cambia
        helper(x);

        //@ `t` e` una variabile locale che punta alla locazione di memoria dove e` allocata un `TNT`
        TNT t = new TNT();
        //@ il riferimento di `t` viene passato a `reference`, se quest'ultimo modifica `t`, il cambiamento lo vedremo anche qui
        reference(t);

        //@ `arrT` e` una variabile locale che momento contiene `null`
        TNT[] arrT;
        //@ a questo punto la variabile punta alla locazione di memoria dove e` allocato l'array di 5 `TNT`
        arrT = new TNT[5];
        //QUIZ Cosa contiene `arrT[0]` ?
        //@ ---

        //
        //@ questo ciclo inizializza ogni cella dell'array ad una `TNT`
        for (int i = 0 ; i < 5 ; i++){
            arrT[i] = new TNT();
        }
    }
    private static void helper(int p){}
    private static void reference(TNT t){}

    //@ ## Layout di Classi e Oggetti in memoria
    //@ Consideriamo queste classi: `Witch`, `Player`, `TNT`
    //@ Quando viene caricato il programma: dove sono i metodi di ogni classe? e i metodi `static`?
    //@ Dove sono i campi `static`?
    //@ Quando viene eseguito `layoutTest`, che oggetti vengono creati?
    //@ Come viene creato un oggetto di tipo `Witch`? e uno di tipo `Player`? e uno di tipo `TNT`?

    //@ #### Layout di `Witch`
    //@ La classe contiene solo 2 metodi, il cui codice viene messo nella 'code section' del programma

    //@ #### Layout di `Player`
    //@ La classe contiene 4 campi e 6 metodi.
    //@ Nulla e` `static`, quindi il codice dei 6 metodi viene messo nella 'code section' del programma

    //@ #### Layout di `TNT`
    //@ La classe contiene 2 campi `static` e 2 campi, 1 costruttore e 3 metodi
    //@ I campi `static` vengono allocati nella parte di memoria 'read-only'
    //@ I 3 metodi e il costruttore vengono messi nella 'code section' del programma
    //@ Se ci fossero stati metodi `static` sarebbero andati nella 'code section' anche loro

    //@ #### La v-table
    //@ Ogni classe contiene una v-table, cioe` un elenco di puntatori alle implementazioni dei vari metodi
    //@ L'ordinamento dei metodi non e` quello definito dal programmatore, e` una cosa piu` complicata che interagisce con l'ereditarieta`
    //@ Quindi per ora vi basti sapere che i metodi sono ordinati, ma non sapete come

    //@ Supponendo che i due metodi di `Witch` (`fakeAttack` e `attack` siano rispettivamente agli indirizzi `0x000010` e `0x000040`, la v-table di `Witch` sara` fatta come segue:

    //@ ---
    //@ | 0x000010 |
    //@ | :---: |
    //@ | 0x000040 |
    //@ Il compilatore sa che `fakeAttack` quindi corrisponde all'offset `0` nella v-table, mentre `attack` ha offset `1`

    //@ Supponendo che i sei metodi di `Player` siano agli indirizzi `0x0000A0`, `0x0000B0`, ... `0x0000F0`, la v-table di `Player` sara` fatta come segue:

    //@ ---
    //@ | 0x0000A0 |
    //@ | :---: |
    //@ | 0x0000B0 |
    //@ | 0x0000C0 |
    //@ | 0x0000D0 |
    //@ | 0x0000E0 |
    //@ | 0x0000F0 |

    //? Come potrebbe essere fatta la v-table di `TNT`? Contate che alcuni metodi sono `static`

    //@ #### Layout degli oggetti
    //@ Ogni oggetto segue un layout comune:
    //@ - la prima parola e` un link alla v-table della sua classe
    //@ - seguita da una parola per ogni campo definito dalla sua classe
    //@ Considerando un oggetto di tipo `Witch`, il compilatore converte una chiamata al metodo `fakeAttack` come segue:
    //@ - considera l'indirizzo dell'oggetto
    //@ - la prima parola a questo indirizzo e` un puntatore alla sua v-table
    //@ - segui questo puntatore, ora sei nella v-table
    //@ - vai avanti di `0` parole (`0` e` l'offset di `fakeAttack`): questo e` la parola contenente il puntatore al codice che cerchi
    //@ - segui questo puntatore: ora sei nel codice di `fakeAttack`

    //? Esercizio: ragionate su come viene fatta l'invocazione dei metodi di `TNT` e di `Player`

    private static void layoutTest(){
        Witch w1 = new Witch();
        Witch w2 = new Witch();

        //@ Disegnare il layout attuale:
        //@ - code section per Witch, Player, TNT
        //@ - read-only section per Witch, Player, TNT
        //@ - stack: `Lecture04.main()`::`Lecture04.layoutTest()` + local variables w1, w2
        //@ - heap: oggetti per w1, w2
        //@ Gli oggetti hanno solo la v-table

        Player p1 = new Player();
        Player p2 = new Player();
        //@ update di campo diretto
        p2.username = "steve";
        //@ Aggiornare il layout durante la chiamata a `setPoisoned`
        p1.setPoisoned(true);
        //@ Aggiornare il layout:
        //@ - stack: local variables p1, p2
        //@ - heap: oggetti per p1, p2
        //@ Gli oggetti hanno la v-table e 4 parole
        //@ Assumendo che la v-table di `Player` sia all'indirizzo `0x00A000`, il layout di `p1` e` il seguente

        //@ ---
        //@ | 0x00A000 |
        //@ | :---: |
        //@ | null |
        //@ | 20 |
        //@ | false |
        //@ | 10 |

        //@ ---
        //@ | 0x00A000 |
        //@ | :---: |
        //@ | null |
        //@ | 20 |
        //@ | true |
        //@ | 10 |

        //@ Quest'ultimo invece e` il layout di `p1` dopo la chiamata a `setPoisoned()`.

        p2.damage(10);
        //@ Aggiornare il layout:
        //@ - heap: oggetto per p2

        TNT t1 = new TNT();
        TNT t2 = new TNT();
        //@ Aggiornare il layout durante le chiamate a `ignite` e `tick`
        t1.ignite();
        t2.tick();
        //@ Aggiornare il layout:
        //@ - stack: local variables t1, t2
        //@ - heap: oggetti per t1, t2

        //? Esercizio: pensate al layout di `t1` e `t2`
    }

    //@ ## Link Utili
    //@ -
    //@ FIXME: se avete suggerimenti, fateli via pull request

    //@ ## Follow-up
}


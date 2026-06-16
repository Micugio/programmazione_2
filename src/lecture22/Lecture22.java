package lecture22;

import lecture22.collectionsMVC.CollectionsMain;

public class Lecture22 {

    //@ # Lezione 22
    public static void main(String[] args){
        //@ BT: Analyze, Evaluate
        System.out.println("---------------- MVC ----------------");
        //@ # Il pattern MVC
        //@ MVC e` un design pattern molto usato per separare la business logic dalla display logic
        //@ Acronimo: M = model | V = view | C = controller
        //@ Il potere dell'MVC si vede specialmente quando andiamo ad estendere un'applicazione.
        //@ Qui facciamo proprio questa cosa, partiamo da una app semplice, con requisiti di business logic semplice per poi aggiungerne altri

        //@ ## Packaging
        //@ Per prima cosa: organizziamo i package
        //@ - nel package model, mettiamo la business logic iniziale: vogliamo poter visualizzare uno `StoneBlock` la cui quantita` e` fissa, e il nome si puo` cambiare con click utente
        //@ - nel package view, mettiamo la grafica dello `StoneBlock`: una hbox con un bottone per il nome , e un testo per la quantita`, il tutto su un rettangolo grigio bordato.
        //@ - nel package controller, mettiamo lo `StoneBlockController`.
        //@ Il controller deve:
        //@ 1. prendere l'input utente -> quindi e` un handler, che leghiamo al bottone della view
        //@ 2. modificare il modello in base all'input -> quindi deve avere una reference al model: student
        //@ 3. modificare la view -> quindi deve avere una reference alla view: studentview

        //! model e view non si parlano, quindi non hanno bisogno di references tra loro e neanche di references a controller, perche` non devono dire nulla al controller
        //@ ---

//        Main.main(args);

        System.out.println("---------------- Controller non vuol dire Handler ----------------");
        //@ #### Controller != Handler
        //@ Se vogliamo cambiare il nome di uno studente con un tasto?
        //@ Per non duplicare codice, possiamo mettere il key handler dentro il controller
        //@ Questo richiede instanceof, quindi possiamo mettere gli handler dentro alla view
        //@ E passare il gestore della funzionalita`: il controller
        //@ In questo caso il controller non e` un handler, e` un connettore di logica

//        MultiMain.main(args);

        System.out.println("---------------- MVC e collections ----------------");
        //@ Cambiamo il modello. non piu` un solo studente, ma una lista cioe` una classe.
        //@ Aggiungiamo classroom al modello: `BlockStack` e` triviale, serve solo da wrapper
        //@ Aggiungiamo poi la possibilita` di diminuire tutta la quantita` di blocks
        //QUIZ: In quale parte del modello si aggiunge?
        //@ ---
        //@ Aggiungiamo la `BlockStackView`: questo non e` triviale.
        //@ Questa la inizializziamo con una collection di `StoneBlockView` e con un `BlockStackController`
        //@ La collection serve per inizializzare la classe stessa, che e` una collection (spesso una HBox, o VBox, o qualcosa di collection-like, non un BorderPane)
        //@ Come prima non rendiamo i controller handlers e ne passiamo la reference alla view
        //@ E possiamo creare delle lambda che delegano ai metodi del controller
        //@ Cosi` facendo possiamo mettere handler diversi con lo stesso comportamento, in posti diversi

        //@ Per completare la view creiamo `CollectionsMainView`
        //@ Dobbiamo avere dei metodi per delegare l'inizializzazione di alcune parti con oggetti creati altrove
        //@ La main view non sa come creare una `BlockStackView`, percio` si crea un setter

        //@ Ora parliamo del controller `BlockStackController`.
        //@ Questo ha accesso al suo model: il `BlockStack`
        //@ Inoltre ha la sua view `BlockStackView` e un metodo per crearla
        //@ Infine, questo controller ha accesso a tutti i controller degli oggetti che contiene.
        //@ Nel model, il `BlockStack` contiene `StoneBlock`
        //@ Nella view, la `BlockStackView` contiene gli `StoneBlockView`
        //@ Nel controller, il `BlockStackController` contiene tutti gli `StoneBlockController`
        //@ Questo serve per:
        //@ - poter chiamare funzionalita` sugli oggetti, delegando ai loro controller
        //@ - poter aggiornare il model
        //@ - poter creare la view a partire dal model aggiornato
        //@ Per aggiornare la View, inoltre, serve un link alla MainView
        //@ Qui dentro poi riusiamo lo stesso controller senza creare un `MainViewController` per gestire funzionalita` duplicate
        //@ Queste sono l'ordinamento, e la possibilita` di triggerarlo da parti diverse del codice

        //@ Aggiungiamo l'ordinamento del model, per nome ed eta`
        //@ Creiamo i comparators per i Blocks: `BlockNameComparator` e `BlockQtyComparator`
        //@ Questi non bastano, dobbiamo anche creare i comparator per i controller del model
        //@ Creiamo quindi `BlockControllerComparatorByName` e `BlockControllerComparatorByQty`
        //@ Questi delegano semplicemente ai loro corrispettivi del model
        //QUIZ: Si potrebbero astrarre con delle chiamate ai metodi statici di `Comparator.`?
        //@ ---
        //@ La parte cruciale del controller e` come il model sia un riflesso del controller:
        //@ - prima si ordinano i controller
        //@ - poi se ne estrae il model aggiornato
        //@ - infine (nel metodo relativo) si crea la view aggiornata
        //@ Il metodo `controllerSort` non puo` agire sul model
        //@ Il model della classe e` legato ai controller, per cui
        //@ - prima si ordina la collection di controller
        //@ - poi si estrae il modello ordinato dalla collection
        //@ Cosi` facendo si massimizza il riuso di oggetti: gli unici oggetti che abbiamo eliminato sono i contenitori, non il contenuto

        //QUIZ: Se ordino prima il model, posso ricostruire il controller?
        //@ ---

        //@ Aggiungiamo gli shortcut da tastiera nelle varie View
        //@ Notiamo come 'Q' e 'N' ordinino solo col focus settato sulla lista
        //@ Col focus sul borderpane, o da altre parti, si attivano gli handlers della mainview, con 'W' e 'M'

        CollectionsMain.main(args);
    }

    //@ ## Link Utili
    //@ - business logic definition: https://@edencoding.com/mvc-in-javafx/
    //@ - MVC graficamente: https://@bovolato.dev/it/blog/tutorial-java/mvc/
    //@ -
    //@ FIXME: se avete suggerimenti, fateli via pull request

    //@ ## Follow-up
    //@ -
}

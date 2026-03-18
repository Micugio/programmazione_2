#include "test_vars.cpp"
#include "test_stack_frame.cpp"
#include "test_puntatori.cpp"
#include "test_memoria.cpp"
//@ # Lezione 1: Recap delle nozioni di Programmazione 1
//@ ## Introduzione
//@ Fin'ora avete fatto "programming in the small": piccole procedure, familiarizzazione col coding
//@ In questo corso vediamo "programming in the large"
//@  - Suddivisione del lavoro tra persone/gruppi (divide et impera)
//@  - Manutenibilità (che succede se voglio cambiare qualcosa tra un mese/un anno/…)
//@  - Robustezza
//@ Programming in the large si ottiene tramite:
//@  - Ingegneria del software (vedi altri corsi UNITN)
//@  - Buone tecniche di programmazione (es. commenti aggiornati)
//@  - Supporto dal linguaggio: OO -> Questo e` il focus del corso
//@ Di seguito facciamo un recap delle nozioni di P1, programming in the small, per costruire una notional machine corretta sulla quale montare i concetti di questo corso

//@ ## Notional Machine
//@ La notional machine e` un concetto di pedagogia, nello specifico di didattica della programmazione, e` l'astrazione che ognuno di noi si crea nella propria testa per quanto riguarda la semantica di un linguaggio (il runtime, se vogliamo)

int main() {
    //@ Bloom's Taxonomy: https://bpb-us-e1.wpmucdn.com/wordpressua.uark.edu/dist/a/315/files/2013/09/Blooms_Taxonomy_pyramid_cake-style-use-with-permission.jpg?bid=315
    //@ BT: tutto Remember

    //@ #### Primo argomento: variabili
    //@ Le variabili hanno un tipo e si possono inizializzare.
    //@ Non inizializzare le variabili e` un problema, i linguaggi seri forzano l'inizializzazione

    //@ Lo `scope` definisce la visibilita` di una variabile.
    //@ Se una variabile e` visibile da tutto il programma, e` globale.
//    test_vars();
    //@ Se una variabile e` visibile solo all'interno di una funzione (o di una sottoparte della funzione), e` locale
//    test_vars2();
//    test_s1();
//    test_s2();

    //@ #### Secondo argomento: stack frame
    //@ Ogni volta che chiamiamo una funzione viene creato un 'allocation record' o 'stack frame', che contiene:
    //@  - parametri attuali,
    //@  - variabili locali,
    //@  - indirizzo di ritorno
//    test_stack_frame();
    //@ Visualizziamo lo stack di allocation records
    //@ `fact` e` una funzione ricorsiva, perche`?
    int r = test_stack_frame_rec();

    //@ #### Terzo argomento: puntatori
    //@ I puntatori sono indirizzi in memoria, data una variabile si puo` prendere un puntatore con l'operazione `&` e si puo` seguire un puntatore con l'operazione `*`
    //@ La funzione `test_puntatori()` definisce puntatori (int * -- scritti comunemente male ma vabbe`)
//    test_puntatori();
    //@ La funzione `test_puntatori2()` passa un puntatore a `incrementa_ptr` e puo` farlo perche` `x` e` ancora viva sullo stack
//    test_puntatori2();

    //@ #### Quarto argomento: memoria
    //@ Un programma ha diverse zone:
    //@ - la zona di codice, dove si trovano le istruzioni di ogni funzione, tipicamente in RX
    //@ - la zona dei globali, con le variabili globali
    //@ - lo stack e la heap

    //@ Nella funzione `test_mem_static()` la variabile `x` e` locale, quindi viene allocata sullo stack, all'interno dello stack frame di `test_mem_static`
//    test_mem_static();

    //@ Nella funzione `test_mem_dyn()` la variabile `px` e` locale ma viene creata con la keyword `new`, che la alloca sulla heap.
    //@ Esistono 3 modi di gestire l'allocazione della memoria:
    //@ - manuale (C),
    //@ - garbage collection (Java, ...)
    //@ - ownership (Rust)
    test_mem_dyn();
}
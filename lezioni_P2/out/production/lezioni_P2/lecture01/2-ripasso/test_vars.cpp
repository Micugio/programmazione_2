#include <iostream>
void f();
void g();
void modifica2();

// due variabili globali, una delle quali e` inizializzata
int vg;
int x=5;

// La funzione `test_vars()` stampa la variabile globale `x`, prima e dopo la chiamata a `f`, che la incrementa
void test_vars() {
    std::cout << "x in main " << x << std::endl;
    f();
    std::cout << "x in main " << x << std::endl;
    //QUIZ: che valori stampa?
}
void f() {
    x=x+1;
    std::cout << "x in f " << x << std::endl;
    return;
}

// La funzione `test_vars2()` dichiara una variabile locale `b`
void test_vars2() {
    x = 5;
    int b=6;
    printf("(x,b) in main: (%d,%d) -",x,b);
    g();
    printf("(x,b) in main: (%d,%d)\n",x,b);
    //QUIZ: che valori stampa?
}
// La funzione `g()` dichiara una variabile locale `x` che fa shadowing della globale
// La `x` locale fa shadowing della `x` globale, che quindi non e` raggiungibile qui`
void g() {
    int x=2, b=4;
    printf("(x,b) in g: (%d,%d) -",x,b);
    return;
}


// Nella funzione `modifica1()`, `s1` e` un parametro formale
void modifica1(int s1) {
    s1++;
}
// La funzione `test_s1()` invoca `modifica1()` con un valore (1) passato come parametro attuale della funzione
void test_s1(void) {
    int s1=1;
    modifica1(s1);
    std::cout << "s1=" << s1 << std::endl;
    //QUIZ: che valori stampa?
}

// La funzione `test_s2()` modifica una variabile globale
int s2;
void test_s2() {
    s2=1;
    modifica2();
    std::cout << "s2=" << s2 << std::endl;
    //QUIZ: che valori stampa?
}
void modifica2() {
    s2++;
}
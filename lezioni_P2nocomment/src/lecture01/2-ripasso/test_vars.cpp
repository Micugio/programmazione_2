#include <iostream>
void f();
void g();
void modifica2();
int vg;
int x=5;
void test_vars() {
    std::cout << "x in main " << x << std::endl;
    f();
    std::cout << "x in main " << x << std::endl;
}
void f() {
    x=x+1;
    std::cout << "x in f " << x << std::endl;
    return;
}
void test_vars2() {
    x = 5;
    int b=6;
    printf("(x,b) in main: (%d,%d) -",x,b);
    g();
    printf("(x,b) in main: (%d,%d)\n",x,b);
}
void g() {
    int x=2, b=4;
    printf("(x,b) in g: (%d,%d) -",x,b);
    return;
}
void modifica1(int s1) {
    s1++;
}
void test_s1(void) {
    int s1=1;
    modifica1(s1);
    std::cout << "s1=" << s1 << std::endl;
}
int s2;
void test_s2() {
    s2=1;
    modifica2();
    std::cout << "s2=" << s2 << std::endl;
}
void modifica2() {
    s2++;
}
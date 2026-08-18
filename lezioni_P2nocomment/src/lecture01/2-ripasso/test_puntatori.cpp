#include <iostream>
void incrementa_ptr(int* px);
void test_puntatori() {
    int x,b,c,d;
    int* px, * pb;
    px=&x;
    pb=&b;
    x=1;
    b=2;
    c=x+b;
    d=*px + *pb;
    std::cout << x << " " << b   << " " << c << std::endl;
    std::cout << x << " " << *pb << " " << d << std::endl;
}
void test_puntatori2() {
    int x=1;
    incrementa_ptr(&x);
    std::cout << x << std::endl;
}
void incrementa_ptr(int* px) {
    *px=*px+1;
}
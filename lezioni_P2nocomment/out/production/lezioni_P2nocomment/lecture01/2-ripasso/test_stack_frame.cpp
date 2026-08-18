#include <iostream>
int fact(int n);
int somma(int x, int b);
int prodotto(int b, int x);
void test_stack_frame() {
    int x = 2, b = 4, res = 0;
    res=prodotto(x,b);
    std::cout << x << " * " << b << " = " << res << "\n";
}
int prodotto(int b, int x) {
    int res=0;
    for (int k=0; k<b; k++)
        res=somma(res,x);
    return res;
}
int somma(int x, int b) {
    int res;
    res=x+b;
    return res;
}
int test_stack_frame_rec(){
    int n = 6;
    std::cout << "Il suo fattoriale vale " << fact(n) << "\n";
    return 0;
}
int fact(int n) {
    if (n==0) return 1;
    else return n*fact(n-1);
}
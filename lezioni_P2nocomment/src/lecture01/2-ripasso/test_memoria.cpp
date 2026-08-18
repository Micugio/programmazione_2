#include <iostream>
void test_mem_static() {
    int x;
    std::cout << x << std::endl;
    x=3;
    std::cout << x << std::endl;
}
void test_mem_dyn() {
    int* px;
    px=new int;
    std::cout << *px << std::endl;
    *px=3;
    std::cout << *px << std::endl;
    delete(px);
    std::cout << *px << std::endl;
}
#include "test_vars.cpp"
#include "test_stack_frame.cpp"
#include "test_puntatori.cpp"
#include "test_memoria.cpp"
int main() {
    int r = test_stack_frame_rec();
    test_mem_dyn();
}
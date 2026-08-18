#include <iostream>
using namespace std;
class Player {
    int vita = 10;
public:
    char name = 's';
    void damage(){
        this->vita -= 5;
    }
};
int main() {
    Player* p1 = new Player();
    Player* p2 = new Player();
    p1->name = 'r';
    int* bo = (int*)p1;
    cout << "p1: vita " << *bo << "\n";
    bo++;
    cout << "p1: name " << *bo << "\n";
    bo++;
    cout << "?? " << *bo << "\n";
    bo++;
    cout << "?? " << *bo << "\n";
    bo++;
    cout << "p2: vita " << *bo << "\n";
    p2->damage();
    cout << "p2: vita " << *bo << "\n";
    bo++;
    cout << "p2: name " << *bo << "\n";
}
#include <iostream>
#include <conio.h>
#include <stdlib.h>
using namespace std;

class VectorA{
    int tab1 [10], tab2[10];
public:
    void load();
    int compare();

};

void VectorA::load(){
    for( int i=0; i<10; i++ ){
        cout<< "\n Input element(" << i << ") tab1: " ;
        cin>> tab1 [i];

}
    for (int i=0; i<10;i++) {
        cout<< "\n input element (" << i<< ") tab2: ";
        cin>> tab2 [i];}

}

int VectorA::compare() {
    int result=0;

    for (int i=0; i< 10; i++ ){
        if (tab1 [i] != tab2 [i]){
            cout << " The arrays are not the same. ";}
            else
                cout << " The arrays are the same. ";
    }

    system ("pause");

}


int main(){

VectorA d;
d.load();
d.compare();

return 0;

}

#include <iostream>
#include <windows.h>
#include <string.h>
using namespace std;

int main(){
int numeros[5];

cout<< "introduce los numeros: \n ";
for (int i=0; i<5; i++){
    cin>> numeros [i];}



     cout<< "los numeros al reves son: \n";

     for(int i = 5; i>=0; i--){
        cout<< numeros[i] << "\n ";
     }

     return 0;
}

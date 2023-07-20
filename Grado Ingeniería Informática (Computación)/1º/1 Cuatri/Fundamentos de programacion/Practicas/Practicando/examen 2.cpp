//segundo, Tabla con cinco estructuras formadas por el nombre del producto y su precio, rellenabas y pedias una palabra.
//si esa palabra esta, saca su precio.

#include <iostream>
#include <windows.h>
#include <conio.h>
#include <string.h>
using namespace std;
typedef char cadena[30];

struct pepe {
cadena nombre;
float precio;

};

class frutas {
struct pepe tabla[3];

public:
    void cargar();
    void encontrar();

};


void frutas::cargar(){

for (int i=0; i<3; i++){
    cout<< "Input the name of the fruit " << i << ": ";
    cin>> tabla[i].nombre;

    cout<< "Input the prize of the fruit " << i << ": ";
    cin>> tabla[i].precio;

}
system("cls");
}


void frutas::encontrar(){
int i=0;
cadena comprobar;
bool comp=false;

cout<< "Input the name of the fruit you want to look for: \n";
cin>> comprobar;

while (i<3 && !comp){
    if (strcmp(comprobar, tabla[i].nombre)==0)
        comp=true;
    else
        i++;

}



if (comp){
    cout<< "The fruit " << comprobar << " is in the array and its prize is: " << tabla[i].precio << " Euros/kg";}
    else
        cout << "The fruit " << comprobar << " is not in the array ";


}


int main(){
frutas f;
f.cargar();
f.encontrar();

return 0;
}

#include <iostream>
#include <windows.h>
using namespace std;

#define M 10
class uno {
int tabla[M];
public:
void cargar();
//Rellena la tabla con valores enteros leídos desde teclado
int maximo();
//Devuelve el valor máximo almacenado en la tabla
int minimo();
//Devuelve el valor mínimo almacenado en la tabla
};


void uno::cargar(){

cout << "Rellene la tabla: \n";

for (int i=0; i<M ; i++){
    cin>> tabla[i];

}


}


int uno::maximo(){
int maxim=-9999;

for (int i=0;i<M;i++){
    if(tabla[i]>maxim)
        maxim=tabla[i];

}

cout << "Max number is: "<< maxim;
}

int uno::minimo(){
int minim=9999;

for (int i=0;i<M;i++){
    if(tabla[i]<minim)
        minim=tabla[i];

}
cout << "\n Min mumber is: " << minim;

}


int main(){
uno u;
u.cargar();
u.maximo();
u.minimo();

return 0;

}

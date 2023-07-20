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

for (int i=0; i<M ; i++){
    cout<< "Input the element number " << i << ": ";
    cin>> tabla[i];

}

}

int uno::maximo(){
int maxim=-999999;

for(int i=0; i<M; i++){
    if (tabla[i]>maxim)
        maxim=tabla[i];

}

return maxim;
}






int uno::minimo(){
int minim=999999;


}


int main(){
uno u;
u.cargar();
cout<< "\nThe maximum is: " << u.maximo();
cout<< "\nThe minimum is: " << u.minimo();
}

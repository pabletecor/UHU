// Hacer una tabla [3][4] y ordenarlo para que salga en forma de tabla. numeros entre 0 y 50
//pedir un numero, y si en la tabla habia un multiplo del numero que tu has metido tienes que decir su posicion.
//Almacenar numero de multiplos en un atributo

#include <iostream>
#include <windows.h>
using namespace std;

class paquito{
int tabla [3][4];
int mult;

public:
    void cargar();
    void encontrar();
    int multiplos();

};


void paquito::cargar(){

cout<< "Input the numbers of the array: \n ";


for (int i=0; i<3;i++){
    for (int j=0;j<4;j++){
    do{
        cout<< "\n Input number [" << i << "] [" << j << "] : " ;
        cin>> tabla[i][j];}

        while (tabla[i][j] <0 || tabla [i][j] >50 );
    }

}


}

void paquito::encontrar(){
int pedirnum;
int i=0;
int j=0;
bool encontrado = false;

system("cls");
cout<<" Introduzca el numero a buscar: ";
cin>> pedirnum;

while (i<3  && j<4 && !encontrado){


    if(tabla[i][j]%pedirnum==0)
        encontrado=true;
    else
        j++;



        if (!encontrado);
        i++;
}

if (encontrado)
cout<< "El numero " << pedirnum << " tiene un multiplo en la posicion [" << i << "] [" << j << "]";
else
    cout<< "El numero no tiene multiplos en la tabla ";
}

int main(){
paquito p;
p.cargar();
p.encontrar();


return 0;
}

#include <iostream>
using namespace std;

class merged {
int uno[15], dos[15], fus[30];
int numuno; //Número de elementos almacenados en la tabla uno
int numdos;//Número de elementos almacenados en la tabla dos
public:
void cargar ();
/*Preguntará al usuario cuantos elementos va a poner, colocando este valor en numuno, a continuación solicitará tantos elementos como numuno, almacenándolos en la tabla uno.
Preguntará al usuario cuantos elementos va a poner, colocando este valor en numdos, a continuación solicitará tantos elementos como numdos, almacenándolos en la tabla dos.
Los valores es preciso ponerlos desde teclado ordenados crecientemente para cada tabla. */
void mezclar ();
//Cargará la tabla fus con los valores de las tablas uno y dos
//quedando la tabla fus ordenada crecientemente.
void ver();
//Visualizará por pantalla el contenido de las tablas uno y
//dos.
void verfusion();
//Visualizará por pantalla el contenido de la tabla fus.
};

void merged::cargar(){

cout<< "Cuantos elementos tendra la tabla 1? ";
cin>> numuno;

for (int i=0 ; i<numuno; i++ ){
    cout<< "\n Introduzca el valor " << i << ": " ;
        cin>> uno[i];
    }

cout<< "Cuantos elementos tendra la tabla 2? ";
cin>> numdos;

    for (int j=0 ; j<numdos; j++ ){
    cout<< "\n Introduzca el valor " << j << ": " ;
        cin>> dos[j];
    }

}


void merged::mezclar(){
int i=0 , j=0 , k=0 ;

while (i < numuno || j< numdos ) {
    if (uno[i] < dos [j] || j == numdos ) { //second array element is greater

        fus[k]=uno[i] ;
        i++;
        k++;
    }
        else if (dos [j] < uno [i] || i == numuno ) { //first array element is greater

            fus [k] = dos [j];
            j++;
            k++;
        }

}

}


void merged::ver(){

cout<< "La tabla numero 1 es: \n " ;
for (int i=0; i < numuno; i++){
    cout<< uno[i] << "\n" ;

}

cout<< "La tabla numero 2 es: \n " ;
for (int j=0; j < numdos; j++){
    cout<< dos[j] << "\n" ;

}

}


void merged::verfusion(){
int numfus;
numfus=numuno+numdos;
cout<< " La tabla fusionada es \n: ";


for(int k=0 ; k <numfus; k++){

    cout << fus[k] << "\n";
}


}

int main(){
merged m;
m.cargar();
m.mezclar();
m.ver();
m.verfusion();

return 0;

}

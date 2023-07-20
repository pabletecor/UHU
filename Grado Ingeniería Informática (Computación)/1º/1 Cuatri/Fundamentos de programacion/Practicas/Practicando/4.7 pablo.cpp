#include <iostream>
#include <windows.h>
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

cout<< "Input the number of elements of tab1: \n";
cin>> numuno;

for (int i=0; i< numuno; i++){
    cout << "Input number " << i << ": \n";
    cin>> uno[i];
}

cout<< "Input the number of elements of tab2: \n";
cin>> numdos;

for (int j=0; j< numdos; j++){
    cout << "Input number " << j  << ": \n";
    cin>> dos[j];

}

}

void merged::mezclar(){
int i=0, j=0, k=0;

while ( k<(numuno+numdos) ){
    if(uno[i]<dos[j] || j==numdos){
        fus[k]=uno[i];
        i++;
        k++;}
    else
        if (dos[j]<uno[i] || i==numuno){
        fus[k]=dos[j];
    j++;
    k++;
        }
}

}


void merged::ver(){
cout<< "The first array is: \n";

for (int i=0; i<numuno; i++)
    cout << uno[i] << "\t \n";

cout<< "The second array is: \n";

for (int j=0; j<numdos; j++)
    cout << dos[j] << "\t \n";



}


void merged::verfusion(){

int sumnum=numuno+numdos;

for (int i=0; i<sumnum; i++){
    cout<< "The merged array is: " << fus[i]<< "\n ";


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

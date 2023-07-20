#include <iostream>
#include <windows.h>
using namespace std;


class merged {
int uno[15], dos[15], fus[30];
int numuno; //Número de elementos almacenados en la tabla uno
int numdos;//Número de elementos almacenados en la tabla dos
public:
void cargar ();
/*Preguntará al usuario cuantos elementos va a poner, colocando este valor en numuno,
a continuación solicitará tantos elementos como numuno, almacenándolos en la tabla uno.

Preguntará al usuario cuantos elementos va a poner, colocando este valor en numdos,
a continuación solicitará tantos elementos como numdos, almacenándolos en la tabla dos.

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

cout<< "Input the number of elements you want tab1 to have: \n";
cin>> numuno;

for (int i=0; i< numuno; i++){
    cout<< "Input number " << i<<  " of tab1: \n"  ;
    cin>> uno[i];
}
system("cls");
cout<< "Input the number of elements you want tab2 to have: ";
cin>> numdos;

for (int i=0; i< numdos; i++){
    cout<< "Input number " << i << " of tab2: \n" ;
    cin>> dos[i];
}

}


void merged::mezclar(){
int i=0;
int j=0;
int k=0;
int numfus=numuno+numdos;

while(k<numfus){
    if (uno[i]<dos[j] || j==numdos ){
        fus[k]=uno[i];

i++;
k++;}

    else
        if (dos[j]<uno[i] || i==numuno){
        fus[k]=dos[j];

j++;
k++;}
}

}

void merged::ver(){
cout<< "The elements of the first array are: \n";
for (int i=0;i<numuno;i++){
    cout<< "\n " << uno[i] << "\n";

}
cout<< "The elements of the second array are: \n";
for (int i=0;i<numdos;i++){
    cout<< "\n" << dos[i] << "\n";

}

}


void merged::verfusion(){
int numfus= numuno+numdos;
cout<< "\n The elements of the merged array are: \n";
for (int i=0;i<numfus;i++){
    cout<< "\n" << fus[i] << "\n";

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

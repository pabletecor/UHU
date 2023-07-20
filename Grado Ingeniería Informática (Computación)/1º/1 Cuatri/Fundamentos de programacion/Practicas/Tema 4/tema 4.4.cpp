#include <iostream>
#include <string.h>
#include <windows.h>
using namespace std;
#define M 3
#define N 4
typedef char cadena[30];
class cuatro {
cadena tabla[M][N];
public:
void cargar();
//Pondrá en cada elemento de la tabla una palabra leída desde
//teclado.
void encontrar();
//Pedirá una palabra por teclado y mostrará por pantalla si esa
//palabra está o no en la tabla y en qué fila y columna se
//encuentra
};


void cuatro::cargar(){
cout << "Input the words: \n ";

for (int i=0; i<M ; i++) {
    for (int j=0; j<N; j++ ) {
        cin>> tabla[i][j];

    }
}

cout<< "\n\t\t\t Saving"; Sleep(1000); cout<< ".";Sleep(1000); cout<< ".";Sleep(1000); cout<< "."; Sleep(1000);

}

void cuatro::encontrar() {
cadena palabra ;
bool encontrada=false;
int i =0;
int j=0;
system("cls");
cout<< "Input a word: ";
cin>> palabra;

while (i<M && !encontrada ){
    while (j<N && !encontrada) {
        if (strcmp(palabra,tabla[i][j])==0){
            encontrada=true;
        }
        else
            j++;
    }
i++;
}

if (encontrada)
    cout<< "The word " << palabra << " is in the position [ " << i << " ] [ "<< j << " ]" ;

    else
        cout<< "The word " << palabra << " is not in the array ";

}

int main (){
cuatro c;
c.cargar();
c.encontrar();

return 0;

}

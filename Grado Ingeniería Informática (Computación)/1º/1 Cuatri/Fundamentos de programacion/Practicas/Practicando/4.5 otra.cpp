#include <iostream>
#include <windows.h>
using namespace std;
#define M 2
#define N 2
typedef char cadena[30];

struct persona {
int dni; //DNI de la persona sin letra
cadena nombre; //Nombre de la persona
};

class matrices {
persona tabla[M][N];
public:
void cargar();
//Pondrá en cada elemento de la tabla un dni (sin letra) y un
//nombre leídos desde teclado.
void encontrar();
//Pedirá un dni por teclado y mostrará por pantalla si ese dni
//está o no en la tabla y además en el caso de que esté
//mostrará el nombre de esa persona.
};


void matrices::cargar(){

for (int i=0; i<M; i++){
    for (int j=0; j<N; j++){

        cout<< "\nInput the Id number [" << i << "] ["<< j <<"]: " ;
        cin>> tabla[i][j].dni;

        cout<< "\nInput the name [" << i <<"] [" << j << "]: " ;
        cin>> tabla[i][j].nombre;
    }
}


}


void matrices::encontrar(){
int buscar, i=0, j=0;
bool encontrado=false;
system("cls");

cout<< "Input an ID for its search: ";
cin>> buscar;

while (i<M && !encontrado){
        while(j<N && !encontrado){

    if (buscar==tabla[i][j].dni){
        encontrado = true;
    }
    else
     j++;
}
if (!encontrado)
i++;
}
if (encontrado)
    cout<< "The ID " << tabla[i][j].dni << " belongs to " << tabla[i][j].nombre;
    else
        cout<< "A xuparla";

}


int main(){

matrices m;
m.cargar();
m.encontrar();
}

#include <iostream>
#include <windows.h>
#define M 4
#define N 2
using namespace std;
typedef char cadena[30];
struct persona {
int dni; //DNI de la persona sin letra
cadena nombre; //Nombre de la persona
};
class matrices {
persona tabla[M][N];
public:
void cargar();
//Pondr� en cada elemento de la tabla un dni (sin letra) y un
//nombre le�dos desde teclado.
void encontrar();
//Pedir� un dni por teclado y mostrar� por pantalla si ese dni
//est� o no en la tabla y adem�s en el caso de que est�
//mostrar� el nombre de esa persona.
};

void matrices::cargar(){

for (int i=0; i <M; i++ ){
    for (int j=0; j<N; j++){

        cout<< "Input the ID of the person: ";
        cin>> tabla[i][j].dni;

        cout<< "Input the Name of that person: ";
        cin>> tabla[i][j].nombre;

    }
}

system("cls");
}

void matrices::encontrar() {
int dnibuscar;
int i=0;
int j=0;
bool encontrado=false;
cout<< "Input the ID of the person you are looking for: ";
cin>> dnibuscar;

while(i<M && !encontrado){
        while( j<N && !encontrado ){

        if (tabla[i][j].dni==dnibuscar)
                encontrado=true;


        else
          j++;

    }
   if(!encontrado)
        i++;
}
    if(encontrado)
        cout<< "The ID " << dnibuscar << " Belongs to " << tabla[i][j].nombre;

    else
        cout<< "The ID is not in the array ";
}


int main(){
matrices m;
m.cargar();
m.encontrar();

return 0;


}

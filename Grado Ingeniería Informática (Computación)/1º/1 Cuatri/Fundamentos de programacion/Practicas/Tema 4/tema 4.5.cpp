#include <iostream>
#include <string.h>
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
//Pondr� en cada elemento de la tabla un dni (sin letra) y un
//nombre le�dos desde teclado.
void encontrar();
//Pedir� un dni por teclado y mostrar� por pantalla si ese dni
//est� o no en la tabla y adem�s en el caso de que est�
//mostrar� el nombre de esa persona.
};


void matrices::cargar(){
for (int i=0 ; i<M ; i++){
 for (int j=0 ; j<N ; j++){
        cout << "Input the id of the person: " ;
            cin>> tabla[i][j].dni;
            cout<< "Input the name of the person: ";
            cin>> tabla[i][j].nombre;}


}

}

void matrices::encontrar(){
int pedirdni, i=0,j=0 ;
bool encontrado= false;

cout<< " Please, input an id for its search: ";
cin>> pedirdni ;

while (i<M && !encontrado ) { // ! es el operador l�gico not

    while (j<N && !encontrado) {

        if (tabla[i][j].dni ==pedirdni)
        encontrado = true;
    else
        j++;
    }
    if (!encontrado)
        i++;
}
    if (encontrado)
        cout << "Se encontro la tabla y su nombre es: " << tabla[i][j].nombre;
    else
        cout << "No se encontro.";

}

int main () {
matrices m;
m.cargar();
m.encontrar();

return 0 ;
}

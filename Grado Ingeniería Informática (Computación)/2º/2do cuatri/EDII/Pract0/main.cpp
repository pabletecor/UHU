#include <iostream>
#include "MultiConjunto.h"
#include "Persona.h"
using namespace std;

int main(int argc, char *argv[])
{
    Multiconjunto<char> mccchar;
    Multiconjunto<Persona> mcpers;

    Persona P1("Manuel", 19);
    Persona P2("Pablo", 20);
    Persona P3("Matusalen", 600);

    mcpers.anade(P1);
    mcpers.anade(P2);
    cout<< "El multiconjunto tiene " << mcpers.cardinalidad() << " personas\n";
    mcpers.elimina(P2);
    cout<< "Hemos eliminado uno. ahora hay "<< mcpers.cardinalidad() << " personas\n";
    if(mcpers.pertenece(P3))
        cout<< "P3 pertenece al multiconjunto\n";
    else
        cout<< "P3 no pertenece al multiconjunto\n";
    mcpers.elimina(P1);

    if(mcpers.esVacio())
        cout<< "El multiconjunto esta vacio\n";
    else
        cout << "Algo va mal\n";

    return 0;
}

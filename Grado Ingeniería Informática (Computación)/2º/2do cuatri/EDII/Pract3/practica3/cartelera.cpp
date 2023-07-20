#include "cartelera.h"
#include <iostream>

using namespace std;

Cartelera::Cartelera(): espectaculos()
{
    //Constructor vacio
}

void Cartelera::insertaEspectaculo(const string& e)
{
    espectaculos.insert(PEspectaculos(e,DSalas()));

}

void Cartelera::insertaSala(const string& e, const string& s, const string& c)
{
    espectaculos[e].insert(PSalas(s,c));
}

void Cartelera::eliminaEspectaculo(const string& e)
{
    espectaculos.erase(e);

}

void Cartelera::eliminaSala(const string& e, const string& s)
{
    DEspectaculos::iterator it = espectaculos.find(e);
    if(it != espectaculos.end())
        it->second.erase(s);

}

void Cartelera::listaEspectaculos()
{

    if(espectaculos.empty()){
     cout<< "No hay espectaculos";

    }
    else{
    cout<< "-- Lista de espectaculos --\n";

    for (DEspectaculos::iterator it = espectaculos.begin(); it != espectaculos.end();it++){

        cout<< it->first<<"\n";

    }
    }

}

void Cartelera::listaSalas(const string& e)
{
    cout<< "-- lista de salas --\n";

    DEspectaculos::iterator ite = espectaculos.find(e);

        for(DSalas::iterator its = ite->second.begin();its!=ite->second.end();its++ ){
        cout<< its->first << endl;


    }


}


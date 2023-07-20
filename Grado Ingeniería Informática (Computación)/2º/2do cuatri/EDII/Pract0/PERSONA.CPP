#include "Persona.h"
#include <string.h>

    Persona::Persona(const string& n , int e ){
    this->nombre = n;
    this->edad = e;

    }


     const string& Persona::getNombre() const{

     return this->nombre;

     }

    int Persona::getEdad() const{

    return this->edad;
    }

    void Persona::setNombre(const string& n){
    this->nombre = n;
    }

    void Persona::setEdad(int e){
    this->edad = e;
    }

    bool Persona::operator==(const Persona& p) const{
    bool igual= false;

    if(this->nombre == p.getNombre() && this->edad == p.getEdad())
        igual = true;

    return igual;

    }

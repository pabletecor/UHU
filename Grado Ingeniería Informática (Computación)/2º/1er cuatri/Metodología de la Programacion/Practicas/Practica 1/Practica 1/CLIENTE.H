#ifndef CLIENTE_H
#define CLIENTE_H
#include "fecha.h"
#include <iostream>
#include <string.h>
#include <stdio.h>
using namespace std;

class cliente {
long int dni;   //Si fuera const se incializa en el constructor
char *nombre;
fecha fechaAlta;

public:

    cliente(long int d,char *nom,fecha f);
    virtual~cliente();
    void setnombre(char *nom);
    fecha getfecha() const;
    void setfecha(fecha f);
    long int getDNI()const ;
    const char* getnombre()const ; //Const al principio y al final porque es puntero (*const)
    //operators
    cliente& operator= (const cliente& c);
    bool operator== (cliente c);
    friend bool operator > (const cliente &c1, const cliente &c2);

};

ostream& operator << (ostream &s, const cliente &c);  //No queremos que sea amiga
bool operator > (const cliente &c1, const cliente &c2);
#endif // CLIENTE_H

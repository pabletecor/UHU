#ifndef FECHA_H
#define FECHA_H
#include <iostream>
using namespace std;

class fecha{
int dia,mes,anio;
public:
    fecha(int dia,int mes,int anio);
    void setfecha(int d,int m,int a);
    int getdia()const {return this->dia;}
    int getmes()const {return this->mes;}
    int getanio()const {return this ->anio;}
    bool bisiesto()const;
    void ver()const;
//Operators
    fecha operator++();       //++f
    fecha operator++(int i);  //f++
    fecha operator+ (int i) const;  //for(int i=1 ; i<54;i++) dia ++                            ¡¡¡ESTE SERÍA fecha + i!!!

    friend fecha operator+ (int i, const fecha &f); //const por seguridad y & por velocidad     ¡¡¡ESTE SERÍA i + fecha!!!
    friend bool operator>(const fecha f1, const fecha f2);
};

fecha operator+ (int i, const fecha &f); //Una funcion no puede ser const, los const son solo de los metodos
ostream& operator << (ostream &j, const fecha &f);
bool operator>(const fecha f1, const fecha f2);
#endif // FECHA_H

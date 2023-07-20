using namespace std;
#include <ctime>  //para time
#include <cstdlib> // para srand, rand
#include "ConjuntoInt.h"
#include <iostream>
#include <string>
#include <fstream>
#include <iomanip>
#include <windows.h>

ConjuntoInt::ConjuntoInt (int max)
{
 tamano= max;
 datos= new int[max];
}
ConjuntoInt::~ConjuntoInt ()
{
 delete[] datos;
}
void ConjuntoInt::vaciar ()
{
 tamano= 0;
}
int* ConjuntoInt::getDatos()
{
	int* v=datos;
	for (int i= 0; i <tamano;i++){
		v[i]=datos[i];}
	return v;
}

void ConjuntoInt::GeneraVector (int tam)
{
 tamano=tam;
 srand( (unsigned)time( NULL ) ); //srand(time(0));
 for (int i= 0; i<tamano; i++){
     datos[i] = rand()%1000; //genera un número aleatorio entre 1 y 999
 }
}

int ConjuntoInt::generaKey()
{
    return datos[rand() % tamano]; // devuelve el valor del  array en una posición aleatoria entre 1 y el tamaño del array
}

void ConjuntoInt::escribe()
{
 for (int i= 0; i<tamano; i++)
     cout << datos[i] << (i<tamano-1? ", ": "\n");
}

void ConjuntoInt::Clonar (int *v, int tam)
{
    tamano=tam;
    for (int i=0; i<tamano; i++){
        datos[i] = v[i];
    }
}
//examenjunio21
int ConjuntoInt::generaKey_Menor()
{
    return rand() % tamano;
}

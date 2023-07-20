#include <iostream>
#include <stdlib.h>
#include <windows.h>
#include <fstream>
#define SALTO 4
using namespace std;

struct Corredor {
int indice;
int dorsal;
int marca;
};

class Clasificacion {
Corredor *elementos; //elementos de la tabla
int corredores;
int tamano;
public:
Clasificacion();
~Clasificacion();
void anadircorredor(Corredor a);
void eliminar(int i);
Corredor consultar(int i);
int numcorredores(){return corredores;}
bool vacio();
void OrdenacionBurbuja();
};

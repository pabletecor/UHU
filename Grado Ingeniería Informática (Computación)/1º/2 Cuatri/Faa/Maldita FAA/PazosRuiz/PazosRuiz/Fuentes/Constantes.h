/* 
 *Definiciones de las Constantes para la pr�ctica 2
 */
#ifndef CONSTANTES_H_INCLUDED
#define CONSTANTES_H_INCLUDED
#include <iostream>
#include <string>
#include <fstream>
#include <iomanip>
#include <iostream>
#include <windows.h>

/* Constantes simb�licas para indicar el metodo de ordenacion*/
enum algoritmosO {BURBUJA, INSERCION, SELECCION};
enum algoritmosB {SECIT, BINIT, ITEIT,KED,KEIT};
/* Constantes para indicar el Orden del metodo de ordenacion*/
enum ordenes {CUADRADO, NlogN, N, LOGN, LOGLOGN};
/* Constantes para indicar el Numero de repeticiones para el caso medio de cada m�todo de b�squeda */
static const int NUMREPETICIONES=100;
/* Constantes para indicar la variacion de las tallas del vector */
enum valoresTallas { tallaIni = 100,tallaFin = 1000,incTalla = 100};

#endif // CONSTANTES_H_INCLUDED

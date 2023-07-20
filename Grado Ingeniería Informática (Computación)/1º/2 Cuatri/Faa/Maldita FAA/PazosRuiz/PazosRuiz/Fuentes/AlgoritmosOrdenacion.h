/*
 * Clase AlgoritmosOrdenacion que define los Algoritmos de Ordenaci�n para ordenar un vector de enteros en orden descendente.
 * Define las implementaciones de los siguientes m�todos de Ordenaci�n en vectores:
 *	- Burbuja
 *	- Inserci�n
 *	- Selecci�n.
 */
#ifndef ALGORITMOSORDENACION_H
#define ALGORITMOSORDENACION_H
#include <iostream>
#include <string>
#include <fstream>
#include <iomanip>
#include <iostream>
#include <windows.h>

/*
 * declaraci�n de la clase AlgoritmosOrdenacion
 */
class AlgoritmosOrdenacion
{
public:
    AlgoritmosOrdenacion();
    ~AlgoritmosOrdenacion();

	/*
	 * Funci�n ordenaBurbuja, implementa el m�todo de ordenaci�n Burbuja
	 * param v: el array de enteros a ordenar
	 * param size: tama�o del array de enteros a ordenar
	 */
   void ordenaBurbuja(int v[], int size);

	/*
	 * Funci�n ordenaInsercion, implementa el m�todo de ordenaci�n por Inserci�n
	 * param v: el array de enteros a ordenar
	 * param size: tama�o del array de enteros a ordenar
	 */
    void ordenaInsercion(int v[], int size);

	/*
	 * Funci�n ordenaSeleccion, implementa el m�todo de ordenaci�n por Selecci�n
	 * param v: el array de enteros a ordenar
	 * param size: tama�o del array de enteros a ordenar
	 */
    void ordenaSeleccion(int v[], int size);
};

#endif // ALGORITMOSORDENACION_H

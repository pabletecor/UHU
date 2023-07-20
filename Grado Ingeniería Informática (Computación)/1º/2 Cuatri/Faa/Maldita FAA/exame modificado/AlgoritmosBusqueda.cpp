
/*
 * Clase AlgoritmosBusqueda que implementa los Algoritmos de Búsqueda para buscar un elemento en un vector de enteros.
 * Define las implementaciones iterativas de los siguientes métodos de búsqueda en vectores de enteros en orden creciente:
 *	- Secuencial
 *	- Binaria o dicotómica
 *  - Interpolacion
 */

#include "AlgoritmosBusqueda.h"
#include "AlgoritmosOrdenacion.h"
#include "ConjuntoInt.h"

/*
 * Implementación de los métodos de la clase AlgoritmosBusqueda
 */
AlgoritmosBusqueda::AlgoritmosBusqueda(){}
AlgoritmosBusqueda:: ~AlgoritmosBusqueda(){}

/*
	 * Función busquedaSecuencialIt, implementa el método de búsqueda secuencial Iterativo
	 * param v: el array de enteros donde buscar
	 * param size: tamaño del array
	 * param key: clave o elemento a buscar
	 * return posición de la clave en el array
	 */
int AlgoritmosBusqueda::busquedaSecuencialIt(int v[], int size,int key)
{
   //** ESCRIBIR PARA COMPLETAR LA PRACTICA **//
	int i = 0;

	while (v[i] != key && i < size)
	{
		i = i + 1;
	}
	if (v[i] == key)
		return i;
	else
		return -1;
}

/*
	 * Función busquedaBinariaIt, implementa el método de búsqueda binaria Iterativa
	 * param v: el array de enteros donde buscar
	 * param size: tamaño del array
	 * param key: clave o elemento a buscar
	 * return posición de la clave en el array
	 */
int AlgoritmosBusqueda::busquedaBinariaIt(int v[], int size,int key)
{
	//** ESCRIBIR PARA COMPLETAR LA PRACTICA **//
	bool encontrado;
	int mitad, primero, ultimo;

	primero = 1;
	ultimo = size;
	encontrado = false;

	while ((primero <= ultimo) && !encontrado)
	{
		mitad = (primero + ultimo) / 2;

		if (key == v[mitad])
			encontrado = true;
		else 
			if (key < v[mitad])
				ultimo = mitad - 1;
			else if (key > v[mitad])
				primero = mitad + 1;
	}

	if (encontrado == true)
		return mitad;
	else
		return mitad - 1;
}

/*
 * Función busquedaInterpolacionIt, implementa el método de búsqueda por Interpolacion Iterativa
 * param v: el array de enteros donde buscar
 * param size: tamaño del array
 * param key: clave o elemento a buscar
 * return posición de la clave en el array
 */	 
int AlgoritmosBusqueda::busquedaInterpolacionIt(int v[], int size,int key)
{
	//** ESCRIBIR PARA COMPLETAR LA PRACTICA **//
	int p, primero, ultimo;
	primero = 0;
	ultimo = size-1;

	while ((v[ultimo] >= key) && (v[primero] < key))
	{
		p = primero + ((ultimo - primero) * (key - v[primero]) / (v[ultimo] - v[primero]));
		if (key > v[p])
			primero = p + 1;
		else
			if (key < v[p])
				ultimo = p - 1;
			else
				primero = p;
	}

	if (v[primero] == key)
		return primero;
	else
		return -1;
}
//***EXJUNIO21***
int AlgoritmosBusqueda::busquedaKesimoMenorD(int A[], int talla, int k)
{
	AlgoritmosOrdenacion a;
	//ConjuntoInt c;
	a.ordenaInsercion(A, talla);
	return A[k-1];
	
}
int AlgoritmosBusqueda::busquedaKesimoMenorIt(int A[], int talla, int k)
{
	//ConjuntoInt c;
	//k = c.generaKey();
	int p = 0;
	int r = talla-1;
	while (p < r)
	{
		int q = Partition(A, p, r);
		int i = q - p + 1;
		if (k <= i)
			r = q;
		else
		{
			p = q + 1;
			k = k - i;
		}
	}
	return A[p];

}
int AlgoritmosBusqueda::Partition(int A[], int p, int r)
{
	
	
		int x = A[p];
		int i = p- 1;
		int j = r + 1;
		while(j>=i)
		{
			while (x < A[--j]);
			while (A[++i] < x);
			if (i >= j) return j;
			int temp;
			temp = A[j];
			A[j] = A[i];
			A[i] = temp;
		}
		
		/*
		int piv=A[p];
		int i=p;
		int j=r;
		while(j>=i)
		{
			while(A[j] >= piv)
			{
				j=j-1;
			}
			while(A[i] <= piv)
			{
				i=i+1;
			}
			if(i<j)
			{
				int temp = A[j];
				A[j] = A[i];
				A[i]= temp;
			}


		}
		
		
		*/
		return j;
			
	
}


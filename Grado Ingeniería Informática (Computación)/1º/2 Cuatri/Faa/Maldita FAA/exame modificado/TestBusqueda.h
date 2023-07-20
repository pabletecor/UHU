/* 
 * La clase TestBusqueda contiene los metodos para:
 * 1. Comprobar que los m�todos de b�squeda de la clase AlgoritmosBusqueda funcionan adecuadamente.
 * 2. Calcular la eficiencia para el caso medio de un m�todo de b�squeda, permitiendo guardar los 
 *    datos e imprimir la gr�fica correspondiente con ajuste al Orden de complejidad.
 * 3. Comparar el coste temporal de dos m�todos de b�squeda
 *    permitiendo guardar los datos e imprimir la gr�fica correspondiente.
 * 4. Comparar todos los algoritmos de b�squeda implementados.
 */

#ifndef _TEST_BUSQUEDA
#define _TEST_BUSQUEDA

#include <string>
#include <vector>
#include <iostream>

using namespace std;

//** ESCRIBIR PARA COMPLETAR LA PRACTICA **//

class TestBusqueda
{
	vector<string> nombreAlgoritmo;
	vector<int> complejidad;

public:
    
	TestBusqueda();
	~TestBusqueda();
	double buscaArrayDeInt(int v[], int size, int clave, int metodo, int &pos);
	void comprobarMetodosBusqueda();
	void casoMedio(int metodo1);
	void comparar(int metodo1, int metodo2);
	void compararTodos();
};

#endif
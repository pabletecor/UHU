/*
 * Clase Graficas, contiene m�todos para guardar las gr�ficas de los resultados, 
 * es decir, crea los ficheros por lo lotes (comandos) para generar los ficheros gr�ficos que corresponda. 
 */
#ifndef _GRAFICA
#define _GRAFICA
#include "Elemental.h"
class Graficas
{
public:
	/*
	* Configura los par�metros para el fichero de comandos y dibuja la gr�fica del caso medio de un m�todo de
	* ordenaci�n y su ajuste a la funci�n correspondiente.  
  * param metodo: metodo de ordenacion.
	* param metodo: orden de complejidad del metodo de ordenacion.
  */
	void 	generarGraficaMEDIO(string metodo,int orden);
	 
	/*
	* Configura los par�metros para el fichero de comandos 
	* y dibuja la gr�fica de dos m�todos de ordenaci�n.  
  * param metodo1: primer metodo de ordenacion.
	* param metodo2: segundo metodo de ordenacion.
  */
	void generarGraficaCMP(string metodo1,string metodo2);
}; 
#endif

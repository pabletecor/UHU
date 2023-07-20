#ifndef CONJUNTOINT_H
#define CONJUNTOINT_H
#include <string>
#include <fstream>
#include <iomanip>
#include <iostream>
#include <windows.h>
#include <iostream>
using namespace std;

/////////// Declaración de la clase conjuntoInt /////////////

class ConjuntoInt
{
private:
	int tamano;
	int *datos;
public:
	ConjuntoInt (int max= 0);
	~ConjuntoInt ();
	void vaciar ();
	void GeneraVector (int tam);
	int generaKey();
	int* getDatos();
	void escribe ();
	void Clonar(int *v, int tam);
	int generaKey_Menor();
};
#endif // CONJUNTOINT_H

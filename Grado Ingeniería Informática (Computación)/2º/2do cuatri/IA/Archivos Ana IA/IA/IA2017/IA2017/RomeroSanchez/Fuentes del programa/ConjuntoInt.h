#ifndef _LIB_CONJUNTOINT
#define _LIB_CONJUNTOINT

#include "Elemental.h"

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
	int getMitad();
};

#endif


#ifndef _MTIME
#define _MTIME

#include "Elemental.h"
/////////// Declaraci�n de la clase TIEMPO /////////////

class Mtime
{
public:
	/* retorna "a - b" en segundos */
	double performancecounter_diff(LARGE_INTEGER *a, LARGE_INTEGER *b);
};

#endif

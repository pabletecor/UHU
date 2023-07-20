#include <"funciones.h">

float media(int tabla[], int posiciones){
float media;

for (int i=0; i <posiciones;i++){
media+=tabla[i];

}

return media/posiciones;

}

int minimo(int tabla[], int posiciones){
int minimo = 999;

for (int i =0;i<posiciones;i++){
if (tabla[i] < minimo)
	minimo=tabla[i];

}

return minimo;

}

double coseno(int minimo){

return cos(minimo);

}

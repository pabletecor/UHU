#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <signal.h>

#include "comun.h"

//ESQUEMA DEL PROGRAMA

//Inicializar semilla ramdom
//"ACceder" servidor grafico
//Generar parada inicial
//Generar parada final
//Puntero en parada inicial


int llega10=0;

//////////////////////

void R10(){
 
llega10=1;

}

//////////////////////


void visualiza(int cola, int parada, int inout, int pintaborra, int destino){

 struct tipo_elemento peticion;

 
 peticion.tipo=2; // Tipo 2 porque es el cliente
 peticion.pid=getpid();
 peticion.parada=parada; // 0 ES EL BUS Y 7 LA ACERA. DE 1 A 6 SON LAS PARADAS NORMALES
 peticion.inout=inout;  // 1 in 0 out. LA ACERA DEBE PONERLA OUT. 
 peticion.pintaborra=pintaborra; // 1 pinta 0 borra.
 peticion.destino=destino; //parada de destino.

 msgsnd(cola,&peticion,sizeof(peticion)-sizeof(long),0);

 if(!llega10) pause();
}



/////////////////////


int main(){

 signal (10,R10	);

 int colagrafica, llega, sale;


 srand(getpid());
 colagrafica=crea_cola(ftok("fichcola.txt",18));
 
 llega=(rand()%NUMPARADAS)+1;
 sale=(rand()%NUMPARADAS)+1;
 while(llega==sale) sale = (rand()%NUMPARADAS)+1;

 visualiza(colagrafica,llega,IN,PINTAR,sale);
 sleep(3);
 visualiza(colagrafica,llega,IN,BORRAR,sale);
 visualiza(colagrafica,llega,OUT,BORRAR,sale);
 sleep(1);
 visualiza(colagrafica,0,OUT,PINTAR,sale);
 visualiza(colagrafica,7,OUT,PINTAR,sale);
 return 0;
}

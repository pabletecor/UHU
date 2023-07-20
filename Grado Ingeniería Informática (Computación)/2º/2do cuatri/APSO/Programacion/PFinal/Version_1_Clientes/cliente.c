#include <stdio.h>
#include <stdlib.h>
#include <string.h> 
#include <sys/msg.h>
#include <errno.h>
#include <signal.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>

#include <unistd.h>

#include "comun.h"

void R10();
void visualiza(int cola, int parada, int inout, int pintaborra, int destino);

int llega10=0;

/**********************************************************************************/
/************       MAIN        ***************************************************/
/**********************************************************************************/

int main()
{

 int colagrafica, llega, sale;
 
 srand(getpid());
 signal(10,R10); // me preparo para la senyal 10

 // Creamos y abrimos la cola de mensajes
 colagrafica=crea_cola(ftok ("./fichcola.txt", 18));
 
 srand(getpid());
 llega = rand()%(NUMPARADAS)+1;
 sale = rand()%(NUMPARADAS)+1;
 while(llega == sale ) sale = rand()%(NUMPARADAS)+1;

 visualiza(colagrafica, llega, IN, PINTAR, sale);
 sleep(5);
 visualiza(colagrafica, llega, IN, BORRAR, sale);
 visualiza(colagrafica, sale, OUT, PINTAR, sale);
 
 return 0;
}


/************************************************************************/
/***********   FUNCION: visualiza     ***********************************/
/************************************************************************/
// Pinta o borra en el servidor gráfico

void visualiza(int cola, int parada, int inout, int pintaborra, int destino)
{
 struct tipo_elemento peticion;

 peticion.tipo=2; //Los clientes son tipo 2, el autobus tipo 1
 peticion.pid=getpid();
 peticion.parada=parada;
 peticion.inout=inout;
 peticion.pintaborra=pintaborra;
 peticion.destino=destino;

 
 msgsnd (cola, (struct tipo_elemento *) &peticion,sizeof(struct tipo_elemento)-sizeof(long),0);
 
 if(pintaborra==PINTAR) 
 {
	if(!llega10) pause(); //espero conformidad de que me han pintado, sino me mataran
	llega10=0;
 }
}

/************************************************************************/
/***********    FUNCION: R10     ****************************************/
/************************************************************************/

void R10()
{
 llega10=1;
}


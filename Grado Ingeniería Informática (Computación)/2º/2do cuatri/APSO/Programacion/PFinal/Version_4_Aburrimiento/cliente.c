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
void R12();
void R14();
void visualiza(int cola, int parada, int inout, int pintaborra, int destino);

int llega12=0, llega14=0, llega10=0;

/**********************************************************************************/
/************       MAIN        ***************************************************/
/**********************************************************************************/

int main()
{

 int colagrafica, llega, sale, colaparada, tuberia;
 struct tipo_parada pasajero;
 char nombrefifo[10];
 int fifosalir, testigo=1;
 struct ParametrosCliente params;
 
 srand(getpid());
 signal(10,R10); // me preparo para la senyal 10
 signal(12,R12); // me preparo para la senyal 12
 signal(14,R14);

 // Creamos y abrimos la cola de mensajes
 colagrafica=crea_cola(ftok ("./fichcola.txt", 18));
 colaparada=crea_cola(ftok ("./fichcola.txt", 20));
 //Recuperar posicion 2 de la pipe
 tuberia=dup(2);
 close(2);
 open("/dev/tty",O_WRONLY);
 
 //Leemos los parametros de la tuberia
 read(tuberia, &params, sizeof(params));
 
 //Generamos parada de llegada y de bajada
 srand(getpid());
 llega = rand()%(params.numparadas)+1;
 sale = rand()%(params.numparadas)+1;
 while(llega == sale ) sale = rand()%(params.numparadas)+1;
 
 //Abre fifo de la parada de llegada
 sprintf(nombrefifo,"fifo%d",sale);
 fifosalir=open(nombrefifo,O_RDONLY);

 pasajero.tipo=llega;
 pasajero.pid=getpid();
 pasajero.destino=sale;
 //Se visualiza en la parada de bajada
 visualiza(colagrafica, llega, IN, PINTAR, sale);
 //Escribe en la cola de mensajes entre bus y cliente
 msgsnd(colaparada, (struct tipo_parada *) &pasajero,sizeof(struct tipo_parada)-sizeof(long),0);

 //Activamos la alarma
 alarm(rand()%(params.aburrimientomax-params.aburrimientomin+1)+params.aburrimientomin);

//Espera la llegada del bus (señal 12) 
if(!llega12) pause();
 if(llega12)
 {
	alarm(0);
	
	llega12=0;
	
	//Se borra de la parada
	visualiza(colagrafica, llega, IN, BORRAR, sale);
	//Se pinta en el bus
	visualiza(colagrafica, 0, 0, PINTAR, sale);
	//Espera testigo de parada de bajada
	read(fifosalir,&testigo,sizeof(testigo));
	//sleep(5);
	//Se borra del bus
	visualiza(colagrafica, 0, 0, BORRAR, sale);
	//Se visualiza en la parada de bajada	
	visualiza(colagrafica, sale, OUT, PINTAR, sale);
 }
 else //llego la 14
 {
	//Se borra
	visualiza(colagrafica, llega, IN, BORRAR, sale);
	//Se visualiza en la acera	
	visualiza(colagrafica, 7, OUT, PINTAR, sale); // La 7 es la acera
 }
 return 0;
}


/************************************************************************/
/***********   FUNCION: visualiza     ***********************************/
/************************************************************************/
// Pinta o borra en el servidor gráfico

void visualiza(int cola, int parada, int inout, int pintaborra, int destino)f
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

/************************************************************************/
/***********    FUNCION: R14     ****************************************/
/************************************************************************/

void R14()
{
	llega14=1;
}

/************************************************************************/
/***********    FUNCION: R12     ****************************************/
/************************************************************************/

void R12(){
	llega12=1;
}


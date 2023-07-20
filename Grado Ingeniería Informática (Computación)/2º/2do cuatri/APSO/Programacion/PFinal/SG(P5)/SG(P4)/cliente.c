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

int llega10=0;
int llega12=0;
int llega14=0;

/**********************************************************************************/
/************       MAIN        ***************************************************/
/**********************************************************************************/

int main()
{

 int colagrafica, llega, sale,colaparada,fifosalir,testigo,tuberia;
 char nombrefifo[10];

 struct tipo_parada {
 	long tipo;
 	int pid;
 	int destino;
 };

 struct tipo_parada pasajero;
 struct ParametrosCliente params;
 srand(getpid());
 signal(10,R10); // me preparo para la senyal 10
 signal (12,R12);
 signal (14,R14);

 // Creamos y abrimos la cola de mensajes
 colagrafica=crea_cola(ftok ("./fichcola.txt", 18));
 colaparada=crea_cola(ftok("./fichcola.txt",20));
 
 //Recuperar posicion 2 de la pipe
 tuberia = dup(2);
 close(2);
 open ("/dev/tty",O_WRONLY);

 //Leemos los parametros de la tuberia
 read (tuberia,&params,sizeof(params));

 srand(getpid());
 llega = rand()%(params.numparadas)+1;
 sale = rand()%(params.numparadas)+1;
 while(llega == sale ) sale = rand()%(params.numparadas)+1;


 //Abro la fifo para recoger testigo en la parada de salida
 sprintf(nombrefifo,"fifo%d",sale);
 fifosalir=open(nombrefifo,O_RDONLY);


 visualiza(colagrafica, llega, IN, PINTAR, sale);
 //ENVIAR MENSAJE A LA COLA DE LA PARADA
 pasajero.tipo=llega;
 pasajero.pid=getpid();
 pasajero.destino=sale;
 msgsnd(colaparada,&pasajero, sizeof(pasajero)-sizeof(long),0);
 
 //programar la alarma para el aburrimiento
 alarm(rand()%(params.aburrimientomax - params.aburrimientomin+1) + params.aburrimientomin);
 
 //ESPERAR SEÑAL 12
 if(!llega12) pause();
 if(llega12) { 
	alarm(0); //Desactivo la alarma 	
	llega12=0;
 	visualiza(colagrafica, llega, IN, BORRAR, sale);
 	visualiza(colagrafica, 0, OUT, PINTAR, sale);
 	//ESTOY MONTADO EN EL BUS
 	//leer mensaje desde la fifo de mi parada destino
 	read(fifosalir,&testigo,sizeof(testigo));

 	visualiza(colagrafica, 0, OUT, BORRAR, sale);
 	visualiza(colagrafica, sale, OUT, PINTAR, sale);
 	
	} else{ //Ha llegado la 14

 	visualiza(colagrafica, llega, IN, BORRAR, sale);
	visualiza(colagrafica, 7, OUT, PINTAR, sale); //La parada 7 es la acera
 }
 close(fifosalir);

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


/************************************************************************/
/***********    FUNCION: R10     ****************************************/
/************************************************************************/

void R12()
{
 llega12=1;
}


/************************************************************************/
/***********    FUNCION: R10     ****************************************/
/************************************************************************/

void R14()
{
 llega14=1;
}



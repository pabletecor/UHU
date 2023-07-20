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

void pinta(int cola, int parada);
void R10();
void Rfin();

int llega10=0;
int colaparada, bajarse;

/**********************************************************************************/
/************       MAIN        ***************************************************/
/**********************************************************************************/

int main()
{
 
 int colagrafica, parada, tuberia;
 struct tipo_parada pasajero;
 int libres;
 int montados[7], gente, i, testigo=1;
 struct ParametrosBus params;

 char nombrefifo[10];
 int fifos[7];
 
 srand(getpid());

 signal(10,R10); // me preparo para la senyal 10
 signal(12,Rfin); // me preparo para la senyal 12
 
 // Creamos y abrimos la cola de mensajes
 colagrafica=crea_cola(ftok ("./fichcola.txt", 18));
 colaparada=crea_cola(ftok ("./fichcola.txt", 20));
 //Recuperar la posicion 2 de la pipe
 tuberia=dup(2);
 close(2);
 open("/dev/tty",O_WRONLY);
 read(tuberia, &params, sizeof(params));
 //Indicamos la capacidad del bus
 libres=params.capacidadbus;

 //Abrimos todas las fifos
 for(i=1;i<=params.numparadas;i++)
 {
	sprintf(nombrefifo,"fifo%d",i);
	fifos[i]=open(nombrefifo,O_WRONLY);
 }

 for(i=1;i<=params.numparadas;i++) montados[i]=0;

 while(1)
 {
	for(parada=1;parada<=params.numparadas;parada++)
	{	
		//Pinta el bus en la parada
		pinta(colagrafica, parada);
		
		// Bajamos la gente de esa parada
		for(gente=1;gente<=montados[parada];gente++) 
		{
			write(fifos[parada], &testigo, sizeof(testigo));
			libres++;
			usleep(RETARDO); //para que de tiempo a pintarse
		}
		//Ya no hay nadie montado en esa parada
		montados[parada]=0;
		
		//Montamos la gente de esa parada
		while(libres>0 && (-1 != msgrcv(colaparada, (struct tipo_parada *) &pasajero,sizeof(struct tipo_parada)-sizeof(long),parada,IPC_NOWAIT)))
		{
			//montamos a los pasajeros (-1 si el pasajero se aburrio. mandamos la señal 12 para avisar de que se monte)
			if(-1 != kill(pasajero.pid,12))
			{
				libres--;
				montados[pasajero.destino]++;
				usleep(RETARDO); //para que de tiempo a pintarse
			}
		}

		//Se pinta un bus entre paradas
		if(parada==params.numparadas) pinta(colagrafica, parada*10+1);
		else pinta(colagrafica, parada*10+parada+1);
		//Se espera un tiempo de recorrido
		sleep(params.tiempotrayecto);
	}
 }
 return 0;
}


/************************************************************************/
/***********    FUNCION: pinta   ****************************************/
/************************************************************************/

//Como visualiza pero con menos parametros, al ser el bus
void pinta(int cola, int parada)
{
 struct tipo_elemento peticion;

 peticion.tipo=1; //Los clientes son tipo 2, el autobus tipo 1
 peticion.pid=getpid();
 peticion.parada=parada;
 peticion.inout=0; // no se usa
 peticion.pintaborra=0; //no se usa
 peticion.destino=0; //no se usa

 msgsnd (cola, (struct tipo_elemento *) &peticion,sizeof(struct tipo_elemento)-sizeof(long),0);
 if(!llega10) pause(); //espero conformidad de que me han pintado, sino me mataran
 llega10=0;
}

/************************************************************************/
/***********    FUNCION: R10     ****************************************/
/************************************************************************/

void R10()
{
 llega10=1;
}

/************************************************************************/
/***********    FUNCION: Rfin    ****************************************/
/************************************************************************/

void Rfin(){
 msgctl (colaparada, IPC_RMID, 0); //Borra la cola de mensajes

 exit(0);
}




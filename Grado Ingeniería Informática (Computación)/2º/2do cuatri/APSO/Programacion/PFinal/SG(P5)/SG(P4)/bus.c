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
int llega10=0,colaparada;


/**********************************************************************************/
/************       MAIN        ***************************************************/
/**********************************************************************************/


int main(){

 int  colagrafica,parada,libres, montados[7],i,gente,fifos[7],testigo;
struct ParametrosBus params;
 char nombrefifo[10];

 struct tipo_parada pasajero;

 int tuberia;

 signal (10,R10);
 signal (12,Rfin);

 //Recuperamos la posicion 2, pasando la lectura de la tuberia a "tuberia"
 tuberia=dup(2);
 close (2);
 open("/dev/tty",O_WRONLY);

 read(tuberia,&params,sizeof(params));


 libres=params.capacidadbus;

 //Creamos y abrimos la cola de mensajes
 colagrafica = crea_cola(ftok("./fichcola.txt",18));
 colaparada = crea_cola(ftok("./fichcola.txt",20));


 //Creamos y abrimos las FIFOS
 for(i=1;i<=params.numparadas;i++){

	 sprintf(nombrefifo,"fifo%d",i); //Usamos sprintf para concatenar el nombre
	 fifos[i]=open(nombrefifo,O_WRONLY); //Abrimos la fifo para lectura y escritura

	}



for(i=1;i<=6;i++)
 montados[i]=0;

 while(1) {
 
	for(parada=1;parada<=params.numparadas;parada++) {
		pinta(colagrafica,parada);

		//BAJAR GENTE QUE  VA A ESTA PARADA
		for(gente=1;gente<=montados[parada];gente++){
			//poner mensaje en la fifo de la parada
			write(fifos[parada],&testigo,sizeof(testigo));
			libres++;
			usleep(RETARDO);	//Para que de tiempo a pintarse
		}
		montados[parada]=0;
		//SUBIR GENTE QUE ESTÁ EN LA PARADA (SI HAY SITIO)
		

		while (libres >0 && -1!=msgrcv(colaparada,&pasajero,sizeof(pasajero)-sizeof(long),parada,IPC_NOWAIT))
		{
			
			if(-1!=kill(pasajero.pid,12)){			
			montados[pasajero.destino]++;
 			usleep(RETARDO);	//Para que de tiempo a pintarse
			libres--;
			}		
		}


		if(parada==params.numparadas)
			pinta(colagrafica,params.numparadas*10+1);
		else 
			pinta(colagrafica,parada*10+parada+1);
	
			sleep(params.tiempotrayecto);	
		}

 }


 return 0;

}





/************************************************************************/
/***********   FUNCION: Pinta     ***************************************/
/************************************************************************/
// Pinta o borra en el servidor gráfico

void pinta(int cola, int parada)
{

 struct tipo_elemento peticion;

 peticion.tipo=1; //Los clientes son tipo 2, el autobus tipo 1
 peticion.pid=getpid();
 peticion.parada=parada;
 peticion.inout=0; //NO ES USADO PARA EL BUS
 peticion.pintaborra=0; //NO ES USADO PARA EL BUS
 peticion.destino=0; //NO ES USADO PARA EL BUS

 
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


void Rfin()
{
 
 msgctl(colaparada,IPC_RMID,NULL);
 exit(0);

}






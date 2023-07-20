#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/wait.h>
#include <signal.h>

#include "comun.h"



int llega10=0;

//////////////////////

void R10(){
 
llega10=1;

}

//////////////////////

void R12(){

 printf("No es posible arrancar el servidor grafico");
 exit(-1);

}

//////////////////////

int creaservidorgraf(int ultimaparada){

 int vpid;
 char cadparada[10];

 sprintf(cadparada,"%d",ultimaparada); //Usamos el sprintf para convertir ultimaparada en char y pueda estar en execl

 vpid=fork();
 
 if(vpid==0){ //EStoy en la copia
 	execl("servidor_ncurses","servidor_ncurses",cadparada,NULL);
 	perror ("Error de execl");
	exit(-1);
 
 } else if (vpid==-1) {

	perror("Error de fork");
	exit(-1);	

	}

 return vpid;

}


//////////////////////////////

int creaproceso(const char nombre[]){

 int vpid;

 vpid=fork();
 
 if(vpid==0){ //EStoy en la copia
 	execl(nombre,nombre,NULL);
 	perror ("Error de execl");
	exit(-1);
 
 } else if (vpid==-1) {

	perror("Error de fork");
	exit(-1);	

	}

 return vpid;

}




int main(){

 int i;
 signal (10,R10);
 signal(12,R12);
 srand(getpid());

 
 //Creo el servidor gradico y espero la señal 10
 int pidservgraf=creaservidorgraf(NUMPARADAS);
 if(!llega10) pause();
 llega10=0;

//Genero 30 clientes a intervalos de tiempo aleatorios

 for ( i = 1;i<=10;i++) {
	creaproceso("cliente");
	sleep(rand()%5+1);

 }

 //Espero a que finalicen los clientes
 
 for(i = 1; i<=30;i++) wait(NULL);

 sleep(10);

 kill(pidservgraf,12);

 system("reset"); //Se usa para limpiar el terminal
 return 0;
}

#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <signal.h>

#include "comun.h"

void R10();
void R12();

int creaservidorgrafico();
int llega10=0;
int llega12=0;

int main(){

 int pidsg,colagraf;
 struct tipo_elemento datos;
 signal(10,R10);
 signal(12,R12);
 pidsg = creaservidorgrafico();
 if(!llega10) pause();
 llega10=0; //Despues de recibir una señal siempre la reseteo a 0 (false)

 
colagraf=crea_cola(ftok("./fichcola.txt",18));

datos.tipo=2;
datos.pid=getpid();
datos.parada=3;
datos.inout=IN;
datos.pintaborra=PINTAR;
datos.destino=6;


msgsnd(colagraf,&datos,sizeof(datos)-sizeof(long),0);
if(!llega10) pause();
 llega10=0; 

 sleep(10);
 kill(pidsg,12);
 system("reset");

return 0;
}

void R10(){
llega10=1;

}

void R12(){
printf("Imposible arrancar servidor grafico.");
exit(-1);
}

int creaservidorgrafico(){

int pidsg;

pidsg = fork();

if (pidsg==0){
	execl("servidor_ncurses","servidor_ncurses",NULL);
	perror("execl");
	exit(-1);

} else if (pidsg==-1){
	perror("fork");
	exit(-2);

	}
	return pidsg;
}

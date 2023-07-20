#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>
#include <stdlib.h>
#include <sys/wait.h>

int llega10=0, llega12=0;


void R10(){
llega10=1;
}

void R12(){
llega12=1;

}

int main(){

signal (10,R10);
signal (12,R12);

int vpidB, vpidC;


vpidB=fork();
if(vpidB==0){ //Estamos trabajando con la copia 

	execl("B","B",NULL);
	perror("Error de execl");
	exit(-1);

	} else if (vpidB==-1)
		printf("Imposible hacer el fork");

else{
vpidC=fork();
if(vpidC==0){ //Estamos trabajando con la copia 

	execl("C","C",NULL);
	perror("Error de execl");
	exit(-1);

	} else if (vpidC==-1)
		printf("Imposible hacer el fork");

else{ //Volvemos al proceso original
	sleep(1);
printf("A:Primer Mensaje\n");

//Mandamos una señal a B para que escriba el segundo msj
kill(vpidB,10);

if(llega10==0)
pause();

//Mandamos la señal a C para que escriba los mensajes 3 y 4
kill(vpidC,12);

pause();

//Mandamos una señal a B para que escriba el quinto msj
kill(vpidB,12);
wait(NULL);

printf("A:Ultimo mensaje\n");
return 0;

	}
   }
}



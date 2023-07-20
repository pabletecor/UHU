#include <stdio.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <sys/wait.h>
#include <unistd.h>
#include <stdlib.h>


int llega12=0;

void R12(){ //El proceso B ha recibido la señal 12
llega12=1;

}



int main(){

signal (12,R12);


int vpidB, vpidC;

int tubo[2];
pipe (tubo);



vpidB=fork();
if(vpidB==0){	//Estoy trabajando con la copia

 close(2);	//Cerramos la segunda posicion el canal
 dup(tubo[0]);	//Duplicamos tubo[0] para lectura
 execl("B","B",NULL);
 perror("error de execl");
 exit(-1);

	} else if(vpidB==-1) perror("Imposible hacer el fork");



vpidC=fork();
write(tubo[1],&vpidC,sizeof(vpidC));
if(vpidC==0){	//Estoy trabajando con la copia

 execl("C","C",NULL);
 perror("error de execl");
 exit(-1);

	} else if(vpidC==-1) perror("Imposible hacer el fork");

else{	//EStoy trabajando con el original 

sleep(1);
printf ("A: Primer mensaje\n");

//Mandamos señal 10 a B para que escriba el segundo mensaje

kill (vpidB,10);

//Esperamos a que B nos indique que ha recibido y procesado la señal

if(llega12==0)
pause();

printf("A: Ultimo mensaje\n");

unlink ("fifo1");


}



return 0;
}

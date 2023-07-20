//Proceso B: Crea al proceso C. Lee de la tuberia el numero entero que le envia el proceso uno, visualiza el numero leido. Hace un alarm del numero de segundos leido de la tuberia. Transcurrido el tiempo de alarma, envia la señal 10 al proceso A. Espera que termine su hijo y termina.

#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>
#include <signal.h>
#include <unistd.h>
#include <stdio.h>
#include <errno.h>
#include <sys/wait.h>

int llega14=0;

void R14(){
alarm(0);
llega14=1;
}

int main(){
 
 signal(14,R14);
 int num,vpidC;

 //Lee el numero de la tuberia
 
 read(2,&num,sizeof(num));
 printf("\nEl numero generado aleatoriamente es: %d\n",num);
 alarm(num);

 if (llega14==0) pause();
 
 //Manda la señal 10 a A
 kill(getppid(),10);

 //Crea al proceso C

 vpidC=fork();

 if(vpidC==0){ //Estamos en el proceso hijo
 
 execl("C","C",NULL);
 perror("Error de execl");
 exit(-1);


 } else if(vpidC==-1) perror("Error de fork");

 wait(NULL);

 printf("Fin de B\n"); 

 return 0;
}

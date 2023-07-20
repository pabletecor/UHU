/*Dos:
-Espera señal 10 de proceso “uno”. Muestra mensaje al recibir señal
-Lee pid del proceso “tres” de la FIFO.
-Genera un número aleatorio entre 5 y 10 y lo escribe en la FIFO.
-Envía señal 10 a proceso “tres”.
-Finaliza.*/

#include <stdio.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <signal.h>
#include <unistd.h>
#include <stdlib.h>

int llega10=0;

void R10{

llega10=1;
printf("B: Llega la senyal 10");

}




int main(){

signal(10,R10);
srand(getpid());


int fd,vpidC,num;
fd=open("f1",O_RDWR);
if(llega10==0) pause();

//Generamos un numero aleatorio
num= rand () % 6 + 10; 

//Leemos el vpidC de la FIFO
read(fd,&vpidC,sizeof(vpidC));

//Escribimos el numero aleatorio en la fifo
write(fd,&num,sizeof(num));

//Mandamos la señal 10 al proceso C

kill (vpidC,10);



}

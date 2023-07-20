#include <sys/types.h>
#include <sys/stat.h>
#include <unistd.h>
#include <stdio.h> 
#include <stdlib.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <signal.h>

int llega10=0;
int llega12=0;
int vpidB;
int vpidC=0;
int fd;



void R10(){
printf("B: Segundo mensaje\n");
llega10=1;


}

void R12(){
printf("B: Quinto mensaje\n");
llega12=1;
}

int main(){

signal (10,R10);
signal (12,R12);
vpidB = getpid();
//Meto el pid de B en una FIFO para C
mkfifo("fifo1",0777); //Creo la FIFO (nombre,permisos)
fd = open("fifo1",O_RDWR); //Abro la FIFO para lectura y escritura
write(fd,&vpidB,sizeof(vpidB));

//Leo el valor del vpid de C
read(2,&vpidC,sizeof(vpidC));

if(llega10==0)
      pause(); 
//Mandamos la señal a C
kill(vpidC,10);

if(llega12 == 0) //Si no ha llegado la señal 12 esperamos
pause();

kill(getppid(),12); //Indicamos al padre que ha llegado la señal 12




}

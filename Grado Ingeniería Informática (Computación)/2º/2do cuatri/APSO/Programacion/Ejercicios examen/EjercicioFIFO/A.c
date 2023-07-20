/*Uno:
-Creará la Fifo llamada f1 con todos los permisos y la elimina.
-Crea a los procesos C y B.
-Envía pid del proceso “C” al proceso “B” a través de la FIFO.
-Envía señal 10 al proceso “B”.
-Espera a que finalicen todos sus procesos hijos para finalizar.*/


#include <stdio.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <signal.h>
#include <unistd.h>
#include <stdlib.h>

int llega10=0;
int llega12=0;

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

//creamos y abrimos la FIFO con lectura y escritura
mkfifo("f1",0777);
int fd;
fd=open("f1",O_RDWR);

//Creo los procesos
vpidB=fork();
if(vpidB==0){ //EStoy trabajando con la copia

execl("B","B",NULL);
perror("Error de execl");
exit(-1);


} else if(vpidB==-1) perror("Error de fork");

//Escribo en la FIFO vpidC
vpidC=fork();
write(fd,&vpidC,sizeof(vpidC));

if(vpidC==0){ //EStoy trabajando con la copia

execl("C","C",NULL);
perror("Error de execl");
exit(-1);


} else if(vpidC==-1) perror("Error de fork");


else{  //Vuelvo al proceso original
sleep(1);

kill()


unlink ("f1");

}




}

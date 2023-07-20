// ----  HELLO WORLD! ----


#include<signal.h>
#include<sys/types.h>
#include<sys/wait.h>
#include<unistd.h>
#include<stdlib.h>
#include<stdio.h>
#include <fcntl.h> 

int llega10=0; //variable global
int llega14=0; //variable global

void R10(){
   signal(10,R10);
   llega10=1;
   printf("Soy proceso tres. Recibo señal 10.\n");
}

void R14(){
   signal(14,R14);
   llega14=1;
   printf("Soy proceso tres. Recibo señal de alarma.\n");
   alarm(0); //abortamos alarma
}

main()
{
   signal(10,R10); //me preparo para recibir señal 10
   signal(14,R14); //me preparo para recibir señal 14 del alarm
   if(llega10==0) //espero señal 10
      pause();
   int fd=open("f1",O_RDWR); //abro FIFO
   int numero_aleatorio;
   read(fd,&numero_aleatorio,sizeof(int));
   alarm(numero_aleatorio);
}

// ----  HELLO WORLD! ----

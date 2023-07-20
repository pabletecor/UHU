// ----  HELLO WORLD! ----


#include<signal.h>
#include<sys/types.h>
#include<sys/wait.h>
#include<unistd.h>
#include<stdlib.h>
#include<stdio.h>
#include <fcntl.h> 

int llega10=0; //variable global

void R10(){
   signal(10,R10);
   llega10=1;
   printf("Soy proceso dos. Recibo señal 10.\n");
}

main()
{
   signal(10,R10); //me preparo para recibir señal 10
   srand(getpid()); //inicializamos la semilla.
   int fd;
   fd=open("f1",O_RDWR); //abrimos FIFO
   int pid_tres;
   if(llega10==0) //espero señal 10
      pause();
   read(fd,&pid_tres,sizeof(pid_tres)); //lee de la FIFO el pid del proceso "tres"
   int aleatorio=rand()%6+5; //numero aleatorio entre 5 y 10
   write(fd,&aleatorio,sizeof(int));
   kill(pid_tres,10); //envía señal 10 al proceso "tres"
}

// ----  HELLO WORLD! ----

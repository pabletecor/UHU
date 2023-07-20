// ----  HELLO WORLD! ----

#include <signal.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>

int llega10=0;

void rutina10()
{
   signal(10,rutina10); //por seguridad, hago esto
   llega10=1;
   printf("Proceso uno recibe señal 10.\n");
}

main()
{
   signal(10,rutina10); //me preparo para recibir señal 10
   int pid_dos;
   int tubo[2];
   pipe(tubo); // <<< ¡¡CREAMOS LA TUBERÍA!! >>>
   int aleatorio;
   int pid_uno=getpid();
   pid_dos=fork();
   if(pid_dos==0) //proceso copia, hago execl
   {
      close(2);
      dup(tubo[0]);
      execl("dos","dos",NULL);
      perror("Error de execl.\n");
      exit(-1);
   }
   else if(pid_dos==-1)
      printf("Error en el fork.\n");
   else  //proceso original
   {
      sleep(1);
      srand(getpid()); //semilla
      aleatorio=rand()%6+5; //Rango=10-5+1=6
      write(tubo[1],&aleatorio,sizeof(aleatorio)); //escribe numero aleatorio en tubería
      if(llega10==0)
         pause(); //espero a recibir señal 10
      llega10=0;
      write(tubo[1],&pid_uno,sizeof(int)); //escribo pid en tubería
      if(llega10==0)
         pause();
      wait(NULL); //espera a que termine su hijo para terminar
   }
}

// ----  HELLO WORLD! ----

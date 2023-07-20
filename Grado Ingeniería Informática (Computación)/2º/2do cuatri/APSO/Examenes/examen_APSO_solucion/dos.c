// ----  HELLO WORLD! ----

#include <signal.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>

int llega14=0;

void rutina14()
{
   llega14=1;
   printf("Llega el alarm. Fin del alarm.\n");
   alarm(0); //anulo el alarm
}

main()
{
   signal(14,rutina14);
   int pid_tres;
   pid_tres=fork();
   int numero;
   if(pid_tres==0)
   {
      execl("tres","tres",NULL);
      perror("Error de execl.\n");
      exit(-1);
   }
   else if(pid_tres==-1)
      printf("Error del fork.\n");
   else
   {
      read(2,&numero,sizeof(int)); //lee de tubería el numero aleatorio enviado por proceso uno
      printf("Soy proceso dos. Leo de tubería el número %d.\n",numero);
      alarm(numero);
      if(llega14==0)
         pause();
      kill(getppid(),10); //envía señal 10 al proceso uno
      wait(NULL); //espera a que termine el hijo
   }
}


// ----  HELLO WORLD! ----
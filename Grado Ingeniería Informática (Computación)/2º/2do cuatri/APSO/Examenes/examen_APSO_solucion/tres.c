// ----  HELLO WORLD! ----

#include <unistd.h>
#include <stdio.h>
#include <signal.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <stdlib.h>

main()
{
   int pid_uno;
   read(2,&pid_uno,sizeof(pid_uno));
   printf("Soy proceso tres. Leo de tubería el número %d.\n",pid_uno);
   sleep(5);
   kill(pid_uno,10); //envía señal 10 al proceso uno
}


// ----  HELLO WORLD! ----
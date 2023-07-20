// ----  HELLO WORLD! ----


#include<signal.h>
#include<sys/types.h>
#include<sys/wait.h>
#include<unistd.h>
#include<stdlib.h>
#include<stdio.h>
#include<fcntl.h>              


main()
{
   mkfifo("f1",0777); 
   int pid_dos, pid_tres;
   pid_dos=fork();
   if(pid_dos==0)
   {
      execl("dos","dos",NULL);
      perror("Error de execl.\n");
      exit(-1);
   }
   else if(pid_dos==-1)
      printf("Error del fork.\n");
   else
   {
      pid_tres=fork();
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
         int fd;
         fd=open("f1",O_RDWR); //abro FIFO
         write(fd,&pid_tres,sizeof(pid_tres)); //escribe en FIFO el pid del proceso "tres".
         kill(pid_dos,10); //envía señal 10 a proceso "dos"
         sleep(10);
         wait(NULL); //espero a que finalice un hijo
         wait(NULL);
      }
   }
   unlink("f1"); //elimino FIFO
}


// ----  HELLO WORLD! ----

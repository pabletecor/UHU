// ----  HELLO WORLD! ----

//El proceso1 crea al proceso2 y le envia 10 números aleatorios entre 10 y 50 al proceso2 a través de una cola de mensajes.

#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/msg.h>
#include <sys/wait.h>

struct Mensaje1
{
   long tipo;
   int numero;
};

main()
{
   struct Mensaje1 m1;
   key_t clave;
   int id_cola;
   srand(getpid()); //inicializamos semilla
   int pid_proceso2;
   pid_proceso2=fork();
   if(pid_proceso2==0) //soy la copia, hago execl
   {
      execl("proceso2","proceso2",NULL);
      perror("Error en execl.\n");
      exit(-1);
   }
   else if(pid_proceso2==-1)
      printf("Error del fork.\n");
   else //soy proceso original
   {
      sleep(1);
      clave=ftok("./ficheroftok",25);   
      if(clave==(key_t)-1)
      {
         perror("Error de ftok\n");
         exit(-1);
      }
      id_cola=msgget(clave,0600 | IPC_CREAT);
      if(id_cola==-1)
      {
         perror("Error de msgget\n");
         exit(-1);
      }
      for(int i=0;i<10;i++)
      {
         m1.tipo=1;
         m1.numero=rand()%41+10; //numero aleatorio entre 10 y 50 
         msgsnd(id_cola,(struct msgbuf *)&m1,sizeof(m1)-sizeof(long),IPC_NOWAIT); //escribo en la cola de mensajes
      }
      wait(NULL); //espero a que termine el hijo
      msgctl(id_cola, IPC_RMID, (struct msqid_ds *)NULL); //elimino cola
   }
}

// ----  HELLO WORLD! ----





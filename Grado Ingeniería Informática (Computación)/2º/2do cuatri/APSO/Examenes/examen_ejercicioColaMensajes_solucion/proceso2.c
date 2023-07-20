// ----  HELLO WORLD! ----

//El proceso2 lee de la cola de mensajes los números uno a uno y los muestra por pantalla.

#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/msg.h>

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
      msgrcv(id_cola,(struct msgbuf*)&m1,sizeof(m1)-sizeof(long),1,0); //leo de la cola de mensajes
      printf("Elemento leido %d: número %d.\n",i+1,m1.numero);
   }

}

// ----  HELLO WORLD! ----

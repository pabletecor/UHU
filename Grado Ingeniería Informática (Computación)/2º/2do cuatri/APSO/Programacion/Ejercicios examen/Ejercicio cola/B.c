//El proceso2 lee de la cola de mensajes los números uno a uno y los muestra por
//pantalla.
#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>
#include <sys/ipc.h>
#include <sys/msg.h>
#include <stdio.h>
#include <errno.h>


//Definimos el struct fuera del main
struct numeros{

long tipo;
int num;

};


int main(){

//Declaramos el struct en el main
struct numeros n;

//Declaramos la cola
key_t clave;
int id_cola,vpidB,i;

clave=ftok("./caquita",7);

if(clave==(key_t)-1)
{
perror("error ftok");
exit(-1);
}


id_cola = msgget(clave,0600 | IPC_CREAT);

if(id_cola==-1)
{
perror("error msgget");
exit(-1);
}

//Cola declarada

for(i=1;i<=10;i++){
msgrcv(id_cola,(struct msgbuf*)&n,sizeof(n)-sizeof(long),1,0);
printf("\nElemento %d: %d ",i,n.num);

}


return 0;
}

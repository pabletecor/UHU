//El proceso A crea al proceso B y le envía 10 números aleatorios entre 10 y 50 al
//proceso2 a través de una cola de mensajes.
#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>
#include <sys/ipc.h>
#include <sys/msg.h>
#include <stdio.h>
#include <errno.h>
#include <sys/wait.h>


//Definimos el struct fuera del main
struct numeros{

long tipo;
int num;

};


int main(){

srand(getpid());

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

//Creamos el proceso B

vpidB=fork();

if(vpidB==0)//Estamos trabajando con la copia
{

execl("B","B",NULL);
perror("Error de execl");
exit(-1);

}
else if(vpidB==-1) perror("Error de fork");

else{	//He vuelto al proceso original
sleep(1);

n.tipo=1;

//Escribimos los numeros aleatorios en la cola
for(i=1;i<=10;i++){

n.num=rand()%41+10;
msgsnd(id_cola,(struct msgbuf*)&n, sizeof(n)-sizeof(long),IPC_NOWAIT);

}

wait(NULL);

//Borro la cola
msgctl(id_cola,IPC_RMID,(struct msqid_ds*)NULL);


}
return 0;
}

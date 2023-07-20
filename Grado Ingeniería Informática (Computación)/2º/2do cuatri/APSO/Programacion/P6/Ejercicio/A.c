#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/msg.h>
#include <errno.h>
#include <unistd.h>

struct numpos
{

long Id_Mensaje;
int pos;

};

struct numneg
{

long Id_Mensaje;
int neg;

};

//MAIN

int main(){

//Creo la cola de mensajes
key_t clave= ftok ("caquita",7);
int vpidB,vpidC,idcola,i;
struct numpos np;
struct numneg nn;


if(clave == (key_t) - 1){
	perror("Error ftok");
	exit(-1);

	}

idcola= msgget(clave, 0600 | IPC_CREAT);

if( idcola ==  -1){
	perror("Error msgget");
	exit(-1);

	}

//Creamos los procesos hijos

vpidB=fork();

if(vpidB==0){ //Soy una copia
	execl("B","B",NULL);
	perror("Error de execl");
	exit(-1);

}
else if(vpidB == -1) printf("Imposible hacer el fork\n");


vpidC=fork();

if(vpidC==0){ //Soy una copia
	execl("C","C",NULL);
	perror("Error de execl");
	exit(-1);

}
else if(vpidC == -1) printf("Imposible hacer el fork\n");

else{

sleep(1);

for(i=1;i<=10;i++)
{
	//El tipo de mensaje 1 es un numpos, 2 es un numneg
	//Ponemos 0 en el ultimo parametro porque queremos que espere
	msgrcv(idcola,(struct msgbuf*)&np,sizeof(np)-sizeof(long),1,0);
	printf("Numero positivo %d: %d\n",i,np.pos);
	
	msgrcv(idcola,(struct msgbuf*)&nn,sizeof(nn)-sizeof(long),2,0);
	printf("Numero negativo %d: %d\n\n",i,nn.neg);
	
	

}

printf("\nFin de la cola\n");

//ELiminamos la cola
msgctl(idcola,IPC_RMID,(struct msqid_ds *)NULL);

}

return 0;

}

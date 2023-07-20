#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/msg.h>
#include <errno.h>
#include <unistd.h>
#include <time.h>

struct numneg
{

long tipo;
int neg;

};


//MAIN

int main(){

key_t clave= ftok ("caquita",7);
int idcola,i,num,times;
struct numneg nn;
srand(time(NULL));

nn.tipo=2;

if(clave == (key_t )- 1){
	perror("Error ftok");
	exit(-1);

	}

idcola= msgget(clave, 0600 | IPC_CREAT);

if( idcola ==  -1){
	perror("Error msgget");
	exit(-1);

	}

for(i=0;i<10;i++){

//Generamos el numero aleatorio entre 0 y 100

nn.neg = (rand()%100+1)*-1;

times = (rand()%3+1);

sleep (times);
//mandamos el numero a la cola

msgsnd(idcola,(struct msgbuf *)&nn,sizeof(nn)-sizeof(long),IPC_NOWAIT);

}

return 0;
}

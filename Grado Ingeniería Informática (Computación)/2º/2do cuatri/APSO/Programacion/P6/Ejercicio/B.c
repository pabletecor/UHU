#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/msg.h>
#include <errno.h>
#include <unistd.h>
#include <time.h>

struct numpos
{

long tipo;
int pos;

};


//MAIN

int main(){

key_t clave= ftok ("caquita",7);
int idcola,i,times;
struct numpos np;
srand(time(NULL));


np.tipo=1;

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

np.pos = (rand()%100+1);

times = (rand()%3+1);

sleep (times);
//mandamos el numero a la cola

msgsnd(idcola,(struct msgbuf *)&np,sizeof(np)-sizeof(long),IPC_NOWAIT);

}

return 0;

}


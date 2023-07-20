#include <stdio.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/msg.h>
#include <unistd.h>

int main(){


key_t nombrecola;
int idcola,ret;

struct inforcv{
	long tipo;
	int num;

};

struct inforcv datosrcv;



printf("Hola soy el proceso A\n");

nombrecola = ftok("./paracolas.txt",7); //EL NOMBRE Y EL NUMERO DAN IGUAL

idcola = msgget(nombrecola,0600 | IPC_CREAT);

}

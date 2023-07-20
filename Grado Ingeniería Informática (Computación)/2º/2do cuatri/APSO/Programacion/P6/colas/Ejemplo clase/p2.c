#include <stdio.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/msg.h>
#include <unistd.h>



int main (){

key_t nombrecola;
int idcola,ret;

struct inforcv{
	long tipo;
	float pi;

};

struct inforsnd{
	long tipo;
	char ch;

};

struct infosnd datos;

struct infosnd datosrcv;

datos.tipo=2;
datos.ch= 'P';


printf("Hola soy el proceso P2\n");

nombrecola = ftok("./paracolas.txt",6); //EL NOMBRE Y EL NUMERO DAN IGUAL

idcola = msgget(nombrecola,0600 | IPC_CREAT);

msgsnd(idcola,&datos, sizeof(datos) - sizeof(long),0 ); //ESCRIBIMOS EN LA COLA
msgrcv(idcola, &datosrcv, sizeof(datosrcv) - sizeof(long),1 ,0  );

printf("P2: La lectura devuelve, %d, y me mandan %f\n", ret, datosrcv.pi);
//sleep(10);

//msgctl(idcola,IPC_RMID,NULL); //BORRAMOS LA COLA. TAMBIEN PODEMOS USAR EN LA TERMINAL ipcrm -q (msqid)

return 0;

} 

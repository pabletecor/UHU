#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>

#include <signal.h>
#include <sys/wait.h>
#include <unistd.h>

#include "comun.h"

void leeparametros(struct ParametrosBus *parambus, struct ParametrosCliente *paramclientes, int *maxclientes, int *creamin,int *creamax);
int creaproceso(const char *, int);
int creaservigraf(int);
void R10();
void R12();
void R17();

int llega10=0, terminados=0;



/****************************CREATESTIGOS*******************************/
int createstigos(const char nombre[],int cantidad){

int fifo,i;
int testigo=1;

mkfifo(nombre,0777);

fifo=open(nombre,O_RDWR);

for (i=1;i<=cantidad;i++){

write(fifo,&testigo,sizeof(testigo));

}

return fifo;

}


/****************************BORRATESTIGOS*******************************/
int borratestigos(const char nombre[],int id){

close(id);
unlink(nombre);


}


/**********************************************************************************/
/************       MAIN        ***************************************************/
/**********************************************************************************/

int main()
{
 
 int pservidorgraf, i, pidbus;
 int tubocliente[2],tubobus[2];
 char nombrefifo[10];
 int fifos[7];
 struct ParametrosBus parambus;
 struct ParametrosCliente paramclientes;
 int maxclientes, creamin,creamax;

 signal(10,R10);
 signal(12,R12);
 signal(17,R17);
 srand(getpid());

 //Lee los parametros 
 leeparametros(&parambus, &paramclientes, &maxclientes, &creamin, &creamax); 	


 pservidorgraf=creaservigraf(parambus.numparadas); // El argumento es la ultima parada. Maximo 6
 if(!llega10) pause(); //Espero a que el servidor gráfico de el OK


 //creamos las fifos. Las abrimos para que luego no haya que esperar en aperturas síncronas
 for(i=1;i<=parambus.numparadas;i++)
 {
	sprintf(nombrefifo,"fifo%d",i);
	unlink(nombrefifo); // por si se quedo de una ejecución previa
	mkfifo(nombrefifo,0600);
	fifos[i]=open(nombrefifo,O_RDWR);
 }

 pipe(tubobus);
 pipe(tubocliente);
 pidbus=creaproceso("bus",tubobus[0]);

//Escribimos en la tuberia los parametros del bus
 write(tubobus[1],&parambus,sizeof(parambus));

//Creamos los clientes
 for(i=1;i<=maxclientes;i++) 
 {
	creaproceso("cliente",tubocliente[0]);
	//Escribimos en la tuberia los parametros de cliente
	write(tubocliente[1],&paramclientes,sizeof(paramclientes));
 	sleep(rand()%(creamax-creamin+1)+creamin);
 }

 //Esperamos a que todos los clientes envien sigchld (El hijo ha terminado)
 while(terminados<=maxclientes) pause();

 sleep(3);
 kill(pidbus,12);
 kill(pservidorgraf,12);

 //Cerramos y borramos las fifos
 for(i=1;i<=parambus.numparadas;i++)
 {
	sprintf(nombrefifo,"fifo%d",i);
	close(fifos[i]);
	unlink(nombrefifo);
 }

 system("reset");
 return 0;
 
}

/************************************************************************/
/***********    FUNCION: leeparametros     ******************************/
/************************************************************************/

void leeparametros(struct ParametrosBus *parambus, struct ParametrosCliente *paramclientes, int *maxclientes, int *creamin,int *creamax)
{
 int ok=0;

 *maxclientes=30; //Numero de clientes que se crearan
 *creamin=1;   //Intervalo de tiempo para crear nuevos clientes MIN
 *creamax=5;   //Intervalo de tiempo para crear nuevos clientes MAX
 parambus->numparadas = paramclientes->numparadas=6;   // Cantidad de paradas
 parambus->capacidadbus=5;  //Capacidad del bus
 parambus->tiempotrayecto=3;   // Tiempo del trayecto entre paradas
 paramclientes->aburrimientomax=12;   //Intervalo de tiempo en aburrirse MAX
 paramclientes->aburrimientomin=20;  //Intervalo de tiempo en aburrirse MIN
 
 while(ok == 0)
 {
	system("clear");
	printf("Valores de los parámetros...\n\n");
	printf("Numero de pasajeros que se crearan: %d\n",*maxclientes);
	printf("Intervalo de tiempo para crear nuevos pasajeros: [%d-%d] \n",*creamin,*creamax);
	printf("Número de paradas: %d\n",parambus->numparadas);
	printf("Capacidad del Bus: %d\n",parambus->capacidadbus);
	printf("Tiempo en el trayecto entre paradas: %d\n",parambus->tiempotrayecto);
	printf("Intervalo de tiempo de aburrimiento: [%d-%d]\n",paramclientes->aburrimientomin,paramclientes->aburrimientomax); 
	printf("Pulse 0 si desea introducir nuevos valores, cualquier otro valor si desea continuar.\n");
	scanf("%d",&ok);

	if(ok == 0){
		do{
			printf("Numero de pasajeros que se crearan [maximo 50]:\n");
			scanf("%d",maxclientes);
		}while(*maxclientes <= 0 || *maxclientes > 50);

		do{
			printf("Intervalo de tiempo para crear nuevos pasajeros MIN [entre 1 y 8]: \n");
			scanf("%d",creamin);
		}while(*creamin< 1 ||*creamin > 8 );

		do{
			printf("Intervalo de tiempo para crear nuevos pasajeros MAX [entre 2 y 20]: \n");
			scanf("%d",creamax);
		}while(*creamax < 2 || *creamax > 20 || *creamax<=*creamin);
 
		do{
			printf("Número de paradas: \n");
			scanf("%d",&parambus->numparadas);
		}while(parambus->numparadas < 2 || parambus->numparadas > 6);
		paramclientes->numparadas=parambus->numparadas;
	
		do{
			printf("Capacidad del bus [maximo 10]: \n");
			scanf("%d",&parambus->capacidadbus);
		}while(parambus->capacidadbus <= 0 || parambus->capacidadbus > 10);
	
		do{
			printf("Tiempo en el trayecto entre paradas [maximo 10]:\n");
			scanf("%d",&parambus->tiempotrayecto);
		}while(parambus->tiempotrayecto < 1 ||parambus->tiempotrayecto > 10 );
	
		do{
			printf("Intervalo de tiempo en esperar para aburrirse MIN [entre 1 y 10]:\n");
			scanf("%d",&paramclientes->aburrimientomin);
		}while(paramclientes->aburrimientomin< 1 || paramclientes->aburrimientomin > 10 );
	
		do{
			printf("Intervalo de tiempo en esperar para aburrirse MAX [entre 5 y 200]:\n");
			scanf("%d",&paramclientes->aburrimientomax);
		}while(paramclientes->aburrimientomax < 5 || paramclientes->aburrimientomax > 20 || paramclientes->aburrimientomin > paramclientes->aburrimientomax);
	}
 }
}


/************************************************************************/
/***********     FUNCION: creaproceso      ******************************/
/************************************************************************/

int creaproceso(const char nombre[],int tubo)
{
 
 int vpid;

 vpid=fork();
 if(vpid==0)
 {
    close(2);
    dup(tubo);
    execl(nombre,nombre,NULL);
    perror("error de execl");
    exit(-1);
 }
 else if (vpid==-1)
 {
   perror("error de fork");
   exit(-1);
 }
 return vpid;
}

/************************************************************************/
/***********    FUNCION: creaservigraf     ******************************/
/************************************************************************/

// Lanza el servidor gráfico
int creaservigraf(int ultimaparada)
{
 
 int vpid;
 char cadparada[10];

 sprintf(cadparada,"%d", ultimaparada);
 
 vpid=fork();
 if(vpid==0)
 {
    execl("servidor_ncurses","servidor_ncurses",cadparada,NULL);
    perror("error de execl");
    exit(-1);
 }
 else if (vpid==-1)
 {
   perror("error de fork");
   exit(-1);
 }
 return vpid;
}

/************************************************************************/
/***********    FUNCION: R10     ****************************************/
/************************************************************************/

 
void R10()
{
 llega10=1;
}

/************************************************************************/
/***********    FUNCION: R12     ****************************************/
/************************************************************************/


void R12()
{
 printf("No es posible arrancar el servidor gráfico\n");
 exit(-1);
}

/************************************************************************/
/***********    FUNCION: R17     ****************************************/
/************************************************************************/


void R17() //para que no se queden zombies
{
 wait(NULL); 
 terminados++;
}





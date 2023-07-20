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

int creaproceso(const char *);
int creaservigraf(int);
void R10();
void R12();

int llega10=0;

/**********************************************************************************/
/************       MAIN        ***************************************************/
/**********************************************************************************/

int main()
{
 
 int pservidorgraf, i, pidbus;
 
 signal(10,R10);
 signal(12,R12);
 srand(getpid());


 pservidorgraf=creaservigraf(NUMPARADAS); // El argumento es la ultima parada. Maximo 6
 if(!llega10) pause(); //Espero a que el servidor gráfico de el OK

 pidbus=creaproceso("bus");
 

 
 for(i=1;i<=30;i++) 
 {
	creaproceso("cliente");
 	sleep(rand()%5+1);
 }

 for(i=1;i<=30;i++) wait(NULL);
 
 sleep(2);
 kill(pidbus,12);
 kill(pservidorgraf,12);

 system("reset");
 return 0;
 
}


/************************************************************************/
/***********     FUNCION: creaproceso      ******************************/
/************************************************************************/

int creaproceso(const char nombre[])
{
 
 int vpid;

 vpid=fork();
 if(vpid==0)
 {
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




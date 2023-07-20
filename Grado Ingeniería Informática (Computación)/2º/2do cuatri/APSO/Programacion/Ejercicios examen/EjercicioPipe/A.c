//Proceso A: Crea una tuberia llamada tubo y le pasa el puntero de lectura al proceso B. Genera un numero aleatorio entre 5 y 10 y lo escribe en la tuberia. Se queda esperando a que el proceso B le envie la señal 10. Visualiza un mensaje de llegada de señal. Escribe su pid en la tuberia. Se queda esperando a que el proceso C le envie la señal 10. Visualiza un mensaje de llegada de señal. Espera a que termine su hijo y termina

#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>
#include <signal.h>
#include <unistd.h>
#include <stdio.h>
#include <errno.h>
#include <sys/wait.h>



int llega10=0;

void R10(){
 printf("Llega la señal 10\n");
 llega10=1;
}


int main(){
 
 srand(getpid());
 signal (10,R10);
 int vpidB,vpidA,num;

//Creamos la pipe

 int tubo[2];
 pipe (tubo);

//Escribimos un numero aleatorio en la pipe
 
 num=rand()%6+5;
 write(tubo[1],&num,sizeof(num));

//Creamos el proceso B

 vpidB=fork();

 if(vpidB==0){ //Estamos en el proceso copia
 
// Cerramos el canal dos para duplicar el puntero de lectura y que B pueda leer 
 close(2);
 dup(tubo[0]);
//Creamos el proceso copia
 execl("B","B",NULL);
 perror("Error de execl");
 exit(-1);


 } else if(vpidB == -1) perror("Error de fork");

 if(llega10==0) pause();
 
// El mensaje B ya ha mandado la señal 10
 llega10=0;
 
//Escribimos el pid de A en la tuberia
 vpidA=getpid();
 write(tubo[1],&vpidA,sizeof(vpidA));
 
 if(llega10==0) pause();
 
 wait(NULL);

 printf("Fin de A\n"); 

 return 0;
 }




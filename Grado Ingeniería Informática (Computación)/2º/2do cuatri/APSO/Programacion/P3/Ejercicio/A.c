#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdlib.h> 
#include <sys/wait.h>

int main(){
printf("Soy el proceso A y mi PID es %d", getpid());
printf("\n");

int vpidB,vpidC;

vpidB=fork();

if(vpidB==0){ //Es la copia del proceso A

execl("B","B",NULL);
perror("Error de execl\n");
exit(-1);
}

vpidC=fork();

if(vpidC==0){ //Es la copia del proceso A

 //Estas ordenes sirven para direccionar al otro a f1
 close(1);
 open("f1.txt",O_WRONLY | O_CREAT);
 // Ya está redireccionado 

execl("C","C",NULL);
perror("Error de execl\n");
exit(-1);
}

else if (vpidC ==1) perror ("Imposible hacer fork");

wait(NULL); //El wait se pone para que el proceso padre espere a sus hijos (que hacen sleep o tardan en ejecutarse)

printf("\nFin del original, papi\n");


return 0;
}

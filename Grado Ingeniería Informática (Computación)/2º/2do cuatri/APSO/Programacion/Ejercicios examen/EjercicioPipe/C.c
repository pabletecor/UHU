//Proceso C: Lee de la tuberia el pid que le envia el proceso A. Visualiza el numero leido. Hace un sleep de 5 segundos. envia la señal 10 al proceso A y termina.

#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>
#include <signal.h>
#include <unistd.h>
#include <stdio.h>
#include <errno.h>


int main(){

int vpidA;

read(2,&vpidA,sizeof(vpidA));

printf("C: El pid de A es: %d\n", vpidA);

sleep(5);

printf("Fin de C\n");

kill(vpidA,10);


}

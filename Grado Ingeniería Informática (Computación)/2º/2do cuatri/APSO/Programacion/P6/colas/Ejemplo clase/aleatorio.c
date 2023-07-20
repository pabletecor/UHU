#include <stdio.h>
#include <stdlib>
#include <sys/types.h>
#include <unistd.h>

int main(){

int aleatorio;

srand(getpid());

aleatorio= rand()%100+1;
printf("El numero aleatorio es %d\n", aleatorio);

}

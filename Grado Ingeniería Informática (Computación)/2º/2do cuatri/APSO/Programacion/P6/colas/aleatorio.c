#include <stdio.h>
#include <stdlib>
#include <sys/types.h>
#include <unistd.h>

int main(){

int aleatorio;

//Usamos el pid porque cada vez da un numero diferente
srand(getpid());

aleatorio= (rand()%100+1)*-1;	//ASI NOS DARIA UN NUMERO NEGATIVO
printf("El numero aleatorio es %d\n", aleatorio);

}

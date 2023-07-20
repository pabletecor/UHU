#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>


int main(){

struct sigaction s2;

s2.sa_flags=0;
s2.sa_handler=R2;

signal (9,R9);
sigaction (2,&s2,NULL);

printf("Hola, soy recibidor con pid %d\n", getpid());

while(1){
  printf("Aqui currando...\n");
  sleep(2);

}

return 0;
}

void R10(){

printf ("ha llegado la señal 10\n");

}

void R2(){

prinf ("Ha llegado la señal 2");
}


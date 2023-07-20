#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <fcntl.h>

int main(){

int f1;
float pi=3.14;
char a = 'a';

printf("Hola soy C\n");

f1=open("fifo1", O_RDONLY);

read (f1,&a, sizeof(a));
printf("B: Acabo de leer %c \n", a);
read(f1,&pi, sizeof(pi));
printf("B: Acabo de leer %f \n", pi);
close(f1);


printf("Finaliza C\n");

}

#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <sys/stat.h>
#include <fcntl.h>

void R13(){

printf("Ha llegado SIGPIPE\n");

}

int main(){

int f1;
float pi=3.14;
char a = 'a';

signal(13,R13)

printf("Hola soy B\n");

f1=open("fifo1", O_WRONLY);

write (f1,&a, sizeof(a));
printf("\nB: Acabo de escribir una a");
write (f1,&pi, sizeof(pi));
printf("\nB: Acabo de escribir un pi");

close(f1);

printf("Finaliza B\n");

return 0;
}

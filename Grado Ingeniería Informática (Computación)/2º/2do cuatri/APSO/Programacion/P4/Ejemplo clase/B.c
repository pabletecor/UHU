#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

int main(){
printf ("Hola, soy el proceso B\n");

sleep(10);
printf("B: He estado dormido un rato y acabo, mi padre es %d\n", getpid());
exit(10);

return 0;

}



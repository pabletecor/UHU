#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>
#include <stdlib.h> 

int main(){

printf("Soy el proceso B, mi PID es %d, y el PID de mi padre es %d\n",getpid(),getppid() );

printf("Fin del proceso B\n");

sleep(2);
exit(-1);
return 0;


}

#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>
#include <stdlib.h> 

int main(){

printf("\nSoy el proceso C, mi PID es %d, y el PID de mi padre es %d\n",getpid(),getppid() );

printf("\nFin del proceso C\n");

sleep(3);

return 0;


}

#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>
#include <stdlib.h>
#include <sys/wait.h>
#include <signal.h>

int llega12=0;

void R12(){

printf("C:Tercer mensaje\n");
sleep(3);
printf("C:Cuarto mensaje\n");
llega12 =1;

}



int main(){

signal(12,R12);
if(llega12==0)
pause();
kill(getppid(),12);
return 0;
}

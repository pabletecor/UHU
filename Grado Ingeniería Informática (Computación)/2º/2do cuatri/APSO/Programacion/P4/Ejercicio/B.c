#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>
#include <stdlib.h>
#include <sys/wait.h>

int llega10=0;
int llega12=0;

void R10(){
printf("B: Segundo mensaje\n");
//Mandamos a A que hemos recibido la señal

llega10 =1;
}

void R12(){
printf("B: Quinto mensaje\n");

llega12=1;
}


int main(){
signal(10,R10);
if(llega10==0)
      pause(); 
kill(getppid(),10);
signal(12,R12); 
if(llega12==0)
      pause();
kill(getppid(),12);
return 0;
}

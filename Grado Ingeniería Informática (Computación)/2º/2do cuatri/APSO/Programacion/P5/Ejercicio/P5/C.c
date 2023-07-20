#include <sys/types.h>
#include <sys/stat.h>
#include <unistd.h>
#include <stdio.h> 
#include <stdlib.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <signal.h>



int vpidB, fd;
int llega10=0;


void R10(){

printf("C: Tercer mensaje\n");
sleep(3);
printf("C: Cuarto mensaje\n");
llega10=1;

}

int main(){
 
 signal(10,R10);
 fd=open("fifo1",O_RDWR);
 read(fd,&vpidB,sizeof(vpidB));

 if(llega10==0)
	pause();

 kill (vpidB,12);

}

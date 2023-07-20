#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <sys/stat.h>
#include <fcntl.h>
int main(){
int vpidb,vpidc;

printf("Hola soy A, y creo la fifo\n");

mkfifo("fifo1",0600);

vpidb=fork();
if(vpidb== 0){
	execl("B","B", NULL);
	perror("excel de B");
	exit(-1); 

} else if (vpidb==-1) perror ("Fork para B");

vpidc=fork();

if(vpidc== 0){
	execl("C","C", NULL);
	perror("excel de C");
	exit(-1); 

} else if (vpidc==-1) perror ("Fork para C");

wait (NULL);
wait (NULL);

//unlink ("fifo1");

printf("Finaliza A\n");

}

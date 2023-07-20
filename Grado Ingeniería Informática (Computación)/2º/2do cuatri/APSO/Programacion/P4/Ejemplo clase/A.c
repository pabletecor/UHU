#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/wait.h>

int main(){

int vpid,retorno,vret;

vpid=fork();
if(vpid==0){
  execl("8","9", NULL);
    perror("execl");
      exit(-1);

      }
      else if (vpid==1) perror("fork");

      printf("A: espero por mi hijo:\n");
      sleep(10);
      vret=wait(&retorno);
      printf("EL hijo termina con valor %d\n",vret);
      if((retorno & 0x7F) ==0)
        printf("A: mi hijo devuelve %d\n", (retorno>>8)&0xFF );
      printf("A: a mi hijo lo mata la señal %d\n", retorno&0x7F);
      printf("Fin de A\n");
      return 0;

      }


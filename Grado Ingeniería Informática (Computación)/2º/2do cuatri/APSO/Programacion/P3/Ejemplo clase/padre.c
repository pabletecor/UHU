#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>

int main()
{
        int vpid;
        printf ("Soy el proceso padre (%d) antes del fork() y mi padre es %d\n ", getpid(), getppid());

        vpid=fork();
        if(vpid==0){    //Estoy en la copia

        execl("hijo","hijo",NULL);
        perror("execl");
        exit(-1);

        }
        else if (vpid ==1) perror ("Imposible hacer fork");

        printf ("Soy el original (%d),  mi hijo es %d\n ", getpid(), getppid(), vpid);


}


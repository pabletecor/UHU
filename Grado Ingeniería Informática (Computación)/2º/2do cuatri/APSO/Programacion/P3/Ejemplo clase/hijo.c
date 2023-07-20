#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>

int main()
{
        int vpid;
        printf ("Soy la copia (%d), mi padre es %d\n ", getpid(), getppid());
        return 0;                         
  }


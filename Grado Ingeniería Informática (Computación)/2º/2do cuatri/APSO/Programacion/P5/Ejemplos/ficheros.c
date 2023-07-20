#include <stdio.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>


int main(){

int f1;
float pi;

f1=open("f1.txt", O_RDONLY);

printf("Leo el numero pi\n");
read(f1,&pi,sizeof(pi));
printf("He leido %f\n", pi);

close(f1);

}

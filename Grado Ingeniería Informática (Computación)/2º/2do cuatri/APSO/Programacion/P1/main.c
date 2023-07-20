#include <stdio.h>

int main(){

int opcion=0;

do{

printf ("1.Leer datos \n2. Visualizar media\n3.Visualizar coseno del menor\n4.Salir");
printf("Indique la opcion a elegir:\n");
scanf ("%d",&opcion);

switch (opcion){

case 1:
printf("Introduzca los numeros que quiera hasta llegar a 10, o bien pulsa -1 para dejar de leer datos.\n\n");

int tabla[10];
int i=0;
int valor=0;

while (i<=10 || valor!=-1){

printf("Introduce el valor de la posicion %d:", i);
scanf("%d",&valor);
printf("\n");

tabla[i]=valor;
i++;
}

break;

case 2:
printf("La media de los numeros introducidos es:");
printf("%f",media(tabla[10], i));

break;

case 3:
printf ("El menor numero introducido es:");
printf("%d",minimo(tabla[10],i));
double min=minimo(tabla[10],i);
printf("Y su coseno es:");
printf("%lf",coseno(min));

break;

case 4:
printf ("Adios!");
break;

default:
printf ("ALV");
} 

}while(opcion!=4);

return 0;
}

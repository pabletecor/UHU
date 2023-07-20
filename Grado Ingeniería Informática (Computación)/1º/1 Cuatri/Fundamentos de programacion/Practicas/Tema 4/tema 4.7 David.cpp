#include <iostream>
#include <windows.h>
#define M 2
#define N 1
typedef char cadena[30];
using namespace std;


class merges{

 int uno[15], dos[15], fus[30];
 int numUno, numDos; //numero de elementos que se quieran almacenar en cada tabla

 public:
 void  cargar();   //pregunta al usuario cuantos elementos va a poner en la tabla 1 y son los que solicitara despues
                   //lo mismo con la tabla 2

 void mezclar();  //Cargar la tabla fus con los valores de la tablas 1 y 2

 void ver(); // visualizar tablas 1 y 2

 void verfusion(); //visualizar tabla fus
};


void merges::cargar(){
system ("cls");

cout<<"\n\t\t¿How many numbers has Array 1? -> "; cin >>numUno;
for(int i=0; i<numUno; i++){
system("cls");
 cout<<"\t\t\tArray 1:\n\t\t\t--------\n\t\t\tNumber: "; cin>>uno[i];
}

cout<<"\t\t\t\n\nSaving";Sleep(1000);cout<<".";Sleep(1000);cout<<".";Sleep(1000);cout<<".";
system ("cls");

cout<<"\n\t\t¿Numbers Array 2? -> "; cin >>numDos;
for(int j=0; j<numDos; j++){
system("cls");
 cout<<"\t\t\tArray 2:\n\t\t\t--------\n\t\t\tNumber: "; cin>>dos[j];
}

cout<<"\n\nSaving";Sleep(1000);cout<<".";Sleep(1000);cout<<".";Sleep(1000);cout<<".";

}


void merges::mezclar(){

int f=0;

int tamano=numDos+numUno;
bool menor=false;


		for(int i=0; i<numUno; i++){
		fus[f]=uno[i];
		f++;
        }
        for(int j=0; j<numDos; j++){
        fus[f]=dos[j];
		f++;
        }

       //ORDENACION
       int aux;

        for(int k=1; k<tamano; k++){
            for(int m=0; m<tamano-1; m++){
                if(fus[m] > fus[m+1]){
                    aux=fus[m];
                    fus[m]=fus[m+1];
                    fus[m+1]=aux;
                }
             }
             }//termina for
        }

void merges::ver(){
system("cls");
int tamano=numUno+numDos;

cout<<"\t\t\tArray 1:\n\t\t\t---------\n";

for(int i=0; i<numUno; i++){

cout<<"\t\t\t    "<<uno[i]<<"\n";
}

cout<<"\n\n\t\t\tArray 2:\n\t\t\t--------\n";

for(int j=0; j<numDos; j++){
cout<<"\t\t\t    "<<dos[j]<<"\n";

}

}

void merges::verfusion(){
system("cls");

int tamano=numUno+numDos;

cout<<"\t\t\tArray Fusion:\n\t\t\t-------------\n\n";
                                                    //tamaño de un int SIZEOF(INT)
	for(int k=0; k<tamano; k++){                    //tamaño del arreglo SIZEOF(nombreARREGLO)
                                                    //-> numero de elementos del arreglo SIZEOF(FUS)/SIZEOF(INT)
    cout<<"\t\t\t    "<<fus[k]<<"\n";
	}
}



int main(){

merges start;
bool iniciado=false;
bool mezclado=false;
bool salir=false;
int opc,devuelve;

while (salir==false){

system("cls");

cout<<"\n";
cout<<"\t\t\t*********MENU***********\n";
cout<<"\t\t\t*                      *\n";
cout<<"\t\t\t* 1->    START     <-1 *\n";
cout<<"\t\t\t*                      *\n";
cout<<"\t\t\t* 2->  MIX ARRAYS  <-2 *\n";
cout<<"\t\t\t*                      *\n";
cout<<"\t\t\t* 3->   ARRAY 1&2  <-3 *\n";
cout<<"\t\t\t*                      *\n";
cout<<"\t\t\t* 4-> ARRAY FUSION <-4 *\n";
cout<<"\t\t\t*                      *\n";
cout<<"\t\t\t* 5->     EXIT     <-5 *\n";
cout<<"\t\t\t************************\n";
cout<<"\n\n\t\t\tOption: "; cin>>opc;


switch(opc){

case 1: start.cargar();
        iniciado=true;
        Sleep(2000);
break;

case 2: if(iniciado==true){
        start.mezclar();
        cout<<"\n\n\t\t\tMIX DONE!";
        mezclado=true;
        Sleep(2000);
        }else if(iniciado==false){
        cout<<"\n\n\t\t\tSTART FIRST!";
        } Sleep(2000);
break;

case 3: if(iniciado==true){
        start.ver();
        Sleep(2000);
        }else if(iniciado==false){
        cout<<"\n\n\t\t\tSTART FIRST!";
        } Sleep(2000);
break;

case 4: if(iniciado==true && mezclado==true){
        start.verfusion();
        Sleep(2000);
        }else if(iniciado==false){
        cout<<"\n\n\t\t\tSTART FIRST!";
        Sleep(2000);
        }else if((iniciado==true) && (mezclado==false)){
        cout<<"\n\n\t\t\tMIX FIRST!";
        }Sleep(2000);
break;


case 5: cout <<"\n\n\t\t\t\tLeaving";Sleep(1000);cout<<".";Sleep(1000);cout<<".";Sleep(1000);cout<<".\n\n";
salir=true;
break;

}

}

system("pause");
return 0;

}

#include <iostream>
#define TAMA 25
#include <math.h>
#include <time.h>
#include <stdlib.h>
#include <string.h>
using namespace std;

class Analisis
{
 int Datos[1000];
 int NDatos;
 int Valores[TAMA];
    public:
    void PedirDatos();
    void AnalizarDatos();
    bool EstanTodos();
    int ValorRepetido();
    int ValorMasRepetido();
    void MostrarDatos();
    void MostrarAnalisis();
};

void Analisis::PedirDatos(){
char elige;
cout<<"Bienvenido al Generador de datos"<<endl;
cout<<"Introduzca 'm' si quiere introducir su vector manualmente, o 'a' si quiere que se genere de forma aleatoria. Introduzca cualquier otro valor para salir del programa"<<endl;
cin>>elige;
if(elige=='m'){
    int i=0;
    NDatos=0;
    cout<<"Has elegido que el introducir los datos manualmente\nIntroduzca valores entre el 0 y el 24, y pulse -1 si quiere finalizar la cadena"<<endl;
    do{
        cout<<"Posicion del vector "<<i<<endl;
        cin>>Datos[i];
        while((Datos[i]<-1)||(Datos[i]>24))
        {
         cout<<"Introduzca un valor entre 0 y 24"<<endl;
         cin>>Datos[i];
        }
        i++;
        NDatos++;
    }
    while(Datos[i-1]!=-1);
    NDatos--;
}
else if(elige=='a'){
cout<<"Ha elegido la version aleatoria.\nA continuacion se generara el vector de forma automatica"<<endl;
srand(time(NULL));
NDatos=rand()%1001;
cout<<"El vector Datos va del 0 al "<<NDatos<<endl;
for(int i=0;i<NDatos;i++) Datos[i]=rand()%25;
}
}

void Analisis::AnalizarDatos(){

int i,j,a;
for(a=0;a<TAMA;a++) Valores[a]=0;       //Inicializacion de los valores a 0
for(i=0;i<NDatos;i++){                  //Recorrido del vector Datos
    for(j=0;j<TAMA;j++)                 //Recorrido de los valores de la componente i del vector Datos
       if(Datos[i]==j) Valores[j]++ ;
                     }
}
bool Analisis::EstanTodos(){
bool a;
int i;
i=0;
a=true;
while(i<TAMA&&a){
if(Valores[i]==0) a=false;
else i++;
}

return a;
}

int Analisis::ValorRepetido(){
    int a,b;
do{
        cout<<"Introduzca un valor entre 0 y 24"<<endl;
        cin>>a;
    }while((a<0)||(a>24));
b=Valores[a];
return b;
}

int Analisis::ValorMasRepetido(){
int Max=0;
int i,b=0;
for(i=0;i<TAMA;i++)
{   if(Valores[i]>=Max)
    {
        Max=Valores[i];
        b=i;
    }
}
return b;
}

void Analisis::MostrarDatos(){
for(int i=0;i<NDatos;i++) cout<<"Componente "<<i<<" : "<<Datos[i]<<endl;
}

void Analisis::MostrarAnalisis(){
for(int i=0;i<TAMA;i++) cout<<"El numero "<<i<<" se ha repetido "<<Valores[i]<<" veces ."<<endl;
}

int main()
{
    Analisis obj;
    bool a;
    int b,c;
    char yes[2];
    obj.PedirDatos();
    obj.AnalizarDatos();
    a=obj.EstanTodos();
    if(a)cout<<"Estan todos los valores posibles, desde el 0 hasta el 24.'"<<endl;
    else cout<<"No estan todos los valores posibles."<<endl;
    b=obj.ValorRepetido();
    cout<<"El numero solicitado se repite "<<b<<" veces."<<endl;
    c=obj.ValorMasRepetido();
    cout<<"El numero que mas se repite es el "<<c<<" ."<<endl;
    cout<<"Desea mostrar los vectores usados? Escriba Si si desea mostrarlos"<<endl;
    cin>>yes;
    if(strcmp(yes,"Si")==0){
        system("cls");
        obj.MostrarDatos();
        obj.MostrarAnalisis();
        }

    return 0;
}

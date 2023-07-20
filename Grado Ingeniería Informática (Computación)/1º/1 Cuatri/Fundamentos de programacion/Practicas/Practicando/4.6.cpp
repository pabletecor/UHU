#include <iostream>
#include <windows.h>
using namespace std;

class vect {
int tab1[5], tab2[5];
public:
void cargar ();
//Llenara las dos tablas con valores leídos desde teclado.
int comparar ();
//Devolverá un 1 si son diferentes tab1 y tab2, 0 si son
//idénticas
};


void vect::cargar(){
cout<<"input the numbers of the first array: \n";
for (int i=0; i<5; i++){
    cin>> tab1[i];



}
cout<< "\n Saving"; Sleep(1000); cout<<"."; Sleep(1000); cout<<"."; Sleep(1000); cout<<".";
system ("cls");
cout<<"input the numbers of the second array: \n";
for (int i=0; i<5; i++){
    cin>> tab2[i];

}
cout<< "\n Saving"; Sleep(1000); cout<<"."; Sleep(1000); cout<<"."; Sleep(1000); cout<<".";
}


int vect::comparar(){
int i=0;
bool igual=false;

while(i<5 && tab1 [i]!=tab2[i]){
    if(tab1[i]==tab2[i])
        igual = true;
    else
        i++;


}

if(igual=true)
    cout<<"\n 0";
else
    cout<< "\n 1";

}



int main(){
vect v;
v.cargar();
v.comparar();

return 0;

}

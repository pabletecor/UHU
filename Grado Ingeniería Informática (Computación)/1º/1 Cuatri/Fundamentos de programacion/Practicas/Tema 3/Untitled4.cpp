#include <iostream>
#include <windows.h>
using namespace std ;
int main () {
int tablaini, tablafin ;

        cout<< "introduzca el numero incial del que quiere hacer la tabla de multiplicar: \n" ;

        cin>>tablaini ;

        cout<< "Introduzca el numero hasta el que quiere hacer la tabla de multiplicar: \n";
        cin>> tablafin;
system("cls");


        for(int i=0; i <= 10; i++){
            for(int j=tablaini; j<=tablafin; j++)
            cout<< j << " * " << i << " = " << j*i << "\t ";
            cout<< "\n \n";

        }





return 0;

}

#include <iostream>
using namespace std;

class billete {
float precio ;
float distancia ;
int dias;
int edad;
public:
    void informacion() ;
    void operacion ();
    void resultado();

};

void billete::informacion() {
    cout<< "Introduzca la distancia del viaje, el numero de dias de dicho viaje y la edad del turista: ";
    cin>> distancia, dias, edad ;

}

void billete::operacion(){

precio= (distancia*2)*0.5;

if (dias>7 && distancia >800 )
    precio=precio*0.25;

else if (edad> 55 )
        precio=precio*0.25;

else precio=precio ;

}


void billete::resultado() {

cout <<"El precio del billete de ida y vuelta sera: " << precio ;

}


int main (){
billete b ;
b.informacion() ;
b.operacion() ;
b.resultado() ;

}

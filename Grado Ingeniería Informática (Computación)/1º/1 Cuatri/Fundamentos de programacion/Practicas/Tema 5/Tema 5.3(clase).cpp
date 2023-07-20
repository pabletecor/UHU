#include <iostream>
#include <windows.h>
using namespace std;
typedef char cadena[50];//Tipo de datos Cadena
#define MAX_CUENTAS 100 //Número de Cuentas

class cuenta //Contiene los datos de una cuenta bancaria
{
float saldo; //Saldo de la cuenta
int nocuenta; //Número de la cuenta
bool bloqueada; //true si está bloqueada
public:
cuenta();
cuenta(int pno, float psal);
bool actualizarsaldo(int psal);
void actualizarbloqueo(bool pbloq);
void actualizarncuenta(int pno){nocuenta=pno;}
float damesaldo(){return saldo;}        //
int damenocuenta(){return nocuenta;}    // Si la funcion es tan simple como devolver un valor, la podemos declarar
bool establoqueada(){return bloqueada;} // Entre corchetes despues de los parentesis
};

cuenta::cuenta(){
saldo=0;
nocuenta=0;
bloqueada=false;
}

cuenta::cuenta(int pno , float psal ){
saldo=psal;
nocuenta=pno;
bloqueada=false;
}

bool cuenta::actualizarsaldo(int psal){
cout<< "Indique el nuevo saldo de su cuenta: ";

if (bloqueada==false){
    saldo=psal;
    return true;}

    else
        return false;
}

void cuenta::actualizarbloqueo(bool pbloq){

bloqueada=pbloq;

}

int buscarcuenta(cuenta ctas[MAX_CUENTAS], int ncuentas, int nocuenta){
int i=0, posicion=-1;
bool encontrada = false;

while (i<ncuentas && encontrada==false){
    if(nocuenta== ctas[i].damenocuenta()){
        encontrada = true;
        posicion=i;
    }
    else
        i++;
    }
    return posicion;

}

int menucuentas(){
int opcion;

cout<< "    MENU DE GESTION DE CUENTAS\n";
cout<< "1 -> Añadir una cuenta a un cliente\n";
cout<< "2 -> Mostrar las cuentas del cliente\n";
cout<< "3 -> Borrar una cuenta del cliente\n";
cout<< "4 -> Modificar saldo de una cuenta\n";
cout<< "5 -> Modificar estado de una cuenta\n";
cout<< "6 -> Salir \n";
cout<< "Elige opción: \n";
cin>> opcion;

return opcion;
}

int main(){
cuenta datoscuentas[MAX_CUENTAS];
int ncuentas=0;
char siono;

do {

switch (menucuentas()){

case 1 :
    if(ncuentas<MAX_CUENTAS){

    int nocuentatemp;
    float saldocuentatemp;

cout<<"Indique el numero de la nueva cuenta: \n";
cout<< nocuentatemp;

    if (buscarcuenta(datoscuentas,ncuentas,nocuentatemp)==-1){ //Existe

    cout<<"Indique el saldo de la nueva cuenta: \n";
    cin>> saldocuentatemp;                                                      //Crea una cuenta con los datos ingresados
    datoscuentas[ncuentas].actualizarsaldo()=saldocuentatemp;
    datoscuentas[ncuentas].=nocuentatemp;          //En el vector, asigna esos datos a una cuenta
    ncuentas++;                                                             //Se adelanta una posicion en el vector

    }
    else
        cout<< "\nERROR!!!!! ESA CUENTA NO EXISTE";

}
    else
        cout<<"\nSe ha alcanzado el número máximo de cuentas.";
break;

case 2:
cout<< "Saldo/num cuenta/bloqueada\n";

for (int i=0;i<ncuentas;i++){
        cout<< datoscuentas[i].damenocuenta() << " / ";
        cout<< datoscuentas[i].damesaldo() << " / ";
        if (datoscuentas[i].establoqueada()==false){
            cout<< "ACTIVA\n\n";
        }
        else
            cout<< "BLOQUEADA\n";

}
break;

case 3:
    int cuentaborrar;
    cout<< "Indique el numero de la cuenta a borrar: ";
    cin>> cuentaborrar;

    if(buscarcuenta(datoscuentas,ncuentas,cuentaborrar) !=-1){
            for (int i=buscarcuenta(datoscuentas,ncuentas,cuentaborrar);i<ncuentas;i++){
        datoscuentas[i].nocuenta=datoscuentas[i+1].damenocuenta();
        ncuentas--;

            }
    }

    else
            cout<< "ERROR!!!!! ESA CUENTA NO EXISTE";
break;

case 4:
    int cuentatemp;
    float saldotemp;

    if(buscarcuenta(datoscuentas,ncuentas,cuentatemp) !=-1){
        cout<< "Indique el nuevo saldo de la cuenta: ";
        cin>> saldotemp;

        bool actualizacion = datoscuentas[MAX_CUENTAS].actualizarsaldo(saldotemp);

    }
        else
            cout<< "ERROR!!! ESA CUENTA NO EXISTE";
break;

case 5:

break;

case 6:

break;

case default:
    cout<< "\n Opcion incorrecta!\n";
    break;

}
}
while (choice !=6 );
return 0;
}

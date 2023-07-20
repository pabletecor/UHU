#include <iostream>
#include <stdio.h>
#include <string.h>
#include <windows.h>
#include <stdlib.h>
using namespace std;
typedef char cad[20];

class tprod {
cad nombre;
float precio;
int stock;
public:
tprod();
void cambiarnombre(cad nom);
void cambiarprecio(float prec);
void cambiarstock(int stoc);
void leenombre (cad nom);
float leeprecio ();
void leestock(int &st);
void vender(int cantidad);

};

tprod::tprod(){

strcpy(nombre,"NO HAY PRODUCTO");
precio=0;
stock=0;

}

void tprod::cambiarnombre(cad nom){
strcpy(nombre,nom);

}

void tprod::cambiarstock(int stoc){
stock=stoc;

}

void tprod::cambiarprecio(float prec){
precio=prec;

}

void tprod::leenombre(cad nom){
strcpy(nom,nombre);

}

float tprod::leeprecio(){
return precio;
}

void tprod::leestock(int &st){
st=stock;
}

void tprod::vender(int cantidad){
float preciotot;

if((stock-cantidad)<0){
    cout<< "\nLA VENTA NO SE REALIZARÁ, NO HAY STOCK SUFICIENTE.\n";
}
    else
    {
        preciotot=precio*cantidad;
        stock=stock-cantidad;
    cout<< "El precio es " << preciotot << " Euros\n";
    cout<<"Quedan "<<stock<<" productos\n";
        }
}

#define MAX 5
class almacen {
tprod productos[MAX]; //Vector de objetos
int nprod;
public:
almacen();
void vaciar();
int existe(cad nom);
void verprod(int pos, tprod &prod);
int insertar(tprod P);
void vertabla();
void vender(int pos, int cant);
char menu();
};

almacen::almacen(){
 //almacen::vaciar();
 nprod=0;
}

void almacen::vaciar(){
nprod=0;
/*cad nombredefecto= "NO HAY PRODUCTO";
 for(int i=0; i<MAX; i++)
    {
        productos[i].cambiarnombre(nombredefecto);
        productos[i].cambiarprecio(0);
        productos[i].cambiarstock(0);
    }
*/
}

int almacen::existe(cad nom){
bool encontrado=false;
int i=0;
cad nombreprod;

while (!encontrado && /*i<MAX*/i<nprod ){

    productos[i].leenombre(nombreprod);  //Usa el método de la clase tprod para asignar nombreprod al atributo privado nombre

    if (strcmp(nom,nombreprod)==0)
        encontrado= true;
    else
        i++;
}
if (!encontrado)
    /*return -1*/i=-1;

    return i;
}

void almacen::verprod (int pos, tprod &prod){

prod= productos[pos];
}


int almacen::insertar(tprod P){
cad nombreprod;
P.leenombre(nombreprod); //No hace falta poner productos[i] porque P ya es un objeto
int devolver;

if (nprod >= MAX) devolver=2;
else
{
    if (existe(nombreprod)!=-1) devolver=1;
    else {
    productos[nprod]=P;
    nprod++;
    devolver =0;
    }
}
return devolver;
}


void almacen::vertabla(){
cad nombreprod;
float precioprod;
int stockprod;

if (nprod ==0)
    cout<< "\nEl almacen está vacío. ";
else
    cout<< "\nNumero\tNombre\tprecio\tstock\n";
for (int i=0; i<nprod; i++){
    productos[i].leenombre(nombreprod);
    precioprod=productos[i].leeprecio();
    productos[i].leestock(stockprod);
    cout<< i << ":\t" << nombreprod << "\t" << precioprod << "\t" << stockprod << ".\n";

}

}

void almacen::vender (int pos, int cant){

productos[pos].vender(cant);

}


char menu(){
char letra;
system("cls");

cout<<"\n************MENU***********\n";
cout<<"******A.- Visualizar la tabla.\n";
cout<<"******B.- Insertar un producto.\n";
cout<<"******C.- Vender un producto.\n";
cout<<"******D.- Vaciar el almacen.\n";
cout<<"******E.- Salir.\n";
cout<<"***************************\n";
cout<<"Pon la opcion que desees: ";
cin>> letra;

return letra;
}

int main(){
almacen obj1;
tprod obj2;
cad producto;
float precio;
int stock;
int cant;
almacen();

do{
char opcion;
opcion = menu();
switch(opcion){

case 'a':
case 'A': obj1.vertabla();
system("pause");
break;

case 'b':
case 'B': cout<<"\n\nIntroduzca el nombre del nuevo producto:\n";
          cin>> producto;
          obj2.cambiarnombre(producto);

          cout<< "\nIntroduzca el precio del nuevo producto:";
          cin>> precio;
          obj2.cambiarprecio(precio);

          cout<<"\nIntroduzca la cantidad en stock del nuevo producto:\n";
          cin>> stock;
          obj2.cambiarstock(stock);

          if (obj1.insertar(obj2)==2)
            cout<< "\n El almacen está lleno. \n";
          else if(obj1.insertar(obj2)==1)
            cout << "\nYa existe un producto con el mismo nombre. \n";
            else
                cout<< "El objeto ha sido introducido.";
                system("pause");
break;

case 'c':
case 'C':

    cout<< "\nIntroduzca el nombre del producto que va a vender: ";
    cin>> producto;

    if (obj1.existe(producto)==-1){
        cout << "\nNo se puede vender ya que no existe el producto.";
    }
    else
        cout<< "Introduzca la cantidad que va a vender";
        cin>> cant;
    obj1.vender(obj1.existe(producto),cant);
system("pause");

break;

case 'd':
case 'D': obj1.vaciar();
          cout<< "\nCRISIS ABSOLUTA";
system("pause");
break;

case 'e':
case 'E': cout<< "Adios!";
system("pause");
break;

default: cout<< "Opcion incorrecta.";
system("pause");
break;
}

}while ((menu() !='E') &&( menu() !='e'));

return 0;
}

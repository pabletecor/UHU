#include <iostream>
#include <string.h>
using namespace std;
typedef char cadena[30];

struct producto {
cadena nomprod;
//Nombre del producto
int precio;
//Precio por unidad del producto
};
struct seccion {
producto prods[40];
int nprod;
//Número de productos almacenados en prods
//desde prods[0] a prods[nprod-1]
};

class empresa {
cadena nomempresa;
//Nombre de la empresa;
seccion sec[5];
int nsec;
//Número de secciones almacenadas en sec
//desde sec[0] hasta sec[nsec-1]
public:
empresa();
//Pedirá por teclado el nombre de la empresa y cuantas secciones tiene la empresa
//Para cada sección anterior pedirá cuantos productos tiene y para cada
//producto su nombre y el precio unitario, guardando todos estos valores en el objeto
bool insertar (int ns, producto prod);
//Trata de añadir el producto prod en la seccion ns
//Comprobará si ya existe un producto almacenado en cualquier sección
//de la empresa con el mismo nombre que tiene
//Si ya existía devolverá false y no lo insertará.
//Si no existía, comprobará si la tabla
//Si está llena devolverá false no añadiéndolo, en caso contrario
//si hay hueco lo insertará devolviendo true.
void numerosecciones(int &n);
//Devuelve en n el número de secciones que hay en la empresa
   void listar();
//No hay que implementarlo
//Muestra por pantalla todos los datos de la empresa
};

empresa::empresa(){
cout<< "Indique el nombre de la empresa: ";
cin>> nomempresa;
cout<< "\nIntroduce el numero de secciones de la empresa: ";
cin>> nsec;

for  (int i=0; i<nsec;i++){
    cout<< "\nIndique el numero de productos de la seccion " << i << ": ";
    cin>> sec[i].nprod;

    for (int j=0; j<sec[i].nprod;j++){
        cout<< "\nIndique el nombre del producto: ";
        cin>> sec[i].prods[j].nomprod;
        cout<< "\nIndique el precio del producto: ";
        cin>> sec[i].prods[j].precio;
    }

}

}

bool empresa::insertar(int ns, producto prod){
bool dev=true;
int i=0, j=0;

while (dev && i<nsec){

    while (dev && j<sec[i].nprod){

        if(strcmp(sec[i].prods[j].nomprod,prod.nomprod)==0)
        dev= false;
        else
        j++;

    }
    i++;
}

if (dev==true){
    if (sec[ns].nprod==40) dev= false;
    else
        strcpy(sec[ns].prods[sec[ns].nprod].nomprod,prod.nomprod);
        sec[ns].prods[sec[ns].nprod].precio=prod.precio;

        sec[ns].nprod++;

}
}


void empresa::numerosecciones(int &n){

n = nsec;

}

int main(){
empresa obj;
producto p; //Struct
int nsec;
cout<< "\n\t\tNUEVO PRODUCTO";
cout<< "\n\nIndique el nombre del producto: ";
cin>> p.nomprod;
cout<< "\nIndique el precio del producto: ";
cin>> p.precio;
cout<< "\nIndique el numero de la seccion donde lo quiere añadir: ";
cin>> nsec;



}

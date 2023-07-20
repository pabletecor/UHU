#include "empresa.h"
#include <windows.h>
#include <conio.h>

Empresa::Empresa()
{

this->ncli = 0;
this->nmaxcli = 100;
this->ncon= 0;
this->nmaxcon = 10; //Vamos sumando de 10 en 10

//clientes = new cliente[100];
this->contratos=new contrato *[nmaxcon]; //array dinamico de n punteros a contrato [*?, *?, *?, *?, *?]
}

Empresa::~Empresa(){

delete [] this->contratos;

}

void Empresa::crearContrato(){
bool nuevo = true; //El cliente es nuevo
long int dnicomp;


cout << "Introduzca el DNI del cliente: \n";
cin >> dnicomp ;
cout << endl;

int i =0;

while(i<ncli && nuevo ){
    if(dnicomp == clientes[i]->getDNI())
        nuevo = false;

    i++;
}

if(!nuevo){     //Si ya existe
    system("cls");
    if (this->ncon >= nmaxcon){ //Hemos llegado al maximo numero de contratos

        contrato **aux; //creamos un puntero auxiliar de tipo int *
        aux=contratos; //hacemos que dicho puntero auxiliar apunte al array dinamico
        contratos=new contrato*[this->ncon *2]; //creamos un nuevo array dinamico mayor y haceos que t apunte a él [*?, *?, *?, *?, *?, *?, *?, *?]
        for(i=0; i<ncon; i++)
        contratos[i]=aux[i]; //copiamos del array antiguo al nuevo [*1, *2, *3, *4, *5, *?, *?, *?]

        delete [] aux; //liberamos la memoria del array dinamico antiguo
        this->ncon = this->ncon *2;

    }

    cout << "El cliente " << dnicomp << " ya existe";Sleep(1000);cout<<"!\n\n";
    int opcion;
    bool opcioncorrecta = false;

    do{

    cout<< "Indique el tipo de contrato que desea: (1->tarifa, 2-> Movil, 3-> Movil Duo )\n";
    cin>> opcion ;

    //  HAGO UN SWITCH PARA VER SI QUIERE CONTRATO MOVIL O TARIFA.

    switch (opcion){

        case 3: {           //CONTRATO MOVIL DUO
        int dia,mes,anio, minutos;
            float preciomin1, preciomin2;
            char nac[50];

        cout<< "Indique la fecha del dia de hoy: \n";
        cout<< "dia : ";
        cin>> dia;
        cout << "\nmes : ";
        cin>> mes;
        cout<< "\nanio : ";
        cin>> anio;

        fecha f1(dia,mes,anio);

        cout<< "\nIndique el precio por minuto (menos de 100min) que desea: (0.XX): ";
        cin>> preciomin1;

        cout<< "\nIndique el precio por minuto (mas de 100min) que desea: (0.XX): ";
        cin>> preciomin2;

        cout<< "\nIndique los minutos hablados por el cliente: ";
        cin>> minutos;

        cout<< "\nIntroduzca la nacionalidad del cliente: (en MAYUSCULA, por favor): ";
        cin>> nac;

        contratomovilduo *cm = new contratomovilduo(dnicomp,f1,preciomin1, preciomin2 ,minutos,nac);

        contratos[this->ncon] = cm;
        this->ncon++;
        opcioncorrecta = true;


        break;




        }

        case 2: {           //CONTRATO MOVIL

            int dia,mes,anio, minutos;
            float preciomin;
            char nac[50];

        cout<< "Indique la fecha del dia de hoy: \n";
        cout<< "dia : ";
        cin>> dia;
        cout << "\nmes : ";
        cin>> mes;
        cout<< "\nanio : ";
        cin>> anio;

        fecha f1(dia,mes,anio);

        cout<< "\nIndique el precio por minuto que desea: (0.XX): ";
        cin>> preciomin;

        cout<< "\nIndique los minutos hablados por el cliente: ";
        cin>> minutos;

        cout<< "\nIntroduzca la nacionalidad del cliente: (en MAYUSCULA, por favor): ";
        cin>> nac;

        ContratoMovil *cm = new ContratoMovil(dnicomp,f1,preciomin,minutos,nac);

        contratos[this->ncon] = cm;
        this->ncon++;
        opcioncorrecta = true;


        break;
        }

        case 1: {           //CONTRATO TARIFA
            system("cls");
        int dia,mes,anio, minutos;

         cout<< "Indique la fecha del dia de hoy: \n";
        cout<< "dia : ";
        cin>> dia;
        cout << "\nmes : ";
        cin>> mes;
        cout<< "\nanio : ";
        cin>> anio;

        fecha f1(dia,mes,anio);


        cout<< "\nIndique los minutos hablados por el cliente: ";
        cin>> minutos;

        contratotp *ct = new contratotp(dnicomp,f1,minutos);

        contratos[this->ncon] = ct;
        this->ncon++;
        opcioncorrecta = true;

        break;
        }

        default:

            cout<< "\n\nELIGE UNA OPCION CORRECTA"; Sleep(1000);cout<<"!";Sleep(1000);cout<<"!";Sleep(1000);cout<<"!";


            break;

    }

} while (!opcioncorrecta);

}
    else //El cliente es nuevo


if (this->ncli == nmaxcli)
    cout << "NO CABEN MAS CLIENTES";

else{

    //CREAMOS EL NUEVO CLIENTE
     cout<< "El cliente " << dnicomp << " es nuevo, procedemos a darlo de alta: \n\n";
     int dia,mes,anio;


     cout << "Introduzca la fecha del dia de hoy): (dd/mm/aaaa)\n";
     cout<< "\nDia: ";
     cin>> dia;
     cout<< "Mes: ";
     cin>> mes;
     cout<< "Anio: ";
     cin>> anio;

     fecha f(dia,mes,anio);

     char nom[100];

     cout << "Introduzca el nombre del cliente: \n";
      cin.get();
      cin.getline(nom, 100);


     this->clientes[this->ncli] = new cliente(dnicomp,nom,f);
     this->ncli++;

     cout<< "\n\nEl cliente ha sido dado de alta";Sleep(1000);cout<<"!\n";


//ELEGIMOS CONTRATO PARA EL NUEVO CLIENTE

    int opcion;
    bool opcioncorrecta = false;

 do{

    cout<< "\nIndique el tipo de contrato que desea: (1->tarifa, 2-> Movil, 3-> MovilDuo): ";
    cin>> opcion ;

    //  HAGO UN SWITCH PARA VER SI QUIERE CONTRATO MOVIL O TARIFA.

    switch (opcion){

        case 3: {           //CONTRATO MOVIL DUO
        int dia,mes,anio, minutos;
            float preciomin1, preciomin2;
            char nac[50];

        cout<< "Indique la fecha del dia de hoy: \n";
        cout<< "dia : ";
        cin>> dia;
        cout << "\nmes : ";
        cin>> mes;
        cout<< "\nanio : ";
        cin>> anio;

        fecha f1(dia,mes,anio);

        cout<< "\nIndique el precio por minuto (menos de 100min) que desea: (0.XX): ";
        cin>> preciomin1;

        cout<< "\nIndique el precio por minuto (mas de 100min) que desea: (0.XX): ";
        cin>> preciomin2;

        cout<< "\nIndique los minutos hablados por el cliente: ";
        cin>> minutos;

        cout<< "\nIntroduzca la nacionalidad del cliente: (en MAYUSCULA, por favor): ";
        cin>> nac;

        contratomovilduo *cm = new contratomovilduo(dnicomp,f1,preciomin1, preciomin2 ,minutos,nac);

        contratos[this->ncon] = cm;
        this->ncon++;
        opcioncorrecta = true;


        break;




        }

        case 2: {           //CONTRATO MOVIL
            int dia,mes,anio, minutos;
            float preciomin;
            char nac[50];

        cout<< "\nIndique la fecha del dia de hoy: (dd/mm/aaaa)\n";
        cout<< "dia : ";
        cin>> dia;
        cout << "\nmes : ";
        cin>> mes;
        cout<< "\nanio : ";
        cin>> anio;

        fecha f1(dia,mes,anio);

        cout<< "\nIndique el precio por minuto que desea: (0.XX): ";
        cin>> preciomin;

        cout<< "\nIndique los minutos hablados por el cliente: ";
        cin>> minutos;

        cout<< "\nIntroduzca la nacionalidad del cliente: (en MAYUSCULA, por favor): ";
        cin>> nac;

        ContratoMovil *cm = new ContratoMovil(dnicomp,f1,preciomin,minutos,nac);

        this->contratos[this->ncon] = cm;
        this->ncon++;
        opcioncorrecta = true;


        break;
        }

        case 1: {           //CONTRATO TARIFA

        int dia,mes,anio, minutos;

         cout<< "Indique la fecha del dia de hoy: \n";
        cout<< "dia : ";
        cin>> dia;
        cout << "\nmes : ";
        cin>> mes;
        cout<< "\nanio : ";
        cin>> anio;

        fecha f1(dia,mes,anio);


        cout<< "\nIndique los minutos hablados por el cliente: ";
        cin>> minutos;

        contratotp *ct = new contratotp(dnicomp,f1,minutos);

        this->contratos[this->ncon] = ct;
        this->ncon++;
        opcioncorrecta = true;

        break;
        }

        default:

            cout<< "\n\nELIGE UNA OPCION CORRECTA"; Sleep(1000);cout<<"!";Sleep(1000);cout<<"!";Sleep(1000);cout<<"!";


            break;

    }

} while (!opcioncorrecta);

}

system("cls");

}

bool Empresa::cancelarContrato(int idContrato){ //true si el Contrato existe, false si no
idContrato--;
bool existe = false;
int i= 0;

while (i < this->ncon && !existe){
    if(idContrato == this->contratos[i]->getidcontrato())
        existe = true;

    i++;
}

if(existe){
    //ELIMINAR DE LA LISTA

    delete this->contratos[i];

    while(i<ncon-1){
        this->contratos[i] =this->contratos[i+1];   //DESLAZAMIENT0
        i++;

    }
this->ncon--;

}


    return existe;
}

bool Empresa::bajaCliente(long int dni){ //true si el Cliente existe, false si no
bool existe = false;
int i= 0;
bool d=false;

while (i < this->ncli && !existe){

    if(dni == this->clientes[i]->getDNI()){

        existe = true;

        }
    i++;
}
i--;

if(existe){
    //Aprovechamos para borrar todos los contratos del cliente en concreto
    int j=0;
    int k=0;
    while(j< this->ncon){
            d=false;
            if(dni == this->contratos[j]->getdnicontrato()){

    d=true;
    delete this->contratos[j];

        k=j;

        while(k<this->ncon-1){
            this->contratos[k] =this->contratos[k+1];   //DESLAZAMIENT0
            k++;
        }
    this->ncon--;
            }
        if(d) j=0;
        else j++;
        }
    //Ya hemos borrado todos sus contratos, ahora borramos el propio cliente
    delete  this->clientes[i];

        while(i<this->ncli-1){
            this->clientes[i] =this->clientes[i+1];   //DESLAZAMIENT0
            i++;
        }
    this->ncli--;

}

else
    cout<< "\nEl cliente no existe.\n";



return existe;
}

int Empresa::descuento (float porcentaje) const{ //devuelve a cuantos aplica el descuento

int n=0;

    for (int i=0; i< this->ncon; i++) {
        ContratoMovil* cm;
        cm= dynamic_cast<ContratoMovil*> (this->contratos[i]);

        if(cm){ //El casting se ha hecho bien porque el i-esimo es un contrato movil
            cm->setPrecioMinuto(cm->getPrecioMinuto() * (1-porcentaje/100) );
            n++;
        }


    }

return n;
}

//COMO SE HARIA CON EL TYPEID
/*
int Empresa::descuento (float porcentaje) const{ //devuelve a cuantos aplica el descuento

int n=0;

    for (int i=0; i< this->ncon; i++) {
        ContratoMovil* cm;
        if(typeid(*this->contratos[i]) == typeid(ContratoMovil)){}

        cm = (ContratoMovil *) this->contratos[i];
        cm->setPrecioMinuto(cm->getPrecioMinuto() * (1-porcentaje/100) );
        n++;
    }
return n;
}

*/

int Empresa::nContratosTP() const{      //Indica el numero de contratos de tarifa plana (typeid)
int contador =0;

for (int i=0;i<this->ncon;i++){
    if(typeid(*this->contratos[i]) == typeid(contratotp))
        contador++;

}

return contador;
}


 void Empresa::cargarDatos(){
     //creamos los clientes
fecha f1(28, 2, 2001);
this->clientes[this->ncli] = new cliente(75547001,"PETER LEE",f1);
this->ncli++;

fecha f2(29, 2, 2000);
this->clientes[this->ncli] = new cliente(45999000,"JUAN PEREZ",f2);
this->ncli++;

fecha f3(31, 1, 2002);
this->clientes[this->ncli] = new cliente(37000017,"LUIS BONO",f3);
this->ncli++;

//Creamos los contratos
ContratoMovil *cm1 = new ContratoMovil(75547001,f1,0.12,110,"DANES");

        this->contratos[this->ncon] = cm1;
        this->ncon++;

ContratoMovil *cm2 = new ContratoMovil(75547001,f3,0.09,170,"DANES");

        this->contratos[this->ncon] = cm2;
        this->ncon++;

fecha f4(01,2,2002);
contratotp *ct1 = new contratotp(37000017,f4,250);

        this->contratos[this->ncon] = ct1;
        this->ncon++;

contratotp *ct2 = new contratotp(75547001,f1,312);

        this->contratos[this->ncon] = ct2;
        this->ncon++;

ContratoMovil *cm3 = new ContratoMovil(45999000,f3,0.10,202,"ESPAÑOL");

        this->contratos[this->ncon] = cm3;
        this->ncon++;

ContratoMovil *cm4 = new ContratoMovil(75547001,f3,0.15,80,"DANES");

        this->contratos[this->ncon] = cm4;
        this->ncon++;

contratotp *ct3 = new contratotp(45999000,f4,300);

        this->contratos[this->ncon] = ct3;
        this->ncon++;
 }

 void Empresa::ver(){
cout<< "La empresa tiene " << this->ncli << " clientes y " << this->ncon << " contratos.\n\n";
cout<< "Clientes: \n\n";
for(int i=0; i < this->ncli; i++){
    cout<< *this->clientes[i];
    cout<<endl;
}
cout<< "\nContratos: \n\n";
for(int j=0;j<ncon;j++){
    contratos[j]->ver();
    cout<< " - " << this->contratos[j]->factura() << "$";
    cout<< endl;
}

 }

/// MOD
/*Añade un nuevo metodo a la clase empresa que permita dar de baja y eliminar
de la memoria a todos los clientes de la empresa más nuevos. Se entiende por clientes
nuevos aquellos clientes que se dieron de alta en la compañia en el año más
reciente.

void Empresa::bajaNuevos()
{
    int mayor_anio = 0;

    for(int i=0; i<this->ncli; i++)
        if(this->Clientes[i]->getFecha().getAnio() > mayor_anio)
            mayor_anio = this->Clientes[i]->getFecha().getAnio();

    for(int i=this->ncli-1; i>=0; i--)
        if(this->Clientes[i]->getFecha().getAnio() == mayor_anio)
            this->bajaCliente(this->Clientes[i]->getDni());
}

/// MOD
Añade un nuevo metodo que calcule cuantos clientes de TP tienen factura minima
( Se debe calcular la factura mas pequeña entre los clientes de TP y devolver
 cuantos clientes de dicho tipo tienen una factura igual a la minima calculada
int Empresa::menorFactura()
{
    float menor_facturaTP = Contratos[this->ncon-1]->factura();
    int contador=0;
    // IMPORTANTE!!!!
    // ES NECESARIO PONER EL VALOR DEL ULTIMO, ESTAMOS BUSCANDO UN MENOR
    // SI EMPEZAMOS EN 0, NUNCA CAMBIARÁ EL VALOR, PORQUE NO PUEDEN EXISTIR FACTURAS MENORES A 0

    for(int i=0; i<this->ncon; i++)
        if((typeid(*Contratos[i]) == typeid(ContratoTP)) && (this->Contratos[i]->factura() < menor_facturaTP))
            menor_facturaTP = this->Contratos[i]->factura();

    for(int i=0; i<this->ncon; i++)
        if(typeid(*Contratos[i]) == typeid(ContratoTP) && this->Contratos[i]->factura() == menor_facturaTP)
            contador++;

    return contador;
}
*/



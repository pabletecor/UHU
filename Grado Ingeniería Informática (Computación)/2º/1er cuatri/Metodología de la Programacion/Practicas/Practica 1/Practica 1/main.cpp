//Fecha: 01 - 12 - 2018
//Created by: Pablo Cordon Hidalgo
//2º año Grado Ingenieria Informatica

#include <cstdlib>
#include <iostream>
#include <iomanip> //std::setprecision
#include "fecha.h" //definicion clase Fecha
#include "CLIENTE.H" // definicion clase Cliente
#include  "contrato.h"// definicion de la clase Contrato
#include  "contratomovil.h"// definicion de la clase ContratoTP
#include  "contratotp.h"// definicion de la clase ContratoMovil
#include "empresa.h" // definicion de la clase Empresa

using namespace std;

/*
int main()
{

    //PARTE 1

    fecha f1(28,2,2001), f3(29,2,2004), f4(29,2,1900); //Fecha f5; //no permitido
    const fecha f2=f1; //indica que metodo se esta ejecutando aqui
    f1.setfecha(f3.getdia()-3, f3.getmes()-2, 2007); //29-3/2-2/2007--> f1=26/1/2007
    cout << "Fechas: (D/M/A)\n\n";
    f1.ver();
    cout << ", ";
    f2.ver();
    cout << ", ";
    f3.ver();
    cout << ", ";
    f4.ver();
    cout << "\n\n";
    if (f3.bisiesto() && !f2.bisiesto()&& f4.bisiesto()==false)
        cout <<f3.getanio() << " es bisiesto, " << f2.getanio()
             <<" y " << f4.getanio()<< " no\n\n";
             f4.setfecha(31, 12, 2000); //f4=31/12/2000
    f3=f4++; //indica que método/s se esta ejecutando aqui
    ++f4;
    f1=2+f2+3;
    cout << "Fechas:";
    f1.ver();
    cout<< ", ";
    f2.ver();
    cout<< ", ";
    f3.ver();
    cout<< ", ";
    f4.ver();
    cout << endl;

    //CLIENTE.CPP

 cliente *p = new cliente(75547001, "Susana Diaz", f1);
    f1.setfecha(7,10,2015);
    cliente *c = new cliente(75547999, "Juan Sin Miedo", fecha(29,2,2000));     // NECESARIO CREARLO DINÁMICO POR EL USO DE PUNTEROS
    const cliente j(44228547, "Luis", f1);
    c->setnombre("Juan Palomo");                                                // USAMOS -> POR SER PUNTERO

    if (&j==c)                                                                  // COMPARAMOS OBJETOS PUROS
        cout << "\nj y c son iguales\n";
    else
        cout << "\nj y c no son iguales\n";

    cout << p->getDNI() << " - " << c->getnombre() << ": " << j.getfecha() << endl;
    cout << *p << "\n" << *c << "\n" << j << "\n";
    *c = *p; // fallo ?????                                                     // POR USAR ESTATICO = DINÁMICO, SOLUCIONADO CON PUNTEROS
    p->setnombre("Susanita"); p->setfecha(p->getfecha()+10);
    cout << "\nDatos de los clientes: \n"; cout << *p << "\n" << *c << "\n" << j << "\n";
    delete p; p = NULL;


 //PARTE 2


 int main(int argc, char *argv[]) {
 fecha f1(29,2,2001), f2(f1), f3(29,2,2004);
 cout << "Fechas:" << f1 << ", " << f2 << ", " << f3 << endl;

 contrato *p = new contrato(75547111, f1), c(23000111, fecha(2,2,2002));
 cout << contratotp::getLimiteMinutos() << " - " << contratotp::getPrecio() << endl;
 contratotp ct1(17333256, f1, 250); //habla 250 minutoe
 contratotp ct2(12555100, f3, 320); //habla 320 minutos
 contratotp ct3(ct1);
 ContratoMovil cm1(17333256, f1, 0.12, 100, "ESPAÑOL"); //habla 100 minutos
 ContratoMovil cm2(17000000, fecha(3,3,2003), 0.10, 180, "FRANCES"); //habla 180 minutos
 ContratoMovil cm3(cm2);
 p->ver(); cout << "\n"; c.ver(); cout << endl;
 ct1.ver(); cout << endl; ct2.ver(); cout << "\n"; ct3.ver(); cout << "\n";
 cm1.ver(); cout << endl; cm2.ver(); cout << "\n"; cm3.ver(); cout << "\n";
 cout << p->getidcontrato() << ct2.getidcontrato() << cm2.getidcontrato() << endl;

 cout << setprecision(2) << fixed; //a partir de aqui float se muestra con 2 decimales

 cout << "Facturas: " << ct1.factura() <<"-"<< ct2.factura() <<"-"<< cm1.factura() << endl;
 contratotp::setTarifaPlana(350, 12); //350 minutos por 12 euros
 p->setdnicontrato(cm1.getdnicontrato());
 ct3.setfechacontrato(p->getfechacontrato()++);
 cm3.setNacionalidad( cm1.getNacionalidad());
 cm2.setPrecioMinuto(cm1.getPrecioMinuto()+0.02);
 cm1.setMinutosHablados(ct2.getminutoshablados()/2);    //movil
 ct1.setMinutosHablados(cm3.getMinutosHablados()*2);    //tarifa

 cout << *p <<"\n"<< c << endl;
 cout << ct1 <<endl<< ct2 <<"\n"<< ct3 <<"\n\n"<< cm1 <<"\n"<< cm2 <<endl<< cm3 << endl;

contrato *t[4];
t[0]=p; t[1]=&c; t[2]=&ct2; t[3]=&cm1;
cout << "\n-- Datos de los contratos: -- \n";
t[3]->setdnicontrato(75547111);
for(int i=0; i<4; i++) {
t[i]->setfechacontrato(t[i]->getfechacontrato()+2);
t[i]->ver(); cout << endl;
}
*/

    //PARTE 3

int main(int argc, char *argv[])
{
bool ok;
bool comp;
Empresa Yoigo;
cout << setprecision(2) << fixed; //a partir de aqui float se muestra con 2 decimales
cout << endl << "APLICACION DE GESTION TELEFONICA\n" << endl;
Yoigo.cargarDatos(); //crea 3 clientes y 7 contratos. metodo creado para no
Yoigo.ver(); //tener que meter datos cada vez que pruebo el programa
cout <<"Yoigo tiene " << Yoigo.nContratosTP() << " Contratos de Tarifa Plana\n\n";
//Yoigo.crearContrato(); //ContratoMovil a 37000017 el 01/01/2017 con 100m a 0.25
//Yoigo.crearContrato(); //ContratoTP a 22330014 (pepe luis) el 2/2/2017 con 305m
//Yoigo.ver();
ok=Yoigo.cancelarContrato(28); //este Contrato no existe
if (ok) cout << "Contrato 28 cancelado\n"; else cout << "El Contrato 28 no existe\n";
ok=Yoigo.cancelarContrato(4); //este Contrato si existe
if (ok)
cout << "El Contrato 4 ha sido cancelado\n";
else
cout << "El Contrato 4 no existe\n";
ok=Yoigo.bajaCliente(75547001); //debe eliminar el cliente y sus 3 Contratos
if (ok) cout << "El cliente 75547001 y sus Contratos han sido cancelados\n";
else cout << "El cliente 75547001 no existe\n";
Yoigo.ver();
Yoigo.descuento(20);
cout << "\nTras rebajar un 20% la tarifa de los ContratosMovil...\n\n";
Yoigo.ver();
cout <<"Yoigo tiene " << Yoigo.nContratosTP() << " Contratos de Tarifa Plana\n";

system("PAUSE");
return 0;
}

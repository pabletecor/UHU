#ifndef EMPRESA_H
#define EMPRESA_H
#include "fecha.h" //definicion clase Fecha
#include "cliente.h" // definicion clase Cliente
#include "contrato.h" // definicion de la clase Contrato
#include "contratotp.h" // definicion de la clase ContratoTP
#include "contratomovil.h" // definicion de la clase ContratoMovil
#include "contratomovilduo.h"
#include <typeinfo>
using namespace std;

class Empresa {

 cliente *clientes[100]; //array estático (tamaño 100) de punteros a Clientes
 int ncli; //para saber cuántos clientes hay en el array (al principio 0)
 int nmaxcli; //para saber cuántos caben en el array clientes (100)
 contrato **contratos; //array dinámico de punteros a Contratos
 int ncon; //para saber cuántos Contratos hay en el array (al principio 0)
 int nmaxcon; //para saber cuántos caben en el array Contratos

 public:

 Empresa();
 virtual ~Empresa();
 //EL CONTRUCTOR DE COPIA Y EL OPERADOR DE ASIGNACION NO LO IMPLEMENTAMOS
 //PORQUE EXPLICITAMENTE SE INDICA EN LA PRACTICA QUE NO SE HAGA

 void crearContrato();
 bool cancelarContrato(int idContrato); //true si el Contrato existe, false si no
 bool bajaCliente(long int dni); //true si el Cliente existe, false si no

 int descuento (float porcentaje) const; //devuelve a cuantos aplica el descuento
 int nContratosTP() const;

 void cargarDatos();
 void ver();


};
#endif // EMPRESA_H

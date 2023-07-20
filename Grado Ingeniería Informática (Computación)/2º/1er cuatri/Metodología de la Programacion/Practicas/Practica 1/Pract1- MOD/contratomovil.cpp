#include "contratomovil.h"

ContratoMovil::ContratoMovil(long int dni, fecha f, float precio, int minutos, char* nac) : contrato(dni, f)
{

 this->preciominuto = precio;
    this->minutohablado=minutos;
    this->nacionalidad = new char [strlen(nac)+1];      //MUCHO OJO CON ESTO EXAMEN!!!!!!!!!!!!!!
    strcpy(this->nacionalidad, nac);

}


ContratoMovil::~ContratoMovil()
{
    delete [] this->nacionalidad;
}

//ContratoMovil::ContratoMovil(const ContratoMovil& c):Contrato(c) { //IMPORTANTE: SI PONGO ESTA CABECERA y no implemento un constructor de copia en CONTRATO
                                                                     //NO DA ERROR AL COMPILAR PORQUE EL COMPILADOR GENERA UN CONSTRUCTOR COPIA POR SU CUENTA...
                                                                     //...EL PROBLEMA ES QUE EL QUE GENERA EL COMPILADOR NO INCREMENTA contador
                                                                     //Y ASIGNARIA EL MISMO CODIGO A AMBOS CONTRATOS
                                                                     //solucion: implementar constructor copia en CONTRATO y usar esta cabecera...
ContratoMovil::ContratoMovil(const ContratoMovil& c):contrato(c.getdnicontrato(), c.getfechacontrato()) { //... o usar esta otra cabecera
    this->preciominuto=c.preciominuto;
    this->minutohablado=c.minutohablado;
    this->nacionalidad = new char [strlen(c.nacionalidad)+1];
    strcpy(this->nacionalidad,c.nacionalidad );

}

void ContratoMovil::ver() const {
  contrato::ver(); //IMPORTANTE: llamamos al ver que heredo de mi padre PARA QUE MUESTRE LO DEL PADRE
                   //... y a continuacion solo "me preocupo" de mostrar lo que es exclusivo del hijo
  cout << " " << this->minutohablado << "m, " << this->nacionalidad << " " << this->preciominuto;
}

float ContratoMovil::factura() const {

  float fact = this->minutohablado * this->preciominuto;

  return fact;

}


void ContratoMovil::setNacionalidad(char* nac) {

delete[]this->nacionalidad;
this->nacionalidad = new char [strlen(nac)+1];      //MUCHO OJO CON ESTO EXAMEN!!!!!!!!!!!!!!
strcpy(this->nacionalidad, nac);
}

ostream& operator<<(ostream &s, const ContratoMovil &c) {
  s << (contrato &)c; //IMPORTANTE: convierto el objeto c (ContratoMovil &) a objeto Contrato &
                      // de esta forma se cree que es un objeto Contrato y muestra lo que indica el operator<< de Contrato
                      //... y a continuacion solo "me preocupo" de mostrar lo que es exclusivo del hijo

  s << " " << c.getMinutosHablados() << "m, " << c.getNacionalidad() << " " << c.getPrecioMinuto() << " - ";
  s << c.factura() << "€";

  return s;
}


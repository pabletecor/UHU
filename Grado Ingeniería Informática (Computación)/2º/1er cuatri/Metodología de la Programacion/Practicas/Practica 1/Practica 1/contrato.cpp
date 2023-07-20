#include "contrato.h"

int contrato::contador=1;

contrato::contrato(long int dni, fecha f) :idcontrato(contador), fechacontrato(f)   //idcontrato = contador , fechacontrato = f
{
    contador ++;
    this->dnicontrato=dni;
}

contrato::~contrato()
{
    //dtor
}

 void contrato::ver() const{
    cout << this->dnicontrato << " (" << this->idcontrato << " - ";
  this->fechacontrato.ver(); //llamo al ver del objeto fecha
  cout << ")";
 }


 ostream& operator << (ostream &s, const contrato &c){

s<< c.getdnicontrato() << " (" << c.getidcontrato() << " - " << c.getfechacontrato() << ") ";

return s;
 }

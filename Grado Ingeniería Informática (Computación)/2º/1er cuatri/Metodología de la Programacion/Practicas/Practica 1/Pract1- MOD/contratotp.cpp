#include "contratotp.h"

int contratotp::minutostp = 300;
float contratotp::preciotp = 10;


contratotp::contratotp(long int dni, fecha f, int m) :  contrato( dni, f)   //Inicializa el constructor de la clase madre para que se encargue de lo que es suyo (lo heredado)
{
    this->minutoshablados = m;
}

contratotp::~contratotp()
{
    //dtor
}

void contratotp::setTarifaPlana(int mtp, float ptp){ //Si es metodo estatico no uso this->
    contratotp::minutostp = mtp;        //puedo poner minutosTP=m ...pongo ContratoTP::minutosTP para recordar que es estatico
    contratotp::preciotp = ptp;

}

float contratotp::factura() const {
float fact;

fact = contratotp::preciotp + ( (this->minutoshablados - contratotp::minutostp) * contratotp::precioexcesominutos );

if ( this->minutoshablados < contratotp::minutostp )
    fact = contratotp::preciotp;

return fact;

}

 void contratotp::ver() const{
  contrato::ver();

  cout << " " << this->getminutoshablados() << "m, " << this->getLimiteMinutos() << "( " << this->getPrecio() << ") ";


 }


//OPERATORS

ostream& operator<<(ostream &s, const contratotp &c){
s << (contrato &)c; //IMPORTANTE: convierto el objeto c (Contratotp &) a objeto Contrato &
                      // de esta forma se cree que es un objeto Contrato y muestra lo que indica el operator<< de Contrato
                      //... y a continuacion solo "me preocupo" de mostrar lo que es exclusivo del hijo

s << c.getminutoshablados() << "m, " << c.getLimiteMinutos() << "( " << c.getPrecio() << ") - ";

return s;
}

#include "contratomovilduo.h"

contratomovilduo::contratomovilduo(long int dni, fecha f,float preciomenos100, float preciomas100 , int minutos, char* nac) : contrato(dni, f)
{

 this->preciominuto1 = preciomenos100;
 this->preciominuto2 = preciomas100;
    this->minutohablado=minutos;
    this->nacionalidad = new char [strlen(nac)+1];      //MUCHO OJO CON ESTO EXAMEN!!!!!!!!!!!!!!
    strcpy(this->nacionalidad, nac);

}


contratomovilduo::~contratomovilduo()
{
    delete [] this->nacionalidad;
}

//contratomovilduo::contratomovilduo(const contratomovilduo& c):Contrato(c) { //IMPORTANTE: SI PONGO ESTA CABECERA y no implemento un constructor de copia en CONTRATO
                                                                     //NO DA ERROR AL COMPILAR PORQUE EL COMPILADOR GENERA UN CONSTRUCTOR COPIA POR SU CUENTA...
                                                                     //...EL PROBLEMA ES QUE EL QUE GENERA EL COMPILADOR NO INCREMENTA contador
                                                                     //Y ASIGNARIA EL MISMO CODIGO A AMBOS CONTRATOS
                                                                     //solucion: implementar constructor copia en CONTRATO y usar esta cabecera...
contratomovilduo::contratomovilduo(const contratomovilduo& c):contrato(c.getdnicontrato(), c.getfechacontrato()) { //... o usar esta otra cabecera
    this->preciominuto1=c.preciominuto1;
    this->preciominuto2=c.preciominuto2;
    this->minutohablado=c.minutohablado;
    this->nacionalidad = new char [strlen(c.nacionalidad)+1];
    strcpy(this->nacionalidad,c.nacionalidad );

}

void contratomovilduo::ver() const {
  contrato::ver(); //IMPORTANTE: llamamos al ver que heredo de mi padre PARA QUE MUESTRE LO DEL PADRE
                   //... y a continuacion solo "me preocupo" de mostrar lo que es exclusivo del hijo
  cout << " " << this->minutohablado << "m, " << this->nacionalidad << " " << this->preciominuto1 << " (< 100m) , " << this->preciominuto2 << " (>100m) , ";
}

float contratomovilduo::factura() const {

  float fact;

  if(this->minutohablado < 100)
    fact = this->minutohablado * this->preciominuto1;
    else{
        fact= 100 * this->preciominuto1 + (this->minutohablado - 100) * preciominuto2;
    }


  return fact;

}


void contratomovilduo::setNacionalidad(char* nac) {

delete[]this->nacionalidad;
this->nacionalidad = new char [strlen(nac)+1];      //MUCHO OJO CON ESTO EXAMEN!!!!!!!!!!!!!!
strcpy(this->nacionalidad, nac);
}

ostream& operator<<(ostream &s, const contratomovilduo &c) {
  s << (contrato &)c; //IMPORTANTE: convierto el objeto c (contratomovilduo &) a objeto Contrato &
                      // de esta forma se cree que es un objeto Contrato y muestra lo que indica el operator<< de Contrato
                      //... y a continuacion solo "me preocupo" de mostrar lo que es exclusivo del hijo

  s << " " << c.getMinutosHablados() << "m, " << c.getNacionalidad() << " " << c.getPrecioMinuto1() << " - " << c.getPrecioMinuto2() << " - ";
  s << c.factura() << "€";

  return s;
}

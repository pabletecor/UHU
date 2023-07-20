#ifndef CONTRATOMOVILDUO_H
#define CONTRATOMOVILDUO_H

#include "contrato.h"

class contratomovilduo : public contrato
{

    float preciominuto1;    //Tarifa menos de 100 minutos
    float preciominuto2;    //Tarifa mas de 100 minutos
    int minutohablado;  //Redondeamos los minutos
    char* nacionalidad;

public:
        contratomovilduo(long int dni, fecha f,float preciomenos100, float preciomas100 , int minutos, char* nac);
  virtual ~contratomovilduo(); //necesario porque hay un atributo puntero char*
  contratomovilduo(const contratomovilduo& c); //necesario porque hay un atributo puntero char*

//ContratoMovil& operator=(const ContratoMovil& c); //necesario si en el main pensamos usar = para asignar
                                                    //si NUNCA vamos a usar = en el main no hace falta implementarlo
                                                    //como ContratoMovil hereda un atributo constante idContrato que no puede modificarse
                                                    //no podemos usar el = en el main() y por tanto no lo implementamos

  float getPrecioMinuto1() const { return this->preciominuto1; }
  float getPrecioMinuto2() const { return this->preciominuto2; }
  int getMinutosHablados() const { return this->minutohablado; }
  char* getNacionalidad() const { return this->nacionalidad; } //IMPORTANTE devuelve un puntero constante (const al principio y al final)
  void setPrecioMinuto(float precio) { this->preciominuto=precio; }
  void setMinutosHablados(int m) { this->minutohablado=m; }
  void setNacionalidad (char* nac);
  void ver() const;
  float factura() const;
};

ostream& operator<<(ostream &s, const ContratoMovil &c);

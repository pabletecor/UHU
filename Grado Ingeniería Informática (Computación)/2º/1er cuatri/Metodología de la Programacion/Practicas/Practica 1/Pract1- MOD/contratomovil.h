#ifndef CONTRATOMOVIL_H
#define CONTRATOMOVIL_H
#include "contrato.h"

class ContratoMovil : public contrato
{

    float preciominuto;
    int minutohablado;  //Redondeamos los minutos
    char* nacionalidad;

public:
        ContratoMovil(long int dni, fecha f,float precio, int minutos, char* nac);
  virtual ~ContratoMovil(); //necesario porque hay un atributo puntero char*
  ContratoMovil(const ContratoMovil& c); //necesario porque hay un atributo puntero char*

//ContratoMovil& operator=(const ContratoMovil& c); //necesario si en el main pensamos usar = para asignar
                                                    //si NUNCA vamos a usar = en el main no hace falta implementarlo
                                                    //como ContratoMovil hereda un atributo constante idContrato que no puede modificarse
                                                    //no podemos usar el = en el main() y por tanto no lo implementamos

  float getPrecioMinuto() const { return this->preciominuto; }
  int getMinutosHablados() const { return this->minutohablado; }
  char* getNacionalidad() const { return this->nacionalidad; } //IMPORTANTE devuelve un puntero constante (const al principio y al final)
  void setPrecioMinuto(float precio) { this->preciominuto=precio; }
  void setMinutosHablados(int m) { this->minutohablado=m; }
  void setNacionalidad (char* nac);
  void ver() const;
  float factura() const;
};

ostream& operator<<(ostream &s, const ContratoMovil &c);

#endif // CONTRATOMOVIL_H

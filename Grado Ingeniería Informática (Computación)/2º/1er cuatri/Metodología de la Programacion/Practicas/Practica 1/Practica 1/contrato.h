#ifndef CONTRATO_H
#define CONTRATO_H
#include "fecha.h"
#include <string.h>
//CLASE MADRE

class contrato
{
    static int contador;
    const int idcontrato;
    long int dnicontrato;
    fecha fechacontrato;

    public:
        contrato(long int dni, fecha f);
        virtual ~contrato();

        fecha getfechacontrato() const {return this->fechacontrato;}
        int getidcontrato() const {return this->idcontrato;}
        int getdnicontrato() const {return this->dnicontrato;}
        void setfechacontrato(fecha f){ this->fechacontrato = f;}
        void setdnicontrato(long int dni){this->dnicontrato = dni;}
        virtual void ver() const ;
        virtual float factura() const =0;

};

ostream& operator << (ostream &s, const contrato &c);
#endif // CONTRATO_H

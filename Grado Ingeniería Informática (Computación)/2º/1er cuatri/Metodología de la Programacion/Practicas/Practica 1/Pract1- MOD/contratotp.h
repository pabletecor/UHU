#ifndef CONTRATOTP_H
#define CONTRATOTP_H
#include "contrato.h"

class contratotp : public contrato
{

    static int minutostp;
    static float preciotp;
    int minutoshablados;
    const static float precioexcesominutos = 0.15;  //SI ES STATIC Y CONST SE DECLARA EN EL .h , SI NO EN EL .cpp

public:
        contratotp(long int dni, fecha f, int m);

        virtual ~contratotp(); //¿es necesario? pensar y reflexionad
        //ContratoTP(const ContratoTP& c);  //¿es necesario? pensar y reflexionad
        //ContratoTP& operator=(const ContratoTP& c); //¿es necesario? pensar y reflexionad

        static int getLimiteMinutos() { return contratotp::minutostp; }
        static float getPrecio() { return contratotp::preciotp; }
        void setMinutosHablados(int m) { this->minutoshablados=m; }
        int getminutoshablados() const {return this->minutoshablados;}
        static void setTarifaPlana(int mtp, float ptp);
        float factura() const;
        void ver() const;

};

ostream& operator<<(ostream &s, const contratotp &c);
#endif // CONTRATOTP_H

#include "fecha.h"

fecha::fecha(int dia,int mes,int anio)
{

this->setfecha(dia,mes,anio);

}

void fecha::setfecha(int d, int m,int a){

int day;
int diames [13] ={0,31,28,31,30,31,30,31,31,30,31,30,31};

if(this->bisiesto()){
    diames[2]=29;
}


if (m>12){

    m=12;
    }

    else if (m<1){

        m=1;
        }

day = diames[m];

if(d>day)
    d=diames[m];
    else if (d<1)
    d=1;

this->dia=d;
this->mes=m;
this->anio=a;
}

bool fecha::bisiesto()const {  //True Bisiesto, False no Bisiesto

int a=this->anio;

if(a % 4 == 0 && (a % 100 != 0 || a % 400 == 0)){
         return true;
     } else {
         return false;
    }

}

void fecha::ver()const{
cout<< this->dia << "/" << this->mes << "/" << this->anio;
}

//OPERADORES

fecha fecha::operator++(){ //++f
int d;
int diames [13] ={0,31,28,31,30,31,30,31,31,30,31,30,31};

if(this->bisiesto()){
    diames[2]=29;
}

d= diames[this->mes];  //el this-> llama directamente a un atributo privado de la clase

if(this->dia<d){
    this->dia++;
    }
else {
        this->dia=1;
    if(this->mes==12){
        this->anio++;
        this->mes=1;
    }

else {
    this->mes++;
        }
    }

    return *this;
}

fecha fecha::operator++(int i){


fecha copia(this->dia,this->mes,this->anio);

int d;
int diames [13] ={0,31,28,31,30,31,30,31,31,30,31,30,31};

if(this->bisiesto()){
    diames[2]=29;
}

d = diames[this->mes];  //el this-> llama directamente a un atributo privado de la clase

if(this->dia<d){
    this->dia++;
    }
else {
        this->dia=1;
    if(this->mes==12){
        this->anio++;
        this->mes=1;
    }

else {
    this->mes++;
        }
    }

    return copia;

}

fecha fecha::operator+ (int i) const{
fecha copia(this->dia,this->mes,this->anio);

for (int j=0; j<i;j++ ){
    copia++;                //This llama al objeto de la clase que estoy invocando en ese momento
}
return copia;
}


fecha operator+ (int i, const fecha &f){
fecha copia(f.getdia(),f.getmes(),f.getanio());

for (int j=0; j<i;j++ ){
    copia++;
}
return copia;

}


ostream& operator << (ostream &j, const fecha &f){

j << f.getdia() << " " << f.getmes() << " " << f.getanio();

return j;
}

//MOD

bool operator>(const fecha f1, const fecha f2){ //FALSE SI F1 MENOR QUE F2;
bool mayor = false;

if (f1.getanio() > f2.getanio())
    mayor = true;

    else if (f1.getmes() > f2.getmes())
        mayor = true;

        else if (f1.getdia() > f2.getdia())
            mayor = true;

return mayor;

}

#include "CLIENTE.H"

cliente::cliente(long int d,char *nom,fecha f) : fechaAlta(f.getdia(),f.getmes(),f.getanio()){      //Se puede poner fechaAlta(f)

this->dni=d;
this->nombre = new char [strlen( nom ) + 1  ];   //strlen : longitud de una cadena       ¡¡¡EXAMEN!!!
strcpy(this->nombre, nom);

}

cliente::~cliente()
{
    delete [] this->nombre; //Hay que liberar la memoria que hemos reservado en el new. por cada new x, delete x
}

void cliente::setnombre(char* nom){

this->nombre = new char [strlen( nom ) + 1  ];
strcpy(this->nombre,nom);

}

fecha cliente::getfecha() const{

return this->fechaAlta;

}

void cliente::setfecha(fecha f){

this->fechaAlta.setfecha(f.getdia(),f.getmes(),f.getanio());

}

long int cliente::getDNI()const{

return this->dni;
}

const char* cliente::getnombre()const{

return this->nombre;
}


//OPERADORES

cliente& cliente::operator= (const cliente& c){

if(this != &c){

    this->dni=c.dni;
    this->setfecha(c.fechaAlta);
    delete [] this->nombre;
    this->nombre = new char;
    strcpy(this->nombre,c.nombre);
}
return *this;

}

bool cliente::operator== (cliente c){
bool enc = false;
if(strcmp(this->nombre,c.getnombre())==0 && this->dni == c.dni && this->fechaAlta.getdia() == c.getfecha().getdia() && this->fechaAlta.getmes() == c.getfecha().getmes() && this->fechaAlta.getanio() == c.getfecha().getanio())
    enc = true;

    return enc;
}



ostream& operator << (ostream &s, const cliente &c){

s<< c.getnombre() << " (" << c.getDNI() << " - " << c.getfecha().getdia() << " " << c.getfecha().getmes() << " " << c.getfecha().getanio() << ")";

return s;

}

//MOD

bool operator > (const cliente &c1, const cliente &c2){ //FALSE SI F1 MENOR QUE F2;
bool mayor = false;
if(c1.getfecha() > c2.getfecha())
    mayor = true;

return mayor;
}

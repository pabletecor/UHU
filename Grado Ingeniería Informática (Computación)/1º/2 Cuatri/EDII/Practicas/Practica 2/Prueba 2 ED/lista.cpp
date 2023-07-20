#include "lista.h"

void copiartecnico(tecnico &destino, tecnico &origen){
    /*declarante d;
    int numdeclarantes;

    destino.Codigo=origen.Codigo;
    destino.Especialidad=origen.Especialidad;

    strcpy(destino.Nombre, origen.Nombre);
    strcpy(destino.Apellidos, origen.Apellidos);

    numdeclarantes=origen.Col.longitud();

    for(int i=0; i<numdeclarantes; i++){
        d=origen.Col.primero();
        destino.Col.encolar(d);
        origen.Col.encolar(d);
        origen.Col.desencolar();
    }*/

    declarante d;
    int numdeclarantes;

    destino.Codigo=origen.Codigo;
    destino.Especialidad=origen.Especialidad;

    strcpy(destino.Nombre, origen.Nombre);
    strcpy(destino.Apellidos, origen.Apellidos);

    numdeclarantes=origen.Col.longitud();



 	int longcola;
	longcola = destino.Col.longitud();

	for (int i = 0; i < longcola; i++){
            destino.Col.desencolar();
    }

	cola cola_1;

	longcola = origen.Col.longitud();

	for (int i = 0; i < longcola; i++){
		cola_1.encolar(origen.Col.primero());
		destino.Col.encolar(origen.Col.primero());
		origen.Col.desencolar();
	}

	for (int i = 0; i < longcola; i++){
		origen.Col.encolar(cola_1.primero());
		cola_1.desencolar();

	}
}







lista::lista(){
    elementos=new tecnico[INCREMENTO];
    if (elementos!=NULL) {
        Tama=INCREMENTO;
        n=0;
    }

    else{
        Tama=n=-1;
    }
}

lista::lista(tecnico &e) {
    elementos=new tecnico[INCREMENTO];
    if (elementos!=NULL) {
        Tama=INCREMENTO;
        n=1;
        copiartecnico(elementos[0], e);
    }
    else {
        Tama=n=-1;
    }
}


lista::~lista(){
    if (elementos!=NULL)
        delete [] elementos;
    elementos=NULL;
    Tama=n=0;
}


bool lista::esvacia(){
    return (n == 0);
}


int lista::longitud(){
    return n;
}


bool lista::pertenece(tecnico &e){
    return (posicion(e) != -1);
}


void lista::insertar(int i, tecnico &e){
    int pos;
    if (n==Tama){
        tecnico *NuevaZona=new tecnico[Tama+INCREMENTO];
        if (NuevaZona!=NULL){
        for (int j=0;j<n; j++)
            copiartecnico(NuevaZona[j], elementos[j]);

        Tama+=INCREMENTO;
        delete [] elementos;
        elementos=NuevaZona;
        }
    }

    if (n<Tama){
        for (pos=n-1; pos>=i-1; pos--)
        copiartecnico(elementos[pos+1], elementos[pos]);  // Desplazamiento
        copiartecnico(elementos[i-1], e);
        n++;
    }
}


void lista::eliminar(int i){
    while (i<n)  {
        copiartecnico(elementos[i-1], elementos[i]); // Desplazamiento
        i++;
    }
    n--;
    if (Tama-n>=INCREMENTO && Tama>INCREMENTO) {
        tecnico *NuevaZona=new tecnico[Tama-INCREMENTO];
        if (NuevaZona!=NULL){
            Tama-=INCREMENTO;
            for (int i=0;i<Tama; i++)
                copiartecnico(NuevaZona[i], elementos[i]);
            delete [] elementos;
            elementos=NuevaZona;
        }
    }
}


void lista::modificar(int i, tecnico &e) {
    copiartecnico(elementos[i-1], e);
}


tecnico &lista::observar(int i) {
    return(elementos[i-1]);
}


int lista::posicion(tecnico &e){
    int i=0;
    while (elementos[i].Codigo!=e.Codigo && i < n)
        i++;
    return (i == n ? -1 : i+1);
}

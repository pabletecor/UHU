#include "oficina.h"
#include <string.h>
#include <cstring>
struct tecnicof{
    int Codigo;
    cadena Apellidos;
    cadena Nombre;
    int Especialidad;
};




void copiartec(tecnico &destino, tecnico &origen){

    int numdeclarantes;

    destino.Codigo=origen.Codigo;
    destino.Especialidad=origen.Especialidad;

    strcpy(destino.Nombre, origen.Nombre);
    strcpy(destino.Apellidos, origen.Apellidos);

    numdeclarantes=origen.Col.longitud();



 	int longcol;
	longcol = destino.Col.longitud();

	for (int i = 0; i < longcol; i++){
            destino.Col.desencolar();
    }

	cola colaaux;

	longcol = origen.Col.longitud();

	for (int i = 0; i < longcol; i++){
		colaaux.encolar(origen.Col.primero());
		destino.Col.encolar(origen.Col.primero());
		origen.Col.desencolar();
	}

	for (int i = 0; i < longcol; i++){
		origen.Col.encolar(colaaux.primero());
		colaaux.desencolar();

	}
}






void oficina::AbrirOficina(char *nombrefichero){
    fstream fich;
    fich.open(nombrefichero, ios::in | ios::out | ios::binary);

    if(fich.fail()){
        fich.clear();
        cout<< "\n\n\tError al abrir el fichero.";
    }

    else{
        int i=0, numtecs, j=0, numdeclarantes;
        fich.seekg(0, ios::beg);
        fich.read((char*) &numtecs, sizeof (int));

        fich.seekg(sizeof(int) + sizeof(tecnicof)*numtecs, ios::beg);
        fich.read((char*) &numdeclarantes, sizeof (int));

        fich.seekg(sizeof(int) + sizeof(tecnicof)*i, ios::beg);

        while(i<numtecs){
            tecnico t;
            tecnicof tf;

            fich.read((char*) &tf, sizeof (tecnicof));

            t.Codigo=tf.Codigo;
            strcpy(t.Apellidos, tf.Apellidos);
            strcpy(t.Nombre, tf.Nombre);
            t.Especialidad=tf.Especialidad;

            IncorporarTecnico(t);

            i++;

        }

        fich.seekg(sizeof (int) + sizeof(tecnicof)*i + sizeof(int) + sizeof(declarante)*j, ios::beg);
        while(j<numdeclarantes){
            declarante d;
            bool incorporar;
            fich.read((char*) &d, sizeof(declarante));
            incorporar=IncorporarDeclarante(d);
            j++;
        }
    }

    fich.close();

}


void oficina::IncorporarTecnico(tecnico &t){
    int i=1, numtecs, posicion;
    bool insertado=false, existente=false;
    tecnico tec;
    declarante d;
    numtecs=L.longitud();

    if(numtecs==0){
        L.insertar(i, t);
        insertado=true;

        posicion=i;
    }

    else{
        while(i<=numtecs && !insertado && !existente){
            copiartec(tec, L.observar(i));
            if(L.pertenece(t)){
                cout<<"\nTecnico introducido ya existente.";
                existente=true;
            }

            else{
                if(t.Codigo<tec.Codigo){
                    L.insertar(i, t);

                    insertado=true;
                    posicion=i;
                }
            }
            i++;
        }

        if(!insertado && !existente){
            L.insertar(i, t);
            posicion=i;
            insertado=true;
        }

    }


    if(insertado){
        numtecs++;


        int esp, numdeclarantes, numespecialistas, rep, pos;
        esp=t.Especialidad;

        numespecialistas=numesp(esp);
        numdeclarantes=numdecs(esp);


        rep=numdeclarantes/numespecialistas;


        for(int j=0; j<rep; j++){
            pos=masdecs(esp);
            copiartec(tec, L.observar(pos));
            numdeclarantes=tec.Col.longitud();

            for(int k=0; k<numdeclarantes-1; k++){
                d=tec.Col.primero();
                tec.Col.encolar(d);
                tec.Col.desencolar();
            }

            d=tec.Col.primero();
            tec.Col.desencolar();
            L.modificar(pos, tec);

            t.Col.encolar(d);
        }

        L.modificar(posicion, t);

    int longcol;
	longcol = tec.Col.longitud();

	for (int i = 0; i < longcol; i++){
            tec.Col.desencolar();
    }

    longcol = t.Col.longitud();

	for (int i = 0; i < longcol; i++){
            t.Col.desencolar();
    }
    }
}


bool oficina::RetirarTecnico(int codigo){
    bool ret=false, enc=false;
    tecnico t;
    int numtecs, i=1;

    numtecs=L.longitud();

    while(i<=numtecs && !enc){
        copiartec(t, L.observar(i));
        if(t.Codigo==codigo){
            enc=true;
        }
        else i++;
    }

    if(numesp(t.Especialidad)==1){
        return false;
    }

    else{
        while(i<=numtecs && !enc){
            copiartec(t, L.observar(i));
            if(t.Codigo==codigo){
                enc=true;
            }
            else i++;
        }

        if(enc){
            int numdeclarantes;
            declarante d;
            cola colaaux;
            numdeclarantes=t.Col.longitud();

            for(int j=0; j<numdeclarantes; j++){
                d=t.Col.primero();
                colaaux.encolar(d);
                t.Col.desencolar();
            }

            L.eliminar(i);

            for(int j=0; j<numdeclarantes; j++){
                d=colaaux.primero();
                IncorporarDeclarante(d);
                colaaux.desencolar();
            }

            ret=true;
        }

    }
    return ret;
}


int oficina::TiempoEspera(int especialidad,int horaactual){
    tecnico t;
    int numtecs=L.longitud(), numdec=0, numdectotal=0, tiempodec=0, tiempototal=0, tiempomedio=0;
    if(especialidad==-1){
        for (int i=1; i<=numtecs; i++){
            copiartec(t, L.observar(i));

            numdec=t.Col.longitud();
            numdectotal=numdectotal+numdec;

            for(int j=0; j<numdec; j++){
                declarante d;

                d=t.Col.primero();
                t.Col.encolar(d);
                t.Col.desencolar();

                tiempodec=horaactual-d.HoraLlegada;
                tiempototal=tiempototal+tiempodec;
            }
        }
        tiempomedio=tiempototal/numdectotal;
    }

    else{
        for (int i=1; i<=numtecs; i++){
            copiartec(t, L.observar(i));
            if(t.Especialidad==especialidad){
                numdec=t.Col.longitud();
                numdectotal=numdectotal+numdec;

                for(int j=0; j<numdec; j++){
                    declarante d;

                    d=t.Col.primero();
                    t.Col.encolar(d);
                    t.Col.desencolar();

                    tiempodec=horaactual-d.HoraLlegada;
                    tiempototal=tiempototal+tiempodec;
                }
            }
        }
        tiempomedio=tiempototal/numdectotal;

    }

    return tiempomedio;
}


bool oficina::IncorporarDeclarante(declarante dec){
    bool incorp=false;

    int esp, pos;
    tecnico t;
    esp=dec.Especialidad;

    if(numesp(esp)!=0){

        pos=menosdecs(esp);

        copiartec(t, L.observar(pos));
        t.Col.encolar(dec);

        L.modificar(pos, t);

        incorp=true;

    }

    return incorp;
}


void oficina::Mostrar(){
    int numtecs, longcol;
    tecnico t;
    declarante d;
    numtecs=L.longitud();
    for(int i=1; i<=numtecs; i++){
        copiartec(t, L.observar(i));
        cout<< "\n\n\n\nTecnico " << i <<": ";
        cout<< "\n" << t.Codigo << "\tApellidos: " << t.Apellidos << "\tNombre: " << t.Nombre << "\tEspecialidad: " << t.Especialidad << "\tcon los declarantes: ";
        cout<< "\n";
        cout<< "\n\tApellidos \tNombre \tDNI \t\tHora Llegada\tEdad";
        cout<< "\n_______________________________________________________________________________________________________________";
        longcol=t.Col.longitud();

        for(int j=0; j<longcol;j++){
            d=t.Col.primero();
            t.Col.encolar(d);
            t.Col.desencolar();
            cout<< "\n\t" << d.Apellidos << "\t\t"<< d.Nombre << "\t" << d.Dni << " \t" << d.HoraLlegada << "\t\t" << d.Edad;
        }
    }
}

//Segundo ejercicio modificacion 2

void oficina::primerocola(){
int numtecs = L.longitud();
int longcol;
int tiemp = -1;
tecnico t;
declarante d;
declarante prim;

for(int i=1; i<=numtecs;i++){
    copiartec(t,L.observar(i));
    longcol= t.Col.longitud();
    for(int j=0;j<longcol;j++){
        d=t.Col.primero();
        t.Col.encolar(d);
        t.Col.desencolar();
        if(d.HoraLlegada > tiemp){
            tiemp = d.HoraLlegada;
            prim.Especialidad = d.Especialidad;
            prim.Edad = d.Edad;
            prim.HoraLlegada = d.HoraLlegada;
            strcpy(prim.Dni,d.Dni);
            strcpy(prim.Nombre,d.Nombre);
            strcpy(prim.Apellidos,d.Apellidos);
        }

    }

}

cout<< "\n\t" << prim.Especialidad << "\t\t" << prim.Apellidos << "\t\t" << prim.Nombre << "\t\t" <<prim.Dni << "\t\t" <<prim.HoraLlegada << "\t\t" <<prim.Edad<< "\n\n";


}

bool oficina::AtenderDeclarante(int CodigoTecnico){
    bool atends=false;
    tecnico t;
    int numtecs, i=1;
    numtecs=L.longitud();

    while(i<=numtecs && !atends){
        copiartec(t, L.observar(i));
        if(t.Codigo==CodigoTecnico){
            atends=true;
        }
        else i++;
    }

    if(atends){
        t.Col.desencolar();
        L.modificar(i, t);
    }

    return atends;
}

// Primer ejercicio segunda modificacion
void oficina::eliminardec(char *Dni){

bool enc=false;
tecnico t;
declarante d;
int numtecs = L.longitud();
int longcol;
int i=1;
int j=0;

while(i<=numtecs && !enc){
    copiartec(t, L.observar(i));
    longcol = t.Col.longitud();

    while(j<longcol && !enc){
        d = t.Col.primero();
        if(strcmp(d.Dni,Dni) == 0){
            enc = true;
        }
        else{
            t.Col.encolar(d);
            t.Col.desencolar();
            j++;

        }


    }
    if(!enc)
        i++;
}

//Ya hemos recorrido todos los tecnicos y sus respectios declarantes

if(!enc)
    cout<< "\nNo se ha encontrado ningun declarante con ese DNI.\n\n";
else{
    t.Col.desencolar();
    L.modificar(i,t);
    for (int k=0; k<j;k++){
        d=t.Col.primero();
        t.Col.encolar(d);
        t.Col.desencolar();
    }
    cout<< "\nDeclarante eliminado con exito!";
}


}

void oficina::VolcarOficina(char *nombrefichero){

    int numtecs;
    tecnico t;
    tecnicof tf;


    fstream fich;
    fich.open(nombrefichero, ios::out);

    if(fich.fail()){
        fich.clear();
        cout<<"\n\n\tImposible crear el fichero.";
    }

    else{

        fich.close();
        fich.open(nombrefichero, ios::in | ios::out | ios::binary);

        numtecs=L.longitud();
        int numdec, numdectotal=0;

        fich.seekp(0, ios::beg);
        fich.write((char*)&numtecs, sizeof(int));

        for (int i=1; i<=numtecs; i++){
            copiartec(t, L.observar(i));

            tf.Codigo=t.Codigo;
            tf.Especialidad=t.Especialidad;
            strcpy(tf.Apellidos, t.Apellidos);
            strcpy(tf.Nombre, t.Nombre);

            fich.seekp(sizeof(int)+sizeof(tecnicof)*(i-1), ios::beg);
            fich.write((char*)&tf, sizeof(tecnicof));

            numdec=t.Col.longitud();

            for(int j=0; j<numdec; j++){
                declarante d;

                d=t.Col.primero();
                t.Col.encolar(d);
                t.Col.desencolar();

                fich.seekp(sizeof(int) + sizeof(tecnicof)*(numtecs) + sizeof(int) + sizeof(declarante)*(numdectotal+j), ios::beg);
                fich.write((char*)&d, sizeof(declarante));

            }
            numdectotal=numdectotal+numdec;


        }

        fich.seekp(sizeof(int) + sizeof(tecnicof)*(numtecs), ios::beg);
        fich.write((char*)&numdectotal, sizeof(int));
    }
    fich.close();
}



int oficina::numdecs(int esp){
    int numdeclarantes=0, numtecs=L.longitud();

    for(int i=1; i<=numtecs; i++){
        tecnico t;
        copiartec(t, L.observar(i));
        if (t.Especialidad==esp){
            numdeclarantes=numdeclarantes+t.Col.longitud();
        }
    }
    return numdeclarantes;
}

int oficina::numesp(int esp){
    int numespecialistas=0, numtecs=L.longitud();

    for(int i=1; i<=numtecs; i++){
        tecnico t;
        copiartec(t, L.observar(i));
        if (t.Especialidad==esp){
            numespecialistas++;
        }
    }
    return numespecialistas;
}

int oficina::masdecs(int esp){
    int numtecs=L.longitud(), postecnico, numdeclarantes=-1;
    tecnico t;
    for(int i=1; i<=numtecs; i++){
        copiartec(t, L.observar(i));
        if (t.Especialidad==esp){
           if (t.Col.longitud()>numdeclarantes){
                numdeclarantes=t.Col.longitud();
                postecnico=i;
            }
        }
    }

    return postecnico;
}

int oficina::menosdecs(int esp){
    int numtecs=L.longitud(), postecnico=-1, numdeclarantes=L.observar(0).Col.longitud();
    tecnico t;
    for(int i=1; i<=numtecs; i++){
        copiartec(t, L.observar(i));
        if (t.Especialidad==esp){

           if (t.Col.longitud()<numdeclarantes){
                numdeclarantes=t.Col.longitud();
                postecnico=i;
            }
        }
    }

    return postecnico;
}

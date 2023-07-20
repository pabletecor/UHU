#include "Maraton.h"
#include "string.h"
#include "Clasificacion.h"
#include <iostream>
#include <stdlib.h>
#include <windows.h>

int marcas(int semilla)
{
    srand(semilla);
    return (rand()%7000+3000);
}

void marcaDisplay(int marca, int &h, int &m, int &s)
{
    s = marca%60;
    m = marca/60;
    h = m/60;
    m = m%60;
}


Maraton::Maraton(char FicheroOrigen[],char FicheroDestino[]){

 numAtletas = 0;

    fich.open(FicheroOrigen, ios::in| ios::out | ios::binary);
    if(fich.fail())
    {
        fich.close();
        fich.clear();
        fichero.open(FicheroDestino, ios::out|ios::binary);//Se abre como escritura para crearlo
        fichero.write((char *)&numAtletas, sizeof(int));//Se guarda un entero 0 en el fichero de destino
        fichero.close();
        fichero.open(FicheroDestino, ios::in|ios::out|ios::binary);

    }
    else // Si existe el fichero:
    {
        int i=0, nAtletasPais;
        Atleta Temp;


        fichero.open(FicheroDestino, ios::out|ios::binary); //Al crear un fichero, este siempre debe ser de escritura (out), ya que no se puede crear un fichero que no tenga contenido
        fichero.close();
        fichero.open(FicheroDestino, ios::in | ios::out | ios::binary);
        fich.seekg(0,ios::beg);
        fichero.seekp(sizeof(int),ios::beg);

        while(!fich.eof())
        {
            nAtletasPais=0;
            fich.read((char *)&nAtletasPais, sizeof(int));

            for(i=0; i<nAtletasPais; i++)
            {
                fich.read((char *)&Temp,sizeof(Atleta));//Guarda en objeto Temp
                fichero.write((char *)&Temp, sizeof(Atleta));//Sube el objeto temp
                numAtletas++;
            }

        }
        fichero.seekp(0,ios::beg);
        fichero.write((char *)&numAtletas,sizeof(int));//Escribe el total de atletas
    }

}

Maraton::~Maraton(){

}

void Maraton::consultar(cadena pais){

Atleta atl;
fichero.seekg(sizeof(int),ios::beg);
if (strcmp (pais,"*")==0){

    for(int i=0;i<numAtletas;i++){
    fichero.read((char*)&atl, sizeof(Atleta)); //Lee todos los datos del fichero que estén declarados como atletas
    cout<< "Atleta numero " << i+1 << ":\n";
    cout<< "Dorsal: "<< atl.dorsal<<  "\nPais: " << atl.pais<< "\nNombre: " << atl.nombre<<"\nApellido: " << atl.apellidos<< "\n\n";

    }

}
else{

    for(int i=0;i<numAtletas;i++){
    fichero.read((char*)&atl, sizeof(Atleta));
        if (strcmp (atl.pais,pais)==0){

    cout<< "Pais: " << pais << "\n";
    cout<< "Dorsal: " << atl.dorsal << "\nNombre: " << atl.nombre<< "\nApellido: " << atl.apellidos<< "\n\n";
    }
}

}


}

void Maraton::insertar(Atleta s)
{
    Atleta atl;
    int Paispos;
    int i=2;
    //bool dorsal = false;
    //No es necesario comprobar si el dorsal a añadir existe puesto que estos se asignan consecutivamente de forma automatica

    bool bpais=false;
    fichero.seekg(sizeof(int),ios::beg); //Se posiciona al principio del fichero y salta un entero pq
                                         //el primer elemento del fichero es numatletas

    while(i<=numAtletas)
    {

    fichero.read((char*)&atl,sizeof(Atleta));

    if(strcmp(atl.pais,s.pais)==0) //Comprueba si ya existe el pais para insertar en una u otra posicion
    {
        Paispos=i - 1;
        bpais=true;
    }
    i++;
    } //Fin del while



        if(!bpais)
        {
            fichero.seekg(sizeof(int) + numAtletas*sizeof(Atleta),ios::beg); //Si no existe el pais, posiciona al final del fichero
            fichero.write((char*)&s,sizeof(Atleta));
        }
        else
        {
            Atleta atl2;
            fichero.seekg(Paispos*sizeof(Atleta)+sizeof(int),ios::beg);
            fichero.read((char*)&atl,sizeof(Atleta));
            fichero.seekg(Paispos*sizeof(Atleta)+sizeof(int),ios::beg);
            fichero.write((char*)&s,sizeof(Atleta));

            for(int j=Paispos+1;j<numAtletas;j++)
            {
                fichero.read((char*)&atl2,sizeof(Atleta));
                fichero.seekg(sizeof(int)+j*sizeof(Atleta),ios::beg);
                fichero.write((char*)&atl,sizeof(Atleta));
                atl=atl2;
            }
            fichero.write((char*)&atl,sizeof(Atleta));

        }


numAtletas++;

}


void Maraton::modificar(int dor){ //Modifica Dorsal, Nombre , Apellidos y Pais

int pos=0; //Se compara con numatletas para escribir en la posicion
bool encontrado =false;
Atleta atl;

fichero.seekg (sizeof(int), ios::beg);

while (!encontrado && pos<numAtletas)
{
    fichero.read((char*)&atl, sizeof(Atleta));
    if (atl.dorsal==dor){
        encontrado=true;
    }
    else
        pos++;
}

if (!encontrado) {
    cout<< "\n\nError: dorsal no encontrado\n";

}
else{ //Procedemos a la modificación del atleta

    cout<< "\n\nModificando a: (Dorsal/Nombre/Apellidos/Pais) \n\n\t\t" << atl.dorsal << "/" <<atl.nombre << "/" << atl.apellidos<< "/" << atl.pais<<"\n\n";

    //Eliminamos el atleta que hemos modificado, y situamos su modificacion donde corresponda
     eliminarsilencioso(atl.dorsal);

    cout<< "Introduce el nuevo dorsal: "; cin>> atl.dorsal;
    cout<< "\nIntroduce el nuevo nombre: "; cin>> atl.nombre;
    cout<< "\nIntroduce los nuevos apellidos: "; cin>> atl.apellidos;
    //Modificacion 2, Ahora podremos modificar el pais
    cout<< "\nIntroduce el nuevo pais: "; cin>> atl.pais;

    //Usamos el metodo insertar, que lo coloca ordenada y correctamente

     insertar(atl);
        if(!fichero.fail()){
            cout<<"\n\nModificando";Sleep(1000);cout<<".";Sleep(1000);cout<<".";Sleep(1000);cout<<".";
            cout << "\nAtleta modificado correctamente!";
        }
}

}

void Maraton::eliminar(int dor)
{
    int pos=0;
    bool encontrado = false;
    Atleta atl;
    fichero.seekg(sizeof(int),ios::beg);
    //Encontrar el indice del atleta a eliminar

    while(!encontrado && pos<numAtletas)
    {

        fichero.read((char *)&atl,sizeof(Atleta));
        if(atl.dorsal==dor)
        {
            encontrado = true;
        }
        else pos++;
    }
    if (encontrado){
        cout << "\n\nEliminando a: "<< atl.nombre<< " "<<atl.apellidos << ", "<< atl.pais<<", dorsal " << atl.dorsal;

        for (int j=pos;j<numAtletas-1;j++){
            fichero.seekg(sizeof(int) + (j+1)*sizeof(Atleta) , ios::beg); //Se posiciona en una posicion más que el atleta a borrar
            fichero.read((char*)&atl, sizeof(Atleta)); //Lee los datos del siguiente atleta al que queremos borrar

            fichero.seekp (sizeof(int) + j*sizeof(Atleta), ios::beg); //Se posiciona en el atleta a borrar (el anterior)
            fichero.write((char*)&atl, sizeof(Atleta)); //Sobreescribe sus datos por los de el atleta siguiente
        }
        numAtletas--; //Resta 1 al numero de atletas para borrar la posicion sobrante
        fichero.seekp(0, ios::beg);
        fichero.write((char *)&numAtletas, sizeof(int)); //Escribe el nuevo numero de atletas
        if(fichero.fail())
            cout << "\nError en la operacion del fichero!";
        else
             cout<<"\n\nEliminando";Sleep(1000);cout<<".";Sleep(1000);cout<<".";Sleep(1000);cout<<".";
            cout << "\nAtleta eliminado con exito!";

    }
    else
        cout<< "/nError: Este dorsal no existe.";
}


void Maraton::eliminarsilencioso(int dor) //Metodo creado para que al eliminar en el metodo modificar, no salga texto
{
    int pos=0;
    bool encontrado = false;
    Atleta atl;
    fichero.seekg(sizeof(int),ios::beg);
    //Encontrar el indice del atleta a eliminar

    while(!encontrado && pos<numAtletas)
    {

        fichero.read((char *)&atl,sizeof(Atleta));
        if(atl.dorsal==dor)
        {
            encontrado = true;
        }
        else pos++;
    }
    if (encontrado){


        for (int j=pos;j<numAtletas-1;j++){
            fichero.seekg(sizeof(int) + (j+1)*sizeof(Atleta) , ios::beg); //Se posiciona en una posicion más que el atleta a borrar
            fichero.read((char*)&atl, sizeof(Atleta)); //Lee los datos del siguiente atleta al que queremos borrar

            fichero.seekp (sizeof(int) + j*sizeof(Atleta), ios::beg); //Se posiciona en el atleta a borrar (el anterior)
            fichero.write((char*)&atl, sizeof(Atleta)); //Sobreescribe sus datos por los de el atleta siguiente
        }
        numAtletas--; //Resta 1 al numero de atletas para borrar la posicion sobrante
        fichero.seekp(0, ios::beg);
        fichero.write((char *)&numAtletas, sizeof(int)); //Escribe el nuevo numero de atletas

    }


}


void Maraton::mostrarClasificacion(){

    fichero.seekg(sizeof(int),ios::beg);
    Atleta aTemp;
    Corredor cTemp;
    Clasificacion clasi;

    for(int i=0; i<numAtletas; i++)
    {
        fichero.read((char *)&aTemp, sizeof(Atleta));
        cTemp.dorsal = aTemp.dorsal;
        cTemp.indice = i+1;//Para mostrarlo igual que en inscripciones
        cTemp.marca = marcas(rand()%5000);
        clasi.anadircorredor(cTemp);

        //Pasa las marcas del objeto corredor al objeto atleta

        aTemp.marca = cTemp.marca;//guardar marca en fichero
        fichero.seekp(sizeof(int)+i*sizeof(Atleta),ios::beg);
        fichero.write((char *)&aTemp, sizeof(Atleta));
    }
 clasi.OrdenacionBurbuja();
    cout << "MARCA\t\tDORSAL\tPAIS\tNOMBRE\n";
    for(int j=0; j<clasi.numcorredores(); j++)
    {
        cTemp = clasi.consultar(j);
        fichero.seekg(sizeof(int)+(cTemp.indice-1)*sizeof(Atleta),ios::beg);//Obtener el correspondiente atleta del fichero
        fichero.read((char *)&aTemp,sizeof(Atleta));

        aTemp.posicion = j+1;// guardar posicion i+1 en atleta fichero
        fichero.seekp(sizeof(int)+(cTemp.indice-1)*sizeof(Atleta),ios::beg);
        fichero.write((char *)&aTemp,sizeof(Atleta));

        int h,m,s;
        marcaDisplay(cTemp.marca,h,m,s);
        cout <<aTemp.posicion<<") "<<h<<"h "<<m<<"m "<<s<<"s \t"<<aTemp.dorsal<<"\t"<<aTemp.pais<<"\t"<<aTemp.nombre<<" "<<aTemp.apellidos<<endl;
    }
}



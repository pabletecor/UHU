#include <iostream>
#include <fstream>
#include <cstring>
#include <conio.h>
#include "maraton.h"
#include <stdlib.h>
#include <windows.h>
using namespace std;

int main()
{
    char p[]= "huelvaorigen.dat" , r[]= "huelvadestino.dat";
    Maraton M(p,r);
    int opcion;

    do
    {
        system ("cls");
        cout << "Maraton de Huelva"<<endl
             << "------------------------------"<<endl
             << "Atletas: "<<M.getNumAtletas()<<endl
             << "\n1. Consulta de inscripciones\n2. Inscripciones a la maraton\n3. Modificar una inscripcion\n4. Eliminar una inscripcion\n5. Mostrar clasificacion\t\n6. Salir\n\nIndique la opcion deseada: ";
        cin>>opcion;

        switch(opcion) {

        case 1: {
            system("cls");
            cadena pais;
            cout<< "Indique el pais a consultar: (escriba * Para consultarlos todos)";
            cin>>pais;
            cout<< "\n";

            system("cls");
            M.consultar(pais);
            system("pause");
        }
        break;

        case 2:{
        system("cls");
        Atleta atl;
        cout<< "Indique los datos del nuevo atleta:\n\n";

        //Modificacion 1: el dorsal se asigna automaticamente
        //cout<< "Dorsal -> "; cin>> atl.dorsal;
        int i = M.getNumAtletas() + 1;
        atl.dorsal = i;
        cout<< "\nPais -> "; cin>> atl.pais;
        cout<< "\nNombre -> "; cin>> atl.nombre;
        cout<< "\nApellidos -> "; cin>> atl.apellidos;
        atl.marca=0; atl.posicion=0;

        M.insertar(atl);
        system("pause");
        }

        break;

        case 3: {
        system("cls");
        int dorsal;
        cout<< "Indique el dorsal del atleta a modificar: \n";
        cin>> dorsal;
        M.modificar(dorsal);
        system("pause");
        }

        break;

        case 4:{
        system("cls");
        int dorsal;
        cout<< "Indique el dorsal del atleta a eliminar: \n";
        cin>> dorsal;
        M.eliminar(dorsal);

        }

        break;

        case 5:{

        system("cls");
        cout<<"\n\nSimulando Maraton";Sleep(1000);cout<<".";Sleep(1000);cout<<".";Sleep(1000);cout<<".\n\n";
        M.mostrarClasificacion();
        system("pause");
        }

        break;

        case 6:

            cout<< "\nAdios!";

        break;

        default:
            cout<< "\nElige una opcion correcta.";
        }

}while(opcion != 6);
    return 0;

system("pause");
}

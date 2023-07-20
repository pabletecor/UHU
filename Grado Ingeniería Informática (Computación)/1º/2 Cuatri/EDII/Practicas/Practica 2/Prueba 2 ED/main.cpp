#include "oficina.h"
#include <iostream>
#include <windows.h>
using namespace std;

int main(){
    int opcion;
    oficina ofi;
    tecnico t;
    declarante d;
    do{
    system("cls");

    cout<<"\n\tOficina de Hacienda de Huelva"
        <<"\n____________________________________________________________"
        <<"\n1.- Leer fichero (rescatar copia)"
        <<"\n2.- Insertar tecnico"
        <<"\n3.- Insertar declarante"
        <<"\n4.- Retirar  tecnico"
        <<"\n5.- Atender Declarante"
        <<"\n6.- Mostrar oficina"
        <<"\n7.- Estadisticas de tiempo"
        <<"\n8.- Volcar fichero (crear copia)"
        <<"\n9.- Primera persona que llego a la oficina"
        <<"\n10.-Eliminar Declarante"
        <<"\n11.- Salir"
        <<"\n\n\nPulse la opcion deseada: ";

    cin>>opcion;


        switch(opcion){
            case 1:{
                system("cls");

                cout<<"\nAbriendo la oficina";Sleep(1000);cout<<".";Sleep(1000);cout<<".";Sleep(1000);cout<<".\n\n";
                ofi.AbrirOficina("inicial.dat");
                cout<<"\n\n";
                break;
            }

            case 2:{
                system("cls");

                cout << "\nInserte los datos del nuevo tecnico: ";

                cout << "\n\tCodigo: ";
                cin >> t.Codigo;

                cout << "\n\tEspecialidad: ";
                cin >> t.Especialidad;

                cout << "\n\tNombre: ";
                cin >> t.Nombre;

                cout << "\n\tApellido: ";
                cin >> t.Apellidos;


                ofi.IncorporarTecnico(t);

                cout<< "\n\nIncorporando";Sleep(1000);cout<<".";Sleep(1000);cout<<".";Sleep(1000);cout<<".\n\n";

                cout<<"\n\n\nTecnico incorporado";

                cout<<"\n\n";

                break;
            }

            case 3:{
                system("cls");

                cout << "\nInserte los datos del nuevo declarante: ";

                cout << "\n\tDNI: ";
                cin >> d.Dni;

                cout << "\n\tNombre: ";
                cin >> d.Nombre;

                cout << "\n\tApellido: ";
                cin >> d.Apellidos;

                cout << "\n\tEdad: ";
                cin >> d.Edad;

                cout << "\n\tEspecialidad: ";
                cin >> d.Especialidad;

                cout << "\n\tIndique la hora de llegada \n\t\t(Formato: indique la suma de minutos totales, es decir, para las 11.30, seria 11*60+60=690): ";
                cin >> d.HoraLlegada;


                bool incorporado=ofi.IncorporarDeclarante(d);

                if(incorporado)
                    {
                        cout<< "\n\nIncorporando";Sleep(1000);cout<<".";Sleep(1000);cout<<".";Sleep(1000);cout<<".\n\n";

                    cout<<"\n\n\tDeclarante incorporado";

                }
                else cout<<"\n\n\tError al incoporar";

                cout<<"\n\n";

                break;
            }

            case 4:{
                system("cls");

                cout<<"\nInserte el codigo del tecnico a retirar:";
                int code;
                cin >> code;

                cout << "\nRetirando tecnico";
                bool retirado=ofi.RetirarTecnico(code);

                if(retirado)
                    cout<<"\n\tTecnico retirado";
                else
                    cout<<"\n\tImposible retirar el tecnico";

                cout<<"\n\n";

                system("pause");
                break;
            }

            case 5:{
                system("cls");
                int codigo;
                cout<<"\nIndique que tecnico ha atendido al declarante: (codigo)";
                cin>>codigo;

                bool atendido=ofi.AtenderDeclarante(codigo);

                if (atendido){
                    cout<<"\n\tDeclarante atendido";
                }

                else{
                    cout<<"\n\tError al atender el declarante";
                }

                cout<<"\n\n";

                system("pause");
                break;
            }

            case 6:{
                system("cls");

                ofi.Mostrar();

                cout<<"\n\n";

                system("pause");
                break;
            }

            case 7:{
                system("cls");
                int esp, horaactual, tiempo;
                cout<<"\nIndique la especialidad de la que desea saber la media de tiempo de espera,\n";
                cout<<"\n\tSi desea la media de toda la oficina, pulse -1";

                cin >> esp;

                cout<<"\n\n\tIndique la hora actual"
                    << "\n\t(Indique la suma de minutos totales, es decir, para las 11.30, seria 11*60+60=690): ";
                cin>>horaactual;

                tiempo=ofi.TiempoEspera(esp, horaactual);
                cout<<"\n\n\tTiempo medio de espera para la especialidad " << esp << ": " << tiempo/60 <<"h " << tiempo%60 <<"min\n";

                cout<<"\n\n";
                system("pause");
                break;
            }

            case 8:{
                system("cls");
                int opcion;
                cout<<"Introduzca 1 si desea sobreescribir los datos del fichero ya existente (\"inicial.dat\"), o 2 para crear uno nuevo (\"oficina.dat\")";
                cin>> opcion;

                cout<<"\nVolcando los datos en el fichero.\n\n";

                if(opcion==1){
                    ofi.VolcarOficina("inicial.dat");
                }

                else{
                    ofi.VolcarOficina("oficina.dat");
                }

                cout<<"\n\n";

                system("pause");
                break;
            }

            case 9:{
            system("cls");
            cout<< "Los datos de la persona que lleva mas tiempo esperando son: \n\n";
            cout<< "\tEspecialidad\tApellidos\tNombre\t\tDNI\tHora llegada\tEdad";
            cout<< "\n_______________________________________________________________________";
            ofi.primerocola();

            system("pause");
            break;

            }

            case 10:{
            system("cls");
            char ID[20];
            cout<< "Indique el DNI del declarante a eliminar: \n";
            cin>> ID;
            ofi.eliminardec(ID);
            system("pause");

            break;
            }

            default:{
                cout<<"\n\nAdios!";
                opcion=-1;

                cout<<"\n\n";

                break;
            }

        }
    }while(opcion!=-1);


    return 0;
}

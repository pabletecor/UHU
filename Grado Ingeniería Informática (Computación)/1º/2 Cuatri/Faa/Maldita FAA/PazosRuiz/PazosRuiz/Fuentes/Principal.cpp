#include "TestOrdenacion.h"
#include <iostream>
#include <string>
#include <fstream>
#include <iomanip>
#include <iostream>
#include <windows.h>
#include "TestBusqueda.h"

using namespace std;

int menu0()
{
    int opc;
    system("cls");
    cout << "*** FAA. Practica 3. Curso 20/21 *** \t Manuel Angel Pazos Ruiz\n \t\t\t\t\t Alvaro Carrillo Casado" << endl;
    cout << "              *** MENU PRINCIPAL ***" << endl;
    cout << "\n";
    cout << "\t1.- MENU ORDENACION\n";
    cout << "\t2.- MENU BUSQUEDA\n";
    cout << "\t0.- SALIR\n";
    cout << "\t-----------\n";
    cout << "\tElige opcion:";
    cin >> opc;

    return opc;
}

int menu1()
{
    int opc1;
    system("cls");
    cout << "              *** MENU PRINCIPAL ***" << endl;
    cout << "*** ANALISIS EXPERIMENTAL DE ALGORITMOS DE ORDENACION ***\n";
    cout << "\n";
    cout << "\t1. Probar los metodos de ordenacion\n";
    cout << "\t2. Obtener el caso medio de un metodo de ordenacion\n";
    cout << "\t3. Comparar dos metodos\n";
    cout << "\t0. Salir\n";
    cout << "\t--------\n";
    cout << "\tElija una opcion: ";
    cin >> opc1;

    return opc1;
}

void menu2()
{
    system("cls");
    cout << "\t*** Metodo a estudiar para el caso medio *** " << endl;
    cout << "\t0: Burbuja\n";
    cout << "\t1: Inserccion\n";
    cout << "\t2: Seleccion\n";
    cout << "\t---------\n";
    cout << "\tElige Opcion: ";
}

void menu3()
{
    system("cls");
    cout << "\t*** COMPARACION DE DOS METODOS DE ORDENACION *** " << endl;
    cout << "Selecciona los dos metodos a comparar" << endl;
    cout << "\t0: Burbuja\n";
    cout << "\t1: Inserccion\n";
    cout << "\t2: Seleccion\n";
    cout << "\t---------\n";
    cout << "\tElige metodo 1: ";
}

int menu4()
{
    int opc2;
    system("cls");
    cout << "              *** MENU BUSQUEDA ***" << endl;
    cout << "\n";
    cout << "\t1.- Probar los metodos de busqueda\n";
    cout << "\t2.- Obtener el caso medio de un metodo de busqueda\n";
    cout << "\t3.- Comparar dos metodos\n";
    cout << "\t4.- Comparar todos los metodos \n";
    cout << "\t0.- Volver al menu principal \n";
    cout << "\t-----------\n";
    cout << "\tElige opcion:";
    cin >> opc2;

    return opc2;
}

void menu5()
{
    system("cls");
    cout << "              *** Metodo a estudiar para el caso medio ***" << endl;
    cout << "\n";
    cout << "\t0.- Busqueda Secuencial Iterativa\n";
    cout << "\t1.- Busqueda Binaria Iterativa\n";
    cout << "\t2.- Busqueda de Interpolacion Iterativas\n";
    cout << "\t3.- Busqueda de kesimo menor directa\n";
    cout << "\t4.- Busqueda de kesimo menor iterativo\n";
    cout << "\t5.- Salir\n";
    cout << "\t-----------\n";
    cout << "\tElige opcion:";
}

void menu6()
{
    system("cls");
    cout << "\t*** COMPARACION DE DOS METODOS DE BUSQUEDA *** " << endl;
    cout << "Selecciona los dos metodos a comparar" << endl;
    cout << "\t0: Busqueda Secuencial Iterativa\n";
    cout << "\t1: Busqueda Binaria Iterativa\n";
    cout << "\t2: Busqueda de Interpolacion Iterativa\n";
    cout << "\t3.- Busqueda de kesimo menor directa\n";
    cout << "\t4.- Busqueda de kesimo menor iterativo\n";
    cout << "\t---------\n";
    cout << "\tElige metodo 1: ";
}


int main()
{
    int opc;
    do
    {
        opc = menu0();
        switch (opc)
        {
            case 0: break;
            case 1: int opc1;
                do
                {
                    TestOrdenacion testO;
                    opc1 = menu1();
                    switch (opc1)
                    {
                        case 0: break;
                        case 1: system("cls");
                            testO.comprobarMetodosOrdenacion();
                            break;

                        case 2: int opc11;
                            do
                            {
                                menu2();
                                cin >> opc11;
                                if (opc<0 || opc>2)
                                    cout << "\nOpcion incorrecta";
                            } while (opc<0 || opc>2);
                            
                            testO.casoMedio(opc11);
                            break;

                        case 3: int metodo1, metodo2;
                            do
                            {
                                menu3();
                                cin >> metodo1;
                                cout << "\tElige metodo 2: ";
                                cin >> metodo2;
                                if (metodo1 < 0 || metodo2 < 0 || metodo1>2 || metodo2>2)
                                {
                                    cout << "\nOpcion incorrecta" << endl;
                                    system("pause");
                                }   
                                if (metodo1 == metodo2)
                                {
                                    cout << "No se puede comparar dos metodos iguales" << endl;
                                    system("pause");
                                }      
                            } while (metodo1 < 0 || metodo2 < 0 || metodo1>2 || metodo2>2 || metodo1 == metodo2);

                            system("cls");
                            testO.comparar(metodo1, metodo2);
                            break;

                        default: cout << "\nOpcion incorrecta";
                            system("pause");
                    }
                } while (opc1 != 0);
                break;

            case 2: int opc2;
            do
            {
                TestBusqueda testB;
                opc2 = menu4();
                switch (opc2)
                {
                case 0: break;
                case 1: system("cls");
                    testB.comprobarMetodosBusqueda();
                    break;

                case 2: int opc21;
                    do
                    {
                        system("cls");
                        menu5();
                        cin >> opc21;
                        if (opc < 0 || opc>5)
                            cout << "\nOpcion incorrecta";
                    } while (opc < 0 || opc>5);

                    if (opc21 == 5)
                        break;
                    testB.casoMedio(opc21);
                    break;

                case 3: int metodo1, metodo2;
                    do
                    {
                        menu6();
                        cin >> metodo1;
                        cout << "\tElige metodo 2: ";
                        cin >> metodo2;
                        if (metodo1 < 0 || metodo2 < 0 || metodo1>4 || metodo2>4)
                            cout << "\nOpcion incorrecta" << endl;
                        if (metodo1 == metodo2)
                            cout << "No se puede comparar dos metodos iguales" << endl;
                    } while (metodo1 < 0 || metodo2 < 0 || metodo1>4 || metodo2>4 || metodo1 == metodo2);

                    system("cls");
                    testB.comparar(metodo1, metodo2);
                    break;

                case 4: system("cls");
                    testB.compararTodos();
                    break;

                default: cout << "\nOpcion incorrecta";
                    system("pause");
                }
            } while (opc2 != 0);
                break;

            default: cout << "\nOpcion incorrecta";
                system("pause");
        }
    } while (opc != 0);
    
  
    return 0;
};
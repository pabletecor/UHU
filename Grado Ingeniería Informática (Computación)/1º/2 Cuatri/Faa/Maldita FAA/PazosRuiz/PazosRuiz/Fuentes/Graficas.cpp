     /* 
 * Clase Graficas, contiene métodos crear los ficheros de comandos para dibujar
 * la gráfica que corresponda.
 */
#include "Graficas.h"
#include "Constantes.h"
#include <fstream>
#include <iostream>
#include <cstdlib>
#include <cstring>
#include <string>
#include <iomanip>
#include <windows.h>

using namespace std;

/*
 * Método generarGraficaMEDIO, genera el fichero de comandos para GNUPlot
 * y dibuja la gráfica del caso medio de un método de
 * ordenación y su ajuste a la función correspondiente.
 * param nombre_metodo: metodo de ordenacion.
 * param orden: Orden del metodo de ordenacion.
 */
void Graficas::generarGraficaMEDIO(string nombre_metodo,int orden)
{
//** ESCRIBIR PARA COMPLETAR LA PRACTICA **//
    ofstream f("CmdMedio.gpl");
    f<<"\t#ESTE ES UN SCRIP DE GNUPLOT PARA ESTUDIO DE 1 METODO"<< endl;
    f<<"set title \"" << nombre_metodo << "\""<<endl;
    f<<"set key top left vertical inside" << endl;
    f<<"set grid"<<endl;
    f<<"set xlabel \"Talla (n)\""<<endl;
    f<<"set ylabel \"Tiempo (ms)\""<<endl;
   

    if (orden == CUADRADO)
    {
        f << "Cuadrado(x) = a*x*x +b*x + c" << endl;
        f << "fit Cuadrado(x) \"" << nombre_metodo << ".dat\" using 1:2 via a,b,c" << endl;
        f << "plot \"" << nombre_metodo << ".dat\" using 1:2 title \"" << nombre_metodo << "\", Cuadrado (x)" << endl;
    } 
    else if(orden == NlogN)
    {
        f << "NLOGN(x) = a*x*log(x) +b*x + c" << endl;
        f << "fit NLOGN(x) \"" << nombre_metodo << ".dat\" using 1:2 via a,b,c" << endl;
        f << "plot \"" << nombre_metodo << ".dat\" using 1:2 title \"" << nombre_metodo << "\", NLOGN(x)" << endl;
    }
    else if (orden == N)
    {
        f << "N(x) = a*x +b" << endl;
        f << "fit N(x) \"" << nombre_metodo << ".dat\" using 1:2 via a,b" << endl;
        f << "plot \"" << nombre_metodo << ".dat\" using 1:2 title \"" << nombre_metodo << "\", N(x)" << endl;
    }
    else if (orden == LOGN)
    {
        f << "LOGN(x) = a*log(x) +b" << endl;
        f << "fit LOGN(x) \"" << nombre_metodo << ".dat\" using 1:2 via a,b" << endl;
        f << "plot \"" << nombre_metodo << ".dat\" using 1:2 title \"" << nombre_metodo << "\", LOGN(x)" << endl;
    }
    else if (orden == LOGLOGN)
    {
        f << "LOGLOGN(x) = a*log(log(x)) +b" << endl;
        f << "fit LOGLOGN(x) \"" << nombre_metodo << ".dat\" using 1:2 via a,b" << endl;
        f << "plot \"" << nombre_metodo << ".dat\" using 1:2 title \"" << nombre_metodo << "\", LOGLOGN(x)" << endl;
    }
    f << "set logscale y" << endl;
    f<<"set terminal pdf" << endl;
    f<<"set output\"" << nombre_metodo << ".pdf\""<< endl;
    f<<"replot" << endl;
    f<<"pause-1 \"Pulsa Enter para continuar...\""<< endl;

    f.close();

    system("start CmdMedio.gpl"); system("cls");
    cout << "Grafica guardada en el fichero " << nombre_metodo << ".pdf" << endl;
    cout << "Fichero de comandos guardado en el fichero CmdMedio.gpl" << endl;
}

/*
 * Método generarGraficaCMP, genera el fichero de comandos para GNUPlot.
 * param nombre1: es el nombre del fichero de datos del primer método de ordenación
 * param nombre2: es el nombre del fichero de datos del segundo método de ordenación
 */
void  Graficas::generarGraficaCMP(string nombre1,string nombre2)
{
//** ESCRIBIR PARA COMPLETAR LA PRACTICA **//
    ofstream f("grafica.gpl");
    f<<"\t#ESTE ES UN SCRIP DE GNUPLOT PARA ESTUDIO DE 2 METODOS"<< endl;
    f<<"set title \" Comparacion tiempos " << nombre1 << " y " << nombre2 << "\""<<endl;
    f<<"set key top left vertical inside" << endl;
    f<<"set grid"<<endl;
    f<<"set xlabel \"Talla (n)\""<<endl;
    f<<"set ylabel \"Tiempo (ms)\""<<endl;
    f << "set logscale y" << endl;//examenjunio21
    f<<"plot \"" << nombre1 << ".dat\" using 1:2 with lines title \"" << nombre1 << "\",\"" << nombre2 << ".dat\" using 1:2 with lines title \"" << nombre2 << "\"" << endl;
    f<<"set terminal pdf" << endl;
    f<<"set output \"" << nombre1+nombre2 << ".pdf\""<< endl;
    f<<"replot" << endl;
    f<<"pause -1 \"Pulsa Enter para continuar...\""<< endl;
    f.close();

    system("grafica.gpl"); system("cls");
    cout << "Grafica guardada em el fichero " << nombre1 + nombre2 << ".pdf" << endl;
    cout << "Fichero de comandos guardado en el fichero CmdComparar.gpl" << endl;
}

void Graficas::generarGraficaTodos(vector<string> nombreAlgoritmos)
{
    //** ESCRIBIR PARA COMPLETAR LA PRACTICA **//
    ofstream f("CmdCompararTodos.gpl");
    f << "\t#ESTE ES UN SCRIP DE GNUPLOT PARA ESTUDIO DE TODOS LOS METODOS" << endl;
    f << "set title \" Comparacion tiempos de todos los algoritmos\"" << endl;
    f << "set key top left vertical inside" << endl;
    f << "set grid" << endl;
    f << "set xlabel \"Talla (n)\"" << endl;
    f << "set ylabel \"Tiempo (ms)\"" << endl << endl;
    f << "set logscale y" << endl;//examenjunio21
    f << "plot ";
    for (int i = 0; i < nombreAlgoritmos.size(); i++)
        f << "\"tBcompararTodos.dat\" using 1:" << (i + 2) << " with lines title \"" << nombreAlgoritmos[i] << "\"" << (i == nombreAlgoritmos.size() - 1 ? "\n" : ",");
    f << "\n";
    
    f << "set terminal pdf" << endl;
    f << "set output \"CompararTodos.pdf\"" << endl;
    f << "replot" << endl;
    f << "pause -1 \"Pulsa Enter para continuar...\"" << endl;
    f.close();

    system("CmdCompararTodos.gpl"); system("cls");
    cout << "Fichero de comandos guardado en el fichero CompararTodos.gpl" << endl;
}



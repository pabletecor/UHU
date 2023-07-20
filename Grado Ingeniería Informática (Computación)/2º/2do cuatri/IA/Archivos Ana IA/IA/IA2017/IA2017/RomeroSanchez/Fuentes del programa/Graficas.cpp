/*
*Clase Graficas, contiene métodos crear los ficheros de comandos para dibujar
* la gráfica que corresponda.
*/
#include "Graficas.h"



/*
* Método generarGraficaMEDIO, genera el fichero de comandos para GNUPlot
* y dibuja la gráfica del caso medio de un método de
* ordenación y su ajuste a la función correspondiente.
* param nombre_metodo: metodo de ordenacion.
* param orden: Orden del metodo de ordenacion.
*/
void Graficas::generarGraficaMEDIO(string nombre_metodo, int orden)
{
	string tipoPLT = nombre_metodo + ".plt";

	ofstream f2(tipoPLT.c_str());

	f2 << "set title \"" << nombre_metodo << "\" \n";
	f2 << "set key top left vertical inside\n";
	f2 << "set grid\n";
	f2 << "set xlabel \"Talla (n)\" \n";
	f2 << "set ylabel \"Tiempo (milisegundos)\" \n";
	f2 << "plot \"" << nombre_metodo << ".dat\" using 1:2\n";

	if (orden == 2)
	{
		//Busqueda Binaria y Ternaria -> O(log(n))
		f2 << "Logaritmica(x) =log(a*x)\n";
		f2 << "fit Logaritmica(x) \"" << nombre_metodo << ".dat\" using 1:2 via a\n";
		f2 << "plot \"" << nombre_metodo << ".dat\" using 1:2 title \"" << nombre_metodo << "\", Logaritmica(x)\n";
		f2 << "plot \"" << nombre_metodo << ".dat\" with lines\n";
	}

	else if (orden == 1)
	{
		//Busqueda Secuencial -> O(n)
		f2 << "Lineal(x) =m*x + n\n";
		f2 << "fit Lineal(x) \"" << nombre_metodo << ".dat\" using 1:2 via m,n\n";
		f2 << "plot \"" << nombre_metodo << ".dat\" using 1:2 title \"" << nombre_metodo << "\", Lineal(x)\n";
		f2 << "plot \"" << nombre_metodo << ".dat\" with lines\n";
	}

	else if (orden == 3)
	{
		//Son los metodos de ordenacion de tipo n log n
		f2 << "Logaritmica(x) = a*x*log(b*x) + c\n";
		f2 << "fit Logaritmica(x) \"" << nombre_metodo << ".dat\" using 1:2 via a,b,c\n";
		f2 << "plot \"" << nombre_metodo << ".dat\" using 1:2 title \"" << nombre_metodo << "\", Logaritmica(x)\n";
		f2 << "plot \"" << nombre_metodo << ".dat\" with lines\n";
	}

	else if (orden == 4)
	{
		//Son los metodos de ordenacion de tipo n2 ( Burbuja,inserccion,seleccion)
		f2 << "Cuadratica(x) =a*x**2 + b*x + c\n";
		f2 << "fit Cuadratica(x) \"" << nombre_metodo << ".dat\" using 1:2 via a,b,c\n";
		f2 << "plot \"" << nombre_metodo << ".dat\" using 1:2 title \"" << nombre_metodo << "\", Cuadratica(x)\n";
		f2 << "plot \"" << nombre_metodo << ".dat\" with lines\n";
	}

	f2 << "set terminal pdf\n";
	f2 << "set output \"" << nombre_metodo << ".pdf"" \n";
	f2 << "replot\n";
	f2 << "pause 5\"Pulsa Enter para continuar....";
	f2.close();
	system(tipoPLT.c_str());
}

/*
* Método generarGraficaCMP, genera el fichero de comandos para GNUPlot.
* param nombre1: es el nombre del fichero de datos del primer método de ordenación
* param nombre2: es el nombre del fichero de datos del segundo método de ordenación
*/


/*void  Graficas::generarGraficaCMP(string nombre1,string nombre2)
{
string dat1  = nombre1 + ".dat" ;
string dat2  = nombre2 + ".dat" ;
dat1.c_str();
dat2.c_str();
ofstream a("CmdComparar.plt",ios::trunc);
a << "set title " <<"\""<<" Comparacion tiempos "<<nombre1<<" y "<<nombre2<<"\"\n"<< " set key top left vertical inside " << "\n" <<" set grid "<<"\n"<< " set xlabel \"talla(n)\"" << "\n" 
<< "set ylabel \"Tiempo(ms)\" " << "\n"<<"plot "<<"\""<< dat1.c_str()<<"\"" <<"using 1:2 with lines title "<<"\""<<nombre1<<"\""<<","
<<"\""<< dat2.c_str()<<"\"" <<"using 1:2 with lines title "<<"\""<<nombre2<<"\""<<"\n"
<< "set terminal pdf \n set output "<<"\""<< nombre1<<nombre2<< ".pdf " <<"\""<< "\n replot \n pause 5 \"Pulse Enter para continuar...\"";
a.close();
system("CmdComparar.plt");
system("pause");*/


/*
set title " Comparacion tiempos insercion y seleccion"
set key top left vertical inside
set grid
set xlabel "Talla (n)"
set ylabel "Tiempo (ms)"
plot "Insercion.dat" using 1:2 with lines title "Insercion",
"Seleccion.dat" using 1:2 with lines title "Seleccion"
set terminal pdf
set output "InsercionSeleccion.pdf"
replot
pause 5 "Pulsa Enter para continuar..."
*/
//}








void  Graficas::generarGraficaCMP(string nombre1, string nombre2)
{
	string tipoPLT = (nombre1 + nombre2) + ".plt";

	ofstream f3(tipoPLT.c_str());

	f3 << "set title \"Comparacion " << nombre1 << " y " << nombre2 << "\" \n";
	f3 << "set key top left vertical inside\n"; //top left vertical: un titulo debajo de otro
	f3 << "set grid\n";
	f3 << "set xlabel\"Talla(n)\"\n";
	f3 << "set ylabel\"Tiempo(milisegundos)\"\n";
	f3 << "plot\"" << nombre1+ ".dat\" using 1:2 with lines title\"" << nombre1 << "\",\"" << nombre2 + ".dat\" using 1:2 with lines title\"" << nombre2 << "\"\n";
	f3 << "set terminal pdf\n";
	f3 << "set output \"" << nombre1 + nombre2 << ".pdf\" \n";
	f3 << "replot\n";
	f3 << "pause 5 'Pulsa enter para continuar' \n";
	f3.close();
	system(tipoPLT.c_str());
}









/* 
 * La clase TestBusqueda contiene los metodos para:
 * 1. Comprobar que los métodos de búsqueda de la clase AlgoritmosBusqueda funcionan adecuadamente.
 * 2. Calcular la eficiencia para el caso medio de un método de búsqueda,
 *    permitiendo guardar los datos e imprimir la gráfica correspondiente con ajuste al Orden de complejidad.
 * 3. Comparar el coste temporal de dos métodos de búsqueda
 *    permitiendo guardar los datos e imprimir la gráfica correspondiente.
 * 4. Comparar todos los algoritmos de búsqueda implementados.
 */
#include "TestBusqueda.h"
#include "ConjuntoInt.h"
#include "AlgoritmosBusqueda.h"
#include "Mtime.h"
#include "Constantes.h"
#include "AlgoritmosOrdenacion.h"
#include "Graficas.h"

//** ESCRIBIR PARA COMPLETAR LA PRACTICA **//
TestBusqueda::TestBusqueda()
{
	nombreAlgoritmo.push_back("SecuencialIt");
	complejidad.push_back(N);
	nombreAlgoritmo.push_back("BinariaIt");
	complejidad.push_back(LOGN);
	nombreAlgoritmo.push_back("InterpolacionIt");
	complejidad.push_back(LOGLOGN);
	nombreAlgoritmo.push_back("kesimomenorD");
	complejidad.push_back(CUADRADO);
	nombreAlgoritmo.push_back("kesimomenorit");
	complejidad.push_back(N);//examenjunio21

	
} 

TestBusqueda::~TestBusqueda(){}

double TestBusqueda::buscaArrayDeInt(int v[], int size, int clave, int metodo, int &pos)
{
	AlgoritmosBusqueda estrategia;
	Mtime t;
	LARGE_INTEGER t_inicial, t_final;
	QueryPerformanceCounter(&t_inicial);
	// Invoca al método de busqueda elegido
	switch (metodo) {
	case SECIT: pos = estrategia.busquedaSecuencialIt(v, size, clave);
		break;
	case BINIT: pos = estrategia.busquedaBinariaIt(v, size, clave);
		break;
	case ITEIT: pos = estrategia.busquedaInterpolacionIt(v, size, clave);
		break;
	case KED:  pos = estrategia.busquedaKesimoMenorD(v, size, clave);
		break;
	case KEIT:
		 pos = estrategia.busquedaKesimoMenorIt(v, size, clave);//examenjunio21
		 break;
	}
	QueryPerformanceCounter(&t_final);
	return t.performancecounter_diff(&t_final, &t_inicial) * 1000;
}

void TestBusqueda::comprobarMetodosBusqueda()
{
	cout << "Comprobacion Metodos de Busqueda" << endl;
	cout << "Introduce talla: ";
	int talla;
	cin >> talla;
	int clave;

	ConjuntoInt* v = new ConjuntoInt(talla);
	for (int metodo = 0; metodo < nombreAlgoritmo.size(); metodo++)
	{
		v->GeneraVector(talla);
		int pos;
		if (metodo == BINIT || metodo == ITEIT)
		{
			AlgoritmosOrdenacion a;
			a.ordenaInsercion(v->getDatos(), talla);
		}
		cout << endl << endl << "Array ordenado con metodo " << nombreAlgoritmo[metodo] << ":" << endl << endl;
		v->escribe();
		
		if (metodo == SECIT || metodo == BINIT || metodo == ITEIT)
		{
			cout << "Clave a buscar: ";

		}
		else
			cout << "Introduce el k-menor(clave) a buscar:";
		cin >> clave;
		
		double tiempo = buscaArrayDeInt(v->getDatos(), talla, clave, metodo, pos);
		if (pos == -1)
			cout << "La clave no se encuentra en el vector" << endl;
		else
			cout << "Posicion = " << pos << " Tiempo = " << tiempo << endl;
	}
	system("pause");
}

void TestBusqueda::casoMedio(int metodo)
{
	double tiempo;
	int pos = 0;

	ofstream f(nombreAlgoritmo[metodo] + ".dat");
	system("cls");
	cout << endl << "\tBusqueda " << nombreAlgoritmo[metodo];
	cout << ". Tiempos de ejecucion promedio \n\n" << endl;
	cout << "\tTalla\t\tTiempo (mseg)" << endl;

	for (int talla = tallaIni; talla <= tallaFin; talla += incTalla)
	{
		ConjuntoInt* v = new ConjuntoInt(talla);
		tiempo = 0;
		for (int i = 0; i < NUMREPETICIONES; i++)
		{
			v->GeneraVector(talla);
			if (metodo != SECIT && metodo!=KEIT)
			{
				AlgoritmosOrdenacion a;
				a.ordenaInsercion(v->getDatos(), talla);
			}
			int clave;
			if (metodo == SECIT || metodo == BINIT || metodo == ITEIT)
			{

				clave = v->generaKey();

			}
			else
				clave = v->generaKey_Menor();

			tiempo += buscaArrayDeInt(v->getDatos(), talla, clave, metodo, pos);
			v->vaciar();
		}

		tiempo /= NUMREPETICIONES;
		cout << "\t" << talla << "\t\t" << tiempo << endl;
		f << talla << "\t" << tiempo << "\n";
		delete v;
	}
	f.close();

	//Generacion de graficas
	char opt;
	cout << "\n\nGenerar grafica(s,n): ";
	cin >> opt;
	switch (opt)
	{
		Graficas g;
	case 's':
	case 'S':
		g.generarGraficaMEDIO(nombreAlgoritmo[metodo], complejidad[metodo]);
		break;

	default: cout << "Grafica no guardada en el fichero" << endl;
	}
	system("pause");
}

void TestBusqueda::comparar(int metodo1, int metodo2)
{
	double tiempo1, tiempo2;
	int pos;


	system("cls");
	cout << "Busqueda " << nombreAlgoritmo[metodo1] << " y " << nombreAlgoritmo[metodo2] << ". Tiempos de ejecucion promedios" << endl << endl;
	cout << "\t\t\t" << nombreAlgoritmo[metodo1] << "\t\t" << nombreAlgoritmo[metodo2] << endl;
	cout << "\tTalla\t\tTiempo (mseg)\t\tTiempo(mseg)" << endl;
	
	ofstream f( nombreAlgoritmo[metodo1] + nombreAlgoritmo[metodo2] + ".dat");

	for (int talla = tallaIni; talla <= tallaFin; talla += incTalla)
	{
		ConjuntoInt* v = new ConjuntoInt(talla);
		tiempo1 = tiempo2 = 0;

		for (int i = 0; i < NUMREPETICIONES; i++)
		{
			v->GeneraVector(talla);
			if ((metodo1 == BINIT) || (metodo1 == ITEIT))
			{
				AlgoritmosOrdenacion a;
				a.ordenaInsercion(v->getDatos(), talla);
			}
			int clave;
			if (metodo1 == SECIT || metodo1 == BINIT || metodo1 == ITEIT )
			{

				clave = v->generaKey();

			}
			else
				clave = v->generaKey_Menor();

			tiempo1 += buscaArrayDeInt(v->getDatos(), talla, clave, metodo1, pos);
			v->GeneraVector(talla);
			if ((metodo2 == BINIT) || (metodo2 == ITEIT))
			{
				AlgoritmosOrdenacion a;
				a.ordenaInsercion(v->getDatos(), talla);
			}
			
			if ( metodo2 == SECIT || metodo2 == BINIT || metodo2 == ITEIT)
			{

				clave = v->generaKey();

			}
			else
				clave = v->generaKey_Menor();
			tiempo2 += buscaArrayDeInt(v->getDatos(), talla, clave, metodo2, pos);
			v->vaciar();
		}
		tiempo1 /= NUMREPETICIONES;
		tiempo2 /= NUMREPETICIONES;
		cout << "\t" << talla << "\t\t" << tiempo1 << "\t\t\t" << tiempo2 << endl;
		f << talla << "\t" << tiempo1 << "\t" << tiempo2 << "\n";
	}
	f.close();

	//Genera grafica
	char opt;
	cout << "\n\nGenerar grafica(s,n): ";
	cin >> opt;

	switch(opt)
	{
		Graficas g;
		case 's':
		case 'S':
			g.generarGraficaCMP(nombreAlgoritmo[metodo1], nombreAlgoritmo[metodo2]);
			break;

		default: cout << "Grafica no guardada en el fichero" << endl;
	}
	system("pause");
}

void TestBusqueda::compararTodos()
{
	system("cls");
	cout << "Comparativa de todos los algoritmos. Tiempos de ejecucion promedios" << endl << endl;
	cout << "\t\t\t";
	for (int j = 0; j < nombreAlgoritmo.size(); j++)
	{
		cout << nombreAlgoritmo[j];
		if (j == nombreAlgoritmo.size() - 1)
			cout << "\n";
		else
			cout << "\t\t";
	}

	cout << endl << "\tTalla\t\tTiempo (mseg)\t\tTiempo(mseg)\t\tTiempo(mseg)\t\tTiempo(mseg)\t\tTiempo(mseg)" << endl;

	ofstream f("tBcompararTodos.dat");

	double* tiempo = new double[nombreAlgoritmo.size()];
	int pos;
	for (int talla = tallaIni; talla <= tallaFin; talla += incTalla)
	{
		ConjuntoInt* v = new ConjuntoInt(talla);
		for (int a = 0; a < nombreAlgoritmo.size(); a++)
		{
			tiempo[a] = 0.0;
			for (int i = 0; i < NUMREPETICIONES; i++)
			{
				v->GeneraVector(talla);
				if (a == BINIT || a == ITEIT)
				{
					AlgoritmosOrdenacion al;
					al.ordenaInsercion(v->getDatos(), talla);
				}
				/***EXAJUN21***/
				if (a == KED || a == KEIT)
					tiempo[a] += buscaArrayDeInt(v->getDatos(), talla, v->generaKey_Menor(), a, pos);
				else
					tiempo[a] += buscaArrayDeInt(v->getDatos(), talla, v->generaKey(), a, pos);
				v->vaciar();
			}
			tiempo[a] /= NUMREPETICIONES;
		}
		delete v;

		cout << "\t" << talla << "\t\t";
		for (int j = 0; j < nombreAlgoritmo.size(); j++)
		{
			cout << tiempo[j];
			if (j == nombreAlgoritmo.size() - 1)
				cout << "\n";
			else
				cout << "\t\t";
		}
		f << talla << "\t";
		for (int a = 0; a < nombreAlgoritmo.size(); a++)
		{
			f << tiempo[a];
			if (a == nombreAlgoritmo.size() - 1)
				f << "\n";
			else
				f << "\t";
		}
	}
	f.close();

	//Genera grafica
	char opt;
	cout << "\n\nGenerar grafica(s,n): ";
	cin >> opt;

	switch (opt)
	{
		Graficas g;
	case 's':
	case 'S':
		g.generarGraficaTodos(nombreAlgoritmo);
		break;

	default: cout << "Grafica no guardada en el fichero" << endl;

	}
	system("pause");
}
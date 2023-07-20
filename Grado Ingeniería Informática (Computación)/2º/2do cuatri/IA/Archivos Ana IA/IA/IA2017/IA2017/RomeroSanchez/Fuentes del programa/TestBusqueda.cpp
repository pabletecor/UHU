/* 
 * La clase TestBusqueda contiene los metodos para:
 * 1. Comprobar que los métodos de búsqueda de la clase AlgoritmosBusqueda funcionan adecuadamente.
 * 2. Calcular la eficiencia para el caso medio de un método de búsqueda,
 *    permitiendo guardar los datos e imprimir la gráfica correspondiente con ajuste al Orden de complejidad.
 * 3. Comparar el coste temporal de dos métodos de búsqueda
 *    permitiendo guardar los datos e imprimir la gráfica correspondiente.
 */
#include "TestBusqueda.h"
#include "Constantes.h"
#include "AlgoritmosBusqueda.h"
#include "AlgoritmosOrdenacion.h"
#include "ConjuntoInt.h"
#include "Graficas.h"
#include "Mtime.h"


TestBusqueda::TestBusqueda()
{
	nombreAlgoritmo.push_back("SecuencialI");
	nombreAlgoritmo.push_back("BinariaR");
	nombreAlgoritmo.push_back("TernariaR");
} 
TestBusqueda::~TestBusqueda()
{
}

/*
 * Busca en un array de enteros según el método indicado
 * param v: el array de enteros a ordenar
 * param size: tamaño del array de enteros a ordenar
 * param metodo: Metodo de búsqueda a utilizar
 * return Tiempo empleado en la busqueda (en milisegundos)
 */
double TestBusqueda::buscaEnArrayDeInt(int key,int v[],int size,int metodo,int &posicion) 
{
    AlgoritmosBusqueda strategia;
		Mtime t;
		LARGE_INTEGER t_inicial, t_final;
		QueryPerformanceCounter(&t_inicial);
// Invoca al método de búsqueda elegido
    switch (metodo)
		{
        case SECUENCIALIt: posicion=strategia.busquedaSecuencialIt(v, size, key);
            break;
				case BINARIARc: posicion=strategia.busquedaBinariaRc(v, size, key); 
            break;
				case TERNARIARc: posicion=strategia.busquedaTernariaRc(v, size, key); 
            break;
		}
		QueryPerformanceCounter(&t_final);
    return t.performancecounter_diff(&t_final, &t_inicial) * 1000000;   
}

/*
 * Comprueba los metodos de búsqueda
 */
void TestBusqueda::comprobarMetodosBusqueda(){
	int talla;
	cout<<endl<<endl<<"Introduce la talla: ";
	cin>>talla; 
	system("cls");
	ConjuntoInt *v= new ConjuntoInt(talla); 
	AlgoritmosOrdenacion AlOrdena;
	for (int metodo = 0; metodo < nombreAlgoritmo.size(); metodo++){
		v->GeneraVector(talla);
		/* Ordenar array*/
		AlOrdena.ordenaInsercion(v->getDatos(),talla);
		cout <<endl<<endl<< "vector para el metodo "<<nombreAlgoritmo[metodo]<< ":"<<endl<<endl;
		v->escribe();
		int key;
		cout<<endl<<endl<<"Introduce la clave a buscar: ";
		cin>>key; 
		int posicion;
		buscaEnArrayDeInt(key,v->getDatos(),talla,metodo,posicion);
		cout<<endl<<endl<<"posicion de "<<key<<" buscado con el metodo "<<nombreAlgoritmo[metodo]<< " : "<<posicion<<endl<<endl;
		v->vaciar(); 
		system("pause");
		system("cls");
	}
	system("cls");
}
    
/*
 * Compara dos metodos de búsqueda. 
 * Permite las opciones de crear el fichero de datos y la gráfica correspondiente.
 * param metodo1: Primer metodo de búsqueda a comparar
 * param metodo2: Segundo metodo de búsqueda a comparar
 */
void TestBusqueda::comparar(int metodo1, int metodo2)
{
	system("cls");
	LARGE_INTEGER t_inicial, t_final;
	AlgoritmosOrdenacion AlOrdena;
	AlgoritmosBusqueda strategia;
	Graficas g;
	Mtime t;
	int talla[10];
	double tiemposM1[10], tiemposM2[10];
	int posArray = 0, key;

	string nombreMetodo1 = nombreAlgoritmo[metodo1];
	string nombreMetodo2 = nombreAlgoritmo[metodo2];

	cout << "\n\n\t*** Comparacion " << nombreMetodo1 << " y " << nombreMetodo2 << " ***" << endl << endl;
	cout << "\t\tTiempo de ejecucion promedio" << endl << endl << endl;
	cout << "\t\t\t" << nombreMetodo1 << "\t" << nombreMetodo2 << endl << endl;
	cout << "\t" << "Talla" << "\t\t" << "Tiempo" << "\t\t" << "Tiempo" << endl << endl;

	std::cout.precision(4);
	string tipoFinal1, tipoFinal2;
	tipoFinal1 = nombreMetodo1 + ".dat";
	tipoFinal2 = nombreMetodo2 + ".dat";
	ofstream fout1(tipoFinal1.c_str());
	ofstream fout2(tipoFinal2.c_str());

	for (int i = tallaIni; i< tallaFin; i = i + incTalla)
	{
		double acumulador_tiempo1 = 0; //Acumulador para el 1º metodo
		double acumulador_tiempo2 = 0; // Acumulador para el 2º metodo

		for (int j = 0; j < NUMREPETICIONES; j++) //For para repeticion metodo1
		{
			ConjuntoInt *v = new ConjuntoInt(i);
			v->GeneraVector(i); //Creo el vector segun la talla que sea.
			AlOrdena.ordenaSeleccion(v->getDatos(), tallaIni);//Ordeno el array
			key = v->getMitad(); //Obtengo el elemento mitad.
			QueryPerformanceCounter(&t_inicial); //Inicio el tiempo
			switch (metodo1)
			{
			case SECUENCIALIt:
				strategia.busquedaSecuencialIt(v->getDatos(), tallaIni, key);
				break;
			case BINARIARc:
				strategia.busquedaBinariaRc(v->getDatos(), tallaIni, key);
				break;
			case TERNARIARc:
				strategia.busquedaTernariaRc(v->getDatos(), tallaIni, key);
				break;
			}
			QueryPerformanceCounter(&t_final);// Para tiempo	
			acumulador_tiempo1 = acumulador_tiempo1 + (t.performancecounter_diff(&t_final, &t_inicial) * 10000);
		}

		acumulador_tiempo1 = acumulador_tiempo1 / NUMREPETICIONES;//Para hacer la media 
		tiemposM1[posArray] = acumulador_tiempo1; //Meto el tiempo del 1º metodo en el vector

		for (int j = 0; j < NUMREPETICIONES; j++) //For para repeticiones metodo2
		{
			ConjuntoInt *v = new ConjuntoInt(i);
			v->GeneraVector(i); //Creo el vector segun la talla que sea.
			AlOrdena.ordenaSeleccion(v->getDatos(), tallaIni);//Ordeno el array
			key = v->getMitad(); //Obtengo el elemento mitad.
			QueryPerformanceCounter(&t_inicial); //Inicio el tiempo
			switch (metodo2)
			{
			case SECUENCIALIt:
				strategia.busquedaSecuencialIt(v->getDatos(), tallaIni, key);
				break;
			case BINARIARc:
				strategia.busquedaBinariaRc(v->getDatos(), tallaIni, key);
				break;
			case TERNARIARc:
				strategia.busquedaTernariaRc(v->getDatos(), tallaIni, key);
				break;
			}
			QueryPerformanceCounter(&t_final);// Para tiempo	
			acumulador_tiempo2 = acumulador_tiempo2 + (t.performancecounter_diff(&t_final, &t_inicial) * 10000);
		}
		acumulador_tiempo2 = acumulador_tiempo2 / NUMREPETICIONES;//Para hacer la media 
		tiemposM2[posArray] = acumulador_tiempo2;

		fout1 << "\t" << i << "\t\t" << acumulador_tiempo1 << "\n";
		fout2 << "\t" << i << "\t\t" << acumulador_tiempo2 << "\n";

		talla[posArray] = i;
		posArray++;
		cout << "\t" << i << "\t\t" << setprecision(3) << acumulador_tiempo1 << "\t\t" << acumulador_tiempo2 << "\n";
	}
	cout << "\n";
	fout1.close();
	fout2.close();
	cout << "Datos guardados en los ficheros " << tipoFinal1<<" y "<<tipoFinal2 << endl;

	//Pregunta para generar la gráfica
	char op;
	cout << "\n\nQuiere crear la grafica(s/n): ";
	cin >> op;
	if ((op == 's'))
	{
		g.generarGraficaCMP(nombreMetodo1, nombreMetodo2);
		cout << "\nGrafica guardada en el fichero " << nombreMetodo1 + nombreMetodo2 + ".plt" << endl;
	}
	system("pause");
}
/*
* Calcula el caso medio de un metodo de búsqueda,
* Permite las opciones de crear el fichero de datos y la gráfica correspondiente.
* param metodo: metodo de búsqueda a estudiar.
*/
void TestBusqueda::casoMedio(int metodo)
{
	system("cls");
	LARGE_INTEGER t_inicial, t_final;
	AlgoritmosOrdenacion AlOrdena;
	AlgoritmosBusqueda strategia;
	Graficas g;
	Mtime t;

	// 0 = Busqueda Secuencial .... 1 = Busqueda Binaria
	system("cls");
	double tiempo[10]; //Array que almacena los tiempos
	int talla[10]; //Array que alamacena la talla
	int contadorDos = 0, key;

	cout << "\n\n\t*** Ordenacion por " << nombreAlgoritmo[metodo] << " ***" << endl << endl;
	cout << "\tTiempos de ejecucion promedio" << endl << endl << endl;
	cout << "\t\t" << "Talla" << "\t" << "Tiempo" << endl << endl;
	//double acumulador_tiempo = 0;

	string tipoFinal;
	tipoFinal = nombreAlgoritmo[metodo] + ".dat";
	ofstream fout(tipoFinal.c_str());

	for (int i = tallaIni; i < tallaFin; i = i + incTalla)
	{
		double tiempo_medio = 0;
		for (int j = 0; j < NUMREPETICIONES; j++)
		{
			ConjuntoInt *v = new ConjuntoInt(i);
			v->GeneraVector(i);
			AlOrdena.ordenaSeleccion(v->getDatos(), i); //Ordeno el array
			key = v->getMitad(); //Obtengo el elemento mitad.
			QueryPerformanceCounter(&t_inicial); //Inicio el tiempo
			switch (metodo)
			{
			case SECUENCIALIt:
				strategia.busquedaSecuencialIt(v->getDatos(), tallaIni, key);
				break;
			case BINARIARc:
				strategia.busquedaBinariaRc(v->getDatos(), tallaIni, key);
				break;
			case TERNARIARc:
				strategia.busquedaTernariaRc(v->getDatos(), tallaIni, key);
				break;

			}
			QueryPerformanceCounter(&t_final);// Para tiempo			
			tiempo_medio = tiempo_medio + (t.performancecounter_diff(&t_final, &t_inicial) * 100000);
			v->vaciar();
			v->~ConjuntoInt();
		}
		tiempo_medio = tiempo_medio / NUMREPETICIONES; //Hacemos la media.
		cout << "\t\t" << i << "\t" << tiempo_medio << " \n";
		talla[contadorDos] = i;
		tiempo[contadorDos] = tiempo_medio;
		contadorDos++;

		fout << "\t\t" << i << "\t" << tiempo_medio << "\n";

	}
	fout.close();
	cout << "\n";
	cout << "\nDatos guardados en el fichero " << tipoFinal << endl;

	//Pregunta para generar la gráfica
	char op;
	cout << "\n\nQuiere crear la grafica(s/n): ";
	cin >> op;

	if (op == 's')
	{
		int num_orden;
		if (metodo == SECUENCIALIt)
			num_orden = 1;
		else num_orden = 2;

		g.generarGraficaMEDIO(nombreAlgoritmo[metodo], num_orden);
		cout << "\nGrafica guardada en el fichero " << nombreAlgoritmo[metodo] + ".plt" << endl;
	}

	system("pause");
}

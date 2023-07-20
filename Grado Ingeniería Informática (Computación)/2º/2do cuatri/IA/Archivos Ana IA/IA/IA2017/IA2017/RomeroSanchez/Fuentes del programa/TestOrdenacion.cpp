/* 
 * La clase TestOrdenacion contiene los metodos para:
 * 1. Comprobar que los métodos de ordenacion de la clase Ordenacion funcionan adecuadamente.
 * 2. Calcular la eficiencia para el caso medio de un método de ordenación,
 *    guardando los datos y permitiendo imprimir la gráfica correspondiente 
 * 3. Comparar el coste temporal de dos de los métodos de ordenación 
 *    burbuja, inserción, y selección, guardando los datos y permitiendo imprimir la gráfica correspondiente.
 */
#include "AlgoritmosOrdenacion.h"
#include "TestOrdenacion.h"
#include "ConjuntoInt.h"
#include "Graficas.h"
#include "Mtime.h"
#include <string>
#include <fstream>
#include <iomanip>
#include <iostream>
#include <math.h>
using namespace std;

/* Constantes simbólicas para indicar el metodo de ordenacion*/
enum algoritmos { BURBUJA, INSERCION, SELECCION};
/* Constantes para indicar el Orden del metodo de ordenacion*/
enum ordenes { CUADRADO, NlogN};
/* Constantes para indicar el Numero de repeticiones para el caso medio de cada método de búsqueda */
static const int NUMREPETICIONES=10;
/* Constantes para indicar la variacion de las tallas del vector */
enum valoresTallas { tallaIni = 100,tallaFin = 1000,incTalla = 100}; 
TestOrdenacion::TestOrdenacion()
{
	nombreAlgoritmo.push_back("Burbuja");
	nombreAlgoritmo.push_back("Insercion");
	nombreAlgoritmo.push_back("Seleccion");
} 
TestOrdenacion::~TestOrdenacion(){}

/*
 * Ordena un array de enteros según el método indicado
 * param v: el array de enteros a ordenar
 * param size: tamaño del array de enteros a ordenar
 * param metodo: Metodo de ordenacion a utilizar
 * return Tiempo empleado en la ordenación (en milisegundos)
 */
double TestOrdenacion::ordenarArrayDeInt(int v[],int size,int metodo) 
{
	AlgoritmosOrdenacion estrategia;
	Mtime t;
	LARGE_INTEGER t_inicial, t_final;
	QueryPerformanceCounter(&t_inicial);
	// Invoca al método de ordenación elegido
	switch (metodo){
		case BURBUJA: estrategia.ordenaBurbuja(v, size);
			break;
		case INSERCION: estrategia.ordenaInsercion(v, size);
			break;
		case SELECCION: estrategia.ordenaSeleccion(v, size);
			break;
	}
	QueryPerformanceCounter(&t_final);
	return t.performancecounter_diff(&t_final, &t_inicial) * 1000;   
}

/*
 * Comprueba los metodos de ordenacion
 */
void TestOrdenacion::comprobarMetodosOrdenacion()
{
	int talla;
	cout<<endl<<endl<<"Introduce la talla: ";
	cin>>talla; 
	system("cls"); 
	for (int metodo = 0; metodo < nombreAlgoritmo.size();metodo++){
		ConjuntoInt *v= new ConjuntoInt(talla); 
		v->GeneraVector(talla);
		cout <<endl<<endl<< "vector inicial para el metodo "<<nombreAlgoritmo[metodo]<< ":"<<endl<<endl;
		v->escribe();
		double secs=ordenarArrayDeInt(v->getDatos(),talla,metodo);
    		cout<<endl<<endl<<"Array ordenado con metodo "<<nombreAlgoritmo[metodo]<< ":"<<endl<<endl;
        	v->escribe();
		cout<<endl;
		v->vaciar(); 
		system("pause");
		system("cls");
    	} /* fin del for */
	system("cls");
}
    
/*
 * Calcula el caso medio de un metodo de ordenacion,
 * Permite las opciones de crear el fichero de datos y la gráfica correspondiente.
 * param metodo: metodo de ordenacion a estudiar.
 */
void TestOrdenacion::casoMedio(int metodo)
{
	double media;
	system("cls");
	string dat= ".dat";
	string nombre= nombreAlgoritmo[metodo] + dat;
	ofstream fichero(nombre.c_str());
	
	
	
	if(metodo==0){
		cout<<"\t\t***Ordenacion por Burbuja***\n\n";
	}
	if(metodo==1){
		cout<<"\t\t***Ordenacion por Insercion***\n\n";
	}
	if(metodo==2){
		cout<<"\t\t***Ordenacion por Seleccion***\n\n";
	}
	cout<<"\t\tTiempos de ejecucion promedio\n\n\n";
	cout<<"\tTalla\t\tTiempo <mseg>\n";
	for(int i=100; i<=1000; i=i+100){
	    media=0;
	    ConjuntoInt v(i); 
     	v.GeneraVector (i);	
	    for(int j=0; j<10; j++){
	      	media=media + ordenarArrayDeInt(v.getDatos(),i,metodo);
	    }
     	//media= (media/10)*100000;//Se multiplica por 100000 para redondear
     	//media=round(media)/100000;
        cout<<"\t"<<i<<"\t\t"<<media<<"\n";
        fichero<<i<<"\t"<<media<<"\n";
    }
	fichero.close();
	cout<<"Datos guardados en el fichero "<<nombre<<endl;
	char a;
	cout<<"Quiere crear la grafica(s/n): ";
	cin>>a;
	if(a=='s'){
		Graficas g;
		g.generarGraficaMEDIO(nombreAlgoritmo[metodo],0);
		system("cls");
		cout<<"Grafica guardada en el fichero "<<nombreAlgoritmo[metodo]<<".pdf\n";
		cout<<"Ficheros de comandos guardado en" <<nombreAlgoritmo[metodo]<<".plt\n";
	}	
	system("pause");	
}
/*
 * Compara dos metodos de ordenacion. 
 * Genera el fichero de datos y permite las opcion de crear la gráfica correspondiente.
 * param metodo1: Primer metodo de ordenacion a comparar
 * param metodo2: Segundo metodo de ordenacion a comparar
 */
void TestOrdenacion::comparar(int metodo1, int metodo2) {
	double media1,media2;
	system("cls");
	string dat= ".dat";
	string nombre1= nombreAlgoritmo[metodo1] + dat;
	string nombre2= nombreAlgoritmo[metodo2] + dat;
	ofstream fichero1(nombre1.c_str());
	ofstream fichero2(nombre2.c_str());

	cout<<"\t\t***Comparacion entre "<< nombreAlgoritmo[metodo1]<<" y "<< nombreAlgoritmo[metodo2]<<"***\n\n";
		
	cout<<"\t\tTiempos de ejecucion promedio\n\n\n";
	cout<<"\tTalla\t\t"<<nombreAlgoritmo[metodo1]<<"<mseg>\t\t"<<nombreAlgoritmo[metodo2]<<"<mseg>\n";
	for(int i=100; i<=1000; i=i+100){
	    media1=0;
	    media2=0;
	    ConjuntoInt v(i); 
     	v.GeneraVector (i);	
	    for(int j=0; j<10; j++){
	      	media1=media1 + ordenarArrayDeInt(v.getDatos(),i,metodo1);
	      	media2=media2 + ordenarArrayDeInt(v.getDatos(),i,metodo2);
	    }
     	//media1= (media1/10)*100000;//Se multiplica por 100000 para redondear
     	//media2= (media2/10)*100000;//Se multiplica por 100000 para redondear
     	//media1=round(media1)/100000;
     	//media2=round(media2)/100000;
        cout<<"\t"<<i<<"\t\t"<<media1<<"\t\t\t"<<media2<<"\n";
        fichero1<<i<<"\t"<<media1<<"\n";
        fichero2<<i<<"\t"<<media2<<"\n";
    }
	fichero1.close();
	fichero2.close();
	cout<<"Datos guardados en el fichero "<<nombre1<<" y "<<nombre2<<endl;
	char a;
	cout<<"Quiere crear la grafica(s/n): ";
	cin>>a;
	if(a=='s'){
		Graficas g;
        g.generarGraficaCMP(nombreAlgoritmo[metodo1],nombreAlgoritmo[metodo2]);
		system("cls");
		cout<<"Grafica guardada en el fichero "<<nombreAlgoritmo[metodo1]<<nombreAlgoritmo[metodo2]<<".pdf\n";
		cout<<"Ficheros de comandos guardado en CmdComparar.plt\n";
	}	
	system("pause");	
		
}	

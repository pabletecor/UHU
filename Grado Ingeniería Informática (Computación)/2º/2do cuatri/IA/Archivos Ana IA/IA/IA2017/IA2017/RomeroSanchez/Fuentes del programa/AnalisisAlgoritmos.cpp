
#include "TestBusqueda.h"
#include "Constantes.h"
#include "AlgoritmosBusqueda.h"
#include "AlgoritmosOrdenacion.h"
#include "ConjuntoInt.h"
#include "Graficas.h"
#include "Mtime.h"
#include "TestOrdenacion.h"
#include <iostream>


using namespace std;

#define pause system("pause");
#define cls system("cls");


		/** Programa principal **/
int main()
{
	int opciones;	
	do{system("cls");
	cout<<"\t\t\t\t\t*** FAA. Practica 3. Curso 2016/2017 ***\n";
	cout<<"\t\t\t\t\t\t\tMiguel Romero Sanchez\n\n";
	cout<<"\t\t\t*** MENU PRINCIPAL ***\n\n";
	cout<<"\t\t1.- Menu de ordenacion\n\n";
	cout<<"\t\t2.- Menu de busqueda\n\n";
	cout<<"\t\t0.- Salir\n\n";
	cout<<"\t\t---------\n\n";
	cout<<"\t\tElige una opcion: ";
	cin>>opciones;
	switch (opciones){
		
	case 1: {system("cls");
	
		int opciones1;	
		do{system("cls");
		TestOrdenacion objeto;
		cout<<"\t\t\t*** MENU DE ORDENACION ***\n\n";
		cout<<"\t*** ANALISIS EXPERIMENTAL DE ALGORITMOS DE ORDENACION ***\n\n";
		cout<<"\t\t1.- Probar los metodos de ordenacion\n\n";
		cout<<"\t\t2.- Obtener el caso medio de un metodo de ordenacion\n\n";
		cout<<"\t\t3.- Comparar dos metodos\n\n";
		cout<<"\t\t0.- Volver al menu principal\n\n";
		cout<<"\t\t---------\n\n";
		cout<<"\t\tElige una opcion: ";
		cin>>opciones1;
		switch (opciones1){
		case 1: {system("cls");
			objeto.comprobarMetodosOrdenacion();
			system("pause");
			break;
				}

		case 2: {system("cls");
			int opcion1;
			cout<<"\n\n\t\t*** Metodo a estudiar para el caso medio***\n\n";
			cout<<"\t\t0.- Burbuja\n\n";
			cout<<"\t\t1.- Insercion\n\n";
			cout<<"\t\t2.- Seleccion\n\n";
			cout<<"\t\t---------\n\n";
		    cout<<"\t\tElige una opcion: ";
		    cin>>opcion1;
    	    objeto.casoMedio(opcion1);
    	    break;
    	}
	
		case 3: {system("cls");
		int opcion2,opcion3;
			cout<<"\n\n\t\t*** Metodos a comparar***\n\n";
			cout<<"\t\t0.- Burbuja\n\n";
			cout<<"\t\t1.- Insercion\n\n";
			cout<<"\t\t2.- Seleccion\n\n";
			cout<<"\t\t---------\n\n";
		    cout<<"\t\tElige el primer metodo: ";
		    cin>>opcion2;
		    cout<<"\n\n\t\tElige el segundo metodo: ";
		    cin>>opcion3;
	        objeto.comparar(opcion2,opcion3);
			system("pause");
			break;
				}
		case 0:{system("pause");
		cout<<"\n\n\n";
			break;
			   }
	
		default: {system("cls");
			cout<<"Opcion invalida";
			system("pause");
			break;
				 }
		}
	}while(opciones1!=0);
		break;
	}
	
	
	case 2: {system("cls");
		int opciones2;	
		do{system("cls");
		TestBusqueda objeto;
		cout<<"\t\t\t*** MENU DE BUSQUEDA ***\n\n";
		cout<<"\t*** ANALISIS EXPERIMENTAL DE ALGORITMOS DE ORDENACION ***\n\n";
		cout<<"\t\t1.- Probar los metodos de busqueda\n\n";
		cout<<"\t\t2.- Obtener el caso medio de un metodo de busqueda\n\n";
		cout<<"\t\t3.- Comparar dos metodos\n\n";
		cout<<"\t\t0.- Volver al menu principal\n\n";
		cout<<"\t\t---------\n\n";
		cout<<"\t\tElige una opcion: ";
		cin>>opciones2;
		switch (opciones2){
		case 1: {system("cls");
			objeto.comprobarMetodosBusqueda();
			system("pause");
			break;
				}

		case 2: {system("cls");
			int opcion1;
			cout<<"\n\n\t\t*** Metodo a estudiar para el caso medio***\n\n";
			cout<<"\t\t0.- Secuencial Iterativa\n\n";
			cout<<"\t\t1.- Binaria Recursiva\n\n";
			cout<<"\t\t2.- Ternaria Recursiva\n\n";
			cout<<"\t\t---------\n\n";
		    cout<<"\t\tElige una opcion: ";
		    cin>>opcion1;
    	    objeto.casoMedio(opcion1);
    	    break;
    	}
	
		case 3: {system("cls");
		int opcion2,opcion3;
			cout<<"\n\n\t\t*** Metodos a comparar***\n\n";
			cout<<"\t\t0.- Secuencial Iterativa\n\n";
			cout<<"\t\t1.- Binaria Recursiva\n\n";
			cout<<"\t\t2.- Ternaria Recursiva\n\n";
			cout<<"\t\t---------\n\n";
		    cout<<"\t\tElige el primer metodo: ";
		    cin>>opcion2;
		    cout<<"\n\n\t\tElige el segundo metodo: ";
		    cin>>opcion3;
	        objeto.comparar(opcion2,opcion3);
			system("pause");
			break;
				}
		case 0:{system("pause");
		cout<<"\n\n\n";
			break;
			   }
	
		default: {system("cls");
			cout<<"Opcion invalida";
			system("pause");
			break;
				 }
		}
	}while(opciones2!=0);
		break;
	}
	
	
	case 0:{system("pause");
		cout<<"\n\n\n";
			break;
			   }
	
		default: {system("cls");
			cout<<"Opcion invalida";
			system("pause");
			break;
				 }
	}
	
	
	
	}while(opciones!=0);
	
	
	return 0;
}

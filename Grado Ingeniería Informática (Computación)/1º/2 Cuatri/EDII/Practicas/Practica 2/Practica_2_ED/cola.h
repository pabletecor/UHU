#include <iostream>
#include <fstream>
#include <windows.h>
#include <cstring>

#define INCREMENTO 4
using namespace std;

typedef char cadena[50];

struct declarante{
    char Dni[20];
    cadena Apellidos;
    cadena Nombre;
    int Edad;
    int Especialidad;
    int HoraLlegada; /*almacenamos los minutos que representa la hora en cuestión, a modo de ejemplo las 11:30 serían 11*60 + 30 = 690 */
};

class cola{
        declarante *elementos; //elementos de la cola
        int inicio, fin; //principio y fin de la cola
        int Tama; //Capacidad de la tabla
        int ne; //Nº de elementos

    public:
        cola(); // constructor de la clase
        ~cola();
        void encolar(declarante e);
        void desencolar();
        bool esvacia();
        declarante primero() ;
        int longitud();
};

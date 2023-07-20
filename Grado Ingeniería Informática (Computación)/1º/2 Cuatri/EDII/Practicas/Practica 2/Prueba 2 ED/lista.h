#include "cola.h"
using namespace std;

struct tecnico{
    int Codigo;
    cadena Apellidos;
    cadena Nombre;
    int Especialidad;
    cola Col;
};

class lista {
        tecnico *elementos; // elementos de la lista
        int n; // nº de elementos que tiene la lista
        int Tama; // tamaño de la tabla en cada momento
    public:
        lista(); // constructor de la clase
        ~lista(); // destructor de la clase
        lista(tecnico &e);
        bool esvacia();
        int longitud();
        //void anadirIzq(tecnico e); No necesario implementar
        //void anadirDch(tecnico e); No necesario implementar
        //void eliminarIzq();No necesario implementar
        //void eliminarDch();No necesario implementar
        //tecnico observarIzq();No necesario implementar
        //tecnico observarDch();No necesario implementar
        //void concatenar(lista l); No necesario implementar
        bool pertenece(tecnico &e);
        void insertar(int i, tecnico &e);
        void eliminar(int i);
        void modificar(int i, tecnico &e);
        tecnico &observar(int i);
        int posicion(tecnico &e);
};

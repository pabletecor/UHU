#include "lista.h"
using namespace std;

class oficina{
        lista L; //lista de los técnicos que están atendiendo
    public:
        void AbrirOficina(char *nombrefichero);
        void IncorporarTecnico(tecnico &t);
        bool RetirarTecnico(int codigo);
        int TiempoEspera(int especialidad,int horaactual);
        bool IncorporarDeclarante(declarante dec);
        void Mostrar();
        bool AtenderDeclarante(int CodigoTecnico);
        void VolcarOficina(char *nombrefichero);
        int masdecs(int esp);
        int menosdecs(int esp);
        int numdecs(int esp);
        int numesp(int esp);
        void eliminardec(char *Dni);
        void primerocola();

};

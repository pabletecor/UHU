#include <iostream>
#include <cstdlib>
#include "grafo.h"
#include "conjunto.h"
#include <queue>
#include <sstream>
#include <map>


using namespace std;

//Ejercicio 1
template <typename T>
T verticeMaxCoste(const Grafo<T, float>& G)
{

    map<T,float> coste;
    T v;

    //Inicializa el conjunto de vertices a 0
    Conjunto<Vertice<T> > cv;
    cv = G.vertices();

    while(!cv.esVacio())
    {

        v = cv.quitar().getObj();
        coste[v] = 0;

    }
    //Actualiza el coste de cada vértice, sumando el coste de las aristas que salen
    Conjunto<Arista<T, float> > ca;
    ca = G.aristas();

    while (!ca.esVacio()){
        Arista<T,float> a = ca.quitar();
        coste[a.getOrigen()] += a.getEtiqueta();

    }

    //Recorre todas las aristas
    float devolver=0;
    T vmax;
    for(typename map <T,float>::iterator it = coste.begin(); it != coste.end();it++){

        if(it->second >= devolver){

            devolver = it->second;
            vmax = it->first;

        }



    }

    return vmax;
}


//Ejercicio 2

//PODRIAMOS HABER USADO ANTECESORES()

template <typename T, typename U>
void inaccesibles(const Grafo<T, U>& G)
{
    map<T,float> coste; //El primer campo es el vertice, el segundo el coste
    T v;

    //Inicializa el conjunto de vertices a 0
    Conjunto<Vertice<T> > cv;
    cv = G.vertices();

    while(!cv.esVacio())
    {

        v = cv.quitar().getObj();
        coste[v] = 0;

    }

    //Actualiza el coste de cada vértice, sumando el coste de las aristas que salen
    Conjunto<Arista<T, float> > ca;
    ca = G.aristas();

    while (!ca.esVacio()){
        Arista<T,float> a = ca.quitar();
        coste[a.getDestino()]++;

    }

    //Recorre todas las aristas
    for(typename map <T,float>::iterator it = coste.begin(); it != coste.end();it++){
        if(it->second==0){

            cout<< " " << it->first << " ";

        }



    }


}


// Ejercicio 3
//Recorrido en anchura
template <typename T, typename U>
bool caminoEntreDos(const Grafo<T, U>& G, const T& vo, const T& vd)
{
    //VARIABLES

    bool encontrado= false;
    queue <T> q;
    T v,w;
    map<T,bool> procesados;

    //ES MUY PARECIDO AL RECORRIDO EN ANCHURA
    //Guardamos en un map los vertices y false para indicar que

    Conjunto<Vertice<T> > cv = G.vertices();

    while(!cv.esVacio())
    {
        procesados[cv.quitar().getObj()]=false;
    }

    Conjunto<Vertice<T> > cady = G.adyacentes(vo);

    while (!cady.esVacio()){
        v=cady.quitar().getObj();
        if(procesados[v]==false)
        {
            procesados[v]=true;
            q.push(v);
        }

        }

        while(!q.empty() && !encontrado)
        {
            if(q.front()==vd)
                encontrado =true;
            else
            {
                Conjunto <Vertice<T> > cu;
                cu=G.adyacentes(q.front());

                while(!cu.esVacio())
                {
                    w = cu.quitar().getObj();
                    if(procesados[w]==false)
                    {
                        procesados[w]=true;
                        q.push(w);
                    }
                    }
                    q.pop();
                }


            }


        return encontrado;

        }







//Ejercicio 4
template <typename T>
void caminosAcotados(const Grafo<T, float>& G, const T& u, float maxCoste)
{

   cout<< "\n";
   caminosAcotados(G,u,0,maxCoste,0);

    //queue <T> cola;
    //caminosAcotados(G,u,0,maxCoste,cola);


}

//Otra forma de hacerlo, sin necesidad de colas


template <typename T>
void caminosAcotados(const Grafo<T, float>& G, const T& u,  float coste ,float maxCoste, int espacio){

    for(int i=0;i<espacio;i++)
        cout<< " ";
    cout<< u << endl;
    Conjunto <Arista<T,float> > aristas = G.aristas();

    while(!aristas.esVacio())
    {
        Arista <T,float> a = aristas.quitar();
        //La arista parte de u ( vertice pasado por parametro)
        if(a.getOrigen()== u && coste+a.getEtiqueta() <= maxCoste)
            caminosAcotados(G,a.getDestino(),coste + a.getEtiqueta(),maxCoste,espacio +1);
    }




}

/*
template <typename T>
void caminosAcotados(const Grafo<T, float>& G, const T& u, float costeacumulado, float maxCoste, queue<T> cola){

    cola.push(u);
    int tam= cola.size();

    while (tam > 0){
        cout << cola.front() << " ";
        cola.push(cola.front());
        cola.pop();
        tam--;

    }

    cout<< "(" << costeacumulado << ")\n";

    Conjunto <Arista <T,float> > ca = G.aristas();
    while(!ca.esVacio()) {
        Arista <T,float> a = ca.quitar();
        if(a.getOrigen()==u && costeacumulado+a.getEtiqueta() <=maxCoste)
                    caminosAcotados(G,a.getDestino(),costeacumulado + a.getEtiqueta(),maxCoste,cola);


    }


}
*/

//Ejercicio 5
template <typename T, typename U>
T outConectado(const Grafo<T, U>& G)
{
    T w;
    Conjunto <Vertice <T > > cv;

    cv = G.vertices();


    while(!cv.esVacio() ) {
        w = cv.quitar().getObj();
        if(G.adyacentes(w).cardinalidad()> G.antecesores(w).cardinalidad())
           //Como suponemos que hay un vertice outconectado podemos poner aqui el return
            return w;


    }



}


//Ejercicio 6
template <typename T, typename U>
void recorrido_profundidad(const Grafo<T, U>& G, const T& v)
{
    T w;
    Conjunto<Vertice<T > > cv;
    map <T,bool> visitados;

    cv = G.vertices();

    while(!cv.esVacio() ) {
        w = cv.quitar().getObj();
        visitados[w]=false;


    }

    recorrido_profundidad(G,v, visitados);


}

template <typename T, typename U>
void recorrido_profundidad(const Grafo<T, U>& G, const T& v, map <T,bool> &visitados){

    cout<< "\n" << v ;
    Conjunto < Vertice <T> > ady;
    T w;

    visitados[v]=true;

    ady= G.adyacentes(v);

    while(!ady.esVacio()){
        w= ady.quitar().getObj();
        if(!visitados[w])
            recorrido_profundidad(G,w,visitados);


    }



}


//EXAMEN JUEVES

//caminoLongitud() devuelve si existe un camino desde un nodo dado de longintud x,
//el camino de longitud 0 es siempre verdadero

template <typename T>
bool caminoLongitud(const Grafo<T, float>& G, const T& u,int longitud){

//Conjunto <Vertice <T> > cv;
bool encontrado=false;

encontrado=caminoLongitud(G,u,longitud,encontrado);

return encontrado;
}


template <typename T>
bool caminoLongitud(const Grafo<T, float>& G, const T& u,int longitud,bool encontrado){

T w;
Conjunto <Arista <T,float > > ca = G.aristas();
Arista <T,float > a;

if(longitud <= 0)
{
    return true;
}



while(!ca.esVacio())
{
    a=ca.quitar();
    if(a.getOrigen()==u)
       return caminoLongitud(G,a.getDestino(),longitud-1,encontrado);

}

//Hemos recorrido todos los vertices y ninguno tiene un camino de ese coste
if(ca.esVacio())
    return false;

}

//el otro es comprobar si gE==gS y devolver el nodo que lo valide, puede haber varios pero solo devuelve 1


//1. Escribe una función que devuelva cierto si un grafo es regular. Un grafo es regular si
//todos sus vértices tienen el mismo número de vértices adyacentes.


template <typename T>
bool regular(const Grafo <T,float> &G){

bool regular= true;
T w,v;
Conjunto <Vertice < T > > cv = G.vertices();

//Cogemos un vertice cualquiera del grafo, y vemos su numero vertices adyacentes como ejemplo.
v= cv.quitar().getObj();


//Recorremos todos los vertices del grafo

while(!cv.esVacio())
{
    w = cv.quitar().getObj();

    if(G.adyacentes(v).cardinalidad() != G.adyacentes(w).cardinalidad() )
        regular=false;

}

return regular;

}


template <typename T, typename U>

//Camino entre dos pero con recursividad
bool accesible(const Grafo<T, U>& G, const T& vo, const T& vd)
{
 map <T,bool> visitados;
 T v;
 Conjunto < Vertice < T > > cv;
 cv= G.vertices();

 //Ponemos todos los visitados a false
 while(!cv.esVacio())
    {
     v= cv.quitar().getObj();
     visitados[v]=false;

    }

 return accesible(G,vo,vd,visitados);

}

template <typename T,typename U>
bool accesible(const Grafo<T, U>& G, const T& vo, const T& vd, map <T,bool> visitados)
{
 T w;
 Conjunto <Vertice <T> > ady;
 ady = G.adyacentes(vo);
 bool encontrado=false;

 while(!ady.esVacio() && !encontrado)
 {
    w=ady.quitar().getObj();
    if(w==vd)
        encontrado=true;
    else if(visitados[w]!=true){
        visitados[w]=true;
        encontrado=accesible(G,w,vd,visitados);


    }
 }

 return encontrado;

 }


//********************************************************************//
int main()
{

    Grafo<int, float> G(7);
    for (int i = 1; i <= 7; i++) G.insertarVertice(i);
    G.insertarArista(2, 1, 4);
    G.insertarArista(1, 3, 3);
    G.insertarArista(1, 4, 2);
    G.insertarArista(1, 6, 1);
    G.insertarArista(6, 4, 5);
    G.insertarArista(4, 7, 3);
    G.insertarArista(5, 1, 6);


    Grafo<string, float> H(7);
    H.insertarVertice("Huelva"); H.insertarVertice("Lepe"); H.insertarVertice("Niebla");
    H.insertarVertice("Mazagon"); H.insertarVertice("Almonte"); H.insertarVertice("Aljaraque");
    H.insertarVertice("Matalascañas");
    H.insertarArista("Lepe", "Huelva", 4);
    H.insertarArista("Huelva", "Niebla", 3);
    H.insertarArista("Huelva", "Mazagon", 2);
    H.insertarArista("Huelva", "Aljaraque", 1);
    H.insertarArista("Mazagon", "Almonte", 3);
    H.insertarArista("Mazagon", "Matalascañas", 4);
    H.insertarArista("Aljaraque", "Mazagon", 5);
    H.insertarArista("Almonte", "Huelva", 6);

    cout << " Vertice de maximo coste en G: " << verticeMaxCoste(G) << endl;
    cout << " Vertice de maximo coste en H: " << verticeMaxCoste(H) << endl;

    cout << endl << " Vertices inaccesibles en G: ";
    inaccesibles(G);

    cout << endl << "\n Camino entre Dos en H de Lepe a Almonte: ";
    cout << (caminoEntreDos(H, string("Lepe"), string("Almonte")) ? " SI " : " NO ") << endl;
    cout << endl << " Camino entre Dos en H de Aljaraque a Lepe: ";
    cout << (caminoEntreDos(H, string("Aljaraque"), string("Lepe")) ? " SI " : " NO ") << endl;

    cout << endl << " Caminos acotados en G a coste 9 desde el vertice 2:" << endl;
    caminosAcotados(G, 2, 9);

    cout << endl << endl << " Vertice outConectado en G: " << outConectado(G);
    cout << endl << " Vertice outConectado en H: " << outConectado(H);

    cout << endl << endl << " Recorrido en profundidad de H desde el vertice Huelva:  ";
    recorrido_profundidad(H, string("Huelva"));
    cout << endl << endl;

    cout << endl << "Es regular el grafo G?:";
    cout << (  regular (G)  ? " SI " : " NO ") << endl;
    cout << endl << endl;

    cout << endl << "\n Accesibles en H de Lepe a Almonte: ";
    cout << (accesible(H, string("Lepe"), string("Almonte")) ? " SI " : " NO ") << endl;
    cout << endl << " Accesibles en H de Aljaraque a Lepe: ";
    cout << (accesible(H, string("Aljaraque"), string("Lepe")) ? " SI " : " NO \n") << endl;

    system("PAUSE");
    return EXIT_SUCCESS;
}

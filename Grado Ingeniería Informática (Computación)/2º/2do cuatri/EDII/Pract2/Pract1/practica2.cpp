#include <iostream>
#include <cstdlib>
#include <queue>
#include <string>
#include "arbin.h"
#include "abb.h"

// Recorridos

template <typename T>
void inorden(const Arbin<T>& a, const typename Arbin<T>::Iterador& r) {
    if (!r.arbolVacio()) {
        inorden(a, a.subIzq(r));
        cout << r.observar() << " ";
        inorden(a, a.subDer(r));
    }
}

template <typename T>
void preorden(const Arbin<T>& a, const typename Arbin<T>::Iterador& r) {
    if (!r.arbolVacio()) {
        cout << r.observar() << " ";
        preorden(a, a.subIzq(r));
        preorden(a, a.subDer(r));
    }
}

template <typename T>
void postorden(const Arbin<T>& a, const typename Arbin<T>::Iterador& r) {
    if (!r.arbolVacio()) {
        postorden(a, a.subIzq(r));
        postorden(a, a.subDer(r));
        cout << r.observar() << " ";
    }
}

template <typename T>
void anchura(const Arbin<T>& a) {
    if (!a.esVacio()) {
        queue<typename Arbin<T>::Iterador> c;
        typename Arbin<T>::Iterador ic = a.getRaiz();
        c.push(ic);
        while (!c.empty()) {
             ic = c.front();
             c.pop();
             cout << ic.observar() << " ";
             if (!a.subIzq(ic).arbolVacio())
                c.push(a.subIzq(ic));
             if (!a.subDer(ic).arbolVacio())
                c.push(a.subDer(ic));
        }
    }
}


/***************************************************************************/
/****************************** EJERCICIOS *********************************/
/***************************************************************************/
//Ejercicio 1
template <typename T>

int numHojas(const Arbin<T>& a)
{
    int contador =0;
    return numHojas(a,a.getRaiz(),contador);

}

template <typename T>

int numHojas(const Arbin<T>& a,const typename Arbin<T>::Iterador& r, int& contador )
{
    if(!r.arbolVacio())
    {
        if(a.subIzq(r).arbolVacio() && a.subDer(r).arbolVacio())
            //return 1;
        //else return (numHojas(a,a.subIzq(r)) + numHojas(a,a.subDer(r)));
       return contador++;

        else return (numHojas(a,a.subIzq(r),contador) + numHojas(a,a.subDer(r),contador) );
    }
    else return 0;
}

/****************************************************************************/
//Ejercicio 2
template <typename T>
Arbin<T> simetrico(const Arbin<T>& a)
{
    return simetrico(a,a.getRaiz());
}
template <typename T>
Arbin<T> simetrico(const Arbin<T>& a, const typename Arbin<T>::Iterador& r)
{
    if(!r.arbolVacio())
    return Arbin<T>(r.observar(),simetrico(a,a.subDer(r)),simetrico(a,a.subIzq(r)));
    else
    return Arbin<T>();
}

/****************************************************************************/
//Ejercicio 3
template<typename T>
void recorridoZigzag(const Arbin<T>&a,char sentido)
{
        recorridoZigzag(a,sentido,a.getRaiz());
}

template<typename T>
void recorridoZigzag(const Arbin<T>&a,char sentido,const typename Arbin<T>::Iterador& r)
{
    if(!r.arbolVacio())
    {
        cout<<r.observar()<<" ";
        if(sentido=='I') recorridoZigzag(a,'D',a.subIzq(r));
        else if(sentido=='D') recorridoZigzag(a,'I',a.subDer(r));
    }

}
/******************************************************************************/
//Ejercicio 4
template<typename T>
bool compensado(const Arbin<T>&a)
{
    //int contador=0;
    //char f;
    //cout<<"\nDesea usar parametro o funcion auxiliar? (P||F)\n";
    //cin>>f;
    //if(f=='P') return compensado(a,a.getRaiz(),contador);
    //else
        return compensado(a,a.getRaiz());
}


template<typename T>
bool compensado(const Arbin<T>&a,const typename Arbin<T>::Iterador& r) //Con función auxiliar(Profesor)
{
    if(!r.arbolVacio())
    {
        if(a.subIzq(r).arbolVacio()&&a.subDer(r).arbolVacio())
            return true;
        else
        {
            if(compensado(a,a.subIzq(r))&&compensado(a,a.subDer(r)))
            {
                if((numNodos(a,a.subIzq(r))-numNodos(a,a.subDer(r))<-1)||(numNodos(a,a.subIzq(r))-numNodos(a,a.subDer(r))>1)) return false;
                else return true;
            }
            else return false;
        }
    }
    return true;
}

/*
template<typename T>
bool compensado(const Arbin<T>&a,const typename Arbin<T>::Iterador& r,int &contador) //Con parámetro por referencia(original)
{
    if(!r.arbolVacio())
    {
        if(a.subIzq(r).arbolVacio()&&a.subDer(r).arbolVacio())
        {
            contador=1;
            return true;
        }
        else
        {
            int i=0,j=0;
            if(compensado(a,a.subIzq(r),i)&&compensado(a,a.subDer(r),j))
            {
                if(i-j<-1||i-j>1) return false;
                else
                {
                    contador=i+j+1;
                    return true;
                }
            }
            else return false;
        }
    }
    else contador=0;
    return true;
}

*/


template <typename T>
int numNodos(const Arbin<T>&a,const typename Arbin<T>::Iterador& r) //Función auxiliar
{
    if(r.arbolVacio()) return 0;
    else return (numNodos(a,a.subIzq(r))+1+numNodos(a,a.subDer(r)));
}

/*****************************************************************************/
//Ejercicio 5



void palabras(const Arbin<char>& a,const Arbin<char>::Iterador& r,char tabla[],int contador)
{
    if(!r.arbolVacio())
    {
        tabla[contador]=r.observar();
        if(a.subIzq(r).arbolVacio()&&a.subDer(r).arbolVacio())
            {
                for(int i=0;i<=contador;i++)
                    cout<<tabla[i]<<" ";
                cout<<"\n";
            }
        else
        {
            palabras(a,a.subIzq(r),tabla,contador+1);
            palabras(a,a.subDer(r),tabla,contador+1);
        }
    }
}



void palabras(const Arbin<char>& a)
{
    int contador=0;
    char tabla[20];        //Ponemos T porque ya hemos puesto arriba que T es typename
    palabras(a,a.getRaiz(),tabla,contador);
}



/******************************************************************************/
//Ejercicio 6

template <typename T>
int siguienteMayor(const ABB<T>& a, int x){

return siguienteMayor(a, a.getRaiz(), x, 999 );
//if (sm==0)
//    throw(noHaySiguienteMayor())
//else
//return sm;
}

template <typename T>int siguienteMayor(const ABB<T>& a, const typename ABB<T>::Iterador& r , int x, int candidato){

if(!r.arbolVacio()){

    if(r.observar() > x) {
        if(r.observar()< candidato)
            candidato = r.observar();

        siguienteMayor(a,a.subIzq(r),x,candidato);

    }

    else {

        if (a.subDer(r).arbolVacio() && candidato == 999)
            throw(noHaySiguienteMayor());
    else return siguienteMayor(a,a.subDer(r),x, candidato);
}





}

return candidato;
}


/*

int siguienteMayor(const ABB<T>& a, const typename ABB<T>::Iterador& r , int x, int candidato){
if(!r.arbolVacio())
{
    int eraiz = r.observar();
    if(eraiz ==x)
    {
        if(!a.subDer(r).arbolVacio())
            sm=minimo(a,a.subder(r));
    }
    else if (eraiz < x)
        siguienteMayor(a,a.subDer(r),x,sm);
    }

    else{   //eraiz > x
        if(a.subIzq(r).observar < x)
            siguienteMayor(a,a.subIzq(r),x,sm);
        else
            sm ==


    }


}


*/



/******************************************************************************/
//Ejercicio 7

template <typename T>
int posicionInorden(const ABB<T>& a, const T& objeto ){

int contador = 0;
int devolver =0;
posicionInorden(a, a.getRaiz(), objeto, contador,devolver);
return devolver;


}

template <typename T>
void posicionInorden(const ABB<T>& a, const typename ABB<T>::Iterador& r ,const T& objeto, int& contador,int& devolver ){


if (!r.arbolVacio() ){
        if(!a.subIzq(r).arbolVacio())
    posicionInorden(a, a.subIzq(r), objeto,contador,devolver);

    contador++;
    if (r.observar()==objeto)
        devolver = contador;

    posicionInorden(a, a.subDer(r), objeto,contador,devolver);


}


}

//HAY OTRA SOLUCION RECURSIVA



/******************************************************************************/
//Ejercicio 8

//IMPORTANTE!!!!! SI NO TIENE TEMPLATE HAY QUE PONER PRIMERO LA FUNCION CON MAS PARAMETROS

bool haySumaCamino(const Arbin<int>& a , const typename Arbin<int>::Iterador& r  , int suma, int contador){



if(!r.arbolVacio()){

        contador+= r.observar();
     if(a.subIzq(r).arbolVacio() && a.subDer(r).arbolVacio())

            return (contador == suma);  //True si se cumple, false en caso contrario

     else
           if ( haySumaCamino(a,a.subIzq(r),suma,contador) || haySumaCamino(a,a.subDer(r),suma,contador) )
            return true;

            else
                return false;
}

else

return false;

}

bool haySumaCamino(const Arbin<int>& a, int suma){

int contador = 0;
return haySumaCamino(a,a.getRaiz(),suma,contador);


}





/****************************************************************************/
/****************************************************************************/

//PRACTICANDO
//1. Escribir una función que, dados dos árboles binarios A y B, determine si son idénticos o no.

template <typename T>

bool identicos(const Arbin<T>& a,const Arbin<T>& b){

bool iguales= false;
iguales = identicos(a,a.getRaiz(), b ,b.getRaiz(), iguales);
return iguales;
}

template <typename T>
bool identicos (const Arbin<T>& a , const typename Arbin<T>::Iterador& ra, const Arbin<T>& b , const typename Arbin<T>::Iterador& rb, bool &iguales){

if (!ra.arbolVacio() && !rb.arbolVacio())
{
    if(ra.observar() == rb.observar())  //Podria haber usado el != y no complicarme la existencia
    iguales = true;             //He tenido que crear el parametro iguales para que no saliera del programa al catar los dos primeros parametros iguales
    else
        return false;

        if (identicos(a, a.subIzq(ra), b, b.subIzq(rb), iguales ) && identicos(a, a.subDer(ra),b, b.subDer(rb), iguales) )
            return true;
        else
        return false;



}

else
return true;
}

//2. Suponiendo que los nodos del árbol almacenan enteros, construir una función que
//calcule la suma de sus elementos.

int sumaenteros (const Arbin<int>& a , const typename Arbin<int>::Iterador& r ){

if(!r.arbolVacio()){
    int total =0;
    //int valor =r.observar();
    return total +=  sumaenteros(a, a.subIzq(r)) + r.observar() + sumaenteros(a, a.subDer(r)) ;


}

else return 0;
}

int sumaenteros (const Arbin<int>& a){

return sumaenteros(a, a.getRaiz());
}

//4. Escribir una función que visualice los nodos que están en el nivel n de un árbol
//binario.

template <typename T>

void verNivel(const Arbin<T>& a,int nivel){

verNivel(a,a.getRaiz(),nivel);

}

template <typename T>

void verNivel(const Arbin<T>& a,  const typename Arbin<T>::Iterador& r ,int nivel){   //Sin el & es el valor que tenia anteriormente, no se mantiene
if(!r.arbolVacio() && nivel>=0){

    if(nivel ==0)
        cout<< r.observar() << " ";

    else{
        verNivel(a,a.subIzq(r),nivel-1);
        verNivel(a,a.subDer(r),nivel-1);

    }
}

}

//5. Diseñar una función que, dado un árbol binario, devuelva verdadero si el árbol es
//completo y falso en otro caso. Suponemos que el árbol vacío es completo.
//Mas dificil que la milk
template <typename T>

bool completo(const Arbin<T>& a){

int nivel=0;
int candidato = 999;
bool bien = true;
completo(a,a.getRaiz(), bien, nivel);
return bien;
}

template <typename T>   //TODOS LOS NODOS HOJA ESTAN EN EL MISMO NIVEL
void completo(const Arbin<T>& a,  const typename Arbin<T>::Iterador& r, bool& bien, int nivel){

if (!r.arbolVacio()){
    if ( a.subIzq(r).arbolVacio() && a.subDer(r).arbolVacio()){
        bien = false;
    }
        nivel++;
        completo(a,a.subIzq(r),bien,nivel);
        completo(a,a.subDer(r),bien,nivel);


}

}

//6. Se define por frontera de un árbol binario la secuencia formada por los elementos
//almacenados en las hojas de un árbol binario, tomados de izquierda a derecha.
//Diseñar una función que, dado un árbol binario y una lista vacía pasados como
//parámetros, devuelva en dicha lista la frontera del árbol.
template <typename T>

void frontera (const Arbin<T>& a){

return frontera(a,a.getRaiz());
}

template <typename T>
void frontera (const Arbin<T>& a,  const typename Arbin<T>::Iterador& r){

if(!r.arbolVacio()){

    if(a.subIzq(r).arbolVacio() && a.subDer(r).arbolVacio()){
        cout << r.observar() << " ";

    }
    else{
        frontera(a,a.subIzq(r));
        frontera(a,a.subDer(r));
    }



}

}
/****************************************************************************/
/****************************************************************************/
int main(int argc, char *argv[])
{
    Arbin<char> A('t', Arbin<char>('m', Arbin<char>(),
                                        Arbin<char>('f', Arbin<char>(), Arbin<char>())),
                       Arbin<char>('k', Arbin<char>('d', Arbin<char>(), Arbin<char>()),
                                        Arbin<char>()));

    Arbin<char> A2('t', Arbin<char>('m', Arbin<char>(),
                                        Arbin<char>('f', Arbin<char>(), Arbin<char>())),
                       Arbin<char>('k', Arbin<char>('d', Arbin<char>(), Arbin<char>()),
                                        Arbin<char>()));
    Arbin<char> A3('t', Arbin<char>('m', Arbin<char>('ñ',Arbin<char>(), Arbin<char>()),
                                        Arbin<char>('f', Arbin<char>(), Arbin<char>())),
                       Arbin<char>('k', Arbin<char>('d', Arbin<char>(), Arbin<char>()),
                                        Arbin<char>()));
    Arbin<char> B('t', Arbin<char>('n', Arbin<char>(),
                                        Arbin<char>('d', Arbin<char>('e', Arbin<char>(), Arbin<char>()),
                                                         Arbin<char>())),
                       Arbin<char>('m', Arbin<char>('f', Arbin<char>(), Arbin<char>()),
                                        Arbin<char>('n', Arbin<char>(), Arbin<char>())));

    Arbin<char> D('t', Arbin<char>('k', Arbin<char>('d', Arbin<char>(), Arbin<char>()),
                                        Arbin<char>()),
                       Arbin<char>('m', Arbin<char>(),
                                        Arbin<char>('f', Arbin<char>(), Arbin<char>())));

    Arbin<char> E('o', Arbin<char>('r', Arbin<char>(),
                                        Arbin<char>('o', Arbin<char>(), Arbin<char>())),
                       Arbin<char>('l', Arbin<char>('a', Arbin<char>(), Arbin<char>()),
                                        Arbin<char>('e', Arbin<char>(), Arbin<char>())));

    Arbin<int> F(2, Arbin<int>(7, Arbin<int>(2, Arbin<int>(), Arbin<int>()),
                                  Arbin<int>(6, Arbin<int>(5, Arbin<int>(), Arbin<int>()),
                                                Arbin<int>(11, Arbin<int>(), Arbin<int>()))),
                    Arbin<int>(5, Arbin<int>(),
                                  Arbin<int>(9, Arbin<int>(),
                                                  Arbin<int>(4, Arbin<int>(), Arbin<int>()))));

    ABB<int> BB6, BB7;




    cout << "Num. hojas del arbol B: " << numHojas(B) << endl;
    cout << "Num. hojas del arbol E: " << numHojas(E) << endl << endl;

    // COPIA SIMETRICA //
    Arbin<char> C = simetrico(B);
    cout << "Recorrido en inorden del arbol B: " << endl;
    inorden(B, B.getRaiz());
    cout << endl << "Recorrido en inorden del arbol C: " << endl;
    inorden(C, C.getRaiz());
    cout << endl << endl;


    // RECORRIDO EN ZIG-ZAG //
    cout << "Recorrido en zigzag I de B:\n";
    recorridoZigzag(B, 'I');
    cout << endl;
    cout << "Recorrido en zigzag D de C:\n";
    recorridoZigzag(C, 'D');
    cout << endl << endl;


    // COMPENSADO //
    cout << "Esta A compensado?:";
    cout << (compensado(A) ? " SI" : " NO") << endl;
    cout << "Esta B compensado?:";
    cout << (compensado(B) ? " SI" : " NO") << endl << endl;

    // PALABRAS DE UN ARBOL //
    cout << "PALABRAS DE A:\n";
    palabras(E);
    cout << "PALABRAS DE B:\n";
    palabras(B);
    cout << endl;

    // SIGUIENTE MAYOR
    BB6.insertar(8); BB6.insertar(3); BB6.insertar(10); BB6.insertar(1); BB6.insertar(6);
    BB6.insertar(14); BB6.insertar(4); BB6.insertar(7); BB6.insertar(13);
    try
    {
        cout << "Siguiente mayor en BB6 de 5: " << siguienteMayor(BB6, 5) << endl;
        cout << "Siguiente mayor en BB6 de 8: " << siguienteMayor(BB6, 8) << endl;
        cout << "Siguiente mayor en BB6 de 13: " << siguienteMayor(BB6, 13) << endl;
        cout << "Siguiente mayor en BB6 de 14: " << siguienteMayor(BB6, 14) << endl;
    }
    catch(const noHaySiguienteMayor& e)
    {
        cout << e.Mensaje() << endl << endl;
    }

    // POSICION INORDEN //
    BB7.insertar(5); BB7.insertar(1); BB7.insertar(3); BB7.insertar(8); BB7.insertar(6);
    cout << "Posicion Inorden en BB7 de 3: ";
    cout << posicionInorden(BB7, 3);
    cout << endl << "Posicion Inorden en BB7 de 8: ";
    cout << posicionInorden(BB7, 8);
    cout << endl << "Posicion Inorden en BB7 de 7: ";
    cout << posicionInorden(BB7, 7);
    cout << endl << endl;

    // SUMA CAMINO
    cout << "Hay un camino de suma 26 en F?:";
    cout << (haySumaCamino(F, 26) ? " SI" : " NO") << endl;
    cout << "Hay un camino de suma 9 en F?:";
    cout << (haySumaCamino(F, 9) ? " SI" : " NO") << endl << endl;

    //IDENTICOS
    cout << "Son los arboles A y A2 identicos?";
    cout << (identicos(A,A2) ? " SI" : " NO") << endl;
    cout << "Son los arboles A y B identicos?";
    cout << (identicos(A,B) ? " SI" : " NO\n") << endl;

    //SUMAENTEROS
    cout << "La suma de los enteros de F es: ";
    cout << sumaenteros(F) << "\n\n";

    //VERNIVEL
    cout<< "Los nodos del nivel 1 de A son: ";
    verNivel(A,1);

    cout<< "\nLos nodos del nivel 2 de F son: ";
    verNivel(F,2);

    //COMPLETO
    cout << "\n\nEs el arbol A completo? ";
    cout << (completo(A) ? " SI" : " NO") << endl;
    cout << "Es el arbol B completo? ";
    cout << (completo(B) ? " SI" : " NO") << endl;

    //FRONTERA
    cout << "\n\nLa frontera del arbol A es: ";
    frontera(A);
    cout << "\nLa frontera del arbol B es: ";
    frontera(B);

    system("PAUSE");
    return 0;
}




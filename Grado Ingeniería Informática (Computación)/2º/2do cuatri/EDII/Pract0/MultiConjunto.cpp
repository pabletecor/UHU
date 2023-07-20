#include "MultiConjunto.h"
#include "Persona.h"

template <typename T>  Multiconjunto<T>::Multiconjunto(){

this->num = 0;

}


template <typename T> bool Multiconjunto<T>::esVacio() const{
bool vacio = true;

if (this->num != 0)
vacio = false;

return vacio;
}

template <typename T> int Multiconjunto<T>::cardinalidad() const {
return this->num;

}


template <typename T> void Multiconjunto<T>::anade( const T& objeto){
this->c[num++] =  objeto;

}

template <typename T> void Multiconjunto<T>::elimina(const T& objeto){
for (int i = 0; i<num; i++){
    if(this->c[i] == objeto){

      this->c[i] = this->c[--this->num];
        i--;

    }

}

}

template <typename T> bool Multiconjunto<T>::pertenece(const T& objeto) const{
bool encontrado = false;
int i =0;

while (i<num && !encontrado){
    if(this->c[i] == objeto)
        encontrado = true;

    i++;
}

return encontrado;
}


template class Multiconjunto<int>;
template class Multiconjunto<char>;
template class Multiconjunto<Persona>;

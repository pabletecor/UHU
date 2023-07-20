#include <iostream>
using namespace std;
#define M 10

class one {
    int arry [M] ;
public:
        void load();
        int maximum();
        int minimum();

};

void one::load() {

   cout<< "Introduzca 10 numeros: " ;
   for (int i=0; i<=M ; i++) {
    cin>> arry [i];

   }

}


int one::maximum() {
    int maxim= -9999999;
    for(int i=0; i<M; i++ )
        if (arry [i]>maxim ) maxim=arry [i];
    return maxim;

}

int one::minimum() {
    int minim= 9999999;
    for(int i=0; i<M; i++ )
        if (arry [i]<minim ) minim=arry [i];
    return minim;

}

int main(){
    one a;
    a.load();
    cout << "maximum is: " << a.maximum();
    cout<< "minimum is: " << a.minimum() ;

    return 0;
}

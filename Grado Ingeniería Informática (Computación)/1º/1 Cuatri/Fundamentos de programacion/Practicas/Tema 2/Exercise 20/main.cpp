#include <iostream>

using namespace std;

class fraction {

int a ;
int b ;
int c ;
int d ;

public:
    void question () ;
    int sum () ;
    int substract () ;
    int multiply() ;
    int divide () ;


};


void fraction::question () {

cout<< "input the value of a: " ;
cin >> a ;
cout << "input the value of b: " ;
cin >> b ;
cout<< "input the value of c: " ;
cin >> c ;
cout << "input the value of d: " ;
cin >> d ;

}



int fraction::sum () {
    int numer1, denom1 ;
    numer1= a*d + b*c ;
    denom1 = b*d ;

    cout << a << " / " << b << " + " << c << " / " << d << " = " << numer1 << " / " << denom1 ;

    cout<< "\n" ;

}

int fraction ::substract () {
    int numer2, denom2 ;
    numer2= a*d - b*c ;
    denom2 = b*d ;

    cout << a << " / " << b << " - " << c << " / " << d << " = " << numer2 << " / " << denom2 ;


cout<< "\n" ;
}

int fraction :: multiply () {
    int numer3, denom3 ;
    numer3= a*c ;
    denom3 = b*d ;

    cout << a << " / " << b << " * " << c << " / " << d << " = " << numer3 << " / " << denom3 ;
cout<< "\n" ;
}

int fraction :: divide () {
    int numer4, denom4 ;
    numer4 = a*d ;
    denom4 = b*c ;

    cout << a << " / " << b << " : " << c << " / " << d << " = " << numer4 << " / " << denom4 ;
cout<< "\n" ;
}



int main(){

fraction f;

f.question();
f.sum();
f.substract();
f.multiply();
f.divide();

    return 0;
}

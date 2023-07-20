#include <iostream>
#include <cmath>
using namespace std ;
int main () {

int a, b, c, result1, result2 ;

    cout<< "input the value of a: \n " ;
        cin>> a ;

    cout<< "input the value of b: \n " ;
        cin>> b ;

    cout<< "input the value of c: \n " ;
        cin>> c ;


result1= -b+(((sqrt(pow(b,2)) - 4*a*c) ) /2*a ) ;
result2= -b-(((sqrt(pow(b,2)) - 4*a*c) ) /2*a ) ;

cout<< " The results are " <<result1 << " & " <<result2 ;

return 0;
}

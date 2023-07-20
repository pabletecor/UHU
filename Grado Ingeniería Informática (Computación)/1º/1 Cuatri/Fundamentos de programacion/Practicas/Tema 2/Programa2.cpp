#include <iostream>
#include <cmath>
using namespace std ;
int main () {

float x1, x2, y1, y2, distance ;

    cout<< " Indicate the value of x1: \n" ;
        cin>> x1 ;

     cout<< " Indicate the value of x2: \n" ;
        cin>> x2 ;

     cout<< " Indicate the value of y1: \n" ;
        cin>> y1 ;

    cout<< " Indicate the value of y2: \n" ;
        cin>> y2 ;

   distance = sqrt( pow(x1+x2,2) + pow (y1+y2,2)) ;

   cout<< "distance is equal to: " <<distance ;





return 0;
}

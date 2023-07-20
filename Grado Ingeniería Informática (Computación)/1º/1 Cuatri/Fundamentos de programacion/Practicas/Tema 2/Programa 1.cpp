#include <iostream>
using namespace std ;
int main () {

float theory , practice, finalnote ;


    cout<< "Please, input your theory result: \n" ;
        cin>> theory ;

    cout<< "Please, input now your practice result: \n " ;
        cin>> practice ;


finalnote = 0.7*theory + 0.3*practice ;

    cout<< "Your final note is: " << finalnote ;


return 0;
}

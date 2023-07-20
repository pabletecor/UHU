#include <iostream>
#include <cmath>
using namespace std ;
int main () {
int sec, minut, hour ;

    cout<< "Indicate the amount of seconds: \n" ;
        cin>> sec ;

minut=sec/60;
sec=sec%60;

hour=minut/60;
minut=minut%60;

cout<< "The time is " <<hour<< "hours " << minut << "minutes " << sec<< "seconds" ;



return 0;
}

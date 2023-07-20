#include <iostream>
using namespace std;
int calculates_age (int year) {
return (2017-year);
}

int main (){
    int aux;
    cout<< "Please, input your birth year: ";
    cin>> aux ;
    cout<< "You are " << calculates_age(aux) ;

    return 0;
}


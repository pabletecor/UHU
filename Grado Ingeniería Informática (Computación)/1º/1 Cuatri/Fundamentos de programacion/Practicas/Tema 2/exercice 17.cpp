#include <iostream>
using namespace std ;

#define extracosthour 8
#define normalcosthour 5

class employee {
        int normalhours, overtimehours;

public:
    void load ();
    float payroll ();

};


void employee :: load (){
    cout<< "Please, enter # of normal hours worked: " ;
    cin>> normalhours ;
    cout<< "Please, enter # of extra hours worked: " ;
    cin>> overtimehours ;

}

float employee :: payroll () {
    return (normalhours*normalcosthour+overtimehours*extracosthour) *0.85;
}


int main () {

employee e;

    e.load();
    cout<< "payroll is " << e.payroll();

    return 0 ;

}

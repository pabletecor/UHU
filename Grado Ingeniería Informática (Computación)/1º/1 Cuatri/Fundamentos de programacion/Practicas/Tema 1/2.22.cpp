#include <iostream>
#include <math.h>

using namespace std;

class charges {
float q1,q2, r;

public:

    void read();
    double force();

};

void charges::read(){
cout << "The value of q1 (MicroC) is: "; cin>> q1;
cout << "\n \n The value of q2 (MicroC) is: "; cin>> q2;
cout << "\n \n The value of R (m) is: "; cin>> r;

}

double charges::force(){

double fuerza;
double k= 9 * pow(10,9);
q1= q1* pow (10,-6);
q2= q2* pow(10,-6);

fuerza = (k*q1*q2)/(r*r);

cout<< "The force is :" << fuerza;
return fuerza;
}


int main(){
charges q;
q.read();
q.force();

return 0;

}

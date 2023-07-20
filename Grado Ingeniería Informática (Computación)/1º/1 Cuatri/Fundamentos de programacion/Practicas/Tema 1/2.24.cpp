#include <iostream>
#include <math.h>
#define e0 8.85* pow(10,-12)
#define pi 3.141592

using namespace std;

class elecfield {

float q1 ,r;

public:

    void read ();
    double intensity ();

};

void elecfield::read(){

cout << "The value of q1 (MicroC) is: "; cin>> q1;
cout << "\nThe value of R (m) is: "; cin>> r;


}

double elecfield::intensity(){
double intensidad;
q1= q1* pow (10,-6);

intensidad= q1/(4*pi*e0*r*r);

return intensidad;
}

int main(){
elecfield el;
el.read();
cout <<"\n \n Intensity is: " << el.intensity();

return 0;
}

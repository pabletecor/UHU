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
int option1,option2;
cout << "Input the numerical value of q1 "; cin>> q1;

cout<< "\n \nSelect the units of the charge: (choose an option) \n";
cout << "                   menu\n\n";
cout<< "1 ---- MiliC.";
cout<< "2 ---- MicroC.";
cout<< "3 ---- NanoC.";
cin>> option1;


switch(option1){

case 1: q1= q1* pow(10,-3);

    break;

case 3: q1= q1* pow(10,-9);

    break;


}

cout << "Input the numerical value of R "; cin>> r;

cout<< "\n \nSelect the units of R: (choose an option) \n";
cout << "                   menu\n\n";
cout<< "1 ---- dm.";
cout<< "2 ---- cm.";
cout<< "3 ---- mm.";
cin>> option2;


switch(option2){

case 1: r= r* pow(1,-1);

    break;

case 2: r= r* pow(1,-2);

    break;

case 3: r= r* pow(1,-3;

    break;


}

}

double elecfield::intensity(){
double intensidad;

intensidad= q1/(4*pi*e0*r*r);

return intensidad;
}



int main(){
elecfield el;
el.read();
cout <<"\n \n Intensity is: " << el.intensity();

return 0;
}

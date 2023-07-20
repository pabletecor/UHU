#include <iostream>
#include <math.h>
using namespace std;

int main (){
int a,b,c,sol1,sol2,aux;

cout<< "ax^2 + bx + c = 0 \n \n ";

cout<< "Input the value of a: ";
cin>> a;

cout<< "\n Input the value of b: ";
cin>> b;

cout<< "\n Input the value of c: ";
cin>> c;


aux=b*b-4*a*c;

if(aux<0){
    cout<< "\n \n There is no solution.";

}

else
if (aux == 0){
        sol1=-b/(2*a);

    cout<<"\n \n There is only one solution.";
    cout<<"\n the solution is " << sol1;


}

else {

    sol1= (-b+(sqrt(aux)))/(2*a);
    sol2= (-b-(sqrt(aux)))/(2*a);

    cout<< "\n \n The first solution is: "  << sol1;
    cout<< "\n The second solution is: " << sol2;
}

return 0;

}

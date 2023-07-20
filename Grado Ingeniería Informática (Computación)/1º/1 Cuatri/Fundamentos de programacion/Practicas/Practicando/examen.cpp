//primero, te pide un numero hasta que el numero sea multiplo de tres o mayor que 20


#include <iostream>
#include <windows.h>
using namespace std;

int main(){
int inputnumber;

cout<< "Input a number: \n";
do
    cin>> inputnumber;

while ( inputnumber <=20 && inputnumber%3!=0);

cout<< "Okay";

return 0;

}

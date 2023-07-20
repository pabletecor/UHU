#include <iostream>
using namespace std;

int main(){
int i;
int opt;

cout<< "Input a number: " ;
do
cin>> i ;
while (i<0);

 switch (opt) {

 case 0:
     cout<< "The number is less or equal to one";
     break;

 case 1:
     cout<< "The number is less or equal one";

     break;



 case 2:
    cout<< "Two";

    break;

 case 3:
    cout<< "Three";

 default : cout<< "More than three";

 break;
 }

 return 0;
}



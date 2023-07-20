#include <iostream>
using namespace std;
#define   M   10

class two{
int  arry [M];
public:

void load ();
bool findy ();

};

void two::load (){
    for (int i=1; i<=M;i ++){
    arry [i]=i ;

}
}

bool two::findy (){
int number,i=0;
bool found=false;

cout<< "Please, input a number: " ;
cin>> number ;

while( (i<M ) && (found==false)) {
    if(arry[i]==number ) found=true;
    else i++;

}
return found;
}

int main () {
two b;
b.load();
if (b.findy()==true) cout <<" Number is in the array";
    else cout<< "Number is not in the array " ;

    return 0;
}

#include <iostream>

using namespace std;

class sumachar {
int num1 , num2;
char letra;

public:
void information();
void operation();

};


void sumachar::information(){
cout<< "Input the two numbers: ";
cin>> num1 ;
cin>> num2 ;

}

void sumachar::operation() {
int res;
cout<<" Input the character or simbol: " ;
cin>> letra;

if (letra=='s'|| letra =='S' || letra == '+'){
    res= num1+num2 ;
    cout<<"El resultado es "<< res ; }
    else if  (letra=='r'|| letra =='R' || letra == '-'){
    res= num1-num2 ;
    cout<<"El resultado es "<< res ; }

    else
        cout<< "Las cagao manito ";


}

int main (){
sumachar sum ;

sum.information();
sum.operation();

return 0;
}

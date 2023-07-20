#include <iostream>
using namespace std ;

class primenumber {
 int n;
public:
 void load();
 bool isprime();

};

void primenumber::load() {

cout<< "Please, input a number: ";
cin>> n ;


}

bool primenumber::isprime() {

if (n==1 || n==2)
    return true;

    for(int i=2;i<n/2;i++){
          if(n%i==0)
              return false;
    }
return true;
}


int main() {

primenumber p ;
p.load();
if (p.isprime())
    cout << "The number is prime";
else cout << "The number is not prime" ;

return 0;

}


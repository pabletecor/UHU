#include <iostream>
using namespace std;

#define M 10
#define N 15
class three {
int tabla [M][N];
public:
void load();
//Pondrá en cada elemento de la tabla el valor del producto de
//sus índices.
int lookfor();
//Pedirá un número entero por teclado y devolverá 1 si ese
//número está en la tabla, en caso contrario devolverá 0.
};

void three::load(){
for (int i=0 ; i<M ; i++){
    for (int j=0 ; j<N ; j++) {
        tabla[i][j]=i*j ;
        cout<< "\n array [" << i << "] [" << j << "]= ";}
}


}

int three::lookfor() {
int number, i=0 , j=0;
bool found=false;
cout<< "Please input a number: ";
cin>> number;
while ((i< M) && found==false){
        j=0;
while ( (j<N)&& found==false) {
     cout<< "\n array [" << i << "] [" << j << "]= "<< tabla [i][j];
     cout<< number;

if( tabla [i][j] ==number){
    found== true;
    cout<< "SUUUU";}
    else
        j++;
    }
    i++;
}
if (found) return 1;
    else
        return 0;
}


int main () {
    three t;
    t.load();
    cout << "Found (0/1) ? "  << t.lookfor();

    return 0;
}

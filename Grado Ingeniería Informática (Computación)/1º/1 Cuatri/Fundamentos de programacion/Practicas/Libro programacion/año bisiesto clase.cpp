#include <iostream>

using namespace std;

class Fecha {
    int agno;

public:
    bool bisiesto ();
    void leer ();

};
void Fecha::leer () {
cout<<"Introducir agno: "; cin>>agno;
}

bool Fecha::bisiesto (){
    bool bisiesto;
if ((agno%4==0) && (agno%100!=0)){
        bisiesto=true;}
else {bisiesto=false;}

return bisiesto;
}

int main (){
 Fecha f;
 f.leer();

    bool bisiesto=f.bisiesto();
 if (bisiesto==true)
    cout<<"El agno es bisiesto";
    else if (bisiesto==false)
     {
            cout<<"El agno no es bisiesto";
    }


return 0;


}

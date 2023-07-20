#include <iostream>
using namespace std ;

class multiplicationtable {
    int tablefrom, tableto ;
public:
    void requestnumtable ();
    void displaytable();

};

void multiplicationtable::requestnumtable() {
    int aux ;

    do {
        cout << "input the first number: " ;
        cin>> tablefrom ;
        cout<< "inpuut the second numer: " ;
        cin>>tableto ;
        }

        while ((tablefrom < 1 ) || (tablefrom >10 ));
            ((tableto < 1 ) || (tableto >10 ));

        if (tableto<tablefrom) {
            aux=tableto;
            tableto=tablefrom ;
            tablefrom=aux ;


        }


}

void multiplicationtable::displaytable() {
    for (int i=0 ; i <=10 ; i++)
        for (int j= tablefrom; j <=tableto ; j++ )
    cout << j << "x" << i << "=" << j*i << endl ;
    cout<<"\t" ;
}



int main () {
multiplicationtable d;
d.requestnumtable() ;
d.displaytable() ;

return 0 ;


}

#include <iostream>

using namespace std;

int main()
{
    //declaration of variables
    int n1,n2 ;

    cout<< "Please, input 2 integer numbers: \n" ;
        cin>> n1 >> n2 ;


    if (n1==n2)
        cout << "The numbers are the same " ; //equal numbers

    else //different numbers
        if (n1>n2)
            cout<< n1 << " is greater than  " << n2 ;

    else
        cout << n2<< " is grater than  " <<n1 ;


    return 0;
}

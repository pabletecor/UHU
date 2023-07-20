#include <iostream>
using namespace std;

struct one{
int maximum=-99999;
int minimum=999999;

};

int main (){
    one mystruct;
    int number;

    for (int i=1;i<=10;i++ ){
        cout << "input number " << i << ": ";
        cin>>number ;
        if ( number> mystruct.maximum) mystruct.maximum=number;
        if ( number< mystruct.minimum) mystruct.minimum=number;
    }

    cout<< " Maximum is : " << mystruct.maximum;
    cout<< "\n minimum is : " << mystruct.minimum;

}

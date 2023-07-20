#include <iostream>

using namespace std; bool prime = true;

int main(void){
    int option, number;

    cout<<"INPUT NUMBER: "; cin>>number;
    cout<<"1. EVEN/ODD";
    cout<<"2. PRIME";
    cout<<"3. EXIT";
    cout<<"CHOOSE OPTION: "; cin>>option;

    switch (option) {
        case 1: if (number%2==1) cout<<number<<" is odd \n";
                else
                    cout<<number<<" is even \n";
                break;

        case 2: for (int i = number - 1; i > 1; i--)
                if (number%i==0)
                    prime = false;
                if (prime==true)
                    cout<<"number is prime";
                else
                    cout<<"number is not prime";


        case 3: cout<<"BYE";
                break;

        default: cout<< "INVALID OPTION";


    }

return 0;
}

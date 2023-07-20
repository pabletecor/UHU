#include <iostream>

using namespace std;

int main()
{
   int option,num1, num2, sum, sub, mult, div ;

cout << "First, select two numbers: " ;
cin >> num1 >> num2 ;
cout << "\n \n \t \t    MENU \n" ;
cout << "\t \t 1. Sum \n" << "\t \t 2. Substract \n" << "\t \t 3. Multiply \n" << "\t \t 4. divide \n\n " ;
cout << "Please select an option: " ;
    cin >> option ;


if (option < 0 || option > 3 )
    cout << "Don't be silly! \n" ;

    else
        if (option == 1 ) {

sum= num1 + num2 ;
cout<< "The result of the sum is: " << sum ; }


        if (option == 2 ) {

sub = num1 - num2 ;
cout<< "The result of the substraction is: " << sub ; }


        if (option == 3 ) {

mult = num1 * num2 ;

cout<< " The result of the multiplication is: " << mult ; }


        if (option == 4 ) {

div = num1 / num2 ;

cout << " The result of the division is: " << div ; }



    return 0;
}

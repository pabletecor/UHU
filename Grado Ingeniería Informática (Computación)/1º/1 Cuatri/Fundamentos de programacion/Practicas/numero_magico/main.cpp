#include <iostream>
#include <time.h>
#include <stdlib.h>


using namespace std;

int main()
{
   int numero, dato, contador = 0;

   srand(time(NULL)); //generar un numero aleatorio.
   dato = 1 + rand()%(1); // (101[limite superior]-1[limite inferior]).

   do{
        cout << "\nInput  a number: " ;
        cin >> numero;

        if (numero < dato){
            cout << "\nThe number is higher";
        }
        if (numero > dato){
            cout << "\nThe number is lower";
        }

        contador++;

   }while (numero != dato);



   cout << "\nCONGRATULATIONS, YOU HAVE GESS THE NUMBER!!!";
   cout << "\nNumber of tries: " << contador << endl;


    system("pause");
    return 0;
}

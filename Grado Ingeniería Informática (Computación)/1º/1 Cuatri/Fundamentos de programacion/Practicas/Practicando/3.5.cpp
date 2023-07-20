#include <iostream>

using namespace std;

class MultiplicationTable
{
    int TableFrom, TableTo;
public:
    void RequestNumTable();
    void DisplayTable();
};

void MultiplicationTable::RequestNumTable(){
    int aux;
    do{
        cout << "Input two numbers:";
        cin >> TableFrom >> TableTo;
    } while((TableFrom<1)||(TableFrom>10)||(TableTo<1)||(TableTo>10));

    if(TableTo<TableFrom){
        aux=TableTo;
        TableTo=TableFrom;
        TableFrom=aux;
    }
}

void MultiplicationTable::DisplayTable(){

    for(int i=0 ; i<=10; i++){
        for(int j=TableFrom; j<=TableTo; j++)
            cout << j << " x " << i << " = " << j*i << "\t";
        cout << "\n";
    }

}

int main()
{
    MultiplicationTable mt;

    mt.RequestNumTable();
    mt.DisplayTable();
    return 0;
}

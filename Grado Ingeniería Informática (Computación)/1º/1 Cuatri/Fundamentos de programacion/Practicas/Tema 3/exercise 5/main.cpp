#include <iostream>

using namespace std;

class date {
    int year ;
public:
    bool leap ();
    void read();

};

void date::read() {
    cout<< "input a year: ";
    cin>> year ;


}


bool date ::leap(){
    if (year%400==0)
        return true;
    else if ((year%4==0) && (year%100!=0))

            return true;
        else
            return false ;
}


int main()
{
    date d;

    d.read () ;
    if (d.leap())
        cout << "Year is leap " ;
        else
            cout << "Year is not leap" ;
    return 0;
}

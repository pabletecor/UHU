#include <iostream>
using namespace std ;

class grades {
    int n_students, n_fail=0, n_aver=0, n_over=0, n_exc=0 ;
public:
    void dostatistic();
    void result();

};

void grades::dostatistic() {

    int grade;
    cout<< "How many students: " ;
    cin>> n_students;
    for (int i=1; i <=n_students; i++){
        do{
            cout<< "\n Input grade: " << i << ":";
            cin >> grade ;
        } while ((grade<0) || (grade > 10 ));
        if (grade < 5 ) n_fail++ ;
        else if (grade < 7) n_aver++;
        else if (grade < 9) n_over++;
        else n_exc++;
    }
}


void grades::result(){

    cout<< "Nº fails: " <<n_fail << endl ;
    cout<< " Nº averages: " << n_aver << endl;
    cout << "Nº over averages: " << n_over << endl ;
    cout<< "Nº excellents: " << n_exc << endl;
}



int main () {

    grades g ;
    g.dostatistic();
    g.result();

    return 0;

}

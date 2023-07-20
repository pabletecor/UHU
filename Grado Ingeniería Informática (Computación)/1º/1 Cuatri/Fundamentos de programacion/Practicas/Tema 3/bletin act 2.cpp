#include <iostream>
using namespace std ;

class listofnumbers{
    int maximum=-9999, minimum=99999;
    float average;
public:
    void statistics() ;
    int showmax () ;
    int showmin ();
    float showaverage();

};

int listofnumbers::showmax(){
    return maximum;
}

int listofnumbers::showmin() {
    return minimum ;
}

float listofnumbers::showaverage() {
    return average ;
}

void listofnumbers::statistics () {
    int num, aux ;
    cout << " how many integers?: " ;
    cin>> num ;
    for (int i=1; i<=num ; i++ ){
        cout << "input the number " << i << ":" ;
        cin >> aux ;
        if (aux>maximum ) maximum =aux ;
        if (aux<minimum ) minimum =aux ;
        average = average + aux ;

    }
    average=average/num ;
}

int main () {

    listofnumbers j;
    j.statistics () ;
    cout << "maximum: " << j.showmax () << endl ;
    cout << "minimum: " << j.showmin () << endl;
    cout << " average: " << j.showaverage () << endl;

    return 0;

}




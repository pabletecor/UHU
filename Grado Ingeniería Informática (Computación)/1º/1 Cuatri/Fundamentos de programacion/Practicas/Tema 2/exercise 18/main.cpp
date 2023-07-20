#include <iostream>

using namespace std;

class rectangle {

int length;
int width;
public:
    void start();
    void load();
    int area ();
    int change ();

};


void rectangle::start() {
    length = 4;
    width = 2 ;

}


void rectangle::load (){

    cout<< "\n length";
    cin>> length;
    cout<< "\n width";
    cin>> width ;

}

int rectangle :: area (){
    return length* width;


}

int rectangle :: change (){
    int aux ;
    aux= length;
    length=width;
    width=aux;

}



int main()
{
  rectangle r;

  r.start ();
  cout << "Area1 : " <<r.area () ;
  r.change ();
  cout<< "\n area2 : "  << r.area();
  r.load();
  cout<< "\n area3 : " <<r.area () ;

  return 0 ;

}

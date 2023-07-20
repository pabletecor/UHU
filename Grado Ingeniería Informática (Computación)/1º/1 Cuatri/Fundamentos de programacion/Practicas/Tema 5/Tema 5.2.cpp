#include <iostream>
#include <windows.h>
using namespace std;

class tictactoe {

char board [3][3];

public:
    tictactoe();
    void cleanboard();
    void paint();
    bool putpiece(char piece, int row, int column);
    bool checkrow(char piece, int row);
    bool checkcolumn(char piece, int column);
    bool checkdiagonal(char piece, int row , int column);
    bool completeboard();

};

tictactoe::tictactoe(){
    cleanboard();
}

void tictactoe::cleanboard(){
    for (int i=0; i<3; i++){
    for (int j=0; j<3; j++)
        board[i][j]= ' ';
    }
}

void requestposition(char piece, int &row, int &column){

cout<< "\n\n Input a position (range is between 0 and 2): " ;
do{
    cin>> row;
    cin>> column;
}
    while((row>2) && (column>2));

}


void tictactoe::paint(){
    system("cls");
for (int i=0; i<3; i++){
        cout<<" ";
    for (int j=0; j<3; j++){
    cout<< board [i][j];
    if (j<2)
        cout<< "|";
    }
    if (i<2){
        cout<<"\n -+-+-\n";
    }

}

}

bool tictactoe::putpiece(char player, int row, int column){
    if (board[row][column]==' ')
        board [row][column]=player;
    else
        return false;
    return true;
}

bool tictactoe::checkrow(char player, int row){
    bool line=false;
    if((board[row][0]==player) && (board[row][1]==player) && (board[row][3]==player))
        line=true;

        return line;

}

bool tictactoe::checkcolumn(char player, int column){
    bool line=false;
    if((board[column][0]==player) && (board[column][1]==player) && (board[column][3]==player))
        line=true;

        return line;

}

bool tictactoe::checkdiagonal(char player,int column, int row){
    bool line=false;
    if (((board[0][0]==player)&&(board[1][1]==player)&&(board[2][2]==player))||((board[0][2]==player)&&(board[1][1]==player)&&(board[2][0]==player)))
line=true;

return line;
}

bool tictactoe::completeboard(){
for (int i=0;i<3;i++){
    for (int j=0;j<3;j++)
    if (board[i][j]==' ')
    return false;
else
    return true;

}

}


int main(){
char playagain;
bool gamefinished = false;
int row, column;
char player= 'X';
tictactoe game;

do{


      do{
        //At the beginning of the game the board will be shown (2)
        //Start the game
        if(playagain=='Y'){ //Second and later games, we need to reset
            game.cleanboard();
            gamefinished=false;
            player='X';
        }
        game.paint();
        //Request a valid move to the player with turn
        do{
            requestposition(player, row, column); //User moves (3)
        } while(game.putpiece(player, row, column)==false); //Check if valid (4)

        if((game.checkcolumn(player,column)||game.checkrow(player,row))||
            game.checkdiagonal(player,row,column)){ // (5)
            //game finished
            gamefinished=true;
            cout << "\n\nGAME FINISHED, WINS PLAYER " << player;
            } else { // (6)
                //change player
                if(player=='X')
                    player='O';
                else
                    player='X';
            }
        } while(game.completeboard()==false && gamefinished==false); //(7)

     if(game.completeboard()==true) cout << "\n\n Game finished and TIED!!! ";

     cout << "\n Do you want to play again? (Y/N) ";
     cin >> playagain;
    }while(playagain=='Y'); //(8)

    return 0;
}

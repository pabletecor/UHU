#include <iostream>
#include <stdlib.h>

using namespace std;

class TicTacToe{
    char Board[3][3];
public:
    TicTacToe();
    void CleanBoard();
    void Paint();
    bool PutPiece(char piece, int row, int column);
    bool CheckRow(char piece, int row);
    bool CheckColumn(char piece, int column);
    bool CheckDiagonal(char piece, int row, int column);
    bool CompletedBoard();
    };

    TicTacToe::TicTacToe(){
        CleanBoard();
    }
    void TicTacToe::CleanBoard(){
        for(int i=0; i<=2; i++)
            for(int j=0; j<=2; j++)
                Board[i][j]=' '; //We reset each board's position
    }
    void TicTacToe::Paint(){
        system("cls"); //function included in stdlib.h that clear the screen
        for(int i=0; i<=2; i++){
            cout << " ";
            for(int j=0; j<=2; j++){
                cout << Board[i][j];
                if(j<2) cout << "|";
            }
            if(i<2) cout << "\n -+-+-\n";
        }
    }
    bool TicTacToe::PutPiece(char player, int row, int column){
        if(Board[row][column]==' ') //position available
            Board[row][column]=player;
        else
            return false;
        return true;
    }
    bool TicTacToe::CheckRow(char player, int row){
        bool line=false;
        if((Board[row][0]==player)&&(Board[row][1]==player)&&(Board[row][2]==player))
            line=true;
        return line;
    }
    bool TicTacToe::CheckColumn(char player, int column){
        bool line=false;
        if((Board[0][column]==player)&&(Board[1][column]==player)&&(Board[2][column]==player))
            line=true;
        return line;
    }
    bool TicTacToe::CheckDiagonal(char player, int row, int column){
        bool line=false;
        if((Board[0][0]==player)&&(Board[1][1]==player)&&(Board[2][2]==player))
            line=true;
        if((Board[0][2]==player)&&(Board[1][1]==player)&&(Board[2][0]==player))
            line=true;
        return line;
    }
    bool TicTacToe::CompletedBoard(){
        for(int i=0; i<=2; i++)
            for(int j=0; j<=2; j++)
                if(Board[i][j]==' ') return false;
        return true;
    }


    void RequestPosition(char player, int &row, int &column){
    do{
        cout << "\n\nPlease, player "<<player<<", input row [0,2]:";
        cin >> row;
    }while(row<0||row>2);
    do{
        cout << "\n\nPlease, player "<<player<<", input column [0,2]:";
        cin >> column;
    }while(column<0||column>2);

    }


int main()
{
    char playAgain=' ';     //variable to control if users want to play again
    bool gameFinished=false;  //variable to control if game has finished
    int row, column;
    char player='X';   //first player to move (1)
    TicTacToe game;
    do{


      do{
        //At the beginning of the game the board will be shown (2)
        //Start the game
        if(playAgain=='Y'){ //Second and later games, we need to reset
            game.CleanBoard();
            gameFinished=false;
            player='X';
        }
        game.Paint();
        //Request a valid move to the player with turn
        do{
            RequestPosition(player, row, column); //User moves (3)
        } while(game.PutPiece(player, row, column)==false); //Check if valid (4)

        if((game.CheckColumn(player,column)||game.CheckRow(player,row))||
            game.CheckDiagonal(player,row,column)){ // (5)
            //game finished
            gameFinished=true;
            cout << "\n\nGAME FINISHED, WINS PLAYER " << player;
            } else { // (6)
                //change player
                if(player=='X')
                    player='O';
                else
                    player='X';
            }
        } while(game.CompletedBoard()==false && gameFinished==false); //(7)

     if(game.CompletedBoard()==true) cout << "\n\n Game finished and TIED!!! ";

     cout << "\n Do you want to play again? (Y/N) ";
     cin >> playAgain;
    }while(playAgain=='Y'); //(8)

    return 0;
}

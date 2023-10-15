package com.jack.sloto.tictactoe;

public class Game {
    //Represents empty cell in 2D array
     static final char empty ='\u0000';

     //Used to check if current player's symbol is circle
    private Boolean isCircle=false;

    //2D array stores current game board
    private final char[][] board=new char[3][3];

    //Update 2D array with current state
    void playDone(int cellIndex){
        if (cellIndex<=2){
            board[0][cellIndex]=isCircle?'O':'X';
        }
        else if (cellIndex<=5) {
            board[1][cellIndex%3]=isCircle?'O':'X';
        }
        else{
            board[2][cellIndex%6]=isCircle?'O':'X';
        }
        isCircle=!isCircle;
    }
    Boolean getIsCircle(){
         return isCircle;
    }

    //Check if current player win
    char isWin(){
        if (board[0][0]==board[1][1]&&board[2][2]==board[0][0])
            if (board[0][0]!=empty)
                return board[0][0];
        if (board[2][0]==board[1][1]&&board[2][0]==board[0][2])
            if (board[2][0]!=empty)
                return board[2][0];
        for (int i = 0; i < 3; i++) {
                if (board[i][0]==board[i][1]&&board[i][2]==board[i][0])
                    if (board[i][0]!=empty)
                        return board[i][0];
                if (board[0][i]==board[1][i]&&board[2][i]==board[0][i])
                    if (board[0][i]!=empty)
                        return board[0][i];
        }
        return empty;
    }

    //Check if the board is full and no one win
    boolean isDraw(){
        boolean draw=true;
        if (isWin()!=empty)  return false;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == empty) {
                    draw = false;
                    break;
                }
            }
        }
        return draw;
    }

}

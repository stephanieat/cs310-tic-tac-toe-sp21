package edu.jsu.mcis.cs310.tictactoe;

import java.text.CollationElementIterator;
import java.util.*;

/**
* TicTacToeModel implements the Model for the Tic-Tac-Toe game.
*
* @author  Stephanie Thompson
* @version 1.0
*/
public class TicTacToeModel {
    
    /**
     * The contents of the Tic-Tac-Toe game board
     */
    private TicTacToeSquare[][] board;
    
    /**
     * xTurn is true if X is the current player, or false if O is the current
     * player
     */
    private boolean xTurn;
    
    /**
     * The dimension (width and height) of the game board
     */
    private int dimension;

    /**
    * Default Constructor (uses the default dimension)
    */    
    public TicTacToeModel() {
        
        this(TicTacToe.DEFAULT_DIMENSION);
        
    }
    
    /**
    * Constructor (uses specified dimension)
    * 
    * @param dimension The <em>dimension</em> (width and height) of the new
    * Tic-Tac-Toe board.
    */
    public TicTacToeModel(int dimension) {
        
        /* Initialize dimension; X goes first */
        
        this.dimension = dimension;
        xTurn = true;
        
        /* Create board as a 2D TicTacToeSquare array */
        
        board = new TicTacToeSquare[dimension][dimension];

        /* Initialize board (fill with TicTacToeSquare.EMPTY) */
        
        // INSERT YOUR CODE HERE
        for (int i = 0; i < dimension; i++)
            {
                for (int j = 0; j < dimension; j++)
                {
                    board[i][j] = TicTacToeSquare.EMPTY;
                }
            }
    }
    /**
    * Use isValidSquare(int, int) to check if the specified square is in range,
    * and use isSquareMarked(int, int) to check if the square is currently
    * empty.  If both conditions are satisfied, create a mark in the square for
    * the current player, then toggle xTurn from true to false (or vice-versa)
    * to switch to the other player before returning TRUE.  Otherwise, return
    * FALSE.
    *
    * @param  row  the row (Y coordinate) of the square
    * @param  col  the column (X coordinate) of the square
    * @return      a Boolean value representing the result of the attempt to
    * make this mark: true if the attempt was successful, and false otherwise
    * @see         TicTacToeSquare
    */
    public boolean makeMark(int row, int col) {

       if (isValidSquare(row, col) && !this.isSquareMarked(row, col))
        {
            if (isXTurn())
            {
              this.board[row][col] = TicTacToeSquare.X;
              xTurn = false; 
              return true;             
            }
            else
            {
                this.board[row][col] = TicTacToeSquare.O;
                xTurn = true; 
                return true;
            }
        }        
       else return false; // this is a stub; you may need to remove it later!       
    }
    
    /**
    * Checks if the specified square is within range (that is, within the bounds
    * of the game board).
    *
    * @param  row  the row (Y coordinate) of the square
    * @param  col  the column (X coordinate) of the square
    * @return      a Boolean value: true if the square is in range, and false
    * if it is not
    */
    private boolean isValidSquare(int row, int col) {

        if ((row <= dimension - 1) && (row >= 0))
        {
            if ((col <= dimension -1) && (col >= 0))
            {
                return true;
            }
        }       
       return false; // this is a stub; you may need to remove it later!
        
    }
    
    /**
    * Checks if the specified square is marked.
    *
    * @param  row  the row (Y coordinate) of the square
    * @param  col  the column (X coordinate) of the square
    * @return      a Boolean value: true if the square is marked, and false
    * if it is not
    */
    private boolean isSquareMarked(int row, int col) {
        if (board[row][col] != TicTacToeSquare.EMPTY)
        {
            return true;
        }
        
        else return false; // this is a stub; you may need to remove it later!            
    }
    
    /**
    * Returns a {@link TicTacToeSquare} object representing the content of the
    * specified square of the Tic-Tac-Toe board.
    *
    * @param  row  the row (Y coordinate) of the square
    * @param  col  the column (X coordinate) of the square
    * @return      the content of the specified square
    * @see         TicTacToeSquare
    */
    public TicTacToeSquare getSquare(int row, int col) {

        return (isValidSquare(row, col) ? board[row][col] : TicTacToeSquare.EMPTY);                    
    }
    
    /**
    * Use isMarkWin() to determine if X or O is the winner, if the game is a
    * tie, or if the game is still in progress. Return the current state of the
    * game as a {@link TicTacToeState} object.
    *
    * @return      the current state of the Tic-Tac-Toe game
    * @see         TicTacToeState
    */
    public TicTacToeState getState() {

        if (isMarkWin(TicTacToeSquare.X)) 
        {
            return TicTacToeState.X;
        }
        if (isMarkWin(TicTacToeSquare.O)) 
        {
            return TicTacToeState.O;
        }
        if (isTie()) 
        {
            return TicTacToeState.TIE;
        }
        else return TicTacToeState.NONE;      
    }
    
    /**
    * Check the squares of the Tic-Tac-Toe board to see if the specified player
    * is the winner.
    *
    * @param  mark  the mark representing the player to be checked (X or O)
    * @return       true if the specified player is the winner, or false if not
    * @see          TicTacToeSquare
    */
    private boolean isMarkWin(TicTacToeSquare mark) {

        for (int i = 0; i < dimension; ++i )
        { 
            int countRow = 0;
            int countCol= 0;
            for (int j = 0; j < dimension; ++j)
            {
                if (board[i][j].equals(mark))
                {
                    countRow++;
                }           
                if(board[j][i].equals(mark))
                {
                    countCol++;
                }  
            }    
            if (countRow == (dimension) || countCol == (dimension))
                {
                    return true;
                }
        }
       
        int countLDiag = 0;
        int countRDiag = 0;
        for (int i = 0; i < dimension; ++i)
        {
            if (board[i][i].equals(mark))
            {
                countRDiag++;
            }

            if (board[dimension-1-i][i].equals(mark))
            {          
                countLDiag++;
            }
        }  
        if (countRDiag == (dimension) || countLDiag == (dimension))
        {
            return true;
        }

       return false;
    }       
    
    
    /**
    * Check the squares of the board to see if the Tic-Tac-Toe game is currently
    * in a tie state.
    *
    * @return  true if the game is currently a tie, or false otherwise
    */	
    private boolean isTie() {

        if (isMarkWin(TicTacToeSquare.X) || (isMarkWin(TicTacToeSquare.O)))
        {
            return false;
        }

        for (int x = 0; x < dimension; ++x) 
        {
            for (int y = 0; y < dimension; ++y) 
            {
                if (board[x][y].equals(TicTacToeSquare.EMPTY)) 
                {
                    return false;
                }
            }
        }       
        return true;        
    }

    /**
    * Uses {@link #getState() getState} to checks if the Tic-Tac-Toe game is
    * currently over, either because a player has won or because the game is
    * in a tie state.
    *
    * @return  true if the game is currently over, or false otherwise
    */	
    public boolean isGameover() {
        
        return TicTacToeState.NONE != getState();
        
    }

    /**
    * Getter for xTurn.
    *
    * @return  true if X is the current player, or false if O is the current
    * player
    */
    public boolean isXTurn() {
        
        return xTurn;
        
    }
    
    /**
    * Getter for dimension.
    *
    * @return  the <em>dimension</em> (width and height) of the Tic-Tac-Toe
    * game board
    */
    public int getDimension() {
        
        return dimension;
        
    }
    
    /**
    * <p>Returns the current content and state of the Tic-Tac-Toe game board as
    * a formatted String.  This method <em>must</em> use a {@link StringBuilder}
    * to compose the output String, which should not include any empty lines.</p>
    * <p>Here is an example of how the output for a 3x3 game board should
    * appear (also see the example output on Canvas):</p>
    * <code>&nbsp;&nbsp;012<br>0&nbsp;O&nbsp;&nbsp;<br>1&nbsp;&nbsp;X&nbsp;<br>2&nbsp;O&nbsp;X</code>
    * @return      the representation of the Tic-Tac-Toe game board
    * @see         StringBuilder
    */
    @Override
    public String toString() {

       // columns
       StringBuilder output = new StringBuilder("  ");
       for (int i = 0; i < dimension; ++i) 
       {
        output.append(i);
       }
       output.append("\n");

       // rows
       for (int x = 0; x < dimension; ++x) 
       {
           output.append(new StringBuilder("\n" + x + " "));
           
           for (int y = 0; y < dimension; ++y) 
           {
            output.append(board[x][y]);
           }
       }
        output.append("\n");
        return output.toString();
        
    }   
    
}
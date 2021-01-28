package edu.jsu.mcis.cs310.tictactoe;

import org.junit.*;
import static org.junit.Assert.*;

public class TicTacToeModelTest {
	
    private TicTacToeModel board;

    @Before
    public void setup(){
        
        board = new TicTacToeModel(3);
        
    }

    @Test
    public void testInitialBoardIsEmpty() {
        
        for (int row = 0; row < board.getDimension(); row++) {
            
            for (int col = 0; col < board.getDimension(); col++) {
                
                assertEquals(TicTacToeSquare.EMPTY, board.getSquare(row,col));   
                
            }
            
        }
        
    }

    @Test
    public void testMarkXInUpperRightCorner() {
        
        assertEquals(TicTacToeSquare.EMPTY, board.getSquare(0,2));
        assertTrue(board.makeMark(0,2));
        assertEquals(TicTacToeSquare.X, board.getSquare(0,2));
        
    }

    @Test
    public void testMarkOInBottomLeftCorner() {
        
        assertEquals(TicTacToeSquare.EMPTY, board.getSquare(1,1));
        assertTrue(board.makeMark(1,1));
        assertEquals(TicTacToeSquare.X, board.getSquare(1,1));
        assertEquals(TicTacToeSquare.EMPTY, board.getSquare(2,0));
        assertTrue(board.makeMark(2,0));
        assertEquals(TicTacToeSquare.O, board.getSquare(2,0));
        
    }

    @Test
    public void testUnableToMarkOverExistingMark() {
        
        assertEquals(TicTacToeSquare.EMPTY, board.getSquare(1,1));
        assertTrue(board.makeMark(1, 1));
        assertEquals(TicTacToeSquare.X, board.getSquare(1,1));
        assertFalse(board.makeMark(1, 1));
        assertEquals(TicTacToeSquare.X, board.getSquare(1,1));
        
    }

    @Test
    public void testGameIsNotOverAfterTheFirstMark() {
        
        assertEquals(TicTacToeState.NONE, board.getState());
        assertTrue(board.makeMark(0, 0));
        assertEquals(TicTacToeState.NONE, board.getState());
        
    }

    @Test
    public void testGameIsWonByXHorizontallyAcrossTopRow() {
        
        assertEquals(TicTacToeState.NONE, board.getState());
        board.makeMark(0, 0);
        assertEquals(TicTacToeState.NONE, board.getState());
        board.makeMark(1, 1);
        assertEquals(TicTacToeState.NONE, board.getState());
        board.makeMark(0, 1);
        assertEquals(TicTacToeState.NONE, board.getState());
        board.makeMark(2, 2);
        assertEquals(TicTacToeState.NONE, board.getState());
        board.makeMark(0, 2);
        assertEquals(TicTacToeState.X, board.getState());
        
    }

    @Test
    public void testGameIsOverByTieIfAllLocationsAreFilled() {
        
        assertEquals(TicTacToeState.NONE, board.getState()); 
        board.makeMark(0, 0);										   
        assertEquals(TicTacToeState.NONE, board.getState());  
        board.makeMark(0, 2);
        assertEquals(TicTacToeState.NONE, board.getState());
        board.makeMark(0, 1);
        assertEquals(TicTacToeState.NONE, board.getState());
        board.makeMark(1, 0);
        assertEquals(TicTacToeState.NONE, board.getState());
        board.makeMark(1, 2);
        assertEquals(TicTacToeState.NONE, board.getState());
        board.makeMark(1, 1);
        assertEquals(TicTacToeState.NONE, board.getState());
        board.makeMark(2, 0);
        assertEquals(TicTacToeState.NONE, board.getState());
        board.makeMark(2, 2);
        assertEquals(TicTacToeState.NONE, board.getState());
        board.makeMark(2, 1);
        assertEquals(TicTacToeState.TIE, board.getState());
        
    }

    @Test
    public void testOutOfBoundsMark() {
        
        assertFalse(board.makeMark(0,3));
        assertFalse(board.makeMark(-1,0));
        assertFalse(board.makeMark(0,-1));
        assertFalse(board.makeMark(40000, 3));
        assertTrue(board.makeMark(1,1));
        
    }

    @Test  
    public void testGameIsWonByOWithFirstCol() {
        
        board.makeMark(1, 1);
        assertEquals(TicTacToeState.NONE, board.getState());
        board.makeMark(0, 0);
        assertEquals(TicTacToeState.NONE, board.getState());
        board.makeMark(0, 1);
        assertEquals(TicTacToeState.NONE, board.getState());
        board.makeMark(1, 0);
        assertEquals(TicTacToeState.NONE, board.getState());
        board.makeMark(0, 2);
        assertEquals(TicTacToeState.NONE, board.getState());
        board.makeMark(2, 0);
        assertEquals(TicTacToeState.O, board.getState());
        
    }

    @Test  
    public void testGameIsWonByXDiagonallyFromTopLeftToBottomRight() {
        
        assertEquals(TicTacToeState.NONE, board.getState());
        board.makeMark(0, 0);
        assertEquals(TicTacToeState.NONE, board.getState());
        board.makeMark(0, 1);
        assertEquals(TicTacToeState.NONE, board.getState());
        board.makeMark(1, 1);
        assertEquals(TicTacToeState.NONE, board.getState());
        board.makeMark(0, 2);
        assertEquals(TicTacToeState.NONE, board.getState());
        board.makeMark(2, 2);
        assertEquals(TicTacToeState.X, board.getState());
        
    }

    @Test
    public void testGameisWonByODiagonallyFromBottomLeftToTopRight() {
        
        assertEquals(TicTacToeState.NONE, board.getState());
        board.makeMark(2,1);
        assertEquals(TicTacToeState.NONE, board.getState());
        board.makeMark(2,0);
        assertEquals(TicTacToeState.NONE, board.getState());
        board.makeMark(0,0);
        assertEquals(TicTacToeState.NONE, board.getState());
        board.makeMark(1,1);
        assertEquals(TicTacToeState.NONE, board.getState());
        board.makeMark(0,1);
        assertEquals(TicTacToeState.NONE, board.getState());
        board.makeMark(0,2);
        assertEquals(TicTacToeState.O, board.getState());
        
    }

    @Test
    public void testXWinDiagonallyFromTopLeftToBottomRightOn5x5Board() {
        
        TicTacToeModel largerBoard = new TicTacToeModel(5);
        assertEquals(5, largerBoard.getDimension());
        largerBoard.makeMark(0,0);
        assertEquals(TicTacToeSquare.X, largerBoard.getSquare(0,0));
        largerBoard.makeMark(0,1);
        assertEquals(TicTacToeSquare.O, largerBoard.getSquare(0,1));
        largerBoard.makeMark(1,1);
        assertEquals(TicTacToeSquare.X, largerBoard.getSquare(1,1));
        largerBoard.makeMark(0,2);
        assertEquals(TicTacToeSquare.O, largerBoard.getSquare(0,2));
        largerBoard.makeMark(2,2);
        assertEquals(TicTacToeSquare.X, largerBoard.getSquare(2,2));
        largerBoard.makeMark(0,3);
        assertEquals(TicTacToeSquare.O, largerBoard.getSquare(0,3));
        largerBoard.makeMark(3,3);
        assertEquals(TicTacToeSquare.X, largerBoard.getSquare(3,3));
        largerBoard.makeMark(0,4);
        assertEquals(TicTacToeSquare.O, largerBoard.getSquare(0,4));
        largerBoard.makeMark(4,4);
        assertEquals(TicTacToeSquare.X, largerBoard.getSquare(4,4));
        assertEquals(TicTacToeState.X, largerBoard.getState());
        
    }
		
}
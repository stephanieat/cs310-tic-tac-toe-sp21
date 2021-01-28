package edu.jsu.mcis.cs310.tictactoe;

import edu.jsu.mcis.cs310.tictactoe.TicTacToeModel;
import edu.jsu.mcis.cs310.tictactoe.TicTacToeView;
import org.junit.*;
import static org.junit.Assert.*;
import java.io.*;

public class TicTacToeViewTest {
	
    private TicTacToeModel model;
    private TicTacToeView view;
    private ByteArrayOutputStream output;

    @Before
    public void setup() {
        
        model = new TicTacToeModel(3);
        view = new TicTacToeView();
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        
    }
	
    @After
    public void cleanup() {
        
        System.setOut(null);
        
    }
	
    @Test
    public void testViewOfBlankGrid() {
        
        view.showBoard(model.toString());
        String s = "\n\n  012\n\n0 ---\n1 ---\n2 ---\n\n\n";
        assertEquals(s.length(), output.size());
        
    }
	
}

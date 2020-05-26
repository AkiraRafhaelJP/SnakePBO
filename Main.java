import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;

public class Main {
	public Main() {
		JFrame frame = new JFrame("SnakePBO");
		Board board = new Board();
		
		frame.add(board);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);    
	}
	
	public static void main(String[] args) {
		new Main();
	}

}

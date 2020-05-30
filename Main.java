import javax.swing.JFrame;

public class Main{
	public Main() {
		JFrame frame = new JFrame("SnakePBO");
		Board board = new Board();
		Menu menu = new Menu();
		
		frame.add(board);
		frame.setJMenuBar(menu);
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

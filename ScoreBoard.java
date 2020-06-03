import java.awt.Component;
import java.awt.Dimension;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScoreBoard extends JFrame{
	private static final long serialVersionUID = 1L;
	private final int WIDTH = 300;
	private final int HEIGHT = 200;
	private Score currentScore;
	
	private ArrayList<Score> scores;
	
	private JLabel label;
	private JLabel label2;
	private JPanel panel;
	private JLabel[] labels;
	
	public ScoreBoard(Score score) {
//		this.setSize(WIDTH, HEIGHT);
		currentScore = new Score(score);
		panel = new JPanel();
		scores = new ArrayList<Score>();
		try {
			getHighScore();
			printScore();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setVisible(true);
		pack();
		setTitle("High Score");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	public void getHighScore() throws IOException{
		File fileScore = new File("High Score.txt");
		Scanner scan = new Scanner(fileScore);
		while(scan.hasNextInt()) {
			scores.add(new Score(scan.nextInt()));
		}
		scan.close();
		insertNewScore();
	}
	
	public void insertNewScore() throws IOException{
		Writer writer = new FileWriter(new File("High Score.txt"));
		int counter = 0;
		int temp = currentScore.getScore();
		while(counter < 5) {
			if(scores.get(counter).getScore() < temp) {
				scores.add(counter, new Score(currentScore));
				break;
			}
			counter++;
		}
		
		counter = 0;
		while(counter < 5) {
			writer.write(scores.get(counter).getScore() + "\n");
			counter++;
		}
		writer.close();
	}
	
	public void printScore() {
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		label = new JLabel("Your Score : " + currentScore.getScore());
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		label2 = new JLabel("High Score : ");
		label2.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		panel.add(label);
		panel.add(label2);
		
		labels = new JLabel[scores.size()];
		for(int i = 0; i < 5; i++) {
			labels[i] = new JLabel(Integer.toString(i + 1) + ". " + scores.get(i).getScore() + '\n');
			labels[i].setAlignmentX(Component.CENTER_ALIGNMENT);
			panel.add(labels[i]);
		}
		add(panel);
	}
}

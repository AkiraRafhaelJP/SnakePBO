import javax.swing.JPanel;

public class Score extends JPanel{
	private static final long serialVersionUID = 1L;
	private int score;
	public Score() {
		score = 0;
	}
	
	int getScore() {
		return score;
	}
	
	public void setScore(int newScore) {
		this.score = newScore;
	}
}

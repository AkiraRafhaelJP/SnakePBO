
public class Score{
	private static final long serialVersionUID = 1L;
	private int score;
	public Score() {
		score = 0;
	}
	
	public Score(Score initScore) {
		score = initScore.getScore();
	}
	
	public Score(int initScore) {
		score = initScore;
	}
	
	int getScore() {
		return score;
	}
	
	public void setScore(int newScore) {
		this.score = newScore;
	}
}

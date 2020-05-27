import java.awt.Color;
import java.awt.Graphics;

public class Food implements BoardObject{
	private int x, y, width, height;
	private static int multiplyBonus = 0;
	
	public Food(int x, int y, int tileSize) {
		this.x = x;
		this.y = y;
		this.width = tileSize;
		this.height = tileSize;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.RED);
		if(multiplyBonus > 4) {
			g.fillOval(x * width, y * height, width * 2, height * 2);
		}else{
			g.fillOval(x * width, y * height, width, height);
		}
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public static int getMultiplyBonus() {
		return multiplyBonus;
	}
	
	public static void setMultiplyBonus(int newMulti) {
		multiplyBonus = newMulti;
	}

}

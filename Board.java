import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;

public class Board extends JPanel implements Runnable, KeyListener{
	private static final long serialVersionUID = 1L;
	final private int HEIGHT = 512;
	final private int WIDTH = 512;
	private Thread thread;
	private boolean isRunning;
	
	private ArrayList<Snake> snake;
	
	private Food[] bigFood;
	private ArrayList<Food> foods;
	
	private Random randomPos;
	private Score score;
	
	private int headX = 5, headY = 5, snakeSize = 5;
	private int ticks = 0;
	private int limit = 300000;
	
	private enum Direction{
		UP, DOWN, LEFT, RIGHT
	}
	private Direction snakeDirection = Direction.RIGHT;
	private boolean up = false, down = false, left = false, right = true;
	
	public Board() {
		setFocusable(true);
		addKeyListener(this);
		
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		snake = new ArrayList<Snake>();
		foods = new ArrayList<Food>();
		randomPos = new Random();
		score = new Score();
		start();
	}
	
	public void start() {
		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public void stop() {
		isRunning = false;
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		new ScoreBoard(score);
	}
	
	public void tick() {
		if(snake.size() == 0) {
			snake.add(new Snake(headX, headY, 10));
		}
		
		ticks++;
	
		if(ticks > limit) {
			if(snakeDirection == Direction.UP) headY--;
			if(snakeDirection == Direction.DOWN) headY++;
			if(snakeDirection == Direction.LEFT) headX--;
			if(snakeDirection == Direction.RIGHT) headX++;
			
			ticks = 0;
			snake.add(new Snake(headX, headY, 10));
			
			if(snake.size() > snakeSize) {
				snake.remove(0);
			}	
		}
		
		if(foods.size() == 0) {
			int newX = randomPos.nextInt(51);
			int newY = randomPos.nextInt(51);
			
			if(Food.getMultiplyBonus() > 4) {
				bigFood = new Food[4];
				bigFood[0] = new Food(newX, newY, 10);
				bigFood[1] = new Food(newX + 1, newY, 10);
				bigFood[2] = new Food(newX, newY + 1, 10);
				bigFood[3] = new Food(newX + 1, newY + 1, 10);
				for(int i = 0; i < 4; i++) foods.add(bigFood[i]);
			}
			else {
				foods.add(new Food(newX, newY, 10));
			}
		}
		
		for(int i = 0; i < foods.size(); i++) {
			if(headX == foods.get(i).getX() && headY == foods.get(i).getY()) {
				if(Food.getMultiplyBonus() > 4) {
					snakeSize += 5;
					score.setScore(score.getScore() + 5);
					Food.setMultiplyBonus(0);
				}else {
					snakeSize++;
					score.setScore(score.getScore() + 1);
					Food.setMultiplyBonus(Food.getMultiplyBonus() + 1);
				}
				foods.clear();
			}
		}
		
		for(int i = 0; i < snake.size() - 1; i++) {
			if(headX == snake.get(i).getX() && headY == snake.get(i).getY()) {
				stop();
			}
		}
		
		if(headX < 0) {
			headX = 51;
		}
		if(headX > 51) {
			headX = 0;
		}
		if(headY < 0) {
			headY = 51;
		}
		if(headY > 51) {
			headY = 0;
		}
	}
	
	public void paint(Graphics g){
		g.clearRect(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		for(int i = 0; i < snake.size(); i++) {
			snake.get(i).draw(g);
		}
		
		if(foods.size() > 0)
			foods.get(0).draw(g);
	}

	public void run(){
		while(isRunning) {
			tick();
			repaint();
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_UP && !down) {
			snakeDirection = Direction.UP;
			up = true;
			left = false;
			right = false;
		}
		if(key == KeyEvent.VK_DOWN && !up) {
			snakeDirection = Direction.DOWN;
			down = true;
			left = false;
			right = false;
		}
		if(key == KeyEvent.VK_LEFT && !right) {
			snakeDirection = Direction.LEFT;
			left = true;
			up = false;
			down = false;
		}
		if(key == KeyEvent.VK_RIGHT && !left) {
			snakeDirection = Direction.RIGHT;
			right = true;
			up = false;
			down = false;
		}
		if(key == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}
		if(e.getKeyChar() == '=') {
			limit -= 50000;
			if(limit == 0) limit = 50000;
		}
		if(e.getKeyChar() == '-') {
			limit += 50000;
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {}
}

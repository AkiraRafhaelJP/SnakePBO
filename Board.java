import java.awt.Container;
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
	
	private Snake s;
	private ArrayList<Snake> snake;
	
	private Food food;
	private ArrayList<Food> foods;
	
	private Random randomPos;
	private int score;
	
	private int headX = 5, headY = 5, snakeSize = 5;
	private int ticks = 0;
	
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
		start();
	}
	
	public void start() {
		//score.setScore(0);
		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public void stop() {
		isRunning = false;
		System.out.println("Score : " + score);
		try {
			thread.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void tick() {
		if(snake.size() == 0) {
			s = new Snake(headX, headY, 10);
			snake.add(s);
		}
		
		ticks++;
		
		//perpindahan posisi ular mengikuti input 
		if(ticks > 300000) {
			if(snakeDirection == Direction.UP) headY--;
			if(snakeDirection == Direction.DOWN) headY++;
			if(snakeDirection == Direction.LEFT) headX--;
			if(snakeDirection == Direction.RIGHT) headX++;
			
			ticks = 0;
			s = new Snake(headX, headY, 10);
			snake.add(s);
			
			if(snake.size() > snakeSize) {
				snake.remove(0);
			}	
		}
		
		if(foods.size() == 0) {
			int newX = randomPos.nextInt(51);
			int newY = randomPos.nextInt(51);
			
			food = new Food(newX, newY, 10);
			foods.add(food);
		}
		
		for(int i = 0; i < foods.size(); i++) {
			if(headX == foods.get(i).getX() && headY == foods.get(i).getY()) {
				snakeSize++;
				foods.remove(i);
				score++;
			}
		}
		
		for(int i = 0; i < snake.size() - 1; i++) {
			if(headX == snake.get(i).getX() && headY == snake.get(i).getY()) {
				stop();
			}
		}
	}
	
	public void paint(Graphics g) {
		g.clearRect(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		for(int i = 0; i < WIDTH / 10; i++) {
			g.drawLine(i * 10, 0, i * 10, HEIGHT);
		}
		
		for(int i = 0; i < HEIGHT / 10; i++) {
			g.drawLine(0,  i * 10, HEIGHT, i * 10);
		}
		
		for(int i = 0; i < snake.size(); i++) {
			snake.get(i).draw(g);
		}
		
		for(int i = 0; i < foods.size(); i++) {
			foods.get(i).draw(g);
		}
	}

	public void run() {
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
			stop();
			System.exit(0);
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {}
}

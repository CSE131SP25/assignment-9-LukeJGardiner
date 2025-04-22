package assignment9;

import java.awt.event.KeyEvent;
import java.util.LinkedList;

import edu.princeton.cs.introcs.StdDraw;
import java.awt.Font;

public class Game {
	Snake snake;
	Food food;
	Font font = new Font("Arial", Font.BOLD, 40);
	private int score;

	public Game() {
		StdDraw.enableDoubleBuffering();
		this.snake = new Snake();
		this.food = new Food();
		this.score = 0;
	}

	public void play() {
		while (snake.isInbounds()) {
			int dir = getKeypress();
			this.snake.changeDirection(dir);
			this.snake.move();
			if(this.snake.eatFood(food) == true) {
				this.food = new Food();
				this.score++;
			}
			updateDrawing();
		}
		LinkedList<BodySegment> segments = snake.getSegments();
		for(int i=segments.size(); i>0; i--) {
			segments.removeLast();
			updateDrawing();
			StdDraw.pause(700);
		}
		StdDraw.setFont(font);
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.text(0.5, 0.5, "GAME OVER!");
		StdDraw.show();
	}


	private int getKeypress() {
		if(StdDraw.isKeyPressed(KeyEvent.VK_W)) {
			return 1;
		} else if (StdDraw.isKeyPressed(KeyEvent.VK_S)) {
			return 2;
		} else if (StdDraw.isKeyPressed(KeyEvent.VK_A)) {
			return 3;
		} else if (StdDraw.isKeyPressed(KeyEvent.VK_D)) {
			return 4;
		} else {
			return -1;
		}
	}

	/**
	 * Clears the screen, draws the snake and food, pauses, and shows the content
	 */
	private void updateDrawing() {
		StdDraw.clear();
		this.snake.draw();
		this.food.draw();
		String str = "" + this.score;
		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.filledSquare(0.9, 0.9, 0.03);
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.text(0.84, 0.9, "Score:");
		StdDraw.text(0.9, 0.9, str);
		StdDraw.pause(50);
		StdDraw.show();
	}

	public static void main(String[] args) {
		Game g = new Game();
		g.play();
	}
}

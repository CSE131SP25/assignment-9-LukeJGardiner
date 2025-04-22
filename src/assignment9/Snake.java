package assignment9;

import java.util.LinkedList;

public class Snake {

	private static final double SEGMENT_SIZE = 0.02;
	private static final double MOVEMENT_SIZE = SEGMENT_SIZE * 1.5;
	private LinkedList<BodySegment> segments;
	private double deltaX;
	private double deltaY;

	public Snake() {
		deltaX = 0;
		deltaY = 0;
		this.segments = new LinkedList<BodySegment>();
		BodySegment head = new BodySegment(0.5, 0.5, SEGMENT_SIZE);
		segments.add(head);
	}

	public void changeDirection(int direction) {
		if(direction == 1) { //up
			deltaY = MOVEMENT_SIZE;
			deltaX = 0;
		} else if (direction == 2) { //down
			deltaY = -MOVEMENT_SIZE;
			deltaX = 0;
		} else if (direction == 3) { //left
			deltaY = 0;
			deltaX = -MOVEMENT_SIZE;
		} else if (direction == 4) { //right
			deltaY = 0;
			deltaX = MOVEMENT_SIZE;
		}
	}

	/**
	 * Moves the snake by updating the position of each of the segments
	 * based on the current direction of travel
	 */
	public void move() {
		BodySegment head = segments.getFirst();
		for(int i=segments.size()-1; i>0; i--) {
			double xOld = segments.get(i-1).getX();
			segments.get(i).setX(xOld);
			double yOld = segments.get(i-1).getY();
			segments.get(i).setY(yOld);
		}	
		head.setX(head.getX() + deltaX);
		head.setY(head.getY() + deltaY);
	}



	/**
	 * Draws the snake by drawing each segment
	 */
	public void draw() {
		for(BodySegment i : segments) {
			i.draw();
		}
	}

	/**
	 * The snake attempts to eat the given food, growing if it does so successfully
	 * @param f the food to be eaten
	 * @return true if the snake successfully ate the food
	 */
	public boolean eatFood(Food f) {
		double xf = f.getX();
		double yf = f.getY();
		double xs = segments.getFirst().getX();
		double ys = segments.getFirst().getY();
		double distSquared = (xf-xs) * (xf-xs) + (yf-ys) * (yf-ys);
		double d = Math.sqrt(distSquared);
		double edgeToEdge = SEGMENT_SIZE + SEGMENT_SIZE; //FOOD_SIZE = SEGMENT_SIZE
		if(d<edgeToEdge) {
			double newX = segments.getLast().getX();
			double newY = segments.getLast().getY();
			BodySegment i = new BodySegment(newX-deltaX, newY-deltaY, SEGMENT_SIZE);
			segments.add(i);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Returns true if the head of the snake is in bounds
	 * @return whether or not the head is in the bounds of the window
	 */
	public boolean isInbounds() {
		double x = segments.getFirst().getX();
		double y = segments.getFirst().getY();
		if((x + SEGMENT_SIZE) >= 1) {
			return false;
		}
		else if ((x - SEGMENT_SIZE) <= 0) {
			return false;
		}
		else if((y + SEGMENT_SIZE) >= 1) {
			return false;
		}
		else if ((y - SEGMENT_SIZE) <= 0) {
			return false;
		} else {
			return true;
		}
	}
	
	public LinkedList<BodySegment> getSegments() {
		return this.segments;
	}
	
}

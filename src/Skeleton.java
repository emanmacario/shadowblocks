/* SWEN20003 Object Oriented Software Development
 * Project 2 - Shadow Blocks
 * 
 * Author: Emmanuel Macario <macarioe>
 * Student Number: 831659
 * Email: macarioe@student.unimelb.edu.au
 * 
 * This file contains the Skeleton class, which is
 * a passive unit that can kill the Player.
 */


public class Skeleton extends Movable {
	
	/** Skeleton attributes.
	 */
	private int direction;
	private Timer timer;
	
	
	/** Creates a new Skeleton, with a default direction
	 * of up, and a default movement interval of 1.0 secs.
	 * 
	 * @param x  The sprite's x-coordinate, in pixels.
	 * @param y  The sprite's y-coordinate, in pixels.
	 */
	public Skeleton(float x, float y) {
		super("res/skull.png", x, y);
		this.direction = DIR_UP;
		this.timer = new Timer(1000);
		this.addTag("Enemy");
	}
		
	
	/** Updates the skeleton's direction if blocked,
	 * and moves if possible.
	 * 
	 * @param world  The world object the skeleton belongs to.
	 * @param delta  Time passed since last frame (milliseconds).
	 * @return void
	 */
	@Override
	public void update(World world, int delta) {
		
		/* Update the timer. 
		 */
		this.timer.update(delta);
		
		
		/* Check if the skeleton's next position
		 * is blocked, and reverse direction if so.
		 */
		float testX = this.getTestX(this.getX(), this.direction);
		float testY = this.getTestY(this.getY(), this.direction);
		
		if (world.isBlocked(testX, testY)) {
			this.reverseDirection();
			
			/* Test that the reverse direction is not blocked 
			 * as well. If it is, then the skeleton is trapped.
			 */
			testX = getTestX(getX(), direction);
			testY = getTestY(getY(), direction);
			
			if (world.isBlocked(testX, testY)) {
				timer.reset();
			}
		}
		
		/* Reset the timer if it expires. 
		 */
		if (timer.expired()) {
			this.moveToDestination(this.direction);
			timer.reset();
		}
	}
	
	
	/** If the Skeleton was blocked and could not 
	 * move, reverse its current direction, and
	 * reset its movement interval timer.
	 */
	private void reverseDirection() {
		
		if (this.direction == DIR_UP) {
			this.direction = DIR_DOWN;
		} else {
			this.direction = DIR_UP;
		}
		timer.reset();
	}
}
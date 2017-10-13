/* SWEN20003 Object Oriented Software Development
 * Project 2 - Shadow Blocks
 * 
 * Author: Emmanuel Macario <macarioe>
 * Student Number: 831659
 * Email: macarioe@student.unimelb.edu.au
 * 
 * This file contains the Mage class, which is 
 * a unit that aims to kill the Player.
 */


public class Mage extends Movable {
	
	/** Mage constants (to avoid magic numbers).
	 */
	private static final int NEG_ONE = -1;
	private static final int POS_ONE = 1;
	
	
	/** Mage attributes.
	 */
	private int direction;
	private float distX;
	private float distY;
	
	
	/** Creates a new Mage object, with no
	 * current direction.
	 * 
	 * @param x  The sprite's x-coordinate, in pixels.
	 * @param y  The sprite's y-coordinate, in pixels.
	 */
	public Mage(float x, float y) {
		super("res/mage.png", x, y);
		this.direction = DIR_NONE;
		this.addTag("Enemy");
	}
	
	
	/** If the player moved, run the Mage algorithm. Then,
	 * move if possible.
	 * 
	 * @param world  The World object the sprite belongs to.
     * @param delta  Time passed since last frame (milliseconds).
     * @return void
     */	
	@Override
	public void update(World world, int delta) {
		
		/* Run the algorithm if player has moved.
		 */
		if (world.getPlayerMoved()) {
			Sprite player = world.getSpriteOfType("Player");
			
			this.update(player.getX(), player.getY());
			
			/* Calculate mage's new candidate position. 
			 */
			float testX = this.getTestX(this.getX(), this.direction);
			float testY = this.getTestY(this.getY(), this.direction);
			
			/* Move if possible.
			 */
			if (!world.isBlocked(testX, testY)) {
				this.moveToDestination(this.direction);
			}
		}
	}
	
	
	/** Mage algorithm, used to track down the player. The
	 * algorithm should be ran each time the player moves.
	 * 
	 * @param playerX  The x-coordinate of the player, in pixel units.
	 * @param playerY  The y-coordinate of the player, in pixel units.
	 * @return void
	 */
	private void update(float playerX, float playerY) {
		
		/* Calculate distances between
		 * player and mage.
		 */
		distX = playerX - this.getX();
		distY = playerY - this.getY();
		
		
		/* Set the mages direction based on
		 * the calculated distances.
		 */
		if (Float.compare(Math.abs(distX), Math.abs(distY)) > 0) {
						
			if (sign(distX) == NEG_ONE) {
				this.direction = DIR_LEFT;
			} else {
				this.direction = DIR_RIGHT;
			}
			
		} else {
						
			if (sign(distY) == NEG_ONE) {
				this.direction = DIR_UP;
			} else {
				this.direction = DIR_DOWN;
			}
		}		
 	}
	
	
	/** Helper method to calculate sign of a float distance.
	 * 
	 * @param x
	 * @return -1 if x is less than 0, else 1.
	 */
	private int sign(float x) {
		if (Float.compare(x, 0.0f) < 0) {
			return NEG_ONE;
		}
		return POS_ONE;
	}
}
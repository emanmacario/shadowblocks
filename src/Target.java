/* SWEN20003 Object Oriented Software Development
 * Project 2 - Shadow Blocks
 * 
 * Author: Emmanuel Macario <macarioe>
 * Student Number: 831659
 * Email: macarioe@student.unimelb.edu.au
 * 
 * This file contains the Target class.
 */


public class Target extends Sprite {
	
	/** Target attributes.
	 */
	private boolean activated;
	

	/** Creates a new Target sprite.
	 * 
	 * @param x  The sprite's x-coordinate, in pixels.
	 * @param y  The sprite's y-coordinate, in pixels.
	 */
	public Target(float x, float y) {
		super("res/target.png", x, y);
		this.activated = false;
	}
	
	
	/** Returns whether a target is activated.
	 * 
	 * @param void
	 * @return True if activated, else false.
	 */
	public boolean getActivated() {
		return this.activated;
	}
	
	
	/** Updates whether a target is activated, by
	 * checking if there is a block currently on
	 * the target.
	 * 
	 * @param world  The world object the target belongs to.
	 * @param delta  Time passed since last frame (milliseconds).
	 */
	@Override
	public void update(World world, int delta) {
		
		Sprite block = world.getSpriteOfType("Block", 
										this.getX(), this.getY());
		
		if (block != null) {
			this.activated = true;
		} else {
			this.activated = false;
		}
	}
}

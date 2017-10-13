/* SWEN20003 Object Oriented Software Development
 * 
 * Author: Emmanuel Macario <macarioe>
 * Student Number: 831659
 * Email: macarioe@student.unimelb.edu.au
 * 
 * This file contains the Stone class, which is
 * a block that can be pushed by units.
 */


public class Stone extends Pushable {
		
	/** Creates a new Stone sprite.
	 * 
	 * @param x  The sprite's x-coordinate, in pixels.
	 * @param y  The sprite's y-coordinate, in pixels.
	 */
	public Stone(float x, float y) {
		super("res/stone.png", x, y);
		this.addTag("Block");
		this.addTag("Blocked");
	}
}

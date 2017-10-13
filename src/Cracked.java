/* SWEN20003 Object Oriented Software Development
 * Project 2 - Shadow Blocks
 * 
 * Author: Emmanuel Macario <macarioe>
 * Student Number: 831659
 * Email: macarioe@student.unimelb.edu.au
 * 
 * This file contains the Cracked class, which
 * is an in game wall that can be destroyed by
 * TNT.
 */

public class Cracked extends Sprite {
	
	/** Creates a new Cracked object.
	 *
	 * @param x  The sprite's x-coordinate, in pixels.
	 * @param y  The sprite's y-coordinate, in pixels.
	 */
	public Cracked(float x, float y) {
		super("res/cracked_wall.png", x, y);
		this.addTag("Blocked");
		this.addTag("Cracked");
	}
}

/* SWEN20003 Object Oriented Software Development
 * Project 2 - Shadow Blocks
 * 
 * Author: Emmanuel Macario <macarioe>
 * Student Number: 831659
 * Email: macarioe@student.unimelb.edu.au
 * 
 * This file contains the Floor class, which 
 * represents a game tile which all units
 * can freely move through.
 */

public class Floor extends Sprite {
	
	/** Creates a new Floor sprite.
	 * 
	 * @param x  The sprite's x-coordinate, in pixels.
	 * @param y  The sprite's y-coordinate, in pixels.
	 */
	public Floor(float x, float y) {
		super("res/floor.png", x, y);
	}
}

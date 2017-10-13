/* SWEN20003 Object Oriented Software Development
 * Project 2 - Shadow Blocks
 * 
 * Author: Emmanuel Macario <macarioe>
 * Student Number: 831659
 * Email: macarioe@student.unimelb.edu.au
 * 
 * This file contains the Wall class, which is
 * responsible for blocking the player from leaving
 * the game map bounds.
 */


public class Wall extends Sprite {
	
	/** Creates a new Wall sprite.
	 */
	public Wall(float x, float y) {
		super("res/wall.png", x, y);
		this.addTag(Constant.BLOCKED_TAG);
	}
}
